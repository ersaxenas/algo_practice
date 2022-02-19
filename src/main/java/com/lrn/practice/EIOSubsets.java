package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;
import com.lrn.leetcode.google.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class EIOSubsets {

     public List<List<Integer>> findPerm(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      boolean[] inuse = new boolean[nums.length];
      backTrackPerm(result, nums, 0, new ArrayList<>(), inuse);
      return result;
    }

    public void backTrackPerm(List<java.util.List<Integer>> result, int[] nums, int start, List<Integer> numlist, boolean[] inuse) {
         if(numlist.size() >= nums.length) {
           result.add(new ArrayList<>(numlist));
           return;
         }
         for(int idx = 0; idx<nums.length; idx++) {
              if(!inuse[idx]) {
                  numlist.add(nums[idx]);
                  inuse[idx] = true;
                  backTrackPerm(result, nums, idx + 1, numlist, inuse);
                  numlist.remove(numlist.size() - 1);
                  inuse[idx] = false;
              }
         }
    }

    public List<String> balanceParentheses(int num) {
       List<String> result = new ArrayList<>();
       backtrackBP(result, 0, 0, num, new StringBuilder());
       return result;
    }

    public void backtrackBP(List<String> result, int open, int close, int N, StringBuilder buffer) {
         if(buffer.length() == (N*2) && open == close) {
             result.add(buffer.toString());
         } else {
             if(open < N) {
                 buffer.append("(");
                 backtrackBP(result, open+1, close, N, buffer);
                 buffer.setLength(buffer.length()-1);
             }
             if(close < open) {
                 buffer.append(")");
                 backtrackBP(result, open, close+1, N, buffer);
                 buffer.setLength(buffer.length()-1);
             }
         }
    }

    static class AbbreviatedWord {
       StringBuilder buffer;
       int start;
       int abCount;
       public AbbreviatedWord(StringBuilder buffer, int start, int abCount) {
           this.buffer = buffer;
           this.start = start;
           this.abCount = abCount;
       }
    }

    public  List<String> generateGeneralizedAbbreviation(String word) {
         List<String> result = new LinkedList<>();
        ArrayDeque<AbbreviatedWord> queue = new ArrayDeque<>();
        queue.add(new AbbreviatedWord(new StringBuilder(), 0,0));
        while(!queue.isEmpty()) {
            AbbreviatedWord caword = queue.poll();
            if(caword.start == word.length()) {
                if(caword.abCount != 0) {
                    caword.buffer.append(caword.abCount);
                }
                result.add(caword.buffer.toString());
            } else {
                // abbreviate
                queue.add(new AbbreviatedWord(new StringBuilder(caword.buffer), caword.start+1, caword.abCount+1));
                // take letter
                if(caword.abCount != 0) { // if any prev abbr then append to buffer
                    caword.buffer.append(caword.abCount);
                }
                queue.add(new AbbreviatedWord(new StringBuilder(caword.buffer.toString() + word.charAt(caword.start)), caword.start+1, 0));
            }
        }
        return result;
    }

    public List<String> genAbbr(String word) {
         List<String> result = new ArrayList<>();
         generateGeneralizedAbbreviation2(result, word, 0, 0, new StringBuilder());
         return result;
    }

    public  void generateGeneralizedAbbreviation2(List<String> result, String word, int start, int count, StringBuilder buffer) {
         if(start == word.length()) {
             String str = "";
            if(count != 0) {
                str = str + count;
            }
            result.add(str + buffer.toString());
         } else {
             // abb
             generateGeneralizedAbbreviation2(result, word, start+1, count+1,buffer);
             // consider word
             if(count != 0) {
                 buffer.append(count);
             }
             generateGeneralizedAbbreviation2(result, word, start+1, 0, buffer.append(word.charAt(start)));
             if(count!=0) {
                 buffer.setLength(buffer.length()-2);
             } else {
                 buffer.setLength( buffer.length()-1);
             }
         }
    }

    public List<Integer> evaluateExpr(String expr, Map<String,List<Integer>> cache) {
         List<Integer> result = new LinkedList<>();
         // base case
        if(!(expr.contains("+") || expr.contains("-") || expr.contains("*"))) {
          result.add(Integer.parseInt(expr));
          return result;
        }
        if(cache.containsKey(expr)) {
            return cache.get(expr);
        }
        for(int idx=0; idx< expr.length(); idx++) {
            final char ch = expr.charAt(idx);
            if(!Character.isDigit(ch)) {
                List<Integer> leftEval = evaluateExpr(expr.substring(0,idx),cache);
                List<Integer> rightEval = evaluateExpr(expr.substring(idx+1),cache);
                for(Integer left: leftEval) {
                    for(Integer right: rightEval) {
                        switch(ch) {
                            case '+': result.add(left+right);
                                break;
                            case '-': result.add(left-right);
                                break;
                            case '*': result.add(left*right);
                                break;
                        }
                    }
                }
            }
        }
        cache.put(expr, result);
        return result;
    }

    public List<TreeNode> findUniqueTreesRec(int start, int end) {
         List<TreeNode> result = new LinkedList<>();
         if(start > end) {
             result.add(null);
             return result;
         }

         for(int idx=start; idx<=end; idx++) {
             List<TreeNode> leftTrees = findUniqueTreesRec(start, idx-1);
             List<TreeNode> rightTrees = findUniqueTreesRec(idx+1, end);
             for(TreeNode left: leftTrees) {
                 for(TreeNode right: rightTrees) {
                     TreeNode root = new TreeNode(idx);
                      root.left = left;
                      root.right = right;
                      result.add(root);
                 }
             }
         }
       return result;

    }


    public int countTrees(int n, Map<Integer, Integer> cache) {
         if(n <=1) {
             return 1;
         }
         if(cache.containsKey(n)) {
             return cache.get(n);
         }
         int count=0;
         for(int idx=1; idx<=n; idx++) {
             int leftc = countTrees(idx-1,cache);
             int rightc = countTrees(n-idx,cache);
             count = count + (leftc * rightc);
         }
         cache.put(n, count);
         return count;
    }

    public int maxProfitProjects(int[] capital, int[] profits, int projectsLimit, int initialCapacity) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> capital[a] - capital[b]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> profits[b]-profits[a]);
        for(int idx=0; idx<capital.length; idx++) {
            minHeap.add(idx);
        }
        int capacity = initialCapacity;
        int projectCount = 0;
        while(projectCount < projectsLimit) {
              while(!minHeap.isEmpty() && capital[minHeap.peek()] <= capacity) {
                  maxHeap.add(minHeap.poll());
              }
              if(maxHeap.isEmpty()) {
                  break; // no projects can be selected with available capital
              }
              capacity = capacity + profits[maxHeap.peek()];
              projectCount++;
        }
        return capacity;
    }

    public int[] findNextInterval(int[][] intervals) {
         int[] result = new int[intervals.length];
         PriorityQueue<Integer> maxStart = new PriorityQueue<>((a,b) -> intervals[b][0] - intervals[a][0]);
         PriorityQueue<Integer> maxEnd = new PriorityQueue<>((a,b) -> intervals[b][1] - intervals[a][1]);
         for(int idx=0; idx<intervals.length; idx++) {
             maxStart.add(idx);
             maxEnd.add(idx);
         }

         while(!maxEnd.isEmpty()) {
             int maxEndIdx = maxEnd.poll();
             result[maxEndIdx] = -1;

             while(!maxStart.isEmpty() && intervals[maxStart.peek()][0] >= intervals[maxEndIdx][1]) {
                 result[maxEndIdx] = maxStart.poll();
             }
             if(result[maxEndIdx] != -1) {
                 maxStart.add(result[maxEndIdx]);
             }
         }
         return result;
    }



    public static void main(String[] args) {
         EIOSubsets sol = new EIOSubsets();
//         LsUtil.printListOfList(sol.findPerm(new int[] {1,2,3}));
//        LsUtil.printList(sol.balanceParentheses(2));
/*
         LsUtil.printList(sol.generateGeneralizedAbbreviation("BAT"));
         LsUtil.printList(sol.genAbbr("BAT"));
*/
/*
         LsUtil.printList(sol.evaluateExpr("1+2*3", new HashMap<>()));
         LsUtil.printList(sol.evaluateExpr("2*3-4-5", new HashMap<>()));
*/
/*
        final List<TreeNode> uniqueTreesRec = sol.findUniqueTreesRec(1, 3);
        System.out.println(uniqueTreesRec.size());
        System.out.println(sol.countTrees(3, new HashMap<>()));
*/
/*
        System.out.println(sol.maxProfitProjects(new int[]{0, 1, 2}, new int[]{1, 2, 3}, 2, 1));
        System.out.println(sol.maxProfitProjects(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0));
*/

        int[][] intervals = new int[][] { new int[] {2, 3}, new int[] {3, 4}, new int[]{5, 6} };
        LsUtil.printArray(sol.findNextInterval(intervals));
        intervals = new int[][] { new int[] {3, 4}, new int[] {1, 5}, new int[]{4, 6} };
        LsUtil.printArray(sol.findNextInterval(intervals));
    }

}
