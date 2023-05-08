package grupofp.dao;

import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDAOFactory extends DAOFactory {

	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Crear la SessionFactory desde hibernate.cfg.xml
            return new Configuration()
                    .configure()
                    .buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
    public static SessionFactory obtenerSesionHibernate() {
    	 return sessionFactory;
    }
    
    @Override
	public ArticuloDAO obtenerArticuloDAO() throws SQLException {
        return new ArticuloDAOHibernateImpl(obtenerSesionHibernate());
    }
    
    @Override
	public ClienteDAO obtenerClienteDAO() throws SQLException {
        return null;
    }
    
    @Override
	public PedidoDAO obtenerPedidoDAO() throws SQLException {
        return null;
    }


	public HibernateDAOFactory() {
		// TODO Auto-generated constructor stub
	}

}
