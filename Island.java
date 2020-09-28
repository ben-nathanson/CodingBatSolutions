class Solution {
    final int water = 0;
    final int land = 1;
    
    // Return the perimeter of an individual cell. 
    private int cellPerimeter(int[][] grid, int row_index, int col_index) {
        
        if (grid[row_index][col_index] != this.land) {
            return 0;
        }
        
        int perimeter = 0;
        
        int grid_width = grid[0].length;
        int grid_height = grid.length;
        if (row_index == 0) perimeter++;
        
        // check left
        if (col_index >= 0 && grid[row_index][col_index - 1] == this.water) {
            perimeter++;// The left cell is water
        } else {
            perimeter++;// The left cell is water because we're on an edge    
        }
        
        // check top
        if (row_index >= 0 && grid[row_index - 1][col_index] == this.water) {
            perimeter++;// The top cell is water
        } else {
            perimeter++;// The top cell is water because we're on an edge    
        }
        
        // check right
        if (col_index < grid_width - 1 && grid[row_index][col_index + 1] == this.water) {
            perimeter++;// The right cell is water
        } else {
            perimeter++;// The right cell is water because we're on an edge    
        }
        
        // check bottom
        if (row_index < grid_height - 1 && grid[row_index + 1][col_index] == this.water) {
            perimeter++;// The bottom cell is water
        } else {
            perimeter++;// The right cell is water because we're on an edge    
        }
        
        return perimeter;
    }
    
    public int islandPerimeter(int[][] grid) {
        // grid is completely surrounded by water
        // always at least one cell
        // island == one or more land cells
        int totalperimeter = 0;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                totalperimeter += this.cellPerimeter(grid, i, j);
            }
        }
        return totalperimeter;
    }
}
