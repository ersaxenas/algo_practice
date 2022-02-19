package com.lrn.practice;

import com.lrn.leetcode.google.ListNode;

public class ReverseLs {


    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start= head, end = head, next = null;
        for(int idx=0; idx<k-1; idx++) {
            if(end != null) end = end.next;
        }
        if(end == null) {
            return head;
        }
        next = end.next; // next start of sublist
        end = reverse(start,k);
        start.next = next;
        for(int idx=0; idx<k-1 && next != null; idx++) {
            next = next.next;
        }
        if(next == null) {
            return end;
        }
        next.next = reverseKGroup(next.next,k);
       return end;

    }


    public ListNode reverse(ListNode node, int K) {
        ListNode curNode = node, prev = null, next;
        while(curNode != null && K > 0) {
            next = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = next;
            K--;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReverseLs sol = new ReverseLs();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode result = sol.reverseKGroup(head, 2);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
