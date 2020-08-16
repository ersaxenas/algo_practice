package com.lrn.educative.gci.slidingwindow;

import java.util.*;

public class WorkSWUtils {
    /*Given an array, find the average of all subarrays of size ‘K’ in it.*/
    // brute-force algorithm
    public static double[] findAverages(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1]; // number of sub arrays
        for (int idx1 = 0; idx1 <= arr.length - K; idx1++) {
            double sum = 0;
            for (int idx2 = idx1; idx2 < idx1 + K; idx2++) {
                sum = sum + arr[idx2];
            }
            result[idx1] = sum / K;
        }
        return result;
    }

    // sliding window approach
    public static double[] findAverages2(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        int windowStart = 0;
        double sum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            sum = sum + arr[windowEnd]; // add next element to sum
            if (windowEnd >= (K - 1)) { // so we have completed window of K elements so now window is of size K elements and now we can start calculating averages and slide the window by subtracting start element.
                /*calculate average*/
                result[windowStart] = sum / K;
                /*subtract start element*/
                sum = sum - arr[windowStart];
                /*move windowstart to next element hence slide the window*/
                windowStart++;
            }
        }
        return result;
    }
//    public static void main(String[] args) {
//        double[] result = WorkSWUtils.findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
//        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
//        result = WorkSWUtils.findAverages2(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
//        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
//    }

    /* Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any contiguous subarray of size ‘k’. */
    public static int findSum1(int K, int[] arr) {
        int maxSum = 0;
        for (int i = 0; i <= arr.length - K; i++) {
            int windowSum = 0;
            for (int j = i; j < i + K; j++) {
                windowSum = windowSum + arr[j];
            }
            maxSum = Math.max(windowSum, maxSum);
        }
        return maxSum;
    }

    public static int findSum2(int K, int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum = windowSum + arr[windowEnd];
            if (windowEnd >= (K - 1)) { /*window established*/
                maxSum = Math.max(windowSum, maxSum);
                windowSum = windowSum - arr[windowStart]; // remove element since window is about to slide
                windowStart++; // select next first element of array
            }
        }
        return maxSum;
    }

//    public static void main(String[] args) {
//        System.out.println("Maximum sum of a subarray of size K: " + findSum2(3, new int[] { 2, 1, 5, 1, 3, 2 }));
//        System.out.println("Maximum sum of a subarray of size K: " + findSum2(2, new int[] { 2, 3, 4, 1, 5 }));
//    }

    /* Given an array of positive numbers and a positive number ‘S’, find the length of the smallest subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists. */
    public static int findSmallestSubArray(int S, int[] arr) {
        int smallestArraySize = Integer.MAX_VALUE;
        int sum = 0, windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            sum = sum + arr[windowEnd]; // add next element
            while (sum >= S) {
                smallestArraySize = Math.min(smallestArraySize, (windowEnd - windowStart) + 1);
                sum = sum - arr[windowStart];
                windowStart++;
            }
        }
        smallestArraySize = (smallestArraySize == Integer.MAX_VALUE) ? 0 : smallestArraySize;
        System.out.println("smallestArraySize = " + smallestArraySize);
        return smallestArraySize;
    }

