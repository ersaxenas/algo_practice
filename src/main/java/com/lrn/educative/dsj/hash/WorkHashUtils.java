package com.lrn.educative.dsj.hash;

import java.util.*;
import java.util.stream.Collectors;

public class WorkHashUtils {
    /*Find whether an array is a subset of another array*/
    static class ArraySubsetOfAnotherArray {
        public boolean check(int[] arr1, int[] arr2) {
            boolean result = true;
            int[] arr = (arr1.length < arr2.length) ? arr1 : arr2;
            Set<Integer> dataSet = new HashSet<>();
            if(arr.length == arr1.length) {
                for (int idx = 0; idx < arr2.length; idx++) {
                    dataSet.add(arr2[idx]);
                }
            } else {
                for (int idx = 0; idx < arr1.length; idx++) {
                    dataSet.add(arr1[idx]);
                }
            }
            for(int idx = 0; idx < arr.length; idx++) {
                if(!dataSet.contains(arr[idx])) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        public static void main(String[] args) {
            ArraySubsetOfAnotherArray arraySubsetOfAnotherArray = new ArraySubsetOfAnotherArray();
            int[] arr1 = {9, 4, 7, 1, -2, 6, 5};
            int[] arr2 = {7, 1, -2};
            int[] arr3 = {10, 12};

            System.out.println(arraySubsetOfAnotherArray.check(arr1, arr2));
            System.out.println(arraySubsetOfAnotherArray.check(arr1, arr3));
        }
    }

    static class DisjointedArrayCheck {
        public boolean check(int[] arr1, int[] arr2) {
            Set<Integer> dataSet = new HashSet<>();
            for(int elem: arr1) {
                dataSet.add(elem);
            }
            for(int elem: arr2) {
                if(dataSet.contains(elem)) {
                    return false;
                }
            }
            return true;
        }
        public static void main(String[] args) {
            DisjointedArrayCheck disjointedArrayCheck = new DisjointedArrayCheck();
            int[] arr1 = {9, 4, 3, 1, -2, 6, 5};
            int[] arr2 = {7, 10, 8};
            int[] arr3 = {1, 12};
            System.out.println(disjointedArrayCheck.check(arr1, arr2));
            System.out.println(disjointedArrayCheck.check(arr1, arr3));
        }
    }
    /*Find symmetric pairs in an Array*/

    static class SymmetricPairCheck {
        public String check(int[][] arr) {
            StringBuilder sbr = new StringBuilder();
            Map<Integer, Integer> dataMap = new HashMap<>();
            for (int row = 0; row < arr.length; row++) {
                 int left = arr[row][0];
                 int right = arr[row][1];
                final Integer val = dataMap.get(left);
                if(val!= null && val.equals(right)) {
                    sbr.append(String.format("{%d,%d}",right,left));
                } else {
                  dataMap.put(right,left);
                }
            }
            return sbr.toString();
        }

        public static void main(String[] args) {
            SymmetricPairCheck symmetricPairCheck = new SymmetricPairCheck();
            int[][] arr = {{1, 2}, {3, 4}, {5, 9}, {4, 3}, {9, 5}};
            String symmetric = symmetricPairCheck.check(arr);
            System.out.println(symmetric);
        }
    }
    /*Trace the complete path of a journey*/
    static class JourneyTracer {
        public String trace(Map<String,String> journeyMap) {
            assert journeyMap != null && !journeyMap.isEmpty();
            StringBuilder sbr = new StringBuilder();
            Set<String> valueSet = new HashSet<>(journeyMap.values());
            String start = null;
            for(String key: journeyMap.keySet()) {
                if(!valueSet.contains(key)) {
                    start = key;
                    break; // found starting point
                }
            }
            while(!journeyMap.isEmpty()) {
                final String end = journeyMap.get(start);
                sbr.append(start).append("->").append(end).append(", ");
                journeyMap.remove(start);
                start = end;
            }
            return sbr.toString();
        }
        public static void main(String[] args) {
            JourneyTracer journeyTracer = new JourneyTracer();
            HashMap<String,String> hMap = new HashMap<>();
            hMap.put("NewYork","Chicago");
            hMap.put("Boston","Texas");
            hMap.put("Missouri","NewYork");
            hMap.put("Texas","Missouri");

            String actual_output = journeyTracer.trace(hMap);
            System.out.println(actual_output);
        }
    }
    /*Find two pairs in an Array such that a+b = c+d*/
    static class TwoPairs {
        public String find(int[] arr) {
            assert arr!=null && arr.length >= 4;
            StringBuilder sbr = new StringBuilder();
            Map<Integer, String> sumMap = new HashMap<>();
            for(int idx1=0; idx1<arr.length; idx1++) {
                for(int idx2=idx1+1; idx2<arr.length; idx2++) {
                    int a = arr[idx1];
                    int b = arr[idx2];
                    if(sumMap.containsKey(a+b)) {
                        sbr.append(sumMap.get(a+b)).append(String.format(",{%d,%d}",a,b));
                        return sbr.toString();
                    }
                    sumMap.put(a+b,String.format("{%d,%d}",a,b));
                }
            }
            return null;
        }
        public static void main(String[] args) {
            int[] arr = {3,4,7,1,12,9};
            TwoPairs twoPairs = new TwoPairs();
            System.out.println(twoPairs.find(arr));
        }
    }
    /*Find if a subarray with a sum equal to 0 exists.*/
    static class SubArrayWithSumZero {
        public boolean find(int[] arr) {
            HashMap < Integer,Integer > hMap = new HashMap < >();
            int sum = 0;
            // Traverse through the given array
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                if (arr[i] == 0 || sum == 0 || hMap.get(sum) != null) return true;
                hMap.put(sum, i);
            }
            return false;
        }
        public static void main(String args[]) {
            SubArrayWithSumZero subArrayWithSumZero = new SubArrayWithSumZero();
            int[] arr = {6, 4, -7, 3, 12, 9};
            System.out.println(subArrayWithSumZero.find(arr));
        }
    }
    /* First Non-Repeating Integer in an Array*/
   static class NonReaptingIntegerInArray {
       public Integer find(int[] arr) {
           assert arr!=null && arr.length >=2;
           Map<Integer, Integer> frequencyMap = new HashMap<>();
           for (int idx = 0; idx < arr.length; idx++) {
               if(frequencyMap.containsKey(arr[idx])) {
                   frequencyMap.remove(arr[idx]);
               } else {
                   frequencyMap.put(arr[idx],1);
               }
           }
           if(frequencyMap.isEmpty()) {
               return null;
           }
           for(int idx=0; idx<arr.length;idx++) {
                    if(frequencyMap.containsKey(arr[idx])) {
                        return arr[idx];
                    }
           }
           return null;
       }
       public static void main(String[] args) {
           NonReaptingIntegerInArray nonReaptingIntegerInArray = new NonReaptingIntegerInArray();
           int[] arr = {2, 54, 7, 2, 6, 54};
           System.out.println("Array: " + Arrays.toString(arr));
           int unique = nonReaptingIntegerInArray.find(arr);
           System.out.print("First Unique in an Array: " + unique);
       }
    }
    /*Remove Duplicate from a Linked List using Hashing*/
    static class ListDupRemove {
        public void removeDuplicate(LinkedList<Integer> list) {
            assert list!=null && !list.isEmpty();
            HashSet<Integer> elements = new HashSet<>();
            final Iterator<Integer> iterator = list.iterator();
            while(iterator.hasNext()) {
                Integer elem = iterator.next();
                if(elements.contains(elem)) {
                  iterator.remove();
                } else {
                    elements.add(elem);
                }
            }

        }
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<>(); // created linked list

