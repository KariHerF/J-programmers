package grupofp.modelo;
/**
 * @author J-Programmers
 *
 */
public class ClienteEstandar extends Cliente {

    public ClienteEstandar(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif); 
    }
    

    @Override
    public String toString() {
    	return "Cliente Est�ndar [email=" + super.getEmail() + ", nombre=" + super.getNombre() +
				", domicilio=" + super.getDomicilio() + ", nif=" + super.getNif() + "]";    
    }

	@Override
	public String tipoCliente() {
		return "Est�ndar";
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
