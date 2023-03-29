package grupofp.modelo;
import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * @author J-Programers
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	Scanner sn= new Scanner(System.in);
    	boolean salir = false;
    	int opcion;
    	
    	while(!salir) {
    		
             System.out.println("1. Gestion de Articulos");
             System.out.println("2. Gestion de Pedidos");
             System.out.println("3. Gestion de Clientes");
             System.out.println("4. Salir");
            try {
             System.out.println("Introduce un numero para seleccionar una de las opciones");
             opcion = sn.nextInt();
             
             switch(opcion) {
             case 1:
            	 System.out.println("Estas en GESTION DE ARTICULOS");
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
            catch(InputMismatchException error){
            	System.out.println("Debes selecionar alguna opcion.");
            	sn.next();
            }
	}
          
    }
}
