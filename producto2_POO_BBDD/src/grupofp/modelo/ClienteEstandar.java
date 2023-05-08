package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClienteEstandar extends Cliente {

	private float cuotaAnual;
	private float dtoGtoEnvio;
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
