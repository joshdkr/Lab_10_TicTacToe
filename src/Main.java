import java.util.*;

public class Main {

    private static final int row = 3;
    private static final int col = 3;
    private static String board[][] = new String[row][col];

    // sets the board back to empty state
    private static void clearBoard()
    {
        for (int countRow = 0; countRow < row; countRow++)
        {
            for (int countCol = 0; countCol < col; countCol++)
            {
                board[countRow][countCol] = " ";
            }
        }
    }

    // displays the current state of the board
    private static void displayBoard()
    {
        for (int countRow = 0; countRow < row; countRow++) {
            for (int countCol = 0; countCol < row; countCol++) {
                System.out.print("|" + board[countRow][countCol] + "|");
            }
            System.out.println();
            if (countRow < 2)
            {
                System.out.println("---------");
            }
        }
    }

    // sets a valid move to the correct position on the board
    private static void newMove(int rowMove, int colMove, String playerTurn)
    {
                board[rowMove][colMove] = playerTurn;

    }

    // checks to see if a move is valid based on whether the space is already occupied
    private static boolean validMove(int rowMove, int colMove)
    {
        boolean isValid = false;
        if (board[rowMove][colMove] == " ")
        {
            isValid = true;
        }
        else
        {
            System.out.println("That is not a valid move.");
        }
        return isValid;
    }

    //tracks which player's turn it is based on the turn number
    private static String playerTurn(int turnCount)
    {
        String x = "x";
        String o = "o";
        String playerInput = "";

        if ((turnCount|1) > turnCount)
        {
            playerInput = o;
        }
        else
        {
            playerInput = x;
        }
        return playerInput;
    }


    // method to check for all three win states, and declare the correct win type if applicable
    private static boolean isWin (boolean col, boolean row, boolean diag, String playerTurn)
    {
        boolean isWin = false;
        if (col)
        {
            System.out.println("Column win for " + playerTurn);
            isWin = true;
        }
        if (row)
        {
            System.out.println("Row win for " + playerTurn);
            isWin = true;
        }
        if (diag)
        {
            System.out.println("Diagonal win for " + playerTurn);
            isWin = true;
        }
        return isWin;
    }






    // method to check for col win type
    private static boolean isColWin(String playerTurn)
    {
        boolean isWin = false;
            for (int countCol = 0; countCol < col; countCol++)
            {
                if (board[0][countCol] == board[1][countCol] && board[1][countCol] == board[2][countCol] && board[1][countCol] == playerTurn) {
                    isWin = true;
                    break;
                }
            }
        return isWin;
    }

    // method to check for row win type
    private static boolean isRowWin(String playerTurn)
    {
        boolean isWin = false;
        for (int countRow = 0; countRow < col; countRow++)
        {
            if (board[countRow][0] == board[countRow][1] && board[countRow][1] == board[countRow][2] && board[countRow][2] == playerTurn) {
                isWin = true;
                break;
            }
        }
        return isWin;
    }

    // method to check for diagonal win type
    private static boolean isDiagWin(String playerTurn)
    {
        boolean isWin = false;
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == playerTurn)
        {
            isWin = true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == playerTurn)
        {
            isWin = true;
        }
        return isWin;
    }

    

public static void main(String[] args) {

        // creates scanner input
        Scanner kb = new Scanner(System.in);
        String playAgain = "n";

        //sets the board to clear state
        clearBoard();


    boolean thisLoop = false;

    while (!thisLoop) {
        // loop for each game, mostly calling methods
        for (int turnCount = 1; turnCount <= 9; turnCount++) {

            //show which player's turn and the number of the turn for this game
            System.out.println(playerTurn(turnCount) + "'s turn.");
            System.out.println("This is turn " + turnCount);
            System.out.println();

            //show the board
            displayBoard();

            System.out.println();

            int rowMove = SafeInput.getRangedInt(kb, "Enter your row", 1, 3) - 1;

            System.out.println();

            int colMove = SafeInput.getRangedInt(kb, "Enter your column", 1, 3) - 1;

            boolean doneLoop = false;

            while (!doneLoop)
                if (!validMove(rowMove, colMove)) {
                    rowMove = SafeInput.getRangedInt(kb, "Enter your row", 1, 3) - 1;

                    System.out.println();

                    colMove = SafeInput.getRangedInt(kb, "Enter your column", 1, 3) - 1;
                } else {
                    doneLoop = true;
                }


            // enters a new move if it is valid
            newMove(rowMove, colMove, playerTurn(turnCount));

            //checks for a win

            if (isWin(isColWin(playerTurn(turnCount)), isRowWin(playerTurn(turnCount)), isDiagWin(playerTurn(turnCount)), playerTurn(turnCount))) {
                System.out.println("Would you like to play again?");
                kb.nextLine();
                playAgain = kb.nextLine();
                if (playAgain.equalsIgnoreCase("y"))
                {
                    clearBoard();
                    turnCount = 0;

                    System.out.println("\nNext game:\n");
                }
                else
                {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }

        }
    }


    }
}



/*
 TO-Do List:
 1. add a tie state if a win is not possible
 2. create two players with scores
*/
