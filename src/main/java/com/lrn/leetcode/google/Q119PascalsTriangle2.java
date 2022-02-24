package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q119PascalsTriangle2 {
    /*2022-02-06T10:17:44.764Z
    * https://leetcode.com/problems/pascals-triangle-ii
    * assm: row < 1000, best time sol,
    * appr: same as 118
    * test cases:
    * */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for(int row=0; row <= rowIndex; row++) {
            result.add(0,1);
            for(int col=1; col < result.size()-1; col++) {
                result.set(col, result.get(col) + result.get(col+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q119PascalsTriangle2 sol = new Q119PascalsTriangle2();
        LsUtil.printList(sol.getRow(3));
    }
}
