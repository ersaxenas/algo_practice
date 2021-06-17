package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Q102BinaryTreeLevelOrderTraversal {
    /*
     * pd: https://leetcode.com/problems/binary-tree-level-order-traversal/
     * assm: tree nodes < 10000, tree node val <= Int.max, best time sol
     * appr: BFS
     * test cases:
     * */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int dequeSize = deque.size();
            List<Integer> levelList = new ArrayList<>();
            for (int idx = 0; idx < dequeSize; idx++) {
                TreeNode node = deque.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
            result.add(levelList);
        }
        return result;
    }
}
