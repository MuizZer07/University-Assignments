
public class extra {

	public static void main(String[] args) throws Exception { 
		Insurance i1 = new Insurance("1", 10.0, 15.6);
		Insurance i2 = new Insurance("2", 10.0, 11.6);
		Insurance i3 = new Insurance("3", 10.0, 0-.6);
		Insurance i4 = new Insurance("4", 10.0, 1.6);
		
		Insurance[] collection = {i1, i2, i3, i4};
		
		Sorter.sortByString(collection);
		
	}
}
