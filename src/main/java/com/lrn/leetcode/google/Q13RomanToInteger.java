package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q13RomanToInteger {
    /*https://leetcode.com/problems/roman-to-integer/
    *
    * Symbol       Value
        I             1
        IV            4
        V             5
        IX            9
        X             10
        XL            40
        L             50
        XC            90
        C             100
        CD            400
        D             500
        CM            900
        M             1000
    * */

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int number  = 0, curr = 0,prev = 0;
        for(int idx=s.length()-1; idx >= 0; idx--) {
            curr = map.get(s.charAt(idx));
            if(curr < prev) {
                number = number - curr;
            } else {
                number = number + curr;
            }
            prev = curr;
        }
        return number;
    }

    public static void main(String[] args) {
        Q13RomanToInteger q13IntegerToRoman = new Q13RomanToInteger();
        System.out.println(q13IntegerToRoman.romanToInt("MCMXCIV"));
        System.out.println(q13IntegerToRoman.romanToInt("IX"));
    }

}
