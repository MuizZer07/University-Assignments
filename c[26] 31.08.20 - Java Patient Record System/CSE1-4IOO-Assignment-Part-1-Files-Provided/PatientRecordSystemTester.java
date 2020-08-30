/*	
PatientRecordSystemTester.java
You should be able to run this test program without any changes

This test program does not cover all possible tests. You are welcome to add more tests if necessary. 

Submit this test file along with other java files.

*/
import java.io.*;
import java.util.*;

public class PatientRecordSystemTester
{
	public static void main(String [] args) throws Exception
	{
		//each test method is independent.
		testInit();
		testAddObservationTypes();
		testAddPatients();
		testAddObservations();
		testQueries();
	}

	public static void testInit()
	{
		String test = "TEST: Create new patient record system and display it";
		System.out.println(test);

		PatientRecordSystem prs = new PatientRecordSystem();
		System.out.println(prs);
	}

	public static void testAddObservationTypes() throws Exception
	{
		PatientRecordSystem prs = new PatientRecordSystem();

		// add a measurement observation type
		prs.addMeasurementObservationType("T100", "Blood Pressure", "psi");
		System.out.println(prs);

		// add a measurement observation type with invalid code
		//	check that it is not added
		try
		{
			prs.addMeasurementObservationType("T100", "Height", "cm");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.println(prs);
		}

		//add a category observation type
		String [] categories = {"Group A", "Group B1", "Group B2"};
		prs.addCategoryObservationType("T200", "blood type", categories);
		System.out.println(prs);
	}

	public static void testAddPatients() throws Exception
	{
		PatientRecordSystem prs = new PatientRecordSystem();

		//add a new patient
		prs.addPatient("P100", "Smith");
		System.out.println(prs);

		// add another patient
		prs.addPatient("P200", "Adams");
		System.out.println(prs);

		// invalid request
		try
		{
			prs.addPatient("P200", "Blake");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.println(prs);
		}
	}

	public static void testAddObservations() throws Exception
	{
		// Create PatientRecordSystem and add patients
		PatientRecordSystem prs = new PatientRecordSystem();

		// add observation types
		prs.addMeasurementObservationType("T100", "Blood Pressure", "psi");
		String [] categories = {"Group A", "Group B1", "Group B2"};
		prs.addCategoryObservationType("T200", "blood type", categories);

		//add patients
		prs.addPatient("P100", "Smith");
		prs.addPatient("P200", "Adams");
		System.out.println(prs);

		// add a measurement observation to Smith's records
		prs.addMeasurementObservation("P100", "T100", 120);
		System.out.println(prs);

		// add a category observation to Smith's records
		prs.addCategoryObservation("P100", "T200", "Group A");
		System.out.println(prs);

		// invalid request: patient already has observation of the type
		try
		{
			prs.addMeasurementObservation("P100", "T100", 140);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.println(prs);
		}

		// invalid request: invalid category value
		try
		{
			prs.addCategoryObservation("P200", "T200", "Group D");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.println(prs);
		}
		//feel free to add more tests here if you want 
	}

	public static void testQueries() throws Exception
	{
		// Create PatientRecordSystem
		// Add observation types, patients and observations
		PatientRecordSystem prs = new PatientRecordSystem();

		prs.addMeasurementObservationType("T100", "Blood Pressure", "psi");

		String [] categories = {"Group A", "Group B1", "Group B2"};
		prs.addCategoryObservationType("T200", "blood type", categories);

		prs.addPatient("P100", "Smith");
		prs.addPatient("P200", "Adams");

		prs.addMeasurementObservation("P100", "T100", 120);
		prs.addCategoryObservation("P100", "T200", "Group A");

		System.out.println(prs);

		System.out.println("\n" + prs.retrieveObservationTypeDetails("T100"));
		System.out.println("\n" + prs.retrieveObservationTypeDetails("T200"));
		System.out.println("\n" + prs.retrieveObservationTypeDetails("T300"));

		System.out.println("\n" + prs.retrievePatientDetails("P100"));
		System.out.println("\n" + prs.retrievePatientDetails("P300"));
	}
}