/**
 * 
 */
package grupofp.vista;

import java.time.Duration;

/**
 * @author josem
 *
 */
public class ArticuloVista {

	/**
	 * 
	 */
	public void printArticuloDetalles(String codigo, String descripcion, Float pvp, Float gastosEnvio,
			Duration tiempoPrep) {
		System.out.println("**** DATOS ARTICULO ****");
		System.out.println("Código: " + codigo);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Pvp: " + pvp);
		System.out.println("Gastos de envío: " + gastosEnvio);
		System.out.println("Tiempo de preparación: " + tiempoPrep);

	}

}
