package com.lrn.leetcode.google;

public class Q912SortAnArray {
    /* https://leetcode.com/problems/sort-an-array/discuss/492042/7-Sorting-Algorithms-(quick-sort-top-downbottom-up-merge-sort-heap-sort-etc.)
    * pd: https://leetcode.com/problems/sort-an-array/
    * assm: array len < 10000, best time sol
    * appr: merge sort / quick sort
    * test cases:
    * */
    /*merge sort*/
    public int[] mergeSort(int[] nums) {
        int[] aux = new int[nums.length];
        sort(nums, aux, 0, nums.length-1);
        return nums;
    }

    public void sort(int[] nums, int[] aux, int start, int end) {
        if(start >= end) return;
        int mid = start + (end - start)/2;
        sort(nums, aux, start, mid); // first half
        sort(nums, aux, mid+1, end); // second half
        merge(nums, aux, start, mid, end);
    }


    public void merge(int[] nums, int[] aux, int start, int mid, int end) {
        //copy to aux
        for(int idx=start; idx <= end; idx++) {
            aux[idx] = nums[idx];
        }
        int i = start, j = mid+1;
        for(int idx=start; idx <= end; idx++) {
            if( i > mid) nums[idx] = aux[j++];// take j
            else if( j > end) nums[idx] = aux[i++]; // take i
            else if(aux[i] < aux[j]) nums[idx] = aux[i++]; // take i since it is less
            else nums[idx] = aux[j++]; // take j
        }
    }




    /*quick sort no dups*/
    public int[] sortArray(int[] nums) {
        quicksort(nums, 0, nums.length-1);
        return nums;
    }
    public void quicksort(int[] nums, int start, int end) {
        if(start >= end) return;
        int partition = partition(nums, start, end);
        quicksort(nums, start, partition-1);
        quicksort(nums, partition+1, end);
    }

    public int partition(int[] nums, int start, int end) {
        int pivot = nums[start]; // saved pivot and space at start index is blank
        while(start < end) {
            // now consider space at index start is empty so find elem less than pivot from right and store at index start
            while(start < end && nums[end] >= pivot) end--;
            nums[start] = nums[end];
            // now consider space at index end is empty so find elem greater than pivot from left and store at index end
            while(start < end && nums[start] <= pivot) start++;
            nums[end] = nums[start];
            // once again space at nums[start] is empty
        }
        // store pivot at nums[start] - last empty slot
        nums[start] = pivot;
        return start; // return index of start
    }
  /* three-way partitioning for dups*/
  public int[] sortArray3(int[] nums) {
      quicksortDup(nums, 0, nums.length-1);
      return nums;
  }

    public void quicksortDup(int[] nums, int start, int end) {
        if(start > end) return;
        int lt=start, gt = end, idx=start;
        int pivot = nums[start];
        while(idx <= gt) { // remember <= gt
            if(nums[idx] < pivot) {
                swap(nums, idx, lt);
                idx++;
                lt++;
            } else if( nums[idx] > pivot) {
                swap(nums, idx, gt);
                gt--;
                /* here no idx++ : element at gt moved to idx which is a new elem we have not see.
                 * so in the next loop iteration we will evaluate it.
                 */
            } else {
                idx++;
            }
        }
        quicksortDup(nums, start, lt-1);
        quicksortDup(nums, gt+1, end);
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }


    public static void main(String[] args) {
        Q912SortAnArray sol = new Q912SortAnArray();
        LsUtil.printArray(sol.sortArray(new int[] {5,2,3,1}));
        LsUtil.printArray(sol.sortArray(new int[] {5,1,1,2,0,0}));
        LsUtil.printArray(sol.sortArray(new int[] {-1,2,-8,-10}));
//        final int[] nums = {1, 1, 1, 2, 0, 0};
//        System.out.println(sol.threeWayPartition(nums, 0, 5));
//        LsUtil.printArray(nums);
    }


}
