package com.lrn.leetcode.google;

public class Q019RemoveNthNodeFromEndList {

    /*
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * PD: Given a linked list, remove the n-th node from the end of list and return its head.
     * Asmp: non null elem, size 1 < int max, best time solution
     * Ach: take two pointers pointing to head.
     *      now move 2nd pointer fwd by n=2
     *      1->2->3->4->5->6->7->8->null
     *     p1     p2
     *     now move both fwd till p2.next == null
     *     now remove elem next to p1 by p1.next = p1.next.next;
     * */

    public ListNode removeNthFromEnd(ListNode head, int n) {
           if(head == null || n ==0) {
               return head;
           }
           ListNode startNode = new ListNode(0);
           startNode.next = head;
           ListNode p1 = startNode, p2 = startNode;
           for(int idx=0; p2!=null && idx<n+1; idx++) {
               p2 = p2.next;
           }
           while(p2 != null) {
               p2 = p2.next;
               p1 = p1.next;
           }
           p1.next = p1.next.next;
           return startNode.next;

    }

    static void printList(ListNode node) {
        StringBuilder sbr = new StringBuilder();
        while(node != null) {
            sbr.append(node.val).append("->");
            node = node.next;
        }
        System.out.println("["+sbr.toString()+"]");
    }

    static ListNode createLs() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        return n1;
    }

    public static void main(String[] args) {
        Q19RemoveNthNodeFromEndList q19RemoveNthNodeFromEndList = new Q19RemoveNthNodeFromEndList();
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 0));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 1));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 2));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 3));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 4));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 5));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(createLs(), 6));
        printList(q19RemoveNthNodeFromEndList.removeNthFromEnd(new ListNode(1), 1));
    }




}
