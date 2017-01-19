package exceptions;

public abstract class OneTimeKeyException extends Exception {
	protected int errorCode;
	
	public OneTimeKeyException(String string) {
		super(string);
		errorCode = 500;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
}

