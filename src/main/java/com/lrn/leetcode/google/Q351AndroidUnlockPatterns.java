package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;

public class Q351AndroidUnlockPatterns {
    /*
    * pd: https://leetcode.com/problems/android-unlock-patterns/
    *https://leetcode.com/problems/android-unlock-patterns/discuss/82463/Java-DFS-solution-with-clear-explanations-and-optimization-beats-97.61-(12ms)
    *
    * */

    // cur: the current position
    // remain: the steps remaining
    int backtrack(boolean[] visited, int[][] jump, int curKey, int remainKeyStrokes) {
          if(remainKeyStrokes <0) return 0;
          if(remainKeyStrokes == 0) return 1;
          visited[curKey] = true;
          int res =0;
          for(int key=1; key<=9; key++) {
              if(!visited[key] && (jump[curKey][key] ==0 || visited[jump[curKey][key]])) {
                  res = res + backtrack(visited,jump,key,remainKeyStrokes-1);
              }
          }
          visited[curKey] = false;
          return res;
    }

    public int numberOfPatterns(int m, int n) {
        int[][] jump = new int[10][10];
        jump[1][3] = jump[3][1] = 2;
        jump[7][9] = jump[9][7] = 8;
        jump[1][7] = jump[7][1] = 4;
        jump[3][9] = jump[9][3] = 6;
        jump[2][8] = jump[8][2] = jump[4][6] = jump[6][4] = jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = 5;
        boolean[] visited = new boolean[10];
        Arrays.fill(visited, false);
        int result = 0;
        for(int keyStrokes=m; keyStrokes<=n; keyStrokes++) {
            result = result + backtrack(visited, jump, 1, keyStrokes-1) * 4; // 1, 3, 7, 9 are symmetric
            result = result + backtrack(visited, jump, 2, keyStrokes-1) * 4; // 2, 4, 6, 8 are symmetric
            result = result + backtrack(visited, jump, 5, keyStrokes-1) ;// 5
        }
        return result;
    }

    public static void main(String[] args) {
        Q351AndroidUnlockPatterns sol = new Q351AndroidUnlockPatterns();
        System.out.println(sol.numberOfPatterns(1, 2));
    }

}
