
public class Book {

	private String BookID;
	private double price;
	
	Book(String BookID, double price) throws Exception{
		
		if(BookID.length() >= 6) {
			this.BookID = BookID;
		}else {
			throw new InvalidBookIDException("Book ID must be at least six characters");
		}
		
		
		if(price >= 0) {
			this.price = price;
		}else {
			throw new InvalidBookPriceException("Book price must be a positive double value");
		}
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDetails() {
		return "Book ID: " + this.BookID + ", Price: " + this.price; 
	}
	
}
