/**
 *
 */
package grupofp.vista;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import grupofp.controlador.Controlador;
import grupofp.excepciones.ExcepcionesPersonalizadas.DAOException;
import grupofp.excepciones.ExcepcionesPersonalizadas.InvalidEmpyArgumentException;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import grupofp.modelo.ClienteEstandar;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Pedido;

/**
 * @author kahef
 *
 */
public class GestionOS {

	private Controlador miControlador;

	// Setar un objeto miControlador que permite la relacion entre esta clase y la
	// clase Controlador
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	// Utilidades para el apartado del men�: Gestion de Articulos
	public void anadirArticuloVistaGestionOS() {

		try {
			String codigo_articulo;
			String descripcion_articulo;
			float pvp_articulo;
			String tiempoPrep_articulo;
			Duration tiempoPrep_articulo_parsed;
			float gastosEnvioArticulo = 0.0f;

			System.out.println("Introducir c�digo del art�culo:");
			Scanner sn_codigo_articulo = new Scanner(System.in);
			codigo_articulo = sn_codigo_articulo.nextLine().toUpperCase();

			// Validamos argumento no vac�o
			this.miControlador.getDatos().validarArgumentoNoVacio(codigo_articulo);

			System.out.println("Introducir descripci�n del art�culo:");
			Scanner sn_descripcion_articulo = new Scanner(System.in);
			descripcion_articulo = sn_descripcion_articulo.nextLine();

			// Validamos argumento no vac�o
			this.miControlador.getDatos().validarArgumentoNoVacio(descripcion_articulo);

			System.out.println("Introducir pvp del art�culo:");
			Scanner sn_pvp_articulo = new Scanner(System.in);
			pvp_articulo = sn_pvp_articulo.nextFloat();

			System.out.println("Introducir el tiempo de preparaci�n del art�culo:");
			System.out.println(
					"(la duraci�n del tiempo de preparaci�n debe introducirse en formato ISO 8601 (PTnHnMnS))");
			Scanner sn_tiempo_prep_articulo = new Scanner(System.in);
			tiempoPrep_articulo = sn_tiempo_prep_articulo.nextLine();
			// Validamos argumento no vac�o
			this.miControlador.getDatos().validarArgumentoNoVacio(tiempoPrep_articulo);
			Duration duration = Duration.parse(tiempoPrep_articulo);
			tiempoPrep_articulo_parsed = duration;

			System.out.println("Introducir gastos env�o del art�culo:");
			Scanner sn_gastos_envio_articulo = new Scanner(System.in);
			
			// Validamos argumento float
			if (sn_gastos_envio_articulo.hasNextFloat()) {
				gastosEnvioArticulo = sn_gastos_envio_articulo.nextFloat();
	            System.out.println("Los gastos de env�o reconocidos para el art�culo son: " + gastosEnvioArticulo);
	        } else {
	        	throw new IllegalArgumentException("Para este par�metro se espera la introducci�n de un valor decimal, el valor introducido no es un n�mero decimal v�lido.");
	        }

			this.miControlador.crearArticulo(codigo_articulo, descripcion_articulo, pvp_articulo,
					tiempoPrep_articulo_parsed, gastosEnvioArticulo);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void mostrarArticulos() throws SQLException, DAOException {
		if (this.miControlador.getListaArticulos().size() > 0) {
			for (Articulo articulo : this.miControlador.getListaArticulos()) {
				System.out.println(articulo.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de art�culos, ya que no se ha registrado ning�n art�culo hasta el momento.");
		}
	}

	// Utilidades para el apartado del men�: Gestion de Clientes
	public void anadirClienteVistaGestionOS() {

		try {
			String email_cliente;
			String nombre_cliente;
			String domicilio_cliente;
			String nif_cliente;
			String tipo_cliente;

			System.out.println("Introducir email de cliente:");
			Scanner sn_email_cliente = new Scanner(System.in);
			email_cliente = sn_email_cliente.nextLine();

			// Validamos formato de email de cliente
			this.miControlador.getDatos().validarEmail(email_cliente);

			System.out.println("Introducir nombre del cliente:");
			Scanner sn_nombre_cliente = new Scanner(System.in);
			nombre_cliente = sn_nombre_cliente.nextLine();

			// Validamos argumento no vac�o
			this.miControlador.getDatos().validarArgumentoNoVacio(nombre_cliente);

			System.out.println("Introducir domicilio del cliente:");
			Scanner sn_domicilio_cliente = new Scanner(System.in);
			domicilio_cliente = sn_domicilio_cliente.nextLine();

			// Validamos argumento no vac�o
			this.miControlador.getDatos().validarArgumentoNoVacio(domicilio_cliente);

			System.out.println("Introducir NIF o NIE del cliente:");
			Scanner sn_nif_cliente = new Scanner(System.in);
			nif_cliente = sn_nif_cliente.nextLine();

			// Validamos formato de DNI o NIE
			this.miControlador.getDatos().validarDNIoNIE(nif_cliente);

			System.out.println("Introducir tipo de cliente (\"estandar\" o \"premium\") :");
			Scanner sn_tipo_cliente = new Scanner(System.in);
			tipo_cliente = sn_tipo_cliente.nextLine();

			// Validamos tipo de cliente
			this.miControlador.getDatos().validarTipoCliente(tipo_cliente);
			this.miControlador.crearCliente(email_cliente, nombre_cliente, domicilio_cliente, nif_cliente,
					tipo_cliente);

		} catch (Exception ex) {
			// Imprime el tipo de excepci�n capturada y su stack trace
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void mostrarClientes() throws SQLException, DAOException {
		if (this.miControlador.getListaClientes().size() > 0) {
			for (Cliente cliente : this.miControlador.getListaClientes()) {
				System.out.println(cliente.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de clientes, ya que no se ha registrado ning�n cliente hasta el momento.");
		}
	}

	public void mostrarClientesEstandar() throws SQLException, DAOException {
		if (this.miControlador.getListaClientesEstandar().size() > 0) {
			for (ClienteEstandar cliente_estandar : this.miControlador.getListaClientesEstandar()) {
				System.out.println(cliente_estandar.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de clientes est�ndar, ya que no se ha registrado ning�n cliente est�ndar hasta el momento.");
		}
	}

	public void mostrarClientesPremium() throws SQLException, DAOException {
		if (this.miControlador.getListaClientesPremium().size() > 0) {
			for (ClientePremium cliente_premium : this.miControlador.getListaClientesPremium()) {
				System.out.println(cliente_premium.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de clientes premium, ya que no se ha registrado ning�n cliente premium hasta el momento.");
		}
	}

	// Utilidades para el apartado del men�: Gestion de Pedidos
	public void anadirPedidoVistaGestionOS() {

		try {
			int num_pedido;
			String email_cliente_pedido;
			String codigo_articulo_pedido;
			LocalDateTime fechaHora_pedido;
			int cantUnidades_pedido;

			/*
			 * System.out.println("Introducir n�mero de pedido:"); Scanner sn_numero_pedido
			 * = new Scanner(System.in); num_pedido = sn_numero_pedido.nextInt();
			 */

			System.out.println("Introducir email del cliente del pedido:");
			Scanner sn_email_cliente_pedido = new Scanner(System.in);
			email_cliente_pedido = sn_email_cliente_pedido.nextLine();

			System.out.println("Introducir c�digo del art�culo del pedido:");
			Scanner sn_codigo_articulo_pedido = new Scanner(System.in);
			codigo_articulo_pedido = sn_codigo_articulo_pedido.nextLine();

			fechaHora_pedido = LocalDateTime.now();

			System.out.println("Introducir cantidad de unidades del art�culo para el pedido:");
			Scanner sn_cantUnidades_pedido = new Scanner(System.in);
			cantUnidades_pedido = sn_cantUnidades_pedido.nextInt();

			this.miControlador.crearPedido(email_cliente_pedido, codigo_articulo_pedido, fechaHora_pedido,
					cantUnidades_pedido);
		} catch (Exception ex) {
			// Prints para la traza de error de una excepcion no esperada
			ex.printStackTrace();
			System.out.println(ex);
		}
	}

	public void eliminarPedidoVistaGestionOS() {
		int num_pedido_a_eliminar;

		System.out.println("Introducir c�digo del pedido:");
		Scanner sn_num_pedido_a_eliminar = new Scanner(System.in);
		num_pedido_a_eliminar = sn_num_pedido_a_eliminar.nextInt();

		if (num_pedido_a_eliminar >= 0) {
			this.miControlador.eliminarPedido(num_pedido_a_eliminar);
		} else {
			// TODO: excepci�n personalizada?
			System.out.println("Introducir un c�digo de art�culo v�lido");
		}
	}

	public void mostrarPedidosPendientesVistaGestionOS() throws SQLException, DAOException {
		if (this.miControlador.getListaPedidosPendientes().size() > 0) {
			for (Pedido pedido : this.miControlador.getListaPedidosPendientes()) {
				System.out.println(pedido.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de pedidos pendientes, ya que no se ha registrado ning�n pedido pendiente en este momento.");
		}
	}

	public void mostrarPedidosPendientesClienteVistaGestionOS() throws SQLException, DAOException {

		String email_cliente_pedido;
		System.out.println("Introducir email del cliente del que se desea obtener la lista de pedidos pendientes:");
		Scanner sn_email_cliente_pedido = new Scanner(System.in);
		email_cliente_pedido = sn_email_cliente_pedido.nextLine();

		boolean existe_cliente = false;

		for (Cliente cliente : this.miControlador.getListaClientes()) {
			if (cliente.getEmail().equals(email_cliente_pedido)) {
				existe_cliente = true;
			}
		}

		if (!existe_cliente) {
			System.out.println(
					"No se ha podido mostrar la lista de pedidos pendientes para este cliente, ya que el email proporcionado no coincide con el de ning�n cliente registrado.");
		} else {
			if (this.miControlador.getListaPedidosPendientesCliente(email_cliente_pedido).size() > 0) {
				for (Pedido pedido : this.miControlador.getListaPedidosPendientesCliente(email_cliente_pedido)) {
					System.out.println(pedido.toString());
				}
			} else {
				System.out.println(
						"No se ha podido mostrar la lista de pedidos pendientes para este cliente, ya que no se ha registrado ning�n pedido pendiente en este momento para el cliente indicado.");
			}
		}
	}

	public void mostrarPedidosEnviadosVistaGestionOS() throws SQLException, DAOException {
		if (this.miControlador.getListaPedidosEnviados().size() > 0) {
			for (Pedido pedido : this.miControlador.getListaPedidosEnviados()) {
				System.out.println(pedido.toString());
			}
		} else {
			System.out.println(
					"No se ha podido mostrar la lista de pedidos enviados, ya que no se ha registrado ning�n pedido enviado hasta el momento.");
		}
	}

	public void mostrarPedidosEnviadosClienteVistaGestionOS() throws SQLException, DAOException {

		String email_cliente_pedido;
		System.out.println("Introducir email del cliente del que se desea obtener la lista de pedidos pendientes:");
		Scanner sn_email_cliente_pedido = new Scanner(System.in);
		email_cliente_pedido = sn_email_cliente_pedido.nextLine();

		boolean existe_cliente = false;

		for (Cliente cliente : this.miControlador.getListaClientes()) {
			if (cliente.getEmail().equals(email_cliente_pedido)) {
				existe_cliente = true;
			}
		}

		if (!existe_cliente) {
			System.out.println(
					"No se ha podido mostrar la lista de pedidos enviados para este cliente, ya que el email proporcionado no coincide con el de ning�n cliente registrado.");
		} else {
			if (this.miControlador.getListaPedidosEnviadosCliente(email_cliente_pedido).size() > 0) {
				for (Pedido pedido : this.miControlador.getListaPedidosEnviadosCliente(email_cliente_pedido)) {
					System.out.println(pedido.toString());
				}
			} else {
				System.out.println(
						"No se ha podido mostrar la lista de pedidos pendientes para este cliente, ya que no se ha registrado ning�n pedido enviado para el cliente indicado.");
			}
		}
	}

}