package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q107BinaryTreeLevelOrderTraversal2 {

    /*
     * pd: https://leetcode.com/problems/binary-tree-level-order-traversal-ii
     * assm: no. of nodes < 10000, tree val < int max, no null val nodes, best time sol
     * approach: BFS
     * test cases:
     * */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            int currQSize = deque.size();
            List<Integer> levelNodeList = new ArrayList<>();
            for(int idx=0; idx<currQSize; idx++) {
                TreeNode node = deque.poll();
                levelNodeList.add(node.val);
                if(node.left != null) deque.offer(node.left);
                if(node.right != null) deque.offer(node.right);
            }
            result.add(0,levelNodeList);
        }
        return result;
    }

}
