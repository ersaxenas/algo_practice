package com.lrn.educative.recur;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkRecStrUtil {
    static class StrReverse {
        public String reverse(String s) {
            // base case
            if(s.isEmpty()) {
                return s;
            }
            // recursive
            return reverse(s.substring(1)) + s.charAt(0);
        }

        public static void main(String[] args) {
            System.out.println(new StrReverse().reverse("abcde"));
        }
    }
    static class DupRemove {
        public String removeDup(String s) {
            // base case
            if(s.length() ==1) {
                return s;
            }
            // recursive
            if(s.substring(0,1).equals(s.substring(1,2))) {
             return removeDup(s.substring(1,2));
            }
            return s.charAt(0) + removeDup(s.substring(1));
        }

        public static void main(String[] args) {
            System.out.println(new DupRemove().removeDup("abcddefgg"));
        }
    }
    static class MergeSortedStrings {
        public String merge(String s1, String s2) {
            // base case
           if(s1==null || s1.isEmpty()) {
               return s2 == null ? s1: s2;
           }
           if(s2 == null || s2.isEmpty()) {
               return s1;
           }
            // recursive
           if(s1.charAt(0) < s2.charAt(0)) {
               return s1.charAt(0) + merge(s1.substring(1),s2);
           } else {
               return s2.charAt(0) + merge(s1, s2.substring(1));
           }
        }

        public static void main(String[] args) {
            System.out.println(new MergeSortedStrings().merge("ace", "bdf"));
        }
    }
    static class VowelCounterFun {
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        public Integer count(String str, int index) {
            // base case
           if(str==null || str.isEmpty()) {
               return 0;
           }
           if(index == str.length()) {
               return 0;
           }
            // recursive
            if(vowelSet.contains(str.charAt(index))) {
               return 1 + count(str, index+1);
           }
           return count(str,index+1);
        }

        public static void main(String[] args) {
            System.out.println(new VowelCounterFun().count("HELLo World",0));
            System.out.println(new VowelCounterFun().count("AEIOUaeiou",0));
            System.out.println(new VowelCounterFun().count("STR",0));
        }
    }
    /*Permutations of a String*/
    static class PermutationOfString {
        public void permutations(char[] str, int len) {
            if(len==1) {
                System.out.println(str);
                return;
            }
            for(int idx=0; idx<len; idx++) {
                swap(str, idx, len -1);
                permutations(str, len-1);
                swap(str, idx, len -1);

            }

        }
        public void swap(char[] arr, int a, int b) {
            char tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        public static void main(String[] args) {
            PermutationOfString permutationOfString = new PermutationOfString();
            char[] input = {'a', 'b', 'b', 'a'};
            permutationOfString.permutations(input, input.length);
        }
    }
    /*Palindrome string*/
    static class Palindrome{
        public boolean isPalindrome(String str, int start, int end) {
            //base case
            if(start == end) {
                return true;
            }
            if(str.charAt(start) == str.charAt(end)) {
                return isPalindrome(str,start+1, end -1);
            }
            return false;
        }

        public static void main(String[] args) {
            Palindrome palindrome = new Palindrome();
            String text = "dad";
            System.out.println(palindrome.isPalindrome(text, 0, text.length()-1));
            text = "none";
            System.out.println(palindrome.isPalindrome(text, 0, text.length()-1));
            text = "MADAM";
            System.out.println(palindrome.isPalindrome(text, 0, text.length()-1));
        }

    }

}
