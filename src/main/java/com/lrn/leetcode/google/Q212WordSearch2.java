package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q212WordSearch2 {
    /*
    * pd: Given a 2D board and a list of words from the dictionary, find all words in the board.
    * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
    * those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
    *Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
    * assm : non null in metrics, row < 100, col < 100, max word len < 100, best time sol,
    * appr:
    *
    * test cases:
    * */

    static class WSTrieNode {
        public WSTrieNode[] child = new WSTrieNode[26];
        public String word;
    }


    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        //checks
        if (words == null || words.length == 0) {
            return result;
        }
        WSTrieNode root = new WSTrieNode();
        buildTrie(words, root);
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[0].length; col++) {
                dfsWithBacktracking(board,row,col, root, result);
            }
        }

        return result;
    }

    public void dfsWithBacktracking(char[][] board, int row, int col, WSTrieNode node, List<String> result) {
        //base
        if(row<0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '#') {
            return;
        }
        char ch = board[row][col];
        int index = ch -'a';
        if(node.child[index] == null) { // no match
            return;
        }
        node = node.child[index]; // move to child node
        if(node.word != null) {// found
            result.add(node.word);
            node.word = null;// to avoid duplicates
        }
        // backtracking
        board[row][col] = '#';
        dfsWithBacktracking(board, row+1, col, node, result);
        dfsWithBacktracking(board, row-1, col, node, result);
        dfsWithBacktracking(board, row, col+1, node, result);
        dfsWithBacktracking(board, row, col-1, node, result);
        board[row][col] = ch;
    }

    public void buildTrie(String[]  words, WSTrieNode root ) {
        for(String word: words) {
            WSTrieNode node = root;
            for(char ch: word.toCharArray()) {
                  int index = ch - 'a';
                  if(node.child[index] == null) {
                      node.child[index] = new WSTrieNode();
                  }
                  node = node.child[index];
            }
            node.word = word;
        }
    }

    public static void main(String[] args) {
        Q212WordSearch2 sol = new Q212WordSearch2();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        LsUtil.printList(sol.findWords(board, new String[] {"oath", "pea", "eat", "rain"}));

    }


}
