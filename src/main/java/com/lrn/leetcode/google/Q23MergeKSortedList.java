package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q23MergeKSortedList {
    /* 2021-12-04T06:21:08.411Z
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * pd: Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     * Asmp: list will not be empty, input will have at least 1 list, best time solution
     * Apch: take priority queue and add first node of each list to queue.
     *       take top node from queue add it to new queue node and insert next of top node to heap
     *       repeat until heap is empty.
     * */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode start = new ListNode(0);
        ListNode cur = start;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);
        for (int idx = 0; idx < lists.length; idx++) {
            ListNode node = lists[idx];
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            ListNode smallNode = queue.poll();
            cur.next = smallNode;
            cur = cur.next;
            if (smallNode.next != null) {
                queue.add(smallNode.next);
            }
        }
        return start.next;
    }
}
