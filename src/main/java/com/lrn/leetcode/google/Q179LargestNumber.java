package com.lrn.leetcode.google;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Q179LargestNumber {
    /*2022-05-17T06:56:35.533Z
    * pd:https://leetcode.com/problems/largest-number/
    * assm: arr len < 1000, num 0 to 100, best time sol
    * appr: https://leetcode.com/problems/largest-number/discuss/53158/My-Java-Solution-to-share
    * test cases:
    * */
    public String largestNumber(int[] nums) {
        Map<Integer, PriorityQueue<String>> nummap = new HashMap<>();
        // Comparator to decide which string should come first in concatenation
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here, so we can do append() later
            }
        };
        for(int num: nums) {
            int n = num;
            while( n >= 10) n = n/10;
            PriorityQueue<String> pq = nummap.getOrDefault(n,new PriorityQueue<>(comp));
            pq.add(String.valueOf(num));
            nummap.put(n,pq);
        }
        StringBuilder sbr = new StringBuilder();
        for(int idx=9; idx >= 0; idx--) {
            PriorityQueue<String> pq = nummap.getOrDefault(idx,new PriorityQueue<>(comp));
            while(!pq.isEmpty()) {
                sbr.append(pq.poll());
            }
        }
        if(sbr.charAt(0) == '0') return "0";
        return sbr.toString();
    }

    public static void main(String[] args) {
        Q179LargestNumber sol = new Q179LargestNumber();
        System.out.println(sol.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }
}
