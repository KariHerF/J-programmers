package grupofp.controlador;

import java.time.Duration;
import java.util.Date;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Pedido;
import grupofp.vista.GestionOS;
import grupofp.vista.OnlineStore;

/**
 * @author J-Programers
 *
 */
public class Controlador {

	private OnlineStore vOnlineStore;
	private GestionOS vGestionOS;
	private Cliente mCliente;
	private Articulo mArticulo;
	private Pedido mPedido;

	/**
	 * @param vista
	 * @param modelo
	 */
	public Controlador(OnlineStore vOnlineStore, Cliente mCliente, Articulo mArticulo, Pedido mPedido) {
		this.vOnlineStore = vOnlineStore;
		this.mArticulo = mArticulo;
		this.mCliente = mCliente;
		this.mPedido = mPedido;
	}

	// getters y setters para actuar sobre el modelo
	public void setCodigoArticulo(String codigo) {
		this.mArticulo.setCodigo(codigo);
	}

	public String getCodigoArticulo() {
		return this.mArticulo.getCodigo();
	}

	public void setDescripcionArticulo(String descripciion) {
		this.mArticulo.setDescripcion(descripciion);
	}

	public String getDescripcionArticulo() {
		return this.getDescripcionArticulo();
	}

	public void setPvpArticulo(Float pvp) {
		this.mArticulo.setPvp(pvp);
	}

	public Float getPvpArticulo() {
		return this.mArticulo.getPvp();
	}

	public void setGastosEnvioArticulo(Float gastosEnvio) {
		this.mArticulo.setGastosEnvio(gastosEnvio);
	}

	public Float getGastosEnvioArticulo() {
		return this.mArticulo.getGastosEnvio();
	}

	public void setTiempoPrepArticulo(Duration tiempoPrep) {
		this.mArticulo.setTiempoPrep(null);
	}

	public Duration getTiempoPrepArticulo() {
		return this.mArticulo.getTiempoPrep();
	}

	//getters y setters para actuar sobre el modelo
	public void setNombreCliente(String nombre) {
		this.mCliente.setNombre(nombre);
	}
	public String getNombreCliente() {
		return this.mCliente.getNombre();
	}
	
	public void setDomicilioCliente(String domicilio) {
		this.mCliente.setDomicilio(domicilio);
	}
	public String getDomicilioCliente() {
		return this.mCliente.getDomicilio();
	}
	
	public void setNifCliente(String nif) {
		this.mCliente.setNif(nif);
	}
	public String getNifCliente() {
		return this.mCliente.getNif();
	}
	
	public void setEmailCliente(String email) {
		this.mCliente.setEmail(email);
	}
	public String getEmailCliente() {
		return this.mCliente.getEmail();
	}

	// getters y setters para actuar sobre el modelo
	public void setNumPedidoPedido(int numPedido) {
		this.mPedido.setNumPedido(numPedido);
	}

	public int getCodigoPedido() {
		return this.mPedido.getNumPedido();
	}

	public void setClientePedido(Cliente cliente) {
		this.mPedido.setCliente(cliente);
		;
	}

	public Cliente getClientePedido() {
		return this.mPedido.getCliente();
	}

	public void setFechaHoraPedido(Date fechaHora) {
		this.mPedido.setFechaHora(fechaHora);
	}

	public Date getFechaHoraPedido() {
		return this.mPedido.getFechaHora();
	}

	// pasa el modelo a la vista para presentar los datos
	public void actualizarVistaArticulos() {
		vGestionOS.printArticuloDetalles(mArticulo.getCodigo(), mArticulo.getDescripcion(), mArticulo.getPvp(), mArticulo.getGastosEnvio(), mArticulo.getTiempoPrep());
	}

	public void actualizarVistaClientes() {
		vGestionOS.printClienteDetalles(mCliente.getNombre(), mCliente.getDomicilio(), mCliente.getNif(), mCliente.getEmail(), mCliente.tipoCliente(), mCliente.calcAnual(), mCliente.descuentoEnv());
	}

	// public void actualizarVistaPedidos() {
	// 	vGestionOS.printPedidoDetalles();
	// }

	//TODO: faltan los actualizadores de la vista para Pedidos
}
