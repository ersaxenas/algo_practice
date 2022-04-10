package com.lrn.leetcode.google;

public class Q147InsertionSortList {
    /*2022-04-09T10:52:49.576Z
    * pd:
    *
    *
    * */


    // swapping values and reversing the values
    public ListNode insertionSortList(ListNode head) {
        ListNode curr=head, pre=null, tmp=null, bcur=null;
        while(curr != null) {
            tmp = curr.next;
            curr.next =pre;

            bcur = curr;
            while(bcur != null) {
                if(bcur.next != null && bcur.val < bcur.next.val) {
                    int tv = bcur.next.val;
                    bcur.next.val = bcur.val;
                    bcur.val = tv;
                }
                bcur = bcur.next;
            }

            pre = curr;
            curr = tmp;
        }
        curr = pre;
        pre = null;
        while(curr != null) {
            tmp = curr.next;
            curr.next =pre;
            pre = curr;
            curr = tmp;
        }
        return head;
    }

    public ListNode insertionSortList2(ListNode head) {
        ListNode node = head, dummy = new ListNode(0), prv = dummy, temp;
        while(node != null) {
            temp = node.next;

            /*node belongs to somewhere before prv so start from the beginning*/
            if(prv.val >= node.val) prv = dummy;

            while(prv.next != null && prv.next.val < node.val) {/*move to next node till prv.next.value is less than node.val*/
                prv = prv.next;
            }
            /*now prv is at node where node.val <= prv.next.val*/
            node.next = prv.next;
            prv.next = node;

            node = temp;/*take next node*/
        }
        return dummy.next;
    }

}
