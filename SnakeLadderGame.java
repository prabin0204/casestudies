import java.io.*; // used to allow user to input data
import java.util.Random;

/**
 * 
 * @author C0-HR-01
 * @since 19-08-2017
 */
public class SnakeLadderGame
{// start class
    
    
    
    //Main Method
    public static void main (String [] args) throws IOException
    {// start main method
        
        //Welcoming Screen
        
        BufferedReader myInput2 = new BufferedReader (new InputStreamReader (System.in));
        // Print the welcome screen and instructions
        System.out.println ("\t\t\t\t\t\tWelcome To Snakes And Ladders\n\n");
        System.out.println ("\t\t\t\t\t\t\tInstructions:");
        System.out.println ("\t\t\t This game will have snakes, ladders, trampoline, spring or pitstop. Player has to use one unit of energy to throw the dice");
        System.out.println ("\t\t\t In case of trampoline, player has to go forward 'm' steps where m is the dice value");
        System.out.println ("\t\t\t In case of spring, player has to go backward 'm' steps where m is the dice value");
        System.out.println ("\t\t\t In case of pitstop , player will be given energy which he can use to throw dice");

        
        
        int counter= 100,iteration=-1; // sets the counter and iteration vaiables to be used in making the board
        System.out.println ("-----------------------------------------------------Game Board-----------------------------------------------------------------------------");
        
        /*
        *this is to create game from 1 to 100.
        */
        while (counter > 0){// start while
            if (counter%10 == 0 && counter != 100){
                if(iteration==-1)
                {
                 
                    counter-=9;
                    iteration=1;
                }
                else
                {
                    System.out.print(counter+"\t");
                    counter-=10;
                    iteration=-1; 
                }
                if(counter!=0)
                System.out.print("\n" + counter + "\t"); 
            }
            else
            System.out.print(counter + "\t"); //prints out the counter
            counter+=iteration; // sets the counter by iteration
        }// end while
        System.out.println();
        System.out.println ("----------------------------------------------------------------------------------------------------------------------------------");
        
        
        
        
        String sGame = "y"; // decare variable used to ask user if he wants to play
        
        System.out.print ("Do you want to play? Y or N     >  "); // ask user if we wants to play the game
        sGame = myInput2.readLine (); // reads the user's input into the variable sGame
        System.out.print ("\n\n");
        
        while (sGame.equals ("y") || sGame.equals ("Y"))
        {
            sGame = startGame(sGame);
        }
        System.out.println ("\n\n\t\t Exiting from the game..");
        
        
    } //end main method
    
    
    
    
    
    
    /*
     * this method starts the game and throws the dice and update the player position
    */
    public static String startGame (String start) throws IOException 
    {
        
        BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));
        
        
        
        int userPosition = 1; // sets the player position to 1
        int compPosition = 1; // sets the default loaction for other player(computer) to 1
        int diceRoll = 0; // creates the first die
        int userRoll = 1; // declares what the user rolled
        int compRoll = 1; // declares what the computer rolled
        int userEnergy = 33;
        int compEnergy = 33;
        String playAgain = "y"; // sets the play again variable
        
        // declare all the snakes and ladders, trampoline, springs and pitstops in an array
        int snakesLaddersArray [] = {20, 99, 32, 48, 65, 12, 24, 73, 82, 78 }; // create a 10 element array
        
        while (playAgain.equals ("y") || playAgain.equals ("Y")) // loops while the playAgaim vaiable equals "y" or "Y".
        {// start playAgain While
            
            
            // gets the dice roll for user and computer
            userRoll =  getDice(diceRoll); // sends data to a function type method called getDice
            compRoll =  getDice(diceRoll); // does the same for the computer
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            System.out.println ("\t\t\t\t\t------------------------------------------");
            System.out.println ("\t\t\t\t\t|\tYou Rolled a " + userRoll + "\t\t|"); // print the roll the user got
            System.out.println ("\t\t\t\t\t|\tThe Computer Rolled a " + compRoll + "\t|"); // print the roll the computer got
            System.out.println ("\t\t\t\t\t------------------------------------------");
            
            // hold the user's last position for switching back if current position was greater than 100
            userPosition = userPosition + userRoll;
            userEnergy--;
            // hold the computer's last position for switching back if current position was greater than 100
            compPosition = compPosition + compRoll;
            compEnergy--;
            if(userEnergy==0){
            	userPosition=1;
            }
            if(compEnergy==0){
            	compPosition=1;
            }
            
            
            
            // check to see if user landed on top of a snake or at the bottom of a ladder or landed on a trampoline/ spring/ pitstop
            // give getPosition parameters to work with, and recieve a final value which can be printed out
            userPosition = getPosition(userPosition, userRoll,userEnergy, snakesLaddersArray);
            // The same goes for compPosition, however getCompPosition gets an additional
            // parameter (userPosition) to check if user has already won
            compPosition = getCompPosition(compPosition, compRoll,compEnergy, snakesLaddersArray, userPosition);
            
            System.out.println("\t\t\t*************************************************************************");
            System.out.println ("\t\t\t*\t\tYou are currently on square " + userPosition + " with energy "+userEnergy+" \t\t\t*"); // print out the user's current location
            System.out.println ("\t\t\t*\t\tThe Computer is currently on square " + compPosition + " with energy "+compEnergy+"\t\t*"); // print out the computer's current location
            System.out.println("\t\t\t*************************************************************************");
            
            // resets the position values, if the user or the computer won
            // so that the user could play the entire game again if he/she wanted to
            if (userPosition == 100 || compPosition == 100)
            {
                userPosition = 1;
                compPosition = 1;
                // asks the user if we wants to play again
                System.out.print ("Do you want to play? Y or N     >  ");
                playAgain = myInput.readLine ();
                System.out.print ("\n\n\n\n\n\n\n\n\n\n\n\n");
            }
            else
            {
                // asks the user if we wants to continue playing
                System.out.print ("Do you want to play? Y or N     >  ");
                playAgain = myInput.readLine ();
                
            }
            
            
        }// end playAgain While
        
        return playAgain; // returns prameter "playAgain" to main: if the user wants to play the game again
    }// end startGame method
    
    
    
    
    
    
   /**
    * this method returns a dice rolled value 
    * @param diceRoll
    * @return
    */
    public static int getDice (int diceRoll)
    {
    	//Random r = new Random();
    	//return r.nextInt((6 - 1) + 1) + 1;
        diceRoll = (int)(Math.random()*6 )+1 ; //creates dice roll number 1
        //diceRoll2 = (int)(Math.random()*6 )+1 ; //creates dice roll number 2
        return diceRoll ; // this will return the rolled dice value
    }
    
    
    
    
   /**
    * this method will set the position of player
    * @param userPosition
    * @param userRoll
 * @param userEnergy 
    * @param snakesLaddersArray
    * @return
    * @throws IOException
    */
    public static int getPosition (int userPosition, int userRoll, int userEnergy, int snakesLaddersArray []) throws IOException // recieves two parameter from startGame
    {
        
        //20, 99, 32, 48, 65, 12, 24, 73, 49, 78 
        if(userPosition == snakesLaddersArray[0]) //if position equals snake 1
        {
            userPosition = 2; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake, GO DOWN!!!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[1]) //if position equals snake 2
        {
            userPosition = 47; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got Bit By A Snake, GO DOWN!!!~~~~~~~~~~~~~");
            
        }
        else if (userPosition == snakesLaddersArray[2]) //if position equals lader 1
        {
            userPosition = 77; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder, Go UP!!!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[3]) //if position equals ladder 2
        {
            userPosition = 75; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Ladder!! GO UP!!!~~~~~~~~~~~~~");
            
        }
        else if (userPosition == snakesLaddersArray[4]) //if position equals trampoline 1
        {
            userPosition =userPosition+ userRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Trampoline!! Move ahead some steps!!!~~~~~~~~~~~~~");
            
        }
        else if (userPosition == snakesLaddersArray[5]) //if position equals trampoline 2
        {
            userPosition =userPosition+ userRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Trampoline!! Move ahead some steps!!!~~~~~~~~~~~~~");
            
        }
        else if (userPosition == snakesLaddersArray[6]) //if position equals spring 1
        {
            
            
            userPosition =userPosition- userRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Spring!! GO back some steps!!!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[7]) //if position equals spring 2
        {
            
            
            userPosition =userPosition- userRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A Spring!! GO back some steps!!!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[8]) //if position equals pitstop 1
        {
            
            
            userEnergy++; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A pitstop!! Energy increased!!!~~~~~~~~~~~~~");
        }
        else if (userPosition == snakesLaddersArray[9]) //if position equals pitstop 2
        {
            
            
            userEnergy++; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~You Got A pitstop!! Energy increased!!!~~~~~~~~~~~~~");
        }
        
        if (userPosition < 0 || userPosition > 112) // This is ab ERROR TRAP to catch any unwanted system errors that may occur by chance
        {
            System.out.println ("An error has occured please reset the game!!!!!!");
        }
        
        else if (userPosition > 100) // checks if user's location if greater then a 100
        {
            userPosition = userPosition - userRoll; // subtract userRoll from the userposition to get back old position
            System.out.println ("OHHH You cant jump, you must land on a 100"); // print out the users current location
            
        }
        else if (userPosition == 100)
        {
            System.out.println ("YOU WON, Great Job!!!");
            
        }
        
        
        
        return userPosition; // return the final position to starGame: this position had gone through all checks and test and can be displayed on screen
    }// end getP
    
    
    
    
    
    
    /**
     * this method sets the position of computer
     * @param compPosition
     * @param compRoll
     * @param compEnergy 
     * @param snakesLaddersArray
     * @param userPosition
     * @return
     * @throws IOException
     */
    
    public static int getCompPosition (int compPosition, int compRoll, int compEnergy, int snakesLaddersArray [], int userPosition) throws IOException
    {// start compgetP
        
    	//20, 99, 32, 48, 65, 12, 24, 73, 49, 78 
        if(compPosition == snakesLaddersArray[0]) //if position equals snake 1
        {
            compPosition = 2; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, HE WENT GO DOWN!!!~~~~~~~~~~~~~");
        }
        else if (compPosition == snakesLaddersArray[1]) //if position equals snake 2
        {
            compPosition = 47; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got Bit By A Snake, HE WENT GO DOWN!!!~~~~~~~~~~~~~");
            
        }
        else if (compPosition == snakesLaddersArray[2]) //if position equals lader 1
        {
            compPosition = 77; // set new position
            System.out.println ("\t\t\t\t Computer Got TO A Ladder, HE WENT UP");
        }
        else if (compPosition == snakesLaddersArray[3]) //if position equals ladder 2
        {
            compPosition = 75; // set new position
            System.out.println ("\t\t\t\t~Computer Got TO A Ladder, HE WENT UP");
            
        }
        else if (compPosition == snakesLaddersArray[4]) //if position equals trampoline 1
        {
            compPosition =compPosition+ compRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A Trampoline!! He moved ahead some steps!!!~~~~~~~~~~~~~");
            
        }
        else if (compPosition == snakesLaddersArray[5]) //if position equals trampoline 2
        {
            compPosition =compPosition+ compRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A Trampoline!! He moved ahead some steps!!!~~~~~~~~~~~~~");
            
        }
        else if (compPosition == snakesLaddersArray[6]) //if position equals spring 1
        {
            
            
            compPosition =compPosition- compRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A Spring!! He went back some steps!!!~~~~~~~~~~~~~");
        }
        else if (compPosition == snakesLaddersArray[7]) //if position equals spring 2
        {
            
            
            compPosition =compPosition+ compRoll; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A Spring!! He went back some steps!!!~~~~~~~~~~~~~");
        }
        else if (compPosition == snakesLaddersArray[8]) //if position equals pitstop 1
        {
            
            
            compEnergy++; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A pitstop!! His Energy increased!!!~~~~~~~~~~~~~");
        }
        else if (compPosition == snakesLaddersArray[9]) //if position equals pitstop 2
        {
            
            
            compEnergy++; // set new position
            System.out.println ("\t\t\t\t~~~~~~~~~~~~~Computer Got A pitstop!! His Energy increased!!!~~~~~~~~~~~~~");
        }
        
       
        
        if (compPosition < 0 || compPosition > 112) //  ERROR TRAP to catch any unwanted system errors that may occur by chance
        {
            System.out.println ("An error has occured for the computer, please reset the game!!!!!!");
        }
        
        else if (compPosition > 100) // checks if computers's location if greater then a 100
        {
            compPosition = compPosition - compRoll; // get old position
            System.out.println ("THE COMPUTER CAN'T JUMP!!! He must land on a 100"); // give message that the computer cant jump
            
        }
        else if (compPosition == 100 && userPosition != 100)
        {
            System.out.println ("THE COMPUTER WON, YOU FAILED!!!"); // print out that the computer won
            
        }
        
        return compPosition; // return the final position to starGame: this position had gone through all checks and test and can be displayed on screen
    } // end compgetPy
}//end class