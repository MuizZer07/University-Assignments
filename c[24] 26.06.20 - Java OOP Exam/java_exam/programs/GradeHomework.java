
public class GradeHomework extends Homework{

	private int weight;
	
	GradeHomework(int number, String topic, int weight) {
		super(number, topic);
		this.weight = weight;
	}
	
	GradeHomework(int number, String topic) {
		super(number, topic);
		this.weight = 0;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public String getDetails() {
		return "ID: " + this.getNumber() +", Name: " + this.getTopic() +", Weight: " + this.getWeight(); 
	}
}
