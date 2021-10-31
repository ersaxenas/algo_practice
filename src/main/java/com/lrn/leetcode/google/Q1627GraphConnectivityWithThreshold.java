package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1627GraphConnectivityWithThreshold {
    /*
    * pd: https://leetcode.com/problems/graph-connectivity-with-threshold/
    * appr: https://leetcode.com/problems/graph-connectivity-with-threshold/discuss/899462/Extremely-useful-Union-Find-class-Feel-Free-to-Reuse
    * union find approach
    * */

    static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n+1];
            size = new int[n+1];
            for(int idx=0; idx <= n; idx++) {
                parent[idx] = idx; // each node is parent of its own
            }
            Arrays.fill(size, 1);
        }

        public int find(int a) {
            if(parent[a] != a) {
                parent[a] = find(parent[a]); // find parent of parent
            }
            return parent[a];
        }

        public boolean connect(int a, int b) {
            int parentOfA = find(a);
            int parentOfB = find(b);

            if(parentOfA == parentOfB) {
                return false; // already connected to same parent
            }

            if(size[parentOfA] > size[parentOfB]) {
                // swap parents of a and b so that parentOfA points to a group with less size
                // and b points to group with large size
                int tmp = parentOfA;
                parentOfA = parentOfB;
                parentOfB = tmp;
            }
            // merge two groups - smaller group is merging in to larger one - set parent of small group to larger group
            parent[parentOfA] = parentOfB; // now b (larger group) is parent of a ( small group)
            size[parentOfB] = size[parentOfB] + size[parentOfA];
            return true;
        }
        public boolean isConnected(int a , int b) {
            return find(a) == find(b);
        }
    }

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        // prepare UF DS
         UnionFind  uf = new UnionFind(n);
        for(int parent=threshold+1; parent <=n; parent++) {
            // parent = threashold + (1, 2, 3, 4 ... n)
            int mulp = 1; // m = 1, 2 , 3, 4, 5
            int node = parent * mulp;
            while( node <= n) { // if node is in range
                uf.connect(parent, node); // connect parent and node
                mulp++;
                node = parent * mulp;
            }
        }
        List<Boolean> result = new ArrayList<>();
        for(int[] arr: queries) {
            final boolean connected = uf.isConnected(arr[0], arr[1]);
            result.add(connected);
        }
        return result;
    }




    public static void main(String[] args) {
        Q1627GraphConnectivityWithThreshold sol = new Q1627GraphConnectivityWithThreshold();
        LsUtil.printList(sol.areConnected(6, 2, new int[][] {{1,4},{2,5},{3,6}}));
    }

}
