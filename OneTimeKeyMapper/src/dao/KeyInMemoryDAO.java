package dao;

import java.util.HashMap;
import java.util.Map;

import exceptions.DependencyUnavailableException;
import exceptions.InvalidParameterException;
import exceptions.KeyNotFoundException;
import module.InputValidator;
import module.Logger;

public class KeyInMemoryDAO implements KeyDAO {

	private Map<String, String> valueMap;
	private InputValidator inputValidator;
	private Logger logger;

	public KeyInMemoryDAO(final InputValidator inputValidator, final Logger logger) {
		this.inputValidator = inputValidator;
		this.logger = logger;
		valueMap = new HashMap<>();
	}

	@Override
	public String getKey(final String userId)
			throws KeyNotFoundException, DependencyUnavailableException, InvalidParameterException {

		try {
			inputValidator.validateUserId(userId);

			String key = valueMap.get(userId);
			if (key == null) {
				throw new KeyNotFoundException("Key not found for user " + userId);
			}
			return key;
		} catch (KeyNotFoundException | InvalidParameterException thrownException) {
			logger.logError(thrownException.getClass().getCanonicalName() + " thrown for getKey for user " + userId);
			throw thrownException;
		} catch (Exception unexpectedException) {
			final String className = unexpectedException.getClass().getCanonicalName();
			logger.logFault(className + " thrown for getKey for user " + userId);
			throw new DependencyUnavailableException("KeyInMemoryDAO failed to call getKeyHash for user " + userId
					+ " due to exception " + className);
		}
	}

	@Override
	public void setKey(final String userId, final String key)
			throws DependencyUnavailableException, InvalidParameterException {
		try {
			inputValidator.validateKey(key);
			inputValidator.validateKey(userId);

			valueMap.put(userId, key);
		} catch (InvalidParameterException thrownException) {
			logger.logError(thrownException.getClass().getCanonicalName() + " thrown for setKey for user " + userId);
			throw thrownException;
		} catch (Exception unexpectedException) {
			final String className = unexpectedException.getClass().getCanonicalName();
			logger.logFault(className + " thrown for getKey for user " + userId);
			throw new DependencyUnavailableException("KeyInMemoryDAO failed to call setKey for user " + userId
					+ " due to exception " + className);
		}

	}

	@Override
	public void clearKey(String userId) throws DependencyUnavailableException, InvalidParameterException {
		// TODO Auto-generated method stub
		try {
			inputValidator.validateUserId(userId);
			
			valueMap.remove(userId);
		} catch (InvalidParameterException thrownException) {
			logger.logError(thrownException.getClass().getCanonicalName() + " thrown for clearKey for user " + userId);
			throw thrownException;
		} catch (Exception unexpectedException) {
			final String className = unexpectedException.getClass().getCanonicalName();
			logger.logFault(className + " thrown for getKey for user " + userId);
			throw new DependencyUnavailableException("KeyInMemoryDAO failed to call clearKey for user " + userId
					+ " due to exception " + className);
		}
	}

}
