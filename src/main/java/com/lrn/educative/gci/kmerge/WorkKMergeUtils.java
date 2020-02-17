package com.lrn.educative.gci.kmerge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class WorkKMergeUtils {
    /*Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.*/
    static class KListMerge {
        class Element {
            List<Integer> elemList;
            Integer num;
            Integer elementIndexInList;

            public Element(List<Integer> elemList, Integer num, Integer elementIndexInList) {
                this.elemList = elemList;
                this.num = num;
                this.elementIndexInList = elementIndexInList;
            }

            public Element getNextElement() {
                if (elementIndexInList + 1 < elemList.size()) {
                    return new Element(elemList, elemList.get(elementIndexInList + 1), elementIndexInList + 1);
                } else {
                    return null;
                }
            }
        }

        public List<Integer> merge(List<List<Integer>> lists) {
            assert lists != null && lists.size() > 0;
            List<Integer> mergedList = new ArrayList<>();
            if (lists.size() == 1) {
                return lists.get(0);
            }
            PriorityQueue<Element> minHeap = new PriorityQueue<>((e1, e2) -> e1.num - e2.num);
            for (int idx = 0; idx < lists.size(); idx++) {
                List<Integer> lst = lists.get(idx);
                if (lst != null && lst.size() > 0) {
                    minHeap.add(new Element(lst, lst.get(0), 0));
                }
            }
            while (!minHeap.isEmpty()) {
                Element element = minHeap.poll();
                mergedList.add(element.num);
                Element nextElement = element.getNextElement();
                if (nextElement != null) {
                    minHeap.add(nextElement);
                }
            }
            return mergedList;
        }

        public static void main(String[] args) {
            List<Integer> list1 = Arrays.asList(2, 6, 8);
            List<Integer> list2 = Arrays.asList(3, 6, 7);
            List<Integer> list3 = Arrays.asList(1, 3, 4);
            KListMerge kListMerge = new KListMerge();
            List<Integer> mergedList = kListMerge.merge(Arrays.asList(list1, list2, list3));
            String res = mergedList.stream().map(String::valueOf).collect(Collectors.joining(" -> "));
            System.out.println("res = " + res);
            list1 = Arrays.asList(5, 8, 9);
            list2 = Arrays.asList(1, 7);
            mergedList = kListMerge.merge(Arrays.asList(list1, list2));
            res = mergedList.stream().map(String::valueOf).collect(Collectors.joining(" -> "));
            System.out.println("res = " + res);
        }
    }
    /*Given ‘M’ sorted arrays, find the K’th smallest number among all the arrays.*/
    static class KthSmallestNumberInSortedLists {
        class Element {
            Integer num;
            Integer numIndexInArray;
            Integer[] arr;

            public Element(Integer num, Integer numIndexInArray, Integer[] arr) {
                this.num = num;
                this.numIndexInArray = numIndexInArray;
                this.arr = arr;
            }
            public boolean hasNextElement() {
                return numIndexInArray + 1 < arr.length;
            }

            public Element getNextElem() {
                Element elem = null;
                if(hasNextElement()) {
                    elem = new Element(arr[numIndexInArray+1], numIndexInArray+1, arr);
                }
                return elem;
            }
        }
        public Integer find(List<Integer[]> lists, int K) {
            assert lists != null && lists.size() > 0;
            PriorityQueue<Element> minHeap = new PriorityQueue<>((e1,e2) -> e1.num - e2.num);
            Integer result = null;
            for(Integer[] arr: lists) {
                if(arr != null && arr.length > 0 && arr[0] !=  null) {
                    minHeap.add(new Element(arr[0],0, arr));
                }
            }
            Integer elemCounter = 0;
            while(!minHeap.isEmpty()) {
                Element element = minHeap.poll();
                elemCounter++;
                if(elemCounter == K) {
                    result = element.num;
                    break;
                }
                if(element.hasNextElement()) {
                    minHeap.add(element.getNextElem());
                }
            }
            return result;
        }
        public static void main(String[] args) {
            KthSmallestNumberInSortedLists kthSmallestNumberInSortedLists = new KthSmallestNumberInSortedLists();
            Integer[] l1 = new Integer[] { 2, 6, 8 };
            Integer[] l2 = new Integer[] { 3, 6, 7 };
            Integer[] l3 = new Integer[] { 1, 3, 4 };
            List<Integer[]> lists = new ArrayList<>();
            lists.add(l1);
            lists.add(l2);
            lists.add(l3);
            int result = kthSmallestNumberInSortedLists.find(lists, 5);
            System.out.print("Kth smallest number is: " + result);
        }
    }
   /*Given an N * NN∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.*/
    static class KthSmallestElementInMatrix {
        class Element {
            int row =0;
            int col = 0;
            int N =0;

            public Element(int row, int col, int N) {
                this.row = row;
                this.col = col;
                this.N = N;
            }
            public Element getNextElem() {
                if(col + 1 > N) {
                    return null;
                }
                return new Element(row, col+1,N);
            }
        }
        public Integer find(int[][] matrix, int K) {
           int N = matrix[0].length-1;
           PriorityQueue<Element> minHeap = new PriorityQueue<>((e1,e2) -> matrix[e1.row][e1.col] - matrix[e2.row][e2.col]);
           int row =0;
           while(row < N) {
               minHeap.add(new Element(row, 0, N));
               row++;
           }
           int cnt=0;
           while(!minHeap.isEmpty()) {
               Element elem = minHeap.poll();
               cnt++;
               if(cnt == K-1) {
                   return matrix[elem.row][elem.col];
               }
               Element nextElem = elem.getNextElem();
               if(nextElem != null) {
                   minHeap.add(nextElem);
               }
           }
           return null;
        }
   }
   public static void main(String[] args) {
        KthSmallestElementInMatrix kthSmallestElementInMatrix = new KthSmallestElementInMatrix();
       int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
       int result = kthSmallestElementInMatrix.find(matrix, 5);
       System.out.print("Kth smallest number is: " + result);
   }
   /*Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.*/
    static class SmallestRangeFinder {
        class Element {
            Integer[] arr;
            int index;

            public Element(Integer[] arr, int index) {
                this.arr = arr;
                this.index = index;
            }
            public Integer getValue() { return arr[index];}
            public Element getNextElement() {
                if(index+1 >= arr.length) {
                    return null;
                }
                return new Element(arr, index+1);
            }
        }
        public Integer[] findRange(List<Integer[]> lists) {
            assert lists != null && !lists.isEmpty();
            Integer[] result = new Integer[2];
            int max = Integer.MIN_VALUE, rangeMin=0, rangeMax=0, range=Integer.MAX_VALUE;
            PriorityQueue<Element> minHeap = new PriorityQueue<>((e1,e2) -> e1.getValue() - e2.getValue());
            for(Integer[] arr: lists) {
                minHeap.add(new Element(arr, 0));
                max = Math.max(max,arr[0]);
            }
            while(!minHeap.isEmpty() && minHeap.size() == lists.size()) {
                Element elem = minHeap.poll();
                if((max - elem.getValue()) < range) {
                     rangeMin = elem.getValue();
                     rangeMax = max;
                     range = max -rangeMin;
                }
                Element nextElem = elem.getNextElement();
                if(nextElem != null) {
                    minHeap.add(nextElem);
                    max = Math.max(max,nextElem.getValue());
                }
            }
            result[0] = rangeMin;
            result[1] = rangeMax;
            return result;
        }
        public static void main(String[] args) {
            SmallestRangeFinder smallestRangeFinder = new SmallestRangeFinder();
            Integer[] l1 = new Integer[] { 1, 5, 8 };
            Integer[] l2 = new Integer[] { 4, 12 };
            Integer[] l3 = new Integer[] { 7, 8, 10 };
            List<Integer[]> lists = new ArrayList<Integer[]>();
            lists.add(l1);
            lists.add(l2);
            lists.add(l3);
            Integer[] result = smallestRangeFinder.findRange(lists);
            System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
        }
   }

   /*Given two sorted arrays in descending order, find ‘K’ pairs with the largest sum where each pair consists of numbers from both the arrays.*/
    static class KLargestPair {
        class PairSum {
            int a;
            int b;
            int sum;
            public PairSum(int a, int b) {
                this.a = a;
                this.b = b;
                sum = a + b;
            }
            public int[] toArray() {
                return new int[] {a,b};
            }
        }
        public List<int[]> findPairs(int[] arr1, int[] arr2, int K) {
           PriorityQueue<PairSum> minHeap = new PriorityQueue<>((p1,p2) -> p1.sum - p2.sum);
           for(int idx1=0; idx1 < arr1.length && idx1 < K; idx1++ ) {
               for(int idx2=0; idx2 < arr2.length && idx2 < K; idx2++) {
                   if(minHeap.size() < K) {
                       minHeap.add(new PairSum(arr1[idx1], arr2[idx2]));
                   } else {
                       PairSum minSum = minHeap.peek();
                       if((arr1[idx1]+arr2[idx2]) > minSum.sum) {
                           minHeap.poll();
                           minHeap.add(new PairSum(arr1[idx1], arr2[idx2]));
                       }
                   }
               }
           }
           List<int[]> result = minHeap.stream().map(pair -> pair.toArray()).collect(Collectors.toList());
           return result;
        }
        public static void main(String[] args) {
            KLargestPair kLargestPair = new KLargestPair();
            int[] l1 = new int[] { 9, 8, 2 };
            int[] l2 = new int[] { 6, 3, 1 };
            List<int[]> result = kLargestPair.findPairs(l1, l2, 3);
            System.out.print("Pairs with largest sum are: ");
            for (int[] pair : result)
                System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
        }
   }

}
