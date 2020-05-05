// CSE1/4OOF Semester 2 2019 - Progress Check Test
import java.io.*;
import java.util.*;

public class RangedVector extends Vector
{
	private int lowerbound;
	private int upperbound;	
	
	RangedVector(int[] input_values, int lowerbound, int upperbound){
		super(input_values);
		
		this.lowerbound = lowerbound;
		this.upperbound = upperbound;
		
		int[] values = super.getValues();
		for(int i=0; i<values.length; i++) {
			if(super.values[i] < this.lowerbound) {
				super.values[i] = this.lowerbound;
			}
			if(super.values[i] > this.upperbound) {
				super.values[i] = this.upperbound;
			}
		}
	}
	
	public double getDistance(RangedVector ranged_vector){
		if(ranged_vector == null || ranged_vector.getSize() != this.getSize()){
			return -1;
		}
		int[] a = this.getValues();
		int[] b = ranged_vector.getValues();
		
		int sum = 0;
		for(int i=0; i<ranged_vector.getSize(); i++) {
			int sub = (a[i]-b[i]);
			int _sqrt = sub * sub;
			sum += _sqrt;
		}
		double result = Math.sqrt(sum);
		return result;
	}
	
	public Vector add(RangedVector ranged_vector) {
		if(ranged_vector == null || ranged_vector.getSize() != this.getSize()){
			return null;
		}
		
		int[] a = this.getValues();
		int[] b = ranged_vector.getValues();
		int[] c = new int[ranged_vector.getSize()];
		
		for(int i=0; i<ranged_vector.getSize(); i++) {
			c[i] = (a[i]+b[i]);
			
		}
		Vector v = new Vector(c, 0);
		return v;
	}

	public String toString() {
		String str = super.toString();
		if(this.lowerbound == this.upperbound) {
			return str;
		}
		
		str += " in range of [ "+ this.lowerbound + ", " + this.upperbound + " ]";
		return str;
	}

	// CHANGE ON main METHOD IS ONLY ALLOWED BETWEEN THE TWO COMMENTS INSIDE!!!
	public static void main(String[] args) throws IOException
	{
		if (args == null || args.length != 3)
		{
			System.out.println("Error: The program requires as input 3 .dat files.");
		}
		else
		{
			RangedVector[] rv = new RangedVector[3];

			//---CHANGE ON main METHOD STARTS HERE
			for(int i=0; i<args.length; i++){
				File input_file = new File(args[i]);
				Scanner scanner = new Scanner(input_file);

				int a;
				int n = 0;
				while (scanner.hasNextInt()){
					a = scanner.nextInt();
					n++;
				}
				int[] arr = new int[n-2];
				
				scanner = new Scanner(input_file);
				int n1 = 0;
				
				int lowerbound = scanner.nextInt();
				int upperbound = scanner.nextInt();
				for(int j=0; j<n-2; j++){
					int number = scanner.nextInt();
					arr[j] = number;
				}
				
				rv[i] = new RangedVector(arr, lowerbound, upperbound);
			}
			
        
			//---CHANGE ON main METHOD ENDS HERE

			for (int i = 0; i < 3; i++)
			{
				System.out.printf("RV %d: %s\n", i, rv[i]);
			}

			System.out.println("--->");
			
			for (int i = 0; i < 3; i++)
			{
				for (int j = i + 1; j < 3; j++)
				{
					System.out.printf("Euclidean distance between RV %d and RV %d: ", i, j);
					System.out.printf("%.2f\n", rv[i].getDistance(rv[j]));

					System.out.printf("Addition of RV %d and RV %d: ", i, j);
					Vector v = rv[i].add(rv[j]);
					if (v != null)
					{
						System.out.println(v);
					}
					else
					{
						System.out.println("Invalid!");
					}
				}
			}
		}
	}
} 
