package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {

    public List<List<Integer>> searchTriplets(int[] arr, int targetSum) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for(int idx=0; idx < arr.length; idx++) {
            searchPair(arr, targetSum-arr[idx],idx+1, arr.length-1, result);
        }
        return result;
    }

    public void searchPair(int[] arr, int targetSum, int start, int end, List<List<Integer>> result) {
        while(start < end) {
            int sum = arr[start] + arr[end];
            if(sum == targetSum) {
                result.add(Arrays.asList(-targetSum, arr[start],arr[end]));
                start++;
                end--;
                while(start< arr.length && arr[start] == arr[start-1]) {
                    start++;
                }
                while(end >0 && arr[end] == arr[end+1]) {
                    end--;
                }
            } else if(sum < targetSum) {
                start++;
            } else {
                end--;
            }
        }
    }

    public void sort(int[] arr) {
        int zp=0, tp=arr.length-1, idx=0;
        while(idx <= tp) {
            if(arr[idx] ==0) {
                swap(arr, idx, zp);
                zp++;
                idx++;
            } else if(arr[idx] == 2) {
                swap(arr, idx, tp);
                tp--;
            } else {
                idx++;
            }
        }
    }

    public void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    public boolean compString(String str1, String str2) {
        int index1 = str1.length()-1, index2=str2.length()-1;
        while(index1 >= 0 || index2 >= 0) {
            index1 = getNextCharIdx(str1, index1);
            index2 = getNextCharIdx(str2, index2);
            if(index1 < 0 && index2 < 0) {
                return true; // both strings ended
            }
            if(index1 <0 || index2 < 0) {
                return false; // one of the string ended before other
            }
            if(str1.charAt(index1) != str2.charAt(index2)) {
                return false;
            }
            index1--;
            index2--;
        }
        return true;
    }

    public int getNextCharIdx(String str, int idx) {
        int bkspace =0, index=idx;
        while(index > 0) {
            if(str.charAt(index) == '#') {
                bkspace++;
            } else if( bkspace > 0) {
                bkspace--;
            } else {
                break;
            }
            index--;
        }
        return index;
    }

    public static void main(String[] args) {
        TwoPointers sol = new TwoPointers();
/*
        LsUtil.printListOfList(sol.searchTriplets(new int[] {-3, 0, 1, 2, -1, 1, -2 }, 0));
        LsUtil.printListOfList(sol.searchTriplets(new int[] { -5, 2, -1, -2, 3 }, 0));
*/
/*
        int[] arr = new int[] {2, 2, 0, 1, 2, 0};
        sol.sort(arr);
        LsUtil.printArray(arr);
*/
        System.out.println(sol.compString("xy#z", "xzz#"));
        System.out.println(sol.compString("xy#z", "xyz#"));
        System.out.println(sol.compString("xp#", "xyz##"));
        System.out.println(sol.compString("xywrrmp", "xywrrmu#p"));

    }

}
