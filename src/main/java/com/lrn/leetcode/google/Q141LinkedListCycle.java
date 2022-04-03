package com.lrn.leetcode.google;

public class Q141LinkedListCycle {
    /*2022-04-02T11:37:32.280Z
    * pd: https://leetcode.com/problems/linked-list-cycle/
    * assm: list nodes < 1000, best time sol
    * appr: walker/slow, runner/fast pointer
    * test cases:
    *
    * */

    public boolean hasCycle(ListNode head) {
        ListNode slow=head, fast=head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

}
