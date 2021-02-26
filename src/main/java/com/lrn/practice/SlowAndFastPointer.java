package com.lrn.practice;

import java.util.HashSet;

public class TwoPointers {

    public boolean findHappyNum(int num) {
        long slow=num, fast=num;
        do {
            slow = square(slow);
            fast = square(square(fast));
        } while(slow != fast);

        return slow ==1;
    }

    public long square(long num) {
        long sum =0, dig;
        while(num >0) {
            dig = num%10;
            sum = sum + dig * dig;
            num /=10;
        }
        return sum;
    }

    public boolean loopCheck(int[] arr) {
        for(int idx=0; idx<arr.length; idx++) {
            int slow = idx, fast = idx;
            boolean isFwd = arr[idx] > 0;
            do {
                slow = findNextIndex(arr, isFwd, slow);
                fast = findNextIndex(arr, isFwd, fast);
                if(fast != -1) {
                    fast = findNextIndex(arr, isFwd, fast);
                }
            } while(slow != -1 && fast != -1 && slow != fast);
            if(slow != -1 && fast != -1) {
                return true;
            }
        }
        return false;
    }


    public int findNextIndex(int[] arr, boolean isFwd, int currIdx) {
        boolean direction = arr[currIdx] >= 0;
        if(isFwd != direction) {
            return -1; // direction changed
        }
        int nextIdx = (currIdx + arr[currIdx]) % arr.length;
        // for -ve nums in arr
        if(nextIdx < 0) {
            nextIdx = arr.length -1;
        }
        if(currIdx == nextIdx) {
            return -1;
        }
        return nextIdx;
    }



    public static void main(String[] args) {
        TwoPointers sol = new TwoPointers();
/*
        System.out.println(sol.findHappyNum(23));
        System.out.println(sol.findHappyNum(12));
*/
        System.out.println(sol.loopCheck(new int[]{1, 2, -1, 2, 2}));
        System.out.println(sol.loopCheck(new int[]{2, 2, -1, 2 }));
        System.out.println(sol.loopCheck(new int[]{2, 1, -1, -2}));

    }

}
