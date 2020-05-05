// CSE1/4OOF Semester 2 2019 - Progress Check Test
import java.io.*;
import java.util.*;

public class Vector
{
	protected int[] values;
	protected int size;

	Vector(int[] input_integers, int default_value){
		if(input_integers.length < 2 || input_integers == null) {
			this.values = new int[2];
			for(int i=0; i<this.values.length; i++) {
				this.values[i] = default_value;
			}
		}else {
			int len = input_integers.length;
			this.values = new int[len];
			for(int i=0; i<this.values.length; i++) {
				this.values[i] = input_integers[i];
			}
		}
		this.size = this.values.length;
	}
	
	Vector(int ... arr){
		this(arr, 0);
	}

	public String toString() {
		String str = "[";
		for(int i=0; i<this.values.length; i++) {
			str += " " +String.valueOf(this.values[i]);
		}
		str += "]";
		return str;
	}
	
	public int[] getValues() {
		int[] arr = new int[this.size];
		for(int i=0; i<arr.length; i++) {
			arr[i] = this.values[i];
		}
		return arr;
	}
	
	public int getSize() {
		return this.size;
	}	
	
	// DO NOT MAKE ANY CHANGE ON main METHOD!!!
	public static void main(String[] args) throws IOException
	{
		Vector v = new Vector();
		System.out.println(v);

		v = new Vector(0);
		System.out.println(v);

		v = new Vector(1, 2);
		System.out.println(v);

		int[] a = {3};
		v = new Vector(a, 1);
		System.out.println(v);

		int[] b = {4, 5, 6};
		v = new Vector(b, 0);
		System.out.println(v);

		b[2] = 7;
		System.out.println(v);

		b = v.getValues();
		b[2] = 7;
		System.out.println(v);
	}
} 
