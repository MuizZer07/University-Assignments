import java.io.*;
import java.util.*;

public class PatientRecordSystem
{
	// collection of observation types and patients
	private ObservationType [] observationTypes;
	private int observationTypeCount;

	private Patient [] patients;
	private int patientCount;

	public PatientRecordSystem()
	{
		observationTypes = new ObservationType[50];
		patients = new Patient[50];
		observationTypeCount = 0;
		patientCount = 0;
	}

	public String toString()
	{
		// return details of the observation types
		String str = "===== PATIENT RECORD SYSTEM =====\n";
		str = str + "OBSERVATION TYPES:\n";

		for(int i = 0; i < observationTypeCount; i++)
		{
			str = str + ">> " + observationTypes[i]+ "\n" ;
		}

		// and details of the patients
		str = str + "\nPATIENTS:\n";
		for(int i = 0; i < patientCount; i++)
		{
			str = str + ">> " + patients[i] + "\n" ;
		}

		return str;
	}

	// Method to add measurement observation types
	public void addMeasurementObservationType(String code, String name, String unit)
	throws Exception
	{
		int index = searchObservationType(code);
		boolean pre = (index == -1);
		if (! pre )
		{
			throw new Exception("ERROR: The measurement observation type already exists");
		}

		observationTypes[observationTypeCount++] =
			new MeasurementObservationType(code, name, unit);
	}

	// Helper method: Search for an observation type
	// Return index if found
	// Return -1 if not
	public int searchObservationType(String code)
	{
		for(int i = 0; i < observationTypeCount; i++)
		{
			if( observationTypes[i].getCode().equals(code))
			{
				return i;
			}
		}
		return -1;
	}

	// Add category observation types
	public void addCategoryObservationType(String code, String name, String [] categories)
	throws Exception
	{
		int index = searchObservationType(code);
		boolean pre = (index == -1);
		if (! pre )
		{
			throw new Exception("ERROR: The category observation type already exists");
		}

		observationTypes[observationTypeCount++]
			= new CategoryObservationType(code, name, categories);
	}

	// Add a new patient
	public void addPatient(String id, String name) throws Exception
	{
		int index = searchPatient(id);
		boolean pre = (index == -1);
		if (! pre )
		{
			throw new Exception("ERROR: The patient already exists");
		}

		patients[patientCount++] = new Patient(id, name);
	}

	// Search for a patient by patient id
	// return index if the patient is found
	// Return -1 if the patient is not found
	public int searchPatient(String id)
	{
		for(int i = 0; i < patientCount; i++)
		{
			if( patients[i].getId().equals(id))
			{
				return i;
			}
		}
		return -1;
	}

	// Add a measurement observation for a patient
	public void addMeasurementObservation(String id, String code, double value)
	throws Exception
	{
		int patientIndex = searchPatient(id);
		boolean pre1 = (patientIndex != -1);
		if (! pre1 )
		{
			throw new Exception("ERROR: The patient does not exist");
		}

		int observationTypeIndex = searchObservationType(code);
		boolean pre2 = (observationTypeIndex != -1);
		if (! pre2 )
		{
			throw new Exception("ERROR: The measurement observation type does not exist");
		}

		// pre3 = 'patient does not have observation of this type'
		// We can try to test it here
		// But we can leave the checking to the addObservation method

		patients[patientIndex++].addObservation(
			new MeasurementObservation(
				(MeasurementObservationType) observationTypes[observationTypeIndex], value));
	}

	// method to add a category observation for a patient
	public void addCategoryObservation(String id, String code, String value)
	throws Exception
	{
		int patientIndex = searchPatient(id);
		boolean pre1 = (patientIndex != -1);
		if (! pre1 )
		{
			throw new Exception("ERROR: The patient does not exist");
		}

		int observationTypeIndex = searchObservationType(code);
		boolean pre2 = (observationTypeIndex != -1);
		if (! pre2 )
		{
			throw new Exception("ERROR: The category observation type does not exist");
		}

		// pre3 = 'patient does not have observation of this type
		// pre4 = 'category value must be one of those of the category observation type'
		// We leave the first check to the constructor of CategoryObservation
		// and the second check to addObservation method

		Patient patient = patients[patientIndex];
		ObservationType observationType = observationTypes[observationTypeIndex];

		patient.addObservation(
			new CategoryObservation((CategoryObservationType) observationType, value));
	}

