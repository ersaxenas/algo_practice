package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q347TopKFrequentElements {
    /*
    * pd: Given a non-empty array of integers, return the k most frequent elements.
    * assm: arr elem +ve int, arr len < 10000, best time sol
    * appr:
    * test cases:
    * */

    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length ==0) {
            return new int[]{};
        }
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int n: nums) {
            freqMap.put(n,freqMap.getOrDefault(n,0)+1);
        }
        for(int key: freqMap.keySet()) {
            final Integer freq = freqMap.get(key);
            if(bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

       List<Integer> res = new ArrayList<>();
        for(int idx=bucket.length-1; idx>=0; idx--) {
            if(bucket[idx] != null && res.size() <k) {
                res.addAll(bucket[idx]);
            }
        }
        int[] rs = new int[res.size()];
        for(int idx=0; idx<res.size(); idx++) {
            rs[idx] = res.get(idx);
        }
        return rs;
    }

}
