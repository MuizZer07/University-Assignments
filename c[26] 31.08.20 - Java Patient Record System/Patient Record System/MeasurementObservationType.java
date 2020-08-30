/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/

public class MeasurementObservationType extends ObservationType
{
	private String unit;

	public MeasurementObservationType(String code, String name, String unit)
	{
		super(code, name);
		this.unit = unit;
	}

	@Override
	public String getObservationTypeDetails() {
		String str = "";
		
		str += "Measurement Observation Type. Code: " + this.getCode() + ", Name: " + this.getName() + ", Unit: " + this.getUnit();
		
		return str;
	}
	

	@Override
	public String toString() {
		return "MeasurementObservationType [unit=" + unit + "]";
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}