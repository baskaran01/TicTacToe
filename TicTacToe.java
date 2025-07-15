import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        boolean playAgain = true;
        System.out.println("Welcome to Tic Tac Toe Game");
        while (playAgain) {
            RunGame game=new RunGame();
            game.displayBoard();
            game.play();
            playAgain = game.checkPlayAgain();
            if(!playAgain)game.closeScanner();
        }
        System.out.println("Thanks for playing! Exiting game.");
    }
}

class RunGame {
    Scanner scanner = new Scanner(System.in);
    char[][] Board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

    void displayBoard() {
        System.out.println("\n ----+---+----");
        for (int i = 0; i < 3; i++) {
            System.out.print(" | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(Board[i][j] + " | ");
            }
            System.out.println("\n ----+---+----");
        }
    }

    void play() {
        int k, n, m;
        for (k = 0; k < 9; k++) {
            System.out.println("Where to place, Enter two index of row and column [1,2,3]");
            char CurrentPlayer = (k%2==0)?'X':'O';
            while (true) {
                System.out.print("Player "+CurrentPlayer + " Please enter a number: ");
                try {
                    n = scanner.nextInt();
                    m = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
            }
            if ((n > 0 && n <= 3 && m > 0 && m <= 3) && Board[n - 1][m - 1] == ' ') {
                Board[n - 1][m - 1] = CurrentPlayer;
                if ((Board[0][0] == Board[1][1] && Board[1][1] == Board[2][2] && Board[0][0] != ' ') ||
                        (Board[2][0] == Board[1][1] && Board[1][1] == Board[0][2] && Board[2][0] != ' ') ||
                        (Board[0][0] == Board[0][1] && Board[0][1] == Board[0][2] && Board[0][0] != ' ') ||
                        (Board[1][0] == Board[1][1] && Board[1][1] == Board[1][2] && Board[1][0] != ' ') ||
                        (Board[2][0] == Board[2][1] && Board[2][1] == Board[2][2] && Board[2][0] != ' ') ||
                        (Board[0][0] == Board[1][0] && Board[1][0] == Board[2][0] && Board[0][0] != ' ') ||
                        (Board[0][1] == Board[1][1] && Board[1][1] == Board[2][1] && Board[0][1] != ' ') ||
                        (Board[0][2] == Board[1][2] && Board[1][2] == Board[2][2] && Board[0][2] != ' ')) {
                    displayBoard();
                    System.out.println("\n\t\tPlayer "+CurrentPlayer+" WINNER\n");
                    return;
                }
            } else {
                System.out.println("Player "+ CurrentPlayer+" invalid index your input must be between [1-3]");
                k--;
            }
            displayBoard();
        }
        System.out.println("\n\n Match Drawn \n -TRY AGAIN-\n\n");
    }
    boolean checkPlayAgain() {
        System.out.println("If you want try again press \"Y\" or else press any key to exit");
        return scanner.next().equalsIgnoreCase("Y");
    }
    void closeScanner(){
        scanner.close();
    }
}
