import java.io.*;
import java.util.*;

public class CategoryObservationType extends ObservationType
{
	private String [] categories;

	public CategoryObservationType(String code, String name, String [] categories)
	{
		super(code, name);
		this.categories = categories;
	}

	public String toString()
	{
		String str =  "CategoryObservationType[" + super.toString()
			+ ", categories: |";

		for(String cat: categories)
		{
			str = str + cat + "|" ;
		}

		str = str + "]";
		return str;

	}

	public String [] getCategories()
	{
		return categories;
	}

	public String getObservationTypeDetails()
	{
		String str =  "CategoryObservationType[code: " + getCode()
			+ ", name: " + getName()
			+ ", categories: |";

		for(String cat: categories)
		{
			str = str + cat + "|" ;
		}

		str = str + "]";
		return str; 
	} 

	// Quick tests
	public static void main(String [] args)
	{
		String [] categories = {"Group A", "Group B1", "Group B2"};
		CategoryObservationType cot = new CategoryObservationType(
				"C10", "Blood Type", categories);

		System.out.println(cot);
	}
	
	public String getObservationTypeName() {
		return "CategoryObservationType";
	}
	
	
	public String getFileString() {
		String str = "";
		
		for(String cat: categories)
		{
			str = str + cat + ", " ;
		}
		
		if("str" != "") {
			str = str.substring(0, str.length()-2);
		}
		
		return this.getCode() + "; " + this.getName() + "; " + str;
	}
}