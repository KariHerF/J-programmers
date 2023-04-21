package grupofp.controlador;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExample {
	 public static void main(String[] args) {

	      // Obtener la conexión a la base de datos
	      try (Connection conexion = Conexion.getConnection()) { //Aqui no me detecta quizás que tengo el java.sql.Connection

	         // Crear una sentencia
	         Statement sentencia = conexion.createStatement();

	         // Definir la sentencia SQL para crear una tabla (esta es de ejemplo)
	         String sql = "CREATE TABLE articulos " +
	            "(id INT AUTO_INCREMENT, " +
	            "codigo VARCHAR(50) NOT NULL, " +
	            "descripcion VARCHAR(50) NOT NULL,"+
	            "pvp DOUBLE NOT NULL,"+
	            "tiempoPrep DOUBLE NOT NULL,"+
	            	"gastosEnvio DOUBLE NOT NULL)";
	         // Ejecutar la sentencia SQL
	         sentencia.executeUpdate(sql);
	         System.out.println("Tabla creada correctamente.");

	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

}
