package com.lrn.leetcode.google;

public class Q174DungeonGame {
    /*
     * pd: https://leetcode.com/problems/dungeon-game/
     * start from last cell of matrix. energy needed at last col is = a). 1 if col value is positive b). 1 - (-ve value of cell) or 1 + abs(cell value)
     * for ex: if value of last cell is 3 then you need just 1 energy level when you reach this cell
     *         if value of last cell is -3 the you need 4 energy level when you reach this cell
     * Now fill last row - max ( 1, health next cell value in the row - dungeon current cell value in the row)
     *  for ex: if value of last cell is 5 then you need just 1 energy level when you reach this cell
     *         if value of last cell is -2 the you need energy level  =  energy level required at next cell + 2
     * Now fill last col - max ( 1, health next cell value in the col - dungeon current cell value in the col)
     *  for ex: if value of last cell is 5 then you need just 1 energy level when you reach this cell
     *         if value of last cell is -2 the you need energy level  =  energy level required at next cell + 2
     * now fill rest of matrix :
     *    calculate energy level for next cell in the COLUMN
     *    calculate energy level for next cell in the ROW
     *    energy level required for next level is = min of above to value
     * */

    public int calculateMinimumHP(int[][] dungeon) {
        int noOfRows = dungeon.length, noOfCols = dungeon[0].length;
        int[][] health = new int[noOfRows][noOfCols];
        health[noOfRows - 1][noOfCols - 1] = Math.max(1 - dungeon[noOfRows - 1][noOfCols - 1], 1); // last col where queen is
        for (int row = noOfRows - 2; row >= 0; row--) { // fill last col.
            health[row][noOfCols - 1] = Math.max(1, health[row + 1][noOfCols - 1] - dungeon[row][noOfCols - 1]);
        }
        for (int col = noOfCols - 2; col >= 0; col--) { // fill last row
            health[noOfRows - 1][col] = Math.max(1, health[noOfRows - 1][col + 1] - dungeon[noOfRows - 1][col]);
        }
        for (int row = noOfRows - 2; row >= 0; row--) {
            for (int col = noOfCols - 2; col >= 0; col--) {
                int hright = Math.max(1, health[row][col + 1] - dungeon[row][col]);
                int hdown = Math.max(1, health[row + 1][col] - dungeon[row][col]);
                health[row][col] = Math.min(hright, hdown);
            }
        }
        return health[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        Q174DungeonGame sol = new Q174DungeonGame();
        //System.out.println(sol.calculateMinimumHP(dungeon));
        System.out.println(sol.calculateMinimumHP(new int[][]{
                {0, -3}
        }));
    }

}
