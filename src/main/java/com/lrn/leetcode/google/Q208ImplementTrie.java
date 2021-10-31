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

    /* Revised 20210914*/
    static class Trie2 {

        static class TrieNode {
            public TrieNode[] child = new TrieNode[26];
            int childCount = 0;
            boolean end = false;
            public TrieNode addChild(int idx) {
                if(child[idx] == null) {
                    child[idx] = new TrieNode();
                    childCount++;
                }
                return child[idx];
            }
            public void markEnd() {
                end = true;
            }

            public boolean isEndNode() {
                return end;
            }

        }

        /** Initialize your data structure here. */
        TrieNode root = new TrieNode();
        public Trie2() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word == null || word.isEmpty()) return;
            insertRec(word, root);
        }

        public void insertRec(String word, TrieNode node) {
            int idx = word.charAt(0) - 'a';
            TrieNode child = node.addChild(idx);
            // base case
            if(word.length() == 1) { // last char of word
                child.markEnd();
                return;
            }
            insertRec(word.substring(1), child);
        }
        /*iterative insert*/
        public void insertIter(String[]  words, TrieNode root ) {
            for(String word: words) {
                TrieNode node = root;
                for(char ch: word.toCharArray()) {
                    int index = ch - 'a';
                    if(node.child[index] == null) {
                        node.child[index] = new TrieNode();
                    }
                    node = node.child[index];
                }
                node.markEnd();
            }
        }


        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word == null || word.isEmpty()) return false;
            return searchRec(word,root);
        }

        public boolean searchRec(String word, TrieNode node) {
            int idx = word.charAt(0) - 'a';
            if(node.child[idx] == null) return false;
            if(word.length() == 1) return node.child[idx].isEndNode();
            return searchRec(word.substring(1), node.child[idx]);
        }


        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.isEmpty()) return false;
            return startWithRec(prefix,root);
        }

        public boolean startWithRec(String word, TrieNode node) {
            int idx = word.charAt(0) - 'a';
            if(node.child[idx] == null) return false;
            if(word.length() == 1) return true;
            return startWithRec(word.substring(1), node.child[idx]);
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
