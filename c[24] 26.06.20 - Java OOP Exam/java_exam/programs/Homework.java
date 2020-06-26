
public class Homework {
	private int number; 
	private String topic;
	
	public Homework(int number, String topic) { 
		this.number = number; 
		this.topic = topic; 
	}
	
	public int getNumber() { 
		return number; 
	}
	
	public String getTopic() { 
		return topic; 
	}
	
	public String getDetails() { 
		return "number: " + number +", topic: " + topic; 
	}
	
	public String toString() { 
		return getClass().getName() + "[" + getDetails() + "]"; 
	}
}
