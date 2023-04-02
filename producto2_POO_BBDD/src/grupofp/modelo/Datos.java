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
	 * @param cliente
	 * @param articulo
	 * @param pedido
	 * @param listaClientes
	 * @param listaArticulos
	 * @param listaPedidos
	 */
	public Datos(Cliente cliente, Articulo articulo, Pedido pedido, ListaClientes listaClientes,
			ListaArticulos listaArticulos, ListaPedidos listaPedidos) {
		this.cliente = cliente;
		this.articulo = articulo;
		this.pedido = pedido;
		this.listaClientes = listaClientes;
		this.listaArticulos = listaArticulos;
		this.listaPedidos = listaPedidos;
	}

	// getters y setters para actuar sobre el modelo
	public void setNombreCliente(String nombre) {
		this.cliente.setNombre(nombre);
	}

	public String getNombreCliente() {
		return this.cliente.getNombre();
	}

	public void setDomicilioCliente(String domicilio) {
		this.cliente.setDomicilio(domicilio);
	}

	public String getDomicilioCliente() {
		return this.cliente.getDomicilio();
	}

	public void setNifCliente(String nif) {
		this.cliente.setNif(nif);
	}

	public String getNifCliente() {
		return this.cliente.getNif();
	}

	public void setEmailCliente(String email) {
		this.cliente.setEmail(email);
	}

	public String getEmailCliente() {
		return this.cliente.getEmail();
	}
	
	public Class getTipoCliente() {
		return this.cliente.getClass();
	}
	
	public float getCalcAnualCliente() {
		return this.cliente.calcAnual();
	}
	
	public float getDescuentoEnvCliente() {
		return this.cliente.descuentoEnv();
	}
	
	public void setCodigoArticulo(String codigo) {
		this.articulo.setCodigo(codigo);
	}

	public String getCodigoArticulo() {
		return this.articulo.getCodigo();
	}

	public void setDescripcionArticulo(String descripciion) {
		this.articulo.setDescripcion(descripciion);
	}

	public String getDescripcionArticulo() {
		return this.articulo.getDescripcion();
	}

	public void setPvpArticulo(Float pvp) {
		this.articulo.setPvp(pvp);
		;
	}

	public Float getPvpArticulo() {
		return this.articulo.getPvp();
	}

	public void setGastosEnvioArticulo(Float gastosEnvio) {
		this.articulo.setGastosEnvio(gastosEnvio);
	}

	public Float getGastosEnvioArticulo() {
		return this.articulo.getGastosEnvio();
	}

	public void setTiempoPrepArticulo(Duration tiempoPrep) {
		this.articulo.setTiempoPrep(tiempoPrep);
	}

	public Duration getTiempoPrepArticulo() {
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
	
	public void setArticuloPedido(Articulo articulo) {
		this.pedido.setArticulo(articulo);
	}

	public Articulo getArticuloPedido() {
		return this.pedido.getArticulo();
	}

	public void setFechaHoraPedido(Date fechaHora) {
		this.pedido.setFechaHora(fechaHora);
	}

	public Date getFechaHoraPedido() {
		return this.pedido.getFechaHora();
	}
	
	public void setCantUnidadesPedido(int unidades) {
		this.pedido.setCantUnidades(unidades);
	}

	public int getCantUnidadesPedido() {
		return this.pedido.getCantUnidades();
	}
	
	public boolean getPedidoEnviado(){
		return this.pedido.pedidoEnviado();
	}
	
	public float getPrecioEnvioPedido(){
		return this.pedido.precioEnvio();
	}
	

}
