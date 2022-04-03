package com.lrn.leetcode.google;

public class Q142LinkedListCycle2 {
    /* 2022-04-02T11:50:09.014Z
    * pd: https://leetcode.com/problems/linked-list-cycle-ii
    * assm: nodes < 10000, best time sol
    * appr: slow and fast pointer
    * test cases:
    * */

    public ListNode detectCycle(ListNode head) {
        ListNode slow=head, fast=head;
        if(fast == null || fast.next == null) return null;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) break;
        }
        if(slow != fast) return null; // rem slow != fast NOT fast == null or slow == null
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
