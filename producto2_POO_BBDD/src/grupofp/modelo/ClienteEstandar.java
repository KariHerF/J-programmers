package grupofp.modelo;
/**
 * @author J-Programers
 *
 */
public class ClienteEstandar extends Cliente {

    public ClienteEstandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif); 
    }
    

    @Override
    public String toString() {
    	return "Cliente Estándar [email=" + super.getEmail() + ", nombre=" + super.getNombre() +
				", domicilio=" + super.getDomicilio() + ", nif=" + super.getNif() + "]";    
    }

	@Override
	public String tipoCliente() {
		return "Estándar";
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
