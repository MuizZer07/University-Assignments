import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) throws Exception { 
		
		Scanner infile = new Scanner(new File("persons.txt"));
		
		while(infile.hasNext()) {
			String line = infile.nextLine(); 
			StringTokenizer tokenizer = new StringTokenizer(line, ";"); 
			
			if(tokenizer.countTokens() == 3) {
				String id = tokenizer.nextToken().trim();
				String name = tokenizer.nextToken().trim();
				String listOfHobbies = tokenizer.nextToken().trim();

				String hobbies = " hobby";
				int totalHobbies = listOfHobbies.split(", ").length;
				if(totalHobbies > 1) {
					hobbies = " hobbies";
				}
				
				System.out.println(name + " (" + id + ") has " + totalHobbies + hobbies) ;	
			}else {
				System.out.println(line);	
			}
					
		}
		infile.close();
	}

}
