package exceptions;

public class ForbiddenException extends OneTimeKeyException {

	public ForbiddenException(String string) {
		super(string);
		errorCode = 403;
	}

}
