package grupofp.dao;

import java.sql.SQLException;
import java.util.List;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Cliente;
import grupofp.modelo.ListaClientes;

public interface ClienteDAO {
    void insertarCliente(Cliente cliente) throws DAOException, SQLException;
    float obtenerCuotaPorTipoCliente(String tipo_cliente) throws DAOException, SQLException;
    float obtenerDescuentoEnvioPorTipoCliente(String tipo_cliente) throws DAOException, SQLException;
    int obtenerIdClientePorTipoCliente(String tipo_cliente) throws DAOException, SQLException;
    Cliente obtenerCliente(String email) throws DAOException, SQLException;
    ListaClientes obtenerTodosClientes() throws DAOException, SQLException;

}
