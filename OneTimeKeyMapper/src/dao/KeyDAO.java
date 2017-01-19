package dao;

import exceptions.DependencyUnavailableException;
import exceptions.InvalidParameterException;
import exceptions.KeyNotFoundException;

public interface KeyDAO {
	
	public String getKey(String userId) throws KeyNotFoundException, DependencyUnavailableException, InvalidParameterException;
	
	public void setKey(String userId, String key) throws DependencyUnavailableException, InvalidParameterException;
	
	public void clearKey(String userId) throws DependencyUnavailableException, InvalidParameterException;
	
}
