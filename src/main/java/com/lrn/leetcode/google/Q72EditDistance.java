package com.lrn.leetcode.google;

public class Q72EditDistance {
    /* https://leetcode.com/problems/edit-distance/
     * pd: Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
     * You have the following 3 operations permitted on a word:
     * Insert a character
     * Delete a character
     * Replace a character
     * appm: best time sol,
     * appr: DP bottom up
     * Ex.     0  1  2  3
     *            a  b  c    - string to be converted
     *       -----------
     *     0 | 0  1  2  3
     *   a 1 | 1  0  1  2
     *   d 2 | 2  1  1  2
     *   c 3 | 3  2  2  1
     *   e 4 | 4  3  3  2
     *
     * */

    public int minDistance(String word1, String word2) {
        int cols = word1.length(); // string to be converted
        int rows = word2.length();
        int[][] edits = new int[rows+1][cols+1];
        for(int idx=0; idx<=cols;idx++) {
            edits[0][idx] = idx;
        }
        for(int idx=0; idx<=rows;idx++) {
            edits[idx][0] = idx;
        }
        for(int i=1; i<=rows; i++) {
            for(int j=1; j<=cols; j++) {
                if(word1.charAt(j-1) == word2.charAt(i-1)) {
                    edits[i][j] = edits[i-1][j-1];
                } else {
                    edits[i][j] = Math.min(edits[i-1][j], Math.min(edits[i-1][j-1], edits[i][j-1])) +1;
                }
            }
        }
        return edits[rows][cols];
    }

    public static void main(String[] args) {
        Q72EditDistance sol = new Q72EditDistance();
        System.out.println(sol.minDistance("abc", "adce"));
        System.out.println(sol.minDistance("horse", "ros"));
        System.out.println(sol.minDistance("intention", "execution"));
    }
}
