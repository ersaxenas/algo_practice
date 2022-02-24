package com.lrn.educative.crust;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrWorkUtils {
    /*Reverse Words in a Sentence*/
    static class ReverseSentenceWords {
        public void reverse(char[] arr, int start, int end) {
            if (arr == null || arr.length == 0) {
                return;
            }
            while (start < end) {
                char ch = arr[start];
                arr[start] = arr[end];
                arr[end] = ch;
                start++;
                end--;
            }
        }

        public String reverseSentence(String str) {
            if (str == null || str.trim().length() == 0) {
                return str;
            }
            char[] charArr = str.toCharArray();
            reverse(charArr, 0, charArr.length - 1);
            int start = 0, end = 0;
            while (end < charArr.length) {
                while (start < charArr.length && charArr[start] == ' ') {
                    start++;
                }
                if (start >= charArr.length - 1) {
                    break;
                }
                end = start + 1;
                while (end < charArr.length && charArr[end] != ' ') {
                    end++;
                }
                if (start < end) {
                    reverse(charArr, start, end - 1);
                }
                start = end;
            }
            return new String(charArr);
        }

        public String reverseSentence2(String str) {
            if (str == null || str.trim().length() == 0) {
                return str;
            }
            char[] charArr = str.toCharArray();
            reverse(charArr, 0, charArr.length - 1);
            int start = 0;
            for (int end = 0; end < charArr.length; end++) {
                if (charArr[end] == ' ') {
                    while (start < end && charArr[start] == ' ') {
                        start++;
                    }
                    reverse(charArr, start, end - 1);
                    start = end;
                }
            }
            if (start < charArr.length) {
                while (start < charArr.length && charArr[start] == ' ') {
                    start++;
                }
                reverse(charArr, start, charArr.length - 1);
            }
            return new String(charArr);
        }


        public static void main(String[] args) {
            ReverseSentenceWords reverseSentenceWords = new ReverseSentenceWords();
            String str = "Hello World!";
            System.out.println(reverseSentenceWords.reverseSentence2(str));
        }
    }

    /*Remove Duplicates from a String*/
    static class StringDupRemoval {
        public String removeDuplicate(String source) {
            if (source == null || source.trim().length() <= 1) {
                return source;
            }
            HashSet<Character> characterHashSet = new HashSet<>();
            char[] chrArr = source.toCharArray();
            int wripteP = 0, readP = 0;
            while (readP < chrArr.length) {
                if (!characterHashSet.contains(chrArr[readP])) {
                    characterHashSet.add(chrArr[readP]);
                    swap(chrArr, wripteP, readP);
                    wripteP++;
                }
                readP++;
            }
            return new String(chrArr, 0, wripteP);
        }

        public void swap(char[] arr, int p1, int p2) {
            char tmp = arr[p1];
            arr[p1] = arr[p2];
            arr[p2] = tmp;
        }

        public static void main(String[] args) {
            StringDupRemoval stringDupRemoval = new StringDupRemoval();
            String input = "dabbac";
            System.out.print("Before: ");
            System.out.println(input);
            String result = stringDupRemoval.removeDuplicate(input);
            System.out.print("After: ");
            System.out.println(result);
        }
    }

    static class StringWhileSpaceRemoval {
        public String removeWs(String source) {
            if (source == null) {
                return source;
            }
            int readP = 0, writeP = 0;
            char[] chrArr = source.toCharArray();
            while (readP < chrArr.length) {
                if (chrArr[readP] != ' ' || chrArr[readP] != '\t') {
                    swap(chrArr, writeP, readP);
                    writeP++;
                }
                readP++;
            }
            return new String(chrArr, 0, writeP);
        }

        public void swap(char[] arr, int a, int b) {
            char tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        public static void main(String[] args) {
            StringWhileSpaceRemoval stringWhileSpaceRemoval = new StringWhileSpaceRemoval();
            String str = "All greek to me";
            System.out.print("Before: ");
            String result = stringWhileSpaceRemoval.removeWs(str);
            System.out.print("After: ");
            System.out.println(result);
        }
    }

    /*Word Break Problem*/
    static class WordBreak {
        /*solution using dp*/
        public Boolean canBreak(String str, Set<String> dict) {
            for (int idx = 0; idx < str.length(); idx++) {
                String first = str.substring(0, idx);
                if (dict.contains(first)) {
                    String second = str.substring(idx);
                    if (dict.contains(second) || canBreak(second, dict)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void main(String[] args) {
            WordBreak wordBreak = new WordBreak();
            Set<String> dictionary = new HashSet<String>();
            String s = "hellonow";

            dictionary.add("hello");
            dictionary.add("hell");
            dictionary.add("on");
            dictionary.add("now");
            if (wordBreak.canBreak(s, dictionary)) {
                System.out.println("String Can be Segmented");
            } else {
                System.out.println("String Can NOT be Segmented");
            }
        }
    }

    /*XML to Tree*/
    static class XmlTree {
        static class TreeNode {
            public int data;
            public String text;
            public ArrayList<TreeNode> Children = new ArrayList<TreeNode>();

            public TreeNode(int data) {
                this.data = data;
            }

            public TreeNode(String text) {
                this.text = text;
            }

            public static void display_level_order(TreeNode root) {
                if (root == null)
                    return;

                ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
                queue.addLast(root);
                TreeNode tempNode = new TreeNode(100);
                queue.addLast(tempNode);

                while (!queue.isEmpty()) {
                    TreeNode temp = queue.removeFirst();

                    if (temp == tempNode) {
                        System.out.println();
                        if (!queue.isEmpty()) {
                            queue.addLast(tempNode);
                            continue;
                        } else {
                            return;
                        }

                    }

                    System.out.print(temp.data + ", ");

                    for (int i = 0; i < temp.Children.size(); i++) {
                        queue.addLast(temp.Children.get(i));
                    }
                }
                System.out.println();
            }
        }

        public TreeNode createXmlTree(String xmlString) {
            if (xmlString == null || xmlString.trim().length() == 0) {
                return null;
            }
            TreeNode lastNode = null;
            try (
                    InputStream is = new ByteArrayInputStream(xmlString.getBytes());
            ) {
                XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(is);
                Stack<TreeNode> stack = new Stack<>();

                while (xmlStreamReader.hasNext()) {
                    // ignore few events
                    xmlStreamReader.next();
                    int eventType = xmlStreamReader.getEventType();
                    switch (eventType) {
                        case XMLStreamConstants.END_ELEMENT:
                            if (!stack.isEmpty()) {
                                lastNode = stack.pop();
                            }
                            break;
                        case XMLStreamConstants.START_ELEMENT:
                            TreeNode nodeElem = new TreeNode(xmlStreamReader.getLocalName());
                            if (!stack.isEmpty()) {
                                stack.peek().Children.add(nodeElem);
                            }
                            stack.push(nodeElem);
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            TreeNode nodeText = new TreeNode(xmlStreamReader.getText());
                            if (!stack.isEmpty()) {
                                stack.peek().Children.add(nodeText);
                            }
                            break;
                    }

                }

            } catch (IOException | XMLStreamException e) {
                e.printStackTrace();
            }
            return lastNode;
        }

        public static void print_tree(TreeNode root, int depth) {
            if (root == null) {
                return;
            }

            for (int i = 0; i < depth; ++i) System.out.print("\t");
            System.out.print(root.text + "\n");
            for (TreeNode child : root.Children) {
                print_tree(child, depth + 1);
            }
        }

        public static void main(String[] args) {
            XmlTree xmltree = new XmlTree();
            try {
                String xml = "<xml><data>hello world     </data>    <a><b></b><b><c></c></b></a></xml>";
                TreeNode result = xmltree.createXmlTree(xml);
                print_tree(result, 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /*Find all Palindrome Substrings*/
    static class AllPalindromicStrings {
        public boolean isPalindromeRecursive(String str, int start, int end) {
            //base
            if (start == end) {
                return true;
            }
            // recursive
            if (str.charAt(start) == str.charAt(end)) {
                return isPalindromeRecursive(str, start + 1, end - 1);
            }
            return false;
        }

        public boolean isPalindromeIterative(String str, int start, int end) {
            while (start < end) {
                if (str.charAt(start) != str.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }

        public Set<String> findAllPalindromeBruteForce(String str) {
            Set<String> palindromeSet = new HashSet<String>();
            if (str == null || str.trim().length() <= 1) {
                return palindromeSet;
            }
            for (int idx1 = 0; idx1 < str.length(); idx1++) {
                for (int idx2 = idx1 + 1; idx2 < str.length(); idx2++) {
                    if (isPalindromeIterative(str, idx1, idx2)) {
                        palindromeSet.add(str.substring(idx1, idx2 + 1));
                    }
                }
            }
            return palindromeSet;
        }

        public Set<String> findPalindromes(String str, int start, int end) {
            Set<String> stringSet = new HashSet<>();
            while (start > 0 && end < str.length()) {
                if (str.charAt(start) == str.charAt(end)) {
                    stringSet.add(str.substring(start, end + 1));
                } else {
                    break;
                }
                start--;
                end++;
            }
            return stringSet;
        }

        public Set<String> findAllPalindrome(String str) {
            Set<String> palindromeSet = new HashSet<>();
            if (str == null || str.trim().length() <= 1) {
                return palindromeSet;
            }
            for (int idx = 0; idx < str.length(); idx++) {
                palindromeSet.addAll(findPalindromes(str, idx - 1, idx + 1)); //case  e - i - e
                palindromeSet.addAll(findPalindromes(str, idx, idx + 1)); //case  i - e
            }
            return palindromeSet;
        }

        public static void main(String[] args) {
            AllPalindromicStrings allPalindromicStrings = new AllPalindromicStrings();
//            String palindromeTest = "abc";
//            System.out.println("is palindrome 'recursive' "+palindromeTest+" :"+ allPalindromicStrings.isPalindromeRecursive(palindromeTest, 0, palindromeTest.length()-1));
//            System.out.println("is palindrome 'iterative' "+palindromeTest+" :"+ allPalindromicStrings.isPalindromeIterative(palindromeTest, 0, palindromeTest.length()-1));
//            palindromeTest = "aba";
//            System.out.println("is palindroms 'recursive' "+palindromeTest+" :"+ allPalindromicStrings.isPalindromeRecursive(palindromeTest, 0, palindromeTest.length()-1));
//            System.out.println("is palindroms 'iterative' "+palindromeTest+" :"+ allPalindromicStrings.isPalindromeIterative(palindromeTest, 0, palindromeTest.length()-1));
            System.out.println("Brute force:");
            Set<String> palindromeSet = allPalindromicStrings.findAllPalindromeBruteForce("aabbaa");
            palindromeSet.forEach(System.out::println);
            System.out.println("Improved:");
            palindromeSet = allPalindromicStrings.findAllPalindrome("aabbaa");
            palindromeSet.forEach(System.out::println);
        }
    }

    /*Regular Expression Matching in String*/
    static class StringPatternMatcher {
        public boolean match(String text, String pattern) {
            return matchRecursive(text, pattern, 0, 0);
        }

        public boolean matchRecursive(String text, String pattern, int textIdx, int patternIdx) {
            //base case
            if (textIdx == text.length() && patternIdx == pattern.length()) {
                return true;
            }

            //recursive case
            if (patternIdx < pattern.length() - 1 && pattern.charAt(patternIdx + 1) == '*') { // case of *
                char pChar = pattern.charAt(patternIdx);
                if(pChar == '.') {
                    return true;
                }
                // 0 match - skip the pattern with *
                if (matchRecursive(text, pattern, textIdx, patternIdx + 2)) {
                    return true;
                }
                // 1 or more match
                while (textIdx < text.length() && pChar == text.charAt(textIdx)) {
                    textIdx++;
                }
                patternIdx = patternIdx + 2;
                if(patternIdx >= pattern.length()) {
                    return true;
                }
                return matchRecursive(text, pattern, textIdx, patternIdx);

            } else if ((textIdx < text.length() && patternIdx < pattern.length()) && (pattern.charAt(patternIdx) == '.' || text.charAt(textIdx) == pattern.charAt(patternIdx))) {
                return matchRecursive(text, pattern, textIdx + 1, patternIdx + 1);
            }
            return false;
        }

        public static void main(String[] args) {
            StringPatternMatcher stringPatternMatcher = new StringPatternMatcher();
            String s = "facde";
            String p = ".ac*d";
            boolean output2 = stringPatternMatcher.match(s, p);

//            Pattern pattern = Pattern.compile(p);
//            Matcher matcher = pattern.matcher(s);

            if (output2) {
                System.out.print(s + " " + p + " match");
            } else {
                System.out.print(s + " " + p + " did not match.");
            }
        }
    }

}
