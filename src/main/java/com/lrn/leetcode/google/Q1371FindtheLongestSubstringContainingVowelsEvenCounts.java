package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.HashMap;

public class Q1371FindtheLongestSubstringContainingVowelsEvenCounts {
    /*
    * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
    *
    * */

    class Node {

        public boolean[] vowels;

        Node(boolean[] vowels){
            this.vowels = vowels;
        }

        @Override
        public boolean equals(Object n){
            if(n == null || !(n instanceof Node)) return false;
            Node nn = (Node) n;
            if(nn.vowels.length != vowels.length) return false;
            for(int i = 0; i < vowels.length; i++){
                if(nn.vowels[i] != vowels[i]) return false;
            }
            return true;
        }

        @Override
        public int hashCode(){
            int ans = 0;
            for(int i = 0; i < vowels.length; i++){
                ans += 10 * ans + (vowels[i] ? 1 : 0);
            }
            return ans;
        }

        @Override
        public String toString() {
            StringBuilder sbr = new StringBuilder();
            for(boolean v: vowels) {
                if(v) {
                    sbr.append("T");
                } else {
                    sbr.append("F");
                }
            }
            return sbr.toString();
        }
    }

    public int findTheLongestSubstring(String S) {
        char ch[] = S.toCharArray();

        // HashMap saving Node (sequences) to index map :
        HashMap<Node, Integer> map = new HashMap<>();

        // Initially, the sequence is all true (all have '0' occurences) :
        boolean[] vowels = new boolean[5];
        Arrays.fill(vowels, true);

        int maxLen = 0;

        // All vowels :
        String v = "aeiou";

        for(int i = 0; i < ch.length; i++){

            // Check whether it is a vowel :
            int k = v.indexOf(ch[i]);
            if(k != -1){

                // If a vowel had odd occurence, now it will be even.
                // Or, if a vowel had even occurence, now it will be odd :
                vowels[k] = !vowels[k];
            }

            // If all are true, then 0 to i is the max length :
            for(int j = 0; j <= vowels.length; j++){
                if(j == vowels.length){
                    maxLen = i+1;
                    break;
                }
                if(!vowels[j]) break;
            }
            LsUtil.printArray(vowels);

            // This is the current sequence :
            Node node = new Node(vowels.clone());

            // If we have this sequence in the map,
            // then the length will be from location of earlier sequence till i.
            if(map.containsKey(node)){
                maxLen = Math.max(maxLen, i - map.get(node));
            } else {

                // Else, we don't have the sequence, put it in the map :
                map.put(node, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Q1371FindtheLongestSubstringContainingVowelsEvenCounts sol = new Q1371FindtheLongestSubstringContainingVowelsEvenCounts();
        System.out.println(sol.findTheLongestSubstring("eleetminicoworoep"));
    }

}
