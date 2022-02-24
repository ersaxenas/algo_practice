package com.lrn.leetcode.google;

public class Q80RemoveDuplicatsFromSortedArray2 {
    /*2022-01-07T12:11:53.709Z
    * pd: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27987/Short-and-Simple-Java-solution-(easy-to-understand)
    * assm: arra len < 10000, arr conains only +ve nums, best time sol
    * appr:
    *
    * test cases:
    * */

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length < 2) {
            return nums == null ? 0 : nums.length;
        }
        int idx=0;
        for(int num : nums) {
            if(idx < 2 || num > nums[idx-2]) {
                nums[idx++] = num;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        Q80RemoveDuplicatsFromSortedArray2 sol = new Q80RemoveDuplicatsFromSortedArray2();
        System.out.println(sol.removeDuplicates(new int[]{1, 1, 1}));
        System.out.println(sol.removeDuplicates(new int[]{1, 1, 1,1,1,1,9}));
        System.out.println(sol.removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

}
