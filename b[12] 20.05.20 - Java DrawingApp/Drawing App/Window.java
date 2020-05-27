import java.util.ArrayList;
import java.io.FileWriter;  // Import the File class
import java.io.IOException; 
import java.io.FileReader;
import java.io.BufferedReader;

/*
This is just an initial skeleton of the class to help you get started. 
It does NOT contain all the methods to complete the assignment requirements.
As you add more code to it, you might have to do more imports.
*/

public class Window
{
   protected int rows;
   protected int cols;
   protected ArrayList<Shape> shapes;
   protected char [][] cells;
   protected char border;
   protected String specText;
   public  boolean isInitialized = false;

   public Window(int rows, int cols, char border)
   {
	   this.rows = rows;
	   this.cols = cols;
	   this.border = border;
	   this.specText = this.getSpecText();
	   this.initializeWindow();
   }

   public Window() {
	   
   }
   
   protected void addBorders(char ch)
   {
   		for(int i=0; i < this.rows+2; i++) {
   			for(int j=0; j < this.cols+2; j++) {
   				if(i == 0 || i == this.rows+1 || j == 0 || j == this.cols+1) {
   					this.cells[i][j] = ch;	
   				}
   			}
   		}
   }
   
   public boolean increaseShapeSize(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).increaseSize();
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }
   
   public boolean decreaseShapeSize(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).decreaseSize();
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }
   
   public boolean moveShapeUp(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).setRowBasePosition(this.shapes.get(shapeIndex).getRowBasePosition()-1);
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }
   
   public boolean moveShapeDown(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).setRowBasePosition(this.shapes.get(shapeIndex).getRowBasePosition()+1);
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;

   }
   
   public boolean moveShapeRight(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).setColumnBasePosition(this.shapes.get(shapeIndex).getColumnBasePosition()+1);
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }
   
   public boolean moveShapeLeft(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.get(shapeIndex).setColumnBasePosition(this.shapes.get(shapeIndex).getColumnBasePosition()-1);
		   this.refreshImage();
		   flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;

   }
   
   public void addGrid()
   {	
	    char integer = '1';
	    char col_int = '1';
	    
		for(int i=0; i < this.rows+2; i++) {
			for(int j=0; j < this.cols+2; j++) {
				
				// first row & last row
				if(j != 0 && j!= this.cols+1) {
					this.cells[0][j] = integer;
					this.cells[this.rows+1][j] = integer;
					
					if(i == this.rows+1) {
						if(integer != '9'){
							integer += 1;
						}else {
							integer = '0';
						}						
					}
				}
				
				// first column & last column
				if(i != 0 && i != this.rows+1) {
					this.cells[i][0] = col_int;
					this.cells[i][this.cols+1] = col_int;		
					
					if(j==this.cols+1) {
						if(col_int != '9'){
							col_int += 1;
						}else {
							col_int = '0';
						}
					}
				}
			}
		}
   }
   
   public void refreshImage() {
  		for(int i=0; i < this.rows+2; i++) {
   			for(int j=0; j < this.cols+2; j++) {
   				if(i != 0 && i != this.rows+1 && j != 0 && j != this.cols+1) {
   					this.cells[i][j] = ' ';	
   				}
   			}
   		}
  		
  		for(int i=0; i<this.shapes.size(); i++) {
  			Shape shape = this.shapes.get(i);
  			shape.draw(this);
  		}
   }
   
   public boolean display()
   {
	   boolean flag = false;
	   try {
		   for(int i=0; i < this.cells.length; i++) {
	  			for(int j=0; j < this.cells[i].length; j++) {
	  				System.out.print(this.cells[i][j] + " ");
	  			}
	  			System.out.println();
	  		}
		   flag = true;
	   }catch(Exception e){
		   flag = false;
	   }
	  return flag;
   }

   public boolean addShape(Shape shape)
   {
	   boolean flag;
	   try {
	   		shape.draw(this);
	   		this.shapes.add(shape);
	   		flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }
   
   public boolean eraseShape(int shapeIndex) {
	   boolean flag;
	   try {
		   this.shapes.remove(shapeIndex);
		   this.refreshImage();
	   		flag = true;
	   }catch(Exception e) {
		   flag = false;
	   }
	   return flag;
   }

   void setCell(int row, int col, char ch)
   {
	   this.cells[row][col] = ch;
   }

   void writeSpecToFile(String filename) {
	   try {
		   FileWriter fileWriter = new FileWriter(filename);
		   
		   for(int i=0; i<this.shapes.size(); i++) {
			   Shape shape = this.shapes.get(i);	
			   this.specText += "\n" + shape.getSpecText();
		   }
		   
		   fileWriter.write(this.specText);
		   fileWriter.close();   
	   }catch(IOException e) {
		   this.isInitialized = false;
		   e.printStackTrace();
	   }	   
   }
   
   Window readSpecFromFile(String fileName) {
	   Window window = new Window();
	   
	   try {
		   BufferedReader reader;
		   FileReader fileReader = new FileReader(fileName);
		   reader = new BufferedReader(fileReader);
				   
		   String line = reader.readLine();
		   int i = 0;
			while (line != null) {
				String[] words = line.split(" ");
				
				// read next line
				line = reader.readLine();

				if(i == 0) {
					// extract row and column from the first line
					int row = Integer.parseInt(words[0]);
					int col = Integer.parseInt(words[1]);
					
					window.setRows(row);
					window.setColumns(col);
				}
				
				if(i == 1) {
					// extract border character from the second line
					char border = words[0].charAt(0);
					window.setBorder(border);
					window.initializeWindow();  // initialize window 2D matrix with borders
				}
				
				if(words[0].equals("line")) { // read line
					int flag = readLineFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
				}
				
				if(words[0].equals("circle")) {// read circle
					int flag = readCircleFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
				}
				
				if(words[0].equals("oval")) { // read oval
					int flag = readOvalFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
				}
				
				if(words[0].equals("rectangle")) {  // read rectangle
					int flag = readRectangleFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
				}
				
				if(words[0].equals("triangle")) { // read triangle
					int flag = readTriangleFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
				}
				
				if(words[0].equals("text")) {  // read text
					int flag = readTextFromTxtfile(line, reader, window, i);
					if(flag != -1) {
						i += flag;
					}else {
						this.isInitialized = false;
					}
					
				}
				i++;
			}
			reader.close();
			this.isInitialized = true;
	   }catch(IOException e) {
		   this.isInitialized = true;
		   System.out.println("Unable to open file.");
	   }	
	   
	   return window;
   }
   
   private int readTextFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, rInc = 0, cInc = 0;
		String text = "";
		char ch = ' ';
		
		int k = 0;
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(k==0) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);							
			}else if(k == 1){
				text = line;
			}else if(k == 2){
				rInc = Integer.parseInt(words[0]);
				cInc = Integer.parseInt(words[1]);	
			}

			k++;
			try {
				line = reader.readLine();
				words = line.split(" ");
				ch = words[0].charAt(0);
				i++;	
			}catch(Exception e) {
				i = -1;
			}
		}

	    Text newText = new Text(row, col, text, rInc, cInc);
		window.addShape(newText);
	   return i;
   }
   
   private int readOvalFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, a = 0, b = 0;
		char ch = ' ', newCH = ' ';
		
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(words.length > 1) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);
				a = Integer.parseInt(words[2]);
				b = Integer.parseInt(words[3]);
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
		
		Oval newOval = new Oval(row, col, a, b, newCH);
		window.addShape(newOval);
	   return i;
   }
   
   private int readRectangleFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, height = 0, width = 0;
		char newCH = ' ';
		char ch = ' ';
		
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(words.length > 1) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);
				height = Integer.parseInt(words[2]);
				width = Integer.parseInt(words[3]);
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
		
		Rectangle newRect = new Rectangle(row, col, height, width, newCH);
		window.addShape(newRect);
	   return i;
   }
   
   private int readTriangleFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, height = 0, rInc = 0, cInc = 0;
		char newCH = ' ';
		char ch = ' ';
		
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(words.length > 1) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);
				height = Integer.parseInt(words[2]);
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

		Triangle newTriangle = new Triangle(row, col, height, rInc, cInc, newCH);
		window.addShape(newTriangle);
	   return i;
   }
   
   private int readCircleFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, rad = 0;
		char ch = ' ', newCH = ' ';
		
		while(ch != '.') {
			String[] words = line.split(" ");
			
			if(words.length > 1) {
				row = Integer.parseInt(words[0]);
				col = Integer.parseInt(words[1]);
				rad = Integer.parseInt(words[2]);
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
		
		Circle newCircle = new Circle(row, col, rad, newCH);
		window.addShape(newCircle);
	   return i;
   }
   
   private int readLineFromTxtfile(String line, BufferedReader reader, Window window, int i) {
	   int row = 0, col = 0, length = 0, rInc = 0, cInc = 0;
		char newCH = ' ';
		char ch = ' ';
		
		try {
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

				line = reader.readLine();
				words = line.split(" ");
				ch = words[0].charAt(0);
				i++;
			}
		}catch(Exception e) {
			i = -1;
		}
		
		Line newLine = new Line(row, col, length, rInc, cInc, newCH);
		window.addShape(newLine);
		return i;
   }
   
   private String getSpecText() {
	   String string = this.rows + " " + this.cols + "\n" + this.border + "\n.";
	   
	   return string;
   }
   
   public void setRows(int rows) {
	   this.rows = rows;
   }
   
   public void setColumns(int cols) {
	   this.cols = cols;
   }
   
   public void setBorder(char ch) {
	   this.border = ch;
   }
   
   private void initializeWindow() {
	   this.shapes = new ArrayList<Shape>();
	   this.cells = new char[this.rows+2][this.cols+2];
	   this.specText = this.getSpecText();
	   addBorders(this.border);
	   addGrid();
	   this.isInitialized = true;
   }
}
