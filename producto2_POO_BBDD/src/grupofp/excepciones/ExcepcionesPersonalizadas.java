package grupofp.excepciones;

public class ExcepcionesPersonalizadas {

	public static class InvalidDNIorNIEFormatException extends Exception {
		public InvalidDNIorNIEFormatException(String message) {
	        super(message);
	    }
    }
	
	public static class InvalidEmailFormatException extends Exception {
		public InvalidEmailFormatException(String message) {
	        super(message);
	    }
    }
	
	public static class InvalidEmpyArgumentException extends Exception {
		public InvalidEmpyArgumentException(String message) {
	        super(message);
	    }
    }
	

}
