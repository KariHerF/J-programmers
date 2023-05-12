package grupofp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

/**
 * @author J-Programers
 *
 */

@Entity
@Table (name = "tipos_cliente")
@PrimaryKeyJoinColumn(name = "tipo_cliente")
public class ClienteEstandar extends Cliente {
	
	@Column(name = "cuota_anual")
	private float cuotaAnual;
	@Column(name = "descuento_envio")
	private float dtoGtoEnvio;
	@Column(name = "tipo_cliente")
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
