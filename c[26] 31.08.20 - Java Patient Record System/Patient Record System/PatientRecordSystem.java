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
		this.observationTypes = new ObservationType[50];
		this.patients = new Patient[50];
		this.observationTypeCount = 0;
		this.patientCount = 0;
	}

	public String toString()
	{
		String str = "===== PATIENT RECORD SYSTEM =====\n";
		str = str + "OBSERVATION TYPES:\n...........\n";
		
		// return details of the observation types
		for(int i=0; i<this.observationTypeCount; i++) {
			ObservationType obeservationType = this.observationTypes[i];
			str = str + obeservationType.getObservationTypeDetails() +"\n";
		}
		
		str = str + "Patients:\n...........\n";
		// and details of the patients
		for(int i=0; i<this.patientCount; i++) {
			Patient patient = this.patients[i];
			str = str + patient.toString() + "\n";
		}
		
		return str;
	}

	// Method to add measurement observation types (you can add to the method header if required)
	public void addMeasurementObservationType(String code, String name, String unit) throws Exception 
	{
		if(this.searchObservationType(code) == -1) {
			ObservationType newObservationType = new MeasurementObservationType(code, name, unit);
			this.observationTypes[this.observationTypeCount++] = newObservationType;
		}else {
			throw new Exception("Observation Type code already exists!");
		}
	}

	// Helper method: Search for an observation type to ensure functional correctness
	// Return index if found
	// Return -1 if not
	public int searchObservationType(String code)
	{
		for(int i=0; i<this.observationTypeCount; i++) {
			ObservationType obeservationType = this.observationTypes[i];
			if(obeservationType.getCode().equals(code)) {
				return i;
			}
		}
		return -1;
	}

	// Add category observation types (you can add to the method header if required)
	public void addCategoryObservationType(String code, String name, String [] categories) throws Exception
	{
		if(this.searchObservationType(code) == -1) {
			ObservationType newObservationType = new CategoryObservationType(code, name, categories);
			this.observationTypes[this.observationTypeCount++] = newObservationType;
		}else {
			throw new Exception("Observation Type code already exists!");
		}
	}

	// Add a new patient (you can add to the method header if required)
	public void addPatient(String id, String name) throws Exception
	{
		if(this.searchPatient(id) == -1) {
			Patient newPatient = new Patient(id, name);
			this.patients[this.patientCount++] = newPatient; 
		}else {
			throw new Exception("Patient ID already exists!");
		}

	}

	// Helper method: Search for a patient by patient id to ensure functional correctness
	// return index if the patient is found
	// Return -1 if the patient is not found
	public int searchPatient(String id)
	{
		for(int i=0; i<this.patientCount; i++) {
			Patient patient = this.patients[i];
			if(patient.getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	// Add a measurement observation for a patient (you can add to the method header if required)
	public void addMeasurementObservation(String id, String code, double value) throws Exception
	{
		// id for patient, code for observation
		int patientIndex = this.searchPatient(id);
		int typeIndex = this.searchObservationType(code);
		
		if(patientIndex!=-1 && typeIndex!=-1) {
			Observation observation = new MeasurementObservation((MeasurementObservationType) this.observationTypes[typeIndex], value);
			this.patients[patientIndex].addObservation(observation);
		}		
	}

	// method to add a category observation for a patient (you can add to the method header if required)
	public void addCategoryObservation(String id, String code, String value) throws Exception
	{
		// id for patient, code for observation
		int patientIndex = this.searchPatient(id);
		int typeIndex = this.searchObservationType(code);
		
		if(patientIndex!=-1 && typeIndex!=-1) {
			Observation observation = new CategoryObservation((CategoryObservationType) this.observationTypes[typeIndex], value);
			this.patients[patientIndex].addObservation(observation);
			
		}	
	}

	// retrieve details of an observation type
	public String retrieveObservationTypeDetails(String code)
	{
		int index = this.searchObservationType(code);
		
		if(index!=-1) {
			return this.observationTypes[index].getObservationTypeDetails();
		}
		return "Observation Code not found!";
	}

	// retrieve details of a patient
	public String retrievePatientDetails(String id)
	{
		int index = this.searchPatient(id);
		
		if(index!=-1) {
			return this.patients[index].toString();
		}
		return "Patient ID not found!";
	}

}