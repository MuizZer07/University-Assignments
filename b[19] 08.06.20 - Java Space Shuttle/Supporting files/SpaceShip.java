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
              choice = kb.nextInt( );
              kb.nextLine( );
              process( choice );
         }
    }

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
                   // just trap this choice so that it doesn't appear
                   // as an invalid choice when the user slects it
                   // from the menu
                break;

              default: 
                   System.out.println( choice + " is not a valid choice " );
                break;
         }
    }

    private void addShuttle( )
    {

    }

    public void display( )
    {

    }

    // Since we need to check if there is at least one actual Shuttle object
    // in almost every method, let's write a method that returns 
    // true or false and just call that method, rather than repeat code.
    //
    // Given that we always assign an actual Shuttle object to shuttle1 first,
    // we only need to check shuttle1. This method is complete, you
    // do not have to use this method if you don't want to.
    //
    private boolean hasShuttle( )
    {
         return shuttle1 != null;
    }

    public void addCrew( )
    {

    }

    public void startMission( )
    {
  
    }

    private void endMission( )
    {

    }

    public void load( ) throws IOException
    {

    }
}

