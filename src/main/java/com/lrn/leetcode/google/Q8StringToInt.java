package com.lrn.leetcode.google;

public class Q8StringToInt {
    /*
    https://leetcode.com/problems/string-to-integer-atoi/
     * Problem def: Convert given string to int
     * Assumptions : String can have white spaces, String contain optional + or - sign, String will not contain only white spaces and will not be empty string
     *               Best time solution,
     * Test cases:
     *  "43" res 43
     *  "  -42" res = -42
     *  "  4193 with words" res = 4193
     *  "words and 987" res = 0
     *  "-9839299394" res int.MIN
     *  "980923439299394" res int.MAX
     * APPROACH: Start reading string chr by char
     * if white space move to next char
     * if + or - save sign save sign
     * if digit add and check for over flow  (if over flow return int.min or max)
     * if not whitespace or digit then break
     * */
    public int convert(String str) {
        boolean isNegative = false;
        int num = 0;
        char[] chars = str.toCharArray();
        int index=0;
       //ignore white spaces
        while(index < chars.length) {
            if(chars[index] == ' ') {
                index++;
                continue;
            }
            break;
        }
        if(index == chars.length) {
            return 0;
        }
        // check for sign
        if(chars[index] == '-') {
           isNegative = true;
           index++;
        } else if(chars[index] == '+') {
            index++;
        }
        // now we expext only digits
        while(index < chars.length) {
            if(Character.isDigit(chars[index])) {
                int numericValue = Character.getNumericValue(chars[index]);
                // if num < int.max / 10 then add
                // if num == int.max / 10 and numericvalue < last dig of int.max the add
                if((num < Integer.MAX_VALUE/10) || ((num == Integer.MAX_VALUE/10 && numericValue <= Integer.MAX_VALUE%10))) {
                    num = num * 10 + numericValue;
                } else {
                    return (isNegative) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
            } else {
                break;
            }
            index++;
        }
      return (isNegative) ? -1* (num) : num;
    }

    public static void main(String[] args) {
        Q8StringToInt q8StringToInt = new Q8StringToInt();
        System.out.println(q8StringToInt.convert("42"));
        System.out.println(q8StringToInt.convert("  -42"));
        System.out.println(q8StringToInt.convert("  +1"));
        System.out.println(q8StringToInt.convert("  +-2"));
        System.out.println(q8StringToInt.convert("  -+2"));
        System.out.println(q8StringToInt.convert("  4193 with words"));
        System.out.println(q8StringToInt.convert("words and 987"));
        System.out.println(q8StringToInt.convert("-9839299394"));
        System.out.println(q8StringToInt.convert("980923439299394"));
        System.out.println(q8StringToInt.convert("   +0 123"));
        System.out.println(q8StringToInt.convert("2147483648"));
    }

}