//    public static void main(String[] args) {
//         findSmallestSubArray(7, new int[] { 2, 1, 5, 2, 3, 2 });
//         findSmallestSubArray(7, new int[] { 2, 1, 5, 2, 8 });
//         findSmallestSubArray(8, new int[] { 3, 4, 1, 1, 6 });
//    }

    /*Given a string, find the length of the longest substring in it with no more than K distinct characters.*/

    public static int findLongestSubstring(String text, int K) {
        if (text == null || text.length() == 0 || text.length() < K) {
            throw new IllegalArgumentException();
        }
        int textLength = text.length();
        Set<Character> charSet = new HashSet<>();
        int windowStart = 0, longestSubStringSize = 0;
        for (int windowEnd = 0; windowEnd < textLength - 1; windowEnd++) {
            charSet.add(text.charAt(windowEnd)); /*add element and move window end*/
            if (charSet.size() > K) {
                /*shrink window only if distinct chars are more then K*/
                while (charSet.size() > K) {
                    charSet.remove(text.charAt(windowStart));
                    windowStart++;
                }
            } else {
                longestSubStringSize = Math.max(longestSubStringSize, (windowEnd - windowStart) + 1);
            }
        }
        System.out.println("longestSubStringSize = " + longestSubStringSize);
        return longestSubStringSize;
    }

    public static int findLongestSubstring2(String text, int K) {
        if (text == null || text.length() == 0 || text.length() < K) {
            throw new IllegalArgumentException();
        }
        int textLength = text.length();
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int windowStart = 0, longestSubStringSize = 0;
        for (int windowEnd = 0; windowEnd < textLength - 1; windowEnd++) {
            final char charAtWindowEnd = text.charAt(windowEnd);
            frequencyMap.put(charAtWindowEnd, frequencyMap.getOrDefault(charAtWindowEnd, 0) + 1); /*add element and move window end*/
            while (frequencyMap.size() > K) { // shrink window if necessary till frequency is not more then K elements
                char charAtWindowStart = text.charAt(windowStart);
                Integer frequencyOfCharAtWindowStart = frequencyMap.getOrDefault(charAtWindowStart, 0);
                frequencyOfCharAtWindowStart--;
                if (frequencyOfCharAtWindowStart <= 0) {
                    frequencyMap.remove(charAtWindowStart);
                } else {
                    frequencyMap.put(charAtWindowStart, frequencyOfCharAtWindowStart);
                }
                windowStart++;
            }
            // keep the max length of the window
            longestSubStringSize = Math.max(longestSubStringSize, (windowEnd - windowStart) + 1);
        }
        System.out.println("longestSubStringSize = " + longestSubStringSize);
        return longestSubStringSize;
    }

//    public static void main(String[] args) {
//        findLongestSubstring2("araaci", 2);
//        findLongestSubstring2("araaci", 1);
//        findLongestSubstring2("araaci", 3);
//    }

    /*Given an array of characters where each character represents a fruit tree,
    you are given two baskets and your goal is to put maximum number of fruits in each basket.
    The only restriction is that each basket can have only one type of fruit.*/
    public static void maximumFruitCountOfKTypes(int types, char[] fruitArray) {
        if (fruitArray == null || fruitArray.length <= 0 || types == 0) {
            throw new IllegalArgumentException();
        }
        int windowStart = 0, maxFruitsCount = 0;
        Set<Character> fruitSet = new HashSet<>();
        for (int windowEnd = 0; windowEnd <= fruitArray.length - 1; windowEnd++) {
            fruitSet.add(fruitArray[windowEnd]);
            while (fruitSet.size() > types) {
                fruitSet.remove(fruitArray[windowStart]);
                windowStart++;
            }
            maxFruitsCount = Math.max(maxFruitsCount, (windowEnd - windowStart) + 1);
        }
        System.out.println("maxFruitsCount = " + maxFruitsCount);
    }

    //    public static void main(String[] args) {
