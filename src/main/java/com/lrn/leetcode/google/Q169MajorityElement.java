package com.lrn.leetcode.google;

public class Q169MajorityElement {
    /* https://leetcode.com/problems/majority-element/
    * pd: Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
Example 1:
Input: [3,2,3]
Output: 3
Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
    * assm: non nul elem, array size < 10000, best time sol
    * appr: http://www.cs.utexas.edu/~moore/best-ideas/mjrty/example.html
    * We will sweep down the sequence starting at the pointer position shown above.
As we sweep we maintain a pair consisting of a current candidate and a counter. Initially, the current candidate is unknown and the counter is 0.
When we move the pointer forward over an element e:
If the counter is 0, we set the current candidate to e and we set the counter to 1.
If the counter is not 0, we increment or decrement the counter according to whether e is the current candidate.
When we are done, the current candidate is the majority element, if there is a majority.
    *
    * */

   public int majorityElement(int[] nums) {
       int count=1, mj=nums[0];
       for(int idx=1; idx<nums.length; idx++) {
           if(count == 0) {
             count++;
             mj = nums[idx];
           } else if(nums[idx] == mj) {
               count++;
           } else {
               count--;
           }
       }
       return mj;
   }

    public static void main(String[] args) {
        Q169MajorityElement sol = new Q169MajorityElement();
        System.out.println((sol.majorityElement(new int[]{3, 2, 3}) == 3) ? "passed" : "failed");
        System.out.println((sol.majorityElement(new int[]{2,2,1,1,1,2,2}) == 2) ? "passed" : "failed");
    }
}
