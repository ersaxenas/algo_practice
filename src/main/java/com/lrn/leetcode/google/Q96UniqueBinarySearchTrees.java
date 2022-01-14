package com.lrn.leetcode.google;

public class Q96UniqueBinarySearchTrees {

   /* 2022-01-13T07:20:33.349Z
   https://leetcode.com/problems/unique-binary-search-trees
   * pd: Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
Example:
Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   *
   * assm: 1 < N 20, best time sol
   * Appr:
   *
   * */

    /*
Hope it will help you to understand :

    n = 0;     null

    count[0] = 1


    n = 1;      1

    count[1] = 1


    n = 2;    1__       			 __2
    		      \					/
    		     count[1]	+   count[1]

    count[2] = 1 + 1 = 2



    n = 3;    1__				      __2__	                   __3
    		      \		            /       \			      /
    		  count[2]	 +	  count[1]  *  count[1]	 +	count[2]

    count[3] = 2 + 1 + 2  = 5



    n = 4;    1__  					__2__					   ___3___
    		      \				 /        \					  /		  \
    		  count[3]	 +	 count[1]  *  count[2]	 +	  count[2]  *  count[1]

                 __4
               /
           count[3]

    count[4] = 5 + 2 + 2 + 5 = 14

And  so on...
*/
    public int numTree(int n) {
        int[] count = new int[n+1];
        count[0] = count[1] = 1;
        for(int nodes =2; nodes<=n; nodes++) {
            for(int root=1; root<=nodes; root++) {// take each index as root
                count[nodes] =  count[nodes] + (count[root-1] * count[nodes-root]);
            }
            LsUtil.printArray(count);
        }
        return count[n];
    }

    public static void main(String[] args) {
        Q96UniqueBinarySearchTrees sol = new Q96UniqueBinarySearchTrees();
        System.out.println(sol.numTree(10));
    }

}
