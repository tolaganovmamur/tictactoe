import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private static char currentPlayer = 'X';

    private static boolean winGame = false;


    public static void main(String[] args) {
        printBoard();
        playGame();
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Tic Tac Toe
                Please choose mode
                1-With Friend
                2-With Computer""");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                while (!winGame) {
                    System.out.print("Please player " + currentPlayer + " select row and column: ");
                    int row = scanner.nextInt();
                    int column = scanner.nextInt();
                    if (row < 4 && row > 0 && column < 4 && column > 0) {
                        if (checkMove(row, column)) {
                            board[row - 1][column - 1] = currentPlayer;
                            printBoard();
                        } else {
                            switchPlayer();
                            System.out.println("Incorrect move,please choose another row or column!");
                        }
                    } else {
                        System.out.println("Invalid column or row, please try again!");
                        continue;
                    }
                    winGame = checkWin();
                    if (winGame) {
                        System.out.println("Player " + currentPlayer + " won!");
                        break;
                    } else if (checkDraw()) {
                        System.out.println("DRAW");
                        break;
                    } else {
                        switchPlayer();
                    }
                }
                scanner.close();
            }


            case 2 -> {
                while (!winGame) {
                    if (currentPlayer == 'X') {
                        System.out.println("Player X, enter your move (row and column): ");
                        int row = scanner.nextInt();
                        int col = scanner.nextInt();
                        if (row < 4 && row > 0 && col < 4 && col > 0) {
                            if (checkMove(row, col)) {
                                board[row - 1][col - 1] = currentPlayer;
                                printBoard();
                            } else {
                                System.out.println("Incorrect move,please choose another row or column!");
                                continue;
                            }
                        } else {
                            System.out.println("Invalid column or row, please try again!");
                            continue;
                        }
                    } else {
                        Random random = new Random();
                        int row = random.nextInt(3);
                        int col = random.nextInt(3);
                        if (checkComputerMove(row, col)) {
                            board[row][col] = currentPlayer;
                            printBoard();
                        } else {
                            continue;
                        }
                    }

                    winGame = checkWin();
                    if (winGame) {
                        if (currentPlayer == 'X') {
                            System.out.println("Player X wins!");
                            break;
                        } else {
                            System.out.println("Computer wins!");
                            break;
                        }
                    } else if (checkDraw()) {
                        System.out.println("The game is a draw!");
                        break;
                    } else {
                        switchPlayer();
                    }
                }

                scanner.close();

            }
        }
    }

    private static boolean checkMove(int i, int a) {
        return board[i - 1][a - 1] == ' ';
    }

    private static boolean checkComputerMove(int i, int a) {
        return board[i][a] == ' ';
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }


    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;

        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }


        return false;
    }


}
