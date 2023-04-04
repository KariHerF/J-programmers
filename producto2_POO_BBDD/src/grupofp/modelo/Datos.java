/**
 * 
 */
package grupofp.modelo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import grupofp.controlador.Controlador;
/**
 * @author J-Programers
 *
 */

public class Datos {
	
	private Articulo articulo;
	private Cliente cliente;
	private Pedido pedido;
	private Controlador miControlador;
	protected ListaClientes listaClientes = new ListaClientes();
	protected ListaArticulos listaArticulos = new ListaArticulos();
	protected ListaPedidos listaPedidos = new ListaPedidos();
	
	
	
	public void setControlador(Controlador miControlador) {
		this.miControlador=miControlador;
	}
	
	public void crearArticulo(String codigo_articulo, String descripcion_articulo, float pvp_articulo, Duration tiempoPrep_articulo_parsed, float gastosEnvioArticulo) {
		
		try {
			//Instanciamos el articulo
			this.articulo = new Articulo(codigo_articulo, descripcion_articulo, pvp_articulo, tiempoPrep_articulo_parsed, gastosEnvioArticulo);
			
	 		if (articulo != null) {
	 			System.out.println("Se ha creado un nuevo artículo con las siguientes características:\n");
	 			System.out.println(articulo.toString());
	 			this.anadirArticuloAListaArticulos(articulo);
	 		}	
			
		} catch (Exception ex) {
			// printStackTrace method
            // prints line numbers + call stack
            ex.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(ex);
		}
	}
	
	public void anadirArticuloAListaArticulos(Articulo articulo) {
		
		try {
			//Anadimos el articulo a la lista de articulos
			this.listaArticulos.add(articulo);
			
		} catch (Exception ex) {
			// printStackTrace method
            // prints line numbers + call stack
            ex.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(ex);
		}
	}
	

	/**
	 * @return the articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}


	/**
	 * @param cliente the articulo to set
	 */
	public void setArticulo(Cliente cliente) {
		this.articulo = articulo;
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
	
	public Class<? extends Cliente> getTipoCliente() {
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
