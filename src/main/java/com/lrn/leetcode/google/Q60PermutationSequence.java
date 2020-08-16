package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q60PermutationSequence {

    /*
    * pd: The set [1,2,3,...,n] contains a total of n! unique permutations.
    * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
    * "123"
    * "132"
    * "213"
    * "231"
    * "312"
    * "321"
    * Given n and k, return the kth permutation sequence.
    *
    * */

    public String getPermutation(int n, int k) {
        List<Integer> ls = new ArrayList<>();
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for(int idx=1; idx<=n; idx++) {
            factorial[idx]= factorial[idx-1] * idx;
            ls.add(idx);
        }
        k--;
        StringBuilder sbr = new StringBuilder();
        for(int idx=1; idx<=n; idx++) {
            int eindex = k/factorial[n-idx];
            sbr.append(ls.get(eindex));
            ls.remove(eindex);
            k= k - eindex*factorial[n-idx];
        }
       return sbr.toString();
    }

    public static void main(String[] args) {
        Q60PermutationSequence sol = new Q60PermutationSequence();
        sol.getPermutation(3,3);
    }

}
