package grupofp.excepciones;

import java.sql.SQLException;

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

	public static class InvalidClientTypeException extends Exception {
		public InvalidClientTypeException(String message) {
	        super(message);
	    }
    }

	public static class NotFloatException extends Exception {
		public NotFloatException(String message) {
	        super(message);
	    }
    }

	public static class DAOException extends Exception {
		public DAOException(String message, Exception e) {
	        super(message + e.toString());
	    }
    }

}
