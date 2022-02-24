package com.lrn.leetcode.google;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Q1153StringTransformIntoAnotherString {
    /*
    * pd: Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
Return true if and only if you can transform str1 into str2.
    * assm; str len < 10000, str1 len == str2 len, only lower case letters, best time sol.
    * appr:
    * This is a proof of why the solution works. This assumes length of s1 and s2 are equal.
    * |s1| = size of set of characters in s1
    * |s2| = size of set of characters in s2
    * For s1 to be transformable to s2, the condition |s1| >= |s2| needs to hold true
    * because |s1| < |s2| means there is at least one character in s1 which maps to multiple characters
    * (because lengths of s1 and s2 are equal) and this makes s1 non-transformable to s2.
    * For example, "aa" -> "bc" is not possible.
    *
    * Now let's examine the condition |s1| >= |s2| in 3 separate cases with various size of |s1|:
1). |s1| < 26
This case is always transformable because any cycle or linked list can be broken by using the character which does not belong to s1.

2). |s1| = 26 and |s1| = |s2|
Let's sub-categorize this in two cases:

2a. s1 = s2
This is transformable because it is already transformed.

2b. s1 != s2
This is not transformable because any transformations will create a cycle.

3). |s1| = 26 and |s1| > |s2|
Since, |s1| > |s2| this suggests there is at least two characters in s1 map to a same character in s2. Let's say those character are 'x' and 'y'.
The transformation 'x' -> 'y' can be made without changing any cyclical characteristic of original transformation because 'x' and 'y' map to same character .
x -> y
z -> y
After 'x' -> 'y', say s1 -> s1'. |s1'| < 26 because 'x' does not belong to s1' and this is same as case 1.

So combining all 3 cases below condition needs to hold true for s1 to be transformable s2:

s1 = s2 OR (No character is s1 map to 2 characters in s2 AND |s2| < 26)
    *
    *
    *
    *
    * */

    public boolean canConvert(String str1, String str2) {
        if(str1.equals(str2)) {
            return true;
        }
        Map<Character, Character> transformationMap = new HashMap<>();
        for(int idx=0; idx<str1.length(); idx++) {
            final char ch1 = str1.charAt(idx);
            final char ch2 = str2.charAt(idx);
            final Character mappedChar = transformationMap.getOrDefault(ch1, ch2);
            if(mappedChar != ch2) {
                 return false; // same chars will be mapped to two chars a ->b, a -> c
            }
            // valid mapping
            transformationMap.put(ch1, mappedChar);
        }
        java.util.Collection<Character> values = transformationMap.values();
        return new HashSet<>(values).size() < 26; // distinct chars in s2 must be less then 26
    }

    public static void main(String[] args) {
        Q1153StringTransformIntoAnotherString sol = new Q1153StringTransformIntoAnotherString();
        System.out.println(sol.canConvert("aabcc", "ccdee"));
        System.out.println(sol.canConvert("leetcode", "codeleet"));
    }



}
