package grupofp.controlador;
import java.util.Scanner;

import grupofp.modelo.Articulo;

import java.time.Duration;
import java.util.InputMismatchException;
/**
 * @author J-Programers
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	Scanner sn= new Scanner(System.in); //introducimos el teclado
    	boolean salir = false;
    	int opcion;
    	 System.out.println("Introduce un numero para seleccionar una de las opciones:");
    	while(!salir) { //hacemos un bucle para que nos salgan las opciones cada vez a ecpecion de que pongamos la opcion de salir
    		
             System.out.println("1. Gestion de Articulos");
             System.out.println("2. Gestion de Pedidos");
             System.out.println("3. Gestion de Clientes");
             System.out.println("4. Salir");
             
          /* try { *///este es un try and catch de intentar una cosa y si no fucniona sale la otra opcion
            
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
	            	 		String codigo_articulo;
	            	 	    String descripcion_articulo;
	            	 	    float pvp_articulo;
	            	 	    String tiempoPrep_articulo;
	            	 	    Duration tiempoPrep_articulo_parsed;
	            	 	    float gastosEnvioArticulo;
	            	 		System.out.println("Introducir nombre del artículo:\n");
	            	 		Scanner sn_codigo_articulo = new Scanner(System.in);
	            	 		codigo_articulo = sn_codigo_articulo.nextLine();
	            	 		
	            	 		System.out.println("Introducir descripción del artículo:\n");
	            	 		Scanner sn_descripcion_articulo = new Scanner(System.in);
	            	 		descripcion_articulo = sn_descripcion_articulo.nextLine();
	            	 		
	            	 		System.out.println("Introducir pvp del artículo:\n");
	            	 		Scanner sn_pvp_articulo = new Scanner(System.in);
	            	 		pvp_articulo = sn_descripcion_articulo.nextFloat();
	            	 		
	            	 		System.out.println("Introducir el tiempo de preparación del artículo:\n");
	            	 		System.out.print("(la duración debe introducirse en formato ISO 8601 (PTnHnMnS))\n");
	            	 		Scanner sn_tiempo_prep_articulo = new Scanner(System.in);
	            	 		tiempoPrep_articulo = sn_tiempo_prep_articulo.nextLine();
	            	 		Duration duration = Duration.parse(tiempoPrep_articulo);
	            	 		tiempoPrep_articulo_parsed = duration;
	            	 		
	            	 		System.out.println("Introducir gastos envío del artículo:\n");
	            	 		Scanner sn_gastos_envio_articulo = new Scanner(System.in);
	            	 		gastosEnvioArticulo = sn_descripcion_articulo.nextFloat();
	            	 		
	            	 		Articulo nuevo_articulo = new Articulo(codigo_articulo,descripcion_articulo,pvp_articulo,tiempoPrep_articulo_parsed,gastosEnvioArticulo);
	            	 		
	            	 		if (nuevo_articulo != null) {
	            	 			System.out.println("Se ha creado un nuevo artículo con las siguientes características:\n");
	            	 			nuevo_articulo.toString();
	            	 		}	
	            	 		
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
                 System.out.println("Solo nÃºmeros entre 1 y 4");
             }
            /*}*/
            /*catch(InputMismatchException error){*/ //en caso de error
            	System.out.println("Debes selecionar alguna opcion.");
            	sn.next();
            /*}*/
	}
          
    }
}
