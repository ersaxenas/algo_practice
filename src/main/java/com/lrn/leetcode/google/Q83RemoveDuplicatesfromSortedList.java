package com.lrn.leetcode.google;

public class Q83RemoveDuplicatesfromSortedList {
    /* 2022-01-09T12:05:09.751Z
    * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    * assm: list size < 10000, 0 < node value < 100, best time sol
    * approach: same as question 82
    * testcase:
    * */

    public ListNode deleteDuplicates(ListNode node) {
        if(node == null || node.next == null) return node;
        ListNode prv=null, currNode = node;
        while(currNode != null) {
            if(prv != null && prv.val == currNode.val) {
                prv = currNode.next;
            } else {
                prv = currNode;
            }
            currNode = node.next;
        }
       return node;
    }

}
