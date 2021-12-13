package сom.work.example.myPackage.util;

import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
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

    @SneakyThrows
    public static void closeConnection(PreparedStatement preparedStatement, Connection connection, ResultSet resultSet) {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void createDB() {

    }
}
