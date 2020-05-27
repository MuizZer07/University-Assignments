/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Circle extends Shape
{
   private int rad;    // radius

   //define the constructor following the signature in the specification
   public Circle(int row, int col, int rad, char ch) {
	   super(row, col, ch);
	   this.rad = rad;
   }

   public void draw(Window window)
   {
      for(int i = 0; i < 20; i++)
      {
         double angle = i * Math.PI/10; //radian angle
         int rdif = (int) Math.round(this.rad * Math.cos(angle));
         int row = this.getRowBasePosition() + rdif;
         int cdif = (int) Math.round(this.rad * Math.sin(angle));
         int col = this.getColumnBasePosition() + cdif;

         window.setCell(row, col, this.getCharacter()); 
      }
   }

   //define other methods...
  	public String getSpecText() {
   		String string = "circle\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition() + " " + this.rad + 
   				"\n" + this.getCharacter() + "\n.";
   		
   		return string;
   	}
  	
    public String toString() {
 	   String string = "circle (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition() + ") (" +
 			   	this.rad + ") (" + this.getCharacter() + ")";
 	   return string;
    }
    
    public void increaseSize() {
 	   this.rad++;
    }
    
    public void decreaseSize() {
 	   this.rad--;
    }
}
