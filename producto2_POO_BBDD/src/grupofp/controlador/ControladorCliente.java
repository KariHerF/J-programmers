/**
 * 
 */
package grupofp.controlador;

import grupofp.modelo.Cliente;
import grupofp.vista.ClienteVista;

/**
 * @author josem
 *
 */
public class ControladorCliente {
	
	private ClienteVista vista;
	private Cliente modelo;
	
	/**
	 * @param vista
	 * @param modelo
	 */
	public ControladorCliente(ClienteVista vista, Cliente modelo) {
		super();
		this.vista = vista;
		this.modelo = modelo;
	}

	//getters y setters para actuar sobre el modelo
	public void setNombre(String nombre) {
		this.modelo.setNombre(nombre);
	}
	public String getNombre() {
		return this.modelo.getNombre();
	}
	
	public void setDomicilio(String domicilio) {
		this.modelo.setDomicilio(domicilio);
	}
	public String getDomicilio() {
		return this.modelo.getDomicilio();
	}
	
	public void setNif(String nif) {
		this.modelo.setNif(nif);
	}
	public String getNif() {
		return this.modelo.getNif();
	}
	
	public void setEmail(String email) {
		this.modelo.setEmail(email);
	}
	public String getEmail() {
		return this.modelo.getEmail();
	}
	
	//pasa el modelo a la vista para presentar los datos
	public void actualizarVista() {
		vista.printClienteDetalles(modelo.getNombre(),modelo.getDomicilio(), modelo.getNif(), modelo.getEmail());
	}
		
}
