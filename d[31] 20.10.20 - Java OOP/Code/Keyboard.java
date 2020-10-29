import java.util.Scanner;

public class Keyboard {

	private static Scanner scanner = new Scanner(System.in);
	
	public String readString() {
		return scanner.nextLine();
	}
}
