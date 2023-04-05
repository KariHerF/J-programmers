package grupofp.controlador;

import java.time.Duration;
import java.util.Date;

import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.Datos;
import grupofp.modelo.Lista;
import grupofp.modelo.ListaArticulos;
import grupofp.modelo.ListaClientes;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.vista.GestionOS;

/**
 * @author J-Programers
 *
 */
public class Controlador {

	private GestionOS vGestionOS;
	private Datos datos;

	/**
	 * @param vista
	 * @param modelo
	 */
	public Controlador(GestionOS vGestionOS, Datos datos) {
		this.vGestionOS = vGestionOS;
		this.datos = datos;
	}
	
	public void crearArticulo(String codigo_articulo, String descripcion_articulo, float pvp_articulo, Duration tiempoPrep_articulo_parsed, float gastosEnvioArticulo) {
		datos.crearArticulo(codigo_articulo, descripcion_articulo, pvp_articulo, tiempoPrep_articulo_parsed, gastosEnvioArticulo);
	}
	
	public ListaArticulos getListaArticulos() {
		return datos.getListaArticulos();
	}
	
	public void crearCliente(String email_cliente, String nombre_cliente, String domicilio_cliente,String nif_cliente, String sn_tipo_cliente) {
		datos.crearCliente(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, sn_tipo_cliente);
	}
	
	public ListaClientes getListaClientes() {
		return datos.getListaClientes();
	}
	
	public Lista<ClienteEstandar> getListaClientesEstandar() {
		
		Lista<ClienteEstandar> listaClientesEstandar = new Lista<>();
		
		for (Cliente cliente : datos.getListaClientes()) {
			  if (cliente instanceof ClienteEstandar) {
				  listaClientesEstandar.add((ClienteEstandar) cliente);
			  }
		}
		return listaClientesEstandar;
	}
	
	public Lista<ClientePremium> getListaClientesPremium() {
		
		Lista<ClientePremium> listaClientesPremium = new Lista<>();
		
		for (Cliente cliente : datos.getListaClientes()) {
			  if (cliente instanceof ClientePremium) {
				  listaClientesPremium.add((ClientePremium) cliente);
			  }
		}
		return listaClientesPremium;
	}
	
	public void crearPedido(int numPedido, String email_cliente, String codigo_articulo, Date fechaHora, int cantUnidades) {
		datos.crearPedido(numPedido, email_cliente, codigo_articulo, fechaHora, cantUnidades);
	}

	//Getters y setters para actuar sobre todas las clases del modelo
	
	//Para articulo:
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

	public void setTiempoPrepArticulo(Duration tiempoPrep) {
		this.datos.setTiempoPrepArticulo(tiempoPrep);
	}

	public Duration getTiempoPrepArticulo() {
		return this.datos.getTiempoPrepArticulo();
	}

	//Para cliente:
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

	//Para Pedido:
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

	public void setFechaHoraPedido(Date fechaHora) {
		this.datos.setFechaHoraPedido(fechaHora);
	}

	public Date getFechaHoraPedido() {
		return this.datos.getFechaHoraPedido();
	}

	// Pasa el modelo a la vista para presentar los datos
	public void actualizarVistaArticulos() {
		vGestionOS.printArticuloDetalles(datos.getCodigoArticulo(), datos.getDescripcionArticulo(), datos.getPvpArticulo(), datos.getGastosEnvioArticulo(), datos.getTiempoPrepArticulo());
	}

	public void actualizarVistaClientes() {
		vGestionOS.printClienteDetalles(datos.getNombreCliente(), datos.getDomicilioCliente(), datos.getNifCliente(), datos.getEmailCliente(), datos.getTipoCliente(), datos.getCalcAnualCliente(), datos.getDescuentoEnvCliente());
	}

	public void actualizarVistaPedidos() {
		vGestionOS.printPedidoDetalles(datos.getNumPedido(), datos.getClientePedido(), datos.getArticuloPedido(), datos.getCantUnidadesPedido(), datos.getFechaHoraPedido(), datos.getPedidoEnviado(), datos.getPrecioEnvioPedido());
	}

}
