package com.lrn.leetcode.weeklycomp;

import java.util.ArrayList;
import java.util.List;

public class WContest269 {

    /*
    * 2089. Find Target Indices After Sorting Array
    * */

    // better approach: couting sort
    public List<Integer> targetIndices2(int[] nums, int target) {
        int cnt = 0; /*Freq of target*/
        int numsLessThenTarget = 0;
        for(int n: nums) {
            if(n == target) {
                cnt++;
            } else if(n < target) {
                numsLessThenTarget++;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        int idx=numsLessThenTarget;
        while(cnt > 0) {
            result.add(idx++);
            cnt--;
        }
        return result;
    }

    // my sol : quick sort approach
    public List<Integer> targetIndices(int[] nums, int target) {
        int p1=0, p2=nums.length-1, idx=0;
        while(idx <= p2) {
            if(nums[idx] < target) {
                swap(nums, p1, idx);
                p1++;
                idx++;
            } else if(nums[idx] > target) {
                swap(nums, p2, idx);
                p2--;
            } else {
                idx++;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        if(p1 < nums.length && nums[p1] == target) {
            idx = p1;
            while(idx <= p2) {
                result.add(idx);
                idx++;
            }
        }
        return result;
    }
    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
    public static void main(String[] args) {
        WContest269 sol = new WContest269();


    }


}
