package com.lrn.educative.recur;

import java.util.Arrays;

public class WorkRecDSUtil {
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node setNext(Node node) {
            this.next = node;
            return node;
        }
        public void printList() {
            Node tmp = this;
            System.out.println();
            while(tmp != null) {
                System.out.print(tmp.value + " -> ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    };
    /*print reverse linked list*/
    static class ShowReverseOrder {
        public void printReverse(Node node) {
            // base case
            if(node == null) {
                return;
            }
            // recursive
            printReverse(node.next);
            System.out.print(node.value+ " <- ");
        }

        public static void main(String[] args) {
            // Linked List = 1->2->3->4->5
            Node head = new Node(1);
            head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(4)).setNext(new Node(5));
            head.printList();
           ShowReverseOrder showReverseOrder = new ShowReverseOrder();
           showReverseOrder.printReverse(head);
        }
    }
    /*Reverse a linked list*/
    static class ReverseLinkList {
        public Node reverse(Node node, Node preNode) {
            if(node == null) {
                return preNode;
            }
            Node tmp = node.next;
            node.next = preNode;
            return reverse(tmp, node);
        }
        public static void main(String[] args) {
            ReverseLinkList reverseLinkList = new ReverseLinkList();
            Node head = new Node(1);
            head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(4)).setNext(new Node(5));
            head.printList();
            Node newHead = reverseLinkList.reverse(head, null);
            newHead.printList();
        }
    }
    /*Sum Numbers in a Linked List*/
    static class SumLSValues {
        public int sum(Node node) {
            // base case
            if(node == null) {
                return 0;
            }
            // recursion
            return node.value + sum(node.next);
        }

        public static void main(String[] args) {
            Node head = new Node(3);
            head.setNext(new Node(1)).setNext(new Node(5)).setNext(new Node(8)).setNext(new Node(2));
            head.printList();
            SumLSValues sumLSValues = new SumLSValues();
            System.out.println(sumLSValues.sum(head));
        }
    }
    /*Search values in LS*/
    static class LSSearch {
        public boolean search(Node node, int value) {
            // base
            if(node == null) {
                return false;
            }
            if(value == node.value) {
                return true;
            }
            // recursion
            return search(node.next, value);
        }

        public static void main(String[] args) {
            Node head = new Node(1);
            head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(4)).setNext(new Node(5));
            head.printList();
            LSSearch lsSearch = new LSSearch();
            System.out.println(lsSearch.search(head, 4));
            System.out.println(lsSearch.search(head, 9));
        }
    }

}
