package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q89GrayCode {
    /*
    * pd: https://leetcode.com/problems/gray-code
    * assm: n < 100, best time sol
    * appr:
    * test case:
    *
    * */

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
