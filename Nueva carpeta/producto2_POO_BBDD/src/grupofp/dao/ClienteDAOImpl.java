package grupofp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.ListaClientes;
import grupofp.modelo.Pedido;

public class ClienteDAOImpl implements ClienteDAO {
	
	private static final  String CLIENTE_ESTANDAR = "estandar";
	private static final String CLIENTE_PREMIUM = "premium";

	private Connection conn;

	public ClienteDAOImpl(Connection conn) {
		this.conn = conn;
	}

	// Método que se encarga de cerrar la conexión con la base de datos
	public void close() throws SQLException {
		if (this.conn != null) {
			this.conn.close();
		}
	}

	@Override
	public void insertarCliente(Cliente cliente) throws DAOException, SQLException {
		PreparedStatement statement = null;

		try {
			String sql = "INSERT INTO clientes (email, nombre, domicilio, nif, tipo_cliente) VALUES (?, ?, ?, ?, ?)";

			statement = conn.prepareStatement(sql);
			statement.setString(1, cliente.getEmail());
			statement.setString(2, cliente.getNombre());
			statement.setString(3, cliente.getDomicilio());
			statement.setString(4, cliente.getNif());
			statement.setInt(5, this.obtenerIdClientePorTipoCliente(cliente.tipoCliente()));
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error al insertar el cliente en la base de datos: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}

	}

	/**
	 * @throws SQLException
	 * @Override public void actualizarCliente(Cliente cliente) throws DAOException
	 *           { String sql = "UPDATE clientes SET nombre = ?, domicilio = ?, nif
	 *           = ?, tipo_cliente = ? WHERE email = ?"; try (PreparedStatement ps =
	 *           conn.prepareStatement(sql)) { ps.setString(1, cliente.getNombre());
	 *           ps.setString(2, cliente.getDomicilio()); ps.setString(3,
	 *           cliente.getNif()); ps.setString(4, cliente.tipoCliente());
	 *           ps.setString(5, cliente.getEmail()); ps.executeUpdate(); } catch
	 *           (SQLException e) { throw new DAOException("Error al actualizar el
	 *           cliente en la base de datos: ", e); } }
	 * 
	 * @Override public void eliminarCliente(String email) throws DAOException {
	 *           String sql = "DELETE FROM clientes WHERE email = ?"; try
	 *           (PreparedStatement ps = conn.prepareStatement(sql)) {
	 *           ps.setString(1, email); ps.executeUpdate(); } catch (SQLException
	 *           e) { throw new DAOException("Error al eliminar el cliente de la
	 *           base de datos: ", e); } }
	 **/

	
	
