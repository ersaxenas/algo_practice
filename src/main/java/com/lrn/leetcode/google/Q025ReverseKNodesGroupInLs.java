package com.lrn.leetcode.google;


public class Q025ReverseKNodesGroupInLs {
   /* https://leetcode.com/problems/reverse-nodes-in-k-group/
   * 2021-12-10T15:47:04.092Z
   *
   * */
    public ListNode reverseKGroup(ListNode node, int k) {
        // base case
        if (node == null) {
            return node;
        }
        ListNode s = node, e = node, next = null;
        for (int idx = 0; idx < k - 1 && e != null; idx++) {
            e = e.next;
        }
        if (e == null) {
            return node;
        }
        next = e.next;
        // recursive
        reverse(s, k); // after reverse s will become tail and e will become head
        s.next = reverseKGroup(next, k);
        return e;
    }

    public ListNode reverse(ListNode s, int k) {
        ListNode prv = null, curr = s, tmp;
        for (int idx = 0; idx < k; idx++) {
            tmp = curr.next;
            curr.next = prv;
            prv = curr;
            curr = tmp;
        }
        return prv;
    }


    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        ListNode prev=null, curr=head, next=null;
        ListNode lastNodeP1, lastNodeP2;
        while(curr != null) {
            lastNodeP1 = prev; /*previous will point to last node of part 1*/
            lastNodeP2 = curr; /*current node will be the last node of part2 after reversal of K nodes*/

            /*revers K nodes*/
            for(int idx=0; idx<k && curr != null; idx++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            /*
            *            e | e <- e <- e <- e | -> e -> e -> e -> e
            *   lastnodep1                  p      c
            * */
            /*link tails of part 1 to part 2 */
            if(lastNodeP1 != null) {
                lastNodeP1.next = prev; // prev will be new head
            } else {
                head = prev;
            }
            prev = lastNodeP2;
            /*Link tail of part2*/
            lastNodeP2.next = curr;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        Q025ReverseKNodesGroupInLs q24ReverseKNodesGroupInLs = new Q025ReverseKNodesGroupInLs();
        ListNode result = q24ReverseKNodesGroupInLs.reverseKGroup2(head, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }


}
