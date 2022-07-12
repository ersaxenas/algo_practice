package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Q199BinaryTreeRightSideView {
    /*2022-06-20T18:27:38.190Z
    * pd: https://leetcode.com/problems/binary-tree-right-side-view/
    * assm: tree nodes < 1000, best time sol
    * appr: bfs
    * test cases:
    *
    * */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            result.add(queue.peek().val);
            for(int idx=0; idx < qsize; idx++) {
                TreeNode node = queue.poll();
                if(node.right != null) queue.offer(node.right); // add right node first
                if(node.left != null) queue.offer(node.left);
            }
        }
        return result;
    }



}
