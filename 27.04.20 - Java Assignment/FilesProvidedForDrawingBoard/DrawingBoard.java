import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class DrawingBoard
{
	private static Scanner kb = new Scanner(System.in);
	private static Window w = null;
	private static ArrayList<Shape> shapes = null;
	private static Shape selectedShape = null;

	public static void main(String [] args) throws Exception
   	{
		// Create or load a window
		// Display the window with grid added
		System.out.println("Enter the window file name (or NEW): ");
		String name = kb.nextLine().trim();
		if(name.equalsIgnoreCase("NEW"))
		{
			System.out.println(
				"Enter number of rows, number of columns and character (separated by space): " );
			int rbase = kb.nextInt();
			int cbase = kb.nextInt();
			char ch = kb.nextLine().trim().charAt(0);
			w = new Window(rbase, cbase, ch);
		}
		else
		{
			//call the appropriate method in the Window class to read the drawing specifications from file
			//and initialize the Window object w
			//you should use the method you developed in Task 4
		}

		//add the grids using the method you developed in Task 5
		//initialize the shapes ArrayList by calling a get method of the Window class 

		// Perform options
		
		boolean repeat = true;
		while(repeat)
		{
			System.out.println("\n");
			
			//you may add a call to the refreshImage() method you developed in Task 5 
			w.display();

			System.out.println("Add Erase Select Write Quit");
			System.out.println("Up Down Left Right + -");

			String cmd = kb.next();
			switch(cmd.toUpperCase().charAt(0))
			{
				case 'U':
					/*do something with the selected shape */ break;
			 	case 'D':
			 		/*do something with the selected shape */ break;
				case 'L':
					/*do something with the selected shape */ break;
				case 'R':
					/*do something with the selected shape */ break;
				case '+':
					/*do something with the selected shape */ break;
				case '-':
					/*do something with the selected shape */ break;

				case 'S':
					selectShape();break;
				case 'A':
					addShape(); break;
				case 'E':
					deleteShape(); break;
				case 'W':
					writeSpecToFile(); break;
				case 'Q':
						repeat = false;
						break;	// quit

				default: System.out.println("Wrong option chosen!");
			}
		} // while

		System.out.println("Thank You!");
	}

	//implement all the following methods...

	public static void selectShape()
	{

	}

	public static void addShape()
	{
	
	}

	public static void deleteShape()
	{

	}

	public static void writeSpecToFile()
	{
	
	}

	public static void listShapes()
	{
	
	}

}

