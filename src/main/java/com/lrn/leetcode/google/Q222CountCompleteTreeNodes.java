package com.lrn.leetcode.google;

import java.util.LinkedList;
import java.util.Queue;

public class Q222CountCompleteTreeNodes {
    /*
    * pd: Given a complete binary tree, count the number of nodes.
Note:
Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
    * assm: max nodes 10000, best time sol
    * appr: bfs
    * test cases:
    * Input:
    1
   / \
  2   3
 / \  /
4  5 6
    output 6
    * */

    // O(long2n & long2n)
    public int countNodes2(TreeNode node) {
        if(node == null) {return 0;}
        int leftHeight = getLeftHeight(node);
        int rightHeight = getRightHeight(node);

        if(leftHeight == rightHeight) {
            return (int) (Math.pow(2,leftHeight) -1);
        }
        return 1 + countNodes2(node.left) + countNodes2(node.right);
    }


    public int getLeftHeight(TreeNode node) {
        int count=0;
        while(node != null) {
            count++;
            node = node.left;
        }
        return count;
    }

    public int getRightHeight(TreeNode node) {
        int count =0;
        while(node != null) {
            count++;
            node = node.right;
        }
        return count;
    }

    // O(n) sol
    public int countNodes(TreeNode root) {
        int nodeCount = 0;
        if(root == null) {
            return nodeCount;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            nodeCount = nodeCount + qsize;
            for(int idx=0; idx<qsize; idx++) {
                final TreeNode treeNode = queue.poll();
                if(treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if(treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
        }
       return nodeCount;
    }

    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        TreeNode n3 = new TreeNode(3);
        n3.left = n6;

        TreeNode n2 = new TreeNode(2);
        n2.left = n4;
        n2.right = n5;

        TreeNode n1 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        Q222CountCompleteTreeNodes sol = new Q222CountCompleteTreeNodes();
        System.out.println(sol.countNodes2(n1));

    }


}
