import java.io.*;
import java.util.*;

public abstract class Observation implements Serializable
{
	private ObservationType observationType;

	public Observation(ObservationType observationType)
	{
		this.observationType = observationType;
	}

	public ObservationType getObservationType()
	{
		return observationType;
	}
	
	public abstract String getObservationDetails();
	
	public abstract String getFileWriteString();
}
