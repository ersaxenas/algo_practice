package com.lrn.educative.gci.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPointerTech {
    /*
    *Given that the input array is sorted, an efficient way would be to start with one pointer in the beginning and another pointer at the end.
    * At every step, we will see if the numbers pointed by the two pointers add up to the target sum. If they do not, we will do one of two things:
    * - If the sum of the two numbers pointed by the two pointers is greater than the target sum, this means that we need a pair with a smaller sum.
    * So, to try more pairs, we can decrement the end-pointer.
    * - If the sum of the two numbers pointed by the two pointers is smaller than the target sum, this means that we need a pair with a larger sum.
    * So, to try more pairs, we can increment the start-pointer.
    * */
    /*Assumption array is sorted*/
    public static int[] findSumInSortedArray(int[] arr, int targetSum) {
        int[] result = null;
        int pf = 0 /*forward pointer */, pb = arr.length - 1;/*backward pointer*/
        while (pf < pb) {
            int sum = arr[pf] + arr[pb];
            if (sum < targetSum) {
                pf++;
            } else if (sum > targetSum) {
                pb--;
            } else {
                System.out.println(String.format("E1 = %d (%d), E2 = %d (%d), Sum = %d", pf, arr[pf], pb, arr[pb], sum));
                result = new int[]{pf, pb};
                break;
            }
        }
        return (result == null) ? new int[]{-1, -1} : result;
    }

    /*
    An Alternate approach:
    Instead of using a two-pointer or a binary search approach, we can utilize a HashTable to search for the required pair. We can iterate through the array one number at a time.
    Let’s say during our iteration we are at number ‘X’, so we need to find ‘Y’ such that “X + Y == TargetX+Y==Target”. We will do two things here:

    1. Search for ‘Y’ (which is equivalent to “Target - XTarget−X”) in the HashTable. If it is there, we have found the required pair.
    2. Otherwise, insert “X” in the HashTable, so that we can search it for the later numbers.
    */
    /*Assumption array is not sorted or cannot be sorted.*/
    public static int[] findSumInSortedArrayUsingHashTable(int[] arr, int targetSum) {
        int[] result = null;
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int idx = 0; idx < arr.length; idx++) {
            int currElement = arr[idx];
            final int elementNeededTotargetSum = targetSum - currElement;
            if (valueIndexMap.containsKey(elementNeededTotargetSum)) {
                /*found elements*/
                System.out.println(String.format("E1 = %d (%d), E2 = %d (%d), Sum = %d", valueIndexMap.get(elementNeededTotargetSum), elementNeededTotargetSum, idx, currElement, targetSum));
                result = new int[]{valueIndexMap.get(elementNeededTotargetSum), idx};
                break;
            } else {
                valueIndexMap.put(currElement, idx);
            }
        }
        return (result == null) ? new int[]{-1, -1} : result;
    }
    /*Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after removing the duplicates in-place return the new length of the array.*/
    public static int removeDuplicateFromSortedArray(int[] arr) {
        assert arr != null && arr.length > 1;
        int pointer1 = 1; /*increment only for non duplicates so it points to last non duplicate element*/
        for (int pointer2 = 1; pointer2 < arr.length; pointer2++) { /*both pointer start from same position. pointer2 keeps incrementing.*/
            if (arr[pointer1 - 1] != arr[pointer2]) { /*compare previous element with current one*/
                /*if non duplicate swap*/
                arr[pointer1] = arr[pointer2];
                pointer1++;
            }
        }
        return pointer1;
    }

    /*Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.*/
    public static int[] squaresOfAllElementsOftheArrayInSortedOrder(int[] arr) {
        assert arr != null;
        int lp = 0, rp = arr.length - 1;
        int[] squareArray = new int[arr.length];
        int highestIndexOfSquareArray = squareArray.length - 1;

        while (lp <= rp) {
            int lsq = arr[lp] * arr[lp];
            int rsq = arr[rp] * arr[rp];
            if (lsq > rsq) {
                squareArray[highestIndexOfSquareArray] = lsq;
                lp++;
            } else {
                squareArray[highestIndexOfSquareArray] = rsq;
                rp--;
            }
            highestIndexOfSquareArray--;
        }
        return squareArray;
    }

    /*Given an array of unsorted numbers, find all unique triplets in it that add up to zero.*/
    public static List<List<Integer>> findUniqueTripletsWithSumToZero(int[] arr) {
        assert arr != null;
        List<List<Integer>> tripletsList = new ArrayList<>();
        Arrays.sort(arr); // sort first
        /*in sorted array duplicates will be next to each other so skip duplicates*/
        for (int index = 0; index < arr.length - 2; index++) { /* remember index < arr.length -2 */
            if (index > 0 && (arr[index] == arr[index - 1])) {
                continue;// skip duplicates
            }
            /*now start two pointers*/
            int rp = arr.length - 1, lp = index + 1, targetSum = -1 * (arr[index]);
            while (lp < rp) {
                int targetDiff = targetSum - arr[lp];
                if (targetDiff == arr[rp]) { // found the elements
                    tripletsList.add(Arrays.asList(arr[index], arr[lp], arr[rp]));
                    lp++;
                    rp--;
                    /*now once again duplicate check*/
                    while (lp < rp && arr[lp] == arr[lp - 1]) { /*left pointer will compare with previous element in forward direction */
                        lp++;
                    }
                    while (lp < rp && arr[rp] == arr[rp + 1]) { /*right pointer will compare with previous element in backward direction*/
                        rp--;
                    }

                } else {
                    if (targetDiff > arr[rp]) {
                        lp++;
                    } else {
                        rp--;
                    }

                }
            }
        }
        return tripletsList;
    }
}
