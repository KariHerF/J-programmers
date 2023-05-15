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
@DiscriminatorValue("1")
//@SecondaryTable(name = "tipos_cliente", pkJoinColumns = @PrimaryKeyJoinColumn(name = "tipo_cliente"))
public class ClienteEstandar extends Cliente {
	
	//Constantes para guardar la cuota anual y el descuento de gastos de envío de un cliente estandar
	//@Column(name = "cuota_anual", table = "tipos_cliente")
	@Transient
	private float cuotaAnual;
	//@Column(name = "descuento_envio", table = "tipos_cliente")
	@Transient
	private float dtoGtoEnvio;
	//@Column(name = "tipo_cliente")
	@Transient
	private String tipoCliente;

    public ClienteEstandar(String email, String nombre, String domicilio, String nif, String tipoCliente, float cuotaAnual, float dtoGtoEnvio) {
        super(email, nombre, domicilio, nif);
        this.tipoCliente = tipoCliente;
        this.cuotaAnual = cuotaAnual;
        this.dtoGtoEnvio = dtoGtoEnvio;
    }


    @Override
    public String toString() {
    	return "Cliente Estándar [email=" + super.getEmail() + ", nombre=" + super.getNombre() +
				", domicilio=" + super.getDomicilio() + ", nif=" + super.getNif() + "]";
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
