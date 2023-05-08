package grupofp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.Lista;
import grupofp.modelo.ListaArticulos;
import grupofp.modelo.Pedido;

public class ArticuloDAOHibernateImpl implements ArticuloDAO {

	private SessionFactory sessionFactory;

	public ArticuloDAOHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertarArticulo(Articulo articulo) throws DAOException {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.save(articulo);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new DAOException("Error al insertar el artículo en la BD: ", e);
		}
	}

	@Override
	public Articulo obtenerArticulo(String codigo) throws DAOException {
		try (Session session = sessionFactory.openSession()) {
			return session.get(Articulo.class, codigo);
		} catch (Exception e) {
			throw new DAOException("Error al obtener el artículo de la BD: ", e);
		}
	}

	@Override
	public ListaArticulos obtenerTodosLosArticulos() throws DAOException {
		try (Session session = sessionFactory.openSession()) {
			List<Articulo> articulos = session.createQuery("FROM Articulo", Articulo.class).list();
			ListaArticulos listaArticulos =  new ListaArticulos();
			listaArticulos.addAll(articulos);
			return listaArticulos;
		} catch (Exception e) {
			throw new DAOException("Error al obtener los artículos de la BD: ", e);
		}
	}

}
