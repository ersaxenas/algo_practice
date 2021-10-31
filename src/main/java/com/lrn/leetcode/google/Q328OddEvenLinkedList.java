package com.lrn.leetcode.google;

public class Q328OddEvenLinkedList {
    /* https://leetcode.com/problems/odd-even-linked-list
    * pd: Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
    * assm: list len < 10000, best time sol
    * appr:
    * */

    public ListNode oddEventList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode even = head.next;
        ListNode p1 = head, p2 = even;
        while(p2 != null ) {
            p1.next = p2.next;
            p1 = p2;
            p2 = p2.next;
        }
        p1= head;
        while(p1.next!=null) p1 = p1.next;
        p1.next = even;
        return head;
    }

    public static void main(String[] args) {
        Q328OddEvenLinkedList sol = new Q328OddEvenLinkedList();

       ListNode n1 = new ListNode(1);
       n1.add(2).add(3).add(4).add(5);
       n1 = sol.oddEventList(n1);
        System.out.println("done");

    }

}
