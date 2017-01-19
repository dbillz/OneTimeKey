package runtime;

import java.util.Scanner;

import module.KeyController;

public class Launcher {
	
	private static final String GENERATE = "gen";
	private static final String CONSUME = "auth";
	private static final String CLEAR = "revoke";

	public static void main(String[] args) {
		KeyController controller = Injector.keyController();
		Scanner sc = new Scanner(System.in);
		printInstructions();
		while (sc.hasNext()) {
			String inputString = sc.nextLine();
			String[] subStrings = inputString.split(" ");
			if (subStrings.length > 1) {
				String userId = subStrings[1];
				if (subStrings[0].equals(GENERATE)) {
					try {
						String newKey = controller.generateNewKey(userId);
						System.out.println("Welcome, " + userId + ". Your one-time-key is " + newKey);
					} catch (Exception e) {};
				}
				if (subStrings[0].equals(CONSUME) && subStrings.length > 2) {
					String key = subStrings[2];
					try {
						String newKey = controller.consumeKey(userId, key);
						System.out.println("Welcome back, " + userId + ". Your new one-time-key is " + newKey);
					} catch (Exception e) {};
				}
				if (subStrings[0].equals(CLEAR)) {
					try {
						controller.clearKey(userId);
						System.out.println("Removed user " + userId);
					} catch (Exception e) {};
				}
			}
			System.out.println("");
		}
	}
			
    private static void printInstructions() {
    	System.out.println("Generate key: gen userId\n"
    					 + "Consume key: auth userId key\n"
    			         + "Clear key: revoke userId\n");
    }
}
