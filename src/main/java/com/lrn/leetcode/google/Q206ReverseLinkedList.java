package com.lrn.leetcode.google;

public class Q206ReverseLinkedList {

    /*2022-07-12T09:50:11.287Z
    * pd: Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
    * */

    public ListNode reverseListItr(ListNode head) {
         ListNode currNode=head, prev = null, tmp;
         while(currNode != null) {
             tmp = currNode.next;
             currNode.next = prev;
             prev = currNode;
             currNode = tmp;
         }
         return prev;
    }

    public ListNode reverseListRec(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
       return reverse(head,null);
    }
    public ListNode reverse(ListNode prev, ListNode node) {
        ListNode tmp = node.next;
        node.next = prev;
        return (tmp != null) ? reverse(node, tmp) : node;
    }

    public static void main(String[] args) {
        Q206ReverseLinkedList sol = new Q206ReverseLinkedList();
        ListNode n1 = new ListNode(1);
        n1.add(2).add(3).add(4).add(5);
        ListNode listNode = sol.reverseListRec(n1);
        while(listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }





}
