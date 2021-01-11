package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q274Hindex {
    /*
     * pd: Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
     * assm: non null elem, arr len < 10000, only +ve ints, best time sol
     * appr:
     *
     * Formally, if f is the function that corresponds to the number of citations for each publication,
* we compute the h-index as follows: First we order the values of f from the largest to the lowest value.
* Then, we look for the last position in which f is greater than or equal to the position (we call h this position).
* For example, if we have a researcher with 5 publications A, B, C, D, and E with 10, 8, 5, 4, and 3 citations, respectively,
* the h-index is equal to 4 because the 4th publication has 4 citations and the 5th has only 3.
* In contrast, if the same publications have 25, 8, 5, 3, and 3 citations,
* then the index is 3 (i.e. the 3rd position) because the fourth paper has only 3 citations.
f(A)=10, f(B)=8, f(C)=5, f(D)=4, f(E)=3　→ h-index=4
* 1        2       3       4       5  -> at 5 we have citation < 5 so h index 4
f(A)=25, f(B)=8, f(C)=5, f(D)=3, f(E)=3　→ h-index=3
* 1        2       3       4       5     -> at 4 we have citation < 4 so h index 3
     * test cases:
     * Input: citations = [3,0,6,1,5] Output: 3
     * Input: citations = [10,8,5,4,3] Output: 4
     * Input: citations = [25,8,5,3,3] Output: 4
     * */

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);
        for (int elem : citations) {
            maxHeap.add(elem);
        }
        int idx = 1;
        while (!maxHeap.isEmpty() &&  maxHeap.poll() >= idx) {
           idx++;
        }
        return idx-1;
    }

    public static void main(String[] args) {
        Q274Hindex sol = new Q274Hindex();
        System.out.println(sol.hIndex(new int[]{3, 0, 6, 1, 5}) == 3 ? "pass" : "fail");

    }

}
