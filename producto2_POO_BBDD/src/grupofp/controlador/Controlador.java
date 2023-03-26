/**
 * 
 */
package grupofp.controlador;

import java.time.Duration;
import java.util.Date;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Pedido;
import grupofp.vista.GestionOS;
import grupofp.vista.OnlineStore;

/**
 * @author josem
 *
 */
public class Controlador {

	private OnlineStore vista;
	private GestionOS vista;
	private Cliente modelo;
	private Articulo modelo;
	private PedidoVista vista;
	private Pedido modelo;

	/**
	 * @param vista
	 * @param modelo
	 */
	public ControladorArticulo(OnlineStore vista, Articulo modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	// getters y setters para actuar sobre el modelo
	public void setCodigo(String codigo) {
		this.modelo.setCodigo(codigo);
	}

	public String getCodigo() {
		return this.modelo.getCodigo();
	}

	public void setDescripcion(String descripciion) {
		this.modelo.setDescripcion(descripciion);
	}

	public String getDescripcion() {
		return this.modelo.getDescripcion();
	}

	public void setPvp(Float pvp) {
		this.modelo.setPvp(pvp);
		;
	}

	public Float getPvp() {
		return this.modelo.getPvp();
	}

	public void setGastosEnvio(Float gastosEnvio) {
		this.modelo.setGastosEnvio(gastosEnvio);
	}

	public Float getGastosEnvio() {
		return this.modelo.getGastosEnvio();
	}

	public void setTiempoPrep(Duration tiempoPrep) {
		this.modelo.setTiempoPrep(null);
	}

	public Duration getTiempoPrep() {
		return this.modelo.getTiempoPrep();
	}
	/**
	 * @param vista
	 * @param modelo
	 */
	public ControladorCliente(GestionOS vista, Cliente modelo) {
		super();
		this.vista = vista;
		this.modelo = modelo;
	}

	//getters y setters para actuar sobre el modelo
	public void setNombre(String nombre) {
		this.modelo.setNombre(nombre);
	}
	public String getNombre() {
		return this.modelo.getNombre();
	}
	
	public void setDomicilio(String domicilio) {
		this.modelo.setDomicilio(domicilio);
	}
	public String getDomicilio() {
		return this.modelo.getDomicilio();
	}
	
	public void setNif(String nif) {
		this.modelo.setNif(nif);
	}
	public String getNif() {
		return this.modelo.getNif();
	}
	
	public void setEmail(String email) {
		this.modelo.setEmail(email);
	}
	public String getEmail() {
		return this.modelo.getEmail();
	}
	

	/**
	 * @param vista
	 * @param modeloo
	 */
	public ControladorPedido(PedidoVista vista, Pedido modelo) {
		super();
		this.vista = vista;
		this.modelo = modelo;
	}

	// getters y setters para actuar sobre el modelo
	public void setNumPedido(int numPedido) {
		this.modelo.setNumPedido(numPedido);
		;
	}

	public int getCodigo() {
		return this.modelo.getNumPedido();
	}

	public void setCliente(Cliente cliente) {
		this.modelo.setCliente(cliente);
		;
	}

	public Cliente getCliente() {
		return this.modelo.getCliente();
	}

	public void setFechaHora(Date fechaHora) {
		this.modelo.setFechaHora(fechaHora);
	}

	public Date getFechaHora() {
		return this.modelo.getFechaHora();
	}

	// pasa el modelo a la vista para presentar los datos
	public void actualizarVista() {
		vista.printArticuloDetalles(modelo.getCodigo(), modelo.getDescripcion(), modelo.getPvp(), modelo.getGastosEnvio(), modelo.getTiempoPrep());
	}

}
