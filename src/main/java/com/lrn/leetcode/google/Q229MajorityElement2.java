package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q229MajorityElement2 {
    /*
    * pd: iven an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
Follow-up: Could you solve the problem in linear time and in O(1) space?
    * assm: non null elem, array len < int.max, best time sol.
    * appr: Boyer-moore algo.
    * https://leetcode.com/problems/majority-element-ii/discuss/63520/Boyer-Moore-Majority-Vote-algorithm-and-my-elaboration
    * test cases:
    * Input: nums = [3,2,3] Output: [3]
    * Input: nums = [1] Output: [1]
    * Input: nums = [1,2] Output: [1,2]
    * */

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0 ) {
            return res;
        }
        int num1=nums[0], num2=nums[0], cnt1=0, cnt2=0;
        for(int idx=0; idx<nums.length; idx++) {
            int val = nums[idx];
            if(val == num1) {
                cnt1++;
            } else if(val == num2) {
                cnt2++;
            } else if(cnt1==0) {
                num1=val;
                cnt1=1;
            } else if(cnt2 == 0) {
                num2= val;
                cnt2=1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = cnt2 = 0;
        for(int num: nums) {
            if(num == num1) {
                cnt1++;
            } else if(num == num2) {
                cnt2++;
            }
        }
        if(cnt1 > nums.length/3) {
            res.add(num1);
        }
        if(cnt2 > nums.length/3) {
            res.add(num2);
        }
        return res;
    }

    public static void main(String[] args) {
        Q229MajorityElement2 sol = new Q229MajorityElement2();
        LsUtil.printList(sol.majorityElement(new int[]{3, 2, 3}));
        LsUtil.printList(sol.majorityElement(new int[]{1}));
        LsUtil.printList(sol.majorityElement(new int[]{1,2}));
    }

}
