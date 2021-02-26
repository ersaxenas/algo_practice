package com.lrn.practice;

import com.lrn.leetcode.google.ListNode;

public class LeetCodePractise {

       public int maxArea(int[] height) {
           int lh = 0, lp =0, rh=0, rp=height.length-1, maxArea=0;
           while(lp < rp) {
               lh = height[lp];
               rh = height[rp];
               if(lh < rh) {
                   maxArea = Math.max(maxArea, (lh * (rp-lp)));
                   while(lp < rp && lh >= height[lp]) lp++;
               } else {
                   maxArea = Math.max(maxArea, (rh * (rp-lp)));
                   while(lp < rp && rh >= height[rp]) rp--;
               }
           }
           return maxArea;
       }



}
