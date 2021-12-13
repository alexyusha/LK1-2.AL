package сom.work.example.myPackage.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.util.ConnectionDB;
import сom.work.example.myPackage.util.GetDate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InsuredPersonDao extends CRUDTest<InsuredPerson> {

    private static final String CREATE = "INSERT INTO insured_people(first_name, last_name, middle_name, birthday, INN, price, number_contract) VALUES(?, ? ,?, ?, ?, ?, ?)";
    private static final String READ = "SELECT first_name, last_name, middle_name, birthday, INN, price, number_contract FROM insured_people WHERE INN = ?";
    private static final String UPDATE = "UPDATE insured_people SET first_name = ?, last_name = ?, middle_name = ?, birthday = ?, INN = ?, price = ?, number_contract = ? WHERE INN = ?";
    private static final String DELETE = "DELETE FROM insured_people WHERE INN = ?";
    private static final String SELECT_CHECK = "SELECT id, INN FROM insured_people WHERE INN = ?";


    public long create(InsuredPerson person) {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            if (checkINN(person.getINN()) != null) {
                setValue(preparedStatement, person);
                preparedStatement.executeUpdate();
                set = preparedStatement.getGeneratedKeys();
                set.next();
                id = set.getInt(1);
            } else {
                id = getId(person.getINN(), SELECT_CHECK, "INN");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return id;
    }

    public InsuredPerson read(String INN) {
        InsuredPerson person = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, INN);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                person = new InsuredPerson.Builder()
                        .withFirstName(set.getString("first_name"))
                        .withLastName(set.getString("last_name"))
                        .withMiddleName(set.getString("middle_name"))
                        .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                        .withINN(set.getString("INN"))
                        .withPrice(set.getDouble("price")).build();
                person.setNumberContract(set.getString("number_contract"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return person;
    }

    public void update(InsuredPerson person) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            if (checkINN(person.getINN()) == null){
                connection = ConnectionDB.getConnection();
                preparedStatement = connection.prepareStatement(UPDATE);
                setValue(preparedStatement, person);
                preparedStatement.setString(8, person.getINN());
                preparedStatement.executeUpdate();
            }
            else{
                create(person);
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

    public void createAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPeoples()) {
            create(person);
        }
    }

    public List<InsuredPerson> readAll(Contract contract) {
        List<InsuredPerson> list = new ArrayList<>();
        for (InsuredPerson person : contract.getInsuredPeoples()) {
            InsuredPerson person1 = read(person.getINN());
            if (person1 != null){
                list.add(read(person.getINN()));
            }
        }
        return list;
    }

    public void updateAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPeoples()) {
            update(person);
        }
    }

    public void deleteAll(Contract contract) {
        for (InsuredPerson person : contract.getInsuredPeoples()) {
            delete(person.getINN());
        }
    }

    String checkINN(String INN) {
        return checkUniqueness(INN, SELECT_CHECK, "INN");
    }

    @SneakyThrows
    protected void setValue(PreparedStatement preparedStatement, InsuredPerson person) {
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setString(3, person.getMiddleName());
        preparedStatement.setDate(4, new Date(person.getBirthday().getTimeInMillis()));
        preparedStatement.setString(5, person.getINN());
        preparedStatement.setDouble(6, person.getPrice());
        preparedStatement.setString(7, person.getNumberContract());
    }


}
