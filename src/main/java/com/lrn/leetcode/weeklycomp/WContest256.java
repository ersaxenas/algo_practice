package com.lrn.leetcode.weeklycomp;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class WContest256 {
    /*1984. Minimum Difference Between Highest and Lowest of K Scores
    https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
     * */

    public int minimumDifference(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        if (k == 1) return 0;
        Arrays.sort(nums);
        int ws = 0;
        for (int we = k - 1; we < nums.length; we++, ws++) {
            min = Math.min(min, nums[we] - nums[ws]);
        }
        return min;
    }

    public String kthLargestNumber(String[] nums, int k) {
        String result = null;
        PriorityQueue<String> queue = new PriorityQueue<>((n1, n2) -> {
            if(n1.length() == n2.length()) { // if length is same then a string compare can be used to find smaller integer
                return n1.compareTo(n2);
            }
            // if length is not same then which ever no. has more char is larger
            return Integer.compare(n1.length(), n2.length());
        });
        for(String num: nums) {
            queue.offer(num);
            if(queue.size() > k) queue.poll();
        }
        return String.valueOf(queue.peek());
    }

    // TLE
    public int minSessions(int[] tasks, int sessionTime) {
        LinkedList<Integer> list = new LinkedList<>();
        for(int task: tasks) {
            list.add(task);
        }
        Collections.sort(list);
        int cnt  = 0;
        while(list.size() > 1) {
            if(list.getLast() == sessionTime || list.getLast() + list.getFirst() > sessionTime) {
                list.removeLast();
                cnt++;
            } else {
                break;
            }
        }
        cnt = cnt + solveRec(list, sessionTime);
        System.out.println(cnt);
       return cnt;
    }

    public int solveRec(LinkedList<Integer> list, int sessionTime) {
        if(list.size() == 1 ) return 1;
        int ans = list.size();
        for(int idx = list.size()-1; idx >= 0; idx--) {
            int task = list.remove(idx);
            for(int pos=list.size()-1; pos >= 0; pos--) {
                if(list.get(pos) + task <= sessionTime) {
                    list.set(pos,list.get(pos)+task);
                    ans = Math.min(ans, solveRec(list, sessionTime));
                    list.set(pos, list.get(pos) - task);
                }
            }
            list.add(idx,task);
        }
        return ans;
    }

    /*https://leetcode.com/contest/weekly-contest-256/problems/number-of-unique-good-subsequences/
    * 1987. Number of Unique Good Subsequences
    * */

    public int numberOfUniqueGoodSubsequences(String binary) {
        int mod = (int)1e9 + 7, ends0 = 0, ends1 = 0, has0 = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                ends1 = (ends0 + ends1 + 1) % mod;
            } else {
                ends0 = (ends0 + ends1) % mod;
                has0 = 1;
            }
        }
        return (ends0 + ends1 + has0) % mod;
    }


    public static void main(String[] args) {
        WContest256 sol = new WContest256();
          System.out.println(sol.minSessions(new int[]{3,6,5,5,10,7}, 10) == 4);
          System.out.println(sol.minSessions(new int[]{3,1,3}, 14) == 1);
//        System.out.println(sol.minSessions(new int[]{7,7,4,3,3,8,10,10,12}, 12));
//        System.out.println(sol.minSessions(new int[]{7,4,3,8,10,12}, 12));
//        System.out.println(sol.minSessions(new int[]{7,4,3,8,10}, 12));
        System.out.println(sol.minSessions(new int[]{1, 2, 3, 4, 5}, 15) == 1);
        System.out.println(sol.minSessions(new int[]{2}, 3) == 1);
        System.out.println(sol.minSessions(new int[]{9,8,8,4,6}, 14) == 3);
    }

}
