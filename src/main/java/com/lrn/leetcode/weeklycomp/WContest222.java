package com.lrn.leetcode.weeklycomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Contest222 {
    /*
    * 1710. Maximum Units on a Truck
    * pd: https://leetcode.com/contest/weekly-contest-222/problems/maximum-units-on-a-truck/
    * */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a1, a2) -> a2[1] - a1[1]);
        for(int[] bt: boxTypes) {
            maxHeap.add(bt);
        }
        int size=0, units =0;
        while(!maxHeap.isEmpty() && size < truckSize) {
            int[] top = maxHeap.poll();
            System.out.println(top[0]+":"+top[1]);
            int boxno = top[0];
            while(boxno > 0 && size < truckSize) {
                units += top[1];
                size++;
                boxno--;
            }
        }
        return units;
    }
    public int maxUnits(int[][] boxTypes, int truckSize) {
        /*sort array by units decreasing order since our goal is to fit maximum units in the truck*/
        Arrays.sort(boxTypes, (bx1, bx2) -> bx2[1] - bx1[1]);
        int unitCount =0;
        for(int[] boxType: boxTypes) {
            /*if we have capacity to fit all the boxes then take all or take as per available capacity*/
            int boxCount = Math.min(truckSize, boxType[0]);
            truckSize = truckSize - boxCount;
            unitCount = unitCount + boxCount * boxType[1]; /*total units = no. of boxes * units in each box*/
            if(truckSize <= 0) {
                break; // can't fit anymore boxes
            }
        }
        return unitCount;
    }

    /*
    * pd: 1711. Count Good Meals
    * https://leetcode.com/contest/weekly-contest-222/problems/count-good-meals/
    *
    * */
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        long dishes =0;
        List<Long> twopow = new ArrayList<>();
        for(int idx=0; idx<=22; idx++) {
            twopow.add((long)Math.pow(2,idx));
        }
        for(int elem: deliciousness) {
            for(Long sum : twopow) {
                final int elemNeeded = (int) (sum - elem);
                if(freqMap.containsKey(elemNeeded)) {
                    dishes += freqMap.get(elemNeeded);
                    dishes %= 1000000007;
                }
            }
            freqMap.put( elem, freqMap.getOrDefault(elem,0)+1);
        }
        return (int)dishes;
    }
    /*
    * pd: 1712. Ways to Split Array Into Three Subarrays
    * https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/discuss/999113/JavaScala-Detailed-Explanation-Prefix-Sum-Binary-Search
    * */

    public int waysToSplit(int[] nums) {
        long ways = 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        // prep sum array
        for(int idx=1; idx<nums.length; idx++) {
            sum[idx] = nums[idx] + sum[idx-1];
        }
        // find range
        for(int idx=0; idx<nums.length; idx++) {
            if(sum[idx] > sum[sum.length-1] - sum[idx]) break; // very large elem at the idx
            int rangeStart = findRange(sum, idx, true);
            int rangeEnd = findRange(sum, idx, false);
            if(rangeStart == -1 || rangeEnd == -1) continue;
            int range = rangeEnd - rangeStart +1;
            ways += range;
        }
        return (int)(ways % (1e9 + 7));
    }

    public int findRange(int[] sum, int idx, boolean isLeftRange ) {
       int index =-1;
       int start = idx+1, end = sum.length-2, leftsum=sum[idx]; // len -2 since right sum is at len-1;
       while(start <= end) {
           int midIdx = start + (end-start) / 2;
           int midsum = sum[midIdx] - leftsum;
           int rightsum = sum[sum.length-1] - sum[midIdx];
           if(leftsum <= midsum && midsum <= rightsum) {
              index = midIdx;
              if(isLeftRange) {
                  end = midIdx-1; // keep searching left
              } else {
                  start = midIdx+1; // keep searching right
              }
           } else if(midsum < leftsum) { // go right
               start = midIdx+1;
           } else {
               end = midIdx -1;
           }
       }
       return index;
    }

    public int minOperations(int[] target, int[] arr) {
      Map<Integer, Integer> indexMap = new HashMap<>();
      for(int idx=0; idx<target.length; idx++) {
          indexMap.put(target[idx],idx);
      }
      // list will main indexes in increasing order
      LinkedList<Integer> longestIncreasingSeqIdx = new LinkedList<>();
      for(int num: arr) { // find largest increasing seq
          if(!indexMap.containsKey(num)) continue;
          // if linked list if empty or if largest index (last index) in the list is smaller then the index of current num
          final Integer numIndex = indexMap.get(num);
          if(longestIncreasingSeqIdx.isEmpty() || numIndex > longestIncreasingSeqIdx.getLast()) {
            // since index of num is larger the last index then just add
              longestIncreasingSeqIdx.add(numIndex);
              continue;
          }
          // numIndex is smaller then the last index so now we will replace an element in the list with smaller index
          // element to be replaced -> first num smaller then numIndex
          // binary search since longestIncreasingSeqIdx list is sorted.
          int start =0, end = longestIncreasingSeqIdx.size()-1, mid=0;
          while(start < end) {
              mid = start + (end-start)/2;
              if(longestIncreasingSeqIdx.get(mid) < numIndex) {
                  start = mid+1;
              } else {
                  end = mid;
              }
          }
         longestIncreasingSeqIdx.set(start, numIndex);
      }
      return target.length - longestIncreasingSeqIdx.size();

    }



    public static void main(String[] args) {
       Contest222 sol = new Contest222();
//        System.out.println(sol.countPairs(new int[]{1,1,1,3,3,3,7}));
//        System.out.println(sol.countPairs(new int[]{1, 3, 5, 7, 9}));
//        System.out.println(sol.waysToSplit(new int[]{1,2,2,2,5,0}));
//        System.out.println(sol.waysToSplit(new int[]{1, 1, 1}));

        System.out.println(sol.minOperations(new int[]{16,7,20,11,15,13,10,14,6,8}, new int[]{11,14,15,7,5,5,6,10,11,6}));
        System.out.println(sol.minOperations(new int[]{6, 4, 8, 1, 3, 2}, new int[]{4, 7, 6, 2, 3, 8, 6, 1}));


    }

}



