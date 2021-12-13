package сom.work.example.myPackage.dao;


import lombok.SneakyThrows;
import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.util.ConnectionDB;

import java.sql.*;

public class ClientDao extends CRUDTest<Client> {

    private static final String CREATE = "INSERT INTO clients(type_client, name, address, INN) VALUES ( ?, ?, ?, ?)";
    private static final String READ = "SELECT id, type_client, name, address, INN FROM clients WHERE INN = ?";
    private static final String UPDATE = "UPDATE clients SET type_client = ?, name = ?, address = ?, INN = ? WHERE INN = ?";
    private static final String DELETE = "DELETE FROM clients WHERE INN = ?";
    private static final String SELECT_CHECK = "SELECT id, INN FROM clients WHERE INN = ?";


    public long create(Client client) {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            if (checkINN(client.getINN()) != null){

                setValue(preparedStatement, client);
                preparedStatement.executeUpdate();

                set = preparedStatement.getGeneratedKeys();
                set.next();
                id = set.getInt(1);
                client.setId(id);
            }else{
                id = getId(client.getINN(), SELECT_CHECK, "INN");
                client.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return id;
    }

    public Client read(String INN) {
        Client client = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, INN);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                client = new Client.Builder()
                        .withTypeClient((set.getString("type_client").equals("INDIVIDUAL")) ? TypeClient.INDIVIDUAL : TypeClient.ENTITY)
                        .withName(set.getString("name"))
                        .withAddress(set.getString("address"))
                        .withINN(set.getString("INN")).build();
                client.setId(set.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }

        return client;
    }

    public void update(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (checkINN(client.getINN())==null){
                connection = ConnectionDB.getConnection();
                preparedStatement = connection.prepareStatement(UPDATE);
                setValue(preparedStatement, client);
                preparedStatement.setString(5, client.getINN());
                preparedStatement.executeUpdate();
            }else{
                create(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, null);
        }
    }


    public void delete(String INN) {
        delete(INN, DELETE);
    }

    @SneakyThrows
    protected void setValue(PreparedStatement preparedStatement, Client client) {
        preparedStatement.setString(1, client.getTypeClient().toString());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getAddress());
        preparedStatement.setString(4, client.getINN());
    }

   protected String checkINN(String INN) {
        return checkUniqueness(INN, SELECT_CHECK, "INN");
    }
}
