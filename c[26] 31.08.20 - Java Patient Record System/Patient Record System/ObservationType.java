/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/
public abstract class ObservationType
{
	private String code;
	private String name;

	public ObservationType(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public abstract String getObservationTypeDetails();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ObservationType [code=" + code + ", name=" + name + "]";
	} 
}
