package com.lrn.leetcode.google;

public class Q153FindMinimumInRotatedSortedArray {
    /* 2022-04-22T07:38:04.317Z 
    https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
    * pd: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.
    * assm: non null, array size < 1000, best time sol
    * appr: binary search
    *
    * */

    /*
     lh      rh
    ------|------
    1 2 3 4 5 6 7  : rotate by 0: rh is sorted ( mid - 4 < end - 7) so go left end = mid (why not mid - 1? because elem at mid can be the smallest )
    7 1 2 3 4 5 6  : rotate by 1: rh is sorted ( mid - 3 < end - 6) so go left end = mid
    6 7 1 2 3 4 5  : rotate by 2: rh is sorted ( mid - 2 < end - 5) so go left end = mid
    5 6 7 1 2 3 4  : rotate by 3: rh is sorted ( mid - 1 < end - 4) so go left end = mid
    (when smallest elem moves in to right half)
    4 5 6 7 1 2 3  : rotate by 4: rh is NOT sorted ( mid - 7 > end - 3) so go right start = mid + 1 ( why not start = mid? because we know that
                                                                                                       elem at mid is greater than elem at end
                                                                                                       so it cannot be smallest elem)
    3 4 5 6 7 1 2  : rotate by 5: rh is NOT sorted ( mid - 6 > end - 2) so go right start = mid + 1
    2 3 4 5 6 7 1  : rotate by 6: rh is NOT sorted ( mid - 5 > end - 1) so go right start = mid + 1
    so
    When mid < end go left end = mid
    else go right start = mid +1
    * */

    public int findMin2(int[] nums) {
        int start = 0, end = nums.length-1, mid;
        while(start < end) {
            mid = start + (end - start)/2;
            if(nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return nums[start];
    }

    /* with dups
     lh      rh
    ------|------
    1 2 2 2 2 2 7  : rotate by 0: rh is sorted ( mid - 2 < end - 7) so go left end = mid (why not mid - 1? because elem at mid can be the smallest )
    7 1 2 2 2 2 2  : rotate by 1: now in rh mid not less than end and mid is also not greater than end so end --
    2 7 1 2 2 2 2  : rotate by 2: now in rh mid not less than end and mid is also not greater than end so end --
    2 2 7 1 2 2 2  : rotate by 3: rh is sorted ( mid - 1 < end - 2) so go left end = mid
    (when smallest elem moves in to right half)
    2 2 2 7 1 2 2  : rotate by 4: now in rh mid is greater than end so go right start = mid + 1 ( why not start = mid? because we know that
                                                                                                       elem at mid is greater than elem at end
                                                                                                       so it cannot be smallest elem)
    2 2 2 2 7 1 2  : rotate by 5: now in rh mid not less than end and mid is also not greater than end so end --
    2 2 2 2 2 7 1  : rotate by 6: now in rh mid is greater than end so go right start = mid + 1
    so
    When mid < end go left end = mid
    When mid > end go right start = mid + 1
    when mid is not greater or less than end just reduce the bound end --
    * */

    public int findMin3(int[] nums) {
        int start = 0, end = nums.length-1, mid;
        while(start < end) {
            mid = start + (end - start)/2;
            if(nums[mid] < nums[end]) {
                end = mid;
            } else if( nums[mid] > nums[end]){
                start = mid+1;
            } else {
                end--;
            }
        }
        return nums[start];
    }






    public int findMin(int[] nums) {
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int mid = start + (end-start)/2;
            int midElem = nums[mid];
            if(nums[start] <= midElem && midElem <= nums[end]) {
                return nums[start];
            }
            /* in a rotated array min elem will be in the unsorted half.
            * to visualize start rotating array by one element, you will see that left side array will be
            * unsorted and min elem will be apart of unsorted left. The moment min elem moves to right half of the array
            * left half will become sorted.
            * */
            if(nums[start] <= midElem) { // left is sorted
                start = mid+1;
            } else {
                end = mid;
            }
        }
      return nums[end];
    }

    public static void main(String[] args) {
        Q153FindMinimumInRotatedSortedArray sol = new Q153FindMinimumInRotatedSortedArray();
        System.out.println(sol.findMin(new int[]{0, 1, 2, 4, 5, 6, 7}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{1, 2, 4, 5, 6, 7, 0}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{2, 4, 5, 6, 7, 0, 1}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{5, 6, 7, 0, 1, 2, 4}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{6, 7, 0, 1, 2, 4, 5}) == 0 ? "passed" : "failed");
        System.out.println(sol.findMin(new int[]{7, 0, 1, 2, 4, 5, 7}) == 0 ? "passed" : "failed");
    }
}
