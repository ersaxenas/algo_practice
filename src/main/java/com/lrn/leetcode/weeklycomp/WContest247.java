package com.lrn.leetcode.weeklycomp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WContest247 {

    /*
     * 1913. Maximum Product Difference Between Two Pairs
     * */
    public int maxProductDifference(int[] nums) {
        int large1 = Integer.MIN_VALUE, large2 = large1, small1 = Integer.MAX_VALUE, small2 = small1;
        for (int idx = 0; idx < nums.length; idx++) {
            if (nums[idx] > large1) {
                large2 = large1;
                large1 = nums[idx];
            } else if (nums[idx] > large2) {
                large2 = nums[idx];
            }
            if (nums[idx] < small1) {
                small2 = small1;
                small1 = nums[idx];
            } else if (nums[idx] < small2) {
                small2 = nums[idx];
            }
        }
        System.out.println("large1 " + large1);
        System.out.println("large2 " + large2);
        System.out.println("small1 " + small1);
        System.out.println("small2 " + small2);
        System.out.println("");
        return (large1 * large2) - (small1 * small2);
    }

    /*
     * https://leetcode.com/contest/weekly-contest-247/problems/cyclically-rotating-a-grid/
     * */
    public int[][] rotateGrid(int[][] grid, int k) {
        int rows = 0, rowe = grid.length - 1, cols = 0, cole = grid[0].length - 1;
        while (rows < rowe && cols < cole) { // for each laryer
            int eleminlayer = 2 * (rowe - rows + 1) + 2 * (cole - cols + 1) - 4;
            int rotation = k % (eleminlayer);
            while (rotation-- > 0) {
                int temp = grid[rows][cols];
                // top row of layer
                for (int col = cols; col < cole; col++) {
                    grid[rows][col] = grid[rows][col + 1];
                }
                // right most col
                for (int row = rows; row < rowe; row++) {
                    grid[row][cole] = grid[row + 1][cole];
                }
                // bottom row of layer
                for (int col = cole; col > cols; col--) {
                    grid[rowe][col] = grid[rowe][col - 1];
                }
                // first col of layer
                for (int row = rowe; row > rows; row--) {
                    grid[row][cols] = grid[row - 1][cols];
                }
                grid[rows + 1][cols] = temp;
            }
            rows++;
            rowe--;
            cols++;
            cole--;
        }
        return grid;
    }

    /*
    * https://leetcode.com/contest/weekly-contest-247/problems/count-ways-to-build-rooms-in-an-ant-colony/
    * */

    public int waysToBuildRooms(int[] prevRoom) {
        if(prevRoom == null || prevRoom.length == 0) return 0;
        if(prevRoom.length == 1) return 1;
        Map<Integer, List<Integer>> childmap = new HashMap<>();
        // build child parent and child room relation.
        for(int pidx=0; pidx < prevRoom.length; pidx++) {
            childmap.put(pidx, childmap.getOrDefault(pidx, new ArrayList<>()));
            if(prevRoom[pidx] != -1) {
                childmap.get(prevRoom[pidx]).add(pidx);
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(queue, childmap, new ArrayList<>(), result);
        return result.size();
    }

    public void backtrack(ArrayDeque<Integer> queue, Map<Integer, List<Integer>> childmap, List<Integer> tmp, List<List<Integer>> result ) {
        if(queue.size() == 0) result.add(new ArrayList<>(tmp));
        for(int room: queue) {
            ArrayDeque<Integer> newQueue = new ArrayDeque<>(queue);
            newQueue.remove(room);
            tmp.add(room);
            for(int child: childmap.get(room)) {
                newQueue.add(child);
            }
            backtrack(newQueue, childmap, tmp, result);
            tmp.remove(tmp.size()-1);
        }
    }


    /*
     * https://leetcode.com/contest/weekly-contest-247/problems/number-of-wonderful-substrings/
     * */
    /*TODO still do not understand this sol completely. */
    public long wonderfulSubstrings(String word) {
        long[] cnt = new long[1024]; // cnt[state] stores how many times the state occurs
        cnt[0] = 1;  //empty string gives case where all characters occur even number of times
        int mask = 0; // current state
        long ans = 0;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            int idx = c - 'a';
            mask ^= 1 << idx; // update state
            ans += cnt[mask]; // add count of same previous states
            for (int i = 0; i < 10; i++) {
                int lookFor = mask ^ (1 << i); // try flick each switch
                ans += cnt[lookFor];
            }
            cnt[mask]++; // add 1 to count of times we've seen current state
        }
        return ans;
    }


    public static void main(String[] args) {
        WContest247 sol = new WContest247();
        //System.out.println(sol.wonderfulSubstrings("aabb"));
        System.out.println(sol.waysToBuildRooms(new int[] {-1,0,0,1,2}));
    }


}
