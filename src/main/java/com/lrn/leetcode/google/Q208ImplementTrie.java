package com.lrn.leetcode.google;

public class Q208ImplementTrie {
    // take a look at Q 211 sol better implementation
    static class TrieNode {
        TrieNode[] childNodes = new TrieNode[26];
        int childCount = 0;
        boolean end = false;

        public void addChild(int idx) {
            childNodes[idx] = new TrieNode();
            childCount++;
        }
        public void markEnd(int idx) {
            childNodes[idx].end = true;
        }

        public boolean isAnyChildPresent() {
            return childCount != 0;
        }
    }

    static class Trie {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            insertRec(word, root);
        }

        private void insertRec(String word, TrieNode node) {
            final int nodeIdx = word.charAt(0) - 'a';
            if (node.childNodes[nodeIdx] == null) { // child not present
                node.addChild(nodeIdx);
            }
            if( word.length() == 1) {
                node.markEnd(nodeIdx);
                return;
            }
            insertRec(word.substring(1), node.childNodes[nodeIdx]);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if(word == null || word.length() ==0) {
                return false;
            }
            return searchRec(word, root);
        }

        public boolean searchRec(String word, TrieNode node) {
            final int nodeIdx = word.charAt(0) - 'a';
            if (word.length() == 1) {
                return node.childNodes[nodeIdx] != null && node.childNodes[nodeIdx].end;
            }
            if (node.childNodes[nodeIdx] != null) {
                return searchRec(word.substring(1), node.childNodes[nodeIdx]);
            }
            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.length() ==0) {
                return false;
            }
            return startWithRec(prefix, root);
        }

        public boolean startWithRec(String word, TrieNode node) {
            final int nodeIdx = word.charAt(0) - 'a';
            if (word.length() == 1) {
                return node.childNodes[nodeIdx] != null;
            }
            if (node.childNodes[nodeIdx] != null) {
                return startWithRec(word.substring(1), node.childNodes[nodeIdx]);
            }
            return false;
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple") ? "passed" : "failed");
        System.out.println(!trie.search("app") ? "passed" : "failed");
        System.out.println(trie.startsWith("app") ? "passed" : "failed");
        trie.insert("app");
        System.out.println(trie.search("app") ? "passed" : "failed");
    }


}
