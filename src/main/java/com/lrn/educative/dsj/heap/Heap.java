package com.lrn.educative.dsj.heap;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

public class Heap {

    private boolean startsWithOne = true;
    private int startIndex = 1;
    private int size = 0;
    private int lastElementIndex = 0;
    private boolean minHeap = false;
    private Integer[] heapArray = null;
    private IntFunction<Integer> parentRootAtIndexOne = (int childIndex) -> {
     if(childIndex <=1) return 1;
     return childIndex/2;
    };
    private IntFunction<Integer> parentRootAtIndexZero = (int childIndex) -> {
     if(childIndex <=0) return 0;
     return (int)Math.floor((double)childIndex/2d);
    };
    private IntFunction<Integer> parentIndexFunc = parentRootAtIndexOne;
    private IntFunction<Integer> firstChildRootAtIndexOne = (int parentIndex) -> {
        return 2*parentIndex;
    };
    private IntFunction<Integer> firstChildRootAtIndexZero = (int parentIndex) -> {
        return 2*parentIndex + 1;
    };
    private IntFunction<Integer> firstChildIndexFunc = firstChildRootAtIndexOne;
    private IntFunction<Integer> secondChildRootAtIndexOne = (int parentIndex) -> {
        return 2*parentIndex +1;
    };
    private IntFunction<Integer> secondChildRootAtIndexZero = (int parentIndex) -> {
        return 2*parentIndex + 2;
    };
    private IntFunction<Integer> secondChildIndexFunc = secondChildRootAtIndexOne;

    private BiFunction<Integer, Integer, Boolean> less = (Integer a, Integer b) -> a < b; // maxHeap
    private BiFunction<Integer, Integer, Boolean> greater = (Integer a, Integer b) -> a > b; //minHeap
    private BiFunction<Integer, Integer, Boolean> elementCompareFunc = less;

    public Heap(boolean startsWithOne, int size, boolean minHeap) {
        this.startsWithOne = startsWithOne;
        if(!startsWithOne) {
            this.parentIndexFunc = parentRootAtIndexZero;
            this.firstChildIndexFunc = firstChildRootAtIndexZero;
            this.secondChildIndexFunc = secondChildRootAtIndexZero;
            this.startIndex = 0;
            lastElementIndex = -1;
        }
        if(minHeap) {
            this.elementCompareFunc = greater;
        }
        this.size = size;
        heapArray = new Integer[size];
        this.minHeap = minHeap;
    }

    public void swim(int elementIndex) { // bottom to up
        int index = elementIndex;
        int parentIndex = parentIndexFunc.apply(index);
        while(index > startIndex && elementCompareFunc.apply(heapArray[parentIndex], heapArray[index])) {
            swap(parentIndex,index);
            index = parentIndex;
            parentIndex = parentIndexFunc.apply(index);
        }
    }

    public void sink(int elementIndex) { // top to down
        int index = elementIndex; // parent index
        while(index <= lastElementIndex) {
            int firstChildIndex = firstChildIndexFunc.apply(index);
            int secondChildIndex = secondChildIndexFunc.apply(index);
            int selectedChild = firstChildIndex;
            if(firstChildIndex < lastElementIndex && elementCompareFunc.apply(heapArray[firstChildIndex], heapArray[secondChildIndex])) { // if first child is smaller then second child
                selectedChild = secondChildIndex; // selected second child
            }
            if(selectedChild > lastElementIndex || !elementCompareFunc.apply(heapArray[index],heapArray[selectedChild])) {
                break;
            }
            swap(index, selectedChild);
            index = selectedChild;
        }
    }

    public void swap(int index1, int index2) {
        int tmp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = tmp;
    }

    public Heap insert(int element) {
        heapArray[++lastElementIndex] = element;
        swim(lastElementIndex);
        return this;
    }

    public int delMax() {
        int max = heapArray[startIndex];// save element at root
        swap(startIndex, lastElementIndex); // swap last element with root
        heapArray[lastElementIndex] = null; // remove last element
        lastElementIndex--; // remove last element
        sink(startIndex); // restore the order
        return max;
    }

    public void printHeap() {
        System.out.println(Arrays.toString(heapArray));
    }

    public void heapify(int[] array, int elementIndex, int size, BiFunction<Integer, Integer, Boolean> comparisonFunc) {
        int parentIndex = elementIndex;
        int selectedChildIndex = elementIndex;
        while(selectedChildIndex < (size/2)) {
            int firstChildIndex = parentIndex * 2 + 1;
            int secondChildIndex = parentIndex * 2 + 2;
            if(firstChildIndex < size && comparisonFunc.apply(array[firstChildIndex],array[parentIndex])) { // compare fist child with parent
                selectedChildIndex = firstChildIndex;
            }
            if(secondChildIndex < size && comparisonFunc.apply(array[secondChildIndex], array[selectedChildIndex])) { // compare second child with selected element
                selectedChildIndex = secondChildIndex;
            }
            if(selectedChildIndex != parentIndex) {
             int tmp = array[selectedChildIndex];
             array[selectedChildIndex] = array[parentIndex];
             array[parentIndex] = tmp;
            } else {
                break;
            }
            parentIndex = selectedChildIndex;
        }
    }

    public void maxHeapify(int[] array, int index, int size) {
        heapify(array, index, size, greater); // greater function since we want to select greater element
    }
    public void minHeapify(int[] array, int index, int size) {
        heapify(array, index, size, less); // less function since we want to select small element
    }

    public void buildMaxHeap(int[] array, int size) {
        // starting with k/2 nodes swap child to parent if child node is greater then parent
        for (int idx = size/2; idx >=0; idx--) {
            maxHeapify(array, idx, size);
        }
    }
    public void buildMinHeap(int[] array, int size) {
        // starting with k/2 nodes swap child to parent if child node is smaller then parent
        for (int idx = size/2; idx >=0; idx--) {
            minHeapify(array, idx, size);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(false,12, false);
        int[] arr = { 1, 4, 7, 12, 15, 14, 9, 2, 3, 16 };
        for (int elem : arr) {
            heap.insert(elem);
        }
        heap.printHeap();
        for (int idx = 0; idx < arr.length; idx++) {
            System.out.println(heap.delMax());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Before heapify: "+Arrays.toString(arr));
        heap.buildMaxHeap(arr, arr.length);
        System.out.println("After Max heapify: "+Arrays.toString(arr));
        heap.buildMinHeap(arr, arr.length);
        System.out.println("After Min heapify: "+Arrays.toString(arr));
    }


}
