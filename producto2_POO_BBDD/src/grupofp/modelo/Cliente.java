package grupofp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import grupofp.controlador.ConexionDB;

public abstract class Cliente {

	/**
	 * @author J-Programmers
	 *
	 */
	private String email;
	private String nombre;
	private String domicilio;
	private String nif;

	public Cliente(String email, String nombre, String domicilio, String nif) {
		this.setEmail(email);
		this.setNombre(nombre);
		this.setDomicilio(domicilio);
		this.setNif(nif);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract String tipoCliente();

	public abstract float calcAnual();

	public abstract float descuentoEnv();

	public void insertarCliente(Cliente cliente) throws SQLException {
		String query = "INSERT INTO clientes (email, nombre, domicilio, nif, tipoCliente, cuotaAnual, dtoGtoEnvio) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection connection = ConexionDB.conectar();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, cliente.getEmail());
		statement.setString(2, cliente.getNombre());
		statement.setString(3, cliente.getDomicilio());
		statement.setString(4, cliente.getNif());
		statement.setString(5, cliente.tipoCliente());
		if (cliente.tipoCliente() == "Estandar") {
			statement.setNull(6, 0);
			statement.setNull(7, 0);
		} else {
			statement.setDouble(6, cliente.calcAnual());
			statement.setDouble(5, cliente.descuentoEnv());
		}

		statement.executeUpdate();
		statement.close();
	}

	/**
	 *
	 */
	@Override
	public abstract String toString();

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email);
	}

}
