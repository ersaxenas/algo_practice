package com.lrn.leetcode.google;

public class Q12IntegerToRoman {
    /* https://leetcode.com/problems/integer-to-roman/
    * PD: Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
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
    public String encodeToRoman(int num) {
        int[] numArr = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArr = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sbr = new StringBuilder();
        for(int idx=0; idx<numArr.length; idx++) {
            while(num >= numArr[idx]) {
                sbr.append(romanArr[idx]);
                num = num - numArr[idx];
            }
        }
        return sbr.toString();
    }

    public String encodeToRoman2(int num) {
        String[] M={"","M","MM","MMM"};
        String[] C={"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] X={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] I={"","I","II","III","IV","V","VI","VII","VIII","IX"};

        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    public static void main(String[] args) {
        Q12IntegerToRoman q12IntegerToRoman = new Q12IntegerToRoman();
        System.out.println("III".equals(q12IntegerToRoman.encodeToRoman(3)));
        System.out.println("IV".equals(q12IntegerToRoman.encodeToRoman(4)));
        System.out.println("IX".equals(q12IntegerToRoman.encodeToRoman(9)));
        System.out.println("LVIII".equals(q12IntegerToRoman.encodeToRoman(58)));
        System.out.println("MCMXCIV".equals(q12IntegerToRoman.encodeToRoman(1994)));

        System.out.println("III".equals(q12IntegerToRoman.encodeToRoman2(3)));
        System.out.println("IV".equals(q12IntegerToRoman.encodeToRoman2(4)));
        System.out.println("IX".equals(q12IntegerToRoman.encodeToRoman2(9)));
        System.out.println("LVIII".equals(q12IntegerToRoman.encodeToRoman2(58)));
        System.out.println("MCMXCIV".equals(q12IntegerToRoman.encodeToRoman2(1994)));
    }

}
