package grupofp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {



    private static final String URL = "jdbc:mysql://j-programmers-bd-karina.mysql.database.azure.com:3306/j-programmers?useSSL=true";
    private static final String USER = "karina";
    private static final String PASSWORD = "k1.k2.k3.";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
	public ArticuloDAO obtenerArticuloDAO() throws SQLException {
        return new ArticuloDAOImpl(obtenerConexion());
    }
    
    @Override
	public ClienteDAO obtenerClienteDAO() throws SQLException {
        return new ClienteDAOImpl(obtenerConexion());
    }
    
    @Override
	public PedidoDAO obtenerPedidoDAO() throws SQLException {
        return new PedidoDAOImpl(obtenerConexion());
    }


	public MySQLDAOFactory() {
		// TODO Auto-generated constructor stub
	}

}
