package grupofp.modelo;

import java.time.Duration;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * @author J-Programers
 *
 */

@Entity
@Table (name = "articulos")
public class Articulo {

    @Id
    @Column(name = "codigo_articulo")
    public String codigo;
    @Column(name = "descripcion")
    public String descripcion;
    @Column(name = "precio_venta")
    public float pvp;
    @Column(name = "gastos_envio")
    public float gastosEnvio;
    @Column(name = "tiempo_preparacion")
    public long tiempo_preparacion;
    

	/**
	 * @param codigo
	 * @param descripcion
	 * @param pvp
	 * @param tiempo_preparacion
	 * @param gastosEnvio
	 */
    public Articulo() {
    	   // default constructor implementation
    }
    
	public Articulo(String codigo, String descripcion, float pvp, long tiempo_preparacion, float gastosEnvio) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.tiempo_preparacion = tiempo_preparacion;
		this.gastosEnvio = gastosEnvio;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the pvp
	 */
	public float getPvp() {
		return pvp;
	}

	/**
	 * @param pvp the pvp to set
	 */
	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	/**
	 * @return the tiempoPrep
	 */
	public long getTiempoPrep() {
		return tiempo_preparacion;
	}

	/**
	 * @param tiempoPrep the tiempoPrep to set
	 */
	public void setTiempoPrep(long tiempoPrep) {
		this.tiempo_preparacion = tiempoPrep;
	}

	/**
	 * @return the gastosEnvio
	 */
	public float getGastosEnvio() {
		return gastosEnvio;
	}

	/**
	 * @param gastosEnvio the gastosEnvio to set
	 */
	public void setGastosEnvio(float gastosEnvio) {
		this.gastosEnvio = gastosEnvio;
	}

	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", pvp=" + pvp + ", tiempoPrep="
				+ tiempo_preparacion + ", gastosEnvio=" + gastosEnvio + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
