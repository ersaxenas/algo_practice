package com.lrn.leetcode.weeklycomp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BWContest61 {
    /*
    * 2006. Count Number of Pairs With Absolute Difference K
    * */

    public int countKDifference(int[] nums, int k) {
        int cnt=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num: nums) {
            if(map.containsKey(num - k)) {
                cnt = cnt + map.get(num - k);
            }
            if(map.containsKey(num + k)) {
                cnt = cnt + map.get(num + k);
            }
            map.put(num, map.getOrDefault(num,0)+1);
        }
        return cnt;
    }
    /*
    * 2007. Find Original Array From Doubled Array
    * */



    // TLE
    public int[] findOriginalArray(int[] changed) {
        if(changed == null || changed.length % 2 != 0) return new int[0];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: changed) {
            minHeap.offer(num);
        }
        int[] result = new int[changed.length/2];
        int idx=0;
        while(!minHeap.isEmpty()) {
            int min = minHeap.poll();
            if(minHeap.remove(min * 2)) {
                result[idx++] = min;
            } else {
                return new int[0];
            }
        }
        return result;
    }

    public int[] findOriginalArray2(int[] changed) {
        if(changed == null || changed.length % 2 != 0) return new int[0];
        int[] result = new int[changed.length/2];
        // sorted map
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num: changed) {
            map.put(num, map.getOrDefault(num, 0) +1);
        }
        int ridx=0;
        for(int num: map.keySet()) {
            int numfreq = map.get(num);
            // if smallest num is present x times then num + num must also be present >= x times
            if(numfreq > map.getOrDefault(num + num, 0)) return new int[0];
            for(int idx=0; idx < map.get(num); idx++) {
                result[ridx++] = num;
                map.put(num+num, map.get(num+num) -1);
            }
        }

        return result;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        long[] dp = new long[n + 1];
        int ride = 0;
        for(int point = 1; point <= n; ++point) {
            dp[point] = Math.max(dp[point], dp[point - 1]);
            while (ride < rides.length) {
                int rideStart = rides[ride][0];
                if (!(rideStart == point)) break; // ride point point < point
                int rideEnd = rides[ride][1];
                dp[rideEnd] = Math.max(
                        dp[rideEnd],
                        dp[point] + rideEnd - rideStart + rides[ride][2]);
                ++ride;
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        BWContest61 sol = new BWContest61();
        sol.findOriginalArray2(new int[] {1,3,4,2,6,8});
    }




}
