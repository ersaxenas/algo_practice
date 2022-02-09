package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q105ConstructBtPreoderAndInorder {
    /*2022-01-30T18:23:24.783Z
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * intuition:
     *                3
     *            /      \
     *          9          20
     *        /   \       /  \
     *      5     10    15     7
     *
     *      0  1  2   3  4   5   6
     *  IO: 5  9  10  3  15  20  7
     *  PO: 3  9  5   10 20  15  7
     *  1. first node of the PO is the root of the tree ex. 3
     *  2. using the location of root node in IO we can say that elem to the left of root node will form left sub tree
     *     and right side nodes will form right sub tree.
     *        <  left subtree >  root    <  right sub tee  >
     *     Ex:     5  9  10        3     15  20  7
     * 3. now we know that there are x nodes in the left / right subtree Ex. [5 9 10] total 3
     * 4. using x node we can take out left nodes Ex: current root + 3 nodes -> [9 5 10]
     * 5. now once again we have two arrays with PO an IO order and we can repeat steps 1 to 4 to build subtrees recursively.
     *
     *      0  1   2  3   4  5   6
     *  IO: 5  9  10  3  15  20  7
     *  PO: 3  9   5 10  20  15  7
     * F(PO, 0 , 6, IO, 0, 6)
     *        <  left subtree >  root    <  right sub tee  >
     *     Ex:     5  9  10        3     15  20  7
     *      [3]. left : F( [9 5 10] , [5 9 10]) => F( [9 5 10], 1, 3,  [5 9 10], 0, 2 )
     *                     subtree root = 9 ( first node of PO)
     *                     IO LEFT: [5], PO LEFT: [5]
     *                                     subtree root = 9 ( first node of PO)
     *                                     idx = index of root (9) in inorder
     *                                     nodesleftTree = idx - inorder start index
     *                                     9.left  = F ( [5], 2 ,2 ,[5], 0, 0 ) == F(preorder, ps+1, ps+nodesleftTree, inorder, is, idx-1)
     *                                     9.right = F ( [10], 3, 3, [10], 2, 2) == F(preorder, ps+nodesleftTree+1, pe, inorder, idx+1, ie)
     *
     * */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length==0) {
            return new TreeNode();
        }
        HashMap<Integer,Integer> indexmap = new HashMap<>();
        for(int idx=0; idx< inorder.length; idx++) {
            indexmap.put(inorder[idx], idx);
        }
        return btpo(preorder,0, preorder.length-1, inorder, 0, inorder.length-1, indexmap);
    }

    public TreeNode btpo(int[] preorder, int ps, int pe, int[] inorder, int is, int ie, Map<Integer, Integer> ioIndexMap) {
        //base case
        if(ps > pe || is > ie) {
           return null;
        }
        // recursive case
        TreeNode subTreeRoot = new TreeNode(preorder[ps]); // root == first node of PO
        int indexOfRootIO = ioIndexMap.get(preorder[ps]);
        int nodesLeft = indexOfRootIO - is;
        subTreeRoot.left = btpo(preorder, ps+1, ps+nodesLeft, inorder, is, indexOfRootIO-1, ioIndexMap);
        subTreeRoot.right = btpo(preorder, ps+nodesLeft+1, pe, inorder, indexOfRootIO+1, ie, ioIndexMap);
        return subTreeRoot;
    }

    public static void main(String[] args) {
        Q105ConstructBtPreoderAndInorder sol = new Q105ConstructBtPreoderAndInorder();
        //TreeNode treeNode = sol.buildTree(new int[]{3, 9, 5, 10, 20, 15, 7}, new int[]{5, 9, 10, 3, 15, 20, 7});
        //System.out.println(treeNode);
        TreeNode treeNode = sol.buildTree(new int[]{1,2}, new int[]{2,1});
        System.out.println(treeNode);
    }

}
