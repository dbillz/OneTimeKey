package exceptions;

public class InvalidParameterException extends OneTimeKeyException {

	public InvalidParameterException(String string) {
		super(string);
		errorCode = 400;
	}

}
