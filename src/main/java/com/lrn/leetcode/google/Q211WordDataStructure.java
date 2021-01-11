package com.lrn.leetcode.google;

public class Q211WordDataStructure {
    /*
    * pd: Design a data structure that supports adding new words and finding if a string matches any previously added string.
Implement the WordDictionary class:
WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
Example:
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]
Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True
Constraints:
1 <= word.length <= 500
word in addWord consists lower-case English letters.
word in search consist of  '.' or lower-case English letters.
At most 50000 calls will be made to addWord and search.
    * assm: word len < 500, non null input, lower case latters only
    * appr: user tries to implement dict and modify search for '." char
    *
    * */

    static class WDTrieNode {
        WDTrieNode[] child = new WDTrieNode[26];
        String word = "";
    }

    static class WordDictionary {
        WDTrieNode root = new WDTrieNode();

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {

        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            WDTrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.child[ch - 'a'] == null) {
                    node.child[ch - 'a'] = new WDTrieNode();
                }
                node = node.child[ch - 'a'];
            }
            node.word = word;
        }


        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            return searchRec(word.toCharArray(), 0, root);
        }

        public boolean searchRec(char[] word, int idx, WDTrieNode node) {
            if (idx == word.length) {
                return !"".equals(node.word);
            }
            if (word[idx] != '.') {
               if(node.child[word[idx] - 'a'] != null) {
                   return searchRec(word, idx+1, node.child[word[idx] - 'a']);
               }
            } else {
               for(WDTrieNode cn: node.child) {
                   if(cn != null) {
                       if(searchRec(word, idx+1, cn)) {
                           return true;
                       }
                   }
               }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary sol = new WordDictionary();
//        sol.addWord("bad");
//        sol.addWord("dad");
//        sol.addWord("mad");
//        sol.addWord("at");
//        sol.addWord("and");
//        sol.addWord("a");
//        sol.addWord("an");
//        sol.addWord("add");
////        System.out.println(!sol.search("pad") ? "passed" : "failed");
////        System.out.println(sol.search("bad")  ? "passed" : "failed");
////        System.out.println(sol.search(".ad")  ? "passed" : "failed");
////        System.out.println(sol.search("b..")  ? "passed" : "failed");
//        //System.out.println(sol.search(".")  ? "passed" : "failed");
//        System.out.println(!sol.search("a")  ? "passed" : "failed");
//        System.out.println(!sol.search(".at")  ? "passed" : "failed");
//        sol.addWord("bat");
//        System.out.println(sol.search(".at")  ? "passed" : "failed");
//,"search","search","search","search","search","search","search","search","search","search"]
//[["r.n"],["ru.n.e"],["add"],["add."],["adde."],[".an."],["...s"],["....e."],["......."],["..n.r"]]
        sol.addWord("ran");
        sol.addWord("rune");
        sol.addWord("runner");
        sol.addWord("runs");
        sol.addWord("add");
        sol.addWord("adds");
        sol.addWord("adder");
        sol.addWord("addee");
        System.out.println(sol.search("r.n") ? "passed" : "failed");
        System.out.println(!sol.search("ru.n.e") ? "passed" : "failed");
        System.out.println(sol.search("add") ? "passed" : "failed");
        System.out.println(sol.search("add.") ? "passed" : "failed");

    }


}
