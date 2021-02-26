package com.lrn.leetcode.google;

public class Q27RemoveElem {
    /*
    * https://leetcode.com/problems/remove-element/
    * pd: Given an array nums and a value val, remove all instances of that value in-place and return the new length.
    * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
    * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
    * Assmp: arr len 0 < N < int max, best time solution, array elem != null
    * aprc: take two indexes p1 , p2
    *       if [p2] != K then [p1++] = [p2]
    *       p2++
    * test cases : [1,2,3,3,4] res = [1,2,4,3,4] int = 3
    * test cases : [0,0,0,0,0] res = [0,0,0,0,0] int = 1
    * */

    public int removeElement(int[] nums, int val) {
       if(nums == null || nums.length == 0) {
           return 0;
       }
       int p1=0;
       for(int p2=0; p2<nums.length; p2++) {
           if(nums[p2] != val) {
               nums[p1++] = nums[p2];
           }
       }
       return p1;
    }

    public static void main(String[] args) {
        Q27RemoveElem q27RemoveElem = new Q27RemoveElem();
        System.out.println(q27RemoveElem.removeElement(new int[] {1,2,3,3,4},3));
        System.out.println(q27RemoveElem.removeElement(new int[] {0,0,0,0},0));
        System.out.println(q27RemoveElem.removeElement(new int[] {3,2,2,3},3));
    }

}
