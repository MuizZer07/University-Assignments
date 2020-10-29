/**
 * 
 */

/**
 *
 */
public class TestGames {

	/**
	 * @param args
	 */
	public static void main(String[] args){
			final int NO_OF_GAMES = 3 ;
			LuckyGame[] games = new LuckyGame[NO_OF_GAMES];
			TattslottoGame myGame = new TattslottoGame ("Tattslotto", "Saturday", 1 , 45 ) ;
			TattslottoGame OzLotto = new TattslottoGame ( "OZ Lotto", "Tuesday", 1, 45 ) ;
			TattslottoGame WedsLotto = new TattslottoGame ( "Tattslotto", "Wednesday", 1, 40 ) ;
//			Tatts2Game tatts2 = new Tatts2Game ("Tatts2", "everyday", 1 , 99 ) ;
			PowerBallGame powerBall = new PowerBallGame ("PowerBall", "Thursday", 1 , 45 ) ;

			games[0] = myGame ;
			games[1] = OzLotto ;
//			games[2] = WedsLotto ;
//			games[3] = tatts2 ;
			games[2] = powerBall ;
			
			Keyboard keyboard = new Keyboard();

			for (int i = 0 ; i < NO_OF_GAMES ; i++) {
				System.out.println ("\n*******************************************" );
				System.out.print ("Input your numbers for " + games[i].getDay( ) + " " + games[i].getName( ) + " :- " ) ;
				String input = keyboard.readString() ;
				games[i].collectUserInput( input );
				System.out.println ( games[i] ) ;
		}

	}

}
