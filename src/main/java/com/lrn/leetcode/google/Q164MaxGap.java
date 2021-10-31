package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q164MaxGap {
    /*
    * pd: https://leetcode.com/problems/maximum-gap/
    * assm: arr len < 1000, 0 < elem < 1000, best time sol
    * appr: https://leetcode.com/problems/maximum-gap/discuss/1241681/JavaPython-Bucket-Idea-with-Picture-Clean-and-Concise-O(N)
    * test cases:
    *
    * */

    public int maximumGap(int[] nums) {
        if(nums.length <= 1) return 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, N = nums.length;
        for(int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if(min == max) return 0;
        int bucketSize = (int) Math.ceil((double)(max-min)/(N-1));
        int[] minbucket = new int[N];
        int[] maxbucket = new int[N];
        Arrays.fill(minbucket, Integer.MAX_VALUE);
        Arrays.fill(maxbucket, Integer.MIN_VALUE);
        for(int num: nums) {
            int idx = (num - min) / bucketSize;
            minbucket[idx] = Math.min(minbucket[idx], num);
            maxbucket[idx] = Math.max(maxbucket[idx], num);
        }
        int maxGap = bucketSize, prevmax = maxbucket[0];
        for(int idx=1; idx < minbucket.length; idx++) {
            if(minbucket[idx] == Integer.MAX_VALUE) continue;
            maxGap = Math.max(maxGap, minbucket[idx] - prevmax);
            prevmax = maxbucket[idx];
        }
        return maxGap;
    }

    public static void main(String[] args) {
        Q164MaxGap sol = new Q164MaxGap();
        sol.maximumGap(new int[] {3,6,9,1});
    }

}
