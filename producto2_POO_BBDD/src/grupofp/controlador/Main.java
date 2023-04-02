package grupofp.controlador;
import java.util.Scanner;

import grupofp.modelo.Articulo;

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
            	 System.out.println("Introduce un codigo para el artículo:");
            	 
            
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
            /*}*/
            /*catch(InputMismatchException error){*/ //en caso de error
            	System.out.println("Debes selecionar alguna opcion.");
            	sn.next();
            /*}*/
	}
          
    }
}
