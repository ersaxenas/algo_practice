package com.lrn.leetcode.google;

public class Q24SwapNodesInPair {
    /*
    * pd: Given a linked list, swap every two adjacent nodes and return its head.
    * You may not modify the values in the list's nodes, only nodes itself may be changed.
    * Aprc: recursive:
    *       A - B
    *       B - A
    *       return B;
    *       take a pair node and node.next
    *       keep ref fo tmp = node.next
    *       node.next = rev node.next.next -- next pair
    *      node.next = node
    *      return tmp;
    *
    * */
   public ListNode swapPairs(ListNode node) {
       // base case
         if(node == null || node.next == null) {
             return null;
         }
       // recursion
       // we have two nodes
       ListNode currNode = node, nextNode = currNode.next;
       // here we know that nextNode.next will point to currNode. now we need to find what will be the next node of currNode
       currNode.next = swapPairs(currNode.next.next); // take next to pair and get the result
       nextNode.next = currNode;
       return nextNode;
   }
}
