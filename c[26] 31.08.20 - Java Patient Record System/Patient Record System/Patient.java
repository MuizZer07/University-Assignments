/*
This class is just a skeleton. Add your code where necessary. You might need to add additional methods as well.  
*/

import java.io.*;
import java.util.*;

public class Patient
{
	private String id;
	private String name;
	private Observation [] observations;	//size should be initialized to 50
	private int observationCount;  			//Initialize to zero in constructor

	public Patient(String id, String name) //constructor
	{
		this.id = id;
		this.name = name;
		this.observationCount = 0;
		this.observations = new Observation[50];
	}

	//method to add an observation (you can add to the method header if necessary)
	public void addObservation(Observation observation) throws Exception
	{
		int exists = this.checkIfObservationTypeExists(observation.getObservationType().getCode());
		if(exists == -1) {
			this.observations[this.observationCount++] = observation;
		}else {
			throw new Exception("patient already has observation of the type");
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Observation[] getObservations() {
		return observations;
	}

	public void setObservations(Observation[] observations) {
		this.observations = observations;
	}

	public int getObservationCount() {
		return observationCount;
	}

	public void setObservationCount(int observationCount) {
		this.observationCount = observationCount;
	}

	@Override
	public String toString() {
		String str = "Patient ID: " + this.getId() + ", Name:" + this.getName() + ".\nobservationCount: " + this.getObservationCount();
		
		if(this.getObservationCount()>0) 
			str += this.getObservationString();
		
		return str;
	}
	
	private String getObservationString() {
		String str = "";
		
		if(this.observationCount>0)
			str += "\n-------------\n";
		
		for(int i=0; i<this.observationCount; i++) {
			str += "  - " + this.observations[i].getObservationDetails() + "\n";
		}
		
		return str;
	}
	
	public int checkIfObservationTypeExists(String code) {
		for(int i=0; i<this.observationCount; i++) {
			if(this.observations[i].getObservationType().getCode().equals(code)) {
				return i;
			}
		}
		return -1;
	}
}