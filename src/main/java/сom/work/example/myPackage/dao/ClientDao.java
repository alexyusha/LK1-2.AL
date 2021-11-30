package сom.work.example.myPackage.dao;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import сom.work.example.myPackage.dict.CRUD;
import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

@Getter
@Setter
public class ClientDao implements CRUD<Client> {
    private  int numberContract;
    //?
    public ClientDao(int numberContract) {
        this.numberContract = numberContract;
    }

    @SneakyThrows
    @Override
    public void create(Client client) {
        Connection connection = ConnectionDB.getConnection();
        String sqlForClient = "INSERT INTO clients VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForClient);
        preparedStatement.setInt(1, numberContract);
        preparedStatement.setString(2,client.getTypeClient().toString());
        preparedStatement.setString(3, client.getName());
        preparedStatement.setString(4, client.getAddress());
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement,connection);
    }

    @SneakyThrows
    @Override
    public Client read(int numberContract) {
        Connection connection = ConnectionDB.getConnection();
        Client client = null;
        String sqlForClient = "SELECT type_client, name, address FROM clients WHERE number_contract = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForClient);
        preparedStatement.setInt(1, numberContract);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            client = new Client.Builder()
                    .withTypeClient((set.getString("type_client").equals("INDIVIDUAL")) ? TypeClient.INDIVIDUAL : TypeClient.ENTITY)
                    .withName(set.getString("name"))
                    .withAddress(set.getString("address")).build();
        }

        ConnectionDB.closeConnection(preparedStatement, connection, set);

        return client;
    }

    @SneakyThrows
    @Override
    public void update(int numberContract, Map<String, Object> updated) {
        StringBuilder stringBuilder = new StringBuilder();
        Connection connection = ConnectionDB.getConnection();

        stringBuilder.append("UPDATE clients SET ");
        for (Map.Entry<String, Object> pair : updated.entrySet()){
            stringBuilder.append(pair.getKey())
                    .append(" = '")
                    .append(pair.getValue())
                    .append("',");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                .append(" WHERE number_contract = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        preparedStatement.setInt(1, numberContract);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement, connection);
    }

    @SneakyThrows
    @Override
    public void delete(int numberContract) {
    Connection connection = ConnectionDB.getConnection();
    String sqlForClient = "DELETE FROM clients WHERE number_contract = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sqlForClient);
    preparedStatement.setInt(1, numberContract);
    preparedStatement.executeUpdate();

    ConnectionDB.closeConnection(preparedStatement,connection);

    }
}
