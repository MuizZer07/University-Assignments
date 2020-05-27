/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.
*/

public class Rectangle extends Shape
{
	private int height;
   	private int width;

    //define the constructor following the signature in the specification
   	public Rectangle(int row, int column, int height, int width, char ch){
   		super(row, column, ch);
   		this.height = height;
   		this.width = width;
   	}
   	
   	public Rectangle(){

   	}

   	public void draw(Window window)
   	{
   		//treat the rectangle as four lines 

   		//Line line1 = new Line(/*appropriate parameters goes here*/);
      	//similarly line2, line3, line4

		//now use the draw method in the Line class to draw the rectangle
   		Line line1 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.width, 0, 1, this.getCharacter());
   		line1.draw(window);
   		
   		Line line2 = new Line(this.getRowBasePosition(), this.getColumnBasePosition(), this.height, 1, 0, this.getCharacter());
   		line2.draw(window);

   		Line line3 = new Line(this.getRowBasePosition() + this.height, this.getColumnBasePosition(), this.width, 0, 1, this.getCharacter());
   		line3.draw(window);
   		
   		Line line4 = new Line(this.getRowBasePosition(), this.getColumnBasePosition() + this.width, this.height, 1, 0, this.getCharacter());
   		line4.draw(window);
   	
   	}

	//define other methods...
   	public String getSpecText() {
   		String string = "rectangle\n" + this.getRowBasePosition() + " " + this.getColumnBasePosition() + " "
   						+ this.height + " " + this.width + "\n" + this.getCharacter() + "\n.";
   		
   		return string;
   	}
   	
    public String toString() {
 	   String string = "rectangle (" + this.getRowBasePosition() + ", " + this.getColumnBasePosition()
 	   			+ ") (" + this.height + ", " + this.width + ") (" + this.getCharacter() + ")";
 	   return string;
    }
    
    public void increaseSize() {
 	   this.height++;
 	   this.width++;
    }
    
    public void decreaseSize() {
  	   this.height--;
  	   this.width--;
     }
   
}
