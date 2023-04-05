package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClientePremium extends Cliente {
	//Constantes para guardar la cuota anual y el descuento de gastos de envío de un cliente premium
	private final float cuotaAnual = 30f;
	private final float dtoGtoEnvio = 0.2f;

	/**
	 * @param email
	 * @param nombre
	 * @param domicilio
	 * @param nif
	 */
	public ClientePremium(String email, String nombre, String domicilio, String nif) {
		super(email, nombre, domicilio, nif);
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
		return "Premium";
	}

	@Override
	public float calcAnual() {
		return 30f;
	}

	@Override
	public float descuentoEnv() {
		return 0.2f;
	}
}
