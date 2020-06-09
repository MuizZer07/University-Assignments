/*
 * Class Name:    SpaceShip
 *
 * Author:        Your Name
 * Creation Date: Tuesday, April 28 2020, 10:00 
 * Last Modified: Tuesday, April 28 2020, 11:02
 * 
 * Class Description:
 *
 * This is the main ("Driver") class for OOF assignment C
 * Semester 1 2020
 *
 *
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SpaceShip
{
    private Scanner kb;
    private Shuttle shuttle1;
    private Shuttle shuttle2;

    private final int LOAD_FROM_FILE = 1;
    private final int ADD_SHUTTLE = 2;
    private final int START_MISSION = 3;
    private final int END_MISSION = 4;
    private final int DISPLAY = 5;
    private final int ADD_CREW = 6;
    private final int EXIT = 7;

    public static void main( String [ ] args ) throws IOException
    {
         SpaceShip sp = new SpaceShip( );
         sp.run( );
    }

    public SpaceShip( )
    {
         kb = new Scanner( System.in );
         shuttle1 = null;
         shuttle2 = null;
    }

    public void run( ) throws IOException
    {
         int choice = -1;
         while( choice != EXIT )
         {
              displayMenu( );
              System.out.print("Enter choice >> " );
              
              String line = kb.nextLine( );
              try {
            	  choice = Integer.parseInt(line);
                  process( choice );
              }catch(Exception e) {
            	  System.out.println("Invalid input.");  // takes only integer values for menu input
              }
         }
    }

    // displays all menu items and options
    void displayMenu( )
    {
         System.out.println("\nISC Planetary Explorer Main menu" );
         System.out.println("\t" + LOAD_FROM_FILE + ". Load from file " );
         System.out.println("\t" + ADD_SHUTTLE + ". Add Shuttle " );
         System.out.println("\t" + START_MISSION + ". Start Mission" );
         System.out.println("\t" + END_MISSION + ". End Mission" );
         System.out.println("\t" + DISPLAY + ". Display" );
         System.out.println("\t" + ADD_CREW + ". Add Crew" );
         System.out.println("\t" + EXIT + ". Close the program" );
    }

    // selects menu based on user input
    void process( int choice ) throws IOException
    {
         switch( choice )
         {
              case LOAD_FROM_FILE :
                   load( );  
                break;

              case ADD_SHUTTLE :
                   addShuttle( );
                break;

              case START_MISSION :
                   startMission( );
                break;

              case END_MISSION :
                   endMission( );
                break;

              case DISPLAY :
                   display( );
                break;

              case ADD_CREW :
                   addCrew( );
                break;
              case EXIT :
                break;

              default: 
                   System.out.println( choice + " is not a valid choice " );
                break;
         }
    }

    // add a new shuttle to the space ship
    private void addShuttle( )
    {
    	int shuttle_number = getAvailableShuttle();  // available shuttle number
    	
    	if(shuttle_number == -1) {  // -1 if no shuttle is available, 2 of them are already added 
    		System.out.println("\nAlready at maximum Shuttle capacity cannot add any more Shuttles");
    	}else {
    		addShutteByUserInput(shuttle_number);  // adds new shuttle
    	}
    }

    // displays all the information of the spaceship including shuttles and crews
    public void display( )
    {
    	if(hasShuttle()) {  // checks if there is any shuttle available
        	System.out.println("\nHere are the Shuttle details\n");
        	
        	if(isShuttleInitiated(shuttle1))  // checks if shuttle 1 is available
        		System.out.println("Shuttle\n" + shuttle1.toString());  // prints shuttle information
        	
        	if(isShuttleInitiated(shuttle2))  // checks if shuttle 2 is available
           		System.out.println("Shuttle\n" + shuttle2.toString());  // prints shuttle information
    	
    	}else {
    		System.out.println("\nThere are no Shuttles to display\nAdd at one least one Shuttle\n");
    	}
    }

    // returns true if there is at least only one shuttle available
    private boolean hasShuttle( )
    {
         return shuttle1 != null;
    }

    // adds new crew 
    public void addCrew( )
    {
    	if(!hasShuttle()) {  // checks if there is at least one shuttle available
    		System.out.println("\nThere are no Shuttles!\nCannot add Crew until there is at least one Crew" );
    		return;
    	}
    	
    	System.out.print("Enter Shuttle id >> " );  // asks user for shuttle id
        String shuttle_id = kb.nextLine( );
    	int shuttle_number = checkForUniqueShuttleID(shuttle_id);  // checks the entered shuttle id is present 
    	
    	switch(shuttle_number) {
	    	case 0:  // shuttle number 0 means entered id has not matched to any present shuttles
	    		System.out.println("\nThis shuttle does not have a Crew so cannot start a mission" );
	    		break;
	    	case 1:
	    		addCrewByChecking(shuttle1);  // adds crew to shuttle 1
	    		break;
	    	case 2:
	    		addCrewByChecking(shuttle2);  // adds crew to shuttle 2
	    		break;
    	}
    }

    // starts a mission for a specific shuttle
    public void startMission()
    {
    	int shuttle_number = getMissionInput("start");  // receives shuttle id and returns the shuttle number
    	
    	switch(shuttle_number) {
	    	case 0:  // shuttle number 0 means entered id has not matched to any present shuttles
	    		System.out.println("\nNo shuttle with that id was found" );
	    		break;
	    	case 1:
	    		startMissionWithChecking(shuttle1);  // starts mission for shuttle 1
	    		break;
	    	case 2:
	    		startMissionWithChecking(shuttle2);  // starts mission for shuttle 2
	    		break;
    	}
    }

    // ends a mission for a specific shuttle
    private void endMission( )
    {
    	int shuttle_number = getMissionInput("end");  // receives shuttle id and returns the shuttle number
    	
    	switch(shuttle_number) {
	    	case 0:
	    		System.out.print("No shuttle with that id was found" );
	    		break;
	    	case 1:
	    		endMissionWithChecking(shuttle1);  // ends mission for shuttle 1
	    		break;
	    	case 2:
	    		endMissionWithChecking(shuttle2);  // ends mission for shuttle 2
	    		break;
    	}
    }

    // adds crew by receiving user inputs and condition checking
    private void addCrewByChecking(Shuttle shuttle) {
    	
		if(shuttle.shuttleHasCrew()) {  // checks the shuttle has any crew, cannot add if there is already one
			System.out.println("\nThis shuttle already has a Crew, cannot add Crew." );
		}else {
			System.out.print("Enter Crew id >> " );  // user input for new crew id
	        String crew_id = kb.nextLine( );
	        boolean unique = checkForUniqueCrewID(crew_id);  // checks if the crew id is unique
	        
	        if(unique) {
	        	System.out.print("Enter Crew Name >> " );  // user input for new crew name
	        	String crew_name = kb.nextLine( );
	        	
	        	shuttle.addCrew(crew_name, crew_id, 0);  // adds to the shuttle
	        }else {
	        	System.out.println("\nThis crew id is already in use.\nCrew id's must be unique" );
	        }
		}
    }
    
    // receives user input for a mission, returns shuttle number of the shuttle to start or end mission
    private int getMissionInput(String end_or_start) {
    	if(!hasShuttle()) {  // checks if there is any shuttle available
    		System.out.println("\nCannot " + end_or_start + " mission as there are no Shuttles.\nAdd at least one Shuttle" );
    		return -1;
    	}
    	System.out.print("Enter Shuttle id >> " );
        String shuttle_id = kb.nextLine( );   
    	int shuttle_number = checkForUniqueShuttleID(shuttle_id);  // checks and returns the shuttle by id
    	return shuttle_number;
    }
    
    // ends a mission by user inputs and condition checking
    private void endMissionWithChecking(Shuttle shuttle) {
    	if(shuttle.getOnMission()) {  // checks the shuttle is on mission
    		shuttle.endMission();
    	}else {
    		System.out.println("\nThe shuttle with id "+ shuttle.getID() +" is not on a mission, so cannot end mission " );
    	}
    }
    
    // starts a mission by user inputs and condition checking
    private void startMissionWithChecking(Shuttle shuttle) {
    	if(!shuttle.shuttleHasCrew()) {  // there must be a crew to start a mission
    		System.out.println("\nThis shuttle does not have a Crew so cannot start a mission" );
    		return;
    	}
    	
    	if(shuttle.getOnMission()) {  // checks if the shuttle is already on a mission
    		System.out.println("\nThe Shuttle is already on a mission." );
    		return;
    	}
    	
    	System.out.print("Enter required skill level >> " );
        String requiredSkillLevel = kb.nextLine( );
    	
        if(shuttle.checkForRequiredSkillLevel(requiredSkillLevel)) {  // checks if the crew has the required skill level
        	shuttle.startMission();  // starts the mission
        }else {
        	System.out.println("\nThe Crew of this Shuttle does not have required level of expertise  to carry out this mission." );
        }
    }
    
    // receives user input for a adding a shuttle
    private void addShutteByUserInput(int shuttle_number) {
    	System.out.print("Enter Shuttle id >> " );
        String shuttle_id = kb.nextLine( );
        int unique = 0;
        
        if(shuttle_number == 2) {  // check if already a shuttle is available 
        	unique = checkForUniqueShuttleID(shuttle_id);
        }

        if(unique < 1) {  // initiate the shuttle if only the id is unique
        	initiateShuttle(shuttle_number, shuttle_id, false);
        }else {
        	System.out.println("\nThe requested shuttle id is already in use. "
        			+ "Shuttle id's must be unique. Rerun the menu choice "
        			+ "with valid shuttle id");
        }
    }

    // checks a shuttle id is already available or not
    private int checkForUniqueShuttleID(String shuttle_id) {
    	
    	if(isShuttleInitiated(shuttle1)) {
    		if(shuttle1.getID().toLowerCase().equals(shuttle_id.toLowerCase())) {
    			return 1;  // if shuttle id matches with shuttle 1
    		}
    	}

    	if(isShuttleInitiated(shuttle2)) {
        	if(shuttle2.getID().toLowerCase().equals(shuttle_id.toLowerCase()))
        		return 2;   // if shuttle id matches with shuttle 2
    	}
    	
    	return 0;   // if shuttle id doesn't match
    }
    
    
    // checks a crew id is already available or not, returns only true and false
    private boolean checkForUniqueCrewID(String crew_id) {
    	
    	if(isShuttleInitiated(shuttle1)) {
    		if(shuttle1.shuttleHasCrew()) {
        		if(shuttle1.getCrew().getCrewID().toLowerCase().equals(crew_id.toLowerCase())) {
        			return false;
        		}
    		}
    	}

    	if(isShuttleInitiated(shuttle2)) {
    		if(shuttle2.shuttleHasCrew()) {
            	if(shuttle2.getCrew().getCrewID().toLowerCase().equals(crew_id.toLowerCase()))
            		return false;
    		}
    	}
    	
    	return true;
    }

    // loads shuttle and crew information
    
    // loads shuttle and crew information from file
    public void load( )
    {
    	int shuttle_number = getAvailableShuttle(); // get the available shuttle number
    	
    	if(shuttle_number == -1) {  // -1 if no shuttle is available
    		System.out.println("\nAll Shuttles have been allocated");
    	}else {
    		try {
        		System.out.print("Enter file name >> ");  // user input for file name
            	String filename = kb.nextLine( );
        	    File file = new File(filename);
        	    Scanner reader = new Scanner(file);
        	    
        	    int i = 0;
        	    int currentShuttle = shuttle_number;
        	    String shuttleID = "";
        	    boolean shuttleOnMission = false;
        	    boolean shuttlehasCrew = false;
        	    String crewName = "";
        	    String crewID = "";
        	    int crewMissions = 0;
        	    boolean initiated = false;
        	    
        	    while (reader.hasNextLine()) {  // reading file line by line
        	        String line = reader.nextLine();	
        	        
        	        if(i == 0) {
        	        	shuttleID = line;
        	        }else if(i == 1) {
        	        	shuttleOnMission = getBooleanValue(line);
        	        }else if(i == 2) {
        	        	shuttlehasCrew = getBooleanValue(line);
        	        	initiated = initiateShuttle(currentShuttle, shuttleID, shuttleOnMission);  // initiates shuttle
     
        	        	if(!shuttlehasCrew) {
        	        		i = -1;
        	        		currentShuttle = 2;
        	        	}
        	        }else if(i == 3) {
        	        	crewName = line;
        	        }else if(i == 4) {
        	        	crewID  = line;
        	        }
        	        else if(i == 5) {
        	        	crewMissions = Integer.parseInt(line);
    	        		if(shuttlehasCrew && initiated) {
    	        			if(currentShuttle == 1 && checkForUniqueCrewID(crewID)) {  // checks for unique crew id
    	        				shuttle1.addCrew(crewName, crewID, crewMissions);
    	        			}else if(currentShuttle == 2 && checkForUniqueCrewID(crewID)){   // checks for unique crew id
    	        				shuttle2.addCrew(crewName, crewID, crewMissions);
    	        			}
    	        		}
    	        		i = -1;
    	        		currentShuttle = 2;
        	        }
        	        
        	        i++;
        	    }
        	    
        	    reader.close();
        	}catch(Exception e) {
        		System.out.println(e);
        		System.out.println("Error in Reading file");
        	}
    	}
    }
    
    
    // returns the available shuttle number, -1 if no shuttle is available
    private int getAvailableShuttle() {
    	int shuttle_number = -1;
    	
    	if(!isShuttleInitiated(shuttle1))
    		return 1;
    	
    	if(!isShuttleInitiated(shuttle2))
    		return 2;
    	
    	return shuttle_number;
    }
    
    
    // initiate a shuttle by shuttle number, checks for unique shuttle id
    private boolean initiateShuttle(int currentShuttle, String shuttleID, boolean shuttleOnMission) {
    	boolean initiated = false;
    	if(currentShuttle == 1) {
        	if(!isShuttleInitiated(shuttle1) && checkForUniqueShuttleID(shuttleID) == 0) {
        		shuttle1 = new Shuttle(shuttleID, shuttleOnMission);
        		initiated = true;
        	}
    	}else if(currentShuttle == 2) {
    		if(!isShuttleInitiated(shuttle2) && checkForUniqueShuttleID(shuttleID) == 0) {
    			shuttle2 = new Shuttle(shuttleID, shuttleOnMission);
    			initiated = true;
        	}
    	}
    	return initiated;
    }
    
    
    // returns a shuttle is initiated or not
    private boolean isShuttleInitiated(Shuttle shuttle) {
    	return shuttle != null;
    }
    
    
    // returns boolean value for string
    private boolean getBooleanValue(String str) {
    	return str.equals("true");
    }
}

