import java.util.ArrayList;
import java.util.Scanner;

public class DrawingBoard
{
	private static Scanner kb = new Scanner(System.in);
	private static Window w = null;
	private static ArrayList<Shape> shapes = null;
	private static int selectedShapeIndex = -1;

	public static void main(String [] args) throws Exception
   	{
		
		try {
			System.out.println("Enter the window file name (or NEW): ");
			String name = kb.nextLine().trim();
			
			if(name.equalsIgnoreCase("NEW"))
			{
				System.out.println("Enter number of rows, number of columns and character (separated by space): " );
				int rbase = kb.nextInt();
				int cbase = kb.nextInt();
				char ch = kb.nextLine().trim().charAt(0);
				w = new Window(rbase, cbase, ch);
			}
			else
			{
				w = new Window().readSpecFromFile(name);
			}
			
			boolean repeat = w.isInitialized;
			while(repeat)
			{
				System.out.println("\n");
				shapes = new ArrayList<Shape>();
				shapes = w.shapes;
				w.display();

				System.out.println("Add Erase Select Write Quit");
				System.out.println("Up Down Left Right + -");

				String cmd = kb.nextLine();

				if(cmd.length() > 0) {
					switch(cmd.toUpperCase().charAt(0))
					{
						case 'U':
							if(selectedShapeIndex != -1) {
								w.moveShapeUp(selectedShapeIndex);
							}else {
								selectAlert("move up");
							}
							break;
					 	case 'D':
					 		if(selectedShapeIndex != -1) {
						 		w.moveShapeDown(selectedShapeIndex);
					 		}else {
					 			selectAlert("move down");
							}
					 		break;
						case 'L':
							if(selectedShapeIndex != -1) {
								w.moveShapeLeft(selectedShapeIndex);
							}else {
								selectAlert("move left");
							}
							break;
						case 'R':
							if(selectedShapeIndex != -1) {
								w.moveShapeRight(selectedShapeIndex);
							}else {
								selectAlert("move right");
							}
							break;
						case '+':
							if(selectedShapeIndex != -1) {
								w.increaseShapeSize(selectedShapeIndex);
							}else {
								selectAlert("increase size");
							}
							break;
						case '-':
							if(selectedShapeIndex != -1) {
								w.decreaseShapeSize(selectedShapeIndex);
							}else {
								selectAlert("decrease size");
							}
							break;

						case 'S':
							selectShape();
							break;
						case 'A':
							addShape(); 
							break;
						case 'E':
							deleteShape(); 
							break;
						case 'W':
							writeSpecToFile(); 
							break;
						case 'Q':
							repeat = false;
							break;	

						default: System.out.println("Wrong option chosen!");
					}
				}else {
					System.out.println("Please select an option.");
				}
				
			} 

			System.out.println("Thank You!");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private static void selectAlert(String operation) {
		System.out.println("Please select a shape first to " + operation + ". press 's' to select a shape.");
	}

	public static void selectShape()
	{
		if(shapes.size()>0) {
			int index = showOptions();
			if(index != -1) {
				selectedShapeIndex = index;
			}
		}else {
			System.out.println("No shapes in the window");
		}
	}

	private static void showAddSpecs() {
		System.out.println("\nline rowBase colBase length rowIncrement colIncrement character");
		System.out.println("circle rowBase colBase rad character");
		System.out.println("triangle rowBase colBase height rowIncrement colIncrement character");
		System.out.println("rectangle rowBase colBase height width character");
		System.out.println("oval rowBase colBase a b character");
		System.out.println("text rowBase colBase text rowIncrement colIncrement");
	}
	
	public static void addShape()
	{
		showAddSpecs();
		String cmd = kb.nextLine();
		String[] commands = cmd.split(" ");
		
		try {
			switch(commands[0].toLowerCase()) {
			case "line":
				addLine(commands, w);
				break;
			case "circle":
				addCircle(commands, w);
				break;
			case "triangle":
				addTriangle(commands, w);
				break;
			case "rectangle":
				addRectangle(commands, w);
				break;
			case "oval":
				addOval(commands, w);
				break;
			case "text":
				addText(commands, w);
				break;
			default:
				System.out.println("Wrong input format. Follow above instructions.");
			}
		}catch(Exception e) {
			System.out.println("Wrong input format. Follow above instructions.");
		}
	}
	
	private static void addCircle(String[] commands, Window w) {
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);
		int rad = Integer.parseInt(commands[3]);
		char ch = commands[4].charAt(0);
		Circle circle = new Circle(rowBase, colBase, rad, ch);
		w.addShape(circle);
	}
	
	private static void addTriangle(String[] commands, Window w) {
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);
		int height = Integer.parseInt(commands[3]);
		int rowIncrement = Integer.parseInt(commands[4]);
		int colIncrement = Integer.parseInt(commands[5]);
		char ch = commands[6].charAt(0);
		Triangle triangle = new Triangle(rowBase, colBase, height, rowIncrement, colIncrement, ch);
		w.addShape(triangle);
	}
	
