package com.lrn.leetcode.google;

import java.util.ArrayDeque;

public class Q111MinimumDepthofBinaryTree {
    /*2022-02-04T11:26:41.776Z
    * pd: https://leetcode.com/problems/balanced-binary-tree
    * assm: no. of nodes in tree < 10000, best time sol,
    * appr: BFS or plain logic
    * test cases:
    * */

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return findMinDepth(root);
    }
    /*Iterate the tree: at each node return the min height
    * if left and/or right is null then min height is left or right height + 1 (leaf node)
    * else return the min(left,right) +1;
    * Idea is to carry return min height at any node;
    * runtime 5 ms
    * */
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth2(root.left);
        int right = minDepth2(root.right);
        return (left == 0 || right == 0) ? left + right +1 : 1 + Math.min(left,right);
    }

    /*BFS : runtime 0 ms*/
    public int findMinDepth(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            for(int idx =0; idx < qsize; idx++ ) {
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null) return level;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            level++;
        }
        return level;
    }



}
