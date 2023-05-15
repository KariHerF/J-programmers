package grupofp.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * @author J-Programers
 *
 */

@Entity
@DiscriminatorValue("2")
//@SecondaryTable(name = "tipos_cliente", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_tipo_cliente"))
public class ClientePremium extends Cliente {
	
	//Constantes para guardar la cuota anual y el descuento de gastos de envío de un cliente premium
	//@Column(name = "cuota_anual", table = "tipos_cliente")
	@Transient
	private float cuotaAnual;
	//@Column(name = "descuento_envio", table = "tipos_cliente")
	@Transient
	private float dtoGtoEnvio;
	//@Column(name = "tipo_cliente")
	@Transient
	private String tipoCliente;


	/**
	 * @param email
	 * @param nombre
	 * @param domicilio
	 * @param nif
	 */
	public ClientePremium(String email, String nombre, String domicilio, String nif, String tipoCliente, float cuotaAnual, float dtoGtoEnvio) {
		super(email, nombre, domicilio, nif);
		this.cuotaAnual = cuotaAnual;
        this.dtoGtoEnvio = dtoGtoEnvio;
        this.tipoCliente = tipoCliente;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Cliente Premium [email=" + super.getEmail() + ", nombre=" + super.getNombre() +
				", domicilio=" + super.getDomicilio() + ", nif=" + super.getNif() + ", cuotaAnual=" + this.cuotaAnual
				+ ", dtoGtoEnvio=" + this.dtoGtoEnvio + "]";
	}

	@Override
	public String tipoCliente() {
		return this.tipoCliente;
	}

	@Override
	public float calcAnual() {
		return this.cuotaAnual;
	}

	@Override
	public float descuentoEnv() {
		return this.dtoGtoEnvio;
	}
}
