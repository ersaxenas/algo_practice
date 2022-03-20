package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Q017LetterCombinationsOfPhNo {
    /*
    * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    * pd: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
    * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    * Assmp: String - no white space and no. from 0 to 9, best time solution
    * Approach:
    *  Take a queue and add blank list in the queue.
    *  take a num char -> 2 -> a b c
    *  take queue size and deque elems - loop
    *  clone dequeued elem and add char
    *  put back on the queue.
    * Ex: q:{ [] }
    *   2->  a : [a] , b: [b] c: [c]
    *     q: {[a][b][c]}
    *   3-> d: [ad] [bd] [cd] e: [ae] [be] [ce] ...
    * */

    public List<String> letterCombination(String digits) {
        if(digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        HashMap<Character, List<Character>> intMap = new HashMap<>();
        intMap.put('2', Arrays.asList('a','b','c'));
        intMap.put('3', Arrays.asList('d','e','f'));
        intMap.put('4', Arrays.asList('g','h','i'));
        intMap.put('5', Arrays.asList('j','k','l'));
        intMap.put('6', Arrays.asList('m','n','o'));
        intMap.put('7', Arrays.asList('p','q','r','s'));
        intMap.put('8', Arrays.asList('t','u','v'));
        intMap.put('9', Arrays.asList('w','x','y','z'));
        Queue<StringBuilder> queue = new LinkedList<>();
        queue.add(new StringBuilder());
        for(char dig: digits.toCharArray()) {// O(N)
            if(intMap.containsKey(dig)) { // rem: convert char to int or keep map of chars
                int queueSize = queue.size();
                for(int qidx=0; qidx<queueSize; qidx++) {
                    StringBuilder deqe = queue.poll();
                    for(char alp: intMap.get(dig)) {
                        StringBuilder sbr = new StringBuilder(deqe);
                        sbr.append(alp);
                        queue.add(sbr);
                    }
                }
            }
        }
        return queue.stream().map(elem -> elem.toString()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Q17LetterCombinationsOfPhNo q17LetterCombinationsOfPhNo = new Q17LetterCombinationsOfPhNo();
        List<String> strings = q17LetterCombinationsOfPhNo.letterCombination("");
        strings.forEach(System.out::println);

    }

}
