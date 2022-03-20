package com.lrn.leetcode.google;

public class Q086PartitionList {
    /* 2022-01-09T12:28:48.323Z
    * https://leetcode.com/problems/partition-list/
    * */

    /*2022-01-09T12:38:22.671Z
    * simple to understand
    * */

    public ListNode partition2(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode s1 = new ListNode(), curr1 = s1;
        ListNode s2 = new ListNode(), curr2 = s2;
        ListNode curr = head;
        while(curr != null) {
            if(curr.val < x) {
                curr1.next = curr;
                curr1 = curr1.next;
            } else {
                curr2.next = curr;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        curr2.next = null;
        curr1.next = s2.next;
        return s1.next;
    }
    
    /*faster*/
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
