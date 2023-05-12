package grupofp.controlador;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.modelo.Cliente;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Datos;
import grupofp.modelo.Lista;
import grupofp.modelo.ListaArticulos;
import grupofp.modelo.ListaClientes;
import grupofp.modelo.ListaPedidos;
import grupofp.modelo.Pedido;
import grupofp.vista.GestionOS;

/**
 * @author kahef
 *
 */
public class Controlador {

	private GestionOS vGestionOS;
	private Datos datos;

	/**
	 * @param vGestionOS (vista)
	 * @param datos      (modelo)
	 */
	public Controlador(GestionOS vGestionOS, Datos datos) {
		this.vGestionOS = vGestionOS;
		this.datos = datos;
	}

	// Metodos utiles para manejar articulos
	public void crearArticulo(String codigo_articulo, String descripcion_articulo, float pvp_articulo,
			Duration tiempoPrep_articulo_parsed, float gastosEnvioArticulo) {
		datos.crearArticulo(codigo_articulo, descripcion_articulo, pvp_articulo, tiempoPrep_articulo_parsed.getSeconds(),
				gastosEnvioArticulo);
	}

	public ListaArticulos getListaArticulos() throws SQLException, DAOException {
		return datos.getListaArticulos();
	}

	// Metodos utiles para manejar clientes
	public void crearCliente(String email_cliente, String nombre_cliente, String domicilio_cliente, String nif_cliente,
			String sn_tipo_cliente) {
		datos.crearCliente(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, sn_tipo_cliente);
	}

	public ListaClientes getListaClientes() throws SQLException, DAOException {
		return datos.getListaClientes();
	}

	public Lista<ClienteEstandar> getListaClientesEstandar() throws SQLException, DAOException {

		Lista<ClienteEstandar> listaClientesEstandar = new Lista<>();

		for (Cliente cliente : datos.getListaClientes()) {
			if (cliente instanceof ClienteEstandar) {
				listaClientesEstandar.add((ClienteEstandar) cliente);
			}
		}
		return listaClientesEstandar;
	}

	public Lista<ClientePremium> getListaClientesPremium() throws SQLException, DAOException {

		Lista<ClientePremium> listaClientesPremium = new Lista<>();

		for (Cliente cliente : datos.getListaClientes()) {
			if (cliente instanceof ClientePremium) {
				listaClientesPremium.add((ClientePremium) cliente);
			}
		}
		return listaClientesPremium;
	}

	// Metodos utiles para manejar pedidos
	public void crearPedido(String email_cliente, String codigo_articulo, LocalDateTime fechaHora, int cantUnidades)
			throws SQLException, DAOException {
		datos.crearPedido(email_cliente, codigo_articulo, fechaHora, cantUnidades);
	}

	public ListaPedidos getListaPedidos() throws SQLException, DAOException {
		return datos.getListaPedidos();
	}

	public ListaPedidos getListaPedidosPendientes() throws SQLException, DAOException {

		ListaPedidos listaPedidosPendientes = new ListaPedidos();

		for (Pedido pedido : this.datos.getListaPedidos()) {
			if (!pedido.pedidoEnviado()) {
				listaPedidosPendientes.add(pedido);
			}
		}
		return listaPedidosPendientes;
	}

	public ListaPedidos getListaPedidosPendientesCliente(String email_cliente) throws SQLException, DAOException {

		ListaPedidos listaPedidosPendientesCliente = new ListaPedidos();

		for (Pedido pedido : this.datos.getListaPedidos()) {
			if (!pedido.pedidoEnviado() && pedido.getCliente().getEmail().equals(email_cliente)) {
				listaPedidosPendientesCliente.add(pedido);
			}
		}
		return listaPedidosPendientesCliente;
	}

	public ListaPedidos getListaPedidosEnviados() throws SQLException, DAOException {

		ListaPedidos listaPedidosPendientes = new ListaPedidos();

		for (Pedido pedido : this.datos.getListaPedidos()) {
			if (pedido.pedidoEnviado()) {
				listaPedidosPendientes.add(pedido);
			}
		}
		return listaPedidosPendientes;
	}

