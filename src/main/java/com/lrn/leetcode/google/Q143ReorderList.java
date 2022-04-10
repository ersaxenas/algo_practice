package com.lrn.leetcode.google;

import java.util.ArrayDeque;

public class Q143ReorderList {
    /*2022-04-09T10:13:57.079Z
    * pd: https://leetcode.com/problems/reorder-list/
    *
    *
    * */

    public void reorderList2(ListNode head) {

        ListNode slow = head, fast = head, pre = null, tmp = null;
        /*find the center of the list*/
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = null;
        /*break the list in two halves*/
        if(fast == null) { // list has event no. of nodes
            head2 = slow;
            pre.next = null;
        } else { // list has odd no. of nodes
            head2 = slow.next;
            slow.next = null;
        }
        /*put nodes of the second list in the stack*/
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        tmp = head2;
        while(tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }
        /*insert nodes in the first half*/
        pre = head;
        tmp = head.next;
        while(!stack.isEmpty()) {
            ListNode node = stack.pop();
            pre.next = node;
            node.next = tmp;
            pre = tmp;
            if(tmp != null) tmp = tmp.next;
        }

    }
    public void reorderList(ListNode head) {
        ListNode slow=head, fast = head, pre=null;
        /*find middle node */
        while(fast !=null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        /*divide list in two halves*/
        ListNode p1, p2;
        if(fast == null) { // fast == null so list has even no. of nodes slow will be part of second half
            p2 = slow;
            pre.next = null; // break at pre
        } else { // fast != null so list has odd no. of nodes so slow will be part of first half
            p2 = slow.next;
            slow.next = null; // break at slow
        }
        /*reverse second half*/
        ListNode curr = p2, tmp;
        pre = null;
        while(curr != null) {
            tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        /*pre will point to the new head of the list*/
        /*merge two lists*/
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
