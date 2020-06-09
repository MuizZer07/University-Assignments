/*
 * Class Name:    Shuttle
 *
 * Author:        Your Name
 * Creation Date: Tuesday, April 28 2020, 09:02 
 * Last Modified: Tuesday, April 28 2020, 11:06
 * 
 * Class Description:
 *
 * The Shuttle is the vehicle that has a Crew. You can have a Shuttle
 * without a Crew, but you cannot have a Crew without a Shuttle
 *
 * The Shuttle keeps a record of wether or not it is on a mission.
 *
 * The Shuttle has a unique id, which is alphanumeric and can be more 
 * than one word.
 */

public class Shuttle
{
	private String shuttleID;
	private boolean onMission = false;
	private Crew crew = null;
	
	// overloaded constructor, takes shuttle id, crew name
	Shuttle(String shuttleID, Crew crew){
		if(onMission) {
			this.shuttleID = shuttleID;
			this.crew = crew;
		}else {
			try {
				this.finalize();  // destroys the shuttle instance if it is already on mission by calling deconstructor
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	// overloaded constructor, takes shuttle id, and a boolean that refers the shuttle is on mission or not
	Shuttle(String shuttleID, boolean onMission){
		this.shuttleID = shuttleID;
		this.onMission = onMission;
	}
	
	// add crew to the shuttle
	public void addCrew(String name, String id, int missions) {
		this.setCrew(name, id, missions);
	}
	
	// setter method
	public void setCrew(String name, String id, int missions) {
		Crew crew = new Crew(name, id, missions);
		this.crew = crew;
	}
	
	// getter method
	public Crew getCrew() {
		return this.crew;
	}
	
	// setter method
	public void setOnMission(boolean onMission) {
		this.onMission = onMission;
	}
	
	// getter method
	public boolean getOnMission() {
		return this.onMission;
	}
	
	// getter method
	public String getID() {
		return this.shuttleID;
	}
	
	// start a mission for the shuttle
	public void startMission() {
		if(!this.onMission && shuttleHasCrew()) {  // check the shuttle is already on mission and has a crew
			this.setOnMission(true);
			this.crew.missionIncrement();
		}
	}
	
	// end a mission for the shuttle
	public void endMission() {
		if(this.onMission) {  // check the shuttle is on a mission
			this.setOnMission(false); 
		}
	}
	
	// getter method
	public boolean shuttleOnMission() {
		return this.onMission;
	}
	
	// returns true if the shuttle has any crew assigned
	public boolean shuttleHasCrew() {
		return this.crew != null;
	}
	
	// checks if the assigned crew has the required skills
	public boolean checkForRequiredSkillLevel(String requiredSkillLevel) {
		String[] allSkillLevels = this.crew.getAllSkillLevels();
		boolean hasSkillLevel = false;
		int requiredSkillIndex = getIndexOfSkillLevel(allSkillLevels, requiredSkillLevel);
		
		if(requiredSkillIndex >= 0) {  // checks if required skill is present in the all skill list
			int currentSkillIndex = getIndexOfSkillLevel(allSkillLevels, this.crew.getSkillLevel());
			
			if(requiredSkillIndex <= currentSkillIndex) {  // the crew must have the required skill or above
				hasSkillLevel = true;
			}
		}

		return hasSkillLevel;
	}
	
	// returns the index of a skill level from all skill list
	private int getIndexOfSkillLevel(String[] allSkillLevels, String skillLevel) {
		int index = -1;
		
		for(int i=0; i<allSkillLevels.length; i++) {
			if(allSkillLevels[i].toLowerCase().equals(skillLevel.toLowerCase())) {  // case insensitive matching
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	// overloaded toString() method
	public String toString() {
		String onMissionString = "is not on a mission";
		if(this.onMission) {
			onMissionString = "is on a mission";
		}
		
		String str = "[ Id: " + this.shuttleID + "\n  " + onMissionString  + "\n";
		if(this.shuttleHasCrew()) {
			str += this.crew.toString();
		}else {
			str += "  This shuttle has no Crew assigned\n";
		}
		str += "]";
		
		return str;
	}
}

