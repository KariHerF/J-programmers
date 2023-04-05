/**
 * 
 */
package grupofp.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import grupofp.controlador.Controlador;
import grupofp.vista.GestionOS;

/**
 * @author J-Programers
 *
 */

public class Datos {

	private Articulo articulo;
	private Cliente cliente;
	private Pedido pedido;
	private Controlador miControlador;
	private GestionOS miVistaGestionOS;
	protected ListaClientes listaClientes = new ListaClientes();
	protected ListaArticulos listaArticulos = new ListaArticulos();
	protected ListaPedidos listaPedidos = new ListaPedidos();

	protected String cliente_premium = "PREMIUM";
	protected String cliente_estandar = "ESTANDAR";

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * @return the listaClientes
	 */
	public ListaClientes getListaClientes() {
		return listaClientes;
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return the listaArticulos
	 */
	public ListaArticulos getListaArticulos() {
		return listaArticulos;
	}

	/**
	 * @param listaArticulos the listaArticulos to set
	 */
	public void setListaArticulos(ListaArticulos listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	/**
	 * @return the listaPedidos
	 */
	public ListaPedidos getListaPedidos() {
		return listaPedidos;
	}

	/**
	 * @param listaPedidos the listaPedidos to set
	 */
	public void setListaPedidos(ListaPedidos listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public void anadirArticuloAListaArticulos(Articulo articulo) {

		try {
			// Anadimos el articulo a la lista de articulos
			this.listaArticulos.add(articulo);

		} catch (Exception ex) {
			// printStackTrace method
			// prints line numbers + call stack
			ex.printStackTrace();
			// Prints what exception has been thrown
			System.out.println(ex);
		}
	}

	public void crearArticulo(String codigo_articulo, String descripcion_articulo, float pvp_articulo,
			Duration tiempoPrep_articulo_parsed, float gastosEnvioArticulo) {

		try {
			// Instanciamos el articulo
			this.articulo = new Articulo(codigo_articulo, descripcion_articulo, pvp_articulo,
					tiempoPrep_articulo_parsed, gastosEnvioArticulo);

			if (this.articulo != null) {
				System.out.println("Se ha creado un nuevo artículo con las siguientes características:\n");
				System.out.println(this.articulo.toString());
				this.anadirArticuloAListaArticulos(this.articulo);
			}

		} catch (Exception ex) {
			// printStackTrace method
			// prints line numbers + call stack
			ex.printStackTrace();
			// Prints what exception has been thrown
			System.out.println(ex);
		}
	}

	public void anadirClienteAListaClientes(Cliente cliente) {

		try {
			// Anadimos el articulo a la lista de articulos
			this.listaClientes.add(cliente);

		} catch (Exception ex) {
			// printStackTrace method
			// prints line numbers + call stack
			ex.printStackTrace();
			// Prints what exception has been thrown
			System.out.println(ex);
		}
	}

	public void crearCliente(String email_cliente, String nombre_cliente, String domicilio_cliente, String nif_cliente,
			String sn_tipo_cliente) {

		try {
			// Instanciamos el cliente según su tipo
			if (sn_tipo_cliente.toUpperCase().equals(cliente_estandar)) {
				this.cliente = new ClienteEstandar(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente);
				System.out.println("Cliente estándar creado.");
				this.anadirClienteAListaClientes(this.cliente);
			} else if (sn_tipo_cliente.toUpperCase().equals(cliente_premium)) {
				this.cliente = new ClientePremium(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente);
				System.out.println("Cliente premium creado.");
				this.anadirClienteAListaClientes(this.cliente);
			} else {
				// TODO: POSIBLE CASO PARA LANZAR UNA EXCEPCIÓN PERSSONALIZADA (No se ha
				// respetado el formato para
				// indicar el tipo de cliente)
				System.out.println(
						"Tipo de cliente no reconocido. Debe indicar \"estandar\" o \"premium\" para poder determinar el tipo de cliente a crear");
			}

			if (this.cliente != null) {
				System.out.println("Se ha creado un nuevo cliente con las siguientes características:\n");
				System.out.println(this.cliente.toString());
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

	public Cliente getClienteDeListaClientes(String email_cliente) {
		for (Cliente cliente : this.listaClientes) {
			if (cliente.getEmail().equals(email_cliente) == true) {
				return cliente;
			}
		}
		return null;
	}

	public Articulo getArticuloDeListaArticulos(String codigo_articulo) {
		for (Articulo articulo : this.listaArticulos) {
			if (articulo.getCodigo().equals(codigo_articulo) == true) {
				return articulo;
			}
		}
		return null;
	}

	public Pedido getPedidoDeListaPedidos(int numPedido) {
		for (Pedido pedido : this.listaPedidos) {
			if ((this.pedido.getNumPedido() == numPedido) == true) {
				return pedido;
			}
		}
		return null;
	}
	
	public void anadirPedidoAListaPedidos(Pedido pedido) {

		try {
			// Anadimos el articulo a la lista de articulos
			this.listaPedidos.add(pedido);

		} catch (Exception ex) {
			// printStackTrace method
			// prints line numbers + call stack
			ex.printStackTrace();
			// Prints what exception has been thrown
			System.out.println(ex);
		}
	}

	public void crearPedido(int numPedido, String email_cliente, String codigo_articulo, LocalDateTime fechaHora,
			int cantUnidades) {

		Cliente cliente_pedido;
		Articulo articulo_pedido;

		if (this.getArticuloDeListaArticulos(codigo_articulo) == null) {
			// TODO:Lanzar una posible excepción personalizada
			System.out.println(
					"Se está intentando generar un pedido con un código de artículo no registrado, "
					+ "debe de introducir código de artículo que se corresponda con un artículo previamente resgistrado.");
		} else if (this.getClienteDeListaClientes(email_cliente) == null) {
			System.out
					.println("Se está intentando generar un pedido con un email de cliente no registrado, por favor:");
			miVistaGestionOS.anadirClienteVistaGestionOS();
			this.crearPedido(numPedido, email_cliente, codigo_articulo, fechaHora, cantUnidades);
		} else {

			try {
				// Instanciamos el articulo
				this.pedido = new Pedido(numPedido, this.getClienteDeListaClientes(email_cliente), this.getArticuloDeListaArticulos(codigo_articulo), fechaHora, cantUnidades);

				if (this.pedido != null) {
					System.out.println("Se ha creado un nuevo pedido con las siguientes características:");
					System.out.println(this.pedido.toString());
					this.anadirPedidoAListaPedidos(this.pedido);
				}

			} catch (Exception ex) {
				// printStackTrace method
				// prints line numbers + call stack
				ex.printStackTrace();
				// Prints what exception has been thrown
				System.out.println(ex);
			}
		}
	}
	
	public void eliminarPedido(int numPedido) {
		
		LocalDateTime fechaHora_pedido;
		LocalDateTime fechaHora_actual = LocalDateTime.now();
		LocalDateTime fechaHora_pedido_con_tiempo_prep_articulo_sumado;
		Duration duracion_prep_articulo_de_pedido;
		try {
			// Comprobamos que se está eliminando un pedido que exista
			if (getPedidoDeListaPedidos(numPedido) == null) {
				System.out.println("Se ha indicado un número de pedido para eliminar pedido, que no se corresponde con ningún pedido existente.");
			} else {
				fechaHora_pedido = getPedidoDeListaPedidos(numPedido).getFechaHora();
				duracion_prep_articulo_de_pedido = getPedidoDeListaPedidos(numPedido).getArticulo().getTiempoPrep();
				
				fechaHora_pedido_con_tiempo_prep_articulo_sumado =  fechaHora_pedido.plus(duracion_prep_articulo_de_pedido);
				
				// Comparar los dos objetos LocalDateTime
				int resultado_com_fechas = fechaHora_actual.compareTo(fechaHora_pedido_con_tiempo_prep_articulo_sumado);
				
				//TODO: esto quizás podría gestionarse con una excepción personalizada
				if (resultado_com_fechas < 0) {
					System.out.println("Se procede a cancelar y eliminar el pedido del sistema.");
					this.listaPedidos.remove(getPedidoDeListaPedidos(numPedido));
				} else {
				    System.out.println("El pedido no se puede cancelar, ya se ha superado el tiempo de preparación.");
				}
			}
			

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

	public void setFechaHoraPedido(LocalDateTime fechaHora) {
		this.pedido.setFechaHora(fechaHora);
	}

	public LocalDateTime getFechaHoraPedido() {
		return this.pedido.getFechaHora();
	}

	public void setCantUnidadesPedido(int unidades) {
		this.pedido.setCantUnidades(unidades);
	}

	public int getCantUnidadesPedido() {
		return this.pedido.getCantUnidades();
	}

	public boolean getPedidoEnviado() {
		return this.pedido.pedidoEnviado();
	}

	public float getPrecioEnvioPedido() {
		return this.pedido.precioEnvio();
	}

}
