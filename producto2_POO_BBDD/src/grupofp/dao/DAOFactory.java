package grupofp.dao;

import java.sql.SQLException;

public abstract class DAOFactory {

	// Lista de tipos de DAO disponibles en la factoria
	  public static final int MYSQL = 1;
	  public static final int HIBERNATE = 2;

	  // Habrá un método para cada DAO que se pueda crear
	  // Las factorías concretas contendrán la implementación de cada DAO.
	  public abstract ArticuloDAO obtenerArticuloDAO() throws SQLException;
	  public abstract ClienteDAO obtenerClienteDAO() throws SQLException;
	  public abstract PedidoDAO obtenerPedidoDAO() throws SQLException;

	  // Indicamos por parámetro el tipo de sgdb que vamos a utilizar
	  // En nuestro caso la configuración de la conexión a nuestro
	  // servidor con mySQL montado en Azure, se encuentra en la clase 
	  // MySQLDAOFactory
	  public static DAOFactory getDAOFactory(int whichFactory) {

	    switch (whichFactory) {
	      case MYSQL:
	          return new MySQLDAOFactory();
	      case HIBERNATE:
	          return new HibernateDAOFactory();
	      default:
	          return null;
	    }
	  }

}
