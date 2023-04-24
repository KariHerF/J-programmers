package grupofp.modelo;

import java.time.Duration;
import java.util.Objects;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Time;



/**
 * @author J-Programmers
 *
 */
public class Articulo {
	private Connection connection;
	 public Articulo(Connection connection) {
	        this.connection = connection;
	    }

    private String codigo;
    private String descripcion;
    private float pvp;
    private Duration tiempoPrep;
    private float gastosEnvio;
    
	/**
	 * @param codigo
	 * @param descripcion
	 * @param pvp
	 * @param tiempoPrep
	 * @param gastosEnvio
	 */
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
	
	/**
	 * Insertar articulos en la database NO SE ESTAN INSERTANDO QUIZAS ES EN EL CONTROLLER??
	 * Cuando terminas de crear el articulo te printa esto quizás desde esto que te hace el print puedes recojer los datos y ponerlos a la database??
	 * Articulo [codigo=123455, descripcion=olaaaaaaaaaaaaaaaaaaaa, pvp=12.4, tiempoPrep=PT1H30M30S, gastosEnvio=2.4]
	 */
	public void insertarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO articulos (codigo, descripcion, pvp, tiempo_prep, gastos_envio) " +
                "VALUES (?, ?, ?, ?, ?)";
        
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, articulo.getCodigo());
        statement.setString(2, articulo.getDescripcion());
        statement.setFloat(3, articulo.getPvp());
        Time tiempoPrep = Time.valueOf(articulo.getTiempoPrep().toHours() + ":00:00");
        statement.setTime(4, tiempoPrep);
        statement.setFloat(5, articulo.getGastosEnvio());
        statement.executeUpdate();
        statement.close();
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(codigo, other.codigo);
	}
    
}
