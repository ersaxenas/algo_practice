package com.lrn.educative.crust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ArrWorkUtils {
    /*Given a sorted array of integers, return the index of the given key. Return -1 if the key is not found.*/
    static class BSearchArr {
        public Integer findIterative(int[] arr, int key) {
            int start = 0, end = arr.length - 1, mid = -1;
            int result = -1;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                if (arr[mid] == key) {
                    result = mid;
                    break;
                }
                if (key < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return result;
        }

        public Integer findRecursive(int[] arr, int key, int start, int end) {
            if (start > end) {
                return -1;
            }
            int mid = start + ((end - start) / 2);
            if (arr[mid] == key) {
                return mid;
            }
            if (key < arr[mid]) {
                return findRecursive(arr, key, start, mid - 1);
            } else {
                return findRecursive(arr, key, mid + 1, end);
            }
        }

        public static void main(String[] args) {
            BSearchArr bSearchArr = new BSearchArr();
            int[] arr = {1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162, 176, 188, 199, 200, 210, 222};
            int[] inputs = {10, 49, 99, 110, 176};

            for (int i = 0; i < inputs.length; i++) {
                System.out.println("Iterative : binSearch(arr, " + inputs[i] + ") = " + bSearchArr.findIterative(arr, inputs[i]));
                System.out.println("Recursive : binSearch(arr, " + inputs[i] + ") = " + bSearchArr.findRecursive(arr, inputs[i], 0, arr.length - 1));
            }
        }
    }
    /*Given a large array of integers and a window of size ww, find the current maximum value in the window as the window slides through the entire array.*/

    /**
     * Ex. [1,4,3,6,2] win = 3 expected result = 4, 6, 6
     * Assumptions: array len < Integer.max, array elements != null, best time solutions, input can be loaded in memory
     */
    static class MaximumInSlidingWindow {
        public List<Integer> findMax(int[] arr, int win) {
            if (arr == null || arr.length <= 0) {
                return null;
            }
            List<Integer> maxList = new ArrayList<>();
            int winStart = 0;
            Queue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
            for (int winEnd = 0; winEnd < arr.length; winEnd++) {
                maxHeap.add(arr[winEnd]);
                if (maxHeap.size() == win) {
                    maxList.add(maxHeap.peek());
                    maxHeap.remove(arr[winStart++]);
                }
            }
            return maxList;
        }

        public static void main(String[] args) {
            MaximumInSlidingWindow maximumInSlidingWindow = new MaximumInSlidingWindow();
            int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            System.out.println("Array = " + Arrays.toString(array));
            System.out.println("Max = " + maximumInSlidingWindow.findMax(array, 3));

            int[] array2 = {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67};
            System.out.println("Array = " + Arrays.toString(array2));
            System.out.println("Max = " + maximumInSlidingWindow.findMax(array2, 3));

            int[] array3 = {1, 4, 3, 6, 2};
            System.out.println("Array = " + Arrays.toString(array3));
            System.out.println("Max = " + maximumInSlidingWindow.findMax(array3, 3));
        }
    }

    /*Search for a given number in a sorted array, with unique elements, that has been rotated by some arbitrary number. Return -1 if the number does not exist.*/
    /*
     * [7,8,9,10,1,2,3,4,5,6] ex: find 7 return 7 , 0 return -1
     * assumptions: array contains only +ve elements, arrays can be loaded in memory, array length is less then Integer.Max, array elements are < Integer>max, best time solution
     * */
    static class RotatedArraySearch {

        public Integer findElem(int[] arr, int elem) {
            int start = 0, end = arr.length - 1, mid = 0;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                if (elem == arr[mid]) {
                    return mid;
                }
                if (arr[start] < arr[mid]) { // left array is sorted
                    if ((elem >= arr[start] && elem < arr[mid])) {
                        return binarySearch(arr, start, mid, elem);
                    }
                    start = mid + 1;
                } else if (arr[mid] < arr[end]) { // right arrays is sorted
                    if ((elem > arr[mid] && elem <= arr[end])) {
                        return binarySearch(arr, mid, end, elem);
                    }
                    end = mid - 1;
                }
            }
            return -1;
        }

        public Integer binarySearch(int[] arr, int start, int end, int elem) {
            while (start <= end) {
                int mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                if (elem < midElem) {
                    end = mid - 1;
                } else if (elem > midElem) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            RotatedArraySearch rotatedArraySearch = new RotatedArraySearch();
            int[] v1 = {6, 7, 1, 2, 3, 4, 5};
            System.out.println("Key(3) found at: " + rotatedArraySearch.findElem(v1, 3));
            System.out.println("Key(6) found at: " + rotatedArraySearch.findElem(v1, 6));
            int[] v2 = {4, 5, 6, 1, 2, 3};
            System.out.println("Key(3) found at: " + rotatedArraySearch.findElem(v2, 3));
            System.out.println("Key(6) found at: " + rotatedArraySearch.findElem(v2, 6));
            int[] v3 = {7, 8, 9, 10, 1, 2, 3, 4, 5, 6};
            System.out.println("Key(7) found at: " + rotatedArraySearch.findElem(v3, 7));
            System.out.println("Key(0) found at: " + rotatedArraySearch.findElem(v3, 0));
        }
    }

    /*given three positive integer arrays which are sorted in ascending order.
     You have to find the smallest number that is common in all three arrays. Return -1 if the smallest common number is not found.*/
    static class SmallestCommonNum {
        public Integer findCommon1(int[] arr1, int[] arr2, int[] arr3) {
            int idx1 = 0, idx2 = 0, idx3 = 0;
            while (idx1 < arr1.length && idx2 < arr2.length && idx3 < arr3.length) {
                if (arr1[idx1] == arr2[idx2] && arr2[idx2] == arr3[idx3]) {
                    return arr1[idx1];
                }
                int max = Math.max(arr1[idx1], Math.max(arr2[idx2], arr3[idx3]));
                while (idx1 < arr1.length && arr1[idx1] < max) {
                    idx1++;
                }
                while (idx2 < arr2.length && arr2[idx2] < max) {
                    idx2++;
                }
                while (idx3 < arr3.length && arr3[idx3] < max) {
                    idx3++;
                }
            }
            return -1;
        }

        public Integer findCommon2(int[] arr1, int[] arr2, int[] arr3) {
            int idx1 = 0, idx2 = 0, idx3 = 0;
            while (idx1 < arr1.length && idx2 < arr2.length && idx3 < arr3.length) {
                if (arr1[idx1] == arr2[idx2] && arr2[idx2] == arr3[idx3]) {
                    return arr1[idx1];
                }
                if (arr1[idx1] <= arr2[idx2] && arr1[idx1] <= arr3[idx3]) {
                    idx1++;
                } else if (arr2[idx2] <= arr1[idx1] && arr2[idx2] <= arr3[idx3]) {
                    idx2++;
                } else if (arr3[idx3] <= arr2[idx2] && arr3[idx3] <= arr1[idx1]) {
                    idx3++;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] v1 = new int[]{6, 7, 10, 25, 30, 63, 64};
            int[] v2 = new int[]{1, 4, 5, 6, 7, 8, 50};
            int[] v3 = new int[]{1, 6, 10, 14};
            SmallestCommonNum smallestCommonNum = new SmallestCommonNum();
            System.out.println("Least Common Number v1: " + smallestCommonNum.findCommon1(v1, v2, v3));
            System.out.println("Least Common Number v1: " + smallestCommonNum.findCommon1(v1, v2, v3));

            v2 = new int[]{1, 4, 5, 7, 8, 50};
            System.out.println("Least Common Number v1: " + smallestCommonNum.findCommon1(v1, v2, v3));
            System.out.println("Least Common Number v2: " + smallestCommonNum.findCommon2(v1, v2, v3));

            v3 = new int[]{1, 6, 7, 10, 14};
            System.out.println("Least Common Number v1: " + smallestCommonNum.findCommon1(v1, v2, v3));
            System.out.println("Least Common Number v2: " + smallestCommonNum.findCommon2(v1, v2, v3));
        }
    }

    /*Rotate an Array by N Elements*/
    static class ArrayRotator {
        public void swap(Integer[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public void reverse(Integer[] arr, int start, int end) {
            while (start <= end) {
                swap(arr, start++, end--);
            }
        }

        public void rotate(Integer[] arr, int n) {
            if (n == 0) {
                return;
            }
            if (arr == null || arr.length <= 1) {
                return;
            }
            /*steps
             * normalize number of rotations
             * convert -ve rotation to +ve rotations
             * reverse entire array
             * reverse 0 to rotation -1
             * reverse rotation to array length -1
             * */
            int rotations = n % arr.length;
            if (rotations < 0) {
                rotations = rotations + arr.length;
            }
            reverse(arr, 0, arr.length - 1);
            reverse(arr, 0, rotations - 1);
            reverse(arr, rotations, arr.length - 1);
        }

        public static void main(String[] args) {
            ArrayRotator arrayRotator = new ArrayRotator();
            Integer[] arr = {1, 10, 20, 0, 59, 86, 32, 11, 9, 40};
            System.out.println("Array Before Rotation\n" + Arrays.asList(arr));
            arrayRotator.rotate(arr, 2);
            System.out.println("Array After Rotation\n" + Arrays.asList(arr));
        }
    }

    /*Given a sorted array of integers, return the low and high index of the given key. You must return -1 if the indexes are not found.*/
    static class HighLowKey {
        public Integer findLowIndex(Integer[] arr, int key) {
            int start = 0, end = arr.length - 1, mid = 0;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                if (midElem < key) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            if (start < arr.length && arr[start] == key) {
                return start;
            }
            return -1;
        }

        public Integer findHighIndex(Integer[] arr, int key) {
            int start = 0, end = arr.length - 1, mid = 0;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                if (midElem <= key) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            if (end == -1) {
                return end;
            }
            if (end < arr.length && arr[end] == key) {
                return end;
            }
            return -1;
        }

        public static void main(String[] args) {
            HighLowKey highLowKey = new HighLowKey();
            Integer[] array = {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6};
            int key = 5;
            int low = highLowKey.findLowIndex(array, key);
            int high = highLowKey.findHighIndex(array, key);
            System.out.println("Low Index of " + key + ": " + low);
            System.out.println("High Index of " + key + ": " + high);

            key = -2;
            low = highLowKey.findLowIndex(array, key);
            high = highLowKey.findHighIndex(array, key);
            System.out.println("Low Index of " + key + ": " + low);
            System.out.println("High Index of " + key + ": " + high);
        }
    }

    /*Given an integer array, move all elements that are 0 to the left while maintaining the order of other elements in the array. The array has to be modified in-place.*/
    static class MoveZerosToBeginning {
        public void moveZero(int[] arr) {
            int pRead = arr.length - 1, pWrite = arr.length - 1;
            while (pRead >= 0) {
                if (arr[pRead] != 0) {
                    arr[pWrite] = arr[pRead];
                    pWrite--;
                }
                pRead--;
            }
            while (pWrite >= 0) {
                arr[pWrite--] = 0;
            }
        }

        public static void main(String[] args) {
            MoveZerosToBeginning moveZerosToBeginning = new MoveZerosToBeginning();
            int[] v = new int[]{1, 10, 20, 0, 59, 63, 0, 88, 0};
            System.out.println("Original Array: " + Arrays.toString(v));
            moveZerosToBeginning.moveZero(v);
            System.out.println("After Moving Zeroes to Left: " + Arrays.toString(v));
        }
    }

    /*Given a list of daily stock prices (integers for simplicity), return the buy and sell prices for making the maximum profit.
      We need to maximize the single buy/sell profit. If we can’t make any profit, we’ll try to minimize the loss.
      For the below examples, buy and sell prices for making a maximum profit are highlighted.*/
    static class StockBuySell {
        static class ATuple<X, Y> {
            public X x;
            public Y y;

            public ATuple(X x, Y y) {
                this.x = x;
                this.y = y;
            }
        }

        public ATuple<Integer, Integer> getMaxProfit(Integer[] arr) {
            int gBuy = arr[0];
            int gSell = arr[1];
            int gProfit = gSell - gBuy;

            int currBuy = arr[0];
            for (int idx = 1; idx < arr.length; idx++) {

                int currentProfit = arr[idx] - currBuy;
                if (currentProfit > gProfit) {
                    gBuy = currBuy;
                    gSell = arr[idx];
                    gProfit = currentProfit;
                }

                if (currBuy > arr[idx]) {
                    currBuy = arr[idx];
                }
            }
            return new ATuple<>(gBuy, gSell);
        }

        public static void main(String[] args) {
            StockBuySell stockBuySell = new StockBuySell();
            Integer[] array = {1, 2, 3, 4, 3, 2, 1, 2, 5};
            ATuple result = null;
            result = stockBuySell.getMaxProfit(array);
            System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.x, result.y));

            Integer[] array2 = {8, 6, 5, 4, 3, 2, 1};
            result = stockBuySell.getMaxProfit(array2);
            System.out.println(String.format("Buy Price: %d, Sell Price: %d", result.x, result.y));
        }
    }

    /*You are given an array (list) of interval pairs as input where each interval has a start and end timestamp. The input array is sorted by starting timestamps. You are required to merge overlapping intervals and return a new output array.
Consider the input array below. Intervals (1, 5), (3, 7), (4, 6), (6, 8) are overlapping so they should be merged to one big interval (1, 8). Similarly, intervals (10, 12) and (12, 15) are also overlapping and should be merged to (10, 15).*/
    static class OverlappingInterval {
        static class TPair {
            public int first;
            public int second;

            public TPair(int x, int y) {
                this.first = x;
                this.second = y;
            }
        }

        public List<TPair> merge(List<TPair> intervals) {
            if (intervals == null || intervals.isEmpty()) {
                return new ArrayList<>();
            }
            ArrayList<TPair> resultList = new ArrayList<TPair>();
            TPair inter = intervals.get(0);
            resultList.add(inter);
            for (int idx = 1; idx < intervals.size(); idx++) {
                TPair newInter = intervals.get(idx);
                if (inter.second > newInter.first) { // overlapping
                    inter.second = Math.max(inter.second, newInter.second);
                } else { // not overlapping
                    resultList.add(newInter);
                    inter = newInter;
                }
            }
            return resultList;
        }

        public static void main(String[] args) {
            OverlappingInterval overlappingInterval = new OverlappingInterval();
            ArrayList<TPair> intervals = new ArrayList<>();
            intervals.add(new TPair(1, 5));
            intervals.add(new TPair(3, 7));
            intervals.add(new TPair(4, 6));
            intervals.add(new TPair(6, 8));
            intervals.add(new TPair(10, 12));
            intervals.add(new TPair(11, 15));

            List<TPair> result = overlappingInterval.merge(intervals);

            for (int i = 0; i < result.size(); i++) {
                System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
            }
        }
    }

    /*Given an array of integers and a value, determine if there are any two integers in the array whose sum is equal to the given value. Return true if the sum exists and return false if it does not.*/
    static class PairSumFinder {
        public boolean findPairForSum(Integer[] arr, int sum) {
            if (arr == null || arr.length < 1) {
                return false;
            }
            Set<Integer> elemSet = new HashSet<>();
            for (Integer elem : arr) {
                int nElem = sum - elem;
                if (elemSet.contains(nElem)) {
                    return true;
                } else {
                    elemSet.add(elem);
                }
            }
            return false;
        }

        public static void main(String[] args) {
            Integer[] v = {5, 7, 1, 2, 8, 4, 3};
            Integer[] test = {3, 20, 1, 2, 7};
            PairSumFinder pairSumFinder = new PairSumFinder();
            for (int i = 0; i < test.length; i++) {
                boolean output = pairSumFinder.findPairForSum(v, test[i]);
                System.out.println("findSumOfTwo(v, " + test[i] + ") = " + (output ? "true" : "false"));
            }
        }
    }

    /*Sort an Array Using Quicksort Algorithm*/
    static class QuickSortArray {

        public void sort(Integer[] arr) {
            sort(arr,0, arr.length-1);
        }

        public void sort(Integer[] arr, int lo, int hi) {
            if(lo >= hi) {return;}
           int partitionIdx = partition(arr,lo, hi);
           sort(arr, lo , partitionIdx-1);
           sort(arr, partitionIdx+1 , hi);
        }

        public Integer partition(Integer[] arr, int lo, int hi) {
            int pElem = arr[lo];
            int lPnt = lo, rPnt = hi;
            while (lPnt < rPnt) {
                while (arr[lPnt] <= pElem && lPnt <= hi) {
                    lPnt++;
                }
                while (arr[rPnt] > pElem && rPnt >= lo) {
                    rPnt--;
                }
                if(lPnt > rPnt) {
                    break;
                }
                swap(arr, lPnt, rPnt);
            }
            swap(arr, lo, rPnt);
           return rPnt;
        }
        public void swap(Integer[] arr, int a, int b) {
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        public static void main(String[] args) {
            QuickSortArray quickSortArray = new QuickSortArray();
            Integer[] a = new Integer[] {55, 23, 26, 2, 18, 78, 23, 8, 2, 3};

            System.out.print("Before Sorting\n");
            System.out.print(Arrays.toString(a) + "\n");

            quickSortArray.sort(a);

            System.out.print("After Sorting\n");
            System.out.print(Arrays.toString(a));
        }

    }

}



