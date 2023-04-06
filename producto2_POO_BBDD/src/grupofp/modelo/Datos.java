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
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidClientType;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidDNIorNIEFormatException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidEmailFormatException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidEmpyArgumentException;
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

	// Validaci�n de parametros

	public void validarDNIoNIE(String dniNumber) throws InvalidDNIorNIEFormatException {
		String dniRegex = "^\\d{8}[A-Z]$"; // Expresi�n regular para DNI
		String nieRegex = "^[XYZ]\\d{7}[A-Z]$";// Expresi�n regular para NIE

		if (!dniNumber.matches(dniRegex) && !dniNumber.matches(nieRegex)) {
			throw new InvalidDNIorNIEFormatException("Formato inv�lido de NIE o DNI");
		}
		// Si el formato de DNI o NIE es v�lido, contin�a el flujo de ejecuci�n
	}

	public void validarEmail(String email) throws InvalidEmailFormatException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
				"[a-zA-Z0-9_+&*-]+)*@" +
				"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; // Expresion regular para detectar formato de email
		if (!email.matches(emailRegex)) {
			throw new InvalidEmailFormatException("Formato inv�lido de email");
		}
		// Si el formato de email es correcto, se contin�a la ejecuci�n
	}

	public void validarArgumentoNoVacio(String entrada_teclado) throws InvalidEmpyArgumentException {
		String argumento = "";
		if (argumento.matches(entrada_teclado)) {
			throw new InvalidEmpyArgumentException("Es necesario introducir un valor para el para el par�metro");
		}
		// Email format is correct, continue with other code
	}

	public void validarTipoCliente(String entrada_teclado) throws InvalidClientType {
		String argumento1 = "ESTANDARD";
		String argumento2 = "PREMIUM";
		if (!argumento1.matches(entrada_teclado.toUpperCase()) && !argumento2.matches(entrada_teclado.toUpperCase())) {
			throw new InvalidClientType("El cliente debe ser 'estandard' o 'premium'");
		}
		// Client format is correct, continue with other code
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
				System.out.println("Se ha creado un nuevo art�culo con las siguientes caracter�sticas:\n");
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

		// Instanciamos el cliente seg�n su tipo

		try {
			if (sn_tipo_cliente.toUpperCase().equals(cliente_estandar)) {
				this.cliente = new ClienteEstandar(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente);
				System.out.println("Cliente est�ndar creado.");
				this.anadirClienteAListaClientes(this.cliente);
			} else if (sn_tipo_cliente.toUpperCase().equals(cliente_premium)) {
				this.cliente = new ClientePremium(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente);
				System.out.println("Cliente premium creado.");
				this.anadirClienteAListaClientes(this.cliente);
			}

			if (this.cliente != null) {
				System.out.println("Se ha creado un nuevo cliente con las siguientes caracter�sticas:\n");
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
		boolean pedido_encontrado = false;

		for (Pedido pedido : this.getListaPedidos()) {
			if (pedido.getNumPedido() == numPedido) {
				pedido_encontrado = true;
			}
			if (pedido_encontrado == true) {
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
			// TODO:Lanzar una posible excepci�n personalizada
			System.out.println(
					"Se est� intentando generar un pedido con un c�digo de art�culo no registrado, "
							+ "debe de introducir c�digo de art�culo que se corresponda con un art�culo previamente resgistrado.\n Para registrar un nuevo art�culo navege hasta el men� \"GESTION DE ARTICULOS\". ");
		} else if (this.getClienteDeListaClientes(email_cliente) == null) {
			System.out
					.println(
							"Se est� intentando generar un pedido con un email de cliente no registrado, por favor, para proceder al registro introduzca:");
			this.miControlador.getvGestionOS().anadirClienteVistaGestionOS();
			this.crearPedido(numPedido, email_cliente, codigo_articulo, fechaHora, cantUnidades);
		} else {

			try {
				// Instanciamos el articulo
				this.pedido = new Pedido(numPedido, this.getClienteDeListaClientes(email_cliente),
						this.getArticuloDeListaArticulos(codigo_articulo), fechaHora, cantUnidades);

				if (this.pedido != null) {
					System.out.println("Se ha creado un nuevo pedido con las siguientes caracter�sticas:");
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

		try {
			// Comprobamos que se est� eliminando un pedido que exista
			if (getPedidoDeListaPedidos(numPedido) == null) {
				System.out.println(
						"Se ha indicado un n�mero de pedido para eliminar pedido, que no se corresponde con ning�n pedido existente.");
			} else {
				// TODO: esto quiz�s podr�a gestionarse con una excepci�n personalizada
				if (this.getPedidoEnviado() == false) {
					System.out.println("Se procede a cancelar y eliminar el pedido del sistema.");
					this.listaPedidos.remove(getPedidoDeListaPedidos(numPedido));
				} else {
					System.out.println("El pedido no se puede cancelar, ya se ha superado el tiempo de preparaci�n.");
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
