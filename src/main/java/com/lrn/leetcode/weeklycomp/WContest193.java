package com.lrn.leetcode.weeklycomp;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Contest193 {

    static class FlowerBloom {
        /**
         * Given an integer array bloomDay, an integer m and an integer k.
         * We need to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
         * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
         * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.
         * https://leetcode.com/contest/weekly-contest-193/problems/minimum-number-of-days-to-make-m-bouquets/
         *
         */
        /*approach BinarySearch
         * Time Complexity: O(nLog(maxdays))
         *  */

        public int getPossibleBouquetsForGivenDay(int[] flowerBloomDays, int currDay, int flowersNeededForBouquet) {
            /*
             * you need  k 'adjacent' flowers from the garden
             * */
            int bouquets =0, flowersBloomed=0;
            for(int bloomDay: flowerBloomDays) {
                if(bloomDay <= currDay) { // this flower has bloomed
                    flowersBloomed++;
                } else {
                    /*To make a bouquets we need adjacent flowers only and we are in the else part to this flower didnt bloom so reset counter.*/
                    flowersBloomed =0;
                }
                if(flowersBloomed == flowersNeededForBouquet) { // have enough flowers to make a bouquet
                    flowersBloomed=0;
                    bouquets++;
                }
            }
            return bouquets;
        }

        public int findMinDaysToMakeBouquets(int[] flowerBloomDays, int bouquets, int flowersNeededForBouquet) {
            if(flowerBloomDays.length < bouquets*flowersNeededForBouquet) return -1;
            int minDay = 0, maxDay = Integer.MIN_VALUE;
            for(int day: flowerBloomDays) {
                maxDay = Math.max(day, maxDay);
            }
            // binary search between minday =0 to maxday
            while(minDay < maxDay) {
                int midDay = minDay + ((maxDay - minDay)/2);
                int possibleBouquets = getPossibleBouquetsForGivenDay(flowerBloomDays, midDay, flowersNeededForBouquet);
                if(possibleBouquets < bouquets) {
                    minDay = midDay+1;
                } else  {
                    maxDay = midDay -1; // continue searching since we want min days.
                }
            }
            return minDay;
        }

        public static void main(String[] args) {
            FlowerBloom solutions = new FlowerBloom();
            int[] flowerBloomDays = {1,10,3,10,2};
            System.out.println(solutions.findMinDaysToMakeBouquets(flowerBloomDays, 3, 1));
            System.out.println(solutions.findMinDaysToMakeBouquets(flowerBloomDays, 3, 2));
            flowerBloomDays = new int[]{7,7,7,7,12,7,7};
            System.out.println(solutions.findMinDaysToMakeBouquets(flowerBloomDays, 2, 3));
            flowerBloomDays = new int[]{1000000000,1000000000};
            System.out.println(solutions.findMinDaysToMakeBouquets(flowerBloomDays, 1, 1));
            flowerBloomDays = new int[]{1,10,2,9,3,8,4,7,5,6};
            System.out.println(solutions.findMinDaysToMakeBouquets(flowerBloomDays, 4, 2));
        }
    }
    /*Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
       Return the running sum of nums.
    * */
    static class RunningSumArray {
       public int[] runningSum(int[] nums) {
           for(int idx=1; idx<nums.length; idx++) {
               nums[idx] = nums[idx] + nums[idx-1];
           }
           return nums;
       }

        public static void main(String[] args) {
            RunningSumArray runningSumArray = new RunningSumArray();
            int[] result = runningSumArray.runningSum(new int[]{1, 2, 3, 4});
            for (int i : result) {
                System.out.println(i);
            }
        }
    }

    static class LeastNumberAfterRemoval {
        public int findLeastNumOfUniqueInts(int[] arr, int K) {
            Map<Integer,Integer> freqmap = new HashMap<>();
            for(int elem: arr) {
                freqmap.put(elem, freqmap.getOrDefault(elem, 0) +1);
            }
            PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>((e1,e2) -> e1.getValue() - e2.getValue());
            int remove=0;
            while(K >0 && !minHeap.isEmpty()) {
                if(minHeap.poll().getValue() < K) {
                  remove++;
                }
                K--;
            }
            return freqmap.size() - remove;
        }

        public static void main(String[] args) {
            LeastNumberAfterRemoval leastNumberAfterRemoval = new LeastNumberAfterRemoval();
            System.out.println(leastNumberAfterRemoval.findLeastNumOfUniqueInts(new int[]{5,5,4},1));
            System.out.println(leastNumberAfterRemoval.findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2},3));
        }
    }

    static class BinarySearchExp {
        public void binarySearch(int[] nums, int k) {
            int start =0, end = nums.length-1;
            while(start < end) {
                int mid = start + ((end-start)/2);
                if(nums[mid] < k) {
                    start = mid +1;
                } else {
                    end = mid -1;
                }
            }
            System.out.println(String.format("start: %d, end: %d", start,end));
        }

        public static void main(String[] args) {
            BinarySearchExp binarySearchExp = new BinarySearchExp();
//            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10,12,15,20,30,40},0);
//            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10,12,15,20,30},0);
//            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10,12,15,20},0);
//            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10,12,15},0);
            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10,12},0);
            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1,10},0);
            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1,0,1},0);
            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2,-1},0);
            binarySearchExp.binarySearch(new int[]{-10,-7,-6,-5,-2},0);
        }
    }


}
