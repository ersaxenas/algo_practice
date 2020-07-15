package com.lrn.leetcode.google;

public class Q2AddTwoNumbers {

   /*iterative*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }
        ListNode cl1=l1, cl2=l2;
        ListNode result = new ListNode(0);
        ListNode currNode = result;
        int sum  = 0;
        while(cl1 != null || cl2 != null) {
            // take previous carry
            sum = sum / 10;

            if(cl1 != null) {
                sum = sum + cl1.val;
                cl1 = cl1.next;
            }

            if(cl2 != null) {
                sum = sum + cl2.val;
                cl2 = cl2.next;
            }

            currNode.next = new ListNode(sum %10);
            currNode = currNode.next;
        }
        if(sum / 10 == 1) {
            currNode.next = new ListNode(1);
        }
        return result.next;
    }

    /*recursive*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        // base case
        if(l1==null && l2==null && carry == 0) {
            return null;
        }
        ListNode next1=null, next2=null;
        int sum = carry;
        if(l1!=null) {
            sum += l1.val;
            next1 = l1.next;
        }
        if(l2!=null) {
            sum += l2.val;
            next2 = l2.next;
        }

        ListNode node = new ListNode();
        node.val = sum %10;
        node.next = addTwoNumbers(next1, next2, sum/10);
        return node;
    }


    public static void main(String[] args) {
       Q2AddTwoNumbers q2AddTwoNumbers = new Q2AddTwoNumbers();
       ListNode l1 = new ListNode(2);
       l1.add(4).add(3);
       ListNode l2 = new ListNode(5);
       l2.add(6).add(4);
       q2AddTwoNumbers.addTwoNumbers(l1,l2).print();
       q2AddTwoNumbers.addTwoNumbers(l1,l2,0).print();
    }

}
