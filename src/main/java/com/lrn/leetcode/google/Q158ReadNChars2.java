package com.lrn.leetcode.google;

public class Q158ReadNChars2 {
    /*2022-04-24T18:03:55.625Z
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
            if(buffCharCount == 0) {// eof
                break;
            }
            while(chidx < n && buffIndex < buffCharCount) {
                buf[chidx++] = buff4[buffIndex++]; // copy data
            }
            if(buffIndex == buffCharCount) {
                buffIndex =0;
            }

        }
        return chidx;
    }

    char[] buf4 = new char[4];
    int datalen = 0;
    int buf4idx = 0;
    public int read2(char[] buf, int n) {
        int idx=0;
        while(buf4idx > 0 && buf4idx < datalen && n > 0) {
            buf[idx++] = buf4[buf4idx++];
            n--;
        }
        if(n <= 0) return idx;
        while( n > 0 && (this.datalen = read4(buf4)) > 0) {
            for(buf4idx=0; buf4idx < datalen && n > 0; buf4idx++, n--) {
                buf[idx++] = buf4[buf4idx];
            }
        }
        return idx;
    }


    public int read4(char[] b4) {
        buf4[0] = 'a';
        buf4[1] = 'b';
        buf4[2] = 'c';
        return 3;
    }

    public static void main(String[] args) {
        Q158ReadNChars2 sol = new Q158ReadNChars2();
        sol.read2(new char[1],1);
        sol.read2(new char[2],2);
        sol.read2(new char[1],1);
    }

}
