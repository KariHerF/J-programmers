package grupofp.modelo;

import java.time.Duration;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author J-Programers
 *
 */

@Entity
@Table(name = "articulosh")
public class Articulo {

    @Id
    public String codigo;
    @Column
    public String descripcion;
    @Column
    public float pvp;
    @Column(columnDefinition = "BIGINT")
    public Duration tiempoPrep;
    @Column(name = "gastos_envio")
    public float gastosEnvio;

	/**
	 * @param codigo
	 * @param descripcion
	 * @param pvp
	 * @param tiempoPrep
	 * @param gastosEnvio
	 */
    public Articulo() {
    	   // default constructor implementation
    }
	public Articulo(String codigo, String descripcion, float pvp, Duration tiempoPrep, float gastosEnvio) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.pvp = pvp;
		this.tiempoPrep = tiempoPrep;
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
	public Duration getTiempoPrep() {
		return tiempoPrep;
	}

	/**
	 * @param tiempoPrep the tiempoPrep to set
	 */
	public void setTiempoPrep(Duration tiempoPrep) {
		this.tiempoPrep = tiempoPrep;
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
				+ tiempoPrep + ", gastosEnvio=" + gastosEnvio + "]";
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
