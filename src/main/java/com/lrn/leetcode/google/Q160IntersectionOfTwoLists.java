package com.lrn.leetcode.google;

public class Q160IntersectionOfTwoLists {
    /*2022-05-03T07:31:22.651Z
     * pd: Write a program to find the node at which the intersection of two singly linked lists begins.
     * assm: best time sol, list nodes < 1000
     * appr: first determine length of two lists. 1. l1, l2 : take two pointers of each list head
     *       now move pointer of long list by n = l1 -l2
     *       now start moving forward and when both pointers meeting it is the intersection
     *       if pointer don't meet then lists don't intersect
     * */

    /*best sol: https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!*/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode n1 = headA, n2 = headB;
        while(n1 != n2) {
            n1 = (n1 == null) ? headB : n1.next;
            n2 = (n2 == null) ? headA : n2.next;
        }
      return n1;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
          return null;
        }
        /*find length of lists*/
        int len1 = findLength(headA);
        int len2 = findLength(headB);
        /*Move head of the longer list to forward by diff of the length so both list now have same no. of elements to iterate*/
        ListNode n1 = moveahead(len1 - len2, headA), n2 = moveahead(len2 - len1, headB);
        /*Just iterate till the nodes meet*/
        while (n1 != null && n2 != null && n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        /*if node meet then true else false*/
        return n1 == n2 ? n1 : null;
    }

    public ListNode moveahead(int n, ListNode node) {
        while (node != null && n > 0) {
            node = node.next;
            n--;
        }
        return node;
    }

    public int findLength(ListNode head) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        Q160IntersectionOfTwoLists sol = new Q160IntersectionOfTwoLists();
        ListNode n3 = new ListNode(8);
        n3.add(4).add(5);
        ListNode n1 = new ListNode(4);
        n1.add(1).add(n3);
        ListNode n2 = new ListNode(5);
        n2.add(6).add(1).add(n3);

        System.out.println(sol.getIntersectionNode(n1, n2));
    }

}
