package grupofp.dao;

import java.sql.SQLException;
import java.util.List;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.ListaPedidos;
import grupofp.modelo.Pedido;

public interface PedidoDAO {
	
	public void insertarPedido(Pedido pedido) throws SQLException, DAOException;
    public void eliminarPedido(int numPedido) throws SQLException, DAOException;
    public Pedido obtenerPedidoPorId(int numPedido) throws SQLException, DAOException;
    public ListaPedidos obtenerPedidos() throws SQLException, DAOException;
    public ListaPedidos obtenerPedidosPorCliente(String email) throws SQLException, DAOException;
}
