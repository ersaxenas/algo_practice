package com.lrn.leetcode.google;

public class Q109ConvertListToBinaryTree {
    /*2022-02-03T13:25:01.514Z
     *Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     * Input: head = [-10,-3,0,5,9]
     * Output: [0,-3,9,-10,null,5]
     * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
     * assm; non null elem, no. of elem < 10000, best time sol.
     * appr: elem at the mid will become root and break the list in to two left - root - right
     *       now left part will make left subtree and right part will make right subtree.
     * */

    public TreeNode sortedListToBST(ListNode head) {
          if(head == null) {
              return null;
          }
          SplitList sl = splitListInTwo(head);
          TreeNode root = new TreeNode(sl.middle.val);
          if(sl.leftHead != null) {
              root.left = sortedListToBST(sl.leftHead);
          }
          if(sl.rightHead != null) {
              root.right = sortedListToBST(sl.rightHead);
          }
          return root;
    }

    class SplitList {
        ListNode leftHead, middle, rightHead;
    }

    public SplitList splitListInTwo(ListNode head) {
        SplitList sl = new SplitList();
        if(head.next == null) {
            sl.middle = head;
            return sl;
        }
        ListNode slow=head, fast=head,ps=null;
        while(fast != null && fast.next != null) {
            ps = slow;
            slow = slow.next;
            fast = fast.next.next;

        }
        sl.middle = slow;
        sl.leftHead = head;
        sl.rightHead = slow.next;
        ps.next = null;
        slow.next = null;
        return sl;
    }

    public static void main(String[] args) {
        Q109ConvertListToBinaryTree sol = new Q109ConvertListToBinaryTree();
        ListNode node = new ListNode(-10);
        node.add(-3).add(0).add(5).add(9);
        TreeNode treeNode = sol.sortedListToBST(node);
        System.out.println(treeNode.val);
    }

}
