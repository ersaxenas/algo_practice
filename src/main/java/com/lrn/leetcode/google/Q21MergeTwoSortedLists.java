package com.lrn.leetcode.google;

public class Q21MergeTwoSortedLists {
    /*
     * https://leetcode.com/problems/merge-two-sorted-lists/
     * pd: Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
     * 
     * */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode start = new ListNode(0), curnode = start, p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
              if(p1.val <= p2.val) {
                  curnode.next = p1;
                  p1 = p1.next;
              } else {
                  curnode.next = p2;
                  p2 = p2.next;
              }
              curnode = curnode.next;
        }
        curnode.next = (p1 != null) ? p1 : p2;
        return start.next;
    }


    public static void main(String[] args) {

    }

}
