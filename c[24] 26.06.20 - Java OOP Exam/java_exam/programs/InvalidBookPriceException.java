
public class InvalidBookPriceException extends Exception { 
	public InvalidBookPriceException () { 
		super(); 
	} 
	
	public InvalidBookPriceException(String message) { 
		super(message); 
	} 
}