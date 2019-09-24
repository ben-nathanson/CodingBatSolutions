/*
https://leetcode.com/problems/spiral-matrix-ii/
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

I started solving this problem by doing it on an index card
and realizing that there's an intuitive pattern.
For example, if input is 3, we would go right three times,
down 2 times, left 2 times, up once, and right once. The
right-down-left-up pattern is constant, all that changes
 is how many times we move for each direction.
*/

class SpiralMatrix2 {
    public static void testMatrix(int l) {
      printMatrix(generateMatrix(l));
    }
    public static void printMatrix(int[][] m) {
      String spacing = String.valueOf(1 + String.valueOf(m.length * m.length).length());
      for (int row = 0; row < m.length; row++) {
        for (int column = 0; column < m[0].length - 1; column++) {
          System.out.printf("%" + spacing + "d,", m[row][column]);
        }
          System.out.printf("%" + spacing + "d\n", m[row][m[0].length - 1]);
      }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];// this is what we're returning
        int cellValue = 1;// this is what we're putting into the matrix

        // We always fill in the top row first
        // As it turns out we don't have to do this as a
        // separate operation, but this worked and I had another
        // mock interview problem that was more of a priority 
        for (int i = 0; i < n; i++) {
            matrix[0][i] = cellValue++;
        }

        // starting from the cell beneath the upper-right-hand
        // corner cell, we fill in the rest of the matrix:
        int row = 0;
        int column = n - 1;
        for (int ctr = n - 1; ctr > 0; ctr--) {
            // go down
            for(int i = 0; i < ctr; i++) {
                row++;
                matrix[row][column] = cellValue++;
            }
            // go left
            for(int i = 0; i < ctr; i++) {
                column--;
                matrix[row][column] = cellValue++;
            }
            // we have now completed one spiral so
            // we can decrement the ctr. I think
            // of it as the size of one of the sides
            // of one loop in the spiral
            ctr--;
            // go up
            for(int i = 0; i < ctr; i++) {
                row--;
                matrix[row][column] = cellValue++;
            }
            // go right
            for(int i = 0; i < ctr; i++) {
                column++;
                matrix[row][column] = cellValue++;
            }
        }
        return matrix; // return the spiral matrix
    }
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: SpiralMatrix2 <natural number>");
      System.exit(0);
    }
    int input = Integer.parseInt(args[0]);
    testMatrix(input);
  }
}
