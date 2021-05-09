import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TTT {

    // set up arraylist for human and computer's position
    static ArrayList<Integer> humPosition = new ArrayList<Integer>();
    static ArrayList<Integer> comPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
        char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your marker (1-9)");
            int humPos = scan.nextInt();
            while (humPosition.contains(humPos) || comPosition.contains(humPos)) {
                System.out.println("This position has been taken, please place the marker somewhere else. ");
                humPos = scan.nextInt();
            }
            placeMarker(gameBoard, humPos, "human");

            Random randy = new Random();
            int comPos = randy.nextInt(9) + 1;
            while (comPosition.contains(comPos) || humPosition.contains(comPos)) {
                comPos = randy.nextInt(9) + 1;
            }
            placeMarker(gameBoard, comPos, "computer");

            printGameBoard(gameBoard);

            // print out the result using String
            String result = printWinner();
            System.out.println(result);

        }

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeMarker(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';

        // update the position to the human and computer arraylist
        if (user.equals("human")) {
            symbol = 'X';
            humPosition.add(pos);
        } else if (user.equals("computer")) {
            symbol = 'O';
            comPosition.add(pos);
        }

        // update the status of each block
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String printWinner() {
        // List all the possible winning positions
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rigRow = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        // make a list of the lists and add all the possible winning positions
        List<List> winningList = new ArrayList<List>();
        winningList.add(topRow);
        winningList.add(midRow);
        winningList.add(botRow);
        winningList.add(leftCol);
        winningList.add(midCol);
        winningList.add(rigRow);
        winningList.add(cross1);
        winningList.add(cross2);



            // Check who's the winner
             // see which player contains all the possibility of the winning positions
            for (List i : winningList) {
              if (humPosition.containsAll(i)) {
                System.out.println("Yeah, you win!!!");
                System.exit(0);
            } else if (comPosition.containsAll(i)) {
                System.out.println("Opps, you lose :( ");
                System.exit(0);
            } else if (humPosition.size() + comPosition.size() == 9) {
                System.out.println("It's a tie.");
                System.exit(0);
             }

        }

        return "";

    }
}
