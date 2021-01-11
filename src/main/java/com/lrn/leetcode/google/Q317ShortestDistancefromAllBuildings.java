package com.lrn.leetcode.google;

import java.util.LinkedList;
import java.util.Queue;

public class Q317ShortestDistancefromAllBuildings {
    /*
    * pd: You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
    *https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76891/Java-solution-with-explanation-and-time-complexity-analysis
    * */

    int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int rows, cols;
    public int shortestDistance(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        int[][] reachableBuildings = new int[rows][cols];
        int buildingCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        // step 1 : from every building 1 bfs and calculate distance array
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    queue.add(new int[]{r, c});
                    bfs(queue, grid, dp, reachableBuildings);
                    buildingCount++;
                }
            }
        }
        int minStep = Integer.MAX_VALUE;
         for(int r=0; r<rows; r++) {
             for(int c=0; c<cols; c++) {
                 if(grid[r][c] == 0 && reachableBuildings[r][c] == buildingCount) {
                     minStep = Math.min(minStep,dp[r][c]);
                 }
             }
         }
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    public void bfs(Queue<int[]> queue, int[][] grid, int[][] dp, int[][] reachableBuildings) {
        boolean[][] visited = new boolean[rows][cols];
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int idx = 0; idx < size; idx++) {
                final int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];
                for (int[] dir : direction) {
                    int x = r + dir[0];
                    int y = c + dir[1];
                    if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] != 0 || visited[x][y]) {
                        continue;
                    }
                    queue.add(new int[]{x,y});
                    visited[x][y] = true;
                    dp[x][y] += level;
                    reachableBuildings[x][y]++;
                }
            }
            level++;
        }
    }

    public static void main(String[] args) {
        Q317ShortestDistancefromAllBuildings sol = new Q317ShortestDistancefromAllBuildings();
        int[][] grid = new int[][] {
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(sol.shortestDistance(grid));
    }

}
