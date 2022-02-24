package com.lrn.educative.gci.reversels;

public class WorkLsUtils {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ListNode{value=" + value + '}';
        }
    }

    /*Problem: Given the head of a Singly LinkedList, reverse the LinkedList. Write a function to return the new head of the reversed LinkedList.
     * Assumptions: in place, best time solution,
     * Approach: take three reference to listnode - previous=null, current = head, next=null
     *           start with the head of the list,
     *           next = current.next, current.next= previous, previous = current, current=next
     *           run loop till current != null
     *           return previous;
     * Testcase: 1. list == null return list;
     *           2. if list.next is null return list;
     *           3. 1->2->3 3->2->1 return head as 3
     * */
    static class ReverseLinkedListInPlace {
        public ListNode reverse(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode previous = null, current = head, next = null;
            while (current != null) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            return previous;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(2);
            head.next = new ListNode(4);
            head.next.next = new ListNode(6);
            head.next.next.next = new ListNode(8);
            head.next.next.next.next = new ListNode(10);
            ReverseLinkedListInPlace reverseLinkedListInPlace = new ReverseLinkedListInPlace();
            ListNode result = reverseLinkedListInPlace.reverse(head);
            System.out.print("Nodes of the reversed LinkedList are: ");
            while (result != null) {
                System.out.print(result.value + " ");
                result = result.next;
            }
        }
    }

    /* problem : Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
     * Approach: so we have 3 part now. first is the part of list before p and second is p to q and third is q+1 to the last element of the list
     *           iterate list till p-1 and keep the reference to p-1 = x and current = y.
     *           so p -1 is the last element of first half and current list node will become last node of the 2nd part.
                 now make copy of previous and current node
                 now start reversing the list from current = p.next till q.
                 * now previous points to the first element of 2nd list and current is the first element of the third list
                 now x.next = previous and y.next = current
     */
    static class ReversePartOfTheSublist {
        public ListNode reverse(ListNode head, int p, int q) {
            if (head == null || p == q) {
                return head;
            }
            ListNode lastNodeOfFistPart, lastOf2ndPart;
            ListNode previous = null, current = head, next = null;
            for (int idx = 0; current.next != null && idx < p - 1; idx++) {
                previous = current;
                current = current.next;
            }
            lastNodeOfFistPart = previous;
            lastOf2ndPart = current;
            for (int idx = 0; current != null && idx < q - p + 1; idx++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            if (lastNodeOfFistPart != null) {
                lastNodeOfFistPart.next = previous;
            } else { // if p=1
                head = previous;
            }

            lastOf2ndPart.next = current;
            return head;
        }

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
            ReversePartOfTheSublist reversePartOfTheSublist = new ReversePartOfTheSublist();
            ListNode result = reversePartOfTheSublist.reverse(head, 2, 4);
            System.out.print("Nodes of the reversed LinkedList are: ");
            while (result != null) {
                System.out.print(result.value + " ");
                result = result.next;
            }
        }
    }
    /*Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head. If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
     * Approach is same as above.
     * */

    static class ReverseSubSectionOfLinkedList {
        public ListNode reverse(ListNode head, int k) {
            if (head == null) return head;
            if (k <= 1) return head;
            ListNode previous = null, current = head, next = null;
            ListNode lastNodeOfThe1stPart = null, lastnodeOfthe2ndPart = null;
            /*Since we are starting from 0 there is no need to skip first few elements.*/
            while (current != null) {

                lastNodeOfThe1stPart = previous;
                lastnodeOfthe2ndPart = current;
                for (int idx = 0; current != null && idx < k; idx++) {
                    next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                }

                if (lastNodeOfThe1stPart != null) {
                    lastNodeOfThe1stPart.next = previous;
                } else {
                    head = previous;
                }
                lastnodeOfthe2ndPart.next = current;

                previous = lastnodeOfthe2ndPart;
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
            ReverseSubSectionOfLinkedList reverseSubSectionOfLinkedList = new ReverseSubSectionOfLinkedList();
            ListNode result = reverseSubSectionOfLinkedList.reverse(head, 3);
            System.out.print("Nodes of the reversed LinkedList are: ");
            while (result != null) {
                System.out.print(result.value + " ");
                result = result.next;
            }
        }
    }

    /*Reverse alternating K-element Sub-list */
    static class ReverseAlternatingSubList {
        public ListNode reverse(ListNode head, int k) {
            if (head == null || k <= 1) {
                return head;
            }
            ListNode previous = null, current = head, next = null;
            ListNode endOfPart1 = null, endOfPart2 = null;
            boolean isAlternate = false;
            while (current != null) {
                if (isAlternate) {
                    for (int idx = 0; idx < k && current != null; idx++) {
                        previous = current;
                        current = current.next;
                    }
                }
                if (current == null) {
                    break;
                }
                endOfPart1 = previous;
                endOfPart2 = current;
                for (int idx = 0; idx < k && current != null; idx++) {
                    next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                }

                if (endOfPart1 != null) {
                    endOfPart1.next = previous;
                } else {
                    head = previous;
                }
                endOfPart2.next = current;

                previous = endOfPart2;
                isAlternate = true;
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
            ReverseAlternatingSubList reverseAlternatingSubList = new ReverseAlternatingSubList();
            ListNode result = reverseAlternatingSubList.reverse(head, 3);
            System.out.print("Nodes of the reversed LinkedList are: ");
            while (result != null) {
                System.out.print(result.value + " ");
                result = result.next;
            }
        }
    }

    /*Given the head of a Singly LinkedList and a number ‘k’, rotate the LinkedList to the right by ‘k’ nodes.
     * Approach : find head, tail and length of the linkedlist.
     *            connect tail to head - tail.next = head
     *            rotation: k % length, to traverse list only once
     *            element to move = length - rotation = new tail
     *            iterate to new tail location
     *            now tail = new tail and head = new tail .next and new tail .next = null;
     * */

    static class RotateLinkedList {
        public ListNode rotate(ListNode head, int k) {
            if (head == null || head.next == null || k <= 0) {
                return head;
            }
            ListNode nHead = head, ntail = null, current = head;
            int listLen = 0;
            while (current.next != null) {
                current = current.next;
                listLen++;
            }
            ntail = current;
            ntail.next = head;
            int rotation = k % listLen;
            int elementsToMove = listLen - rotation;
            head = current;
            for (int idx = 0; idx < elementsToMove; idx++) {
                current = current.next;
            }
            ntail = current;
            nHead = ntail.next;
            ntail.next = null;
            return nHead;
        }
        public static void main(String[] args) {
            RotateLinkedList rotateLinkedList = new RotateLinkedList();
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
            head.next.next.next.next.next = new ListNode(6);

            ListNode result = rotateLinkedList.rotate(head, 3);
            System.out.print("Nodes of the reversed LinkedList are: ");
            while (result != null) {
                System.out.print(result.value + " ");
                result = result.next;
            }
        }
    }


}
