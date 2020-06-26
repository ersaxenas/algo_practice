package com.lrn.educative.gci.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingWindowTech {
    /*sliding window approach*/

    /*fixed size window*/
    public static double[] fixedSizeSlidingWindow(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        int ws = 0; // window start
        double sum = 0;
        for (int we = 0; we < arr.length; we++) { // wd = window ene
            sum = sum + arr[we]; // add next element to sum or take element in the window
            if (we >= (K - 1)) { //or (we-ws+1) == K so we have completed window of K elements so now window is of size K elements and now we can start calculating averages and slide the window by subtracting start element.
                /*calculate average*/
                result[ws] = sum / K;
                /*subtract start element = remove element from window step 1*/
                sum = sum - arr[ws];
                /*move windowstart to next element hence slide the window = remove element from window step 2*/
                ws++;
            }
        }
        return result;
    }

    /*shrinking and expanding sliding window */
    public static int dynamicSlidingWindow(int S, int[] arr) {
        int smallestArraySize = Integer.MAX_VALUE;
        int ws = 0; // window start
        int sum = 0;
        for (int we = 0; we < arr.length; we++) { /* we = window end */
            sum = sum + arr[we]; /*add next element to sum or take element in the window*/
            while (sum >= S) { /* loop will shrink window and current window size is (we-ws+1) */
                smallestArraySize = Math.min(smallestArraySize, (we - ws) + 1);
                sum = sum - arr[ws];/*subtract start element = remove element from window step 1*/
                ws++;/*move windowstart to next element hence slide the window = remove element from window step 2*/
            }
        }
        smallestArraySize = (smallestArraySize == Integer.MAX_VALUE) ? 0 : smallestArraySize;
        System.out.println("smallestArraySize = " + smallestArraySize);
        return smallestArraySize;
    }

    public static int dynamicSlidingWindow2(String text, int K) {

        int textLength = text.length();
        Map<Character, Integer> frequencyMap = new HashMap<>(); // frequency map - window size will be guided by this map
        int ws = 0; // window start
        int longestSubStringSize = 0;
        for (int we = 0; we < textLength - 1; we++) {
            final char charAtWindowEnd = text.charAt(we);
            /*window size increases when we add element to map*/
            frequencyMap.put(charAtWindowEnd, frequencyMap.getOrDefault(charAtWindowEnd, 0) + 1); /*add element and move window end*/
            while (frequencyMap.size() > K) { // loop will then shrink window if necessary till frequency is not more then K elements. window size == map.size
                char charAtWindowStart = text.charAt(ws);
                Integer frequencyOfCharAtWindowStart = frequencyMap.getOrDefault(charAtWindowStart, 0);
                frequencyOfCharAtWindowStart--;
                if (frequencyOfCharAtWindowStart <= 0) {
                    frequencyMap.remove(charAtWindowStart); // remove element from window step 1
                } else {
                    frequencyMap.put(charAtWindowStart, frequencyOfCharAtWindowStart); // remove element from window step 1
                }
                ws++;/*move windowstart to next element hence slide the window = remove element from window step 2*/
            }
            // keep the max length of the window
            longestSubStringSize = Math.max(longestSubStringSize, (we - ws) + 1);
        }
        System.out.println("longestSubStringSize = " + longestSubStringSize);
        return longestSubStringSize;
    }


    private static void findLongestSubStringOfDistinctCharacters(String text) {
        int ws = 0, maxWindow = 0;
        int textLength = text.length();
        Map<Character, Integer> charIndexMap = new HashMap<>(); /*remembering the index is the key here*/
        for (int we = 0; we < textLength; we++) {
            char currentChar = text.charAt(we);
            if (charIndexMap.containsKey(currentChar)) { /*duplicate char shrink window*/
                /*now move the window to the index of duplicate char*/
                /*here trick is to move window start to the last seen MAX location of the char
                 * only if current window start is less then last seen MAX location
                 * */
                ws = Math.max(ws, charIndexMap.get(currentChar) + 1); //pay attention - last seen index + 1 = windowstart. here window size may shrinks by more then 1
            }
            charIndexMap.put(currentChar, we);// new index of char.
            maxWindow = Math.max(maxWindow, (we - ws) + 1);
        }
        System.out.println("maxWindow = " + maxWindow);
    }

    /*
    Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
    find the length of the longest substring having the same letters after replacement.
    */
    static class LongestSubStringAfterReplacement {
        public static void findLongestSubStringWithCharacterReplacement(String text, int K) {
            int ws = 0, maxRepeatedLetter = 0, maxWindowLength = 0;
            Map<Character, Integer> characterFrequencyMap = new HashMap<>();

            for (int we = 0; we < text.length(); we++) { // sliding window
                char currentChar = text.charAt(we);
                characterFrequencyMap.put(currentChar, characterFrequencyMap.getOrDefault(currentChar, 0) + 1); /*Add letter to map and increase frequency of the letter by one.*/
                /*now the the current frequency of the letter*/
                int currentCharFrequency = characterFrequencyMap.get(currentChar);
                /*set max frequency ## window size is governed by maxRepeatedLetter##  */
                maxRepeatedLetter = Math.max(currentCharFrequency, maxRepeatedLetter);
                /*find current window length*/
                int windowLength = (we - ws) + 1;
                /*if current window length - maxRepeatedLetter > K. so assume there are N char are in the window and out of which X are being repeated (maxRepeatedLetter)
                  so only N - X letter can change.
                 * So if N - X <= chars allowed to change then it will make longest substring after replacement.
                 * For ex: if there are 5 letters in the window (ababa)  and one char is repeating 3 times (a) so N - X = 5 - 3 = 2
                 so we can change only 2 chars. If K ==2 then yes it will make longest substring.*/
                if (windowLength - maxRepeatedLetter > K) {
                    /*shrink the window by taking out char from window start*/
                    char chatAtTheStartOfTheWindow = text.charAt(ws);
                    /*decrease the current frequency*/
                    characterFrequencyMap.put(chatAtTheStartOfTheWindow, characterFrequencyMap.get(chatAtTheStartOfTheWindow) - 1);
                    /*shrink the window by incrementing the window start*/
                    ws++;
                }
                /*save max length of the window*/
                maxWindowLength = Math.max(maxWindowLength, (we - ws) + 1);
            }
            System.out.println("maxWindowLength = " + maxWindowLength);
        }
    }

    /*problem1: Given a string and a pattern, find out if the string contains any permutation of the pattern.*/
    public static boolean findStringsContainPermutation2(String text, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            characterFrequencyMap.put(ch, characterFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        /*now sliding window algo*/
        for (int windowEnd = 0; windowEnd < text.length(); windowEnd++) {
            char currentChar = text.charAt(windowEnd);
            if (characterFrequencyMap.containsKey(currentChar)) {
                characterFrequencyMap.put(currentChar, characterFrequencyMap.get(currentChar) - 1);
                if (characterFrequencyMap.get(currentChar) == 0) {
                    matched++;
                }
            }

            if (matched == characterFrequencyMap.size()) {
                System.out.println(true);
                return true;
            }

            if (windowEnd >= pattern.length() - 1) { // we created a window of size (windowStart - windowEnd) = windowEnd (since windowStart = 0) = size of the frequency map
                /*now we have window with no. of chars equal to no. of chars in pattern. so we will just add an element to the window end and remove from start*/
                /*remove char from window*/
                final char charAtStart = text.charAt(windowStart);
                if (characterFrequencyMap.containsKey(charAtStart)) {
                    final Integer charFreq = characterFrequencyMap.get(charAtStart);
                    if (charFreq == 0) {
                        matched--; // since in the window charAtStart is no longer matching any char.
                    }
                    characterFrequencyMap.put(charAtStart, charFreq + 1); // now restore the original frequency by incrementing it
                }
                windowStart++;
            }
        }
        System.out.println(false);
        return false;
    }

     /*Given a string and a pattern, find all anagrams of the pattern in the given string.
    * Anagram is actually a Permutation of a string. For example, “abc” has the following six anagrams:
        abc
        acb
        bac
        bca
        cab
        cba
        * */

    public static List<Integer> findAnagramsInString2(String str, String pattern) {
        List<Integer> lstAnagrams = new ArrayList<>();
        int ws = 0, matchedCharCount = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        for (int we = 0; we < str.length(); we++) {
            char currentChar = str.charAt(we);
            if (charFrequencyMap.containsKey(currentChar)) {
                charFrequencyMap.put(currentChar, charFrequencyMap.get(currentChar) - 1);
                if (charFrequencyMap.get(currentChar) == 0) {
                    matchedCharCount++;
                }
            }
            if (matchedCharCount == charFrequencyMap.size()) {
                System.out.println(String.format("Start: %d, End: %d, %s", ws, we, str.substring(ws, we + 1)));
                lstAnagrams.add(ws);
            }
            if (we >= pattern.length() - 1) {
                char charAtStart = str.charAt(ws);
                if (charFrequencyMap.containsKey(charAtStart)) {
                    int freq = charFrequencyMap.get(charAtStart);
                    if (freq == 0) {
                        matchedCharCount--;
                    }
                    charFrequencyMap.put(charAtStart, freq + 1);
                }
                ws++;
            }

        }
        return lstAnagrams;
    }
}
