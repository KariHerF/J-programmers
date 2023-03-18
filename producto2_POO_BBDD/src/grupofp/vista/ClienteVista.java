/**
 * 
 */
package grupofp.vista;

/**
 * @author josem
 *
 */
public class ClienteVista {

	/**
	 * 
	 */
	public void printClienteDetalles(String nombre, String domicilio, String nif, String email) {
		System.out.println("**** DATOS CLIENTE ****");
		System.out.println("Id: " + nombre);
		System.out.println("Nombre: " + domicilio);
		System.out.println("Apellido: " + nif);
		System.out.println("Apellido: " + email);
	}

}
