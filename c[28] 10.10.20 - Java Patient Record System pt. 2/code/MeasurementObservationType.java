import java.io.*;
import java.util.*;

public class MeasurementObservationType extends ObservationType
{
	private String unit;

	public MeasurementObservationType(String code, String name, String unit)
	{
		super(code, name);
		this.unit = unit;
	}

	public String toString()
	{
		return "MeasurementObservationType[" +
			super.toString() + ",unit: " + unit + "]";
	}

	public String getUnit()
	{
		return unit;
	}

	public String getObservationTypeDetails()
	{
		String str =  "MeasurementObservationType[code: " + getCode()
			+ ", name: " + getName()
			+ ",unit: " + unit + "]";

		return str; 
	}

	// Quick tests
	public static void main(String [] args)
	{
		String [] categories = {"Group A", "Group B1", "Group B2"};
		MeasurementObservationType mot = new MeasurementObservationType(
			"M10", "Blood Pressure", "psi");

		System.out.println(mot);
	}
	
	public String getObservationTypeName() {
		return "MeasurementObservationType";
	}
	
	public String getFileString() {
		return this.getCode() + "; " + this.getName() + "; "  + this.unit;
	}
}