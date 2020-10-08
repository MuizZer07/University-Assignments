import java.io.*;
import java.util.*;

public class MeasurementObservation extends Observation
{
	private double value;

	public MeasurementObservation(MeasurementObservationType observationType, double value)
	{
		super(observationType);
		this.value = value;
	}

	public String toString()
	{
		return "MeasurementObservation[" +
			"observationType: " + getObservationType() +
			", value: " + value + "]";
	}

	public String getObservationDetails()
	{
		return "MeasurementObservation[" +
			"observationType: " + getObservationType() +
			", value: " + value + "]";
	}

	public String getFileWriteString() {
		return this.getObservationType().getCode() + "; " + this.value;
	}
	
	// Quick tests
	public static void main(String [] args)
	{
		MeasurementObservationType mot =
			new MeasurementObservationType("MOT10", "Height", "cm");
		MeasurementObservation mo =
			new MeasurementObservation(mot, 175);
		System.out.println(mo);
	}

}