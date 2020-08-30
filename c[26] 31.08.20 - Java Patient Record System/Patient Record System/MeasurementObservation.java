/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/
public class MeasurementObservation extends Observation
{
	private double value;

	public MeasurementObservation(MeasurementObservationType observationType, double value)
	{
		super(observationType);
		this.value = value;
	}

	@Override
	public String getObservationDetails() {
		String str = "";
		
		str += this.getObservationType().getObservationTypeDetails() + ", Value: " + this.value;
		
		return str;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MeasurementObservation [value=" + value + "]";
	}

}