package com.lrn.leetcode.google;

public class Q234PalindromLinkedList {
    /*
     * pd: Given a singly linked list, determine if it is a palindrome.
     * assm: list size < int.max, best time sol, no cycles
     * appr:
     *
     * test cases:
     * */

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode head2 = findMiddleNode(head);
        head2 = reverse(head2);
        while(head != null && head2 != null) {
            if(head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    public ListNode reverse(ListNode node) {
        ListNode prev = null, current = node, tmp = null;
        while (current != null) {
            tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        return prev;
    }

    public ListNode findMiddleNode(ListNode node) {
        ListNode slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null ) {
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Q234PalindromLinkedList sol = new Q234PalindromLinkedList();
        ListNode head = new ListNode(1);
        head.add(2);
        System.out.println(sol.isPalindrome(head));
    }


}
