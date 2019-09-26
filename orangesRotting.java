import java.util.Arrays;
import java.util.Random;
class orangesRotting {
    final int EMPTY = 0;
    final int FRESH = 1;
    final int ROTTEN = 2;
    Random r = new Random();

    public boolean noRotten(int[][] grid) {
        int gridSize = grid.length;
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if (grid[c][r] == ROTTEN) return false;
            }
        }
        return true;
    }

    public boolean allRotten(int[][] grid) {
        int gridSize = grid.length;
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                if (grid[c][r] == FRESH) return false;
            }
        }
        return true;
    }

    public boolean impossible(int[][]grid) {
        if (noRotten(grid)) return true;
        // we are looking for the pattern in which a fresh orange is
        // surrounded by empty space on four sides and so will never mutate
        // into a rotten orange ...
        int columnLength = grid[0].length;
        int rowLength = grid.length;
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
                if (grid[r][c] == FRESH) {// if a
                    // check right
                    if (c + 1 < columnLength) {
                        if (grid[r][c + 1] != EMPTY) continue;
                    }
                    if (c - 1 >= 0) {
                        // check left
                        if (grid[r][c - 1] != EMPTY) continue;
                    }
                    if (r + 1 < rowLength) {
                        // check down
                        if (grid[r + 1][c] != EMPTY) continue;
                    }
                    if (r - 1 >= 0) {
                        // check up
                        if (grid[r - 1][c] == EMPTY) return true;
                    } else {
                        return true;
                    }

                }
            }
        }
        return false;// default would be false
    }

    public void printBoard(int[][] grid) {
      StringBuilder output = new StringBuilder();
      for (int i = 0; i < grid.length; i++) output.append("-");
      output.append("\n");
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
          output.append(String.format("%2d", grid[i][j]));
        }
        output.append("\n");
      }
      for (int i = 0; i < grid.length; i++) output.append("-");
      output.append("\n");
      System.out.print(output);
    }

    public void simulate(int[][] grid) {
        int columnLength = grid[0].length;
        int rowLength = grid.length;
        int[][] original = new int[rowLength][columnLength];
        copyArray(grid, original);
        for (int r = 0; r < rowLength; r++) {
            for (int c = 0; c < columnLength; c++) {
              if (r >= rowLength) break;
                if (original[r][c] == FRESH) {
                    // check right
                    if (c + 1 < columnLength) {
                        if (original[r][c + 1] == ROTTEN) {
                          grid[r][c] = ROTTEN;
                          continue;
                        }
                    }
                    if (c - 1 >= 0) {
                        // check left
                        if (original[r][c - 1] == ROTTEN) {
                          grid[r][c] = ROTTEN;
                          continue;
                        }
                    }
                    if (r + 1 < rowLength) {
                        if (original[r + 1][c] == ROTTEN) {
                          grid[r][c] = ROTTEN;
                          continue;
                        }
                    }
                    if (r - 1 >= 0) {
                        if (original[r - 1][c] == ROTTEN) {
                          grid[r][c] = ROTTEN;
                          continue;
                        }
                    }
                }
            }
        }
    }

    public static void print(Object o) {
      System.out.println(o);
    }

    public int orangesRotting(int[][] grid) {
        // else check if there is a fresh orange surrounded by emptiness
        if (impossible(grid)) return -1;
        // check if they are all rotten to begin with
        if (allRotten(grid)) return 0;

        // else simulate the board and count how many generations it
        // took for every orange to rot
        int i = 0;
        while(!allRotten(grid)) {
            simulate(grid);
            i++;
        }
        return i;
    }

    public int randInt(int min, int max) {
      int random = r.nextInt((max - min) + 1) + min;
      return random;
    }

    public void fuzzGrid(int[][] grid) {
      for(int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
            grid[i][j] = randInt(EMPTY, ROTTEN);
          }
      }
    }

    public void genGrid(int[][] grid, int numFresh, int numRotten) {
      int maxSize = grid.length * grid[0].length;
      // can't pass in too many oranges
      if (numFresh + numRotten > maxSize) return;
      // fill the board with empty cells
      int numRows = grid.length;
      int numColumns = grid[0].length;
      for(int i = 0; i < numRows; i++) {
          for (int j = 0; j < numColumns; j++) {
            grid[i][j] = EMPTY;
          }
      }

      // we need to add numFresh fresh oranges
      for(int k = 0; k < numFresh; k++) {
        int row = randInt(0, numRows -1);
        int column = randInt(0, numColumns - 1);
        while(grid[row][column] != EMPTY) {
          row = randInt(0, numRows -1);
          column = randInt(0, numColumns - 1);
        }
        grid[row][column] = FRESH;
      }

      // we need to add numRotten rotten oranges
      for(int k = 0; k < numRotten; k++) {
        int row = randInt(0, numRows -1);
        int column = randInt(0, numColumns - 1);
        while(grid[row][column] != EMPTY) {
          row = randInt(0, numRows -1);
          column = randInt(0, numColumns - 1);
        }
        grid[row][column] = ROTTEN;
      }


    }

    public int solveGrid(int[][]grid) {
      int i = 0;
      if(impossible(grid)) {
        printBoard(grid);
        print("This is unsolvable.");
        return -1;
      } else {
        int maxPossibleRuntime = grid.length * grid[0].length;
        for (i = 0; i < maxPossibleRuntime; i++) {
            print("Round " + i);
            printBoard(grid);
            simulate(grid);
            try {
              Thread.sleep(100);
            } catch (InterruptedException e){
              e.printStackTrace();
            }
            if (allRotten(grid)) {
              printBoard(grid);
              print((i + 1) + " rounds were needed");
              break;
            }
        }
      }
      return i + 1;
    }

    // copy A into B
    public void copyArray(int[][] A, int[][] B) {
      int rows = A.length;
      int columns = A[0].length;
      if(B.length != rows || B[0].length != columns) return; // have to be the same size
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < columns; c++) {
          B[r][c] = A[r][c];
        }
      }
    }

    public static void main(String[] args) {
      orangesRotting O = new orangesRotting();
      int[][] grid = new int[3][3];
      O.genGrid(grid, 4, 2);
      O.solveGrid(grid);
    }
}
