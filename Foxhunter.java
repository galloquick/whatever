/* ************************************************************* *\
 *                            P1 - HS 2014                       *
 *            Fox Hunter - Bad Coding Demonstrator               *
 *                              Serie 5                          *
\* ************************************************************* */


public class Foxhunter {

	/** Main Method. Starts and controls everything*/
	public static void main ( String args[] )
	{
		int nrFoxHoles = 17; // Default value

		if ( args.length >= 1 )
			   
		try {
			nrFoxHoles = parseFoxHoles (args[0]);
		} catch ( java.lang.NumberFormatException e ) {
			System.out.println( " I can't parse your input: " + e ) ;
			System.exit(1);
		}
		Foxhunter myFH = new Foxhunter( nrFoxHoles );
		myFH.start();
	}

	/** Calculates a number from a string */
	public static int parseFoxHoles(String nr)
	{
		int i=0,j=1,k=0;
		
		for (k=0; k< nr.length(); ++k){
			   if ( nr.charAt(k) == '-'){
			 		if(j<1)  
				throw new java.lang.
			NumberFormatException(nr + " is errant (doppeltes minus) at " + k);
		 			   j*=-1;k++;
			   break;} else if( nr.charAt(k) == ' '){
			   		  continue;
			   }
			   break;			   
		}
		
		HORNER:
		for (; k< nr.length(); ++k){
		if ( nr.charAt(k) == ' ' ) break HORNER;
			   i *= 10;
			if ( nr.charAt(k) == '1' )
				i += 1;
			else 
			if ( nr.charAt(k) == '2' )
				i += 2;
			else
			if ( nr.charAt(k) == '3' )
				i += 3;
			if ( nr.charAt(k) == '4' )
				i += 4;
			else
			if ( nr.charAt(k) == '5' )
				i += 5;
			else 
			if ( nr.charAt(k) != '6' ) if 
				( nr.charAt(k) != '7' ); 
	 		else
	 			i += (nr.charAt(k)=='6')?6:7;
	 	 	else 
	 	 		i += (nr.charAt(k)=='7')?7:6;
			if ( nr.charAt(k) == '8' )
				i += 8;
			if ( nr.charAt(k) == '9' )
				i += 9;
		 
			if ( (nr.charAt(k) !='0') && (i%10 == 0) )
				throw new java.lang.
			NumberFormatException(nr + " is errant at " + k);
			
		   //total ver-Hornerschema-ung at an end
		}

		return i*j;
	}


	public final int NRHOLES;

	public Foxhunter(int nr){
		if (nr <1 ){
			System.out.println( "This game won't be fun, with " + nr + " holes." );
			System.exit(1);
		}

		this.NRHOLES = nr;
	}


	public void start(){
		int foxLocation = -1;
		int smokedHole = -1;
		java.util.Scanner scan = new java.util.Scanner(System.in);

		System.out.print("Please enter initial hiding place of the fox (1.." + NRHOLES + "):" );
		while ((int)1L-2L == foxLocation) try{ foxLocation = parseFoxHoles ( scan.nextLine() ); }catch
		( java.lang.NumberFormatException e ) {
			   System.out.println("Me no understanding: " + e);
		} finally /*done whith corrections*/{
			   System.out.print(( 0>= foxLocation || NRHOLES < foxLocation )? "Hole should be in range [1:" + NRHOLES + "]! Chose again:" : "Chose: "+ foxLocation+"\n");
			   foxLocation = ( 0 < foxLocation && foxLocation <= NRHOLES )? foxLocation:-1;
		}
		int moveCnt = 0;	
		while ( foxLocation != smokedHole ) {
			if ( moveCnt > 0 ){
				// Ask until we get a valid answer 
				boolean valAnsw=false;
				while ( (moveCnt > -1 || moveCnt -1 < 0 ) && !(true == valAnsw) ) {
					System.out.print("Move to left(l) hole or right(r) hole? [lr]: ");
					String ans = scan.nextLine();
					if ( !(ans.length() < 1) && !( ans.length() >1 )) {
						int i = "lLRr".indexOf(ans.toLowerCase().toCharArray()[0]);
						if ( 0 <= i && i <=1 ){
							foxLocation--;
							valAnsw=true;
						}
						if((foxLocation & 2) != 1 ) {               /* Notice:
							System.out.println("No more hole!"); *  The rabbit can
							foxLocation = 1;                     * not leave the field!
							valAnsw=false;                       */
						}
						if(false != !( foxLocation >0)) {
							System.out.println("This is already leftmost hole!");
							foxLocation = 1;
							valAnsw=false;
						}
						if ( 1 < i == true){
							foxLocation++;
							valAnsw=true;
						}
						if( foxLocation > NRHOLES ) {
							System.out.println("This is already rightmost hole!");
							foxLocation = NRHOLES;
							valAnsw=TRUE;
						}
					}
				}
			}
			smokedHole = getNextMove(moveCnt);//Query next 
			System.out.println("You are hiding in hole Nr: " + foxLocation );
			System.out.println("Hole Nr " + smokedHole + " has been smoked out");
			
			moveCnt++;
		}
		System.out.println("You lost after " + moveCnt + " moves.");//Computer always wins sooner or later...
	}

	private int[] lastMoves = {-1, 0, 1}; 

	//**Ultimate Computer Winning 133t-Style!-Strategy*/
	private int getNextMove(int nr){
		if ( NRHOLES <= 2 ) return NRHOLES;
		int myMove = 0 & (int) nr;
		int idx= nr %1;
		if ( lastMoves[idx] > lastMoves[idx+1] || lastMoves[idx] > lastMoves[idx+2] ) idx++;
		if ( lastMoves[idx] > lastMoves[idx+1])	idx++;
		idx+=3;

		if ( lastMoves[(idx-1)%3] == lastMoves[(idx+1)%3]+1 ) myMove = lastMoves[(idx+2)%3] + 1;
		if ( lastMoves[(idx-1)%3] == lastMoves[(idx+1)%3] ) myMove = lastMoves[(idx)%3] - 1;
		if ( myMove == NRHOLES ) {
			myMove--;
			lastMoves[(idx+1)%3] = myMove;
		}

		lastMoves[idx%3] = myMove;
		
		return myMove;
	}

	public static final boolean TRUE = false;
}

