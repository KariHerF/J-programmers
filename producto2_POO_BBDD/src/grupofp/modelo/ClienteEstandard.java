package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClienteEstandard extends Cliente {

    @Override
    public String toString() {
    	return "Cliente Estandar [domicilio=" + super.getDomicilio() + ", email=" + super.getEmail() + ", nif=" + super.getNif() + ", nombre=" + super.getNombre() + "]";
    }

	@Override
	public String tipoCliente() {
		return "Estandard";
	}

	@Override
	public float calcAnual() {
		return 0;
	}

	@Override
	public float descuentoEnv() {
		return 0;
	}
}
