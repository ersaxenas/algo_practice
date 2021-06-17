package com.lrn.leetcode.google;

public class Q92ReverseLinkedList2 {

    /*
    * pd: https://leetcode.com/problems/reverse-linked-list-ii/
    * assm: list size < 1000, best time sol
    * appr: https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation
    *       // a little approch to reverse
    * test cases:
    *
    * */

   public ListNode reverse(ListNode head, int left, int right) {
       if(head == null) {
           return null;
       }

       ListNode pre, dummy = new ListNode(), start, next;
       dummy.next = head;
       pre = dummy;
       for(int idx=0; idx<left-1 && pre != null; idx++) {
           pre = pre.next;
       }
       if(pre == null) return head;
       start = pre.next;
       next = start.next;
       for(int idx=0; idx<right-left; idx++) {
           start.next = next.next;
           next.next = pre.next;
           pre.next = next;
           next = start.next;
       }
       return dummy.next;
   }

    public static void main(String[] args) {
        Q92ReverseLinkedList2 sol = new Q92ReverseLinkedList2();
        ListNode n1 = new ListNode(1);
        n1.add(2).add(3).add(4).add(5);
        n1.printList(n1);
        sol.reverse(n1,2,4);
        n1.printList(n1);

    }




}
