package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q089GrayCode {
    /*2022-01-11T15:58:20.183Z
    * pd: https://leetcode.com/problems/gray-code
    * assm: n < 100, best time sol
    * appr:
    * test case:
    *
    * */

    public List<Integer> graycode2(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for(int idx=0; idx < n; idx++) {
            int listSize = result.size();
            int next = 1 << idx; // 1, 2, 4, 8, ...
            for(int i=listSize-1; i>=0; i--) {
                int elem = result.get(i) + next;
                result.add(elem);
            }
        }
        return result;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> rs=new ArrayList<Integer>();
        rs.add(0);
        for(int idx=0; idx< n; idx++) {
            int size = rs.size();
            for(int i=size-1; i>=0; i--) {
                int elem = rs.get(i) | (1 << idx);
                rs.add(elem);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
         Q89GrayCode sol = new Q89GrayCode();
         LsUtil.printList(sol.grayCode(3));

    }

}
