package module;

import dao.KeyDAO;
import exceptions.DependencyUnavailableException;
import exceptions.ForbiddenException;
import exceptions.InvalidParameterException;
import exceptions.KeyNotFoundException;

public class KeyController {
	
	private final KeyDAO keyDao;
	private final KeyGenerator keyGenerator;
	private final InputValidator inputValidator;
	private final Logger logger;
	
	public KeyController(final KeyDAO keyDao, final KeyGenerator keyGenerator, final InputValidator inputValidator, final Logger logger){
		this.keyDao = keyDao;
		this.keyGenerator = keyGenerator;
		this.inputValidator = inputValidator;
		this.logger = logger;
	}

	public String consumeKey(final String userId, final String key) 
			throws ForbiddenException, KeyNotFoundException, DependencyUnavailableException, InvalidParameterException {
		
		try {
			inputValidator.validateKey(key);
			inputValidator.validateUserId(userId);
		} catch (InvalidParameterException e) {
			logger.logError("Invalid input passed to consumeKey for userId " + userId);
			throw e;
		}
		
		String storedKey = keyDao.getKey(userId);
		if (!storedKey.equals(key)) {
			logger.logInfo("Incorrect key provided by user " + userId);
			throw new ForbiddenException("Key mismatch");
		}
		
		final String newKey = keyGenerator.generateNewKey(userId);
		
		keyDao.setKey(userId, newKey);
		
		return newKey;
	}
	
	public String generateNewKey(final String userId) throws DependencyUnavailableException, InvalidParameterException {
		
		try {
			inputValidator.validateUserId(userId);
		} catch (InvalidParameterException e) {
			logger.logError("Invalid input passed to consumeKey for userId " + userId);
			throw e;
		}
		
		final String newKey = keyGenerator.generateNewKey(userId);
		
		keyDao.setKey(userId, newKey);
		
		return newKey;
	}
	
	public void clearKey(final String userId) throws DependencyUnavailableException, InvalidParameterException {
		try {
			inputValidator.validateUserId(userId);
		} catch (InvalidParameterException e) {
			logger.logError("Invalid input passed to consumeKey for userId " + userId);
			throw e;
		}
		
		keyDao.clearKey(userId);
	}
}
