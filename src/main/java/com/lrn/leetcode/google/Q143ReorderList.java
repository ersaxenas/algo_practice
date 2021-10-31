package com.lrn.leetcode.google;

public class Q143ReorderList {
    /*
    * pd:
    *
    *
    * */

    public void reorderList(ListNode head) {
        ListNode slow=head, fast = head, pre=null;
        while(fast !=null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p1, p2;
        if(fast == null) {
            p2 = slow;
            pre.next = null;
        } else {
            p2 = slow.next;
            slow.next = null;
        }
        ListNode curr = p2, tmp;
        pre = null;
        while(curr != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        p1 = head; p2 = pre;
        ListNode dummy = new ListNode();
        curr = dummy;
        while(p1 != null || p2 != null) {
            if(p1 != null) {
                curr.next = p1;
                p1 = p1.next;
                curr = curr.next;
            }
            if(p2 != null) {
                curr.next = p2;
                p2 = p2.next;
                curr = curr.next;
            }
        }
    }



}
