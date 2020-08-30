/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/
public abstract class Observation
{
	private ObservationType observationType;

	public Observation(ObservationType observationType)
	{
		this.observationType = observationType;
	}

	public abstract String getObservationDetails();

	public ObservationType getObservationType() {
		return observationType;
	}

	public void setObservationType(ObservationType observationType) {
		this.observationType = observationType;
	}

	@Override
	public String toString() {
		return "Observation [observationType=" + observationType + "]";
	}

	
}