            for(int i = 1; i <= 8; i++) {
                list.add(i);      // inserting data in list
            }
            //inserting duplicates
            list.add(2);
            list.add(4);
            list.add(1);
            System.out.println("List before deleting duplicates from list :");
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
            new ListDupRemove().removeDuplicate(list); // calling removeDuplicatesWithHashing function
            System.out.println("List after deleting duplicates from list :");
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }
    /*Union and Intersection of Lists using Hashing*/
    static class ListsUnionAndIntersection {
        public List<Integer> getUnion(List<Integer> list1, List<Integer> list2) {
            HashSet<Integer> dataSet = new HashSet<>();
            List<Integer> result = new ArrayList<>();
            list1.forEach(elem -> {
                if(!dataSet.contains(elem)) {
                    dataSet.add(elem);
                    result.add(elem);
                }
            });
            list2.forEach(elem -> {
                if(!dataSet.contains(elem)) {
                    dataSet.add(elem);
                    result.add(elem);
                }
            });
            return result;
        }

        public List<Integer> getIntersection(List<Integer> list1, List<Integer> list2) {
            HashSet<Integer> dataSet = new HashSet<>();
            List<Integer> result = new ArrayList<>();
            list1.forEach(elem -> {
                if(!dataSet.contains(elem)) {
                    dataSet.add(elem);
                }
            });
            list2.forEach(elem -> {
                if(dataSet.contains(elem)) {
                    result.add(elem);
                }
            });
            return result;
        }
        public static void main(String[] args) {
            LinkedList<Integer> list1 = new LinkedList<>();
            for(int i=7; i>3; i--){
                list1.add(i);
            }
            System.out.print("1st ");
            System.out.println(list1.stream().map(String::valueOf).collect(Collectors.joining(",")));
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            for(int i=0; i<5; i++){
                list2.add(i);
            }
            System.out.print("2nd ");
            System.out.println(list2.stream().map(String::valueOf).collect(Collectors.joining(",")));
            ListsUnionAndIntersection listsUnionAndIntersection = new ListsUnionAndIntersection();
            System.out.print("After Intersection ");
            System.out.println(listsUnionAndIntersection.getIntersection(list1, list2).stream().map(String::valueOf).collect(Collectors.joining(",")));
            System.out.print("After Union ");
            System.out.println(listsUnionAndIntersection.getUnion(list1, list2).stream().map(String::valueOf).collect(Collectors.joining(",")));
        }

    }

}
