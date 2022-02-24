package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q228SummaryRange {
    /*
     * pd: https://leetcode.com/problems/summary-ranges
     * appr: https://leetcode.com/problems/summary-ranges/discuss/63219/Accepted-JAVA-solution-easy-to-understand
     * assm: arr len < 1000, -1000 < val < 1000, no dups and sorted asc, best time sol
     * test cases:
     * */

    // easy to understand.
    public List<String> summaryRanges2(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length == 0) return result;
        for(int idx=0; idx<nums.length; idx++) {
            int start = idx;
            while( idx+1 < nums.length && (nums[idx+1] - nums[idx]) == 1 ) {
                idx++;
            }
            if(start == idx) {
                result.add(String.valueOf(nums[start]));
            } else {
                result.add(nums[start]+"->"+nums[idx]);
            }
        }
        return result;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int ws = 0;
        for (int we = 1; we < nums.length; we++) {
            if (nums[we - 1] + 1 != nums[we]) {
                if (ws == we - 1) {
                    result.add(String.valueOf(nums[ws]));
                } else {
                    result.add(nums[ws] + "->" + nums[we - 1]);
                }
                ws = we;
            }
        }
        if (ws == nums.length - 1) {
            result.add(String.valueOf(nums[ws]));
        } else {
            result.add(nums[ws] + "->" + nums[nums.length - 1]);
        }
        return result;
    }

}
