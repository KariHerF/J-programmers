package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClientePremium extends Cliente {

	private Float cuotaAnual;
	private Float dtoGtoEnvio;

	/**
	 * @param email
	 * @param nombre
	 * @param domicilio
	 * @param nif
	 * @param cuotaAnual
	 * @param dtoGtoEnvio
	 */
	public ClientePremium(String email, String nombre, String domicilio, String nif, Float cuotaAnual, Float dtoGtoEnvio) {
		super(email, nombre, domicilio, nif);
		this.cuotaAnual = cuotaAnual;
		this.dtoGtoEnvio = dtoGtoEnvio;
	}

	/**
	 * @return the cuotaAnual
	 */
	public Float getCuotaAnual() {
		return cuotaAnual;
	}

	/**
	 * @param cuotaAnual the cuotaAnual to set
	 */
	public void setCuotaAnual(Float cuotaAnual) {
		this.cuotaAnual = cuotaAnual;
	}

	/**
	 * @return the dtoGtoEnvio
	 */
	public Float getDtoGtoEnvio() {
		return dtoGtoEnvio;
	}

	/**
	 * @param dtoGtoEnvio the dtoGtoEnvio to set
	 */
	public void setDtoGtoEnvio(Float dtoGtoEnvio) {
		this.dtoGtoEnvio = dtoGtoEnvio;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "Cliente Estandar [domicilio=" + super.getDomicilio() + ", email=" + super.getEmail() + ", nif="
				+ super.getNif() + ", nombre=" + super.getNombre() + ", cuotaAnual=" + this.getCuotaAnual()
				+ "dtoGtoEnvio=" + this.getDtoGtoEnvio() + "]";
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
