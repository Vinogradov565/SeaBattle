import java.util.Random;

public class BattleshipGame {
    private static final int SIZE = 10;
    private static final int EMPTY = 0;
    private static final int SHIP = 1;

    private int[][] board;

    public BattleshipGame() {
        board = new int[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void placeShip(int length) {
        Random random = new Random();
        boolean horizontal = random.nextBoolean();

        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (!isValidPlacement(row, col, length, horizontal));

        for (int i = 0; i < length; i++) {
            if (horizontal) {
                board[row][col + i] = SHIP;
            } else {
                board[row + i][col] = SHIP;
            }
        }
    }

    private boolean isValidPlacement(int row, int col, int length, boolean horizontal) {
        if (horizontal) {
            if (col + length > SIZE) {
                return false;
            }
            for (int i = col; i < col + length; i++) {
                if (board[row][i] == SHIP) {
                    return false;
                }
            }
        } else {
            if (row + length > SIZE) {
                return false;
            }
            for (int i = row; i < row + length; i++) {
                if (board[i][col] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeSingleDeckShips() {
        for (int i = 0; i < 10; i++) {
            placeShip(1);
        }
    }

    public void placeDoubleDeckShips() {
        for (int i = 0; i < 10; i++) {
            placeShip(2);
        }
    }

    public void placeTripleDeckShips() {
        for (int i = 0; i < 5; i++) {
            placeShip(3);
        }
    }

    public void placeAllShips() {
        placeSingleDeckShips();
        placeDoubleDeckShips();
        placeTripleDeckShips();
        placeShip(4);
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.placeAllShips();
        game.printBoard();
    }
}
