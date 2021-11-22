package сom.work.example.myPackage.service;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import сom.work.example.myPackage.dict.GetDate;
import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CRUD {
    private  static DataSource dataSource;

    public static Connection get() throws SQLException {
        Properties pros = new Properties();
        try {
            FileInputStream in = new FileInputStream("src\\main\\properties\\db.properties");
            pros.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource.getConnection();
    }

    public static void INSERT(Contract contract) throws SQLException {
        CREATE();
        String sqlForContact = "INSERT INTO contracts VALUES (?, ?, ?, ?)";
        String sqlForClient = "INSERT INTO clients VALUES (?, ?, ?, ?)";
        String sqlForInsuredPeople = "INSERT INTO insured_people(first_name, last_name, middle_name, birthday, INN, price, number_contract) VALUES(?, ? ,?, ?, ?, ?, ?)";
        Connection connection  = get();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForContact);
        PreparedStatement preparedStatement1 = connection.prepareStatement(sqlForClient);
        PreparedStatement preparedStatement2 = connection.prepareStatement(sqlForInsuredPeople);

        int numberOfContract = ContractServices.checkUniqueness(contract.getNumber());

        preparedStatement.setInt(1, numberOfContract);
        preparedStatement.setDate(2,  new Date(contract.getDateConclusion().getTimeInMillis()));
        preparedStatement.setDate(3, new Date(contract.getStartContract().getTimeInMillis()));
        preparedStatement.setDate(4, new Date(contract.getFinishContract().getTimeInMillis()));

        preparedStatement.executeUpdate();
        preparedStatement.close();

        //preparedStatement = get().prepareStatement(sqlForClient);
        Client client = contract.getClient();
        preparedStatement1.setInt(1, numberOfContract);
        preparedStatement1.setString(2, client.getTypeClient().toString());
        preparedStatement1.setString(3, client.getName());
        preparedStatement1.setString(4, client.getAddress());
        preparedStatement1.executeUpdate();
        preparedStatement1.close();
        List<InsuredPerson> personSet = contract.getInsuredPeoples();
        //preparedStatement = get().prepareStatement(sqlForInsuredPeople);
        for (InsuredPerson person : personSet){
            preparedStatement2.setString(1, person.getFirstName());
            preparedStatement2.setString(2, person.getLastName());
            preparedStatement2.setString(3, person.getMiddleName());
            preparedStatement2.setDate(4, new Date(person.getBirthday().getTimeInMillis()));
            preparedStatement2.setString(5, InsuredPersonServices.checkINN(person.getINN()));
            preparedStatement2.setDouble(6, person.getPrice());
            preparedStatement2.setInt(7, numberOfContract);
            preparedStatement2.executeUpdate();

        }
        connection.close();
        preparedStatement2.close();
    }

    public static Contract SELECT(int number) throws SQLException {
        List<InsuredPerson> insuredPeople = new LinkedList<>();
        Client client = null;
        Contract contract = null;

        String sqlForContract = "SELECT date_conclusion, start_contract, finish_contract FROM contracts WHERE number = ?";
        String sqlForClient = "SELECT type_client, name, address FROM clients WHERE number_contract = ?";
        String sqlForInsuredPerson = "SELECT first_name, last_name, middle_name, birthday, INN, price FROM insured_people WHERE number_contract = ?";
        Connection connection = get();
        PreparedStatement statement = connection.prepareStatement(sqlForInsuredPerson);
        statement.setInt(1, number);
        ResultSet set = statement.executeQuery();
        while (set.next()){
            InsuredPerson person = new InsuredPerson.Builder()
                    .withFirstName(set.getString("first_name"))
                    .withLastName(set.getString("last_name"))
                    .withMiddleName(set.getString("middle_name"))
                    .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                    .withINN(set.getString("INN"))
                    .withPrice(set.getDouble("price")).build();
            insuredPeople.add(person);
        }
        set.close();
        statement = connection.prepareStatement(sqlForClient);
        statement.setInt(1, number);
        ResultSet set1 = statement.executeQuery();
        while (set1.next()){
            client = new Client.Builder()
                    .withTypeClient((set1.getString("type_client").equals("INDIVIDUAL")) ? TypeClient.INDIVIDUAL : TypeClient.ENTITY)
                    .withName(set1.getString("name"))
                    .withAddress(set1.getString("address")).build();
        }
        set1.close();
        statement = connection.prepareStatement(sqlForContract);
        statement.setInt(1, number);
        ResultSet set2 = statement.executeQuery();
        while (set2.next()){
            contract = new Contract.Builder()
                    .withNumber(number)
                    .withDateConclusion(GetDate.getDate(set2.getDate("date_conclusion").toString()))
                    .withStartContract(GetDate.getDate(set2.getDate("start_contract").toString()))
                    .withFinishContract(GetDate.getDate(set2.getDate("finish_contract").toString()))
                    .withClient(client)
                    .withInsuredPeople(insuredPeople).build();
        }

        connection.close();
        set2.close();
        statement.close();
        return contract;
    }

    protected static void CREATE() throws SQLException {
        String sqlForContract = "CREATE TABLE IF NOT EXISTS contracts (" +
                "number INT NOT NULL,\n" +
                "  date_conclusion DATE DEFAULT NULL,\n" +
                "  start_contract DATE DEFAULT NULL,\n" +
                "  finish_contract DATE DEFAULT NULL,\n" +
                "  PRIMARY KEY (number))";
        String sqlForClient = "CREATE TABLE IF NOT EXISTS clients (" +
                "number_contract INT NOT NULL,\n" +
                "  type_client VARCHAR(20) DEFAULT NULL,\n" +
                "  name VARCHAR(50) DEFAULT NULL,\n" +
                "  address VARCHAR(255) DEFAULT NULL,\n" +
                "  PRIMARY KEY (number_contract))";
        String sqlForInsuredPeople = "CREATE TABLE IF NOT EXISTS insured_people (" +
                "id INT NOT NULL AUTO_INCREMENT,\n" +
                "  first_name VARCHAR(255) DEFAULT NULL,\n" +
                "  last_name VARCHAR(255) DEFAULT NULL,\n" +
                "  middle_name VARCHAR(255) DEFAULT NULL,\n" +
                "  birthday DATE DEFAULT NULL,\n" +
                "  INN VARCHAR(20) DEFAULT NULL,\n" +
                "  price DOUBLE DEFAULT NULL,\n" +
                "  number_contract INT DEFAULT NULL,\n" +
                "  PRIMARY KEY (id))";
        Connection connection = get();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlForContract);
        statement.executeUpdate(sqlForClient);
        statement.executeUpdate(sqlForInsuredPeople);
        connection.close();
        statement.close();
    }

    public static void UPDATE(){}

    public static void DELETE(int numberContract) throws SQLException {
        String sqlForContract = "DELETE FROM contracts WHERE number = ?";
        String sqlForClient = "DELETE FROM clients WHERE number_contract = ?";
        String sqlForInsuredPeople = "DELETE FROM insured_people WHERE number_contract = ?";
        Connection connection = get();
        PreparedStatement statement = connection.prepareStatement(sqlForContract);
        statement.setInt(1, numberContract);
        statement.executeUpdate();

        statement = connection.prepareStatement(sqlForClient);
        statement.setInt(1, numberContract);
        statement.executeUpdate();

        statement = connection.prepareStatement(sqlForInsuredPeople);
        statement.setInt(1, numberContract);
        statement.executeUpdate();

        connection.close();
        statement.close();
    }

}
