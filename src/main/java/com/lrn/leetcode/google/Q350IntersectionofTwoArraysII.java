package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q350IntersectionofTwoArraysII {
    /*
    * pd: Given two arrays, write a function to compute their intersection.
    * Each element in the result should appear as many times as it shows in both arrays.The result can be in any order.
    * assm: only +ve int, arr size < 10000, best time sol
    * appr:
    * */

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num: nums1) {
            freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        }
        List<Integer> res = new ArrayList<>();
        for(int num: nums2) {
            if(freqMap.getOrDefault(num,0) != 0) {
                res.add(num);
                freqMap.put(num, freqMap.get(num) -1);
            }
        }
        int[] rs = new int[res.size()];
        for(int idx=0; idx<res.size(); idx++) {
            rs[idx] = res.get(idx);
        }
        return rs;
    }

    public static void main(String[] args) {
        Q350IntersectionofTwoArraysII sol = new Q350IntersectionofTwoArraysII();
        LsUtil.printArray(sol.intersect(new int[]{1,2,2,1}, new int[] {2,2}));
        LsUtil.printArray(sol.intersect(new int[]{4,9,5}, new int[] {9,4,9,8,4}));
    }

}
