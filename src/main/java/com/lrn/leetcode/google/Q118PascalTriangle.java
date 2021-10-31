package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q118PascalTriangle {
    /* https://leetcode.com/problems/pascals-triangle
     *pd: Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
     *Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
     * assm: best time sol, input < 100
     * appr: add 1 at 0 position when size > 2 start from index 1 and calculate value at index = [index] + [index+1]
     *
     *
     *
     * */

    public List<List<Integer>> generate(int numRow) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> lst = new ArrayList<>();
        for(int idx=0; idx<numRow; idx++) {
            lst.add(0,1);
            for(int p=1; p<lst.size()-1; p++) {
                lst.set(p,lst.get(p)+lst.get(p+1));
            }
            result.add(new ArrayList<>(lst));
        }
        return result;
    }

    public static void main(String[] args) {
        Q118PascalTriangle sol = new Q118PascalTriangle();
        LsUtil.printListOfList(sol.generate(10));
    }
}
