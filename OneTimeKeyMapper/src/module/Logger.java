package module;

public class Logger {

	public void logInfo(String message){
		System.out.println("INFO:" + message);
	}
	
	public void logDebug(String message){
		System.out.println("DEBUG:" + message);
	}
	
	public void logError(String message){
		System.out.println("ERROR:" + message);
	}
	
	public void logFault(String message){
		System.out.println("FAULT:" + message);
	}
}
