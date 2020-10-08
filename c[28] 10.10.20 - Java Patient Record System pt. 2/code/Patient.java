import java.io.*;
import java.util.*;

public class Patient
{
	private String id;
	private String name;
	private Observation [] observations;
	int observationCount;

	public Patient(String id, String name)
	{
		this.id = id;
		this.name = name;
		observations = new Observation[50];
		observationCount = 0;
	}

	public String toString()
	{
		String str = "Patient id: " + id + ", name: " + name + "\n";

		str = str 	+ "Observations: \n";
		for(int i = 0; i < observationCount; i++)
		{
			str = str + "- " +  observations[i] +"\n" ;
		}

		return str;
	}

	public void addObservation(Observation observation) throws Exception
	{
		if (hasObservationOfSameType(observation))
		{
			throw new Exception("ERROR: The patient already has observation of this type");
		}

		observations[observationCount++] = observation;
	}

	// helper method
	// used to ensure that the patient does not have more than one
	// observation of the same type
	public boolean hasObservationOfSameType(Observation thisObservation)
	{
		for(int i = 0; i < observationCount; i++)
		{
			if (observations[i].getObservationType().getCode().equals
				(thisObservation.getObservationType().getCode()))
			{
				return true;
			}
		}
		return false;
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
	
	public String getFileWriteString() {
		return this.id + "; " + this.name;
	}
	
	public String[] getObservationsString() {
		String[] str = new String[2];
		String measurementStr = "";
		String categoryStr = "";
		
		for(int i = 0; i < observationCount; i++)
		{
			if(observations[i].getObservationType().getObservationTypeName().equals("MeasurementObservationType")) {
				measurementStr += this.id + "; " +  observations[i].getFileWriteString() + "\n" ;
			}
			
			if(observations[i].getObservationType().getObservationTypeName().equals("CategoryObservationType")) {
				categoryStr += this.id + "; " +  observations[i].getFileWriteString() + "\n" ;
			}
		}
		
		str[0] = measurementStr;
		str[1] = categoryStr;
		return str;
	}
	//	Quick tests
	public static void main(String [] args) throws Exception
	{
		// create a patient
		Patient p = new Patient("P10", "Smith");
		System.out.println(p);

		// create 2 observations
		MeasurementObservationType mot =
					new MeasurementObservationType("MOT10", "Height", "cm");
		MeasurementObservation mo = new MeasurementObservation(mot, 175);

		String [] categories = {"Group A", "Group B1", "Group B2"};
		CategoryObservationType cot = new CategoryObservationType(
				"C10", "Blood Type", categories);
		CategoryObservation co = new CategoryObservation(cot, "Group A");

		// add observation to patient
		p.addObservation(mo);
		p.addObservation(co);
		System.out.println(p);


		// invalid request => throw exception
		try
		{
			MeasurementObservation mo2 = new MeasurementObservation(mot, 180);
			p.addObservation(mo2);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			System.out.println(p);
		}

	}
}

/* Output:
Patient id: P10, name: Smith
Observations:

Patient id: P10, name: Smith
Observations:
- MeasurementObservation[observationType: MeasurementObservationType[code: MOT10
, name: Height,unit: cm], value: 175.0]
- CategoryObservation[observationType: CategoryObservationType[code: C10, name:
Blood Type, categories: |Group A|Group B1|Group B2|], value: Group A]

ERROR: The patient already has observation of this type
Patient id: P10, name: Smith
Observations:
- MeasurementObservation[observationType: MeasurementObservationType[code: MOT10
, name: Height,unit: cm], value: 175.0]
- CategoryObservation[observationType: CategoryObservationType[code: C10, name:
Blood Type, categories: |Group A|Group B1|Group B2|], value: Group A]
*/

