/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports. 
*/

public class Oval extends Shape
{
   private int a;
   private int b;
   

   //define the constructor following the signature in the specification
   public Oval(int row, int col, int a, int b, char ch) {
	   super(row, col, ch);
	   this.a = a;
	   this.b = b;
   }

   public void draw(Window window)
   {
      for(int i = 0; i < 20; i=i+2)
      {
	      double angle = i * Math.PI/10;
	      int rdif = (int) Math.round(this.a * Math.cos(angle));
	      int row = this.getRowBasePosition() + rdif;
	      int cdif = (int) Math.round(this.b * Math.sin(angle));
	      int col = this.getColumnBasePosition() + cdif;
	      
	      window.setCell(row, col, this.getCharacter()); 
      }
   }

   //define other methods...
  	public String getSpecText() {
   		String string = "oval\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition() + " " + this.a + 
   			 " " + this.b + "\n" + this.getCharacter() + "\n.";
   		
   		return string;
   	}
  	
    public String toString() {
 	   String string = "oval (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition() + ") (" +
 			   	this.a + ", " + this.b + ") (" + this.getCharacter() + ")";
 	   return string;
    }
    
    public void increaseSize() {
 	   this.a++;
 	   this.b++;
    }
    
    public void decreaseSize() {
 	   this.a--;
 	   this.b--;
    }
}
