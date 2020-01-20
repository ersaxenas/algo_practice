package com.lrn.educative.gci.bsearch;

import java.lang.reflect.Array;

public class WorkBSearchUtils {
    /*Order-agnostic Binary Search*/
    static class OrderAgnosticBinarySearch {
        public Integer search(int[] arr, int elem) {
            int start = 0, end = arr.length - 1, mid = 0;
            boolean isAscending = arr[start] < arr[end];
            while (start <= end) {
                mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                // ascending array
                if (isAscending) {
                    if (elem < midElem) {
                        end = mid - 1;
                    } else if (elem > midElem) {
                        start = mid + 1;
                    } else {
                        return midElem;
                    }
                } else { // descending array
                    if (elem < midElem) {
                        start = mid + 1;
                    } else if (elem > midElem) {
                        end = mid - 1;
                    } else {
                        return midElem;
                    }
                }
            }
            return null;
        }

        public static void main(String[] args) {
            OrderAgnosticBinarySearch orderAgnosticBinarySearch = new OrderAgnosticBinarySearch();
            System.out.println(orderAgnosticBinarySearch.search(new int[]{4, 6, 10}, 10));
            System.out.println(orderAgnosticBinarySearch.search(new int[]{1, 2, 3, 4, 5, 6, 7}, 5));
            System.out.println(orderAgnosticBinarySearch.search(new int[]{10, 6, 4}, 10));
            System.out.println(orderAgnosticBinarySearch.search(new int[]{10, 6, 4}, 4));
        }
    }

    /*Ceiling of a Number*/
    static class CeilingOfNumber {
        public Integer search(int[] arr, int key) {
            assert arr != null && arr.length > 0;
            int start = 0, end = arr.length - 1, mid = 0;
            boolean isAscending = arr[start] < arr[end];
            while (start <= end) {
                mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                if (isAscending) {
                    if (key < midElem) {
                        end = mid - 1;
                    } else if (key > midElem) {
                        start = mid + 1;
                    } else {
                        return mid;
                    }
                } else {
                    if (key < midElem) {
                        start = mid + 1;
                    } else if (key > midElem) {
                        end = mid - 1;
                    } else {
                        return mid;
                    }
                }
            }
            if (start >= arr.length) {
                return (isAscending) ? arr.length - 1 : 0;
            }
            return start; // by the end of while loop start will point to smallest number greater than the ‘key’
        }

        public static void main(String[] args) {
            CeilingOfNumber ceilingOfNumber = new CeilingOfNumber();
            System.out.println(ceilingOfNumber.search(new int[]{4, 6, 10}, 6));
            System.out.println(ceilingOfNumber.search(new int[]{1, 3, 8, 10, 15}, 12));
            System.out.println(ceilingOfNumber.search(new int[]{4, 6, 10}, 17));
            System.out.println(ceilingOfNumber.search(new int[]{4, 6, 10}, -1));
        }
    }

