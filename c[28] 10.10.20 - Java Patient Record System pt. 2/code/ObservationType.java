import java.io.*;
import java.util.*;

public abstract class ObservationType
{
	private String code;
	private String name;

	public ObservationType(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public String toString()
	{
		return "code: " + code + ", name: " + name;
	}

	public String getCode()
	{
		return code;
	}

	public String getName()
	{
		return name;
	}

	public abstract String getObservationTypeDetails(); 
	
	public abstract String getObservationTypeName(); 
	
	public abstract String getFileString(); 
}
