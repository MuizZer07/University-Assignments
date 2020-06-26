
public class Sorter {

	public static void sortByString(StringKeyed[] collection) { 
		for (int i = collection.length - 1; i > 0; --i) { 
			int indexOfLargest = 0; 
			
			for (int j = 1; j <= i; ++j) { 
				if (collection[j].getStringKey().compareTo(collection[ indexOfLargest].getStringKey())>0) { 
					indexOfLargest = j; 
				} 
			} 
			
			StringKeyed temp = collection[i]; 
			collection[i] = collection[indexOfLargest]; 
			collection[indexOfLargest] = temp; 
		}
		
		for (int j = 0; j < collection.length; j++) { 
			System.out.println(collection[j].getStringKey());
		} 
	}
}