    /*Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ‘key’.
Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter. This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
Write a function to return the next letter of the given ‘key’.*/
    static class NextLetter {
        public Character find(char[] arr, char ch) {
            assert arr != null && arr.length > 1;
            int arrLen = arr.length;
            int start = 0, end = arr.length - 1, mid = 0;
            if (ch < arr[start] || ch > arr[end]) {
                return arr[start];
            }
            while (start <= end) {
                mid = start + ((end - start) / 2);
                char midElem = arr[mid];
                if (ch < midElem) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return arr[start % arrLen];
        }

        public static void main(String[] args) {
            NextLetter nextLetter = new NextLetter();
            System.out.println(nextLetter.find(new char[]{'a', 'c', 'f', 'h'}, 'f'));
            System.out.println(nextLetter.find(new char[]{'a', 'c', 'f', 'h'}, 'b'));
            System.out.println(nextLetter.find(new char[]{'a', 'c', 'f', 'h'}, 'm'));
            System.out.println(nextLetter.find(new char[]{'a', 'c', 'f', 'h'}, 'h'));
        }
    }
  /*Given an array of numbers sorted in ascending order, find the range of a given number ‘key’. The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].*/
  static class FindRange {
      public int[] findRange(int[] arr, int key) {
          assert arr!=null && arr.length > 1;
          int[] result = {-1,-1};
           result[0] = search(arr,key,true);
           if(result[0] != -1) {
               result[1] = search(arr,key,false);
           }
          return result;
        }
        public int search(int[] arr, int key, boolean findFirst) {
            int start=0, end=arr.length-1, mid=0, keyIndex = -1;
            while(start<=end) {
                mid = start + ((end-start)/2);
                int midElem = arr[mid];
                if(key<midElem) {
                   end = mid-1;
                } else if(key>midElem) {
                  start = mid+1;
                } else {
                    keyIndex = mid;
                   if(findFirst) {
                       end = mid-1;
                   } else {
                       start = mid+1;
                   }
                }
            }
            return keyIndex;
        }
        public static void main(String[] args) {
          FindRange findRange = new FindRange();
            int[] result = findRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
            System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
            result = findRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
            System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
            result = findRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
            System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        }
    }
    /*Given an infinite sorted array (or an array with unknown size), find if a given number ‘key’ is present in the array. Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.*/
    static class InfiniteSortedArrayBST {
         class ArrayReader {
             int[] arr;
             ArrayReader(int[] arr) {
                 this.arr = arr;
             }
             public Integer get(int idx) {
                 if(idx >= arr.length) {
                     return Integer.MAX_VALUE;
                 }
                 return arr[idx];
             }
         }
         public Integer search(ArrayReader arrayReader, int key) {
             // find bounds
             int start=0, end=1;
             while(arrayReader.get(end) < key) {
                 int nStart = end+1;
                 end = end + (end-start)*2;
                 start = nStart;
             }
             // binary search
             while(start<=end) {
                 int mid = start + ((end-start)/2);
                 int midElem = arrayReader.get(mid);
                 if(key<midElem) {
                     end = mid-1;
                 } else if(key>midElem) {
                     start = mid+1;
                 } else {
                     return mid;
                 }
             }
             return -1;
         }
         public static void main(String[] args) {
             InfiniteSortedArrayBST infiniteSortedArrayBST = new InfiniteSortedArrayBST();
             InfiniteSortedArrayBST.ArrayReader reader = infiniteSortedArrayBST.new  ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
             System.out.println(infiniteSortedArrayBST.search(reader, 16));
             System.out.println(infiniteSortedArrayBST.search(reader, 11));
             reader = infiniteSortedArrayBST.new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
             System.out.println(infiniteSortedArrayBST.search(reader, 15));
             System.out.println(infiniteSortedArrayBST.search(reader, 200));
         }
    }
    /*Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.*/
    static class MinimumDiffElement {
        public Integer search(int[] arr, int key) {
            assert arr!=null && arr.length > 1;
            int start =0, end=arr.length-1, mid=0;
            if(key<arr[start]) {
                return arr[0];
            }
            if(key>arr[arr.length-1]) {
                return arr[arr.length-1];
            }

            while(start<=end) {
                mid = start + ((end-start)/2);
                int midElem = arr[mid];
                if(key < midElem) {
                    end = mid -1;
                } else if(key > midElem) {
                    start = mid+1;
                } else {
                    return midElem;
                }
            }
            int dif1 = (arr[start] - key);
            int dif2 = (key - arr[end]);
            return (dif1 < dif2) ? arr[start] : arr[end];
        }
        public static void main(String[] args) {
            MinimumDiffElement minimumDiffElement = new MinimumDiffElement();
            System.out.println(minimumDiffElement.search(new int[] { 4, 6, 10 }, 7));
            System.out.println(minimumDiffElement.search(new int[] { 4, 6, 10 }, 4));
            System.out.println(minimumDiffElement.search(new int[] { 1, 3, 8, 10, 15 }, 12));
            System.out.println(minimumDiffElement.search(new int[] { 4, 6, 10 }, 17));
        }
    }
    /*Find the maximum value in a given Bitonic array. An array is considered bitonic if it is monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].*/
    static class BitonicArrayMax {
        public Integer findMax(int[] arr) {
            assert arr!=null && arr.length >1;
            int start =0, end=arr.length-1, mid=0;
            while(start<end) {
                mid = start + ((end-start)/2);
                if(arr[mid] < arr[mid+1]) {
                    // ascending
                    start = mid+1;
                } else {
                    end = mid;
                }
            }
            return arr[start];
        }
        public static void main(String[] args) {
            BitonicArrayMax bitonicArrayMax = new BitonicArrayMax();
            System.out.println(bitonicArrayMax.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
            System.out.println(bitonicArrayMax.findMax(new int[] { 3, 8, 3, 1 }));
            System.out.println(bitonicArrayMax.findMax(new int[] { 1, 3, 8, 12 }));
            System.out.println(bitonicArrayMax.findMax(new int[] { 10, 9, 8 }));
        }
    }
    /*Given a Bitonic array, find if a given ‘key’ is present in it. An array is considered bitonic if it is monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].
Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.*/
    static class BitonicArrayBS {
        public Integer findMaxIndex(int[] arr) {
            assert arr != null && arr.length > 1;
            int start=0, end=arr.length-1, mid=0;
            while(start<end) {
                mid = start + ((end-start)/2);
                if(arr[mid] < arr[mid+1]) {
                    // ascending
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }
        public Integer search(int[] arr, int key) {
            assert arr!= null && arr.length > 1;
            int maxElemIndex = findMaxIndex(arr);
            Integer result = binarySearch(arr,0,maxElemIndex,key);
            if(result==null) {
                result = binarySearch(arr,maxElemIndex+1, arr.length-1, key);
            }
            return result;
        }
        public Integer binarySearch(int[] arr, int idx1, int idx2, int key) {
            boolean isAsc = (arr[idx1] < arr[idx2]);
            int start=idx1, end=idx2, mid=0;
            while(start <= end) {
                mid = start + ((end-start)/2);
                int midElem = arr[mid];
                if(isAsc) {
                    if(key < midElem) {
                        end = mid - 1;
                    } else if(key > midElem){
                        start=mid + 1;
                    } else {
                        return mid;
                    }
                } else {
                    if(key < midElem) {
                        start=mid + 1;
                    } else if(key > midElem){
                        end = mid - 1;
                    } else {
                        return mid;
                    }
                }
            }
            return null;
        }
        public static void main(String[] args) {
            BitonicArrayBS bitonicArrayBS = new BitonicArrayBS();
            System.out.println(bitonicArrayBS.search(new int[] { 1, 3, 8, 4, 3 }, 4));
            System.out.println(bitonicArrayBS.search(new int[] { 3, 8, 3, 1 }, 8));
            System.out.println(bitonicArrayBS.search(new int[] { 1, 3, 8, 12 }, 12));
            System.out.println(bitonicArrayBS.search(new int[] { 10, 9, 8 }, 10));
        }
    }
    /*Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number, find if a given ‘key’ is present in it.
Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1. You can assume that the given array does not have any duplicates.*/
    static class BSRotatedArray {
        public Integer search(int[] arr, int key) {
            assert arr!=null && arr.length>1;
            int start=0, end=arr.length-1, mid=0;
            while(start<=end) {
                mid = start + ((end-start)/2);
                if(key == arr[mid]) {
                    return mid;
                }
                if(arr[start]<arr[mid]) { //left side is sorted
                    if(key>=arr[start] && key<arr[mid]) { // is key present in the left side?
                        // since key is less then mid
                        end = mid-1;
                    } else { // look in to right side array
                        start = mid+1;
                    }
                } else { // right side array is sorted
                    if(key>arr[mid] && key <= arr[end]) { // is key present in the side array?
                        start = mid+1;
                    } else {
                        end=mid -1;
                    }
                }
            }
            return null;
        }
        public static void main(String[] args) {
            BSRotatedArray bsRotatedArray = new BSRotatedArray();
            System.out.println(bsRotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
            System.out.println(bsRotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
        }
    }

    /*Given an array of numbers which is sorted in ascending order and is rotated ‘k’ times around a pivot, find ‘k’.
You can assume that the array does not have any duplicates.*/
    static class RotationFinder {
        public Integer search(int[] arr) {
            assert arr!=null && arr.length >1;
            int start=0, end=arr.length-1, mid=0;
            while(start<=end) {
                mid = start + ((end-start)/2);
                int midElem = arr[mid];
                if(midElem > arr[mid+1]) {
                    return mid+1;
                }
                if(arr[mid-1] > midElem) {
                    return mid;
                }
                if(arr[start] < arr[mid]) { // left side of the array is sorted
                    start = mid+1;
                } else {
                    end = mid -1;
                }
            }
            return 0;
        }
        public static void main(String[] args) {
            RotationFinder rotationFinder = new RotationFinder();
            System.out.println(rotationFinder.search(new int[] { 10, 15, 1, 3, 8 }));
            System.out.println(rotationFinder.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
            System.out.println(rotationFinder.search(new int[] { 1, 3, 8, 10 }));
        }
    }
}
