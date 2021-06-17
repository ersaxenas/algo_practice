package com.lrn.leetcode.google;

public class Q86PartitionList {
    /*
    * https://leetcode.com/problems/partition-list/
    * */

    public ListNode partition(ListNode head, int x) {


        if(head == null || head.next == null) {
            return head;
        }
        ListNode s1 = new ListNode();
        ListNode s2 = new ListNode();
        s1.next = head;
        ListNode prv=s1, curr = head, curr2 = s2, tmp;
        while(curr != null) {
            while(curr != null && curr.val >= x) {
                prv = curr;
                curr = curr.next;
            }
            if(curr != null) {
                tmp = curr;
                prv.next = curr.next;
                curr = curr.next;
                curr2.next = tmp;
                curr2 = tmp;
            }
        }
        if(s1.next != null) {
            curr2.next = s1.next;
        }
        return s2.next;

    }
}
