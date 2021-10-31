package com.lrn.leetcode.google;

public class Q157ReadNChars {
    /*
    * pd: https://leetcode.com/problems/read-n-characters-given-read4/
    * assm: max char read 100 mb, best time sol
    * appr:
    * test cases:
    * */

    public int read(char[] buf, int n) {
        int idx=0; /* index where next char will be stored in the buf array*/
        int charsRead=0; /* chars read by read4 method */

        char[] buf4 = new char[4]; /* char buffer which for read4 method call*/
        while( (charsRead = read4(buf4)) > 0 && n > 0) { /* read if n is greater then 0 */
            for(int i =0; i < charsRead && n > 0; i++, n--) { /* read chars from buf4 array and decrement n for each char read*/
                buf[idx++] = buf4[i]; /* store in buf array*/
                if(n <= 0) break; /* stop if buff array has n chars stored*/
            }
        }
        return idx; /*return total no. of chars read */
    }

    public int read2(char[] buf, int n) {
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
