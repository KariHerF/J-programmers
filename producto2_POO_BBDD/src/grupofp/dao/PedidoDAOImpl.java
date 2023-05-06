package grupofp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Datos;
import grupofp.modelo.ListaPedidos;
import grupofp.modelo.Pedido;

public class PedidoDAOImpl implements PedidoDAO {

	
	private Connection conn;
	private Datos datos;

	public PedidoDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	// Método que se encarga de cerrar la conexión con la base de datos
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }

    // Método que se encarga de insertar un nuevo pedido en la base de datos
    @Override
    public void insertarPedido(Pedido pedido) throws SQLException, DAOException {
        PreparedStatement statement = null;
        try {
            String query = "INSERT INTO pedidos (codigo_articulo, cantidad, fecha_pedido, email) " +
                    "VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(query);
            //statement.setInt(1, pedido.getNumPedido());
            statement.setString(1, pedido.getArticulo().getCodigo());
            statement.setInt(2, pedido.getCantUnidades());
            statement.setObject(3, pedido.getFechaHora());
            statement.setString(4, pedido.getCliente().getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
			throw new DAOException("Error al insertar el nuevo pedido en la bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}
    }



    // Método que se encarga de eliminar un pedido existente en la base de datos
    @Override
    public void eliminarPedido(int numPedido) throws SQLException, DAOException {
        PreparedStatement statement = null;
        try {
            String query = "DELETE FROM pedidos WHERE num_pedido=?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, numPedido);
            statement.executeUpdate();
        } catch (SQLException e) {
			throw new DAOException("Error al intentar eliminar el pedido de la bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}
    }
    
    // Método que se encarga de recuperar un pedido a partir del id de pedido
    @Override
    public Pedido obtenerPedidoPorId(int id_pedido) throws SQLException, DAOException {
    	
            PreparedStatement statement = null;
            ResultSet rs = null;
            ClienteDAO clienteDAO = new ClienteDAOImpl(conn);
            ArticuloDAO articuloDAO = new ArticuloDAOImpl(conn);
            Pedido pedido = null;

            try {
            	statement = conn.prepareStatement("SELECT * FROM pedidos "
            			+ " WHERE num_pedido =?");
            	statement.setInt(1, id_pedido);
                rs = statement.executeQuery();

                if (rs.next()) {
                    int numPedido = rs.getInt("num_pedido");
                    int cantUnidades = rs.getInt("cantidad");
                    LocalDateTime fechaHora = rs.getTimestamp("fecha_pedido").toLocalDateTime();

                    // Get Cliente
                    String email_cliente = rs.getString("email");
                    Cliente cliente = clienteDAO.obtenerCliente(email_cliente);

                    // Get Articulo
                    String codigo_articulo = rs.getString("codigo_articulo");
                    Articulo articulo = articuloDAO.obtenerArticulo(codigo_articulo);

                    pedido = new Pedido(cliente, articulo, fechaHora, cantUnidades);
                    pedido.setNumPedido(numPedido);
                }
            }  catch (SQLException e) {
    			throw new DAOException("Error al intentar obtener el pedido de la bd: ", e);
    		} finally {
    			if (statement != null) {
    				// Liberamos los recursos asignados al statement y a la conexión jdbc
    				statement.close();
    				this.close();
    			}
    		}
            return pedido;
        }    

    // Método que se encarga de recuperar los pedidos prensentes en la bd
    @Override
    public ListaPedidos obtenerPedidos() throws SQLException, DAOException {
    	
    		ListaPedidos pedidos = new ListaPedidos();
            PreparedStatement statement = null;
            ResultSet rs = null;
            ClienteDAO clienteDAO = new ClienteDAOImpl(conn);
            ArticuloDAO articuloDAO = new ArticuloDAOImpl(conn);

            try {
            	statement = conn.prepareStatement("SELECT * FROM pedidos");
                rs = statement.executeQuery();

                while (rs.next()) {
                    int numPedido = rs.getInt("num_pedido");
                    int cantUnidades = rs.getInt("cantidad");
                    LocalDateTime fechaHora = rs.getTimestamp("fecha_pedido").toLocalDateTime();

                    // Get Cliente
                    String email_cliente = rs.getString("email");
                    Cliente cliente = clienteDAO.obtenerCliente(email_cliente);

                    // Get Articulo
                    String codigo_articulo = rs.getString("codigo_articulo");
                    Articulo articulo = articuloDAO.obtenerArticulo(codigo_articulo);

                    Pedido pedido = new Pedido(cliente, articulo, fechaHora, cantUnidades);
                    pedido.setNumPedido(numPedido);
                    pedidos.add(pedido);
                }
            } finally {
            	 if (statement != null) {
                     //Liberamos los recursos asignados al statement y a la conexión jdbc
                 	statement.close();
                     this.close();
                 }
            }

            return pedidos;
        }
   
    // Método que se encarga de recuperar los pedidos por cliente prensentes en la bd
    @Override
    public ListaPedidos obtenerPedidosPorCliente(String email) throws SQLException, DAOException {
    	
    	    ListaPedidos pedidos = new ListaPedidos();
            PreparedStatement statement = null;
            ResultSet rs = null;
            ClienteDAO clienteDAO = new ClienteDAOImpl(conn);
            ArticuloDAO articuloDAO = new ArticuloDAOImpl(conn);

            try {
            	statement = conn.prepareStatement("SELECT * FROM pedidos"
            			+ " WHERE email =?");
            	statement.setString(1, email);
                rs = statement.executeQuery();

                while (rs.next()) {
                    int numPedido = rs.getInt("num_pedido");
                    int cantUnidades = rs.getInt("cantidad");
                    LocalDateTime fechaHora = rs.getTimestamp("fecha_pedido").toLocalDateTime();

                    // Get Cliente
                    String email_cliente = rs.getString("email");
                    Cliente cliente = clienteDAO.obtenerCliente(email_cliente);

                    // Get Articulo
                    String codigo_articulo = rs.getString("codigo_articulo");
                    Articulo articulo = articuloDAO.obtenerArticulo(codigo_articulo);

                    Pedido pedido = new Pedido(cliente, articulo, fechaHora, cantUnidades);
                    pedido.setNumPedido(numPedido);
                    pedidos.add(pedido);
                }
            } finally {
            	 if (statement != null) {
                     //Liberamos los recursos asignados al statement y a la conexión jdbc
                 	statement.close();
                     this.close();
                 }
            }

            return pedidos;
        }    
    
    
}
