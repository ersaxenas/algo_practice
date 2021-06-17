package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q103BinaryTreeZigzagLevelOrder {
    /* https://leetcode.com/problems/maximum-depth-of-binary-tree/
    * pd: Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
    * assm: best time sol, no. of nodes < 1000,
    * appr: bfs
    *         3
    *      /    \
    *     9      20
    *   /   \   /  \
    *  10  11  15   7
    *  [3],[20,9],[7,15,11,10]
    *   Level order traversal -> send right child first to queue then left child
    * */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = true;
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            List<Integer> lst = new ArrayList<>();

            for(int idx=0; idx<qsize; idx++) {
                TreeNode node = queue.poll();
                if(reverse) {
                    lst.add(0,node.val);
                } else {
                    lst.add(node.val);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
                if(node.left != null) {
                    queue.add(node.left);
                }
            }
            result.add(lst);
            reverse = !reverse;
        }
        return result;
    }

    public static void main(String[] args) {
       Q103BinaryTreeZigzagLevelOrder sol = new Q103BinaryTreeZigzagLevelOrder();
       TreeNode n3 = new TreeNode(3);
       TreeNode n20 = new TreeNode(20);
       TreeNode n9 = new TreeNode(9);
       TreeNode n15 = new TreeNode(15);
       TreeNode n7 = new TreeNode(7);

       n20.left = n15;
       n20.right = n7;

       n3.right = n20;
       n3.left = n9;
       LsUtil.printListOfList(sol.zigzagLevelOrder(n3));
    }

}
