import java.sql.*;

public class ConexionBD {
    private static final String URL = "jdbc:mariadb://localhost:3306/Red_Neuronal";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
