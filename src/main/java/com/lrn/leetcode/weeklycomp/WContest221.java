package com.lrn.leetcode.weeklycomp;


import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class WContest221 {
    /*
     * pd : 1704. Determine if String Halves Are Alike
     * https://leetcode.com/contest/weekly-contest-221/problems/determine-if-string-halves-are-alike/
     * */

    public boolean halvesAreAlike(String s) {
        // since array length is always even look at the chars from beginning and end of the array
        // when froward direction index (low) encounters a vowel -> increment the count
        // when backward direction index (hi) enconters a vowel -> decreament the count
        int vowelCount = 0, low = 0, hi = s.length() - 1;
        Set<Character> charSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        while (low < hi) {
            if (charSet.contains(s.charAt(low))) { // found vowel
                vowelCount++;
            }
            if (charSet.contains(s.charAt(hi))) { // found vowel
                vowelCount--;
            }
            low++;
            hi--;
        }
        return vowelCount == 0; // if vowel count is 0 means String has equal no. of vowels in the both the halves
    }


    /**/
    class AppleBag {
        int apple = 0, expireDay = 0;

        public AppleBag(int apple, int expireDay) {
            this.apple = apple;
            this.expireDay = expireDay;
        }

    }

    public int eatenApples(int[] apples, int[] days) {
        int dayEat = 0;
        PriorityQueue<AppleBag> minHeap = new PriorityQueue<>((d1, d2) -> d1.expireDay - d2.expireDay);
        for (int day = 0; day < apples.length || !minHeap.isEmpty(); day++) {
            if (day < apples.length && apples[day] != 0) {
                // tree is producing apples today so create a bag and put in the heap
                AppleBag appleBag = new AppleBag(apples[day], day + days[day]);
                minHeap.add(appleBag);
            }
            // remove expired apples from heap
            while (!minHeap.isEmpty() && (minHeap.peek().expireDay <= day || minHeap.peek().apple == 0)) {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                AppleBag appleBag = minHeap.poll();
                appleBag.apple--; // eat an apple
                dayEat++; // today found an apple to eat
                if (appleBag.apple > 0) { // remove empty bag
                    minHeap.add(appleBag);
                }
            }
        }
        return dayEat;
    }

    /**/
    public int[] findBall(int[][] grid) {
        System.out.println("Start....");
        int[] firstRow = new int[grid[0].length];
        for (int col = 0; col < grid[0].length; col++) {
            firstRow[col] = dfs(grid, 0, col);
            System.out.println();
        }
        return firstRow;
    }

    public int dfs(int[][] grid, int row, int col) {
        int[] nextCell = getNextCell(grid, row, col);
        if (nextCell != null) {
            if (nextCell[0] >= grid.length) {
                return nextCell[1];
            }
            return dfs(grid, nextCell[0], nextCell[1]);
        }
        return -1;
    }


    public int[] getNextCell(int[][] grid, int row, int col) {
        int rows = grid.length, cols = grid[0].length;
        int[] cell = null;
        System.out.print(row + ":" + col + " > ");
        if (grid[row][col] == 1 && col + 1 < cols && grid[row][col + 1] == 1) {
            System.out.print((row + 1) + "::" + (col + 1));
            cell = new int[]{row + 1, col + 1};
        } else if (grid[row][col] == -1 && col - 1 >= 0 && grid[row][col - 1] == -1) {
            System.out.print((row + 1) + "::" + (col - 1));
            cell = new int[]{row + 1, col - 1};
        }
        System.out.println();
        return cell;
    }
    /*
     * pd: 1707. Maximum XOR With an Element From Array
     * https://leetcode.com/contest/weekly-contest-221/problems/maximum-xor-with-an-element-from-array/
     * */

    static class XQuery {
        int x, m;
        int idx;
        int maxXor;
    }

    static class BTrie {
        BTrie[] child = new BTrie[2];
        int prefixVal;
    }

    public void insertNum(BTrie root, int num) {
        BTrie node = root;
        for (int idx = 31; idx >= 0; idx--) {
            int bit = (num >> idx) & 1; // get idxth bit
            if (node.child[bit] == null) {
                node.child[bit] = new BTrie();
            }
            node = node.child[bit];
        }
        node.prefixVal = num;
    }

    public int findMaxXor(BTrie root, int num) {
        BTrie node = root;
        for (int idx = 31; idx >= 0; idx--) {
            int bit = (num >> idx) & 1; // get idxth bit
            int bitToggle = (bit == 0) ? 1 : 0;
            if (node.child[bitToggle] != null) {
                node = node.child[bitToggle];
            } else {
                node = node.child[bit];
            }
        }
        return node.prefixVal ^ num; // do xor here -- good one, storing the value helps
    }

    public int[] maxXor(int[] nums, int[][] queries) {
        List<XQuery> qlist = new ArrayList<>();
        for (int idx = 0; idx < queries.length; idx++) {
            XQuery q = new XQuery();
            q.x = queries[idx][0];
            q.m = queries[idx][1];
            q.idx = idx;
            qlist.add(q);
        }
        Collections.sort(qlist, (e1, e2) -> e1.m - e2.m);
        Arrays.sort(nums);

        BTrie root = new BTrie();
        int numidx = 0;
        int[] result = new int[qlist.size()];
        for (XQuery q : qlist) {
            // add to trie
            while (numidx < nums.length && nums[numidx] <= q.m) {
                insertNum(root, nums[numidx++]);
            }
            if (numidx != 0) { // since we have sorted nums array so this will be true only if trie has some data means q.m has value larger then some elem in array
                final int maxXor = findMaxXor(root, q.x);
                result[q.idx] = maxXor;
            } else {
                result[q.idx] = -1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        WContest221 sol = new WContest221();
/*
        System.out.println(sol.halvesAreAlike("book"));
        System.out.println(sol.eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}));
        System.out.println(sol.eatenApples(new int[]{3, 0, 0, 0, 0, 2}, new int[]{3, 0, 0, 0, 0, 2}));
*/
        LsUtil.printArray(sol.maxXor(new int[]{0, 1, 2, 3, 4}, new int[][]{{3, 1}, {1, 3}, {5, 6}}));
        LsUtil.printArray(sol.maxXor(new int[]{5,2,4,6,6,3}, new int[][]{{12, 4}, {8,1}, {6,3}}));
    }
}








