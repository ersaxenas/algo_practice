package com.lrn.educative.gci.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class WorkSubSetUtil {
    /*Given a set with distinct elements, find all of its distinct subsets.*/
    static class SubsetsBfs {
        public List<List<Integer>> findSubsets(int[] nums) {
            assert nums != null && nums.length > 1;
            List<List<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());
            for (Integer elem : nums) {
                int currentSetSize = subsets.size();
                for (int idx = 0; idx < currentSetSize; idx++) {
                    ArrayList<Integer> set = new ArrayList<>(subsets.get(idx));
                    set.add(elem);
                    subsets.add(set);
                }
            }
            return subsets;
        }

        public static void main(String[] args) {
            SubsetsBfs subsetsBfs = new SubsetsBfs();
            List<List<Integer>> result = subsetsBfs.findSubsets(new int[]{1, 3});
            System.out.println("Here is the list of subsets: " + result);

            result = subsetsBfs.findSubsets(new int[]{1, 5, 3});
            System.out.println("Here is the list of subsets: " + result);
        }
    }

    /*Given a set of numbers that might contain duplicates, find all of its distinct subsets.*/
    static class DistinctSubsetBfs {
        public List<List<Integer>> findSubsets(int[] nums) {
            assert nums != null && nums.length > 1;
            List<List<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());
            // sort array
            Arrays.sort(nums);
            int endIndex = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                int elem = nums[idx];
                int currentSetSize = subsets.size();
                int startIndex = 0;
                if (idx > 0 && nums[idx] == nums[idx - 1]) {// duplicate check
                    startIndex = endIndex;
                }
                for (int setIdx = startIndex; setIdx < currentSetSize; setIdx++) {
                    ArrayList<Integer> lst = new ArrayList<>(subsets.get(setIdx));
                    lst.add(elem);
                    subsets.add(lst);
                }
                endIndex = currentSetSize;
            }
            return subsets;
        }

        public static void main(String[] args) {
            DistinctSubsetBfs distinctSubsetBfs = new DistinctSubsetBfs();
            List<List<Integer>> result = distinctSubsetBfs.findSubsets(new int[]{1, 3, 3});
            System.out.println("Here is the list of subsets: " + result);

            result = distinctSubsetBfs.findSubsets(new int[]{1, 5, 3, 3});
            System.out.println("Here is the list of subsets: " + result);
        }
    }

    /*Given a set of distinct numbers, find all of its permutations.*/
    static class Permutations {
        public List<List<Integer>> findPermutations(int[] nums) {
            assert nums != null && nums.length > 1;
            List<List<Integer>> resultList = new ArrayList<>();
            Queue<List<Integer>> queue = new LinkedList<>();
            queue.add(new ArrayList<>());
            // loop 1:
            for (int num : nums) {
                final int currentQueueSize = queue.size();
                for (int idx1 = 0; idx1 < currentQueueSize; idx1++) {
                    final List<Integer> lst = queue.poll();
                    final int lstSize = lst.size();
                    for (int idx2 = 0; idx2 <= lstSize; idx2++) {
                        ArrayList<Integer> newLst = new ArrayList<>(lst);
                        newLst.add(idx2, num);
                        queue.add(newLst);
                    }
                }
            }
            resultList.addAll(queue);
            return resultList;
        }

        public void generatePermuationsRecursive(int[] nums, int index, List<Integer> lst, List<List<Integer>> resultList) {
            if (index == nums.length) {
                resultList.add(lst);
            } else {
                int lstSize = lst.size();
                for (int idx = 0; idx <= lstSize; idx++) {
                    ArrayList<Integer> newLst = new ArrayList<>(lst);
                    newLst.add(idx, nums[index]);
                    generatePermuationsRecursive(nums, index + 1, newLst, resultList);
                }
            }
        }

        public static void main(String[] args) {
            Permutations permutations = new Permutations();
            List<List<Integer>> result = permutations.findPermutations(new int[]{1, 3, 5});
            System.out.println("Here are all the permutations: " + result);
            result.clear();
            ;
            permutations.generatePermuationsRecursive(new int[]{1, 3, 5}, 0, new ArrayList<>(), result);
            System.out.println("Here are all the permutations: " + result);
        }
    }

    /*Given a string, find all of its permutations preserving the character sequence but changing case*/
    static class PermutationsChangingCase {
        public List<String> findLetterCaseStringPermutations(String str) {
            assert str != null && str.trim().length() > 1;
            List<String> permutations = new ArrayList<>();
            Queue<List<Character>> queue = new LinkedList<>();
            queue.offer(new ArrayList<>());
            final char[] stringChars = str.toCharArray();
            for (Character currentChar : stringChars) {
                int currentQueueSize = queue.size();
                for (int idx = 0; idx < currentQueueSize; idx++) {
                    final List<Character> lst = queue.poll();
                    if (Character.isDigit(currentChar)) {
                        ArrayList<Character> newLst = new ArrayList<>(lst);
                        newLst.add(currentChar);
                        queue.offer(newLst);
                    } else {
                        ArrayList<Character> newLowerLst = new ArrayList<>(lst);
                        newLowerLst.add(Character.toLowerCase(currentChar));
                        ArrayList<Character> newUpperLst = new ArrayList<>(lst);
                        newUpperLst.add(Character.toUpperCase(currentChar));
                        queue.offer(newLowerLst);
                        queue.offer(newUpperLst);
                    }
                }
            }
            queue.forEach(lst -> {
                StringBuilder sbr = new StringBuilder();
                lst.forEach(character -> {
                    sbr.append(character);
                });
                permutations.add(sbr.toString());
            });
            return permutations;
        }

        public List<String> findLetterCaseStringPermutations2(String str) {
            assert str != null && str.trim().length() > 1;
            List<String> permutations = new ArrayList<>();
            permutations.add(str);
            for (int idx = 0; idx < str.length(); idx++) {
                final int currentSize = permutations.size();
                for (int elemIndex = 0; elemIndex < currentSize; elemIndex++) {
                    final char[] currentString = permutations.get(elemIndex).toCharArray();
                    final char ch = currentString[idx];
                    if (Character.isLetter(ch)) {
                        if (Character.isUpperCase(ch)) {
                            currentString[idx] = Character.toLowerCase(ch);
                        } else {
                            currentString[idx] = Character.toUpperCase(ch);
                        }
                        permutations.add(String.valueOf(currentString));
                    }
                }
            }
            return permutations;
        }

        public List<String> findLetterCaseStringPermutations3(String str) {
            assert str != null && str.trim().length() > 1;
            List<String> permutations = new ArrayList<>();
            findLetterCaseStringPermutationsWithRecursion(str, new StringBuilder(), 0, permutations);
            return permutations;
        }

        public void findLetterCaseStringPermutationsWithRecursion(String str, StringBuilder sbr, int charIndex, List<String> result) {
            if (charIndex == str.length()) {
                result.add(sbr.toString());
                return;
            }
            char ch = str.charAt(charIndex);
            if (Character.isLetter(ch)) {
                StringBuilder sbr1 = new StringBuilder(sbr.toString());
                StringBuilder sbr2 = new StringBuilder(sbr.toString());
                char caseChangedChar;
                if (Character.isUpperCase(ch)) {
                    caseChangedChar = Character.toLowerCase(ch);
                } else {
                    caseChangedChar = Character.toUpperCase(ch);
                }
                sbr1.append(ch);
                sbr2.append(caseChangedChar);
                findLetterCaseStringPermutationsWithRecursion(str, sbr1, charIndex + 1, result);
                findLetterCaseStringPermutationsWithRecursion(str, sbr2, charIndex + 1, result);
            } else {
                findLetterCaseStringPermutationsWithRecursion(str, new StringBuilder(sbr.toString()).append(ch), charIndex + 1, result);
            }
        }

        public static void main(String[] args) {
            PermutationsChangingCase permutationsChangingCase = new PermutationsChangingCase();
            List<String> result = permutationsChangingCase.findLetterCaseStringPermutations("ad52");
            System.out.println(" String permutations are: " + result);
            result = permutationsChangingCase.findLetterCaseStringPermutations("ab7c");
            System.out.println(" String permutations are: " + result);

            result = permutationsChangingCase.findLetterCaseStringPermutations2("ad52");
            System.out.println(" String permutations are: " + result);
            result = permutationsChangingCase.findLetterCaseStringPermutations2("ab7c");
            System.out.println(" String permutations are: " + result);

            result = permutationsChangingCase.findLetterCaseStringPermutations3("ad52");
            System.out.println(" String permutations are: " + result);
            result = permutationsChangingCase.findLetterCaseStringPermutations3("ab7c");
            System.out.println(" String permutations are: " + result);
        }
    }

    /*For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.*/
    static class BalancedParentheses {
        class ParenthesesStr {
            StringBuilder paraStr = new StringBuilder();
            int open = 0, close = 0;

            public ParenthesesStr addOpen() {
                ParenthesesStr parenthesesStr = new ParenthesesStr();
                parenthesesStr.paraStr.append(this.paraStr).append("(");
                parenthesesStr.open = this.open + 1;
                parenthesesStr.close = this.close;
                return parenthesesStr;
            }

            public ParenthesesStr addClose() {
                ParenthesesStr parenthesesStr = new ParenthesesStr();
                parenthesesStr.paraStr.append(this.paraStr).append(")");
                parenthesesStr.open = this.open;
                parenthesesStr.close = this.close + 1;
                return parenthesesStr;
            }

            @Override
            public String toString() {
                return "ParenthesesStr{" +
                        "paraStr=" + paraStr +
                        ", open=" + open +
                        ", close=" + close +
                        '}';
            }
        }

        public List<String> generateValidParentheses(int num) {
            assert num >= 2;
            List<String> result = new ArrayList<>();
            Queue<ParenthesesStr> queue = new LinkedList<>();
            queue.offer(new ParenthesesStr());
            while (!queue.isEmpty()) {
                final ParenthesesStr parenthesesStr = queue.poll();
                if (parenthesesStr.open == num && parenthesesStr.close == num) {
                    result.add(parenthesesStr.paraStr.toString());
                } else {
                    if (parenthesesStr.open < num) {
                        queue.offer(parenthesesStr.addOpen());
                    }
                    if (parenthesesStr.close < parenthesesStr.open) {
                        queue.offer(parenthesesStr.addClose());
                    }
                }
            }
            return result;
        }

        public List<String> generateValidParentheses2(int num) {
            List<String> result = new ArrayList<>();
            generateValidParenthesesRecursion(new String(), 0, 0, result, num);
            return result;
        }

        public void generateValidParenthesesRecursion(String str, int openCnt, int closeCnt, List<String> result, int num) {
            if (openCnt == num && closeCnt == num) {
                result.add(str);
                return;
            }
            if (openCnt < num) {
                generateValidParenthesesRecursion(str + "(", openCnt + 1, closeCnt, result, num);
            }
            if (closeCnt < openCnt) {
                generateValidParenthesesRecursion(str + ")", openCnt, closeCnt + 1, result, num);
            }
        }

        public static void main(String[] args) {
            BalancedParentheses balancedParentheses = new BalancedParentheses();
            List<String> result = balancedParentheses.generateValidParentheses(2);
            System.out.println("All combinations of balanced parentheses are: " + result);
            result = balancedParentheses.generateValidParentheses(3);
            System.out.println("All combinations of balanced parentheses are: " + result);

            result = balancedParentheses.generateValidParentheses2(2);
            System.out.println("All combinations of balanced parentheses are: " + result);
            result = balancedParentheses.generateValidParentheses2(3);
            System.out.println("All combinations of balanced parentheses are: " + result);
        }
    }

    /*Given a word, write a function to generate all of its unique generalized abbreviations.*/
    static class AbbreviationGen {
        class AbbreviatedWord {
            StringBuilder sbr = new StringBuilder();
            int count = 0;
            int charIndex = 0;

            public int getLen() {
                return sbr.length();
            }

            @Override
            public String toString() {
                return "AbbreviatedWord{" +
                        "sbr=" + sbr +
                        ", count=" + count +
                        ", charIndex=" + charIndex +
                        '}';
            }

            public AbbreviatedWord addAbbreviation() {
                AbbreviatedWord abbreviatedWord = new AbbreviatedWord();
                abbreviatedWord.sbr.append(this.sbr.toString());
                abbreviatedWord.count = this.count + 1;
                abbreviatedWord.charIndex = this.charIndex + 1;
                return abbreviatedWord;
            }

            public AbbreviatedWord addChar(Character ch) {
                abbreviate();
                AbbreviatedWord abbreviatedWord = new AbbreviatedWord();
                abbreviatedWord.sbr.append(sbr.toString());
                abbreviatedWord.sbr.append(ch);
                abbreviatedWord.charIndex = this.charIndex + 1;
                return abbreviatedWord;
            }

            public void abbreviate() {
                if (this.count != 0) {
                    this.sbr.append(count);
                    this.count = 0;
                }
            }
        }

        public List<String> generateGeneralizedAbbreviation(String word) {
            assert word != null && word.trim().length() != 0;
            List<String> result = new ArrayList<>();
            Queue<AbbreviatedWord> queue = new LinkedList<>();
            queue.offer(new AbbreviatedWord());
            while (!queue.isEmpty()) {
                AbbreviatedWord currentWord = queue.poll();
                if (currentWord.charIndex == word.length()) {
                    currentWord.abbreviate();
                    result.add(currentWord.sbr.toString());
                } else {
                    // skip abbreviation
                    AbbreviatedWord abbreviatedWord1 = currentWord.addAbbreviation();
                    queue.offer(abbreviatedWord1);
                    //add abbreviation and char
                    final char chr = word.charAt(currentWord.charIndex);
                    AbbreviatedWord abbreviatedWord2 = currentWord.addChar(chr);
                    queue.offer(abbreviatedWord2);
                }
            }
            return result;
        }

        public void genAbbreviation(String word, StringBuilder sbr, int chIndex, int abbCount, List<String> result) {
            if (chIndex == word.length()) {
                if (abbCount != 0) {
                    sbr.append(abbCount);
                }
                result.add(sbr.toString());
                return;
            }
            // skip abbreviation
            genAbbreviation(word, new StringBuilder(sbr.toString()), chIndex + 1, abbCount + 1, result);
            // abbreviate and add char
            StringBuilder newSbr = new StringBuilder(sbr.toString());
            if (abbCount != 0) {
                newSbr.append(abbCount);
            }
            newSbr.append(word.charAt(chIndex));
            genAbbreviation(word, newSbr, chIndex + 1, 0, result);
        }

        public List<String> generateGeneralizedAbbreviation2(String word) {
            List<String> result = new ArrayList<>();
            genAbbreviation(word, new StringBuilder(), 0, 0, result);
            return result;
        }

        public static void main(String[] args) {
            AbbreviationGen abbreviationGen = new AbbreviationGen();
            List<String> result = abbreviationGen.generateGeneralizedAbbreviation("BAT");
            System.out.println("Generalized abbreviation are: " + result);
            result = abbreviationGen.generateGeneralizedAbbreviation("code");
            System.out.println("Generalized abbreviation are: " + result);

            result = abbreviationGen.generateGeneralizedAbbreviation2("BAT");
            System.out.println("Generalized abbreviation are: " + result);
            result = abbreviationGen.generateGeneralizedAbbreviation2("code");
            System.out.println("Generalized abbreviation are: " + result);
        }
    }

    /*Given an expression containing digits and operations (+, -, *), find all possible ways in which the expression can be evaluated by grouping the numbers and operators using parentheses.*/
    static class ExpressionSolver {

        public List<Integer> solveExpression(String expression) {
            List<Integer> result = new ArrayList<>();
            if (!expression.contains("+") && !expression.contains("-") && !expression.contains("*")) {
                //System.out.println("Got: " + expression);
                result.add(Integer.valueOf(expression));
            } else {
                // break the expression
                for (int idx = 0; idx < expression.length(); idx++) {
                    Character ch = expression.charAt(idx);
                    // if char is not a digit then split
                    if (!Character.isDigit(ch)) {
                        // split
                        String leftExpression = expression.substring(0, idx);
                        String rightExpression = expression.substring(idx + 1);
                        //System.out.println(String.format("Expression: %s -> leftExpression: '%s', rightExpression '%s'", expression,leftExpression, rightExpression));
                        // now evaluate left expression and then right expression
                        List<Integer> leftExpressionResult = solveExpression(leftExpression);
                        List<Integer> rightExpressionResult = solveExpression(rightExpression);
                        // now we have left, right and an operation +,-,*
                        for (Integer op1 : leftExpressionResult) {
                            for (Integer op2 : rightExpressionResult) {
                                int res = 0;
                                if (ch == '+') {
                                    res = op1 + op2;
                                } else if (ch == '-') {
                                    res = op1 - op2;
                                } else if (ch == '*') {
                                    res = op1 * op2;
                                } else {
                                    System.out.println("Unknown operator :" + ch);
                                }
                                result.add(res);
                            }
                        }
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            ExpressionSolver expressionSolver = new ExpressionSolver();
            List<Integer> result = expressionSolver.solveExpression("1+2*3");
            System.out.println("Expression evaluations: " + result);

            result = expressionSolver.solveExpression("2*3-4-5");
            System.out.println("Expression evaluations: " + result);
        }
    }

    /*Given a number ‘n’, write a function to return all structurally unique Binary Search Trees (BST) that can store values 1 to ‘n’?*/
    static class UniqueBST {
        class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "val=" + val +
                        ", left=" + ((left != null) ? left.val : "-") +
                        ", right=" + ((right != null) ? right.val : "-") +
                        '}';
            }
        }


        Stack<String> stack = new Stack<>();

        public List<TreeNode> findUniqueTrees(int n) {
            if (n <= 0)
                return new ArrayList<TreeNode>();

            final List<TreeNode> uniqueTreesRecursive = findUniqueTreesRecursive2(1, n);
            while (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
            return uniqueTreesRecursive;
        }

        public List<TreeNode> findUniqueTreesRecursive(int start, int end) {
            stack.add(start + "-" + end);
            List<TreeNode> result = new ArrayList<>();
            // base condition, return 'null' for an empty sub-tree
            // consider n=1, in this case we will have start=end=1, this means we should have only one tree
            // we will have two recursive calls, findUniqueTreesRecursive(1, 0) & (2, 1)
            // both of these should return 'null' for the left and the right child
            if (start > end) {
                result.add(null);
                return result;
            }

            for (int i = start; i <= end; i++) {
                // making 'i' root of the tree
                List<TreeNode> leftSubtrees = findUniqueTreesRecursive(start, i - 1);
                List<TreeNode> rightSubtrees = findUniqueTreesRecursive(i + 1, end);
                for (TreeNode leftTree : leftSubtrees) {
                    for (TreeNode rightTree : rightSubtrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        result.add(root);
                    }
                }
            }
            return result;
        }

        public List<TreeNode> findUniqueTreesRecursive2(int start, int end) {
            // same as previous method
            List<TreeNode> result = new ArrayList<>();
            if (start > end) {
                result.add(null); // for leaf node's child it will return null.
                return result;
            }
            for (int idx = start; idx <= end; idx++) {
                // consider each node as root and find left (start to root -1) and right (root+1 to end)
                int root = idx;
                List<TreeNode> leftTree = findUniqueTreesRecursive2(start, root - 1);
                List<TreeNode> rightTree = findUniqueTreesRecursive2(root + 1, end);
                // now we have left trees and right trees for root. Now find all the possible child combination, add it to root node and add to result list
                for (TreeNode leftChild : leftTree) { // for each left tree
                    for (TreeNode rightChild : rightTree) { // for each right tree
                        TreeNode rootNode = new TreeNode(root);
                        rootNode.left = leftChild;
                        rootNode.right = rightChild;
                        // for leaf node both list (left and right) will have null element so it will return null<- root -> null tree structure
                        result.add(rootNode);
                    }
                }
            }
            return result;
        }
    }
    public static void main(String[] args) {
        UniqueBST uniqueBST = new UniqueBST();
        List<UniqueBST.TreeNode> result = uniqueBST.findUniqueTrees(2);
        System.out.print("Total trees: " + result.size());
    }
    /*Count of Structurally Unique Binary Search Trees*/
    static class UniqueBSTCount {
        public Integer countTrees(Integer num) {
            if(num == null || num <=1) {
                return 1;
            }
            Integer count =0;
            for (int idx = 1; idx <= num; idx++) {
                int root = idx;
                // left (root -1) <- root -> right (num - root)
                int leftCount = countTrees(root-1);
                int rightCount = countTrees(num-root);
                count = count + (leftCount*rightCount);
            }
          return count;
        }
        public static void main(String[] args) {
            UniqueBSTCount uniqueBSTCount = new UniqueBSTCount();
            int count = uniqueBSTCount.countTrees(3);
            System.out.print("Total trees: " + count);
        }
    }
}

