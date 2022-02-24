package com.lrn.leetcode.google;

public class Q11ContainerWithMostWater {
    /*
    * https://leetcode.com/problems/container-with-most-water/
    * PD: Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
    * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
    * Assumptions: array has only +ve elements, array has at least 2 elem, best time algorithm
    * Approach: two pointers
    * lp=0 , rp = arr.len (lp < rp)
    * if(lh < rh) : calculate area of rectangle with lh lp++
    * else : calculate area of rectangle with rh rp++
    * save max area
    * test case: [1,8,6,2,5,4,8,3,7] ans 49
    * */
   public int maxArea(int[] height) {
       int lp=0, rp = height.length-1, maxArea =0;
       while(lp < rp) {
           int lh = height[lp], rh = height[rp];
           int area;
           if(lh < rh) {
               area = lh * (rp - lp);
               while(lp < rp && lh >= height[lp]) {lp++;}
           } else {
               area = rh * (rp - lp);
               while(lp < rp && rh >= height[rp]) {rp--;}
           }
           maxArea = Math.max(maxArea, area);
       }
       return maxArea;
   }

    public static void main(String[] args) {
        Q11ContainerWithMostWater q11ContainerWithMostWater = new Q11ContainerWithMostWater();
        System.out.println(q11ContainerWithMostWater.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }



}
