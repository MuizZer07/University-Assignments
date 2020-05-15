/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Line extends Shape
{
   private int length;	// line would have (length + 1) characters
   private int rInc;   // -1 (go up), 0 or 1 (go down)
   private int cInc;   // -1, 0 or 1
               // if both = 0, then have a point

   //define the constructor following the signature in the specification

   public void draw(Window window)
   {
      for(int i = 0; i <= length; i++)
      {
         //determine appropriate row, col values
         //then make a call to setCell() method of the Window class

         //after reading the specification, draw a line in a piece of paper first
         //to visualize the different points in a line
      }
   }

   //define other methods...
}
