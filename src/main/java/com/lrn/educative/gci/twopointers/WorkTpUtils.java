package com.lrn.educative.gci.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WorkTpUtils {
    /*
    Fundamental: Given that the input array is sorted, an efficient way would be to start with one pointer in the beginning and another pointer at the end. At every step, we will see if the numbers pointed by the two pointers add up to the target sum. If they do not, we will do one of two things:

If the sum of the two numbers pointed by the two pointers is greater than the target sum, this means that we need a pair with a smaller sum. So, to try more pairs, we can decrement the end-pointer.
If the sum of the two numbers pointed by the two pointers is smaller than the target sum, this means that we need a pair with a larger sum. So, to try more pairs, we can increment the start-pointer.
    * */

    /*Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.*/
    public static int[] findSumInSortedArray(int[] arr, int targetSum) {
        assert arr != null;
        int[] result = null;
        int pFwd=0, pBwd=arr.length -1;
        while(pFwd < pBwd) {
            int sum = arr[pFwd] + arr[pBwd];
            if(sum < targetSum) {
              pFwd++;
            } else if(sum > targetSum) {
              pBwd--;
            } else {
                System.out.println(String.format("E1 = %d (%d), E2 = %d (%d), Sum = %d", pFwd,arr[pFwd],pBwd,arr[pBwd],sum));
                result = new int[] {pFwd,pBwd};
                break;
            }
        }
        return (result == null) ? new int[] {-1,-1} : result;
    }
    /*An Alternate approach
Instead of using a two-pointer or a binary search approach, we can utilize a HashTable to search for the required pair. We can iterate through the array one number at a time. Let’s say during our iteration we are at number ‘X’, so we need to find ‘Y’ such that “X + Y == TargetX+Y==Target”. We will do two things here:

1. Search for ‘Y’ (which is equivalent to “Target - XTarget−X”) in the HashTable. If it is there, we have found the required pair.
2. Otherwise, insert “X” in the HashTable, so that we can search it for the later numbers.*/

    public static int[] findSumInSortedArrayUsingHashTable(int[] arr, int targetSum) {
      assert arr != null;
      int[] result = null;
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int idx = 0; idx < arr.length; idx++) {
            int currElement = arr[idx];
            final int elementNeededTotargetSum = targetSum - currElement;
            if(valueIndexMap.containsKey(elementNeededTotargetSum)) {
                /*found elements*/
                System.out.println(String.format("E1 = %d (%d), E2 = %d (%d), Sum = %d", valueIndexMap.get(elementNeededTotargetSum), elementNeededTotargetSum,idx,currElement,targetSum));
                result = new int[] {valueIndexMap.get(elementNeededTotargetSum),idx};
                break;
            } else {
              valueIndexMap.put(currElement, idx);
            }
        }
        return (result == null) ? new int[] {-1,-1} : result;
    }

//    public static void main(String[] args) {
//        findSumInSortedArray(new int[] { 1, 2, 3, 4, 6 }, 6);
//        findSumInSortedArray(new int[] { 2, 5, 9, 11 }, 11);
//        System.out.println("=======");
//        findSumInSortedArrayUsingHashTable(new int[] { 1, 2, 3, 4, 6 }, 6);
//        findSumInSortedArrayUsingHashTable(new int[] { 2, 5, 9, 11 }, 11);
//    }

    /*Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after removing the duplicates in-place return the new length of the array.*/
    public static int removeDuplicateFromSortedArray(int[] arr) {
        assert arr != null && arr.length > 1;
        int pointer1 = 1; /*increment only for non duplicates so it points to last non duplicate element*/
        for (int pointer2 = 0; pointer2 < arr.length; pointer2++) { /*both pointer start from same position. pointer2 keeps incrementing.*/
            if(arr[pointer1-1] != arr[pointer2]) { /*compare previous element with current one*/
                 /*if non duplicate swap*/
                arr[pointer1] = arr[pointer2];
                pointer1++;
            }
        }
        return pointer1;
    }

//    public static void main(String[] args) {
//        int[] arr = new int[] { 2, 3, 3, 3, 6, 9, 9 };
//        System.out.println(removeDuplicateFromSortedArray(arr));
//
//        arr = new int[] { 2, 2, 2, 11 };
//        System.out.println(removeDuplicateFromSortedArray(arr));
//    }

    /*Given an unsorted array of numbers and a target ‘key’, remove all instances of ‘key’ in-place and return the new length of the array.*/
    public static int removeAllInstancesOfKeyFromAnArray(int[] arr, int key) {
       assert arr != null;
       int pointer1=0;
        for (int pointer2 = 0; pointer2 < arr.length; pointer2++) {
            if(arr[pointer2] != key) {
               arr[pointer1] = arr[pointer2];
               pointer1++;
            }
        }
        return pointer1;
    }

//    public static void main(String[] args) {
//        int[] arr = new int[] { 3, 2, 3, 6, 3, 10, 9, 3 };
//        System.out.println(removeAllInstancesOfKeyFromAnArray(arr, 3));
//
//        arr = new int[] { 2, 11, 2, 2, 1 };
//        System.out.println(removeAllInstancesOfKeyFromAnArray(arr, 2));
//    }
   /*Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.*/
    public static int[] squaresOfAllElementsOftheArrayInSortedOrder(int[] arr) {
        assert arr != null;
        int leftPointer =0, rightPointer =arr.length-1;
        int[] squareArray = new int[arr.length];
        int highestIndexOfSquareArray = squareArray.length -1;

        while(leftPointer <= rightPointer) {
              int leftElementSquare = arr[leftPointer] * arr[leftPointer];
              int rightElementSquare = arr[rightPointer] * arr[rightPointer];
              if(leftElementSquare > rightElementSquare) {
                 squareArray[highestIndexOfSquareArray] = leftElementSquare;
                 leftPointer++;
              } else {
                  squareArray[highestIndexOfSquareArray] = rightElementSquare;
                  rightPointer--;
              }
            highestIndexOfSquareArray--;
        }
        return squareArray;
    }

    public static void main(String[] args) {
        int[] result = squaresOfAllElementsOftheArrayInSortedOrder(new int[] { -2, -1, 0, 2, 3 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = squaresOfAllElementsOftheArrayInSortedOrder(new int[] { -3, -1, 0, 1, 2 });
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }

}
