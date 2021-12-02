package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Write your code here
        Scanner scanner = new Scanner(System.in);

        //Get the actual game status in a single string ie:_XXOO_OX
        //System.out.print("Enter Cells: ");
        //StringBuilder tictac = new StringBuilder(scanner.next());

        //Print the Game status
        StringBuilder tictac = new StringBuilder("         ");
        paintGame(tictac);

        boolean xTurn = true;  // X starts the game..
        boolean loop = true;
        boolean invalidNumber = false;

        // Game Main Loop //

        while (loop) {

            //Ask the user for the coordinates
            System.out.print("Enter the coordinates: ");

            //get the coordinates as integer values, also check if the input is integer.
            int[] coordinates = new int[2];
            for (int i = 0;  i <=1; i++) {
                if (scanner.hasNextInt()) {
                    coordinates[i] = scanner.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    i++;
                    invalidNumber = true;
                    //continue;
                }
            }

            // validations before putting the user input into the game (3)

            // 1. You should enter numbers!
            if (invalidNumber) {
                continue;
            }

            // 2. Coordinates should be from 1 to 3! (rows and columns)
            if (coordinates[0] > 3 || coordinates[1] >3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            // 3. This cell is occupied! Choose another one!

            // We have the coordinates, but our game input is a String
            // so when the users input [1,1] [1,2]... it means tictac.charAt(0), tictac.charAt(1) and so on
            // lets convert the coordinates into a specific position in the string

            int positionToPlay = -1;
            char positionValue;

            if (coordinates[0] == 1 && coordinates[1] == 1) {
                positionToPlay = 0;
            }
            if (coordinates[0] == 1 && coordinates[1] == 2) {
                positionToPlay = 1;
            }
            if (coordinates[0] == 1 && coordinates[1] == 3) {
                positionToPlay = 2;
            }
            if (coordinates[0] == 2 && coordinates[1] == 1) {
                positionToPlay = 3;
            }
            if (coordinates[0] == 2 && coordinates[1] == 2) {
                positionToPlay = 4;
            }
            if (coordinates[0] == 2 && coordinates[1] == 3) {
                positionToPlay = 5;
            }
            if (coordinates[0] == 3 && coordinates[1] == 1) {
                positionToPlay = 6;
            }
            if (coordinates[0] == 3 && coordinates[1] == 2) {
                positionToPlay = 7;
            }
            if (coordinates[0] == 3 && coordinates[1] == 3) {
                positionToPlay = 8;
            }

            positionValue = tictac.charAt(positionToPlay);


            if (positionValue == 'X' || positionValue == 'O' || positionValue =='x' || positionValue == 'o') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                // if the cell is free to play then an X should be inserted in the respective string position
                //replace the original string with the one with the
                if (xTurn) {
                    tictac.setCharAt(positionToPlay, 'X');
                    xTurn = false;
                } else {
                    tictac.setCharAt(positionToPlay, 'O');
                    xTurn = true;
                }
             }

            // After all input validations have been made,
            // print the actual game status....
            paintGame(tictac);

            // then check if there is a winner...

            if (isWinnerOrDraw(tictac)) {
                loop = false;
            }
        }
   }

    private static void paintGame(StringBuilder tictac) {
        //Print the Game Status
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + tictac.charAt(i) + " " + tictac.charAt(i+1) + " " + tictac.charAt(i+2) + " |");
        }
        System.out.println("---------");
    }

    private static boolean isWinnerOrDraw(StringBuilder tictac) {

        // This is to verify if there is a winner in the game.
        /* This is the String with the representing characters positions in the game

        0 1 2
        3 4 5
        6 7 8

        Because we are working with a String input, we are going to treat the string as an array
        because we already know how many character it has.
        also, we know the 8 winning positions for the game which are the following:

        horizontal:  012 345 678
        vertical:    036 147 258
        diagonal     048 246
        */

        // now we iterate the string
        int countX = 0;
        int countO = 0;
        int countE = 0;  // for empty spaces in the game

        for (int i = 0; i < 9; i++) {

            // count the Xs, Os and Empty Spaces in the game
            countX += tictac.charAt(i) == 'X' || tictac.charAt(i) == 'x'? 1 : 0;
            countO += tictac.charAt(i) == 'O' || tictac.charAt(i) == 'o'? 1 : 0;
            countE += tictac.charAt(i) == '_' || tictac.charAt(i) == ' '? 1 : 0;

          }

        // First Validation: the difference between Xs and Os are more than 1
        if (Math.abs(countX - countO) > 1) {
            System.out.println("Impossible");
            return true;
        }

        // a valid game should have only 1 time three contiguous Xs or Os
        int xs = 0;   // how many times appears X char 3 times in a row, column or diagonal
        int os = 0;   // how many times appears O char 3 times in a row, column or diagonal

        // Winnings Horizontally
        xs += tictac.substring(0, 3).equals("XXX") ? 1: 0;
        xs += tictac.substring(3, 6).equals("XXX") ? 1 : 0;
        xs += tictac.substring(6, 9).equals("XXX") ? 1: 0;

        os += tictac.substring(0, 3).equals("OOO") ? 1: 0;
        os += tictac.substring(3, 6).equals("OOO") ? 1: 0;
        os += tictac.substring(6, 9).equals("OOO") ? 1: 0;

        //Winning Vertically
        xs += (tictac.substring(0, 1) + tictac.substring(3, 4) + tictac.substring(6, 7)).equals("XXX") ? 1: 0;
        xs += (tictac.substring(1, 2) + tictac.substring(4, 5) + tictac.substring(7, 8)).equals("XXX") ? 1: 0;
        xs += (tictac.substring(2, 3) + tictac.substring(5, 6) + tictac.substring(8, 9)).equals("XXX") ? 1: 0;

        os += (tictac.substring(0, 1) + tictac.substring(3, 4) + tictac.substring(6, 7)).equals("OOO") ? 1: 0;
        os += (tictac.substring(1, 2) + tictac.substring(4, 5) + tictac.substring(7, 8)).equals("OOO") ? 1: 0;
        os += (tictac.substring(2, 3) + tictac.substring(5, 6) + tictac.substring(8, 9)).equals("OOO") ? 1: 0;

        //Winnings Diagonally
        xs += (tictac.substring(0, 1) + tictac.substring(4, 5) + tictac.substring(8, 9)).equals("XXX") ? 1: 0;
        xs += (tictac.substring(2, 3) + tictac.substring(4, 5) + tictac.substring(6, 7)).equals("XXX") ? 1: 0;

        os += (tictac.substring(0, 1) + tictac.substring(4, 5) + tictac.substring(8, 9)).equals("OOO") ? 1: 0;
        os += (tictac.substring(2, 3) + tictac.substring(4, 5) + tictac.substring(6, 7)).equals("OOO") ? 1: 0;

        //Once we have counted every winning option for each gamer we need to do the following validations...

        //both winners are impossible for the game.
        // Not happening anymore because a check in main loop
        if (xs > 0 && os > 0) {
            System.out.println("Impossible");
            return true;
        }

        // if any of them won twice is impossible.
        // Not happening anymore because a check in main loop
        if (xs > 1 || os > 1) {
            System.out.println("Impossible");
            return true;
        }

        // if there are empty cells and both X and O haven't won...
        if (countE > 0 && xs == os) {
            //System.out.println("Game not finished");
            return false;
        }

        // if there aren't empty cells and both X and O haven't won...
        if ( countE == 0 && xs == os) {
            System.out.println("Draw");
            return true;
        }

        //After all the checks, just check if any gamer has won
        if (xs == 1) {
            System.out.println("X wins");
            return true;
        }

        //The last condition does not need a return statement.
        if (os == 1) {
            System.out.println("O wins");
        }
        return true;
    }


}
