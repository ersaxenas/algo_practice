package com.lrn.educative.dsj.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkTrieUtil {
    static class TotalWords {
        public Integer findTotalNumberOfWords(TrieNode trieNode) {
            assert trieNode != null;
            Integer wordCount = 0;
            if (trieNode.isEndWord) wordCount++;
            for (int idx = 0; idx < trieNode.children.length; idx++) {
                if (trieNode.children[idx] != null) {
                    wordCount = wordCount + findTotalNumberOfWords(trieNode.children[idx]);
                }
            }
            return wordCount;
        }

        public static void main(String[] args) {
            // Input keys (use only 'a' through 'z' and lower case)
            String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their", "abc"};
            Trie t = new Trie();
            System.out.println("Keys: " + Arrays.toString(keys));
            // Construct trie
            for (int i = 0; i < keys.length; i++) {
                t.insert(keys[i]);
            }
            TotalWords totalWords = new TotalWords();
            System.out.println(totalWords.findTotalNumberOfWords(t.getRoot()));
        }
    }

    /*Find All of the Words in a Trie*/
    static class AllWords {
        public void findWords(TrieNode node, ArrayList<String> result, StringBuilder str) {
            assert node != null;
            if (node.isEndWord) {
                result.add(str.toString());
            }
            for (int idx = 0; idx < node.children.length; idx++) {
                if (node.children[idx] != null) {
                    str.append((char) (idx + 'a'));
                    findWords(node.children[idx], result, str);
                    str.deleteCharAt(str.length() - 1);
                }
            }

        }

        public List<String> findWords(TrieNode node) {
            final ArrayList<String> result = new ArrayList<>();
            findWords(node, result, new StringBuilder());
            return result;
        }

        public static void main(String[] args) {
            // Input keys (use only 'a' through 'z' and lower case)
            String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their", "abc"};
            String output[] = {"Not present in trie", "Present in trie"};
            Trie t = new Trie();
            System.out.println("Keys: " + Arrays.toString(keys));
            // Construct trie
            for (int i = 0; i < keys.length; i++) {
                t.insert(keys[i]);
            }
            AllWords allWords = new AllWords();
            List<String> list = allWords.findWords(t.getRoot());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    /*Word Formation from a Given Dictionary using a Trie*/
    static class WordFormationChecker {
        Trie dictionary = new Trie();
        public WordFormationChecker(String[] dict) {
            assert dict != null;
            if (dict.length < 2) {
                throw new IllegalStateException("Dictionary is too short.");
            }
            Arrays.asList(dict).forEach(dictionary::insert);
        }

        public boolean isFormationPossible(String word) {
            assert word != null;
            if (word.length() < 2) {
                return false;
            }
            final TrieNode root = dictionary.getRoot();
            for (int idx = 0; idx < word.length(); idx++) {
                String firstPart = word.substring(0, idx);
                String secondPart = word.substring(idx, word.length());
                if (dictionary.search(firstPart) && dictionary.search(secondPart)) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            // Input dict (use only 'a' through 'z' and lower case)
            String dict[] = {"the", "hello", "there", "answer", "any", "dragon", "world", "their", "inc"};
            System.out.println("Keys: " + Arrays.toString(dict));
            WordFormationChecker wordFormationChecker = new WordFormationChecker(dict);
            System.out.println(wordFormationChecker.isFormationPossible("helloworld"));
            System.out.println(wordFormationChecker.isFormationPossible("helloworlk"));
        }
    }

}
