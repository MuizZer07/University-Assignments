
public class T7Tester {
	private static int totalPassed = 0;
	private static int totalTests = 0;
	
	public static void main(String [] args) throws Exception
	{
     	String flagString;
		boolean flag;
     	String fileName = "oval.txt";

     	// TEST: Create and initialize the window
		Window w = new Window(20, 30, '*');
		flagString = testResult(w.isInitialized);
		PrintEachTest(totalTests, "WINDOW INITIALIZED", flagString);
		System.out.println("TEST CASE " + totalTests + " : WINDOW INITIALIZED - " + flagString);
		totalTests++;
		
		// TEST: Draw an Oval
		Oval oval = new Oval(6, 8, 2, 6, '*');
		flag = w.addShape(oval);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "ADD AN OVAL (6, 8, 2, 6, '*')", flagString);
		totalTests++;
		
		// TEST: Draw an Oval
		oval = new Oval(8, 22, 2, 3, '*');
		flag = w.addShape(oval);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "DRAW AN OVAL (8, 22, 2, 3, '*')", flagString);
		totalTests++;
		
		// TEST: Draw an Oval
		oval = new Oval(15, 6, 2, 4, '*');
		flag = w.addShape(oval);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "DRAW AN OVAL (15, 6, 2, 4, '*')", flagString);
		totalTests++;
		
		// TEST: Draw an Oval
		oval = new Oval(15, 19, 3, 6, '*');
		flag = w.addShape(oval);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "DRAW AN OVAL (15, 19, 3, 6, '*')", flagString);
		totalTests++;
		
		// TEST: Erase an Oval
		flag = w.eraseShape(3);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "ERASE AN OVAL FROM INDEX 3", flagString);   	
		totalTests++;
		
		// TEST: Increase Size of an oval
		flag = w.increaseShapeSize(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "INCREASE SIZE OF OVAL INDEX 2", flagString);     	
		totalTests++;
		
		// TEST: Decrease Size of an oval
		flag = w.decreaseShapeSize(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "DECREASE SIZE OF OVAL INDEX 2", flagString);     	
		totalTests++;
		
		// TEST: Move Up
		flag = w.moveShapeUp(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "MOVE UP OVAL INDEX 2", flagString);      	
		totalTests++;
		
		// TEST: Move Down
		flag = w.moveShapeDown(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "MOVE Down OVAL INDEX 2", flagString);      	
		totalTests++;
		
		// TEST: Move Right
		flag = w.moveShapeRight(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "MOVE RIGHT OVAL INDEX 2", flagString);      	
		totalTests++;
		
		// TEST: Move Right
		flag = w.moveShapeLeft(2);
		flagString = testResult(flag);
		PrintEachTest(totalTests, "MOVE LEFT OVAL INDEX 2", flagString);      	
		totalTests++;
		
		// TEST: Write to a file
		w.writeSpecToFile(fileName);
		flagString = testResult(w.isInitialized);
		PrintEachTest(totalTests, "WRITE TO FILE" + fileName, flagString);       	
		totalTests++;
		
     	w = w.readSpecFromFile(fileName);
		flagString = testResult(w.isInitialized);
		PrintEachTest(totalTests, "READ FROM FILE" + fileName, flagString);     	
		totalTests++;
		
		flag = w.display();
		flagString = testResult(flag);
		PrintEachTest(totalTests, "DISPLAY WINDOW", flagString);
		totalTests++;
		
		System.out.println("\n\n---------------------------\nTOTAL TEST CASES: " + totalTests);
		System.out.println("TOTAL PASSED: " + totalPassed);
		System.out.println("TOTAL FAILED: " + (totalTests-totalPassed));
	}
	
	private static void PrintEachTest(int testNumber, String testName, String testResult) {
		System.out.println("TEST CASE " + testNumber + " : " + testName + " - " + testResult);
	}
	
	private static String testResult(boolean flag) {
		String result = "FAILED.";
		if(flag){
			result = "PASSED.";
			totalPassed++;
		}
		
		return result;
	}
}
