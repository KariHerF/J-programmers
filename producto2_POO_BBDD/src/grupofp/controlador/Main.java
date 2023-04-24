package grupofp.controlador;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import grupofp.controlador.ConexionDB;
import grupofp.controlador.CreateDB;
import grupofp.controlador.CreateTables;

import grupofp.controlador.Controlador;
import grupofp.modelo.Articulo;
import grupofp.modelo.Datos;
import grupofp.vista.GestionOS;
import java.util.InputMismatchException;

/**
 * @author J-Programers
 *
 */
public class Main {

	Datos misDatos;
	Controlador miControlador;
	GestionOS miVistaGestionOS;
	ConexionDB conexionDB;
	CreateTables createTables;

	public static void main(String[] args) throws Exception {
		Main miPrincipal = new Main();
		miPrincipal.iniciarApp();
		miPrincipal.MenuApp();
		CreateDB createDB = new CreateDB();
		CreateTables createTables = new CreateTables();
		/*
		 * Aqui falta inicializar la clase createDB y la CreateTable (CREO) poque sino
		 * hacerlo separado no tiene sentido
		 */

	}

	/**
	 * Permite instanciar todas las clases que necesitamos para el funcionamiento de
	 * nuestra aplicacion
	 */
	private void iniciarApp() {
		/* Se instancian las clases del MVC */
		miVistaGestionOS = new GestionOS();
		misDatos = new Datos();
		miControlador = new Controlador(miVistaGestionOS, misDatos);
		miVistaGestionOS.setControlador(miControlador);
		misDatos.setControlador(miControlador);
	}

