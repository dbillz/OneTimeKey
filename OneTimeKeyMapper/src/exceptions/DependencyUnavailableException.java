package exceptions;

public class DependencyUnavailableException extends OneTimeKeyException {

	public DependencyUnavailableException(String string) {
		super(string);
		errorCode = 500;
	}

}
