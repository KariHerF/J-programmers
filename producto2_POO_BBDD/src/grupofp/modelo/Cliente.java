package grupofp.modelo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorType;


@Entity
@Table (name = "clientes")
//Anotación que nos sirve para indicar que vamos a guardar todas las entidades en una única tabla.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Anotación que nos indica la columna que va a actuar de discriminador entre los tipos de clientes.
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Cliente {

	/**
	 * @author J-Programers
	 *
	 */
	@Id
    @Column(name = "email")
    private String email;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "domicilio")
    private String domicilio;
    
    @Column(name = "nif")
    private String nif;

	public Cliente(String email, String nombre, String domicilio, String nif) {
		this.setEmail(email);
		this.setNombre(nombre);
		this.setDomicilio(domicilio);
		this.setNif(nif);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract String tipoCliente();

	public abstract float calcAnual();

	public abstract float descuentoEnv();

	/**
	 *
	 */
	@Override
	public abstract String toString();

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email);
	}

}
