/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Text extends Shape
{
   private String text;
   private int rInc;
   private int cInc;

   //define the constructor following the signature in the specification

   public void draw(Window window)
   {
      //assuming row position of the base point of this text is 'rb'
      //assuming column position of the base point of this text is 'cb'

      for(int i = 0; i < text.length(); i++)
      {
         char ch = text.charAt(i);
         int row = rb + i * rInc;
         int col = cb + i * cInc;

         //appropriate call to setCell() method of the Window class...

      }
   }

   //define other methods...
}
