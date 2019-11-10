package com.lrn.educative.gci.cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkCsUtils {
    /*Problem: We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence. This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.

Write a function to sort the objects in-place on their creation sequence number in O(n)O(n) and without any extra space. For simplicity, let’s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.*/
    /*Assumption: Input array length  <= Int.max, only +ve numbers, data can be loaded in the memory, best time solution, no extra space, no duplicates
     * Approach: Since array contains unique number in sequence we can sort array by placing elements at its correct location.
     * */
    static class SequenceSorter {

        public void sort(int[] arr) {
            if (arr == null || arr.length <= 0) {
                return;
            }
            int index = 0;
            while (index < arr.length) {
                int index2 = arr[index] - 1;
//                if (index == index2) {
//                    index++;
//                } else {
//                    swap(arr, index, index2);
//                }
                if (arr[index] != arr[index2]) {
                    swap(arr, index, index2);
                } else {
                    index++;
                }

            }
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public static void main(String[] args) {
            SequenceSorter sequenceSorter = new SequenceSorter();
            int[] arr = new int[]{3, 1, 5, 4, 2};
            sequenceSorter.sort(arr);
            for (int num : arr)
                System.out.print(num + " ");
            System.out.println();

            arr = new int[]{2, 6, 4, 3, 1, 5};
            sequenceSorter.sort(arr);
            for (int num : arr)
                System.out.print(num + " ");
            System.out.println();

            arr = new int[]{1, 5, 6, 4, 3, 2};
            sequenceSorter.sort(arr);
            for (int num : arr)
                System.out.print(num + " ");
        }

        /*We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.*/
        /*Assumptions: number are from 0 to n, n < int.max, data can be loaded in memory, best time solution, no duplicates elements*/
        static class NumberFinder {

            public int findMissingNumber(int[] arr) {
                int missingNumber = -1;
                if (arr == null || arr.length == 0) {
                    return missingNumber;
                }
                /*sort array*/
                int index = 0;
                while (index < arr.length) {
                    if (arr[index] < arr.length && (arr[index] != arr[arr[index]])) {
                        swap(arr, index, arr[index]);
                    } else {
                        index++;
                    }
                }
                /*find missing number*/
                for (int i = 0; i < arr.length; i++) {
                    if (i != arr[i]) {
                        missingNumber = i;
                    }
                }
                return missingNumber;
            }

            public void swap(int[] arr, int index1, int index2) {
                int tmp = arr[index1];
                arr[index1] = arr[index2];
                arr[index2] = tmp;
            }

            public static void main(String[] args) {
                NumberFinder numberFinder = new NumberFinder();
                System.out.println(numberFinder.findMissingNumber(new int[]{4, 0, 3, 1}));
                System.out.println(numberFinder.findMissingNumber(new int[]{8, 3, 5, 2, 4, 6, 0, 1}));
            }
        }
    }

    /*We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.*/
    /*Assumptions: arrays length < int.max, elements < int.max, there can be duplicate elements, best time solution, data can be loaded in memory
     * Approach : user cyclic sort and then iterate over array to find missing numbers*/
    static class AllMissingNumberFinder {

        public List<Integer> findMissingNumbers(int[] arr) {
            List<Integer> missingNum = new ArrayList<>();
            if (arr == null || arr.length == 0) {
                return missingNum;
            }
            int idx1 = 0;
            while (idx1 < arr.length) {
                if (arr[idx1] <= arr.length && arr[idx1] != arr[arr[idx1] - 1]) { /*here arr[idx1] -1 since range starts from 1. So element 1 belongs to 0 position */
                    swap(arr, idx1, arr[idx1] - 1);
                } else {
                    idx1++;
                }
            }

            for (int idx2 = 0; idx2 < arr.length; idx2++) {
                if (idx2 + 1 != arr[idx2]) { /*here idx + 1 since range starts from 1. So element 1 belongs to index 0*/
                    missingNum.add(idx2 + 1);
                }
            }
            return missingNum;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
    }

    public static void main(String[] args) {
        AllMissingNumberFinder allMissingNumberFinder = new AllMissingNumberFinder();
        List<Integer> missing = allMissingNumberFinder.findMissingNumbers(new int[]{2, 3, 1, 8, 2, 3, 5, 1});
        System.out.println("Missing numbers: " + missing);

        missing = allMissingNumberFinder.findMissingNumbers(new int[]{2, 4, 1, 2});
        System.out.println("Missing numbers: " + missing);

        missing = allMissingNumberFinder.findMissingNumbers(new int[]{2, 3, 2, 1});
        System.out.println("Missing numbers: " + missing);
    }

    /*We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. The array has only one duplicate but it can be repeated multiple times. Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.*/
    /*
    Assumptions: array length is less then int.max, data can be loaded in memory, best time algorithm
    Approach:
             use cyclic sort,
             since elements are from 1 to n then an element belongs to element -1 position in the array.
             if an element is not at its place index != arr[index] then it is eligible for swapping.
             if arr[index] == arr[arr[index] -1] then we have duplicate
             if arr[index] != arr[arr[index] -1] then we have swap
    * */
    static class DuplicateFinder {
        public int findDuplicate(int[] arr) {
            int duplicateNum = -1;
            if (arr == null || arr.length == 0) {
                return duplicateNum;
            }
            int index=0; // element n will belong to index n-1
            while(index < arr.length) {
                if(index == (arr[index] -1)) { // element is at correct index
                  index++;
                } else { // element is NOT at correct index. So now element at index belongs to arr[index] -1; Ex. index = 2 and arr[2] = 4 so element 4 belongs to 4 - 1 = arr[2] - 1 = arr[index] -1
                    int element = arr[index];
                    if(element == arr[element -1]) { // element is already present at element-1 index so arr[index] is duplicate
                        duplicateNum = element;
                        break;
                    } else { //swap
                        swap(arr, index, element -1);
                    }
                }
            }
            return duplicateNum;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public static void main(String[] args) {
            DuplicateFinder duplicateFinder = new DuplicateFinder();
            System.out.println(duplicateFinder.findDuplicate(new int[] { 1, 4, 4, 3, 2 }));
            System.out.println(duplicateFinder.findDuplicate(new int[] { 2, 1, 3, 3, 5, 4 }));
            System.out.println(duplicateFinder.findDuplicate(new int[] { 2, 4, 1, 4, 4 }));
        }
    }

    static class AllDuplicateFinder {
        public List<Integer> findAllDuplicates(int[] arr) {
            List<Integer> duplicates = new ArrayList<>();
            if(arr == null || arr.length ==0) {
                return duplicates;
            }
            int index = 0;
            while(index < arr.length) {
               if(index == (arr[index] -1)) { // element is at right place
                   index++;
               } else {
                   int element = arr[index];
                   if(element == arr[element -1]) { // duplicate
                       duplicates.add(element);
                       index++;
                   } else { // swap
                       swap(arr, index, element -1);
                   }
               }
            }
            return duplicates;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public static void main(String[] args) {
            AllDuplicateFinder allDuplicateFinder = new AllDuplicateFinder();
            List<Integer> duplicates = allDuplicateFinder.findAllDuplicates(new int[] { 3, 4, 4, 5, 5 });
            System.out.println("Duplicates are: " + duplicates);

            duplicates = allDuplicateFinder.findAllDuplicates(new int[] { 5, 4, 7, 2, 3, 5, 3 });
            System.out.println("Duplicates are: " + duplicates);
        }
    }

    /*We are given an unsorted array containing ‘n’ numbers taken from the range 1 to ‘n’. The array originally contained all the numbers from 1 to ‘n’, but due to a data error, one of the numbers got duplicated which also resulted in one number going missing. Find both these numbers.*/
    /*Assumptions: array length <= int.max, data can be loaded in memory, best time solution
    * Approach: use cyclic sort and after sort it arrays will have a missing number and duplicate will be at missing index position
    * Ex: [2,3,1,4,4] so here 4 is duplicate and 5 is the missing number
    * case 1: array is null return empty list
    * case2: [2,3,1,4,4] return [4,5]
    * */

    static class DuplicateAndMissingNumberFinder {
        public List<Integer> findDupAndMissingNum(int[] arr) {
            List<Integer> missingNumWithDups = new ArrayList<>();
            if(arr==null || arr.length ==0) {
                return missingNumWithDups;
            }
            int index =0;
            while(index < arr.length) {
                if(index == (arr[index]-1)) { // element is at correct index
                    index++;
                } else {
                    int element = arr[index];
                    if(element == arr[element -1]) { // duplicate
                        index++;
                    } else {
                        swap(arr, index, arr[index]-1);
                    }
                }
            }
            for(int idx=0; idx<arr.length; idx++) {
                // 1 to n
                if(idx+1 != arr[idx]) {
                    missingNumWithDups.add(arr[idx]);//dup
                    missingNumWithDups.add(idx+1);// missing num
                }
            }
            return missingNumWithDups;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }

        public static void main(String[] args) {
            DuplicateAndMissingNumberFinder duplicateAndMissingNumberFinder = new DuplicateAndMissingNumberFinder();
            List<Integer> lst = duplicateAndMissingNumberFinder.findDupAndMissingNum(new int[] { 3, 1, 2, 5, 2 });
            System.out.println(lst);
            lst = duplicateAndMissingNumberFinder.findDupAndMissingNum(new int[] { 3, 1, 2, 3, 6, 4 });
            System.out.println(lst);
        }
    }

    /*Given an unsorted array containing numbers, find the smallest missing positive number in it.*/
    /*Assumptions: array length < int.max, element can be -ve and +ve, best time solution,
    * Approach: user cyclic sort and ignore -ve numbers then iterate over array and first index missing the num is the smallest positive number
    * */
    static class SmallestMissingPositive {
        public int findSmallestMissingPositiveNum(int[] arr) {
            int result = -1;
            if(arr==null || arr.length <0) {
                return result;
            }
            int index = 0;
            while(index<arr.length) {
                if(arr[index] <=0 || arr[index] > arr.length) { // element is out of range
                    index++;
                } else {
                    int element = arr[index];
                    if(element == arr[element-1]) {
                        index++;
                    } else {
                        swap(arr, index, element-1);
                    }
                }
            }
            for (int idx = 0; idx < arr.length; idx++) {
                if(idx+1 != arr[idx]) {
                    result = idx+1;
                    break;
                }
            }
            return result;
        }
        public int findSmallestMissingPositiveNum2(int[] arr) {
            int result = -1;
            if(arr==null || arr.length ==0) {
                return result;
            }
            int index = 0;
            while(index<arr.length) {
                  int elem = arr[index];
                  if(elem >0 && elem <=arr.length && elem != arr[elem-1]) {
                      swap(arr, index, elem-1);
                  } else {
                      index++;
                  }
            }
            for (int idx = 0; idx < arr.length; idx++) {
                if(idx+1 != arr[idx]) {
                    result = idx+1;
                    break;
                }
            }
            return result;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
        public static void main(String[] args) {
            SmallestMissingPositive smallestMissingPositive = new SmallestMissingPositive();
            System.out.println(smallestMissingPositive.findSmallestMissingPositiveNum2(new int[] { -3, 1, 5, 4, 2 }));
            System.out.println(smallestMissingPositive.findSmallestMissingPositiveNum2(new int[] { 3, -2, 0, 1, 2 }));
            System.out.println(smallestMissingPositive.findSmallestMissingPositiveNum2(new int[] { 3, 2, 5, 1 }));
        }
    }

    /*Given an unsorted array containing numbers and a number ‘k’, find the first ‘k’ missing positive numbers in the array.*/
    /*Assumptions: arrays length < int.max, arrays elements : -n to n,  1 < k < array.length, whole dataset can be loaded in the memory, best time solution
    * Approach: cyclic sort: sort array, then iterate over array ignoring -ve number and first k will be smallest +ve numbers.
    * */
    static class FirstKMissingPositiveNumbers {

        public List<Integer> find(int[] arr, int k) {
            List<Integer> missingNumbers = new ArrayList<>();
            if (arr == null || arr.length == 0) {
                return missingNumbers;
            }
            int index=0;
            while(index<arr.length) {
              int element = arr[index];
              if(element > 0 && element <= arr.length && element != arr[element-1]) {
                  swap(arr, index, element-1);
              } else {
                  index++;
              }
            }
            Set<Integer> extraNumbers = new HashSet<>();
            for (int idx = 0; idx < arr.length && missingNumbers.size() < k; idx++) {
                if(idx+1 != arr[idx]) {
                    missingNumbers.add(idx+1);
                    extraNumbers.add(arr[idx]);
                }
            }
            // till this point we have evaluated numbers missing from array which are from 1 to array length. Now for numbers greater then arrays length
            for (int idx = 1; idx < Integer.MAX_VALUE && missingNumbers.size() < k; idx++) {
                int numGreaterThenArrayLen = arr.length + idx;
                if(!extraNumbers.contains(numGreaterThenArrayLen)){ // if num is missing from array.
                    missingNumbers.add(numGreaterThenArrayLen);
                }
            }
            return missingNumbers;
        }

        public void swap(int[] arr, int idx1, int idx2) {
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
        public static void main(String[] args) {
            FirstKMissingPositiveNumbers firstKMissingPositiveNumbers = new FirstKMissingPositiveNumbers();
            List<Integer> missingNumbers = firstKMissingPositiveNumbers.find(new int[] { 3, -1, 4, 5, 5 }, 3);
            System.out.println("Missing numbers: " + missingNumbers);

            missingNumbers = firstKMissingPositiveNumbers.find(new int[] { 2, 3, 4 }, 3);
            System.out.println("Missing numbers: " + missingNumbers);

            missingNumbers = firstKMissingPositiveNumbers.find(new int[] { -2, -3, 4 }, 2);
            System.out.println("Missing numbers: " + missingNumbers);
        }
    }



}
