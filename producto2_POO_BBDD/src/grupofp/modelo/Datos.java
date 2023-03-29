/**
 * 
 */
package grupofp.modelo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
/**
 * @author J-Programers
 *
 */

public class Datos {
	
	private Cliente cliente;
	private Articulo articulo;
	private Pedido pedido;
	private ListaClientes listaClientes;
	private ListaArticulos listaArticulos;
	private ListaPedidos listaPedidos;

	/**
	 * 
	 */
	public Datos() {
		// TODO Auto-generated constructor stub
	}

	// getters y setters para actuar sobre el modelo
	public void setNombre(String nombre) {
		this.cliente.setNombre(nombre);
	}

	public String getNombre() {
		return this.cliente.getNombre();
	}

	public void setDomicilio(String domicilio) {
		this.cliente.setDomicilio(domicilio);
	}

	public String getDomicilio() {
		return this.cliente.getDomicilio();
	}

	public void setNif(String nif) {
		this.cliente.setNif(nif);
	}

	public String getNif() {
		return this.cliente.getNif();
	}

	public void setEmail(String email) {
		this.cliente.setEmail(email);
	}

	public String getEmail() {
		return this.cliente.getEmail();
	}
	
	public void setCodigoPedido(String codigo) {
		this.articulo.setCodigo(codigo);
	}

	public String getCodigoArticulo() {
		return this.articulo.getCodigo();
	}

	public void setDescripcion(String descripciion) {
		this.articulo.setDescripcion(descripciion);
	}

	public String getDescripcion() {
		return this.articulo.getDescripcion();
	}

	public void setPvp(Float pvp) {
		this.articulo.setPvp(pvp);
		;
	}

	public Float getPvp() {
		return this.articulo.getPvp();
	}

	public void setGastosEnvio(Float gastosEnvio) {
		this.articulo.setGastosEnvio(gastosEnvio);
	}

	public Float getGastosEnvio() {
		return this.articulo.getGastosEnvio();
	}

	public void setTiempoPrep(Duration tiempoPrep) {
		this.articulo.setTiempoPrep(null);
	}

	public Duration getTiempoPrep() {
		return this.articulo.getTiempoPrep();
	}
	
	public void setNumPedido(int numPedido) {
		this.pedido.setNumPedido(numPedido);
		;
	}

	public int getNumPedido() {
		return this.pedido.getNumPedido();
	}

	public void setClientePedido(Cliente cliente) {
		this.pedido.setCliente(cliente);
		;
	}

	public Cliente getClientePedido() {
		return this.pedido.getCliente();
	}

	public void setFechaHora(Date fechaHora) {
		this.pedido.setFechaHora(fechaHora);
	}

	public Date getFechaHora() {
		return this.pedido.getFechaHora();
	}
	
	

}
