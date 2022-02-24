package com.lrn.leetcode.google;

import java.util.HashMap;

public class Q128LongestConsecutiveSequence {
    /* https://leetcode.com/problems/longest-consecutive-sequence
    * pd: Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
Your algorithm should run in O(n) complexity.
Example:
Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
    * assm: arr contains non null, no. of elem < 1000, best time solution
    * appr:
    *
    * Test cases:
    *
    * */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> seq = new HashMap<>();
        int max = 0;
        for (int elem : nums) {
            if (!seq.containsKey(elem)) {
                int elemOnLeftSide = seq.getOrDefault(elem - 1, 0); /*elems on the left side*/
                int elemOnRightSide = seq.getOrDefault(elem + 1, 0); /*elem on the right side*/
                int totalElemInSeq = elemOnLeftSide + elemOnRightSide + 1; /*new total of the series*/
                max = Math.max(max, totalElemInSeq);
                int firstElemOfSeq = elem - elemOnLeftSide;
                int lastElemOfSeq = elem + elemOnRightSide;
                seq.put(elem,totalElemInSeq); /*insert elem here to avoid double counting in case of duplicates */
                seq.put(firstElemOfSeq, totalElemInSeq);
                seq.put(lastElemOfSeq, totalElemInSeq);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Q128LongestConsecutiveSequence sol = new Q128LongestConsecutiveSequence();
        System.out.println(sol.longestConsecutive(new int[]{1, 3, 2}));
        System.out.println(sol.longestConsecutive(new int[]{1, 3, 5}));
        System.out.println(sol.longestConsecutive(new int[]{1, 3, 5, 7, 9, 8, 2, 4, 6}));
        System.out.println(sol.longestConsecutive(new int[]{4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3}));
    }


}
