package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Q412FizzBuzz {
    /*
    * pd: https://leetcode.com/problems/fizz-buzz
    * assm: int < 1000, best time sol
    * appr:
    * test cases:
    * */



    /*faster sol. */
    public List<String> fizzBuzz2(int n) {
        List<String> result = new ArrayList<>();
        int cnt3=3, cnt5=5;
        StringBuilder buff = new StringBuilder();
        for(int idx=1; idx<=n; idx++) {
            cnt3--;
            cnt5--;
            if(cnt3 == 0)  {
                buff.append("Fizz");
                cnt3=3;
            }
            if(cnt5 == 0) {
                buff.append("Buzz");
                cnt5=5;
            }
            if(buff.length() == 0) {
                result.add(String.valueOf(idx));
            } else {
                result.add(buff.toString());
                buff.setLength(0);
            }
        }
        return result;
    }
    /*based on hash map approach*/
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        Map<Integer,String> strmap = new LinkedHashMap<>();
        strmap.put(3,"Fizz");
        strmap.put(5, "Buzz");
        for(int idx=1; idx <= n; idx++) {
            StringBuilder buffer = new StringBuilder();
            for(int num: strmap.keySet()) {
                if(idx % num == 0) {
                    buffer.append(strmap.get(num));
                }
            }
            if(buffer.length() == 0) {
                result.add(String.valueOf(idx));
            } else {
                result.add(buffer.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q412FizzBuzz sol = new Q412FizzBuzz();
        LsUtil.printList(sol.fizzBuzz2(15));
    }

}
