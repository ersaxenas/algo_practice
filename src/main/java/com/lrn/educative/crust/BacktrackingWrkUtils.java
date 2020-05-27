package com.lrn.educative.crust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BacktrackingWrkUtils {

    static class TPair<X, Y> {
        public X first;
        public Y second;

        public TPair(X x, Y y) {
            this.first = x;
            this.second = y;
        }
    }

    static class IntPair extends TPair<Integer, Integer> {
        public IntPair(Integer integer, Integer integer2) {
            super(integer, integer2);
        }

        public static IntPair of(Integer a, Integer b) {
            return new IntPair(a, b);
        }
    }

    static class Boggle {
        char[][] grid;
        boolean[][] state;
        Set<String> dictionary;
        int N;

        public Boggle(char[][] grid, Set<String> dictionary) {
            this.grid = grid;
            this.dictionary = dictionary;
            N = grid.length;
            state = new boolean[N][N];
            // initialize state with value false
            for (int idx = 0; idx < N; idx++) {
                Arrays.fill(state[idx], false);
            }
        }

        public List<IntPair> getNeighbours(int x, int y) {
            int Xs = Math.max(x - 1, 0);
            int Ys = Math.max(y - 1, 0);
            int Xe = Math.min(x + 1, N - 1);
            int Ye = Math.min(y + 1, N - 1);
            List<IntPair> neighbours = new ArrayList<>();
            for (int i = Xs; i <= Xe; i++) {
                for (int j = Ys; j <= Ye; j++) {
                    if ((i == x && j == y) || state[i][j]) {
                        continue;
                    }
                    neighbours.add(IntPair.of(i, j));
                }
            }
            return neighbours;
        }

        public void findWordsRecursive(int x, int y, StringBuilder buffer, HashSet<String> words) {
            // base
            if (buffer.length() > 0 && dictionary.contains(buffer.toString())) { // word found in dictionary
                words.add(buffer.toString());
            }
            // recursive
            List<IntPair> neighbours = getNeighbours(x, y); // find neighbours
            for (IntPair neighbour : neighbours) { // for each neighbour
                buffer.append(grid[neighbour.first][neighbour.second]); // add neighbour
                state[neighbour.first][neighbour.second] = true; // set neighbour in use to true;
                findWordsRecursive(neighbour.first, neighbour.second, buffer, words);
                buffer.setLength(buffer.length() - 1); // remove neighbour
                state[neighbour.first][neighbour.second] = false; // set neighbour in use to false;
            }
        }

        public HashSet<String> findAllWords() {
            StringBuilder buffer = new StringBuilder();
            HashSet<String> wordSet = new HashSet<>();
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    findWordsRecursive(x, y, buffer, wordSet);
                }
            }
            return wordSet;
        }


        public static void main(String[] args) {
            char[][] grid = new char[][]{
                    {'c', 'a', 't'},
                    {'r', 'r', 'e'},
                    {'t', 'o', 'n'}
            };

            String[] dict = {"cat", "cater", "cartoon", "art", "toon", "moon", "eat", "ton"};
            HashSet<String> dictionary = new HashSet<>(Arrays.asList(dict));
            Boggle b = new Boggle(grid, dictionary);
            HashSet<String> words = b.findAllWords();
            for (String s : words) {
                System.out.println(s);
            }
        }
    }

    /*Print All Combinations of Balanced Braces*/
    static class BalancedBraces {

        public void braceCombinationRecursive(String currCombination, int sum, int N, Set<String> combinationSet) {
            // base
            if (currCombination.length() > N) {
                return;
            }
            if (currCombination.length() == N && sum == 0) {
                combinationSet.add(currCombination);
            }
            // recursive
            if (sum < N / 2) {
                braceCombinationRecursive(currCombination + "{", sum + 1, N, combinationSet);
            }
            if (sum >= 1) {
                braceCombinationRecursive(currCombination + "}", sum - 1, N, combinationSet);
            }
        }

        public Set<String> braceCombination(int N) {
            if (N < 1) {
                return null;
            }
            Set<String> result = new HashSet<>();
            braceCombinationRecursive("{", 1, N * 2, result);
            return result;
        }

        // backtracking approach
        public void allBraceCombinationBacktrackingRecursive(StringBuilder buffer, int leftP, int rightP, List<String> result, int N) {
            // base case
            if (leftP >= N && rightP >= N) {
                result.add(buffer.toString());
            }
            // recursive
            if (leftP < N) { // add left brace
                buffer.append("{"); // add
                allBraceCombinationBacktrackingRecursive(buffer, leftP + 1, rightP, result, N);
                buffer.setLength(buffer.length() - 1); // remove
            }
            if (rightP < leftP) { // add right brace
                buffer.append("}"); // add
                allBraceCombinationBacktrackingRecursive(buffer, leftP, rightP + 1, result, N);
                buffer.setLength(buffer.length() - 1); // remove
            }
        }

        public List<String> allBraceCombinationBrackTracking(int N) {
            List<String> result = new ArrayList<>();
            allBraceCombinationBacktrackingRecursive(new StringBuilder(), 0, 0, result, N);
            return result;
        }


        public static void main(String[] args) {
            BalancedBraces balancedBraces = new BalancedBraces();
            Collection<String> strings = balancedBraces.braceCombination(3);
            System.out.println("dp:");
            strings.forEach(System.out::println);

            System.out.println("backtracking:");
            strings = balancedBraces.allBraceCombinationBrackTracking(3);
            strings.forEach(System.out::println);
        }

        /*Solve N-Queens Problem*/
        static class NQueens {

            public boolean isValidMove(int proposedRow, int proposedCol, List<Integer> prevQueenLocations) {
                for (int idx = 0; idx < proposedRow; idx++) { // check against each queen before current queen.
                    int prevQueenRow = idx;
                    int prevQueenCol = prevQueenLocations.get(idx);
                    // check for row and col
                    if (prevQueenCol == proposedCol) {
                        return false;
                    }
                    int diagonalOffset = proposedRow - prevQueenRow;
                    if (prevQueenCol == proposedCol - diagonalOffset || prevQueenCol == proposedCol + diagonalOffset) {
                        return false;
                    }
                }
                return true;
            }

            public void solveNQueenRecursive(int N, int proposedRow, List<Integer> queenLocations, List<List<Integer>> solution) {
                //base case
                if (proposedRow == N) {
                    solution.add(new ArrayList<>(queenLocations));
                    return;
                }

                // recursive case
                for (int proposedCol = 0; proposedCol < N; proposedCol++) { // try to place queen at each col of the row
                    if (isValidMove(proposedRow, proposedCol, queenLocations)) {
                        queenLocations.set(proposedRow, proposedCol); // add
                        solveNQueenRecursive(N, proposedRow + 1, queenLocations, solution); // try for next row
                        // no need to remove as in isValidMove method we only check for queens before proposed row.
                    }
                }
            }

            public void solveNQueen(int N) {
                List<Integer> queenLocations = new ArrayList<>(N);
                for (int idx = 0; idx < N; idx++) {
                    queenLocations.add(-1);
                }
                List<List<Integer>> solution = new ArrayList<>();
                solveNQueenRecursive(N, 0, queenLocations, solution);
                System.out.println("Solutions: " + solution.size());
                for (int idx = 0; idx < solution.size(); idx++) {
                    System.out.println("row: " + idx + ", col: " + solution.get(idx));
                }
            }

            public static void main(String args[]) {
                NQueens nQueens = new NQueens();
                nQueens.solveNQueen(4);
            }
        }
    }

    /*Find K-Sum Subsets*/
    static class KSumSubset {
        /*approach using binary representation*/
        public int getBit(int num, int bitPosition) {
            if (num == 0) {
                return 0;
            }
            int shiftOne = (1 << bitPosition);
            return (num & shiftOne) == 0 ? 0 : 1;
        }

        public List<List<Integer>> getSubSets1(List<Integer> integerList, int kSum) {
            List<List<Integer>> subsets = new ArrayList<>();
            double numOfSubsetPossible = Math.pow(2d, (double) integerList.size());
            int subsetSize = integerList.size();
            for (int idx1 = 0; idx1 < numOfSubsetPossible; idx1++) { // 0 zero so N subsets - 000, 001, 011 ...
                //StringBuilder sbr = new StringBuilder();
                int sum = 0;
                List<Integer> subset = new ArrayList<>();
                for (int bitIndex = 0; bitIndex < subsetSize; bitIndex++) {
                    int bit = getBit(idx1, bitIndex);
                    if (bit == 1) { // binary 1 - include
                        //sbr.append(integerList.get(bitIndex)).append(",");
                        sum = sum + integerList.get(bitIndex);
                        if(sum > kSum) {
                            break;
                        }
                        subset.add(integerList.get(bitIndex));
                    }
                }
                if(sum == kSum) {
                    subsets.add(subset);
                }
                //System.out.println("'"+sbr+"'");
            }
            return subsets;
        }

        public void subsetsRecursive(List<Integer> intSet, int targetSum, List<Integer> subList, int currIndex, List<List<Integer>> result) {
            // base
            if(targetSum == 0) {
                result.add(new ArrayList<>(subList));
                return;
            }
            if(currIndex >= intSet.size() || targetSum < 0) {
                return;
            }

            // recursive
            int currElem = intSet.get(currIndex);
            // exclude
            List<Integer> lst = new ArrayList<>(subList);
            subsetsRecursive(intSet, targetSum, lst, currIndex+1, result);
            // include
            lst.add(currElem);
            subsetsRecursive(intSet, targetSum-currElem, lst, currIndex+1, result);
        }

        public List<List<Integer>> subSets2(List<Integer> inSet, int K) {
            List<List<Integer>> result = new ArrayList<>();
             if(inSet == null || inSet.isEmpty()) {
                 return result;
             }
             subsetsRecursive(inSet, K, new ArrayList<>(), 0, result);
             return result;
        }

        public static void main(String[] args) {
            KSumSubset sumSubset = new KSumSubset();
            // initializing vector
            int[] myints = {8, 13, 3, 22, 17, 39, 87, 45, 36};
            List<Integer> vec = new ArrayList<Integer> ();
            for (Integer i: myints) {
                vec.add(i);
            }

            // computing subsets
            List<List<Integer>> subsets;
            subsets = sumSubset.subSets2(vec, 125);

            System.out.print("[");
            // printing subsets
            for (int i = 0; i < subsets.size(); ++i) {
                System.out.print("{");
                for (Integer it: subsets.get(i)) {
                    System.out.print(it + ", ");
                }
                System.out.print("} ");
            }
            System.out.print("]");
        }


    }


}
