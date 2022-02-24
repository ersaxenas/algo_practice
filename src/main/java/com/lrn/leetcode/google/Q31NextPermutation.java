package com.lrn.leetcode.google;

public class Q31NextPermutation {

    /* 2021-12-11T14:27:02.709Z
     https://leetcode.com/problems/next-permutation/
    * https://leetcode.com/articles/next-permutation/#:~:text=Implement%20next%20permutation%2C%20which%20rearranges,use%20only%20constant%20extra%20memory.
    * */
    public void nextPermutation(int[] nums) {
        int i = nums.length-2;
        while(i >= 0 && nums[i+1] <= nums[i]) { // check if num at i+1 is smaller then num at i
          i--;
        }
        if(i>=0) { // found a smaller number out of place.
           int j = nums.length -1;
           // find number just greater then num at i
            while(j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i , j);
        }
        reverse(nums, i+1);
    }

    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    void reverse(int[] nums, int idx) {
        int start = idx, end = nums.length-1;
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Q31NextPermutation q31NextPermutation = new Q31NextPermutation();
        int[] nums = {5, 4, 7, 5, 3, 2};
        q31NextPermutation.nextPermutation2(nums);
        LsUtil.printArray(nums);
    }


}
