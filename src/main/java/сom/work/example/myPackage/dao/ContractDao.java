package сom.work.example.myPackage.dao;

import lombok.SneakyThrows;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.util.ConnectionDB;
import сom.work.example.myPackage.util.GetDate;

import java.sql.*;

public class ContractDao extends CRUDTest<Contract> {

    private static final String CREATE = "INSERT INTO contracts(number, date_conclusion, start_contract, finish_contract, id_client) VALUES (?, ?, ?, ?, ?)";
    private static final String READ = "SELECT date_conclusion, start_contract, finish_contract FROM contracts WHERE number = ?";
    private static final String UPDATE = "UPDATE contracts SET number = ?, date_conclusion = ?, start_contract = ?, finish_contract = ?, id_client = ? WHERE number = ?";
    private static final String DELETE = "DELETE FROM contracts WHERE number = ?";
    private static final String SELECT_CHECK = "SELECT id ,number FROM contracts WHERE number = ?";

    public long create(Contract contract) {
        long id = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            if (checkFullNumber(contract.getNumber()) != null){

                setValue(preparedStatement, contract);
                preparedStatement.executeUpdate();

                set = preparedStatement.getGeneratedKeys();
                set.next();
                id = set.getInt(1);
            }else{
                id = getId(contract.getNumber(),SELECT_CHECK , "number");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }
        return id;
    }

    public Contract read(String numberContract) {
        Contract contract = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, numberContract);
            set = preparedStatement.executeQuery();
            while (set.next()) {
                contract = new Contract.Builder()
                        .withDateConclusion(GetDate.getDate(set.getDate("date_conclusion").toString()))
                        .withStartContract(GetDate.getDate(set.getDate("start_contract").toString()))
                        .withFinishContract(GetDate.getDate(set.getDate("finish_contract").toString())).build();
                contract.setNumber(numberContract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, set);
        }

        return contract;
    }


    public void update(Contract contract) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            setValue(preparedStatement, contract);

            preparedStatement.setString(6, contract.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(preparedStatement, connection, null);
        }
    }

    @SneakyThrows
    public void delete(String numberContract) {
        delete(numberContract, DELETE);
    }

    @SneakyThrows
    protected void setValue(PreparedStatement preparedStatement, Contract contract) {
        preparedStatement.setString(1, contract.getNumber());
        preparedStatement.setDate(2, new Date(contract.getDateConclusion().getTimeInMillis()));
        preparedStatement.setDate(3, new Date(contract.getStartContract().getTimeInMillis()));
        preparedStatement.setDate(4, new Date(contract.getFinishContract().getTimeInMillis()));
        preparedStatement.setLong(5, contract.getClient().getId());
    }

    protected  String checkFullNumber(String number){
        return checkUniqueness(number, SELECT_CHECK, "number");
    }

}