//        maximumFruitCountOfKTypes(2,new char[]{'A', 'B', 'C', 'A', 'C'});
//        maximumFruitCountOfKTypes(2,new char[]{'A', 'B', 'C', 'B', 'B', 'C'});
//    }
    /*Given a string, find the length of the longest substring which has no repeating characters.*/
    private static void findLongestSubStringOfDistinctCharacters1(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        int windowStart = 0, maxWindow = 0;
        int textLength = text.length();
        Map<Character, Integer> charIndexMap = new HashMap<>(); /*remembering the index is the key here*/
        for (int windowEnd = 0; windowEnd < textLength; windowEnd++) {
            char currentChar = text.charAt(windowEnd);
            if (charIndexMap.containsKey(currentChar)) { /*duplicate char shrink window*/
                /*now move the window to the index of duplicate char*/
                while (text.charAt(windowStart) != currentChar) {
                    charIndexMap.remove(text.charAt(windowStart));
                    windowStart++;
                }
                charIndexMap.remove(currentChar);
                windowStart++;
            }
            charIndexMap.put(currentChar, windowEnd);
            maxWindow = Math.max(maxWindow, (windowEnd - windowStart) + 1);
        }
        System.out.println("maxWindow = " + maxWindow);
    }

    private static void findLongestSubStringOfDistinctCharacters2(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        int windowStart = 0, maxWindow = 0;
        int textLength = text.length();
        Map<Character, Integer> charIndexMap = new HashMap<>(); /*remembering the index is the key here*/
        for (int windowEnd = 0; windowEnd < textLength; windowEnd++) {
            char currentChar = text.charAt(windowEnd);
            if (charIndexMap.containsKey(currentChar)) { /*duplicate char shrink window*/
                /*now move the window to the index of duplicate char*/
                /*here trick is to move window start to the last seen MAX location of the char
                 * only if current window start is less then last seen MAX location*/
                windowStart = Math.max(windowStart, charIndexMap.get(currentChar) + 1);
            }
            charIndexMap.put(currentChar, windowEnd);
            maxWindow = Math.max(maxWindow, (windowEnd - windowStart) + 1);
        }
        System.out.println("maxWindow = " + maxWindow);
    }

//    public static void main(String[] args) {
//        findLongestSubStringOfDistinctCharacters2("aabccbb");
//        findLongestSubStringOfDistinctCharacters2("abbbb");
//        findLongestSubStringOfDistinctCharacters2("abccde");
//    }

    /*
    Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
    find the length of the longest substring having the same letters after replacement.
    */
    static class LongestSubStringAfterReplacement {
        public static void findLongestSubStringWithCharacterReplacement(String text, int K) {
            if (text == null || text.trim().length() == 0) {
                System.out.println("Please supply text.");
                return;
            }
            int windowStart = 0, maxRepeatedLetter = 0, maxWindowLength = 0;
            Map<Character, Integer> characterFrequencyMap = new HashMap<>();

            for (int windwoEnd = 0; windwoEnd < text.length(); windwoEnd++) { // sliding window
                char currentChar = text.charAt(windwoEnd);
                characterFrequencyMap.put(currentChar, characterFrequencyMap.getOrDefault(currentChar, 0) + 1); /*Add letter to map and increase frequency of the letter by one.*/
                /*now the the current frequency of the letter*/
                int currentCharFrequency = characterFrequencyMap.get(currentChar);
                /*set max frequency*/
                maxRepeatedLetter = Math.max(currentCharFrequency, maxRepeatedLetter);
                /*find current window length*/
                int windowLength = (windwoEnd - windowStart) + 1;
                /*if current window length - maxRepeatedLetter > K. so assume there are N char are in the window and out of which X are being repeated (maxRepeatedLetter)
                  so only N - X letter can change.
                 * So if N - X <= chars allowed to change then it will make longest substring after replacement.
                 * For ex: if there are 5 letters in the window (ababa)  and one char is repeating 3 times (a) so N - X = 5 - 3 = 2
                 so we can change only 2 chars. If K ==2 then yes it will make longest substring.*/
                if (windowLength - maxRepeatedLetter > K) {
                    /*shrink the window by taking out char from window start*/
                    char chatAtTheStartOfTheWindow = text.charAt(windowStart);
                    /*decrease the current frequency*/
                    characterFrequencyMap.put(chatAtTheStartOfTheWindow, characterFrequencyMap.get(chatAtTheStartOfTheWindow) - 1);
                    /*shrink the window by incrementing the window start*/
                    windowStart++;
                }
                /*save max length of the window*/
                maxWindowLength = Math.max(maxWindowLength, (windwoEnd - windowStart) + 1);
            }
            System.out.println("maxWindowLength = " + maxWindowLength);
        }

        public static void main(String[] args) {
            findLongestSubStringWithCharacterReplacement("aabccbb", 2);
            findLongestSubStringWithCharacterReplacement("abbcb", 1);
            findLongestSubStringWithCharacterReplacement("abccde", 1);
        }
    }


    /*Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of the longest subarray having all 1s.*/
    public static void findLongestSubArrayByReplacingOnes(int[] arr, int K) {
        int windowStart = 0, maxSubArrayLength = 0, countOfZerosInWindow = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            int currentElement = arr[windowEnd];
            if (currentElement == 0) {
                countOfZerosInWindow++;
            }
            while (countOfZerosInWindow > K) { /*only allowed to replace K zeros shrink window*/
                if (arr[windowStart] == 0) { /*count will decrement only if array element is a zero*/
                    countOfZerosInWindow--;
                }
                windowStart++;
            }
            maxSubArrayLength = Math.max(maxSubArrayLength, (windowEnd - windowStart) + 1);
        }
        System.out.println("maxSubArrayLength = " + maxSubArrayLength);
    }

    public static void findLongestSubArrayByReplacingOnes2(int[] arr, int K) {
        if (arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("Array of zero length or null");
        }
        /*here we will count ones*/
        int windowStart = 0, countOfOnes = 0, maxWindowLength = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            int currentElement = arr[windowEnd];
            if (currentElement == 1) {
                countOfOnes++;
            }

            if (((windowEnd - windowStart) + 1) - countOfOnes > K) { /*window length - no. of one = no. of zeros in the window. So if no. of zeros are greater then K then remove element from window*/
                /*here we are not decreasing the size fo the window. Once we have reached a size of window where maxlength is achieved then we keep that window size.*/
                if (arr[windowStart] == 1) { /*if element at window start is 1 then decrement the count*/
                    countOfOnes--;
                }
                windowStart++; /*move window start to next element. here were moving window by +1 and removing and element from the window. And next element will take in the next element from window end.
                 so window is sliding start -> +1 , end -> +1*/
            }
            maxWindowLength = Math.max(maxWindowLength, (windowEnd - windowStart) + 1);
        }
        System.out.println("maxWindowLength = " + maxWindowLength);
    }

