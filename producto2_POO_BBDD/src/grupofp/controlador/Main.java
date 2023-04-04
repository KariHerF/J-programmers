package grupofp.controlador;

import java.util.Scanner;

import grupofp.controlador.Controlador;
import grupofp.modelo.Articulo;
import grupofp.modelo.Datos;
import grupofp.vista.GestionOS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	 * Permite instanciar todas las clases que necesitamos para
	 * el funcionamiento de nuestra aplicacion
	 */
	private void iniciarApp() {
		/*Se instancian las clases del MVC*/
		miVistaGestionOS = new GestionOS();
		misDatos = new Datos();
		miControlador = new Controlador(miVistaGestionOS, misDatos);
		miVistaGestionOS.setControlador(miControlador);
	}
	
	private void MenuApp() {
		Scanner sn= new Scanner(System.in);
		boolean salir = false;
    	int opcion;
    	System.out.println("Introduce un numero para seleccionar una de las opciones:");
    	while(!salir) { //hacemos un bucle para que nos salgan las opciones cada vez a ecpecion de que pongamos la opcion de salir
    		
             System.out.println("1. Gestion de Articulos");
             System.out.println("2. Gestion de Pedidos");
             System.out.println("3. Gestion de Clientes");
             System.out.println("4. Salir");
             
           try { //este es un try and catch de intentar una cosa y si no fucniona sale la otra opcion
        	   opcion = sn.nextInt();
               switch(opcion) { //aqui es donde seleccionamos la opcion
				 case 1:
					 System.out.println("Estas en GESTION DE ARTICULOS");
					 System.out.println("1. Crear Articulo");
					 System.out.println("2. Eliminar Articulo");
					 System.out.println("3. Modificar Articulo");
					 System.out.println("4. Listar Articulos");
					 System.out.println("5. Salir");
					 Scanner sn_submenu = new Scanner(System.in); //introducimos el teclado
					 while(!salir) {
						 opcion = sn_submenu.nextInt();
						 switch(opcion) {
				    	 	 case 1:
				    	 		miVistaGestionOS.crearArticuloVistaGestionOS();
				    	 		break;
				    	 	 case 2:
				    	 		break;
				    	 	 case 3:		 
				    	 		break;
				    	 	 case 4:
				    	 		break;
				    	 	 case 5:
				    	 		salir = true;
				    	 		break;
						 }
						 
					 }
					 break;
				 case 2:
					 System.out.println("Estas en GESTION DE PEDIDOS");
					 break;
				 case 3:
					 System.out.println("Estas en GESTION DE CLIENTES");
					 break;
				 case 4:
					 salir = true;
					 break;
				 default:
				     System.out.println("Solo números entre 1 y 4");
               }
             
            }
            catch(InputMismatchException error){ //en caso de error
            	System.out.println("Debes selecionar alguna opcion.");
            	sn.next();
            }
    	}
    }
}
