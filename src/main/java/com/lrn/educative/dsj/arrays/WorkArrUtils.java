package com.lrn.educative.dsj.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkArrUtils {
    public static String arrayToString(int arr[]) {
        if (arr.length > 0) {
            String result = "";
            for (int i = 0; i < arr.length; i++) {
                result += arr[i] + " ";
            }
            return result;
        } else {
            return "Empty Array!";
        }
    }

    /*Given two sorted arrays, merge them into one array which should also be sorted. Implement the solution in Java and see if your code runs successfully!*/
    /*Assumptions: arr1.length + arr2.length < int.max, there is sufficient memory so merged array can be loaded in to memory, best time solution*/
    /*approach: first create a new array of arr3.length = arr1.length + arr2.length
     *           take three index 1, 2, 3 for arr1, arr2, arr3
     *           loop while index1 < arr1.length && index2 < arr2.length
     *           now append the arr1/arr2 which has elements left.
     * */
    /*Testcase: if arr1 == null and arr1 == null then return null;
     *           if arr1 == null and arr2 != null then return arr2
     *           if arr1 != null and arr2 == null then return arr1
     *           if arr1!=null && arr2!=null return merged array
     *           [1,2,3,4] [5,6,8,9] return [1,2,3,4,5,6,8,9]
     *           [1,2,3,4] [1,2,3,4] return [1,1,2,2,3,3,4,4]
     * */
    static class SortedArrayMergeUtil {
        public int[] mergeArrays(int[] arr1, int[] arr2) {
            if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)) {
                return null;
            }
            if ((arr1 == null || arr1.length == 0)) {
                return arr1;
            }
            if ((arr2 == null || arr2.length == 0)) {
                return arr2;
            }
            int mergedArrayLength = arr1.length + arr2.length;
            int index1 = 0, index2 = 0, index3 = 0;
            int[] mergedArray = new int[mergedArrayLength];
            while (index1 < arr1.length && index2 < arr2.length) {
                if (arr1[index1] < arr2[index2]) {
                    mergedArray[index3++] = arr1[index1++];
                } else {
                    mergedArray[index3++] = arr2[index2++];
                }
            }
            while (index1 < arr1.length) {
                mergedArray[index3++] = arr1[index1++];
            }
            while (index2 < arr2.length) {
                mergedArray[index3++] = arr2[index2++];
            }
            return mergedArray;
        }

        public static void main(String[] args) {
            SortedArrayMergeUtil sortedArrayMergeUtil = new SortedArrayMergeUtil();
            int[] arr1 = {1, 12, 14, 17, 23}; // creating a sorted array called arr1
            int[] arr2 = {11, 19, 27};  // creating a sorted array called arr2

            int[] resultantArray = sortedArrayMergeUtil.mergeArrays(arr1, arr2); // calling mergeArrays

            System.out.print("Arrays after merging: ");
            for (int i = 0; i < arr1.length + arr2.length; i++) {
                System.out.print(resultantArray[i] + " ");
            }
        }
    }

    /*Given an array and a number "n", find two numbers from the array that sums up to "n". Implement your solution in Java and see if your output matches with the correct output.*/
    /*Approach1: first sort the array. So now array will be in [min .... max] form. Now take two pointers indexMin = 0 and indexMax = arr.len -1.
                 if min+max < sum then move by 1 indexMin++
                 if min+max > sum then move by 1 indexMax--
                 if min+max == sum we found the number.
     Approach2: Take a Set. Start iterating the array (index).
                if set contains (sum - index) then we found the answer. element at index = arr[index] and sum - arr[index]
    */
    static class SumOfTwoElementsIntheArray {
        public List<Integer> find(int[] arr, int sum) {
            List<Integer> resultList = new ArrayList<>();
            if (arr == null || arr.length == 0) {
                return resultList;
            }
            Set<Integer> elementSet = new HashSet<>();
            for (int index = 0; index < arr.length; index++) {
                final int elem = arr[index];
                if (elementSet.contains(sum - elem)) {
                    resultList.add(elem);
                    resultList.add(sum - elem);
                    break;
                }
                elementSet.add(elem);
            }
            return resultList;
        }

        public static void main(String[] args) {
            SumOfTwoElementsIntheArray sumOfTwoElementsIntheArray = new SumOfTwoElementsIntheArray();
            int n = 9;
            int[] arr1 = {2, 4, 5, 7, 8};
            if (arr1.length > 0) {
                List<Integer> lst = sumOfTwoElementsIntheArray.find(arr1, n);
                int num1 = lst.get(0);
                int num2 = lst.get(1);

                if ((num1 + num2) != n)
                    System.out.println("Not Found");
                else {
                    System.out.println("Number 1 = " + num1);
                    System.out.println("Number 2 = " + num2);
                    System.out.println("Sum = " + (n));

                }
            } else {
                System.out.println("Input Array is Empty!");
            }
        }
    }

    /*Given an array of size n, can you find the second maximum element in the array? Implement your solution in Java and see if your output matches the correct output!*/
    /*Assumptions: array size < int.max, data can be loaded in the memory, best time solution
     * Approach: Keep to variable first max and second max, iterate over array if an element is greater then first max then second max = first max and first max = new element.
     * */
    static class SecondMaxInArray {
        public Integer findSecondMax(int[] arr) {
            Integer firstMax = Integer.MIN_VALUE, secondMax = null;
            if (arr == null || arr.length == 0) {
                return secondMax;
            }
            for (int element : arr) {
                if (element > firstMax) {
                    secondMax = firstMax;
                    firstMax = element;
                } else if (element > secondMax && element != firstMax) {
                    secondMax = element;
                }
            }
            return secondMax;
        }

        public static void main(String[] args) {
            SecondMaxInArray secondMaxInArray = new SecondMaxInArray();
            int[] arr = {-2, -33, -10, -456};
            System.out.println("Array: " + arrayToString(arr));
            int secMax = secondMaxInArray.findSecondMax(arr);
            System.out.println("Second maximum: " + secMax);
        }
    }

    /*PD: Right Rotate the Array by One Index
     * Assumptions: array length < int.max, data can be loaded in memory, best time solution
     * Approach: save last element of the array,
     *           start from second last element and copy elements to next index
     *           replace first element with the last element.
     * Test cases: if array null return array
     *             if array empty return array
     *             [1,2,3,4,5] -> [5,1,2,3,4]
     * */
    static class RotateArrayByOneIndex {
        public int[] rotate(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }
            int lastElement = arr[arr.length - 1];
            for (int index = arr.length - 2; index >= 0; index--) {
                arr[index + 1] = arr[index];
            }
            arr[0] = lastElement;
            return arr;
        }

        public static void main(String[] args) {
            RotateArrayByOneIndex rotateArrayByOneIndex = new RotateArrayByOneIndex();
            int[] arr = {3, 6, 1, 8, 4, 2};
            System.out.print("Array before rotation: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();

            rotateArrayByOneIndex.rotate(arr);

            System.out.print("Array after rotation: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    /*Problem: Re-arrange Positive & Negative Values
     * Assumptions: arrays length < int.max, best time solution, data can be processed in memory
     * Approach: Take two indexes idx1, idx2 pointing to 0
     *           idx1 where -ve number will go so each time a -ve number is encountered idx1 and idx2 will be swapped adn idx1 will increment by 1
     *           idx2 will start iterating over array elements and swap element with idx1 when encounters a -ve number.
     * Test case 1. [1,2,-4,-5] -> [-4,-5,1,2]
     *           2. [-1,-2,-3, 2,4,5,-6] - > [-1,-2,-3,-6,2,4,5]
     * */
    static class RearrangePositiveAndNegatives {
        public int[] rearrange(int[] arr) {
            if (arr == null || arr.length == 0) {
                return arr;
            }
            int idx1 = 0, idx2 = 0;
            for (; idx2 < arr.length; idx2++) {
                if (idx1 != idx2 && arr[idx2] < 0) {
                    swap(arr, idx1, idx2);
                    idx1++;
                }
            }
            return arr;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public static void main(String[] args) {
            RearrangePositiveAndNegatives rearrangePositiveAndNegatives = new RearrangePositiveAndNegatives();
            int[] arr = {2, 4, -6, 8, -5, -10};

            System.out.print("Array before re-arranging: ");
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");
            System.out.println();

            rearrangePositiveAndNegatives.rearrange(arr);

            System.out.print("Array after rearranging: ");
            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");
        }
    }

    /*Problem: Rearrange Sorted Array in Max/Min Form
    * assumptions: array.length < int.max, array is sorted min ... max, best time solution,
    *Approach: since array is already sorted and reversing the array will produce the expected result.
    *          take two index variables one pointing at 0 and other pointing at last element.
    *          swap elements at two pointers and increment index1 and decrement index2 until they meet.
    *test case: [1,2,3,4,5] -> [5,1,4,2,3]
    *           [1,2,3,4] -> [4,1,3,2]
    * */
    static class RearrangeMaxMin {

        public void maxMin(int[] arr) {
            int maxIdx = arr.length - 1;
            int minIdx = 0;
            int maxElem = arr[maxIdx] + 1; // store maximum element of array
            for( int i = 0; i < arr.length; i++) {
                // at even indices we will store maximum elements
                if (i % 2 == 0){
                    final double i1 = arr[maxIdx] % maxElem;
                    System.out.println(String.format("arr[maxIdx](%d) modulo maxElem (%d) = %f" ,arr[maxIdx],maxElem,i1));
                    arr[i] += i1 * maxElem;
                    System.out.println("arr["+i+"] = "+arr[i]);
                    maxIdx -= 1;
                }
                else { // at odd indices we will store minimum elements
                    final double i1 = arr[minIdx] % maxElem;
                    System.out.println(String.format("arr[minIdx](%d) modulo maxElem (%d) = %f" ,arr[minIdx],maxElem,i1));
                    arr[i] += i1 * maxElem;
                    System.out.println("arr["+i+"] = "+arr[i]);
                    minIdx += 1;
                }
            }
            // dividing with maxElem to get original values.
            for( int i = 0; i < arr.length; i++) {
                arr[i] = arr[i] / maxElem;
            }
        }
        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
    }
    public static void main(String[] args) {
       RearrangeMaxMin rearrangeMaxMin = new RearrangeMaxMin();
        int[] arr = {1,2,3,4,20};
        rearrangeMaxMin.maxMin(arr);
        System.out.println(arrayToString(arr));
        arr = new int[] {-1,0,1,2};
        rearrangeMaxMin.maxMin(arr);
        System.out.println(arrayToString(arr));
    }

}
