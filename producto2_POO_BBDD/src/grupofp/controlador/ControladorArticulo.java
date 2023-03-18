/**
 * 
 */
package grupofp.controlador;

import java.time.Duration;

import grupofp.modelo.Articulo;
import grupofp.vista.ArticuloVista;

/**
 * @author josem
 *
 */
public class ControladorArticulo {

	private ArticuloVista vista;
	private Articulo modelo;

	/**
	 * @param vista
	 * @param modelo
	 */
	public ControladorArticulo(ArticuloVista vista, Articulo modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	// getters y setters para actuar sobre el modelo
	public void setCodigo(String codigo) {
		this.modelo.setCodigo(codigo);
	}

	public String getCodigo() {
		return this.modelo.getCodigo();
	}

	public void setDescripcion(String descripciion) {
		this.modelo.setDescripcion(descripciion);
	}

	public String getDescripcion() {
		return this.modelo.getDescripcion();
	}

	public void setPvp(Float pvp) {
		this.modelo.setPvp(pvp);
		;
	}

	public Float getPvp() {
		return this.modelo.getPvp();
	}

	public void setGastosEnvio(Float gastosEnvio) {
		this.modelo.setGastosEnvio(gastosEnvio);
	}

	public Float getGastosEnvio() {
		return this.modelo.getGastosEnvio();
	}

	public void setTiempoPrep(Duration tiempoPrep) {
		this.modelo.setTiempoPrep(null);
	}

	public Duration getTiempoPrep() {
		return this.modelo.getTiempoPrep();
	}

	// pasa el modelo a la vista para presentar los datos
	public void actualizarVista() {
		vista.printArticuloDetalles(modelo.getCodigo(), modelo.getDescripcion(), modelo.getPvp(), modelo.getGastosEnvio(), modelo.getTiempoPrep());
	}

}
