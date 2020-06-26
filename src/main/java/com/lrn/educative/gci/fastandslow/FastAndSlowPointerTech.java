package com.lrn.educative.gci.fastandslow;

public class FastAndSlowPointerTech {
    /*Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.*/
    public static boolean findIfLinledListHasCycle(WorkFsUtils.ListNode head) {
        WorkFsUtils.ListNode sp = head;
        WorkFsUtils.ListNode fp = head;
        while (fp != null && fp.next != null) {
            fp = fp.next.next;
            sp = sp.next;
            if (fp == sp) {
                return true;
            }
        }
        return false;
    }
}
