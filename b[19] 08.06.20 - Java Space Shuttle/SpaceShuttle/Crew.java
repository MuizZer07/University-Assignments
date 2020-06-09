/*
 * Class Name:    Crew
 *
 * Author:        Your Name
 * Creation Date: Tuesday, April 28 2020, 11:12 
 * Last Modified: Tuesday, April 28 2020, 12:02
 * 
 * Class Description:
 *
 * This is the class for a Crew. A Crew has a number of levels for 
 * tasks that they can perform. The tasks are based on the number
 * of missions that the Crew has flown.
 *
 * Every Crew, when they first start has a task level of 0, as they
 * have just started.
 *
 * Each mission counts as one task.
 *
 * There is a fairly long learning curve. For this reason, a Crew needs
 * to have completed 6 missions before they move to the next task level.
 *
 * The next task level occurs up to 8 missions.
 *
 * The next task level is then up to and including 14 missions, 
 * which means that the final and top level occurs at 15 missions and
 * greater.
 *
 * Crew have a unique id, the id is a mix of characters and numbers
 * and can be more than one word
 *
 */

public class Crew
{
	private String crewID;
	private String name;
	private int missions;
	private String skillLevel;
	private String[] allSkillLevels = {"Novice", "Intermediate", "Advanced", "Expert"};
	
	// overloaded constructor, takes crew name, id, number of missions
	Crew(String name, String crewID, int missions){
		this.name = name;
		this.crewID = crewID;
		this.setMissions(missions);
	}
	
	// overloaded constructor, takes crew name, id
	Crew(String name, String crewID){
		this.name = name;
		this.crewID = crewID;
		this.setMissions(0);
	}
	
	// increments number of missions by 1
	public void missionIncrement() {
		this.missions++;
		setSkillLevel();  // check and set skill level according to incremented number of missions
	}
	
	// setter method
	public void setName(String name){
		this.name = name;
	}
	
	// setter method
	public void setCrewID(String crewID) {
		this.crewID = crewID;
	}
	
	// getter method
	public String getCrewID() {
		return this.crewID;
	}
	
	// getter method
	public String getSkillLevel() {
		return this.skillLevel;
	}
	
	// setter method
	public void setMissions(int missions) {
		this.missions = missions;
		this.setSkillLevel();  // check and set skill level according to number of missions
	}
	
	// returns all skill levels, array of strings
	public String[] getAllSkillLevels() {
		return this.allSkillLevels;
	}
	
	// check and set skill level according to number of missions
	private void setSkillLevel() {
		if(this.missions >= 0 && this.missions <= 6) {
			this.skillLevel = this.allSkillLevels[0];
		}
		else if(this.missions >= 7 && this.missions <= 8) {
			this.skillLevel = this.allSkillLevels[1];
		}
		else if(this.missions >= 9 && this.missions <= 14) {
			this.skillLevel = this.allSkillLevels[2];
		}
		else if(this.missions >= 15) {
			this.skillLevel = this.allSkillLevels[3];
		}
	}
	
	// overloaded toString() method
	public String toString() {
		String str = " Crew \n   [ name: " + this.name + " id: " + this.crewID 
				+ "\n     Missions: " + this.missions + " level: " + this.skillLevel + "\n   ]\n";
		return str;
	}
}

