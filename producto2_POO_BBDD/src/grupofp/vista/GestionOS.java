/**
 * 
 */
package grupofp.vista;

import java.time.Duration;

/**
 * @author J-Programers
 *
 */
public class GestionOS {


	public void printClienteDetalles(String nombre, String domicilio, String nif, String email) {
		System.out.println("**** DATOS CLIENTE ****");
		System.out.println("Id: " + nombre);
		System.out.println("Nombre: " + domicilio);
		System.out.println("Apellido: " + nif);
		System.out.println("Apellido: " + email);
	}
	
	public void printArticuloDetalles(String codigo, String descripcion, Float pvp, Float gastosEnvio,
			Duration tiempoPrep) {
		System.out.println("**** DATOS ARTICULO ****");
		System.out.println("C�digo: " + codigo);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Pvp: " + pvp);
		System.out.println("Gastos de env�o: " + gastosEnvio);
		System.out.println("Tiempo de preparaci�n: " + tiempoPrep);

	}

}
