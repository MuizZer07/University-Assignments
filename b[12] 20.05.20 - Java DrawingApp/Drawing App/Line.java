import java.io.BufferedReader;

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
   public Line(int row, int column, int length, int rInc, int cInc, char ch) {
	   super(row, column, ch);
	   this.length = length;
	   this.rInc = rInc;
	   this.cInc = cInc;
   }
   
   public Line(){
	   
   }

   public void draw(Window window)
   {
      for(int i = 0; i <= length; i++)
      {
         //determine appropriate row, col values
         //then make a call to setCell() method of the Window class

         //after reading the specification, draw a line in a piece of paper first
         //to visualize the different points in a line
    	 
    	 window.setCell(this.getRowBasePosition() + (this.rInc * i), this.getColumnBasePosition() + (this.cInc * i), this.getCharacter()); 
      }
   }
   
   //define other methods...
   public String getSpecText() {
	   String string = "line\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition()  + " "
	   					+ this.length + " " + this.rInc + " " + this.cInc + "\n" + this.getCharacter() + "\n.";
	   
	   return string;
   }
   
   public String toString() {
 	   String string = "line (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition() + ") (" +
			   	this.length + ") (" + this.rInc + ", " + this.cInc  + ") (" + this.getCharacter() + ")";
	   return string;
   }
   
   public void increaseSize() {
	   this.length++;
   }
   
   public void decreaseSize() {
	   this.length--;
   }
   
   public int readShapeFromTextFile(String line, BufferedReader reader, int i, Window window) {
	   int row = 0, col = 0, length = 0, rInc = 0, cInc = 0;
	   char newCH = ' ';
	   char ch = ' ';
		
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(words.length > 1) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);
				length = Integer.parseInt(words[2]);
				rInc = Integer.parseInt(words[3]);
				cInc = Integer.parseInt(words[4]);
			}else{
				if(ch != ' ') {
					newCH = words[0].charAt(0);
				}
			}
			
			try {
				line = reader.readLine();
				words = line.split(" ");
				ch = words[0].charAt(0);
				i++;
			}catch(Exception e) {
				i = -1;
			}
		}
		
		Line newLine = new Line(row, col, length, rInc, cInc, newCH);
		window.addShape(newLine);
		return i;
   }
}
