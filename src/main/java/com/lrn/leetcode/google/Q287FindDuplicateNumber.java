package com.lrn.leetcode.google;

public class Q287FindDuplicateNumber {
    /* https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
    * pd: Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one duplicate number in nums, return this duplicate number.
Follow-ups:
How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem without modifying the array nums?
Can you solve the problem using only constant, O(1) extra space?
Can you solve the problem with runtime complexity less than O(n2)?
    * assm: no null elem, array size < Int.MAX, best time sol, there will always be a dup number
    * appr: linklist cycle approach
    * Test Case:
    * Input: nums = [1,3,4,2,2] Output: 2
    * Input: nums = [3,1,3,4,2] Output: 3
    * Input: nums = [1,1] Output: 1
    * Input: nums = [1,1,2] Output: 1
    * */

    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length ==0) {
            return -1;
        }
        int slow=0, fast =0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        fast =0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    public static void main(String[] args) {
        Q287FindDuplicateNumber sol = new Q287FindDuplicateNumber();
        System.out.println(sol.findDuplicate(new int[]{1, 3, 4, 2, 2}) == 2 ? "pass" : "fail");
        System.out.println(sol.findDuplicate(new int[]{3,1,3,4,2}) == 3 ? "pass" : "fail");
        System.out.println(sol.findDuplicate(new int[]{1,1}) == 1 ? "pass" : "fail");
        System.out.println(sol.findDuplicate(new int[]{1,1,2}) == 1 ? "pass" : "fail");
    }

}
