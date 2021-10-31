package com.lrn.leetcode.google;

import java.util.LinkedList;
import java.util.List;

public class Q163MissingNumber {
    /*
    * pd: https://leetcode.com/problems/missing-ranges/
    * assm: -100 < num < 1000, arrlen < 1000, no dups, lower < num[i] < upper
    * appr:
    * test cases:
    * */

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        LinkedList<String> result = new LinkedList<>();
        int rangeStart = lower; /* start of the range */
        for(int num: nums) { /* start looking at each number */
            if(num < rangeStart) continue; /* num is not in the range so ignore*/
            if(num > rangeStart) { /* num is in range so add the range*/
                addRange(rangeStart, num-1, result);
            }
            /* num is not in range and since it is larger then upper bound stop and return the result. */
            if(num >= upper) return result;
            /*Have added range from rangeStart+1 to num-1 so now move lower bound to num+1*/
            rangeStart = num+1;
        }
        /*Have looked at all the numbers in the array. If rangeStart is less then upper add last range to list*/
        if(rangeStart <= upper) addRange(rangeStart,upper, result);
        return result;
    }

    public void addRange(int start, int end, List<String> result) {
        if(start == end) {
            result.add(String.valueOf(start));
        } else {
            result.add(String.valueOf(start)+"->"+String.valueOf(end));
        }
    }
}
