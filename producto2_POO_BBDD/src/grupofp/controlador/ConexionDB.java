package grupofp.controlador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
	 public static Connection conectar() throws SQLException {
	        
	        Connection conn = null;
	        String url = "jdbc:mysql://localhost:3306/jp_programmers_db";
	        String user = "root";
	        String password = "";
	        
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection(url, user, password);
	            System.out.println("Conexión exitosa a la instancia de MySQL");
	        } catch (ClassNotFoundException e) {
	            System.out.println("Error al cargar el controlador de MySQL: " + e.getMessage());
	        } catch (SQLException e) {
	            System.out.println("Error al conectar a la instancia de MySQL: " + e.getMessage());
	        }
	        
	        return conn;
	    }

}
