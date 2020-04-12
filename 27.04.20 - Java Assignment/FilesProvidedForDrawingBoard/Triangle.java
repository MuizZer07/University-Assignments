/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Triangle extends Shape
{
   	private int height;	// height of isosceles triangle
   	private int rInc;   // only (1, 0), (-1,0), (0,1) and (0,-1)
   	private int cInc;   // are allowed


    //define the constructor following the signature in the specification

   	public void draw(Window window)
   	{
   		//assuming row position of the base point of this triangle is 'rb'
   		//assuming column position of the base point of this triangle is 'cb'
   		//assuming the drawing character is 'character'
   		//assuming the constructor in the Line class has been defined according to the specification

      	if(rInc == 0)//when the height vector goes right or left from the base point
      	{
			Line line1 = new Line(rb, cb, height, -1, cInc, character);
			Line line2 = new Line(rb, cb, height, 1, cInc, character); 
			Line line3 = new Line(rb - height, cb + cInc * height, 2 *height,
				1, 0, character);
			//now use the draw method in the Line class to draw the triangle

		}
		else if(cInc == 0)//when the height vector goes up or down from the base point
		{
			Line line1 = new Line(rb, cb, height, rInc, -1, character);
			Line line2 = new Line(rb, cb,  height, rInc, 1, character);
			Line line3 = new Line(rb + rInc * height, cb - height,
				 2*height, 0, 1, character);
			//now use the draw method in the Line class to draw the triangle

		}
	}

	//define other methods...

}
