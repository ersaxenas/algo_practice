package com.lrn.educative.gci.twopointers;

import java.util.*;

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
        int pFwd = 0, pBwd = arr.length - 1;
        while (pFwd < pBwd) {
            int sum = arr[pFwd] + arr[pBwd];
            if (sum < targetSum) {
                pFwd++;
            } else if (sum > targetSum) {
                pBwd--;
            } else {
                System.out.println(String.format("E1 = %d (%d), E2 = %d (%d), Sum = %d", pFwd, arr[pFwd], pBwd, arr[pBwd], sum));
                result = new int[]{pFwd, pBwd};
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

    public static int[] findSumInSortedArrayUsingHashTable(int[] arr, int targetSum) {
        assert arr != null;
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
        for (int pointer2 = 1; pointer2 < arr.length; pointer2++) { /*both pointer start from same position. pointer2 keeps incrementing.*/
            if (arr[pointer1 - 1] != arr[pointer2]) { /*compare previous element with current one*/
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
        int pointer1 = 0;
        for (int pointer2 = 0; pointer2 < arr.length; pointer2++) {
            if (arr[pointer2] != key) {
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
        int leftPointer = 0, rightPointer = arr.length - 1;
        int[] squareArray = new int[arr.length];
        int highestIndexOfSquareArray = squareArray.length - 1;

        while (leftPointer <= rightPointer) {
            int leftElementSquare = arr[leftPointer] * arr[leftPointer];
            int rightElementSquare = arr[rightPointer] * arr[rightPointer];
            if (leftElementSquare > rightElementSquare) {
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

//    public static void main(String[] args) {
//        int[] result = squaresOfAllElementsOftheArrayInSortedOrder(new int[] { -2, -1, 0, 2, 3 });
//        for (int num : result)
//            System.out.print(num + " ");
//        System.out.println();
//
//        result = squaresOfAllElementsOftheArrayInSortedOrder(new int[] { -3, -1, 0, 1, 2 });
//        for (int num : result)
//            System.out.print(num + " ");
//        System.out.println();
//    }

    /*Given an array of unsorted numbers, find all unique triplets in it that add up to zero.*/
    public static List<List<Integer>> findUniqueTripletsWithSumToZero(int[] arr) {
        assert arr != null;
        List<List<Integer>> tripletsList = new ArrayList<>();
        Arrays.sort(arr);
        /*in sorted array duplicates will be next to each other so skip duplicates*/
        for (int index = 0; index < arr.length - 2; index++) {
            if (index > 0 && (arr[index] == arr[index - 1])) {
                continue;// skip duplicates
            }
            /*now start two pointers*/
            int rightPointer = arr.length - 1, leftPointer = index + 1, targetSum = -1 * (arr[index]);
            while (leftPointer < rightPointer) {
                int targetDiff = targetSum - arr[leftPointer];
                if (targetDiff == arr[rightPointer]) { // found the elements
                    tripletsList.add(Arrays.asList(arr[index], arr[leftPointer], arr[rightPointer]));
                    leftPointer++;
                    rightPointer--;
                    /*now once again duplicate check*/
                    while (leftPointer < rightPointer && arr[leftPointer] == arr[leftPointer - 1]) { /*left pointer will compare with previous element in forward direction */
                        leftPointer++;
                    }
                    while (leftPointer < rightPointer && arr[rightPointer] == arr[rightPointer + 1]) { /*right pointer will compare with previous element in backward direction*/
                        rightPointer--;
                    }

                } else {
                    if (targetDiff > arr[rightPointer]) {
                        leftPointer++;
                    } else {
                        rightPointer--;
                    }

                }
            }
        }
        return tripletsList;
    }

//    public static void main(String[] args) {
//        System.out.println(findUniqueTripletsWithSumToZero(new int[]{-3, 0, 1, 2, -1, 1, -2}));
//        System.out.println(findUniqueTripletsWithSumToZero(new int[]{-5, 2, -1, -2, 3}));
//    }

    /*Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet. If there are more than one such triplet, return the sum of the triplet with the smallest sum.*/
    public static int findTripletWithSumCloseToTarget(int[] arr, int targetSum) {
        assert arr != null;
        Arrays.sort(arr);
        int smallestDiff = Integer.MAX_VALUE;
        for (int index = 0; index < arr.length - 2; index++) {
            /*two pointers algo*/
            int leftpointer = index + 1, rightpointer = arr.length - 1;
            while (leftpointer < rightpointer) {
                int targetDiff = targetSum - (arr[index] + arr[leftpointer] + arr[rightpointer]);
                if (targetDiff == 0) {
                    System.out.println("[" + arr[index] + "," + arr[leftpointer] + "," + arr[rightpointer] + "]");
                    /*now move pointers*/
                    return targetSum;
                } else if (Math.abs(targetDiff) < Math.abs(smallestDiff)) {
                    smallestDiff = targetDiff;
                }

                if (targetDiff > 0) {
                    leftpointer++;
                } else { // targetDiff < arr[rightpointer]
                    rightpointer--;
                }
            }
        }
        return targetSum - smallestDiff;
    }

//    public static void main(String[] args) {
//        System.out.println(findTripletWithSumCloseToTarget(new int[] { -2, 0, 1, 2 }, 2));
//        System.out.println(findTripletWithSumCloseToTarget(new int[] { -3, -1, 1, 2 }, 1));
//        System.out.println(findTripletWithSumCloseToTarget(new int[] { 1, 0, 1, 1 }, 100));
//    }

    /*Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. Write a function to return the count of such triplets*/
    public static int findTripletSumLessThenTarget(int[] arr, int target) {
        assert arr != null;
        Arrays.sort(arr);
        int count = 0;
        for (int index = 0; index < arr.length - 2; index++) {
            /*now two pointers algo*/
            int leftpointer = index + 1, rightpointer = arr.length - 1, targetSum = target - arr[index];
            while (leftpointer < rightpointer) {
                /*to avoid over flow*/
                if (target > (arr[index] + arr[leftpointer] + arr[rightpointer])) {
                    System.out.println(arr[index] + "," + arr[leftpointer] + "," + arr[rightpointer]);
                    count++;
                    rightpointer--;
                } else {
                    leftpointer++;
                }
            }
        }
        System.out.println("count = " + count);
        return count;
    }

    public static int findTripletSumLessThenTarget2(int[] arr, int target) {
        assert arr != null;
        Arrays.sort(arr);
        int count = 0;
        for (int index = 0; index < arr.length - 2; index++) {
            /*now two pointers algo*/
            int leftpointer = index + 1, rightpointer = arr.length - 1, targetSum = target - arr[index];
            while (leftpointer < rightpointer) {
                /*to avoid over flow*/
                if (target > (arr[index] + arr[leftpointer] + arr[rightpointer])) {
                    /*since arrays is sorted all the elements between leftpointer to rightpointer will satisfy condition target > target > (arr[index]+arr[leftpointer]+arr[rightpointer])*/
                    for (int idx = leftpointer + 1; idx <= rightpointer; idx++) {
                        System.out.println(arr[index] + "," + arr[leftpointer] + "," + arr[idx]);
                        count++;
                    }
                    leftpointer++;
                } else {
                    rightpointer--;
                }
            }
        }
        System.out.println("count = " + count);
        return count;
    }

//    public static void main(String[] args) {
//        System.out.println(findTripletSumLessThenTarget2(new int[] { -1, 0, 2, 3 }, 3));
//        System.out.println(findTripletSumLessThenTarget2(new int[] { -1, 4, 2, 1, 3 }, 5));
//    }

    /*Given an array with positive numbers and a target number, find all of its contiguous subarrays whose product is less than the target number.*/
    public static List<List<Integer>> findAllContiguousSubarrysWithProductLessThenTarget(int[] arr, int targetProduct) {
        assert arr != null;
        List<List<Integer>> listOfArrayElements = new ArrayList<>();
        int rightPointer;
        for (int leftPointer = 0; leftPointer < arr.length; leftPointer++) {
            if (arr[leftPointer] <= targetProduct) {
                rightPointer = leftPointer;
                int currentProduct = 1;
                while ((rightPointer < arr.length) && (targetProduct > (currentProduct * arr[rightPointer]))) {
                    currentProduct = currentProduct * arr[rightPointer];
                    List<Integer> elementList = new ArrayList<>();
                    for (int index = leftPointer; index <= rightPointer; index++) {
                        elementList.add(arr[index]);
                    }
                    listOfArrayElements.add(elementList);
                    rightPointer++;
                }
            }
        }
        return listOfArrayElements;
    }

    public static List<List<Integer>> findAllContiguousSubarrysWithProductLessThenTarget2(int[] arr, int target) {
        assert arr != null;
        List<List<Integer>> result = new ArrayList<>();
        int currentProduct = 1, left = 0;
        for (int right = 0; right < arr.length; right++) {
            currentProduct = currentProduct * arr[right];

            /*shrink window if product is greater then target*/
            while ((currentProduct >= target) && (left <= right)) {
                currentProduct = currentProduct / arr[left];
                left++;
            }

            List<Integer> lst = new LinkedList<>();
            for (int index = right; index >= left; index--) {
                lst.add(0, arr[index]);
                result.add(new ArrayList<>(lst));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(findAllContiguousSubarrysWithProductLessThenTarget2(new int[]{2, 5, 3, 10}, 30));
        System.out.println(findAllContiguousSubarrysWithProductLessThenTarget2(new int[]{8, 2, 6, 5}, 50));
    }

    /*Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists of three different numbers that is why it is called Dutch National Flag problem.*/
    public static void sortArrayOfThreeDifferentTypeOfElementOnly(int[] arr) {
        assert arr != null;
        /*three element are 0,1,2.
         * All elements less then 1 go before low and all element greater then 1 go after high.*/
        int lowPointer = 0, highPointer = arr.length - 1, tmp = 0;
        while (lowPointer <= highPointer) {
            if (arr[lowPointer] > 1) {
                tmp = arr[lowPointer];
                arr[lowPointer] = arr[highPointer];
                arr[highPointer] = tmp;
                highPointer--;
            }
            if (arr[highPointer] < 1) {
                tmp = arr[lowPointer];
                arr[lowPointer] = arr[highPointer];
                arr[highPointer] = tmp;
                lowPointer++;
            } else {
                lowPointer++;
            }
            while (arr[highPointer] > 1 && (lowPointer < highPointer)) {
                highPointer--;
            }
            while (arr[lowPointer] < 1 && (lowPointer < highPointer)) {
                lowPointer++;
            }
        }
    }

    public static int[] sortArrayOfThreeDifferentTypeOfElementOnly2(int[] arr) {
        assert arr != null;
        int low = 0, high = arr.length - 1, index = 0;
        while (index <= high) {
            int currentElement = arr[index];
            if (currentElement == 0) {
                /*swap with low*/
                arr[index] = arr[low];
                arr[low] = currentElement;
                low++;
                index++;/*since have seen the element*/
            } else if (currentElement == 2) {
                arr[index] = arr[high];
                arr[high] = currentElement;
                high--; /*not incrementing index since element at high has not been seen*/
            } else {
                index++;
            }
        }
        return arr;
    }

//    public static void main(String[] args) {
//
//        System.out.println(Arrays.stream(sortArrayOfThreeDifferentTypeOfElementOnly2(new int[]{1, 0, 2, 1, 0})).boxed().collect(Collectors.toList()));
//        System.out.println(Arrays.stream(sortArrayOfThreeDifferentTypeOfElementOnly2(new int[]{2, 2, 0, 1, 2, 0})).boxed().collect(Collectors.toList()));
//    }

    /*Given an array of unsorted numbers and a target number, find all unique quadruplets in it, whose sum is equal to the target number.*/
    public static List<List<Integer>> findQuadrupletsInArraySumEqualToTarget(int[] arr, int target) {
        assert arr != null;
        Arrays.sort(arr);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int first = 0; first < arr.length - 3; first++) {
            /*to avoid duplicates*/
            if (first > 0 && (arr[first] == arr[first - 1])) {
                continue;
            }
            for (int second = first + 1; second < arr.length - 2; second++) {
                if (second > (first + 1) && (arr[second] == arr[second - 1])) {
                    continue;
                }
                findPairsWithTargetSum(arr, first, second, target, resultList);
            }
        }
        return resultList;
    }

    public static void findPairsWithTargetSum(int[] arr, int first, int second, int targetSum, List<List<Integer>> quadruplets) {
        int forwardPointer = second + 1, backwardPointer = arr.length - 1;
        while (forwardPointer < backwardPointer) {
            int sum = arr[first] + arr[second] + arr[forwardPointer] + arr[backwardPointer];
            if (sum == targetSum) {
                List<Integer> lst = Arrays.asList(arr[first], arr[second], arr[forwardPointer], arr[backwardPointer]);
                quadruplets.add(lst);
                forwardPointer++;
                backwardPointer--;
                /*check for duplicates*/
                while ((forwardPointer < backwardPointer) && (arr[forwardPointer] == arr[forwardPointer - 1])) {
                    forwardPointer++;
                }
                while ((forwardPointer < backwardPointer) && (arr[backwardPointer] == arr[backwardPointer + 1])) {
                    backwardPointer--;
                }
            } else if (sum < targetSum) {
                forwardPointer++;
            } else {
                // sum > targetSum
                backwardPointer--;
            }
        }
    }

//    public static void main(String[] args) {
//        System.out.println(findQuadrupletsInArraySumEqualToTarget(new int[]{4, 1, 2, -1, 1, -3}, 1));
//        System.out.println(findQuadrupletsInArraySumEqualToTarget(new int[]{2, 0, -1, 1, -2, 2}, 2));
//    }

    /*Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal*/
    public static int getIndexEscapeHashChar(char[] chrs, int index) {
        assert chrs != null && chrs.length > index && index >= 0;
        int backspaceCount = 0;
        while (index >= 0) {
            if (chrs[index] == '#') {
                backspaceCount++;
            } else if (backspaceCount > 0) {
                backspaceCount--;
            } else {
                break;
            }
            index--;
        }
        return index;
    }

    public static boolean checkIfStringsAreEqualEsacpingHashChars(String str1, String str2) {
        assert str1 != null && str2 != null;
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int right1 = charArray1.length - 1, right2 = charArray2.length - 1;
        while (right1 >= 0 && right2 >= 0) {
            right1 = getIndexEscapeHashChar(charArray1, right1);
            right2 = getIndexEscapeHashChar(charArray2, right2);
            if (charArray1[right1] != charArray2[right2]) {
                return false;
            }
            right1--;
            right2--;
        }
        return true;
    }

//    public static void main(String[] args) {
//        System.out.println(checkIfStringsAreEqualEsacpingHashChars("xy#z", "xzz#"));
//        System.out.println(checkIfStringsAreEqualEsacpingHashChars("xy#z", "xyz#"));
//        System.out.println(checkIfStringsAreEqualEsacpingHashChars("xp#", "xyz##"));
//        System.out.println(checkIfStringsAreEqualEsacpingHashChars("xywrrmp", "xywrrmu#p"));
//    }

    /*Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.*/
    public static void findSmallestSubArrayToMakeWholeArraySorted(int[] arr) {
        assert arr != null;
        int left = 0, right = arr.length - 1;
        /*start from index 0 stop when element at index is < element at index + 1*/
        while ((left < arr.length - 1) && (arr[left] <= arr[left + 1])) {
            left++;
        }
        if (left == arr.length-1) {
            System.out.println("Arrays is already sorted. So size is 0");
            return; // arrays is already sorted
        }
        /*start from last index of arrays and stop when element at index is < element at index - 1*/
        while ((right > 0) && (arr[right] >= arr[right - 1])) {
            right--;
        }
        //System.out.println("left: "+left+", right: "+ right);
        /*now find maximum and minimum in the sub array [left to right]*/
        int subArrayMin = Integer.MAX_VALUE, subArrayMax = Integer.MIN_VALUE;
        for (int index = left; index <= right; index++) {
            subArrayMax = Math.max(subArrayMax, arr[index]);
            subArrayMin = Math.min(subArrayMin, arr[index]);
        }
        //System.out.println("subArrayMin: "+subArrayMin+", subArrayMax: "+ subArrayMax);
        /*expand left to include any number bigger then minimum element in the subarray*/
        while((left>0) && (arr[left-1] > subArrayMin)) {
            left--;
        }
        /*expand right to include any number smaller then maximum element in the subarray*/
        while((right<arr.length-1) && (arr[right+1] < subArrayMax)) {
            right++;
        }
        //System.out.println("left: "+left+", right: "+ right);
        System.out.println("smallest sub array size: " + (right - left + 1));
    }

//   public static void main(String[] args) {
//        findSmallestSubArrayToMakeWholeArraySorted(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 });
//        findSmallestSubArrayToMakeWholeArraySorted(new int[] { 1, 3, 2, 0, -1, 7, 10 });
//        findSmallestSubArrayToMakeWholeArraySorted(new int[] { 1, 2, 3 });
//        findSmallestSubArrayToMakeWholeArraySorted(new int[] { 3, 2, 1 });
//    }

    /*Suppose you are given an array containing non-negative numbers representing heights of a set of buildings.
    Now, because of differences in heights of buildings water can be trapped between them.
    Find the two buildings that will trap the most amount of water.
    Write a function that will return the maximum volume of water that will be trapped between these two buildings.*/
    public static int findBuildingWithMaxAreaInBetween(int[] arr) {
        assert arr != null;
        int leftPointer=0, rightPointer=arr.length-1,maxArea=0;
        while(leftPointer < rightPointer) {
            int leftHeight = arr[leftPointer];
            int rightHeight = arr[rightPointer];
            if(leftHeight < rightHeight) {
                int area = (rightPointer-leftPointer) * leftHeight;
                maxArea = Math.max(maxArea, area);
                leftPointer++;
            } else  {
                int area = (rightPointer-leftPointer) * rightHeight;
                maxArea = Math.max(maxArea, area);
                rightPointer--;
            }
        }
        System.out.println("max area: " + maxArea);
        return maxArea;
    }

//    public static void main(String[] args) {
//        findBuildingWithMaxAreaInBetween(new int[] { 1, 3, 5, 4, 1 });
//        findBuildingWithMaxAreaInBetween(new int[] { 3, 2, 5, 4, 2 });
//        findBuildingWithMaxAreaInBetween(new int[] { 1, 4, 3, 2, 5, 8, 4 });
//    }

}
