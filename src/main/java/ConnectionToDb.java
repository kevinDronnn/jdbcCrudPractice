import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDb {
    private static final String url = "jdbc:mysql://localhost:3306/my_db?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "Artem08082003";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
