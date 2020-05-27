
public abstract class Shape
{
	private int rb;				// row position of base point
	private int cb;				// col position of base point
	private char character;		// drawing character

	public Shape(){};

	public Shape(int rb, int cb, char character)
	{
		this.rb = rb;
		this.cb = cb;
		this.character = character;
	}

	// getter
	public int getRowBasePosition(){
		return this.rb;
	}
	
	// getter
	public int getColumnBasePosition(){
		return this.cb;
	}
	
	// getter
	public char getCharacter(){
		return this.character;
	}
	
	// setter
	public void setRowBasePosition(int rb){
		this.rb = rb;
	}
	
	// setter
	public void setColumnBasePosition(int cb){
		this.cb = cb;
	}
	
	// abstract method
	public abstract void draw(Window window);
	
	// abstract method
	public abstract void increaseSize();
	
	// abstract method
	public abstract void decreaseSize();

	// abstract method
	public abstract String getSpecText();
	
	// abstract method
	public abstract String toString();
}