package com.lrn.educative.gci.ksmallest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class WorkKsmlUtils {
    static class KSmallestBrf{
        public Integer findSmallest(int[] arr, int K) {
            if(arr == null || arr.length ==0 || arr.length < K) {
                return null;
            }
            int currentMin = Integer.MAX_VALUE;
            int currentMinIndex=0;
            int previousMin = Integer.MIN_VALUE;
            int previousMinIndex =0;

            for(int idx1=0; idx1<K; idx1++) {
                for(int idx2=0; idx2<arr.length; idx2++) {
                    int element = arr[idx2];
                    if(element > previousMin && element < currentMin) {
                        currentMin = element;
                        currentMinIndex = idx2;
                    } else if(element == previousMin && idx2 > previousMinIndex) {
                        currentMinIndex = idx2;
                        currentMin = previousMin;
                    }
                }
                previousMin = currentMin;
                previousMinIndex = currentMinIndex;
                currentMin = Integer.MAX_VALUE;
            }
            return previousMin;
        }

        public static void main(String[] args) {
            KSmallestBrf kSmallestBrf = new KSmallestBrf();
            int result = kSmallestBrf.findSmallest(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
            System.out.println("Kth smallest number is: " + result);

            // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
            result = kSmallestBrf.findSmallest(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
            System.out.println("Kth smallest number is: " + result);

            result = kSmallestBrf.findSmallest(new int[] { 5, 12, 11, -1, 12 }, 3);
            System.out.println("Kth smallest number is: " + result);
        }
    }

    static class KSmallestMaxHeap {
        public Integer findKSmallest(int[] arr, int K) {
            if(arr == null || arr.length ==0 || arr.length < K) {
                return null;
            }
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1,e2) -> e2 - e1);
            for(int idx=0; idx<arr.length; idx++) {
                int elem = arr[idx];
                if(maxHeap.size() < K) {
                    maxHeap.add(elem);
                } else if(maxHeap.peek() > elem) {
                    maxHeap.poll();
                    maxHeap.add(elem);
                }
            }
            return maxHeap.peek();
        }

        public static void main(String[] args) {
            KSmallestMaxHeap kSmallestMaxHeap = new KSmallestMaxHeap();
            int result = kSmallestMaxHeap.findKSmallest(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
            System.out.println("Kth smallest number is: " + result);

            // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
            result = kSmallestMaxHeap.findKSmallest(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
            System.out.println("Kth smallest number is: " + result);

            result = kSmallestMaxHeap.findKSmallest(new int[] { 5, 12, 11, -1, 12 }, 3);
            System.out.println("Kth smallest number is: " + result);
        }
    }

}
