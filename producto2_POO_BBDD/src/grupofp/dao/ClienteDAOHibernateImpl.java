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
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
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
            Query<Float> query = session.createQuery(
                    "select t.cuotaAnual from TipoCliente t where t.descripcion = :tipoCliente", Float.class);
            query.setParameter("tipoCliente", tipoCliente);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Error al obtener la cuota anual de un tipo de cliente desde la base de datos", e);
        }
    }

    @Override
    public float obtenerDescuentoEnvioPorTipoCliente(String tipoCliente) throws DAOException {
        try (Session session = sessionFactory.openSession()) {
            Query<Float> query = session.createQuery(
                    "select t.descuentoEnvio from TipoCliente t where t.descripcion = :tipoCliente", Float.class);
            query.setParameter("tipoCliente", tipoCliente);
            return query.getSingleResult();
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
            Query<Cliente> query = session.createQuery(
                    "select c from Cliente c join fetch c.tipoCliente where c.email = :email", Cliente.class);
            query.setParameter("email", email);
            List<Cliente> clientes = query.getResultList();
            if (clientes.isEmpty()) {
                return null;
            } else if (clientes.size() > 1) {
                throw new DAOException("Se encontraron múltiples clientes con el mismo email");
            } else {
                return clientes.get(0);
            }
        } catch (Exception e) {
            throw new DAOException("Error al obtener el cliente desde la base de datos", e);
        }
    }
    
    @Override
    public ListaClientes obtenerTodosClientes() throws DAOException, SQLException {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT c.email, c.nombre, c.domicilio, c.nif, tc.descripcion, tc.cuota_anual, tc.descuento_envio " +
                    "FROM Cliente c " +
                    "INNER JOIN c.tipoCliente tc";
            Query query = session.createQuery(hql);
            List<Object[]> resultList = query.getResultList();

            ListaClientes clientes = new ListaClientes();
            for (Object[] result : resultList) {
                String email = (String) result[0];
                String nombre = (String) result[1];
                String domicilio = (String) result[2];
                String nif = (String) result[3];
                String tipoCliente = (String) result[4];
                float cuotaAnual = (float) result[5];
                float descuentoEnvio = (float) result[6];

                Cliente cliente = null;
                try {
                    if (tipoCliente.equals(CLIENTE_ESTANDAR)) {
                        cliente = new ClienteEstandar(email, nombre, domicilio, nif, tipoCliente, cuotaAnual, descuentoEnvio);
                        clientes.add(cliente);
                    } else if (tipoCliente.equals(CLIENTE_PREMIUM)) {
                        cliente = new ClientePremium(email, nombre, domicilio, nif, tipoCliente, cuotaAnual, descuentoEnvio);
                        clientes.add(cliente);
                    } else {
                        // TODO: POSIBLE CASO PARA LANZAR UNA EXCEPCIÓN PERSONALIZADA
                        // (Se tiene que en las tablas de bd un cliente se ha relacionado con un tipo
                        // que no está contemplando en el modelo)
                        cliente.toString();
                        System.out.println("El cliente tiene asignado un tipo de cliente no soportado por el modelo. "
                                + "Debe ser \"estandar\" o \"premium\", ya que son los únicos tipos de cliente soportados por el momento."
                                + " Se aconseja revisar la tabla clientes y tipos_cliente de la base de datos para hacer la limpieza de datos correspondiente.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex);
                }
            }
            return clientes;
        } catch (HibernateException e) {
            throw new DAOException("Error al obtener la lista de clientes de la base de datos: ", e);
        }
    }
    

}
