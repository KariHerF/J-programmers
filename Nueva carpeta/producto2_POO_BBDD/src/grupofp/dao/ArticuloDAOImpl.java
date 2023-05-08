package grupofp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.ListaArticulos;
import grupofp.modelo.Pedido;

public class ArticuloDAOImpl implements ArticuloDAO {

	private Connection conn;

    public ArticuloDAOImpl(Connection conn) {
        this.conn = conn;
    }
    
    // Método que se encarga de cerrar la conexión con la base de datos
    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }


    @Override
    public void insertarArticulo(Articulo articulo) throws DAOException {
    	        
    	 PreparedStatement statement = null;
    	 
         try {
         	String sql = "INSERT INTO articulos (codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion)"
         			+ " VALUES (?, ?, ?, ?, ?)";
         	statement = conn.prepareStatement(sql);
            statement.setString(1, articulo.getCodigo());
            statement.setString(2, articulo.getDescripcion());
            statement.setFloat(3, articulo.getPvp());
            statement.setFloat(4, articulo.getGastosEnvio());
            statement.setLong(5, articulo.getTiempoPrep().getSeconds());
            statement.executeUpdate();
        }catch (SQLException e) {
			throw new DAOException("Error al insertar un nuevo articulo sobre la bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}
    }

    @Override
    public Articulo obtenerArticulo(String codigo) throws SQLException, DAOException {
      	 Articulo articulo = null;
    	 PreparedStatement statement = null;
    	 
         try {
        	 String sql = "SELECT codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion "
        			+ " FROM articulos WHERE codigo_articulo = ?";
        	 statement = conn.prepareStatement(sql);
        	 statement.setString(1, codigo);
  
             try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                     articulo = new Articulo(resultSet.getString("codigo_articulo"), resultSet.getString("descripcion"),
                             resultSet.getFloat("precio_venta"), Duration.ofSeconds(resultSet.getLong("tiempo_preparacion")),
                             resultSet.getFloat("gastos_envio"));
                 }
             }
        }catch (SQLException e) {
			throw new DAOException("Error al obtener el articulo de la bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
			}
		}
         
        return articulo;
    }

    @Override
    public ListaArticulos obtenerTodosLosArticulos() throws SQLException, DAOException {
        ListaArticulos listaArticulos = new ListaArticulos();
        PreparedStatement statement = null;
        Articulo articulo = null;
        
        try {
        	 String sql = "SELECT codigo_articulo, descripcion, precio_venta, gastos_envio, tiempo_preparacion FROM articulos";
        	 statement = conn.prepareStatement(sql);
             try (ResultSet resultSet = statement.executeQuery()) {
            	 while (resultSet.next()) {
            		 articulo = new Articulo(resultSet.getString("codigo_articulo"), resultSet.getString("descripcion"),
                             resultSet.getFloat("precio_venta"), Duration.ofSeconds(resultSet.getLong("tiempo_preparacion")),
                             resultSet.getFloat("gastos_envio"));
                     listaArticulos.add(articulo);
                 }
             }
        }catch (SQLException e) {
			throw new DAOException("Error al obtener los articulos de la bd: ", e);
		} finally {
			if (statement != null) {
				// Liberamos los recursos asignados al statement y a la conexión jdbc
				statement.close();
				this.close();
			}
		}
        return listaArticulos;
    }

}
