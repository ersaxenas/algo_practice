package com.lrn.leetcode.google;

public class Q418SentenceScreenFitting {
    /*
    * pd: Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.
Note:
A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
    * assm: best time sol, sentence size < 1000 words,
    * appr:
    * test cases:
    * */

    public int wordsTyping(String[] sentence, int rows, int cols) {
       if(sentence == null || sentence.length ==0) {
           return 0;
       }
        final String sents = String.join(" ", sentence) + " ";
        int start =0;
        int len = sents.length();
        for(int row=0; row<rows; row++) {
            start = start + cols; // add all cols
            // using mod here since string repeats
            if(sents.charAt(start % len) == ' ') { // start is at space char to string formed with words fit in the cols
               start++;
            } else { // string doesnt fit
                // move start in backward direction
                while(start > 0 && sents.charAt((start-1)% len) != ' ') {
                    start--;
                }
            }
        }
        return start/len;
    }

    public static void main(String[] args) {
        Q418SentenceScreenFitting sol = new Q418SentenceScreenFitting();
        System.out.println(sol.wordsTyping(new String[]{"a"}, 3, 6));

    }


}
