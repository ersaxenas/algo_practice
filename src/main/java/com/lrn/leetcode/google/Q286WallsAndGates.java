package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.Queue;

public class Q286WallsAndGates {
    /*
    * pd: You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
    * assm: grid size < int.max, int.max, best time sol
    *
    * */

    public void wallAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0) {
            return;
        }
        int rows = rooms.length, cols = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                 if(rooms[row][col] == 0) {
                     queue.add(new int[] {row,col});
                 }
            }
        }

        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            if(row >0 && rooms[row-1][col] == Integer.MAX_VALUE) {
                rooms[row-1][col] = rooms[row][col] +1;
                queue.add(new int[]{row-1, col});
            }
            if(row < rows-1 && rooms[row+1][col] == Integer.MAX_VALUE) {
                rooms[row+1][col] = rooms[row][col] +1;
                queue.add(new int[]{row+1, col});
            }
            if(col > 0 && rooms[row][col-1] == Integer.MAX_VALUE) {
                rooms[row][col-1] = rooms[row][col] +1;
                queue.add(new int[]{row, col-1});
            }
            if(col < cols-1 && rooms[row][col+1] == Integer.MAX_VALUE) {
                rooms[row][col+1] = rooms[row][col] +1;
                queue.add(new int[]{row, col+1});
            }
        }
    }

    public static void main(String[] args) {
        int inf = Integer.MAX_VALUE;
        int[][] matrix = {
                {inf, -1, 0, inf},
                {inf, inf, inf, -1},
                {inf, -1, inf, -1},
                {0, -1, inf, inf}
        };
        Q286WallsAndGates sol = new Q286WallsAndGates();
        sol.wallAndGates(matrix);
        LsUtil.printArray(matrix);
    }

}
