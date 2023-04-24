package grupofp.controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	public static void main(String[] args) throws SQLException {
        
        Connection conn = null;
        Statement stmt = null;
        String url = "jdbc:mysql://localhost:3306";
        String user = "root";
        String password = "";
        String nombreBD = "jp_programmers_db";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE " + nombreBD;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos " + nombreBD + " creada exitosamente");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al crear la base de datos en MySQL: " + e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
