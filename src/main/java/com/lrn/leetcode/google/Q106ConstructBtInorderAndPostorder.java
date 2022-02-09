package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q106ConstructBtInorderAndPostorder {

    /*2022-02-03T13:14:47.586Z
    * pd: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    * assm: no. od nodes < 10000, input doesn't contain non null elements, best time sol.
    * Appraoch :
    * test cases:
    * */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null) {
            return new TreeNode();
        }
        HashMap<Integer,Integer> ioIdxMap = new HashMap<>();
        for(int idx=0; idx<inorder.length; idx++) {
            ioIdxMap.put(inorder[idx], idx);
        }

        return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length -1, ioIdxMap);

    }

    public TreeNode buildTree(int[] io, int is, int ie, int[] po, int ps, int pe, Map<Integer, Integer> ioIdxMap) {
        if(is > ie || ps > pe) return null;
        if(is == ie || ps == pe) {
            return new TreeNode(po[pe]);
        }
        TreeNode currentRoot = new TreeNode(po[pe]);
        int rootIdxIo = ioIdxMap.get(po[pe]);
        int nodeOnLeft = rootIdxIo - is;
        int nodeOnRight = ie - rootIdxIo;
        currentRoot.left = buildTree(io, is, rootIdxIo-1, po,ps, ps+nodeOnLeft-1, ioIdxMap);
        currentRoot.right = buildTree(io, rootIdxIo+1, ie, po, pe-nodeOnRight, pe-1, ioIdxMap);
        return currentRoot;
    }

    public static void main(String[] args) {
        Q106ConstructBtInorderAndPostorder sol = new Q106ConstructBtInorderAndPostorder();
        sol.buildTree(new int[] {9,3,15,20,7}, new int[] {9,15,7,20,3});
        System.out.println("done");
    }

}
