package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q068TextJustification {
    /* 2022-01-05T06:03:37.805Z
    * pd: https://leetcode.com/problems/text-justification/
    *
    * */

    class LinePojo {
        public List<String> words;
        public int len;
        public int start;
        public int end;

        public LinePojo(List<String> words, int len, int start, int end) {
            this.words = words;
            this.len = len;
            this.start = start;
            this.end = end;
        }
    }

    public List<String> fullJustify(String[] words, int maxwidth) {
        int start=0;
        List<String> result = new ArrayList<>();
        while(start< words.length) {
            LinePojo line = findLineEnd(start, words, maxwidth);
            String formatedLine = justify(line,maxwidth, line.end == words.length-1);
            System.out.println(formatedLine);
            result.add(formatedLine);
            start = line.end+1;
        }
      return result;
    }

    public String justify(LinePojo line, int maxwidth, boolean lastline) {
        int spacesToAdd = maxwidth - line.len;
        /*if line has only one word and spaces to be added are more then 0 */
        if(line.words.size() == 1 && spacesToAdd > 0) {
            line.words.add("");
        }
        /*add spaces*/
       if(lastline && spacesToAdd > 0) { // if last line then left justify
           line.words.add(new String(new char[spacesToAdd]).replace('\0',' '));
       } else {
           /* right justify */
           /*spaces are at odd indexes, 1, 3, 5.. */
           int spaceidx=1;
           for(int idx = 0; idx< spacesToAdd; idx++) {
               if(spaceidx >= line.words.size()) {
                   spaceidx = 1;
               }
               line.words.set(spaceidx, line.words.get(spaceidx)+" ");
               spaceidx = spaceidx +2;
           }
       }
       return String.join("",line.words);
    }

    public LinePojo findLineEnd(int start, String[] words, int maxwidth) {
        List<String> wordlist = new ArrayList<>();
        int end = start;
        int currlen = words[end].length();
        wordlist.add(words[end]);
        end++;
        /*now add words to window and keep track of length <= maxwidth*/
        /* currlen + 1 space + next word length <= maxwidth */
        while((end)<words.length && (currlen + 1 + words[end].length()) <= maxwidth) {
            currlen = currlen + 1 + words[end].length();
            wordlist.add(" "); /* add space - spaces are at odd indexes, 1, 3, 5.. */
            wordlist.add(words[end]); /* add word - words are at event index 0, 2, 4... */
            end++;
        }
        return new LinePojo(wordlist, currlen, start,end-1);
    }

    public static void main(String[] args) {
        Q068TextJustification sol = new Q068TextJustification();
        String[] strarr= {"This", "is", "an", "example", "of", "text", "justification."};
        sol.fullJustify(strarr, 16);
         strarr= new String[] {"What","must","be","acknowledgment","shall","be"};
        sol.fullJustify(strarr, 16);
    }

}
