package com.lrn.leetcode.weeklycomp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BWContext60 {
    /*
    * 1991. Find the Middle Index in Array
    * https://leetcode.com/contest/biweekly-contest-60/problems/find-the-middle-index-in-array/
    * */

    /*
    * At middle index :
    * 1. sum of left side element  == sum of right side elements
    * 2. (sum of left side elements + element at middle index) == ( element at middle index + sum of right side elements)
    * Now take two arrays left and right.
    * Left array holds sum of elements on left side till index i.
    * Right array holds sum of elements on right side till index i.
    * Ex:
    * index       :    0  |  1  |  2  |  3  |  4
    * array       :    2  |  3  | -1  |  8  |  4
    * left array  :    2  |  5  |  4  |  12 |  16
    * right array :   16  | 14  | 11  |  12 |  4
    * Here at index 3  sum in the left array is equal to sum in the right array, so 3 is the middle index.
    * */

    public int findMiddleIndex(int[] nums) {
        int N = nums.length;
        int[] left = new int[N]; /*Array to hold sum from left side*/
        int[] right = new int[N]; /*Array to hold sum from right side*/

        /*Right side sum */
        for(int idx=N-1; idx >= 0; idx--) {
            right[idx] = ((idx + 1 >= N) ? 0 : right[idx+1]) + nums[idx];
        }
        /*Left side sum */
        for(int idx=0; idx < N; idx++) {
            left[idx] = ((idx-1 < 0) ? 0 : left[idx-1]) + nums[idx];
            if(left[idx] == right[idx]) return idx; // if left side and right sum matches found the middle index.
        }
        return -1;
    }

    /*1992. Find All Groups of Farmland*/
    public int[][] findFarmland(int[][] land) {
        List<int[]> result = new ArrayList<>();
        int rows = land.length, cols = land[0].length;
        for(int row=0; row < rows; row++) {
            for(int col=0; col < cols; col++) {
                if(land[row][col] == 1)   {
                    int mc = col;
                    int mr = row;
                    while(mc+1 < cols && land[row][mc+1] == 1) {
                        mc++;
                    }
                    while(mr+1 < rows && land[mr+1][col] == 1) {
                        mr++;
                    }
                    int[] arr = new int[] {row, col, mr,mc};
                    result.add(arr);
                    for(int r=row; r <= mr; r++) {
                        for(int c=col; c <= mc; c++) {
                            land[r][c]= -1;
                        }
                    }
                }
            }
        }
        int[][] rs = new int[result.size()][4];
        for(int idx=0; idx < rs.length; idx++) {
            rs[idx] = result.get(idx);
        }
        return rs;
    }

    public int[][] findFarmlandBFS(int[][] land) {
        List<int[]> result = new ArrayList<>();
        int rows = land.length, cols = land[0].length;
        for(int row=0; row < rows; row++) {
            for(int col=0; col < cols; col++) {
                if(land[row][col] == 1)   {
                    int[] arr = new int[] {row,col,row,col};
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{row,col});
                    while(!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int r = cell[0], c = cell[1];
                        land[r][c] = -1;
                        arr[2] = Math.max(arr[2],r);
                        arr[3] = Math.max(arr[3],c);
                        if(r+1 < rows && land[r+1][c] == 1) queue.offer(new int[] {r+1,c});
                        if(c+1 < cols && land[r][c+1] == 1) queue.offer(new int[] {r,c+1});
                    }
                    result.add(arr);
                }
            }
        }
        int[][] rs = new int[result.size()][4];
        for(int idx=0; idx < rs.length; idx++) {
            rs[idx] = result.get(idx);
        }
        return rs;
    }


    static class LockingTree {

        int[] parent; /*parent list*/
        int[] lock; /* array for lock : -1 == unlocked */
        Map<Integer, List<Integer>> childmap = new HashMap<>(); /* parent -> child map */

        public LockingTree(int[] parent) {
            this.parent = parent;
            lock = new int[parent.length];
            Arrays.fill(lock, -1); /* initially all nodes are unlocked */
            /*Build parent child map*/
            for (int idx = 0; idx < parent.length; idx++) {
                childmap.put(idx, childmap.getOrDefault(idx, new ArrayList<>()));
                if (parent[idx] != -1) {
                    childmap.put(parent[idx], childmap.getOrDefault(parent[idx], new ArrayList<>()));
                    childmap.getOrDefault(parent[idx], new ArrayList<>()).add(idx);
                }
            }
        }

        public boolean lock(int num, int user) {
            if (lock[num] == -1) { /* if node is unlocked then it can be locked by user */
                lock[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (lock[num] == user) { /* If node is locked by same user then unlock it*/
                lock[num] = -1;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            if (lock[num] != -1) return false; /* check if node is unlocked */
            if (!lockedDesc(num)) return false; /* check if any descendant node is locked */
            if (lockedAnc(num)) return false; /* check if any ancestor is locked */
            unlockDesc(num);/*unlock all child nodes*/
            lock(num, user); /*lock node*/
            return true;
        }

        /*DFS: find if any descendant is locked*/
        private boolean lockedDesc(int num) {
            if (lock[num] != -1) {
                return true;
            }
            List<Integer> childlist = childmap.getOrDefault(num, new ArrayList<>());
            for (Integer ch : childlist) {
                if (lockedDesc(ch)) {
                    return true;
                }
            }
            return false;
        }

        /*DFS: find if any ancestor is locked */
        private boolean lockedAnc(int num) {
            if (lock[num] != -1) {
                return true;
            }
            if (parent[num] != -1 && lockedAnc(parent[num])) {
                return true;
            }
            return false;
        }

        /*DFS : unlock all children*/
        private void unlockDesc(int num) {
            lock[num] = -1;
            List<Integer> childlist = childmap.getOrDefault(num, new ArrayList<>());
            for (Integer ch : childlist) {
                unlockDesc(ch);
            }
        }
    }

    public static void main(String[] args) {
        BWContext60 sol = new BWContext60();
        LockingTree lockingTree = new LockingTree(new int[] {-1,0,3,1,0});
        System.out.println(lockingTree.lock(2, 2));
        System.out.println(lockingTree.unlock(2, 3));
        System.out.println(lockingTree.unlock(2, 2));
        System.out.println(lockingTree.lock(4, 5));
        System.out.println(lockingTree.upgrade(0, 1));
        System.out.println(lockingTree.lock(0, 1));
    }




}
