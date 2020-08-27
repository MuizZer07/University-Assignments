/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/
import java.io.*;
import java.util.*;

public class PatientRecordSystem
{
	// collection of observation types and patients
	private ObservationType [] observationTypes;
	private int observationTypeCount;

	private Patient [] patients;
	private int patientCount;

	public PatientRecordSystem() //constructor
	{

	}

	public String toString()
	{
		String str = "===== PATIENT RECORD SYSTEM =====\n";
		str = str + "OBSERVATION TYPES:\n";
		// return details of the observation types

		// and details of the patients
		
		return str;
	}

	// Method to add measurement observation types (you can add to the method header if required)
	public void addMeasurementObservationType(String code, String name, String unit) 
	{

	}

	// Helper method: Search for an observation type to ensure functional correctness
	// Return index if found
	// Return -1 if not
	public int searchObservationType(String code)
	{

	}

	// Add category observation types (you can add to the method header if required)
	public void addCategoryObservationType(String code, String name, String [] categories)
	{

	}

	// Add a new patient (you can add to the method header if required)
	public void addPatient(String id, String name)
	{

	}

	// Helper method: Search for a patient by patient id to ensure functional correctness
	// return index if the patient is found
	// Return -1 if the patient is not found
	public int searchPatient(String id)
	{

	}

	// Add a measurement observation for a patient (you can add to the method header if required)
	public void addMeasurementObservation(String id, String code, double value)
	{
		
	}

	// method to add a category observation for a patient (you can add to the method header if required)
	public void addCategoryObservation(String id, String code, String value)
	{
		
	}

	// retrieve details of an observation type
	public String retrieveObservationTypeDetails(String code)
	{

	}

	// retrieve details of a patient
	public String retrievePatientDetails(String id)
	{

	}

}