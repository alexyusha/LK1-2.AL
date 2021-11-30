package сom.work.example.myPackage.dao;

import lombok.SneakyThrows;
import сom.work.example.myPackage.dict.CRUD;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.util.ConnectionDB;
import сom.work.example.myPackage.util.GetDate;

import java.sql.*;
import java.util.Map;

public class ContractDao implements CRUD<Contract> {

    @SneakyThrows
    @Override
    public void create(Contract contract) {
        String sqlForContact = "INSERT INTO contracts VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForContact);

        preparedStatement.setInt(1, checkUniqueness(contract.getNumber()));
        preparedStatement.setDate(2, new Date(contract.getDateConclusion().getTimeInMillis()));
        preparedStatement.setDate(3, new Date(contract.getStartContract().getTimeInMillis()));
        preparedStatement.setDate(4, new Date(contract.getFinishContract().getTimeInMillis()));
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement, connection);
    }

    @SneakyThrows
    @Override
    public Contract read(int numberContract){
        Contract contract = null;
        Connection connection = ConnectionDB.getConnection();
        String sqlForContract = "SELECT date_conclusion, start_contract, finish_contract FROM contracts WHERE number = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlForContract);
        preparedStatement.setInt(1, numberContract);
        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            contract = new Contract.Builder()
                    .withNumber(numberContract)
                    .withDateConclusion(GetDate.getDate(set.getDate("date_conclusion").toString()))
                    .withStartContract(GetDate.getDate(set.getDate("start_contract").toString()))
                    .withFinishContract(GetDate.getDate(set.getDate("finish_contract").toString())).build();
        }

        ConnectionDB.closeConnection(preparedStatement, connection, set);

        return contract;
    }
    //typeObject
    @SneakyThrows
    @Override
    public void update(int numberContract, Map<String, Object> updated){
        StringBuilder stringBuilder = new StringBuilder();
        Connection connection = ConnectionDB.getConnection();

        stringBuilder.append("UPDATE contracts SET ");
        for (Map.Entry<String, Object> pair : updated.entrySet()){
            stringBuilder.append(pair.getKey())
                    .append(" = ")
                    .append(pair.getValue())
                    .append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                     .append(" WHERE number = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
        preparedStatement.setInt(1, numberContract);
        preparedStatement.executeUpdate();

        ConnectionDB.closeConnection(preparedStatement, connection);
    }

    @SneakyThrows
    @Override
    public void delete(int numberContract){
        Connection connection = ConnectionDB.getConnection();
        String sqlForContract = "DELETE FROM contracts WHERE number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlForContract);
        preparedStatement.setInt(1, numberContract);
        preparedStatement.executeUpdate();
        ConnectionDB.closeConnection(preparedStatement,connection);
    }

    public static InsuredPerson searchPerson(String INN) throws SQLException {
        InsuredPerson person = null;
        String sql = "SELECT first_name, last_name, middle_name, birthday, INN, price FROM insured_people WHERE INN = ?";
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, INN);
        ResultSet set = statement.executeQuery();
        while (set.next()){
            person = new InsuredPerson.Builder()
                    .withFirstName(set.getString("first_name"))
                    .withLastName(set.getString("last_name"))
                    .withMiddleName(set.getString("middle_name"))
                    .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                    .withINN(set.getString("INN"))
                    .withPrice(set.getDouble("price")).build();
        }

        ConnectionDB.closeConnection(statement, connection, set);

        return person;
    }

    public static  int checkUniqueness(int number) throws SQLException {
        String sql = "SELECT number FROM contracts WHERE number = ?";
        Connection connection = ConnectionDB.getConnection();;
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, number);
        ResultSet set = statement.executeQuery();
        while (set.next()){
            if (set.getInt("number") == number){
                number++;
                System.out.println(set.getInt("number"));
                return checkUniqueness(number);
            }


        }
        ConnectionDB.closeConnection(statement, connection, set);
        System.out.println("Номер контракта -" + number);

        return number;
    }
}

