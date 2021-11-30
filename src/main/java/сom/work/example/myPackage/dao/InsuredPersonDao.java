package сom.work.example.myPackage.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.util.ConnectionDB;
import сom.work.example.myPackage.util.GetDate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class InsuredPersonDao{

    private int numberContract;

    public InsuredPersonDao(int numberContract) {
        this.numberContract = numberContract;
    }

    @SneakyThrows
    public void create(InsuredPerson person) {
        String sqlForInsuredPeople = "INSERT INTO insured_people(first_name, last_name, middle_name, birthday, INN, price, number_contract) VALUES(?, ? ,?, ?, ?, ?, ?)";
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsuredPeople);

        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getMiddleName());
        preparedStatement.setDate(4, new Date(person.getBirthday().getTimeInMillis()));
        preparedStatement.setString(5, checkINN(person.getINN()));
        preparedStatement.setDouble(6, person.getPrice());
        preparedStatement.setInt(7, numberContract);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement, connection);
    }

    @SneakyThrows
    public InsuredPerson read(String INN) {
        String sqlForInsuredPerson = "SELECT first_name, last_name, middle_name, birthday, INN, price FROM insured_people WHERE INN = ?";
        InsuredPerson person = null;
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsuredPerson);
        preparedStatement.setString(1, INN);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            person = new InsuredPerson.Builder()
                    .withFirstName(set.getString("first_name"))
                    .withLastName(set.getString("last_name"))
                    .withMiddleName(set.getString("middle_name"))
                    .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                    .withINN(set.getString("INN"))
                    .withPrice(set.getDouble("price")).build();
        }
        ConnectionDB.closeConnection(preparedStatement, connection, set);

        return person;
    }

    @SneakyThrows
    public void update(String INN, Map<String, Object> updated) {
        StringBuilder stringBuilder = new StringBuilder();
        Connection connection = ConnectionDB.getConnection();

        stringBuilder.append("UPDATE insured_people SET ");
        for (Map.Entry<String, Object> pair : updated.entrySet()){
            stringBuilder.append(pair.getKey())
                    .append(" = '")
                    .append(pair.getValue())
                    .append("',");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                .append(" WHERE INN = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        preparedStatement.setString(1, INN);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement, connection);
    }

    @SneakyThrows
    public void delete(String INN) {
        Connection connection = ConnectionDB.getConnection();
        String sqlForInsuredPeople = "DELETE FROM insured_people WHERE INN = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsuredPeople);
        preparedStatement.setString(1, INN);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement,connection);
    }

    public void createAll(List<InsuredPerson> insuredPersonList){
        for (InsuredPerson person : insuredPersonList){
            create(person);
        }
    }

    @SneakyThrows
    public List<InsuredPerson> readAll(int numberContract){
       List<InsuredPerson> list = new ArrayList<>();
        String sqlForInsuredPerson = "SELECT first_name, last_name, middle_name, birthday, INN, price FROM insured_people WHERE number_contract = ?";
        InsuredPerson person = null;
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsuredPerson);
        preparedStatement.setInt(1, numberContract);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()) {
            person = new InsuredPerson.Builder()
                    .withFirstName(set.getString("first_name"))
                    .withLastName(set.getString("last_name"))
                    .withMiddleName(set.getString("middle_name"))
                    .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                    .withINN(set.getString("INN"))
                    .withPrice(set.getDouble("price")).build();
            list.add(person);
        }
        ConnectionDB.closeConnection(preparedStatement, connection, set);

        return list;
    }

    @SneakyThrows
    public void deleteAll(int numberContract) {
        Connection connection = ConnectionDB.getConnection();
        String sqlForInsuredPeople = "DELETE FROM insured_people WHERE number_contract = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForInsuredPeople);
        preparedStatement.setInt(1, numberContract);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement,connection);
    }

    public static String checkINN(String INN) throws SQLException {
        String tempINN = INN;
        String sql = "SELECT INN FROM insured_people WHERE INN = ?";
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, INN);
        ResultSet set = statement.executeQuery();
        while (set.next()){
            if (set.getString("INN").equals(INN))
                System.out.println("Человек с таким ИНН существует, проверьте введенный данные");
            tempINN = null;
        }
       ConnectionDB.closeConnection(statement, connection, set);

        return tempINN;
    }


}
