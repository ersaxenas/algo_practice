package com.lrn.leetcode.google;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q297SerializeAndDeserializeBinaryTree {
    /*
    * pd: Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
    * assm: best time sol, max tree nodes < 10000, tree val 1 < int.max
    * appr:
    * test cases:
    *
    * */

    public String serialize(TreeNode root) {
        StringBuilder buffer = new StringBuilder();
        serialize(root, buffer);
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    public void serialize(TreeNode node, StringBuilder buffer) {
        if (node == null) {
            buffer.append("null").append(",");
        } else {
            buffer.append(node.val).append(",");
            serialize(node.left, buffer);
            serialize(node.right, buffer);
        }
    }

    public TreeNode deserialize(String str) {
        List<String> dataList = new LinkedList<>(Arrays.asList(str.split(",")));
        return deserializeRec(dataList);
    }

    public TreeNode deserializeRec(List<String> lst) {
        if (lst.get(0).equals("null")) {
            lst.remove(0); // remember to remove
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(lst.remove(0)));
        node.left = deserializeRec(lst);
        node.right = deserializeRec(lst);
        return node;
    }

    public static void main(String[] args) {
        Q297SerializeAndDeserializeBinaryTree sol = new Q297SerializeAndDeserializeBinaryTree();
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        n20.left = n15;
        n20.right = n7;

        n3.right = n20;
        n3.left = n9;
        String serialize = sol.serialize(n3);
        System.out.println(serialize);
        TreeNode deserialize = sol.deserialize(serialize);
        System.out.println("deserialize = " + deserialize);
    }
}
