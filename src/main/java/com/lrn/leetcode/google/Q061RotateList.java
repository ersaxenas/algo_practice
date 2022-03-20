package com.lrn.leetcode.google;

public class Q061RotateList {

    /*2021-12-28T13:20:40.693Z
    *https://leetcode.com/problems/rotate-list/
    * Since n may be a large number compared to the length of list. So we need to know the length of linked list.After that, move the list after the (l-n%l )th node to the front to finish the rotation.
Ex: {1,2,3} k=2 Move the list after the 1st node to the front
Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node to the front.
So the code has three parts.
Get the length
Move to the (l-n%l)th node
3)Do the rotation
    * */

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next== null|| k <= 0) {
            return head;
        }

        int len =1;
        ListNode node=head,tail,prv=null;
        while(node.next != null) {
            len++;
            node = node.next;
        }
        tail = node;
        node = head;
        int breakpoint = len - (k%len);
        if(breakpoint == len) return head;
        for(int idx=0; idx<(breakpoint) && node != null; idx++) {
            prv = node;
            node = node.next;
        }
        tail.next = head;
        prv.next = null;
        return node;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
        Q61RotateList sol = new Q61RotateList();
        final ListNode listNode = sol.rotateRight(n1, 2);
        System.out.println(listNode.val);

    }




}