	@Override
	public float obtenerCuotaPorTipoCliente(String tipo_cliente) throws DAOException, SQLException {
		Cliente cliente = null;
		PreparedStatement statement = null;
		float cuota_anual = 0.00f;

		try {
			String sql = "SELECT cuota_anual "
					+ "FROM tipos_cliente "
					+ "WHERE descripcion = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, tipo_cliente);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					// Obtenemos la cuota anual que debe de pagar un cliente del tipo_cliente indicado
					cuota_anual =  rs.getFloat("cuota_anual");
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener la cuota anual de un tipo de cliente desde bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
			}
		}
		return cuota_anual;
	}
	
	
	@Override
	public float obtenerDescuentoEnvioPorTipoCliente(String tipo_cliente) throws DAOException, SQLException {
		Cliente cliente = null;
		PreparedStatement statement = null;
		float descuento_envio = 0.00f;

		try {
			String sql = "SELECT tipos_cliente.descuento_envio "
					+ "FROM tipos_cliente "
					+ "WHERE descripcion = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, tipo_cliente);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					// Obtenemos el descuento de envio que tiene un cliente del tipo_cliente indicado
					descuento_envio =  rs.getFloat("tipos_cliente.descuento_envio");
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener el descuento de envio de un tipo de cliente desde bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
			}
		}
		return descuento_envio;
	}
	
	@Override
	public int obtenerIdClientePorTipoCliente(String tipo_cliente) throws DAOException, SQLException {
		
		PreparedStatement statement = null;
		int id_cliente = 0;

		try {
			String sql = "SELECT id_tipo_cliente "
					+ "FROM tipos_cliente "
					+ "WHERE descripcion = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, tipo_cliente);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					// Obtenemos el id del tipo_cliente indicado
					id_cliente =  rs.getInt("id_tipo_cliente");
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener el id de un tipo de cliente desde bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
			}
		}
		return id_cliente;
	}
	
	
	@Override
	public Cliente obtenerCliente(String email) throws DAOException, SQLException {
		Cliente cliente = null;
		PreparedStatement statement = null;

		try {
			String sql = "SELECT clientes.nombre,clientes.domicilio,clientes.nif, "
					+ "tipos_cliente.descripcion, tipos_cliente.cuota_anual,tipos_cliente.descuento_envio "
					+ "FROM clientes " + "INNER JOIN tipos_cliente "
					+ "ON clientes.tipo_cliente = tipos_cliente.id_tipo_cliente " + "WHERE clientes.email = ?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, email);

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					String nombre = rs.getString("clientes.nombre");
					String domicilio = rs.getString("clientes.domicilio");
					String nif = rs.getString("clientes.nif");
					String tipo_cliente = rs.getString("tipos_cliente.descripcion");
					float cuota_anual = rs.getFloat("tipos_cliente.cuota_anual");
					float descuento_envio = rs.getFloat("tipos_cliente.descuento_envio");
					// Instanciamos el cliente según su tipo
					try {
						if (tipo_cliente.equals(CLIENTE_ESTANDAR)) {
							cliente = new ClienteEstandar(email, nombre, domicilio, nif, tipo_cliente, cuota_anual,
									descuento_envio);
						} else if (tipo_cliente.equals(CLIENTE_PREMIUM)) {
							cliente = new ClientePremium(email, nombre, domicilio, nif, tipo_cliente, cuota_anual,
									descuento_envio);
						} else {
							// TODO: POSIBLE CASO PARA LANZAR UNA EXCEPCIÓN PERSSONALIZADA
							// (Se tiene que en las tablas de bd un cliente se ha relacionado con un tipo
							// que no está contemplando en el modelo)
							cliente.toString();
							System.out
									.println("El cliente tiene asignado un tipo de cliente no soportado por el modelo. "
											+ "Debe ser \"estandar\" o \"premium\", ya que son los únicos tipos de cliente soportados por el momento."
											+ " Se aconseja revisar la tabla clientes y tipos_cliente de la base de datos para hacer la limpieza de datos correspondiente.");
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println(ex);
					}

				}
				return cliente;
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener el cliente de la base de datos: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
			}
		}

	}

	@Override
	public ListaClientes obtenerTodosClientes() throws DAOException, SQLException {
		Cliente cliente = null;
		PreparedStatement statement = null;

		try {
			String sql = "SELECT DISTINCT clientes.email, clientes.nombre, clientes.domicilio, clientes.nif, "
					+ "tipos_cliente.descripcion, tipos_cliente.cuota_anual, tipos_cliente.descuento_envio "
					+ "FROM clientes " + "INNER JOIN tipos_cliente "
					+ "ON clientes.tipo_cliente = tipos_cliente.id_tipo_cliente ";
			statement = conn.prepareStatement(sql);
			ListaClientes clientes = new ListaClientes();

			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					String email = rs.getString("clientes.email");
					String nombre = rs.getString("clientes.nombre");
					String domicilio = rs.getString("clientes.domicilio");
					String nif = rs.getString("clientes.nif");
					String tipo_cliente = rs.getString("tipos_cliente.descripcion");
					float cuota_anual = rs.getFloat("tipos_cliente.cuota_anual");
					float descuento_envio = rs.getFloat("tipos_cliente.descuento_envio");
					// Instanciamos el cliente según su tipo
					try {
						if (tipo_cliente.equals(CLIENTE_ESTANDAR)) {
							cliente = new ClienteEstandar(email, nombre, domicilio, nif, tipo_cliente, cuota_anual,
									descuento_envio);
							clientes.add(cliente);
						} else if (tipo_cliente.equals(CLIENTE_PREMIUM)) {
							cliente = new ClientePremium(email, nombre, domicilio, nif, tipo_cliente, cuota_anual,
									descuento_envio);
							clientes.add(cliente);
						} else {
							// TODO: POSIBLE CASO PARA LANZAR UNA EXCEPCIÓN PERSSONALIZADA
							// (Se tiene que en las tablas de bd un cliente se ha relacionado con un tipo
							// que no está contemplando en el modelo)
							cliente.toString();
							System.out
									.println("El cliente tiene asignado un tipo de cliente no soportado por el modelo. "
											+ "Debe ser \"estandar\" o \"premium\", ya que son los únicos tipos de cliente soportados por el momento."
											+ " Se aconseja revisar la tabla clientes y tipos_cliente de la base de datos para hacer la limpieza de datos correspondiente.");
						}

					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println(ex);
					}

				}
				return clientes;
			}
		} catch (SQLException e) {
			throw new DAOException("Error al obtener la lista de clientes de la base de datos: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}

	}

}
