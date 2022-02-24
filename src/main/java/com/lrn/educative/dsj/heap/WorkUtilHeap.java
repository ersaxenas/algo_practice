package com.lrn.educative.dsj.heap;

import java.util.Arrays;

public class WorkUtilHeap {
    static class HeapBuilder  {
        public static void main(String[] args) {
            Heap heap = new Heap(false,12, false);
            int[] arr = { 1, 4, 7, 12, 15, 14, 9, 2, 3, 16 };
            System.out.println("Before heapify: "+ Arrays.toString(arr));
            heap.buildMaxHeap(arr, arr.length);
            System.out.println("After Max heapify: "+Arrays.toString(arr));
            heap.buildMinHeap(arr, arr.length);
            System.out.println("After Min heapify: "+Arrays.toString(arr));
        }
    }
}
