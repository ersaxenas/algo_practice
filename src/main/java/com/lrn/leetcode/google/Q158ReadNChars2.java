package com.lrn.leetcode.google;

public class Q158ReadNChars2 {
    /*
     * pd: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
     * assm:
     * appr:
     *
     * test cases
     *
     * */
    int buffCharCount = 0;
    int buffIndex = 0;
    char[] buff4 = new char[4];

    public int read(char[] buf, int n) {
        int chidx = 0;
        while (chidx < n) {
            if(buffIndex ==0) { // read data
                buffCharCount = read4(buff4);
            }
            while(chidx < n && buffIndex < buffCharCount) {
                buf[chidx++] = buff4[buffIndex++]; // copy data
            }
            if(buffIndex == buffCharCount) {
                buffIndex =0;
            }
            if(buffCharCount == 0) {// eof
                break;
            }
        }
        return chidx;
    }

    public int read4(char[] b4) {
        return 0;
    }

}
