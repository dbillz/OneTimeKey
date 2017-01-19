package runtime;

import dao.KeyDAO;
import dao.KeyInMemoryDAO;
import module.InputValidator;
import module.KeyController;
import module.KeyGenerator;
import module.Logger;

public final class Injector {
	
	//Use actual dependency injection frameworks for this :)
	
	private static KeyGenerator keyGenerator() {
		return new KeyGenerator();
	}
	
	private static Logger logger() {
		return new Logger();
	}
	
	private static InputValidator inputValidator() {
		return new InputValidator();
	}
	
	private static KeyDAO keyDAO() {
		return new KeyInMemoryDAO(inputValidator(), logger());
	}
	
	public static KeyController keyController() {
		return new KeyController(keyDAO(), keyGenerator(), inputValidator(), logger());
	}

}