//    public static void main(String[] args) {
//        findLongestSubArrayByReplacingOnes2(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2);
//        findLongestSubArrayByReplacingOnes2(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3);
//    }

    /*problem1: Given a string and a pattern, find out if the string contains any permutation of the pattern.*/

    public static boolean findStringsContainPermutation(String text, String pattern) {
        /*this will fail in duplicate char pattern. for example aac and acc will both work for string "dxacc".
         * we need to count the frequency as well.*/
        if (text == null || text.trim().length() < 0) {
            return false;
        }
        if (pattern == null || pattern.trim().length() < 0) {
            return false;
        }
        int windowStart = 0, charsInPattern = 0, patternLength = pattern.length();
        boolean result = false;
        Set<Character> pattternCharacterSet = new HashSet<>();
        for (char ch : pattern.toCharArray()) {
            pattternCharacterSet.add(ch);
        }
        Set<Character> charsInWindow = new HashSet<>();
        for (int windowEnd = 0; windowEnd < text.length(); windowEnd++) {
            char currentChar = text.charAt(windowEnd);
            if (pattternCharacterSet.contains(currentChar)) {
                charsInWindow.add(currentChar);
            } else {
                charsInWindow.clear();
                windowStart = windowEnd;
            }
            if (((windowEnd - windowStart + 1) >= patternLength) && pattternCharacterSet.size() == charsInWindow.size()) {
                result = true;
            }
        }
        System.out.println(result);
        return result;
    }

    public static boolean findStringsContainPermutation2(String text, String pattern) {
        if (text == null || text.trim().length() < 0) {
            return false;
        }
        if (pattern == null || pattern.trim().length() < 0) {
            return false;
        }
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

    //    public static void main(String[] args) {
//        findStringsContainPermutation2("oidbcaf", "abc");
//        findStringsContainPermutation2("odicf", "dc");
//        findStringsContainPermutation2("bcdxabcdy", "bcdyabcdx");
//        findStringsContainPermutation2("aaacb", "abc");
//
//    }
    /*Given a string and a pattern, find all anagrams of the pattern in the given string.
    * Anagram is actually a Permutation of a string. For example, “abc” has the following six anagrams:
        abc
        acb
        bac
        bca
        cab
        cba
        * */
    public static List<Integer> findAnagramsInString(String str, String pattern) {
        if (str == null || pattern == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> lstAnagrams = new ArrayList<>();
        int windowStart = 0;
        Map<Character, Integer> patternCharFrequencyMap = new HashMap<>();
        Map<Character, Boolean> currentWindowCharMatchStatusMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            patternCharFrequencyMap.put(ch, patternCharFrequencyMap.getOrDefault(ch, 0) + 1);
            currentWindowCharMatchStatusMap.put(ch, false);
        }
        Map<Character, Integer> currentWindowFrequencyMap = new HashMap<>();
        /*sliding window*/
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char currentChar = str.charAt(windowEnd);
            currentWindowFrequencyMap.put(currentChar, currentWindowFrequencyMap.getOrDefault(currentChar, 0) + 1);
            if (patternCharFrequencyMap.containsKey(currentChar)) {
                if (currentWindowFrequencyMap.get(currentChar).equals(patternCharFrequencyMap.get(currentChar))) {
                    currentWindowCharMatchStatusMap.put(currentChar, true);
                } else {
                    currentWindowCharMatchStatusMap.put(currentChar, false);
                }
            }

            /*check for anagram*/
            if (currentWindowFrequencyMap.size() == patternCharFrequencyMap.size() && !currentWindowCharMatchStatusMap.containsValue(false)) {
                System.out.println(String.format("Start: %d, End: %d, %s", windowStart, windowEnd, str.substring(windowStart, windowEnd + 1)));
                lstAnagrams.add(windowStart);
            }

            if (windowEnd >= pattern.length() - 1) { /*current window size is equal to pattern*/
                /*remove a char from start fo the window*/
                final char charAtStart = str.charAt(windowStart);
                if (currentWindowFrequencyMap.containsKey(charAtStart)) {
                    final Integer frequency = currentWindowFrequencyMap.get(charAtStart);
                    if ((frequency - 1) <= 0) {
                        currentWindowFrequencyMap.remove(charAtStart);
                    } else {
                        currentWindowFrequencyMap.put(charAtStart, frequency - 1);
                        if (patternCharFrequencyMap.containsKey(charAtStart)) {
                            if (patternCharFrequencyMap.get(charAtStart).equals(frequency - 1)) {
                                currentWindowCharMatchStatusMap.put(charAtStart, true);
                            } else {
                                currentWindowCharMatchStatusMap.put(charAtStart, false);
                            }
                        }
                    }

                }
                windowStart++;
            }
        }
        return lstAnagrams;
    }

    public static List<Integer> findAnagramsInString2(String str, String pattern) {
        if (str == null || pattern == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> lstAnagrams = new ArrayList<>();
        int windowStart = 0, matchedCharCount = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char currentChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(currentChar)) {
                charFrequencyMap.put(currentChar, charFrequencyMap.get(currentChar) - 1);
                if (charFrequencyMap.get(currentChar) == 0) {
                    matchedCharCount++;
                }
            }
            if (matchedCharCount == charFrequencyMap.size()) {
                System.out.println(String.format("Start: %d, End: %d, %s", windowStart, windowEnd, str.substring(windowStart, windowEnd + 1)));
                lstAnagrams.add(windowStart);
            }
            if (windowEnd >= pattern.length() - 1) {
                char charAtStart = str.charAt(windowStart);
                if (charFrequencyMap.containsKey(charAtStart)) {
                    int freq = charFrequencyMap.get(charAtStart);
                    if (freq == 0) {
                        matchedCharCount--;
                    }
                    charFrequencyMap.put(charAtStart, freq + 1);
                }
                windowStart++;
            }

        }
        return lstAnagrams;
    }

