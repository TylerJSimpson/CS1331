import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {

        // Initialize scanner
        Scanner input = new Scanner(System.in);

        // Print welcome message
        System.out.println("Welcome to Battleship!\n");

        // Initialize player 1 and player 2 arrays and variables
        int Player1Num = 1;
        int Player2Num = 2;
        boolean gameOver = false;
        char[][] player1Array = initializeArray();
        char[][] player2Array = initializeArray();
        char[][] player1HistoryArray = initializeArray();
        char[][] player2HistoryArray = initializeArray();

        // Prompt player 1 to enter board coordinates, update and print their board
        int[][] tempArray1 = enterCoordinates(Player1Num, input);
        for (int i = 0; i < tempArray1.length; i++) {
            int row = tempArray1[i][0];
            int col = tempArray1[i][1];
            player1Array[row][col] = '@';
        }
        printBattleShip(player1Array);
        
        // Print 100 new lines so the player cannot see each other's board
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

        // Prompt player 2 to enter board coordinates, update and print their board
        int[][] tempArray2 = enterCoordinates(Player2Num, input);
        for (int i = 0; i < tempArray2.length; i++) {
            int row = tempArray2[i][0];
            int col = tempArray2[i][1];
            player2Array[row][col] = '@';
        }
        printBattleShip(player2Array);

        // Print 100 new lines so the player cannot see each other's board
        for (int j = 0; j < 99; j++) {
            System.out.println();
        }

        // Start play and continue until all ships are sunk for either play (no @ present in array)
        
        while (!gameOver) {


            // Player 1's turn
            // Enter shots, validate, compare against history array then update history array
            int[][] gameplayArray = enterShots(Player1Num, input, player1HistoryArray);

            // Pass the coords to opponents array to see if it hit or miss
            boolean result = hitOrMiss(gameplayArray, player2Array);
            if (result) {
                System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                player2Array[gameplayArray[0][0]][gameplayArray[0][1]] = 'X';
                player1HistoryArray[gameplayArray[0][0]][gameplayArray[0][1]] = 'X';
            }
            else {
                System.out.println("PLAYER 1 MISSED!");
                player2Array[gameplayArray[0][0]][gameplayArray[0][1]] = 'O';
                player1HistoryArray[gameplayArray[0][0]][gameplayArray[0][1]] = 'O';
            }

            // Print updated history array
            printBattleShip(player1HistoryArray);

            // Check win condition
            if (allShipsSunk(player2Array)) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                System.out.println();
                System.out.println("Final boards:");
                System.out.println();
                printBattleShip(player1Array);
                System.out.println();
                printBattleShip(player2Array);

                gameOver = true;
                continue;
            }

            // Reset gameplay array
            gameplayArray[0][0] = 0;
            gameplayArray[0][1] = 0;

            // Player 2's turn
            // Enter shots, validate, compare against history array then update history array
            gameplayArray = enterShots(Player2Num, input, player2HistoryArray);

            // Pass the coords to opponents array to see if it hit or miss
            result = hitOrMiss(gameplayArray, player1Array);
            if (result) {
                System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                player1Array[gameplayArray[0][0]][gameplayArray[0][1]] = 'X';
                player2HistoryArray[gameplayArray[0][0]][gameplayArray[0][1]] = 'X';
            }
            else {
                System.out.println("PLAYER 2 MISSED!");
                player1Array[gameplayArray[0][0]][gameplayArray[0][1]] = 'O';
                player2HistoryArray[gameplayArray[0][0]][gameplayArray[0][1]] = 'O';
            }

            // Print updated history array
            printBattleShip(player2HistoryArray);

            // Check win condition
            if (allShipsSunk(player1Array)) {
                System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                System.out.println();
                System.out.println("Final boards:");
                System.out.println();
                printBattleShip(player1Array);
                System.out.println();
                printBattleShip(player2Array);

                gameOver = true;
                continue;
            }

            // Reset gameplay array
            gameplayArray[0][0] = 0;
            gameplayArray[0][1] = 0;

        }
	    }

    // Method to check if array was a hit or miss
    public static boolean hitOrMiss(int gameplayArray[][], char[][] opponentArray) {
        if (opponentArray[gameplayArray[0][0]][gameplayArray[0][1]] == '@') {
            return true;
        }
        else {
            return false;
        }
    }

    // Method for players to place shots they are validated and checked against history array
    public static int[][] enterShots(int playerNum, Scanner input, char[][] historyArray) {
        int[][] coordinates = new int[1][2];
        String invalidInt = "Invalid coordinates. Choose different coordinates.";
    
        while (true) { // Loop until valid coordinates are entered
            System.out.println();
            System.out.println("Player " + playerNum + ", enter hit row/column:");
            if (input.hasNextInt()) {
                int coordInputRow = input.nextInt();
                if (input.hasNextInt()) {
                    int coordInputCol = input.nextInt();
    
                    // Check if the entered coordinates are within bounds of the array
                    if (coordInputRow >= 0 && coordInputRow < 5 && coordInputCol >= 0 && coordInputCol < 5) {
                        // Check if the coordinates have been entered before
                        if ((historyArray[coordInputRow][coordInputCol] == 'X') || (historyArray[coordInputRow][coordInputCol] == 'O')) {
                            System.out.print("You already fired on this spot. Choose different coordinates.");
                        } else {
                            coordinates[0][0] = coordInputRow;
                            coordinates[0][1] = coordInputCol;
                            break; // Exit the loop when valid coordinates are entered
                        }
                    } else {
                        System.out.print(invalidInt);
                    }
                } else {
                    System.out.print(invalidInt);
                    input.next(); // Clear the invalid input
                }
            } else {
                System.out.print(invalidInt);
                input.next(); // Clear the invalid input
            }
        }
        return coordinates;
    }


    // Method to initialize a 5x5 array with '-' characters
    private static char[][] initializeArray() {
        char[][] array = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = '-';
            }
        }
        return array;
    }

    // Method to check if all ships are sunk
    public static boolean allShipsSunk(char[][] playerArray) {
        // Iterate through player array to see if there are no '@' chars
        int counter = 0;
        boolean result = false;
        for (int row = 0; row < playerArray.length; row++) {
            for (int col = 0; col < playerArray[row].length; col++) {
                if (playerArray[row][col] == '@') {
                    counter++;
                }
            }
        }
        if (counter == 0) {
            result = true;
        }
        return result;
    }

    // Method for players to input coordinates
    public static int[][] enterCoordinates(int playerNum, Scanner input) {   
        
        // Initialize internal variables
        int[][] tempArray = new int[5][2];
        String invalidInt = "Invalid coordinates. Choose different coordinates.";
        String invalidCoords = "You already have a ship there. Choose different coordinates.";
        
        System.out.println("PLAYER " + playerNum + ", ENTER YOUR SHIPS' COORDINATES.");


        // Iterate through each set of 5 coordinates
        for (int counter = 0; counter < 5; counter++) {
            boolean validInput = false;

            do {
                System.out.println("Enter ship " + (counter + 1) + " location:");
                
                // Check if the entered coordinates are integers
                if (input.hasNextInt()) {
                    int inputRow = input.nextInt();
                    if (input.hasNextInt()) {
                        int inputCol = input.nextInt();

                        // Check if the entered coordinates are within bounds of the array
                        if (inputRow >= 0 && inputRow < 5 && inputCol >= 0 && inputCol < 5) {
                            boolean duplicate = false;
                            
                            // Check if inputs are duplicates
                            for (int i = 0; i < counter; i++) {
                                if (tempArray[i][0] == inputRow && tempArray[i][1] == inputCol) {
                                    duplicate = true;
                                    break;
                                }
                            }

                            // Assign the inputs
                            if (!duplicate) {
                                tempArray[counter][0] = inputRow;
                                tempArray[counter][1] = inputCol;
                                validInput = true;
                            } 
                            else {
                                System.out.println(invalidCoords);
                            }
                        }
                        else {
                            System.out.println(invalidInt);
                        }
                    }
                    else {
                        System.out.println(invalidInt);
                        input.next(); // Clear the invalid input
                    }
                }
                else {
                    System.out.println(invalidInt);
                    input.next(); // Clear the invalid input
                }
            } while (!validInput);
        }
        return tempArray;
    }

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}