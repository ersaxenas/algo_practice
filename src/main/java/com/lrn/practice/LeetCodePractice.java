package com.lrn.practice;

import com.lrn.leetcode.google.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LeetCodePractice {


   public ListNode partition(ListNode head, int x) {
       if(head == null || head.next == null) {
           return head;
       }
       ListNode s1 = new ListNode();
       ListNode s2 = new ListNode();
       s1.next = head;
       ListNode prv=s1, curr = head, curr2 = s2, tmp;
       while(curr != null) {
           while(curr != null && curr.val >= x) {
               prv = curr;
               curr = curr.next;
           }
           if(curr != null) {
               tmp = curr;
               prv.next = curr.next;
               curr = curr.next;
               curr2.next = tmp;
               curr2 = tmp;
           }
       }
       if(s1.next != null) {
           curr2.next = s1.next;
       }
       return s2.next;
   }


    public static void main(String[] args) {
        LeetCodePractice sol = new LeetCodePractice();
        ListNode lst = new ListNode(1);
        lst.add(4).add(3).add(2).add(5).add(2);
        sol.partition(lst, 3);
    }

}