//    public static void main(String[] args) {
//        findAnagramsInString2("ppqp", "pq");
//        findAnagramsInString2("abbcabc", "abc");
//    }

    /*Given a string and a pattern, find the smallest substring in the given string which has all the characters of the given pattern*/
    public static String findSmallestSubStringWithAllCharsOfPattern(String text, String pattern) {
        if (pattern == null || text == null) {
            throw new IllegalArgumentException();
        }
        String smallestSubString = "";
        Map<Character, Integer> charFreqMap = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            charFreqMap.put(ch, 0);
        }

        int windowStart = 0, charsMatched = 0, minLenghtMatch = Integer.MAX_VALUE;
        for (int windowEnd = 0; windowEnd < text.length(); windowEnd++) {
            char currentChar = text.charAt(windowEnd);
            if (charFreqMap.containsKey(currentChar)) {
                final Integer currentCharFreq = charFreqMap.get(currentChar);
                charFreqMap.put(currentChar, currentCharFreq + 1);
                if (currentCharFreq == 0) {
                    charsMatched++;
                }
            }

            while (charsMatched >= charFreqMap.size()) {
                final int currentWindowLen = (windowEnd - windowStart) + 1;
                if(currentWindowLen < minLenghtMatch) {
                    minLenghtMatch = currentWindowLen;
                    smallestSubString = text.substring(windowStart, windowEnd + 1);
                }

                char charAtWindowStart = text.charAt(windowStart);
                if (charFreqMap.containsKey(charAtWindowStart)) {
                    int charFreq = charFreqMap.get(charAtWindowStart);
                    charFreqMap.put(charAtWindowStart, charFreq - 1);
                    if ((charFreq - 1) == 0) {
                        charsMatched--;
                    }
                }
                windowStart++;
            }
        }
        System.out.println("smallestSubString = " + smallestSubString);
        return smallestSubString;
    }

    public static void main(String[] args) {
        findSmallestSubStringWithAllCharsOfPattern("aabdec","abc");
        findSmallestSubStringWithAllCharsOfPattern("abdabca","abc");
        findSmallestSubStringWithAllCharsOfPattern("adcad","abc");
    }

    /*Given a string and a list of words,
    find all the starting indices of substrings in the given string that are a concatenation of all the given words exactly once without any overlapping of words.
    It is given that all words are of the same length.*/
    public static List<Integer> findSubstringWordConcatenation(String str, String[] words) {
        assert str != null;
        assert words != null;
        List<String> wordList = new ArrayList<>();
        List<Integer> wordIndex = new ArrayList<>();
        int noOfWords = words.length, wordLength = words[0].length();
        Map<String,Integer> wordFrequencyMap = new HashMap<>();
        for(String word: words) {
            wordFrequencyMap.put(word,wordFrequencyMap.getOrDefault(word,0)+1);
        }

        for(int windowStart=0; windowStart <= (str.length() - (wordLength * noOfWords)); windowStart++) {
            HashMap<String, Integer> wordsSeen = new HashMap<>();
            /*now look for words*/
            for(int wrd=0; wrd<noOfWords; wrd++) {
                int wordStartIndex = windowStart + (wrd * wordLength);
                String word = str.substring(wordStartIndex, wordStartIndex+wordLength);
                if(wordFrequencyMap.containsKey(word)) {
                    wordsSeen.put(word, wordsSeen.getOrDefault(word,0)+1);
                } else {
                    break;// break for loop
                }
                if(wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word,0)) {
                    break;// frequency is more then required
                }
                if(wordsSeen.size() == wordFrequencyMap.size()) {
                    wordList.add(str.substring(windowStart, wordStartIndex+wordLength));
                    wordIndex.add(windowStart);
                }
            }
        }
        return wordIndex;
    }

//    public static void main(String[] args) {
//        List<Integer> result = findSubstringWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
//        System.out.println(result);
//        result = findSubstringWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" });
//        System.out.println(result);
//    }


}




