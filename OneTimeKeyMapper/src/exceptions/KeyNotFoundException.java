package exceptions;

public class KeyNotFoundException extends OneTimeKeyException {

	public KeyNotFoundException(String message) {
		super(message);
		errorCode = 404;
	}
}
