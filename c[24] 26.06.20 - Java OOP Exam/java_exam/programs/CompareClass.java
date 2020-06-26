
public class CompareClass{
	
	public static void main(String[] args) {
		numberObject num_obj1 =  new numberObject(4);
		numberObject num_obj2 =  new numberObject(5);
		numberObject num_obj3 =  new numberObject(10);
		
		numberObject max_obj = maximumObject(num_obj1, num_obj2, num_obj3);
		System.out.println(max_obj.getValue());
	}

	public static numberObject maximumObject(numberObject num1, numberObject num2) {
		int maxNumber = num1.compareTo(num2);
		numberObject num_obj =  new numberObject(0);
		
		if(maxNumber == num1.value) {
			return num1;
		}
		
		else if(maxNumber == num2.value) {
			return num2;
		}
		
		return num_obj;
	}

	public static numberObject maximumObject(numberObject num1, numberObject num2, numberObject num3) {
		numberObject num_obj =  new numberObject(0);
		numberObject max = maximumObject(num1, num2);
		int maxNumber = max.compareTo(num3);
		
		if(maxNumber == max.value) {
			return max;
		}
		
		else if(maxNumber == num3.value) {
			return num3;
		}
		
		return num_obj;
	}
	
	public static class numberObject implements Comparable<numberObject> {
		int value;
		
		numberObject(int value){
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
		
		@Override
		public int compareTo(numberObject number) {
			
			if (this.value >= number.value) { 
				return this.value; 
			} else { 
				return number.value; 
			} 
		}
		
	}	
}

