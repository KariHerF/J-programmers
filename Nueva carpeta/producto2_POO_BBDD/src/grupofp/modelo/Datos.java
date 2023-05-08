/**
 *
 */
package grupofp.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

import grupofp.controlador.Controlador;
import grupofp.dao.ArticuloDAO;
import grupofp.dao.ArticuloDAOImpl;
import grupofp.dao.ClienteDAO;
import grupofp.dao.DAOFactory;
import grupofp.dao.MySQLDAOFactory;
import grupofp.dao.PedidoDAO;
import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidClientTypeException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidDNIorNIEFormatException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidEmailFormatException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidEmpyArgumentException;
import grupofp.excepciones.ExcepcionesPersonalizadas.NotFloatException;
import grupofp.vista.GestionOS;

/**
 * @author J-Programers
 *
 */

public class Datos {

	// Lista de tipos de DAO disponibles en la factoria
	private static final int TIPO_FACTORIA = 1;
	private static final  String CLIENTE_ESTANDAR = "estandar";
	private static final String CLIENTE_PREMIUM = "premium";
	private Articulo articulo;
	private Cliente cliente;
	private Pedido pedido;
	private Controlador miControlador;
	protected ListaClientes listaClientes = new ListaClientes();
	protected ListaArticulos listaArticulos = new ListaArticulos();
	protected ListaPedidos listaPedidos = new ListaPedidos();

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	//Validación de parametros

	public void validarDNIoNIE(String dniNumber) throws InvalidDNIorNIEFormatException {
	    String dniRegex = "^\\d{8}[A-Z]$"; // Expresión regular para DNI
	    String nieRegex = "^[XYZ]\\d{7}[A-Z]$";// Expresión regular para NIE

	    if (!dniNumber.matches(dniRegex) && !dniNumber.matches(nieRegex)) {
	        throw new InvalidDNIorNIEFormatException("Formato inválido de NIE o DNI");
	    }
	    // Si el formato de DNI o NIE es válido, continúa el flujo de ejecución
	}


	public void validarEmail(String email) throws InvalidEmailFormatException {
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
	                        "[a-zA-Z0-9_+&*-]+)*@" +
	                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; // Expresion regular para detectar formato de email
	    if (!email.matches(emailRegex)) {
	        throw new InvalidEmailFormatException("Formato inválido de email");
	    }
	    // Si el formato de email es correcto, se continúa la ejecución
	}

	public void validarArgumentoNoVacio(String entrada_teclado) throws InvalidEmpyArgumentException {
	    String argumento = "";
	    if (argumento.matches(entrada_teclado)) {
	        throw new InvalidEmpyArgumentException("Es necesario introducir un valor para el para el parámetro");
	    }
	    // Si el argumento de entrada no es vacío, se continúa con la ejecución
	}

	public void validarTipoCliente(String tipo_cliente) throws InvalidClientTypeException {
	    String tipo_estandar = "estandar";
	    String tipo_premium = "premium";
	    if (!tipo_cliente.matches(tipo_estandar) && !tipo_cliente.matches(tipo_premium)) {
	        throw new InvalidClientTypeException("Es necesario introducir valor válido para indicar el tipo de cliente. El valor puede indicarse escribiendo por teclado: \"estandar\" o \"premium\".");
	    }
	    // Si el tipo de cliente se ha indicado correctamente, se continúa con la ejecución
	}

	public void validarArgumentoFloat(float input) throws NotFloatException {
	    if (Float.floatToIntBits(input) == 0) {
	        throw new NotFloatException("El argumento de entrada debe de ser compatible con un tipo float");
	    }
	    // Si el argumento de entrada es un float, se continúa con la ejecución
	}

	/**
	 * @return the listaClientes
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public ListaClientes getListaClientes() throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS

		// Instaciamos un articuloDAO para persistir un nuevo Articulo
		DAOFactory daoFactory = null;
		ClienteDAO clienteDAO = daoFactory.getDAOFactory(TIPO_FACTORIA).obtenerClienteDAO();
		return clienteDAO.obtenerTodosClientes();
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(ListaClientes listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return the listaArticulos
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public ListaArticulos getListaArticulos() throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS
		MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
		// Instaciamos un articuloDAO para persistir un nuevo Articulo
		ArticuloDAO articuloDAO = mySQLFactory.obtenerArticuloDAO();
		return articuloDAO.obtenerTodosLosArticulos();
	}

	/**
	 * @param listaArticulos the listaArticulos to set
	 */
	public void setListaArticulos(ListaArticulos listaArticulos) {
		this.listaArticulos = listaArticulos;
	}

