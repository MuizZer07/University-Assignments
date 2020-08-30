import java.util.Arrays;

/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/
public class CategoryObservationType extends ObservationType
{
	private String [] categories;

	public CategoryObservationType(String code, String name, String [] categories)
	{
		super(code, name);
		this.categories = categories;
	}

	@Override
	public String getObservationTypeDetails() {
		String str = "";
		str += "Category Observation Type. Code: " + this.getCode() + ", Name: " + this.getName() + ", Unit: " + Arrays.toString(this.getCategories());
		
		return str;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "CategoryObservationType [categories=" + Arrays.toString(categories) + "]";
	}

}