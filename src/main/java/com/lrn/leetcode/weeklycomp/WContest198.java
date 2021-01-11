package com.lrn.leetcode.weeklycomp;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Contest198 {

    static class C5465 {
        /*
         * https://leetcode.com/contest/weekly-contest-198/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
         *
         * */

        public int[] countSubTrees(int n, int[][] edges, String labels) {
            HashMap<Integer, Set<Integer>> edgemap = new HashMap<>();
            for (int idx = 0; idx < n; idx++) {
                edgemap.put(idx, new HashSet<>()); // edgemap with all the nodes
            }
            for (int row = 0; row < edges.length; row++) {
                edgemap.get(edges[row][0]).add(edges[row][1]); // add all edges
                edgemap.get(edges[row][1]).add(edges[row][0]); // add all edges
            }
            int[] result = new int[n];
            for (int idx = 0; idx < result.length; idx++) {
                if (result[idx] == 0) { // unvisited node
                    dfs2(0, edgemap, labels, new HashSet<>(), new HashMap<>(), result);
                }
            }
            return result;
        }

        List<Character> dfs(int node, HashMap<Integer, Set<Integer>> edges, String labels, int[] result, Set<Integer> seen) {
            List<Character> charlist = new ArrayList<>();
            int freq = 1;
            seen.add(node);
            if (edges.containsKey(node)) {
                for (Integer child : edges.get(node)) {
                    if (!seen.contains(child)) {
                        charlist.addAll(dfs(child, edges, labels, result, seen));
                    }
                }
                freq = freq + getCharFreq(charlist, labels.charAt(node));
            }
            result[node] = freq;
            charlist.add(labels.charAt(node));
            return charlist;
        }

        int getCharFreq(List<Character> charlist, Character ch) {
            int cnt = 0;
            for (Character lch : charlist) {
                if (ch.equals(lch)) {
                    cnt++;
                }
            }
            return cnt;
        }

        void dfs2(int node, HashMap<Integer, Set<Integer>> edges, String labels, Set<Integer> seen, Map<Character, List<Integer>> parents, int[] result) {
            seen.add(node);
            result[node] += 1;
            char nodechar = labels.charAt(node);
            for (Integer p : parents.getOrDefault(nodechar, new ArrayList<>())) {
                result[p] += 1;
            }

            List<Integer> par = parents.getOrDefault(nodechar, new ArrayList<>());
            par.add(node);
            parents.put(nodechar, par);

            if (edges.containsKey(node)) {
                for (Integer child : edges.get(node)) {
                    if (!seen.contains(child)) {
                        dfs2(child, edges, labels, seen, parents, result);
                    }
                }
            }
            par.remove(par.size() - 1);
        }

        /*---------------------------------------*/
        class TNode {
            public int index;
            public char ch;
            public TNode parent;
            public Set<Character> parents = new HashSet<>();
            public List<TNode> child = new ArrayList<>();

            public TNode(int index, char ch) {
                this.index = index;
                this.ch = ch;
            }

            public String toString() {
                return "[" + index + "," + ch + "]";
            }
        }

        public int[] countSubTrees2(int n, int[][] edges, String labels) {
            TNode[] nodes = new TNode[n];
            int[] result = new int[n];
            Arrays.fill(result, 1);
            for (int idx = 0; idx < n; idx++) {
                nodes[idx] = new TNode(idx, labels.charAt(idx));
            }
            for (int[] ed : edges) {
                if (ed[0] < ed[1]) {
                    addChild(nodes[ed[0]], nodes[ed[1]], result);
                } else {
                    addChild(nodes[ed[1]], nodes[ed[0]], result);
                }
            }
            return result;
        }

        public void addChild(TNode parent, TNode child, int[] result) {
            if (child.parent != null) {
                addChild(child, parent, result);
                return;
            }
            child.parent = parent;
            child.parents = new HashSet<>(parent.parents);
            child.parents.add(parent.ch);
            TNode node = child.parent;
            if (child.parents.contains(child.ch)) {
                while (node != null) {
                    if (node.ch == child.ch) {
                        result[node.index] += 1;
                    }
                    node = node.parent;
                }
            }
        }

        /*---------------------------------------*/
        public int[] countSubTrees3(int n, int[][] edges, String labels) {
            TNode[] nodes = new TNode[n];
            int[] result = new int[n];
            for (int idx = 0; idx < n; idx++) {
                nodes[idx] = new TNode(idx, labels.charAt(idx));
            }
            for (int[] ed : edges) {
                TNode p, c;
                if (ed[0] < ed[1] && nodes[ed[1]].parent == null) {
                    p = nodes[ed[0]];
                    c = nodes[ed[1]];
                } else {
                    p = nodes[ed[1]];
                    c = nodes[ed[0]];
                }
                c.parent = p;
                p.child.add(c);
            }
            dfs2(nodes[0], result);
            return result;
        }

        public Map<Character, Integer> dfs2(TNode node, int[] result) {
            Map<Character, Integer> map = new HashMap<>();
            if(node == null) {return map;}
            map.put(node.ch,1);
            // for each child
            for(TNode child: node.child) {
                Map<Character, Integer> cmap = dfs2(child, result);
                for(Character key: cmap.keySet()) {
                    map.put(key, map.getOrDefault(key,0) + cmap.get(key));
                }
            }
            result[node.index] = map.get(node.ch);
            return map;
        }



        public static void main(String[] args) {
            C5465 sol = new C5465();
            LsUtil.printArray(sol.countSubTrees(4, new int[][]{{0, 2}, {0, 3}, {1, 2}}, "aeed"));
            LsUtil.printArray(sol.countSubTrees3(4, new int[][]{{0, 2}, {0, 3}, {1, 2}}, "aeed"));
            LsUtil.printArray(sol.countSubTrees2(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd"));
            LsUtil.printArray(sol.countSubTrees3(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd"));
//            LsUtil.printArray(sol.countSubTrees2(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, "bbbb"));
//            long tt = System.nanoTime();
//            int[] res;
//            try {
//                res = sol.countSubTrees2(25, new int[][]{{4, 0}, {5, 4}, {12, 5}, {3, 12}, {18, 3}, {10, 18}, {8, 5}, {16, 8}, {14, 16}, {13, 16}, {9, 13}, {22, 9}, {2, 5}, {6, 2}, {1, 6}, {11, 1}, {15, 11}, {20, 11}, {7, 20}, {19, 1}, {17, 19}, {23, 19}, {24, 2}, {21, 24}}, "hcheiavadwjctaortvpsflssg");
//            } finally {
//                tt = System.nanoTime() - tt;
//                System.out.printf("tt = %dms%n", (tt / 1_000_000));
//            }
//            LsUtil.printArray(res);
        }
    }

    /*
     * https://leetcode.com/contest/weekly-contest-198/problems/maximum-number-of-non-overlapping-substrings/
     * */
    static class C1520 {
        class Substring {
            int start, end;
            String str;

            public Substring(int start, int end, String str) {
                this.start = start;
                this.end = end;
                this.str = str;
            }

            public String toString() {
                return str.substring(start, end + 1);
            }
        }

        public List<String> maxNumOfSubstrings(String str) {
            Set<Character> charSet = new HashSet<>();
            Map<Character, Integer> startIdx = new HashMap<>();
            Map<Character, Integer> endIdx = new HashMap<>();
            for (int idx = 0; idx < str.length(); idx++) {
                Character ch = str.charAt(idx);
                if (charSet.add(ch)) {
                    startIdx.put(ch, idx);
                }
                endIdx.put(ch, idx);
            }
            /*A substring that contains a certain character Ex: c must also contain all occurrences of c.*/
            List<Substring> substringList = new ArrayList<>();
            for (Character ch : charSet) {
                int sr = startIdx.get(ch);
                int er = endIdx.get(ch);
                boolean charInBtwOutOfRange = false;
                // chars in range
                for (int idx = sr + 1; idx < er; idx++) {
                    char charcur = str.charAt(idx);
                    if (startIdx.get(charcur) < sr) { // char out of range  x sr... er. we are only trying to include range for char ch
                        charInBtwOutOfRange = true;
                        break;
                    }
                    er = Math.max(er, endIdx.get(charcur)); // we can expand end range since it wil include end range of char ch
                }
                if (!charInBtwOutOfRange) { // all the char in range are withing the sr and er
                    substringList.add(new Substring(sr, er, str));
                }
            }
            /* NOW TAKING CARE OF BELOW CONDITION
             * The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
             * */
            Collections.sort(substringList, (e1, e2) -> e1.end - e2.end); // so that smaller strings are at the start of the list
            List<String> result = new ArrayList<>();
            result.add(substringList.get(0).toString());
            int lastEnd = substringList.get(0).end;
            substringList.remove(0);
            for (Substring substring : substringList) {
                if (substring.start < lastEnd) {
                    continue;
                } // overlapping string so ignore
                result.add(substring.toString());
                lastEnd = substring.end;
            }
            return result;
        }

        public static void main(String[] args) {
            C1520 sol = new C1520();
            LsUtil.printList(sol.maxNumOfSubstrings("adefaddaccc"));
        }
    }

    static class C1521 {

        /*
         * https://leetcode.com/contest/weekly-contest-198/problems/find-a-value-of-a-mysterious-function-closest-to-target/
         * */

        public int closestToTarget(int[] arr, int target) {
            int res = Integer.MAX_VALUE;
            int and;
            for (int idx = 0; idx < arr.length; idx++) {
                and = arr[idx];
                for (int r = idx; r < arr.length; r++) {
                    and = and & arr[r];
                    res = Math.min(res, Math.abs(and - target));
                    if (res == 0) return res;
                    if (and <= target) {
                        break;
                    } // since & operation will result in smallest number so continuing will result in widening the gap target and sum
                }
                if (and > target) {
                    break;
                } // now there will be less numbers so & operation will not result in smaller number.
            }
            return res;
        }

        public static void main(String[] args) {
            C1521 sol = new C1521();
            System.out.println(sol.closestToTarget(new int[]{5, 89, 79, 44, 45, 79}, 965));
        }

    }


}