	/**
	 * @return the listaPedidos
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	public ListaPedidos getListaPedidos() throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS
		MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
		// Instaciamos un pedidoDAO para obtener la lista de pedidos registrados en la bd
		PedidoDAO pedidoDAO = mySQLFactory.obtenerPedidoDAO();
		return pedidoDAO.obtenerPedidos();
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
			
			// Referenciamos a nuestra factoria de DAOS
			DAOFactory miFactoria = null;
			// Instaciamos un articuloDAO de la factoría indicada para persistir un nuevo Articulo
			ArticuloDAO articuloDAO = miFactoria.getDAOFactory(TIPO_FACTORIA).obtenerArticuloDAO();

			if (this.articulo != null) {		
				System.out.println("");
				System.out.println("Se está intentando añadir a la bd un artículo, con las siguientes características:");
				System.out.println(this.articulo.toString());
				articuloDAO.insertarArticulo(articulo);
				System.out.println("Se ha guardado el nuevo artículo en bd.\n");
			}

		} catch (Exception ex) {
			// Print de stacktrace en caso de excepcion
			ex.printStackTrace();
			System.out.println(ex);
		}
	}

	public void anadirClienteAListaClientes(Cliente cliente) {

		try {
			// Anadimos el articulo a la lista de articulos
			this.listaClientes.add(cliente);

		} catch (Exception ex) {
			// Printa numeros de linea + stack de llamadas
			ex.printStackTrace();
			// Printa el tipo de excepcion que se lanza
			System.out.println(ex);
		}
	}

	public void crearCliente(String email_cliente, String nombre_cliente, String domicilio_cliente, String nif_cliente,
			String tipo_cliente) {

		try {
			// Instanciamos nuestra factoria de DAOS
			MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
			// Instaciamos un ClienteDAO para persistir un nuevo cliente
			ClienteDAO clienteDAO = mySQLFactory.obtenerClienteDAO();
			if (tipo_cliente.equals(CLIENTE_ESTANDAR)) {
				float cuotaAnual = clienteDAO.obtenerCuotaPorTipoCliente(tipo_cliente);
				float gastosEnvio = clienteDAO.obtenerDescuentoEnvioPorTipoCliente(tipo_cliente);
				System.out.println("");
				System.out.println("Se está intentando guardar en bd, el cliente estandar:");
				this.cliente = new ClienteEstandar(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente, cuotaAnual, gastosEnvio);
				cliente.toString();
				clienteDAO.insertarCliente(cliente);
				System.out.println("Cliente estándar creado añadido a la bd.");
				
			} else if (tipo_cliente.equals(CLIENTE_PREMIUM)) {
				float cuotaAnual = clienteDAO.obtenerCuotaPorTipoCliente(tipo_cliente);
				float gastosEnvio = clienteDAO.obtenerDescuentoEnvioPorTipoCliente(tipo_cliente);
				System.out.println("");
				System.out.println("Se está intentando guardar en bd, el cliente premium:");
				this.cliente = new ClientePremium(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente, tipo_cliente, cuotaAnual, gastosEnvio);
				cliente.toString();
				clienteDAO.insertarCliente(cliente);
				System.out.println("Cliente premium añadido a la bd.");
			} else {
				// TODO: POSIBLE CASO PARA LANZAR UNA EXCEPCIÓN PERSSONALIZADA (No se ha
				// respetado el formato para
				// indicar el tipo de cliente)
				System.out.println(
						"Tipo de cliente no reconocido. Debe ser \"estandar\" o \"premium\", ya que son los únicos tipos de cliente soportados por el momento.");
			}

			if (this.cliente != null) {
				System.out.println("Se ha creado un nuevo cliente con las siguientes características:\n");
				System.out.println(this.cliente.toString());
			}

		} catch (Exception ex) {
			// Print para la traza de error de una excepción no esperada
			ex.printStackTrace();
			System.out.println(ex);
		}
	}

	public Cliente getClienteDeListaClientes(String email_cliente) throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS
		MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
		// Instaciamos un clienteDAO para poder llamar al metodo obtenerCliente de clienteDAO y 
		// obtener los datos de un cliente buscando por su email sobre la tabla clientes de la bd
		ClienteDAO clienteDAO = mySQLFactory.obtenerClienteDAO();
		return clienteDAO.obtenerCliente(email_cliente);
	}

	public Articulo getArticuloDeListaArticulos(String codigo_articulo) throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS
		MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
		// Instaciamos un articuloDAO para poder llamar al metodo obtenerArticulo de articuloDAO y
		// obtener los datos de un articulo buscando por su codigo sobre la tabla articulos de la bd
		ArticuloDAO articuloDAO = mySQLFactory.obtenerArticuloDAO();
		return articuloDAO.obtenerArticulo(codigo_articulo);
	}

	public Pedido getPedidoDeListaPedidos(int numPedido) throws SQLException, DAOException {
		// Instanciamos nuestra factoria de DAOS
		MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
		// Instaciamos un pedidoDAO para poder llamar al metodo obtenerArticulo de articuloDAO y
		// obtener los datos de un articulo buscando por su codigo sobre la tabla articulos de la bd
		PedidoDAO pedidoDAO = mySQLFactory.obtenerPedidoDAO();
		return pedidoDAO.obtenerPedidoPorId(numPedido);
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

	public void crearPedido(String email_cliente, String codigo_articulo, LocalDateTime fechaHora,
			int cantUnidades) throws SQLException, DAOException {

		Articulo articulo = this.getArticuloDeListaArticulos(codigo_articulo);
		Cliente cliente = this.getClienteDeListaClientes(email_cliente);
		
		if (articulo == null) {
			// TODO:Lanzar una posible excepción personalizada
			System.out.println(
					"Se está intentando generar un pedido con un código de artículo no registrado, "
					+ "debe de introducir código de artículo que se corresponda con un artículo previamente resgistrado.\n Para registrar un nuevo artículo navege hasta el menú \"GESTION DE ARTICULOS\". ");
		} else if (cliente == null) {
			System.out
					.println("Se está intentando generar un pedido con un email de cliente no registrado, por favor, para proceder al registro introduzca:");
			this.miControlador.getvGestionOS().anadirClienteVistaGestionOS();
			this.crearPedido(email_cliente, codigo_articulo, fechaHora, cantUnidades);
		} else {

			try {
				// Instanciamos el pedido
				this.pedido = new Pedido(cliente, articulo, fechaHora, cantUnidades);

				// Instanciamos nuestra factoria de DAOS
				MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
				// Instaciamos un ClienteDAO para persistir un nuevo pedido
				PedidoDAO pedidoDAO = mySQLFactory.obtenerPedidoDAO();
				pedidoDAO.insertarPedido(pedido);
				if (this.pedido != null) {
					System.out.println("Se ha creado un nuevo pedido con las siguientes características:");
					System.out.println(this.pedido.toString());
					this.anadirPedidoAListaPedidos(this.pedido);
				}

			} catch (Exception ex) {
				// Prints de traza de error no esperada
				ex.printStackTrace();
				System.out.println(ex);
			}
		}
	}

	public void eliminarPedido(int numPedido) {


		try {
			// Comprobamos que se está eliminando un pedido que exista
			Pedido pedido = getPedidoDeListaPedidos(numPedido);
					
			if (pedido == null) {
				System.out.println("Se ha indicado un número de pedido para eliminar pedido, que no se corresponde con ningún pedido existente.");
			} else {
				//TODO: esto quizás podría gestionarse con una excepción personalizada
				if (!pedido.pedidoEnviado()) {
					System.out.println("Se procede a cancelar y eliminar el pedido del sistema.");
					// Instanciamos nuestra factoria de DAOS
					MySQLDAOFactory mySQLFactory = new MySQLDAOFactory();
					// Instaciamos un ClienteDAO para persistir un nuevo cliente
					PedidoDAO pedidoDAO = mySQLFactory.obtenerPedidoDAO();
					pedidoDAO.eliminarPedido(numPedido);
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
	public void setArticulo(Articulo articulo) {
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

	}

	public int getNumPedido() {
		return this.pedido.getNumPedido();
	}

	public void setClientePedido(Cliente cliente) {
		this.pedido.setCliente(cliente);

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
