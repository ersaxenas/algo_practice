package com.lrn.leetcode.google;

import java.util.Arrays;

public class Q299BullsAndCows {
    /*
    * pd: You are playing the Bulls and Cows game with your friend.
You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
The number of "bulls", which are digits in the guess that are in the correct position.
The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
    * assm: only digits, string len < 1000, best time sol, str len == guess len
    * appr:
    *
    * test cases:
    * Input: secret = "1807", guess = "7810" Output: "1A3B"
    * Input: secret = "1123", guess = "0111" Output: "1A1B"
    * */

    public String getHint(String secret, String guess) {
        int[] digitcount = new int[10];
        int bull = 0, cow = 0;
        for (int idx = 0; idx < secret.length(); idx++) {
              int sc = secret.charAt(idx) - '0';
              int gd = guess.charAt(idx) - '0';
             if(sc==gd) {
                 bull++;
             } else {
                 if(digitcount[sc] < 0) { // checking for -ve value so we have seen it guess string
                     cow++; //
                 }
                 digitcount[sc]++; // seen digit in secret so +ve value
                 if(digitcount[gd] > 0) {// checking for +ve value so we have seen it secret string
                    cow++;
                 }
                 digitcount[gd]--; // seen digit in guess so -ve value
             }
        }
        return bull+"A"+cow+"B";
    }

    public static void main(String[] args) {
        Q299BullsAndCows sol = new Q299BullsAndCows();
        System.out.println(sol.getHint("1122", "1222"));
        System.out.println(sol.getHint("1807", "7810"));
        System.out.println(sol.getHint("1123", "0111"));
    }
}
