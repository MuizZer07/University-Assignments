import java.io.*;
import java.util.*;

public class CategoryObservation extends Observation
{
	private String value;

	public CategoryObservation(CategoryObservationType observationType, String value)
	throws Exception
	{
		super(observationType);

		if (!isValidValue(observationType, value))
		{
			throw new Exception("ERROR: " + value + " is an invalid category");
		}
		this.value = value;
	}

	//	helper method for the constructor
	public boolean isValidValue(CategoryObservationType observationType, String value)
	{
		for(String category: observationType.getCategories())
		{
			if (category.equals(value))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getFileWriteString() {
		return this.getObservationType().getCode() + "; " + this.value;
	}
	

	public String toString()
	{
		return "CategoryObservation[" +
			"observationType: " + getObservationType() +
			", value: " + value + "]";
	}

	public String getObservationDetails()
	{
		return "CategoryObservation[" +
			"observationType: " + getObservationType() +
			", value: " + value + "]";
	}
	
	// Quick tests
	public static void main(String [] args) throws Exception
	{
		String [] categories = {"Group A", "Group B1", "Group B2"};
		CategoryObservationType cot = new CategoryObservationType(
				"C10", "Blood Type", categories);

		CategoryObservation co = new CategoryObservation(cot, "Group A");
		System.out.println(co);

		// invalid request, throw exception
		try
		{
			CategoryObservation co2 = new CategoryObservation(cot, "Group D");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
