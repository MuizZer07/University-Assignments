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
   	public Triangle(int row, int col, int height, int rInc, int cInc, char ch) {
   		super(row, col, ch);
   		this.height = height;
   		this.rInc = rInc;
   		this.cInc = cInc;
   	}

   	public void draw(Window window)
   	{
   		//assuming row position of the base point of this triangle is 'rb'
   		//assuming column position of the base point of this triangle is 'cb'
   		//assuming the drawing character is 'character'
   		//assuming the constructor in the Line class has been defined according to the specification

   		Line line1, line2, line3;
      	if(rInc == 0)//when the height vector goes right or left from the base point
      	{
			line1 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.height, -1, this.cInc, this.getCharacter());
			line2 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.height, 1, this.cInc, this.getCharacter());
			line3 = new Line(this.getRowBasePosition() - this.height, this.getColumnBasePosition() + this.cInc * this.height, 2 * this.height, 1, 0, this.getCharacter());
			
			//now use the draw method in the Line class to draw the triangle
	      	line1.draw(window);
	      	line2.draw(window);
	      	line3.draw(window);
		}
		else if(cInc == 0)//when the height vector goes up or down from the base point
		{
			line1 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.height, this.rInc, -1, this.getCharacter());
			line2 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.height, this.rInc, 1, this.getCharacter());
			line3 = new Line(this.getRowBasePosition() + this.rInc * this.height, this.getColumnBasePosition() - this.height, 2 * this.height, 0, 1, this.getCharacter());
			
			//now use the draw method in the Line class to draw the triangle
	      	line1.draw(window);
	      	line2.draw(window);
	      	line3.draw(window);
		}
	}

	//define other methods...
 	public String getSpecText() {
   		String string = "triangle\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition() + " " + this.height
   				+ " " + this.rInc + " " + this.cInc + "\n" + this.getCharacter() + "\n.";
   		
   		return string;
   	}
 	
    public String toString() {
 	   String string = "triangle (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition()
 	   			+ ") (" + this.height + ") (" + this.rInc + ", " + this.cInc + ") (" + this.getCharacter() + ")";
 	   return string;
    }
    
    public void increaseSize() {
 	   this.height++;
    }
    
    public void decreaseSize() {
 	   this.height--;
    }
    
}
