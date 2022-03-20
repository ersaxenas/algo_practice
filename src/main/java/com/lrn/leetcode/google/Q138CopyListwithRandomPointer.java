package com.lrn.leetcode.google;

import java.util.HashMap;
import java.util.Map;

public class Q138CopyListwithRandomPointer {
    /*2022-03-16T20:51:03.761Z
    * pd: https://leetcode.com/problems/copy-list-with-random-pointer/
    * assm:  0 < list size < 1000, list is not circular
    * appr: use map to store clones
    * */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Map<Node, Node> nodemap = new HashMap<>();
        Node curr = head, prv=null;
        while(curr != null) {
            nodemap.put(curr, nodemap.getOrDefault(curr, new Node(curr.val)));
            if(curr.random != null) {
                nodemap.put(curr.random, nodemap.getOrDefault(curr.random, new Node(curr.random.val)));
                nodemap.get(curr).random = nodemap.get(curr.random);
            }
            if(prv != null) nodemap.get(prv).next = nodemap.get(curr);
            prv = curr;
            curr = curr.next;
        }
        return nodemap.get(head);
    }

}
