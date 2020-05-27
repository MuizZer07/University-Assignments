import java.io.BufferedReader;

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

   public Text(int row, int col, String text, int rInc, int cInc) {
	   super(row, col, ' ');
	   this.text = text;
	   this.rInc = rInc;
	   this.cInc = cInc;
   }
   
   public void draw(Window window)
   {
      //assuming row position of the base point of this text is 'rb'
      //assuming column position of the base point of this text is 'cb'

      for(int i = 0; i < this.text.length(); i++)
      {
         char ch = this.text.charAt(i);
         int row = this.getRowBasePosition() + i * this.rInc;
         int col = this.getColumnBasePosition() + i * this.cInc;

         //appropriate call to setCell() method of the Window class...
         window.setCell(row, col, ch);
      }
   }

   //define other methods...
 	public String getSpecText() {
   		String string = "text\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition() + "\n" + this.text
   				+ "\n" + this.rInc + " " + this.cInc + "\n.";
   		
   		return string;
   	}
 	
    public String toString() {
 	   String string = "text (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition()
 	   				+ ") (" + this.text + ") ("+ this.rInc + ", " + this.cInc + ")";
 	   return string;
    }
    
    public void increaseSize() {

    }
    
    
    public void decreaseSize() {

    }
    
    public int readShapeFromTextFile(String line, BufferedReader reader, int i, Window window) {
    	return 0;
    }
}