	private void MenuApp() {
		// Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		while (!salir) { // hacemos un bucle para que nos salgan las opciones cada vez a excepcion de que
							// pongamos la opcion de salir
			Scanner sn = new Scanner(System.in);
			System.out.println("");
			System.out.println("");
			System.out.println("============================   MEN� PRINCIPAL   ============================");
			System.out.println("Introduce un n�mero entero entre 1 y 4 para seleccionar una de las opciones:");
			System.out.println("1. Gestion de Articulos");
			System.out.println("2. Gestion de Clientes");
			System.out.println("3. Gestion de Pedidos");
			System.out.println("4. Cerrar aplicaci�n");

			try {
				opcion = sn.nextInt();
				boolean salir_submenu = false;
				switch (opcion) {
					case 1:
						System.out.println("");
						System.out.println("GESTION DE ARTICULOS:");
						System.out.println("1. A�adir Articulo");
						System.out.println("2. Mostrar Articulos");
						System.out.println("3. Salir a men� principal");
						Scanner sn_submenu_articulos = new Scanner(System.in); // introducimos el teclado
						while (!salir_submenu) {
							opcion = sn_submenu_articulos.nextInt();
							switch (opcion) {
								case 1:
									miVistaGestionOS.anadirArticuloVistaGestionOS();
									break;
								case 2:
									miVistaGestionOS.mostrarArticulos();
									break;
								case 3:
									salir_submenu = true;
									break;
							}
							System.out.println("");
							System.out.println("GESTION DE ARTICULOS:");
							System.out.println("1. A�adir Articulo");
							System.out.println("2. Mostrar Articulos");
							System.out.println("3. Salir a menu principal");
						}
						break;
					case 2:
						System.out.println("");
						System.out.println("GESTION DE CLIENTES");
						System.out.println("1. A�adir Cliente");
						System.out.println("2. Mostrar Clientes");
						System.out.println("3. Mostrar Clientes Est�ndar");
						System.out.println("4. Mostrar Clientes Premium");
						System.out.println("5. Salir a men� principal");
						Scanner sn_submenu_clientes = new Scanner(System.in); // introducimos el teclado
						while (!salir_submenu) {
							opcion = sn_submenu_clientes.nextInt();
							switch (opcion) {
								case 1:
									miVistaGestionOS.anadirClienteVistaGestionOS();
									break;
								case 2:
									miVistaGestionOS.mostrarClientes();
									break;
								case 3:
									miVistaGestionOS.mostrarClientesEstandar();
									;
									break;
								case 4:
									miVistaGestionOS.mostrarClientesPremium();
									break;
								case 5:
									salir_submenu = true;
									break;
							}
							System.out.println("");
							System.out.println("GESTION DE CLIENTES");
							System.out.println("1. A�adir Cliente");
							System.out.println("2. Mostrar Clientes");
							System.out.println("3. Mostrar Clientes Est�ndar");
							System.out.println("4. Mostrar Clientes Premium");
							System.out.println("5. Salir a men� principal");
							;
						}
						break;
					case 3:
						System.out.println("");
						System.out.println("GESTION DE PEDIDOS");
						System.out.println("1. A�adir Pedido");
						System.out.println("2. Eliminar Pedido");
						System.out.println("3. Mostrar Pedidos pendientes");
						System.out.println("4. Mostrar Pedidos enviados");
						System.out.println("5. Salir a men� principal");
						Scanner sn_submenu_pedidos = new Scanner(System.in); // introducimos el teclado
						while (!salir_submenu) {
							opcion = sn_submenu_pedidos.nextInt();
							switch (opcion) {
								case 1:
									miVistaGestionOS.anadirPedidoVistaGestionOS();
									break;
								case 2:
									miVistaGestionOS.eliminarPedidoVistaGestionOS();
									break;
								case 3:
									boolean salir_submenu_pedidos_mostrar_pendientes = false;
									System.out.println("");
									System.out.println("GESTION DE PEDIDOS - [Submenu Mostrar Pedidos pendientes]");
									System.out.println("1. Motrar pedidos pendientes");
									System.out.println("2. Mostrar pedidos pendientes por cliente");
									System.out.println("3. Salir a men� anterior -  [GESTION DE PEDIDOS]");
									Scanner sn_submenu_pedidos_mostrar_pendientes = new Scanner(System.in); // introducimos
																											// el
																											// teclado
									while (!salir_submenu_pedidos_mostrar_pendientes) {
										opcion = sn_submenu_pedidos_mostrar_pendientes.nextInt();
										switch (opcion) {
											case 1:
												miVistaGestionOS.mostrarPedidosPendientesVistaGestionOS();
												break;
											case 2:
												miVistaGestionOS.mostrarPedidosPendientesClienteVistaGestionOS();
												break;
											case 3:
												salir_submenu_pedidos_mostrar_pendientes = true;
												break;
										}
										System.out.println("");
										System.out.println("GESTION DE PEDIDOS - [Submenu Mostrar Pedidos pendientes]");
										System.out.println("1. Motrar pedidos pendientes");
										System.out.println("2. Mostrar pedidos pendientes por cliente");
										System.out.println("3. Salir a men� anterior -  [GESTION DE PEDIDOS]");
									}
									break;
								case 4:
									boolean salir_submenu_pedidos_mostrar_enviados = false;
									System.out.println("");
									System.out.println("GESTION DE PEDIDOS - [Submenu Mostrar Pedidos enviados]");
									System.out.println("1. Motrar pedidos enviados");
									System.out.println("2. Mostrar pedidos enviados por cliente");
									System.out.println("3. Salir a men� anterior -  [GESTION DE PEDIDOS]");
									Scanner sn_submenu_pedidos_mostrar_enviados = new Scanner(System.in); // introducimos
																											// el
																											// teclado
									while (!salir_submenu_pedidos_mostrar_enviados) {
										opcion = sn_submenu_pedidos_mostrar_enviados.nextInt();
										switch (opcion) {
											case 1:
												miVistaGestionOS.mostrarPedidosEnviadosVistaGestionOS();
												break;
											case 2:
												miVistaGestionOS.mostrarPedidosEnviadosClienteVistaGestionOS();
												break;
											case 3:
												salir_submenu_pedidos_mostrar_enviados = true;
												break;
										}
										System.out.println("");
										System.out.println("GESTION DE PEDIDOS - [Submenu Mostrar Pedidos enviados]");
										System.out.println("1. Motrar pedidos enviados");
										System.out.println("2. Mostrar pedidos enviados por cliente");
										System.out.println("3. Salir a men� anterior -  [GESTION DE PEDIDOS]");
									}
									break;
								case 5:
									salir_submenu = true;
									break;
							}
							System.out.println("");
							System.out.println("GESTION DE PEDIDOS");
							System.out.println("1. A�adir Pedido");
							System.out.println("2. Eliminar Pedido");
							System.out.println("3. Mostrar Pedidos pendientes");
							System.out.println("4. Mostrar Pedidos enviados");
							System.out.println("5. Salir a men� principal");
						}
						break;
					case 4:
						System.out.println("Saliendo de la aplicaci�n...");
						salir = true;
						break;
					default:
						System.out.println(
								"Para poder acceder a las opciones del men� solo est� permitido introducir n�meros enteros entre 1 y 4");
				}
			} catch (InputMismatchException error) { // en caso de error
				System.out.println("Debes de indicar alguna opci�n del men�.");
				sn.next();
			}
		}
	}
}
