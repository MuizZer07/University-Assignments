/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Circle extends Shape
{
   private int rad;    // radius

   //define the constructor following the signature in the specification

   public void draw(Window window)
   {
      //assuming row position of the center of this circle is 'rb'
      //assuming column position of the center of this circle is 'cb'

      //Attempt to draw 20 points and a possible way is shown below
      //you can come up with other possible logic for drawing a circle as well

      for(int i = 0; i < 20; i++)
      {
         double angle = i * Math.PI/10; //radian angle
         int rdif = (int) Math.round(rad * Math.cos(angle));
         int row = rb + rdif;
         int cdif = (int) Math.round(rad * Math.sin(angle));
         int col = cb + cdif;

         //now row and col forms a point on the perimeter of the circle
         
         //appropriate call to setCell() method of the Window class...
      }
   }

   //define other methods...
}
