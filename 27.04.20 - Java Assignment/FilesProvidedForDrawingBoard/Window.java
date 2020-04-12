import java.util.ArrayList;

/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.
*/

public class Window
{
   protected int rows;
   protected int cols;
   protected ArrayList<Shape> shapes;
   protected char [][] cells;
   protected char border;

	/*
		rows = number of rows available for drawing, borders excluded
		cols = number of cols available for drawing, borders excluded
		cells = 2D array of char depicting the drawing, its size should be: [rows+2][cols+2]
		shapes = ordered list of shapes (You can use Arrays, or LinkedList as well if you want)
	*/
   public Window(int rows, int cols, char border)
   {
   		//Initialize everything
   		//Make a call to addBorders()
   }

   protected void addBorders(char ch)
   {
   		//add the border using ch as the character	
   }

   public void display()
   {
   		//display the content of the array to the screen
   }

   public void addShape(Shape shape)
   {
   		//add a shape to the collection
   		//call the draw() method of the shape to draw itself on this window
   }

	// This method is needed by classes of type Shape for method draw()
	// It cannot be private
	// We choose it to be accessible at the package level as the safest
	// choice open to us
   void setCell(int row, int col, char ch)
   {
   		//set the character at cells[row][col] to 'ch'
   }

   //define other methods as needed...
}
