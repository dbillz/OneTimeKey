package module;

import java.util.UUID;

public class KeyGenerator {
	
	public String generateNewKey(String userId) {
		return UUID.randomUUID().toString();
	}
	
}
