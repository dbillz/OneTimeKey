package module;

import exceptions.InvalidParameterException;

public class InputValidator {

	public void validateUserId(String userId) throws InvalidParameterException {
		validateStringNotEmptyOrNull(userId);
	}
	
	public void validateKey(String key) throws InvalidParameterException {
		validateStringNotEmptyOrNull(key);
	}
	
	private void validateStringNotEmptyOrNull(String val) throws InvalidParameterException {
		if (val == null || val.isEmpty()) {
			throw new InvalidParameterException("Invalid parameter.");
		}
	}
}
