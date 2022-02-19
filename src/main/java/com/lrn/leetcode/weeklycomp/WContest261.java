package com.lrn.leetcode.weeklycomp;

public class WContest261 {
    /*
    * https://leetcode.com/contest/weekly-contest-261/problems/minimum-moves-to-convert-string/
    * assm: String len < 10000, best time sol
    *
    * */
    public int minimumMoves(String s) {
        int idx =0, cnt=0;
        while(idx < s.length()) {
            if(s.charAt(idx) == 'X') {
                cnt++;
                idx = idx+3;
            } else {
                idx++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        WContest261 sol = new WContest261();


    }


}
