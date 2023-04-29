package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClientePremium extends Cliente {
	//Constantes para guardar la cuota anual y el descuento de gastos de envío de un cliente premium
	private float cuotaAnual;
	private float dtoGtoEnvio;
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
