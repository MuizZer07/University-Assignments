/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/

public class CategoryObservation extends Observation
{
	private String value;

	@Override
	public String toString() {
		return "CategoryObservation [value=" + value + "]";
	}

	public CategoryObservation(CategoryObservationType observationType, String value) throws Exception //you may add to this method header
	{
		super(observationType);
		if(this.checkValueIsValid(value)!= -1) 
			this.value = value;
		else
			throw new Exception("Invalid category value!");
	}

	@Override
	public String getObservationDetails() {
		String str = "";
		
		str += this.getObservationType().getObservationTypeDetails() + ", Value: " + this.value;
		
		return str;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) throws Exception {
		if(this.checkValueIsValid(value) != -1) 
			this.value = value;
		else
			throw new Exception("Invalid category value!");
	}
	
	private int checkValueIsValid(String value) {
		CategoryObservationType categoryObservationType = (CategoryObservationType) this.getObservationType();
		String[] validCategories = categoryObservationType.getCategories();
		
		for(int i=0; i < validCategories.length; i++) {
			if(validCategories[i].equals(value))
				return i;
		}
		return -1;
	}
}
