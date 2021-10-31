package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q417PacificAtlanticWaterFlow {
    /*
    * PD: Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
    * assm: matrix six < 1000, -1 < elem < 1000, best time sol
    * appr: dfs
    * https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90758/Not-understanding-the-problem.-Could-someone-please-explain
    * Test cases:
    *
    * */

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] pacificVisited = new boolean[rows][cols];
        boolean[][] atlanticVisited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, row, 0); // first column
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, row, cols - 1); // last column
        }
        for (int col = 0; col < cols; col++) {
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, 0, col); // fist column
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, rows - 1, col); // last column
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (pacificVisited[row][col] && atlanticVisited[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }

        return result;
    }

    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void dfs(int[][] matrix, boolean[][] visited, int prvCellHeight, int row, int col) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || visited[row][col] || matrix[row][col] < prvCellHeight) { // pay attention to last condition.
            return;
        }
        visited[row][col] = true;
        for (int[] dir : direction) {
            dfs(matrix, visited, matrix[row][col], row + dir[0], col + dir[1]);
        }
    }

    public static void main(String[] args) {
        Q417PacificAtlanticWaterFlow sol = new Q417PacificAtlanticWaterFlow();
        int[][] matrix = {
                {1,2 ,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        LsUtil.printList(sol.pacificAtlantic(matrix));
    }
}






