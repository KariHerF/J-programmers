package grupofp.controlador;

import java.util.Scanner;

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

	public static void main(String[] args) throws Exception {
		Main miPrincipal = new Main();
		miPrincipal.iniciarApp();
		miPrincipal.MenuApp();
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
	}

	private void MenuApp() {
		//Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		while (!salir) { // hacemos un bucle para que nos salgan las opciones cada vez a ecpecion de que
							// pongamos la opcion de salir
			Scanner sn = new Scanner(System.in);
			System.out.println("============================   MENÚ PRINCIPAL   ============================");
			System.out.println("Introduce un número entero entre 1 y 4 para seleccionar una de las opciones:");
			System.out.println("1. Gestion de Articulos");
			System.out.println("2. Gestion de Pedidos");
			System.out.println("3. Gestion de Clientes");
			System.out.println("4. Cerrar aplicación");

			try { // este es un try and catch de intentar una cosa y si no fucniona sale la otra
					// opcion
				opcion = sn.nextInt();
				boolean salir_submenu = false;
				switch (opcion) { // aqui es donde seleccionamos la opcion
				case 1:
					System.out.println("");
					System.out.println("GESTION DE ARTICULOS:");
					System.out.println("1. Añadir Articulo");
					System.out.println("2. Mostrar Articulos");
					System.out.println("3. Salir a menú principal");
					Scanner sn_submenu = new Scanner(System.in); // introducimos el teclado
					while (!salir_submenu) {
						opcion = sn_submenu.nextInt();
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
						System.out.println("1. Añadir Articulo");
						System.out.println("2. Mostrar Articulos");
						System.out.println("3. Salir a menu principal");
					}
					break;
				case 2:
					System.out.println("");
					System.out.println("GESTION DE PEDIDOS");
					break;
				case 3:
					System.out.println("");
					System.out.println("GESTION DE CLIENTES");
					break;
				case 4:
					System.out.println("Saliendo de la aplicación...");
					salir = true;
					break;
				default:
					System.out.println("Para poder acceder a las opciones del menú solo está permitido introducir números enteros entre 1 y 4");
				}
			} catch (InputMismatchException error) { // en caso de error
				System.out.println("Debes de indicar alguna opción del menú.");
				sn.next();
			}
		}
	}
}
