package com.lrn.leetcode.google;

public class Q82RemoveDulicatesFromSortedList2 {

    /*
    * pd: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    *
    * assm: list size is < 10000, best time sol, list contains integers only and is sorted
    * appr:
    * test cases:
    *
    * */

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode currNode = head, prv = null;
        while(currNode != null) {
            if(prv != null && prv.val == currNode.val) {
                prv.next = currNode.next;
            } else {
                prv = currNode;
            }
            currNode = currNode.next;
        }
        return head;
    }

    public ListNode deleteDuplicatesAll(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode currNode = head, prv = null, start = new ListNode();
        start.next = head;
        prv = start;
        while(currNode != null) {
            while(currNode != null && currNode.next != null && currNode.val == currNode.next.val) {
                currNode = currNode.next;
            }
            if(prv.next == currNode) {
                prv = currNode;
            } else {
                prv.next = currNode.next;
            }
            currNode = currNode.next;
        }
        return start.next;
    }

    public static void main(String[] args) {
        Q82RemoveDulicatesFromSortedList2 sol = new Q82RemoveDulicatesFromSortedList2();
    }

}
