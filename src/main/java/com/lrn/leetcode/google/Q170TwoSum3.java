package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Collections;

public class Q170TwoSum3 {
    /*
    * pd: https://leetcode.com/problems/two-sum-iii-data-structure-design/
    * assm: arr len < 10000, -1000 < elem < 10000, best time sol
    * appr: two pointers on array list
    * test cases:
    * */

    static class TwoSum3 {

        /**
         * Initialize your data structure here.
         */
        ArrayList<Integer> list = new ArrayList<>();
        boolean isSorted = false;

        public TwoSum3() {

        }

        /**
         * Add the number to an internal data structure..
         */
        public void add(int number) {
            list.add(number);
            isSorted = false;
        }

        /**
         * Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            if (!isSorted) {
                Collections.sort(list);
                isSorted = true;
            }
            int p1 = 0, p2 = list.size() - 1, sum;
            while (p1 < p2) {
                sum = list.get(p1) + list.get(p2);
                if (sum == value) {
                    return true;
                }
                if (sum < value) {
                    p1++;
                } else {
                    p2--;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        TwoSum3 sol = new TwoSum3();
        sol.add(1);
        sol.add(3);
        sol.add(5);
        sol.add(4);
        System.out.println(sol.find(7));
    }

}
