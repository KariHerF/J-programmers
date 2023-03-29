package grupofp.modelo;

public abstract class Cliente {

	/**
	 * @author J-Programers
	 *
	 */
	private String email;
	private String nombre;
	private String domicilio;
	private String nif;

	public Cliente(String email, String nombre, String domicilio, String nif) {
		this.setEmail(email);
		this.setNombre(nombre);
		this.setDomicilio(domicilio);
		this.setNif(nif);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public abstract String tipoCliente();
	
	public abstract float calcAnual();
	
	public abstract float descuentoEnv();

	/**
	 *
	 */
	@Override
	public abstract String toString();
}
