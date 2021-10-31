package com.lrn.leetcode.google;

public class Q203RemoveLinkedListElements {
    /*
    * pd: https://leetcode.com/problems/remove-linked-list-elements
    * assm : 0 < nodes < 10000, 0 <= node val < 1000
    * appr: iterative
    * test cases:
    * */

    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode dummy = new ListNode(), prev, curr;
        dummy.next = head;
        prev = dummy;
        curr = dummy.next;
        while(curr != null) {
            if(curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
    //recursive
    public ListNode removeElementsRec(ListNode node, int val) {
        if(node == null) return node;
        node.next = removeElementsRec(node.next, val);
        return node.val == val ? node.next : node;
    }

}
