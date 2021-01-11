package com.lrn.leetcode.google;

public class Q157ReadNChars {
    /*
    * pd: https://leetcode.com/problems/read-n-characters-given-read4/
    * assm: max char read 100 mb, best time sol
    * appr:
    * test cases:
    * */

    public int read(char[] buf, int n) {
        if(buf.length < n) {
            throw new IllegalStateException("Buffer size is smaller then " +n);
        }
        int bytesToRead = n;
        char[] buf4 = new char[4];
        int bufidx=0;
        while(bytesToRead>0) {
            int br = read4(buf4);
            if(br == 0) {
                break;
            }
            for(int idx=0; idx<br; idx++) {
                buf[bufidx++] = buf4[idx];
            }
            bytesToRead = bytesToRead-br;
        }
        return Math.min(bufidx,n);
    }

    public int read4(char[] buf) {
      return 0;
    }

}
