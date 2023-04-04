package grupofp.controlador;

import java.util.Scanner;

import grupofp.controlador.Controlador;
import grupofp.modelo.Articulo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            		 Articulo art1 = new Articulo(null, null, opcion, null, opcion);
            		 switch(opcion) {
	            	 	 case 1:
	            	 		 
	            	 		 System.out.println("Introducir nombre del art�culo:\n");
	            	 		 Scanner sn_codigo_art = new Scanner(System.in);
	            	 		 art1.setCodigo(sn_codigo_art.nextLine());
	            	 		 System.out.println("Introducir descripci�n del art�culo:\n");
	            	 		 Scanner sn_descripcion_art = new Scanner(System.in);
	            	 		 art1.setDescripcion(sn_descripcion_art.nextLine());
	            	 		System.out.println("Introducir gastos env�o del art�culo:\n");
	            	 		 Scanner sn_gastos_art = new Scanner(System.in);
	            	 		 art1.setGastosEnvio(sn_gastos_art.nextFloat());
	            	 		 System.out.println("Introducir pvp del art�culo:\n");
	            	 		 Scanner sn_pvp_art = new Scanner(System.in);
	            	 		 art1.setPvp(sn_pvp_art.nextFloat());
	            	 		System.out.println("Introducir el tiempo de preparaci�n del art�culo. IMPORTANTE con este formato: yyyy-MM-dd HH:mm:ss \n");
	            	 		Scanner sn_tiempo_prep_art = new Scanner(System.in);
	            	 			            	 	
	            	 		LocalDateTime ahora = LocalDateTime.now();
	            	 		String fechaEntregaString = sn_tiempo_prep_art.nextLine();
	            	 		LocalDateTime fechaEntrega = LocalDateTime.parse(fechaEntregaString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	            	 		art1.setTiempoPrep(Duration.between(ahora, fechaEntrega));

	            	     //Hay que trabajar con la localdate time para eso xd!!!!
	            	 		System.out.println(art1);

	            	   System.out.println("Propiedades del artículo:\n");
	            	   System.out.println("Nombre: "+ art1.getCodigo()+"\n");
	            	   System.out.println("Descricpcion: "+ art1.getDescripcion()+"\n");
	            	   System.out.println("Gastos envio: "+ art1.getGastosEnvio()+"\n");
	            	   System.out.println("Precio Venta: "+ art1.getPvp()+"\n");
	            	   System.out.println("Fecha estimada de entrega: "+ art1.getTiempoPrep()+"\n");
	            	   
	            			   
	            			   
	            	   		/*, "\n Descrpcion: %s ", sn_descripcion_art
	            	   		, "\n Gastos de envio: %f", sn_gastos_art
	            	   		, "\n Precio Venta: %f", sn_pvp_art
	            	   		, "\n Fecha estimada de entrega: ", sn_tiempo_prep_art);
	            	 		 */
	            	 		 /*
	            	 		String codigo_articulo;
	            	 	    String descripcion_articulo;
	            	 	    float pvp_articulo;
	            	 	    String tiempoPrep_articulo;
	            	 	    Duration tiempoPrep_articulo_parsed;
	            	 	    float gastosEnvioArticulo;
	            	 		System.out.println("Introducir nombre del art�culo:\n");
	            	 		Scanner sn_codigo_articulo = new Scanner(System.in);
	            	 		codigo_articulo = sn_codigo_articulo.nextLine();
	            	 		
	            	 		System.out.println("Introducir descripci�n del art�culo:\n");
	            	 		Scanner sn_descripcion_articulo = new Scanner(System.in);
	            	 		descripcion_articulo = sn_descripcion_articulo.nextLine();
	            	 		
	            	 		System.out.println("Introducir pvp del art�culo:\n");
	            	 		Scanner sn_pvp_articulo = new Scanner(System.in);
	            	 		pvp_articulo = sn_descripcion_articulo.nextFloat();
	            	 		
	            	 		System.out.println("Introducir el tiempo de preparaci�n del art�culo:\n");
	            	 		System.out.print("(la duraci�n debe introducirse en formato ISO 8601 (PTnHnMnS))\n");
	            	 		Scanner sn_tiempo_prep_articulo = new Scanner(System.in);
	            	 		tiempoPrep_articulo = sn_tiempo_prep_articulo.nextLine();
	            	 		Duration duration = Duration.parse(tiempoPrep_articulo);
	            	 		tiempoPrep_articulo_parsed = duration;
	            	 		
	            	 		System.out.println("Introducir gastos env�o del art�culo:\n");
	            	 		Scanner sn_gastos_envio_articulo = new Scanner(System.in);
	            	 		gastosEnvioArticulo = sn_descripcion_articulo.nextFloat();
	            	 		
	            	 		Articulo nuevo_articulo = new Articulo(codigo_articulo,descripcion_articulo,pvp_articulo,tiempoPrep_articulo_parsed,gastosEnvioArticulo);
	            	 		
	            	 		if (nuevo_articulo != null) {
	            	 			System.out.println("Se ha creado un nuevo art�culo con las siguientes caracter�sticas:\n");
	            	 			nuevo_articulo.toString();
	            	 		}	
	            	 		*/
	            	 		break;
	            	 	 case 2:
	            	 		 if(art1 != null) {
	            	 			art1.setCodigo(null);
	            	 			art1.setDescripcion(null);
	            	 			art1.setGastosEnvio(opcion);
	            	 			art1.setPvp(opcion);
	            	 			art1.setTiempoPrep(null);
	            	 		 }
	            	 		  System.out.println("No puedes aliminar un articulo que no existe");
	            	 		
	            	 		 
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