	private static void addRectangle(String[] commands, Window w) {
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);
		int height = Integer.parseInt(commands[3]);
		int width = Integer.parseInt(commands[4]);
		char ch = commands[5].charAt(0);
		Rectangle rectangle = new Rectangle(rowBase, colBase, height, width, ch);
		w.addShape(rectangle);
	}
	
	private static void addText(String[] commands, Window w) {
		String text_1 = "";
		int rowIncrement = 0, colIncrement = 0;
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);

		if(commands.length > 6) {
			int i, j = 3;
			int strLength = (commands.length - 6);
			
			for(i=0; i <= strLength; i++) {
				if(i!=0) {
					text_1 += " ";
				}
				text_1 += commands[i+j];
			}
			
			rowIncrement = Integer.parseInt(commands[i+j]);
			colIncrement = Integer.parseInt(commands[i+j+1]);
		}else{
			text_1 = commands[3];
			rowIncrement = Integer.parseInt(commands[4]);
			colIncrement = Integer.parseInt(commands[5]);
		}
		System.out.println(text_1);
		Text text = new Text(rowBase, colBase, text_1, rowIncrement, colIncrement);
		w.addShape(text);
	}
	
	private static void addLine(String[] commands, Window w) {
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);
		int length = Integer.parseInt(commands[3]);
		int rowIncrement = Integer.parseInt(commands[4]);
		int colIncrement = Integer.parseInt(commands[5]);
		char ch = commands[6].charAt(0);
		Line line = new Line(rowBase, colBase, length, rowIncrement, colIncrement, ch);
		w.addShape(line);
	}

	private static void addOval(String[] commands, Window w) {
		int rowBase = Integer.parseInt(commands[1]);
		int colBase = Integer.parseInt(commands[2]);
		int a = Integer.parseInt(commands[3]);
		int b = Integer.parseInt(commands[4]);
		char ch = commands[5].charAt(0);
		Oval oval = new Oval(rowBase, colBase, a, b, ch);
		w.addShape(oval);
	}
	
	public static void deleteShape()
	{
		if(shapes.size()>0) {
			int index = showOptions();
			if(index != -1) {
				w.eraseShape(index);
			}
		}else {
			System.out.println("No shapes in the window");
		}
	}

	public static int showOptions() {
		for(int i=0; i<shapes.size(); i++) {
			Shape shape = shapes.get(i);
			System.out.println(i + ": " + shape.toString());
		}
		int index = -1;
		try {
			index = Integer.parseInt(kb.nextLine());
			if(index < -1 || index > shapes.size()) {
				index = -1;
				System.out.println("Please provide a correct index.");
			}
		}catch(Exception e) {
			System.out.println("Please provide a correct index.");
		}
		
		return index;
	}
	
	public static void writeSpecToFile()
	{
		System.out.print("File name: ");
		String filename = kb.nextLine();
		w.writeSpecToFile(filename);
		if(w.isInitialized) {
			System.out.println("Write to File Successful!");
		}else {
			System.out.println("Failed to Write!");
		}
	}

}

