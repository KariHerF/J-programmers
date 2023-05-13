package grupofp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Lista;
import grupofp.modelo.ListaArticulos;
import grupofp.modelo.ListaClientes;
import grupofp.modelo.Pedido;

public class ClienteDAOHibernateImpl implements ClienteDAO {

	private static final  String CLIENTE_ESTANDAR = "estandar";
	private static final String CLIENTE_PREMIUM = "premium";
	
	private SessionFactory sessionFactory;

	public ClienteDAOHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Override
    public void insertarCliente(Cliente cliente) throws DAOException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            session.persist(cliente);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DAOException("Error al insertar el cliente en la base de datos", e);
        }
    }

    @Override
    public float obtenerCuotaPorTipoCliente(String tipoCliente) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
        	// Query de consulta sql nativa, con un where que tiene como parámetro de entrada 
        	//el string que tipo de cliente a encontrar. Se opta por esta alternativa ya que la
        	//tabla tipos_clientes, no va a relacioarse con ninguna entidad e la capa modelo.
        	String sql = "SELECT cuota_anual FROM tipos_cliente WHERE descripcion = :tipoCliente";

        	Query query = session.createNativeQuery(sql, Float.class);
        	//Inyectamos el parámetro al where de la sentencia sql de consulta
            query.setParameter("tipoCliente", tipoCliente);
            float cuotaAnual = (Float)query.getSingleResult();
            session.close();
            return cuotaAnual;
        } catch (Exception e) {
            throw new DAOException("Error al obtener la cuota anual de un tipo de cliente desde la base de datos", e);
        } 
    }

    @Override
    public float obtenerDescuentoEnvioPorTipoCliente(String tipoCliente) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
        	// Query de consulta sql nativa, con un where que tiene como parámetro de entrada 
        	//el string que tipo de cliente a encontrar. Se opta por esta alternativa ya que la
        	//tabla tipos_clientes, no va a relacioarse con ninguna entidad e la capa modelo.
        	String sql = "SELECT descuento_envio FROM tipos_cliente WHERE descripcion = :tipoCliente";

        	Query query = session.createNativeQuery(sql, Float.class);
        	//Inyectamos el parámetro al where de la sentencia sql de consulta
            query.setParameter("tipoCliente", tipoCliente);
            float descuentoEnvio = (Float)query.getSingleResult();
            session.close();
            return descuentoEnvio;
        } catch (Exception e) {
            throw new DAOException("Error al obtener el descuento de envío de un tipo de cliente desde la base de datos", e);
        }
    }

    @Override
    public int obtenerIdClientePorTipoCliente(String tipoCliente) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(
                    "select t.idTipoCliente from TipoCliente t where t.descripcion = :tipoCliente", Integer.class);
            query.setParameter("tipoCliente", tipoCliente);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Error al obtener el id de un tipo de cliente desde la base de datos", e);
        }
    }

    @Override
    public Cliente obtenerCliente(String email) throws DAOException {
		try (Session session = sessionFactory.openSession()) {
		        	
        	Cliente cliente = null;
        	
        	String sql = "SELECT c.nombre, c.domicilio, c.nif, tc.descripcion, tc.cuota_anual, tc.descuento_envio"
        			   + " FROM clientes c JOIN tipos_cliente tc"
        			   + " ON c.tipo_cliente = tc.id_tipo_cliente"
        			   + " WHERE c.email = :userEmail";
        	Query query = session.createNativeQuery(sql, Object[].class);
        	query.setParameter("userEmail", email);
        	Object[] result = (Object[]) query.getSingleResult();
        	
        	String nombre = (String) result[0];
			String domicilio = (String) result[1];
			String nif = (String) result[2];
			String tipo_cliente = (String) result[3];
			float cuota_anual = (Float) result[4];
			float descuento_envio = (Float) result[5];

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
            
            return cliente;
        } catch (HibernateException e) {
            throw new DAOException("Error al obtener la lista de clientes de la base de datos: ", e);
        }
	
    }
    
    @Override
    public ListaClientes obtenerTodosClientes() throws DAOException, SQLException {
        try (Session session = sessionFactory.openSession()) {
        	
        	ListaClientes clientes = new ListaClientes();
        	Cliente cliente = null;
        	
        	String sql = "SELECT c.email, c.nombre, c.domicilio, c.nif, tc.descripcion, tc.cuota_anual, tc.descuento_envio"
        			   + " FROM clientes c JOIN tipos_cliente tc"
        			   + " ON c.tipo_cliente = tc.id_tipo_cliente";
        	
        	List<Object[]> resultados = session.createNativeQuery(sql, Object[].class).list();
        	        	
			if (resultados != null){
    
				for (Object[] row : resultados) {
		        	String email = (String) row[0];
		        	String nombre = (String) row[1];
					String domicilio = (String) row[2];
					String nif = (String) row[3];
					String tipo_cliente = (String) row[4];
					float cuota_anual = (Float) row[5];
					float descuento_envio = (Float) row[6];
		
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
			 } 
		            
		        return clientes;
		     } catch (HibernateException e) {
		            throw new DAOException("Error al obtener la lista de clientes de la base de datos: ", e);
		     }
    }
    

}
