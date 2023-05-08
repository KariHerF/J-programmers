package grupofp.dao;

import java.sql.SQLException;
import java.util.List;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Articulo;
import grupofp.modelo.ListaArticulos;

public interface ArticuloDAO {
	public void insertarArticulo(Articulo articulo) throws SQLException, DAOException;
    public Articulo obtenerArticulo(String codigo) throws SQLException, DAOException;
    public ListaArticulos obtenerTodosLosArticulos() throws SQLException, DAOException;
}