	// retrieve details of an observation type
	public String retrieveObservationTypeDetails(String code)
	{
		int index = searchObservationType(code);

		if (index == -1)
		{
			return "There is no such observation type";
		}
		else
		{
			return observationTypes[index].toString();
		}
	}

	// retrieve details of a patient
	public String retrievePatientDetails(String id)
	{
		int index = searchPatient(id);

		if (index == -1)
		{
			return "There is no such patient";
		}
		else
		{
			return patients[index].toString();
		}
	}

	public void saveData() throws Exception
	{
		//complete the method to write the data in five
		//text files
		
		//if you want, you can delegate tasks of writing each 
		//text file to individual methods. That way, the code is 
		//easier to manage. It is just a suggestion.
		//you can implement the method in any way you see fit. 
		this.writeObservationTypesTofile();
		this.writePatientsAndObservationsToFile();
	}

	public void loadData() throws Exception 
	{
		//clear existing data first:
		observationTypes = new ObservationType[50];
		patients = new Patient[50];
		observationTypeCount = 0;
		patientCount = 0;

		//now, complete the method to load data from five
		//text files
		
		String[] filenames = { 
							   "MeasurementObservationType", 
							   "CategoryObservationType",
							   "Patient", 
							   "MeasurementObservation", 
							   "CategoryObservation" 
						     };
		
		for(int i=0; i<filenames.length; i++) {
			this.readFromFile(filenames[i]);
		}
	}

	private void readFromFile(String fileName) {
		fileName = "PRS-" + fileName + "s.txt";
		try {
			BufferedReader reader;
			FileReader fileReader = new FileReader(fileName);
			reader = new BufferedReader(fileReader);
			   
		   String line = reader.readLine();
		   int i = 0;
			while (line != null) {
				line = line.replace(" ", "");
				String[] params = line.split(";");
				
				switch(fileName) {
					case "PRS-MeasurementObservationTypes.txt":
						this.addMeasurementObservationType(params[0], params[1], params[2]);
						break;
					case "PRS-CategoryObservationTypes.txt":				
						params[2] = params[2].replace(" ", "");
						String[] categories = params[2].split(",");
						this.addCategoryObservationType(params[0], params[1], categories);
						break;
					case "PRS-Patients.txt":				
						this.addPatient(params[0], params[1]);
						break;
					case "PRS-MeasurementObservations.txt":
						this.addMeasurementObservation(params[0], params[1], Double.parseDouble(params[2]));
						break;
					case "PRS-CategoryObservations.txt":
						this.addCategoryObservation(params[0], params[1], params[2]);
						break;
				}
				
				line = reader.readLine();
			}
			
			reader.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void writePatientsAndObservationsToFile() {
		String str = "";
		String measurementObservationStr = "";
		String categoryObservationStr = "";
		
		for(int i=0; i<this.patientCount; i++) {
			str += this.patients[i].getFileWriteString() + "\n";
			String[] strings = this.patients[i].getObservationsString();
			measurementObservationStr += strings[0];
			categoryObservationStr += strings[1];
		}
		
		this.writeToFile("Patient", str);
		this.writeToFile("MeasurementObservation", measurementObservationStr);
		this.writeToFile("CategoryObservation", categoryObservationStr);
	}
	
	private void writeObservationTypesTofile() {
		String[] observationTypeNames = {"MeasurementObservationType", "CategoryObservationType"};
		
		for(int i=0; i<observationTypeNames.length; i++) {
			String newObservationTypes = this.getObservationByType(observationTypeNames[i]);			
			
			this.writeToFile(observationTypeNames[i], newObservationTypes);
		}
	}
	
	private String getObservationByType(String observationTypeName) {
		String str = "";
		
		for(int i=0; i<this.observationTypeCount; i++) {
			if(this.observationTypes[i].getObservationTypeName().equals(observationTypeName)) {
				str += this.observationTypes[i].getFileString();
			}
		}
		
		return str;
	}
	
   private void writeToFile(String filename, String text) {
	   try {
		   filename = "PRS-" + filename + "s.txt";
		   FileWriter fileWriter = new FileWriter(filename);		   
		   fileWriter.write(text);
		   fileWriter.close();   
	   }catch(IOException e) {
		   System.out.println(e.getMessage());
	   }	   
   }

}