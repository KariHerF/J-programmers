package grupofp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.ListaPedidos;
import grupofp.modelo.Pedido;

public class PedidoDAOHibernateImpl implements PedidoDAO {

	private SessionFactory sessionFactory;

	public PedidoDAOHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	 @Override
	    public void insertarPedido(Pedido pedido) throws DAOException {
		    Transaction transaccion = null;
	        try(Session session = sessionFactory.openSession()) {
	            try {
	            	transaccion = session.getTransaction();
	            	pedido.setCocigoAticulo(pedido.getArticulo().getCodigo());
	 	            pedido.setEmailCliente(pedido.getCliente().getEmail());
	 	            session.persist(pedido);
	 	            transaccion.commit();
	 	            session.close();
	            } catch (IllegalStateException e) {
	                if (transaccion != null && transaccion.isActive()) {
	                	transaccion.rollback();
	                }
	                // Si la transaccion no estaba ya iniciada, la intentamos iniciar
	                try {
	                	transaccion = session.beginTransaction();
	                	pedido.setCocigoAticulo(pedido.getArticulo().getCodigo());
	     	            pedido.setEmailCliente(pedido.getCliente().getEmail());
	     	            session.persist(pedido);
	     	            transaccion.commit();
	     	            session.close();
	                } catch (Exception ex) {
	                    if (transaccion != null && transaccion.isActive()) {
	                    	transaccion.rollback();
	                    }
	                    throw new DAOException("Error al insertar el nuevo pedido en la bd: ", ex);
	                }
	            }
	            
	        } catch (Exception e) {
	            if (transaccion != null && transaccion.isActive()) {
	            	transaccion.rollback();
	            }
	            throw new DAOException("Error al insertar el nuevo pedido en la bd: ", e);
	        } 
	    }

	    @Override
	    public void eliminarPedido(int numPedido) throws DAOException {
	    	 Transaction tx = null;
		     try(Session session = sessionFactory.openSession()) {
	            tx = session.getTransaction();
	            tx.begin();
	            Pedido pedido = session.find(Pedido.class, numPedido);
	            if (pedido != null) {
	                session.remove(pedido);
	            }
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) {
	                tx.rollback();
	            }
	            throw new DAOException("Error al intentar eliminar el pedido de la bd: ", e);
	        } 
	    }

	    @Override
	    public Pedido obtenerPedidoPorId(int id_pedido) throws DAOException {
	    	Transaction tx = null;
		     try(Session session = sessionFactory.openSession()) {
	            return session.find(Pedido.class, id_pedido);
	        } catch (Exception e) {
	            throw new DAOException("Error al intentar obtener el pedido de la bd: ", e);
	        } 
	    }

	    @Override
	    public ListaPedidos obtenerPedidos() throws DAOException {
	    	Transaction tx = null;
	    	ListaPedidos listaPedidos =  new ListaPedidos();
		     try(Session session = sessionFactory.openSession()) {
	            List<Pedido> pedidos = session.createQuery("FROM Pedido", Pedido.class).getResultList();
	            listaPedidos.addAll(pedidos);
            	return listaPedidos;
	        } catch (Exception e) {
	            throw new DAOException("Error al intentar obtener los pedidos de la bd: ", e);
	        } 
	    }

	    @Override
	    public ListaPedidos obtenerPedidosPorCliente(String email) throws DAOException {
	    	Transaction tx = null;
	    	ListaPedidos listaPedidos =  new ListaPedidos();
		     try(Session session = sessionFactory.openSession()) {
	            List<Pedido> pedidos = session.createQuery("FROM Pedido p WHERE p.cliente.email = :email", Pedido.class)
	                .setParameter("email", email)
	                .getResultList();
	 
            	listaPedidos.addAll(pedidos);
            	return listaPedidos;
	            
	        } catch (Exception e) {
	            throw new DAOException("Error al intentar obtener los pedidos del cliente de la bd: ", e);
	        }
	    }

	   
}