	public ListaPedidos getListaPedidosEnviadosCliente(String email_cliente) throws SQLException, DAOException {

		ListaPedidos listaPedidosPendientesCliente = new ListaPedidos();

		for (Pedido pedido : this.datos.getListaPedidos()) {
			if (pedido.pedidoEnviado() && pedido.getCliente().getEmail().equals(email_cliente)) {
				listaPedidosPendientesCliente.add(pedido);
			}
		}
		return listaPedidosPendientesCliente;
	}

	public void eliminarPedido(int numPedido) {
		datos.eliminarPedido(numPedido);
	}

	// Getters y setters para actuar sobre todas las clases del modelo

	// Para articulo:
	public void setCodigoArticulo(String codigo) {
		this.datos.setCodigoArticulo(codigo);
	}

	public String getCodigoArticulo() {
		return this.datos.getCodigoArticulo();
	}

	public void setDescripcionArticulo(String descripciion) {
		this.datos.setDescripcionArticulo(descripciion);
	}

	public String getDescripcionArticulo() {
		return this.getDescripcionArticulo();
	}

	public void setPvpArticulo(Float pvp) {
		this.datos.setPvpArticulo(pvp);
	}

	public Float getPvpArticulo() {
		return this.datos.getPvpArticulo();
	}

	public void setGastosEnvioArticulo(Float gastosEnvio) {
		this.datos.setGastosEnvioArticulo(gastosEnvio);
	}

	public Float getGastosEnvioArticulo() {
		return this.datos.getGastosEnvioArticulo();
	}

	public void setTiempoPrepArticulo(long tiempoPrep) {
		this.datos.setTiempoPrepArticulo(tiempoPrep);
	}

	public long getTiempoPrepArticulo() {
		return this.datos.getTiempoPrepArticulo();
	}

	// Para cliente:
	public void setNombreCliente(String nombre) {
		this.datos.setNombreCliente(nombre);
	}

	public String getNombreCliente() {
		return this.datos.getNombreCliente();
	}

	public void setDomicilioCliente(String domicilio) {
		this.datos.setDomicilioCliente(domicilio);
	}

	public String getDomicilioCliente() {
		return this.datos.getDomicilioCliente();
	}

	public void setNifCliente(String nif) {
		this.datos.setNifCliente(nif);
	}

	public String getNifCliente() {
		return this.datos.getNifCliente();
	}

	public void setEmailCliente(String email) {
		this.datos.setEmailCliente(email);
	}

	public String getEmailCliente() {
		return this.datos.getEmailCliente();
	}

	// Para Pedido:
	public void setNumPedidoPedido(int numPedido) {
		this.datos.setNumPedido(numPedido);
	}

	public int getCodigoPedido() {
		return this.datos.getNumPedido();
	}

	public void setClientePedido(Cliente cliente) {
		this.datos.setClientePedido(cliente);
	}

	public Cliente getClientePedido() {
		return this.datos.getClientePedido();
	}

	public void setFechaHoraPedido(LocalDateTime fechaHora) {
		this.datos.setFechaHoraPedido(fechaHora);
	}

	public LocalDateTime getFechaHoraPedido() {
		return this.datos.getFechaHoraPedido();
	}

	/**
	 * @return vGestionOS (vista)
	 */
	public GestionOS getvGestionOS() {
		return vGestionOS;
	}

	/**
	 * @param vGestionOS (vista)
	 */
	public void setvGestionOS(GestionOS vGestionOS) {
		this.vGestionOS = vGestionOS;
	}

	/**
	 * @return the datos (modelo)
	 */
	public Datos getDatos() {
		return datos;
	}

	/**
	 * @param datos (modelo)
	 */
	public void setDatos(Datos datos) {
		this.datos = datos;
	}
	
}
