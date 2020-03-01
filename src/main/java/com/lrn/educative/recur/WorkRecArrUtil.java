package com.lrn.educative.recur;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkRecArrUtil {
    /*Find the First Occurrence of a Number in an Array*/
    static class FirstOccurrence {
        public int find(int[] arr, int index, int num) {
            // base case
            if(arr.length == index) {
                return -1;
            }
            if(arr[index] == num) {
                return index;
            }
            // recursive
            return find(arr, index+1, num);
        }

        public static void main(String[] args) {
            int[] arr = {2,3,4,1,7,8,3};
            System.out.println(new FirstOccurrence().find(arr, 0,3));
        }
    }
    /*Find the total occurrences of a Number in an Array*/
    static class TotalOccurrences {
        public int find(int[] arr, int index, int num) {
            // base case
            if(arr.length == index) {
                return 0;
            }
            int count =0;
            if(arr[index] == num) {
                count =1;
            }
            // recursive
            return count + find(arr, index+1, num);
        }

        public static void main(String[] args) {
            int[] arr = {2,3,4,1,7,8,3};
            System.out.println(new TotalOccurrences().find(arr, 0,3));
        }
    }
    /*Replacing Negative Values with Zero*/
    public static class RemoveNegativeValues {
        public void remove(Integer[] arr, int index) {
            //base
            if(index == arr.length) {
                return;
            }
            if(arr[index] < 0) {
                arr[index] = 0;
            }
            remove(arr, index+1);
        }

        public static void main(String[] args) {
            RemoveNegativeValues removeNegativeValues = new RemoveNegativeValues();
            Integer[] array = {2,-3,4,-1,-7,8,3};
            System.out.println(Arrays.asList(array));
            removeNegativeValues.remove(array,0);
            System.out.println(Arrays.asList(array));
        }
    }
    /*Invert the Position of Elements in an Array*/
    static class InverseArray {
        public void inverse(Integer[] arr, int index) {
            if(index < arr.length/2) {
                swap(arr, index, (arr.length -1) - index);
                inverse(arr, index+1);
            }
        }
        public void swap(Integer[] arr, int a, int b) {
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        public static void main(String[] args) {
            InverseArray inverseArray = new InverseArray();
            Integer[] arr = {1,2,3,4,5,6,7};
            System.out.println(Arrays.asList(arr));
            inverseArray.inverse(arr,0);
            System.out.println(Arrays.asList(arr));
        }
    }


}
