import java.util.Scanner;

public class PatientRecordSystemMenu {

	private static Scanner kb = new Scanner(System.in);
	public static PatientRecordSystem prs;
	
	public static void main(String [] args) throws Exception
   	{
		prs = new PatientRecordSystem();
		char ch = '0';
		
		while(ch != 'X') {			
			try {
				printMenu();
				String input_ = kb.nextLine().trim();
				
				if(input_.length() == 1) {
					ch = input_.charAt(0);

					checkInput(ch);
				}else {
					throw new Exception();
				}

			}catch(Exception e) {
				print("Wrong Input!");
			}
			
		}
		
   	}
	
	private static void printMenu() {
		print("=====================");
		print("Patient Record System");
		print("=====================\n");
		print("1. Add a measurement observation type ");
		print("2. Add a category observation type");
		print("3. Add a patient");
		print("4. Add a measurement observation");
		print("5. Add a category observation");
		print("6. Display details of an observation type");
		print("7. Display a patient record by the patient id");
		print("8. Save data");
		print("9. Load data");
		print("D. Display all data for inspection");
		print("X. Exit");
		print("Please enter an option (1-9 or D or X):");
	}
	
	private static String[] getInputAndProcess() {
		String line =  kb.nextLine();
		return getSplitString(line);
	}
	
	private static String[] getSplitString(String line) {
		line = line.replace(" ", "");
		return line.split(";");
	}
	
	private static void print(String string) {
		System.out.println(string);
	}
	
	private static void checkInput(char ch) throws Exception {
		String[] params;
		
		switch(ch) {
			case '1':
				print("example => code; name; unit");
				params = getInputAndProcess();
				prs.addMeasurementObservationType(params[0], params[1], params[2]);
				break;
			case '2':
				print("example => code; name; category1, category2, category2");
				params = getInputAndProcess();
				String[] categories = getSplitString(params[2]);
				prs.addCategoryObservationType(params[0], params[1], categories);
				break;
			case '3':
				print("example => id; name");
				params = getInputAndProcess();
				prs.addPatient(params[0], params[1]);
				break;
			case '4':
				print("example => student_id; observation_type_code; value");
				params = getInputAndProcess();
				prs.addMeasurementObservation(params[0], params[1], Double.parseDouble(params[2]));
				break;
			case '5':
				print("example => student_id; observation_type_code; value");
				params = getInputAndProcess();
				prs.addCategoryObservation(params[0], params[1], params[2]);
				break;
			case '6':
				print("example => observation_type_code");
				params = getInputAndProcess();
				print(prs.retrieveObservationTypeDetails(params[0]));
				break;
			case '7':
				print("example => student_id");
				params = getInputAndProcess();
				print(prs.retrievePatientDetails(params[0]));
				break;
			case '8':
				prs.saveData();
				print("Data saved successfully");
				break;
			case '9':
				prs.loadData();
				print("Data loaded successfully");
				break;
			case 'D':
				print(prs.toString());
				break;
			case 'X':
				print("Program Ended.");
				break;
			default:
				print("Wrong Input!");
		}
		
	}
}
