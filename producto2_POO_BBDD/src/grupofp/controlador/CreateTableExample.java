package grupofp.controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExample {

	public static void main(String[] args) {

		// Obtener la conexión a la base de datos
		try (Connection conexion = ConexionDB.conectar()) { // He importado la conexion de ConexionDB.java e
															// inicializado esa

			// Crear una sentencia
			Statement sentencia = conexion.createStatement();

			// Definir la sentencia SQL para crear la tabla de articulos
			String sql = "CREATE TABLE IF NOT EXISTS articulos " +
					"(id INT AUTO_INCREMENT, " +
					"codigo VARCHAR(50) NOT NULL, " +
					"descripcion VARCHAR(50) NOT NULL," +
					"pvp DOUBLE NOT NULL," +
					"tiempoPrep DOUBLE NOT NULL," +
					"gastosEnvio DOUBLE NOT NULL)";
			// Ejecutar la sentencia SQL
			try {
				sentencia.executeUpdate(sql);
				System.out.println("Tabla creada correctamente.");
			} catch (SQLException e) {
				System.out.println("Ha ocurrido el siguiente error al crear la tabla articulos: " + e.getMessage());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
