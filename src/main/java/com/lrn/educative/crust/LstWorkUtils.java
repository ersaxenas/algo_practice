package com.lrn.educative.crust;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LstWorkUtils {
    static class LinkedListNode {
        int data;
        LinkedListNode next;
        LinkedListNode arbPointer;

        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }

        public LinkedListNode append(int data) {
            LinkedListNode node = new LinkedListNode(data);
            this.next = node;
            return node;
        }

        public LinkedListNode append(LinkedListNode node) {
            this.next = node;
            return node;
        }

        public void display() {
            LinkedListNode currNode = this;
            while (currNode != null) {
                System.out.print(String.format("[%d] -> ", currNode.data));
                currNode = currNode.next;
            }
            System.out.println();
        }

        public LinkedListNode getArbPointer() {
            return arbPointer;
        }

        public LinkedListNode setArbPointer(LinkedListNode node) {
            this.arbPointer = node;
            return this;
        }

        @Override
        public String toString() {
            return String.format("[%d]", data);
        }
    }

    /*Reverse a Singly Linked List*/
    static class LsReversal {
        public LinkedListNode reverseLs(LinkedListNode head) {
            LinkedListNode preNode = null, currentNode = head, nextNode = null;
            while (currentNode != null) {
                nextNode = currentNode.next;
                currentNode.next = preNode;
                preNode = currentNode;
                currentNode = nextNode;
            }
            return preNode;
        }

        public LinkedListNode reverseLsRecursive(LinkedListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            LinkedListNode tail = reverseLsRecursive(head.next);
            head.next.next = head; // point next of next to head and then make next of head to null
            head.next = null;
            return tail;
        }

        public static void main(String[] args) {
            LsReversal lsReversal = new LsReversal();
            LinkedListNode listHead = null;
            listHead = new LinkedListNode(7);
            listHead.append(14).append(21).append(28);

            System.out.print("Original: ");
            listHead.display();

            listHead = lsReversal.reverseLs(listHead);
            System.out.print("After Reverse: ");
            listHead.display();

            listHead = lsReversal.reverseLsRecursive(listHead);
            System.out.print("After recursive Reverse: ");
            listHead.display();
        }
    }

    /*Remove Duplicates from a Linked List*/
    static class LsDupRemoval {
        public void removeDups(LinkedListNode node) {
            HashSet<Integer> set = new HashSet<>();
            set.add(node.data);
            LinkedListNode currNode = node;
            while (currNode != null && currNode.next != null) {
                if (set.contains(currNode.next.data)) {
                    // duplicate found
                    currNode.next = currNode.next.next;
                } else {
                    set.add(currNode.next.data);
                    currNode = currNode.next;
                }
            }
        }

        public static void main(String[] args) {
            LsDupRemoval lsDupRemoval = new LsDupRemoval();
            LinkedListNode listHead = new LinkedListNode(4);
            listHead.append(7).append(4).append(9).append(7).append(11).append(4);

            System.out.print("Original: ");
            listHead.display();
            lsDupRemoval.removeDups(listHead);
            System.out.print("After removing duplicates: ");
            listHead.display();
        }
    }

    /*Delete All Occurrences of a Given Key in a Linked List*/
    static class LsRemoveOccurrences {
        public LinkedListNode remove(LinkedListNode head, int key) {
            LinkedListNode currNode = head, prevNode = null, tmp = null;
            while (currNode.next != null) {
                if (currNode.data == key) {
                    if (currNode == head) {
                        tmp = head;
                        head = head.next;
                        currNode = head;
                        tmp.next = null;
                    } else {
                        tmp = currNode;
                        prevNode.next = currNode.next;
                        currNode = currNode.next;
                        tmp.next = null;
                    }
                } else {
                    prevNode = currNode;
                    currNode = currNode.next;
                }
            }
            return head;
        }

        public static void main(String[] args) {
            LsRemoveOccurrences lsRemoveOccurrences = new LsRemoveOccurrences();
            LinkedListNode listHead = null;
            listHead = new LinkedListNode(20);
            listHead.append(14).append(36).append(11).append(72).append(41);
            System.out.print("Original: ");
            listHead.display();

            listHead = lsRemoveOccurrences.remove(listHead, 72);
            System.out.printf("After deleting 72 from list: ");
            listHead.display();
        }
    }

    /*Sort Linked List Using Insertion Sort*/
    static class LsInsertionSort {

        public LinkedListNode insertNode(LinkedListNode head, LinkedListNode node) {
            if (head == null) return node;
            if (node == null) return head;
            if (head.data > node.data) { // insert at head
                node.next = head;
                return node; // new head
            }
            LinkedListNode curNode = head;
            while (curNode.next != null && curNode.next.data < node.data) {
                curNode = curNode.next;
            }
            node.next = curNode.next;
            curNode.next = node;
            return head;
        }


        public LinkedListNode sortList(LinkedListNode head) {
            LinkedListNode curNode = head;
            LinkedListNode newHead = null;
            while (curNode != null) {
                LinkedListNode tmp = curNode.next;
                curNode.next = null;
                newHead = insertNode(newHead, curNode);
                curNode = tmp;
            }
            return newHead;
        }

        public static void main(String[] args) {
            LsInsertionSort lsInsertionSort = new LsInsertionSort();
            LinkedListNode listHead = new LinkedListNode(29);
            listHead.append(23).append(82).append(11);

            System.out.print("Original: ");
            listHead.display();

            listHead = lsInsertionSort.sortList(listHead);
            System.out.print("After sorting: ");
            listHead.display();
        }
    }

    /*Intersection Point of Two Lists*/
    static class IntersectionOfTwoLists {
        public LinkedListNode findIntersection1(LinkedListNode lstHead1, LinkedListNode lstHead2) {
            LinkedListNode revLstHead1 = reverse(lstHead1);
            LinkedListNode revLstHead2 = reverse(lstHead2);
            revLstHead1.display();
            revLstHead2.display();
            if (revLstHead1 != revLstHead2) {
                return null;
            }
            LinkedListNode currNode1 = revLstHead1, currNode2 = revLstHead2;
            while (currNode1 != null && currNode2 != null && currNode1.next != currNode2.next) {
                currNode1 = currNode1.next;
                currNode2 = currNode2.next;
            }
            return currNode1;
        }

        public LinkedListNode reverse(LinkedListNode head) {
            LinkedListNode currNode = head, prevNode = null;
            while (currNode != null) {
                LinkedListNode next = currNode.next;
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = next;
            }
            return prevNode;
        }

        public LinkedListNode findIntersection2(LinkedListNode lstHead1, LinkedListNode lstHead2) {
            int len1 = 0, len2 = 0;
            LinkedListNode currNode1 = lstHead1, currNode2 = lstHead2;
            while (currNode1 != null) {
                len1++;
                currNode1 = currNode1.next;
            }
            while (currNode2 != null) {
                len2++;
                currNode2 = currNode2.next;
            }
            currNode1 = lstHead1;
            currNode2 = lstHead2;
            if (len1 > len2) {
                for (int idx = 0; idx < len1 - len2; idx++) {
                    currNode1 = currNode1.next;
                }
            } else {
                for (int idx = 0; idx < len2 - len1; idx++) {
                    currNode2 = currNode2.next;
                }
            }
            while (currNode1 != currNode2) {
                currNode1 = currNode1.next;
                currNode2 = currNode2.next;
            }
            return currNode1;
        }

        public static void main(String[] args) {
            IntersectionOfTwoLists ils = new IntersectionOfTwoLists();
            LinkedListNode list_head_1 = new LinkedListNode(13);
            LinkedListNode list_head_2 = new LinkedListNode(29);
            LinkedListNode node1 = new LinkedListNode(12);
            LinkedListNode node2 = new LinkedListNode(27);

            list_head_1.append(3).append(node1).append(node2);
            list_head_2.append(23).append(82).append(11).append(node1).append(node2);

            System.out.print("List 1: ");
            list_head_1.display();
            System.out.print("List 2: ");
            list_head_2.display();
            LinkedListNode intersect_node = ils.findIntersection2(list_head_1, list_head_2);
            System.out.println(String.format("Intersect at %d", intersect_node.data));
        }
    }

    /*Find n'th Node from the End of a Linked List*/
    static class NodeFromEnd {
        public LinkedListNode find(LinkedListNode head, int N) {
            LinkedListNode fwdNode = head, currNode = head;
            int cnt = 0;
            while (fwdNode != null && cnt < N) {
                fwdNode = fwdNode.next;
                cnt++;
            }
            if (fwdNode == null) {
                return null;
            }
            while (fwdNode != null) {
                fwdNode = fwdNode.next;
                currNode = currNode.next;
            }
            return currNode;
        }

        public LinkedListNode find2(LinkedListNode head, int N) {
            LinkedListNode revHead = reverse(head);
            int cnt = 1;
            LinkedListNode currNode = revHead;
            while (currNode != null && cnt < N) {
                currNode = currNode.next;
                cnt++;
            }
            reverse(revHead);
            return currNode;
        }

        public LinkedListNode reverse(LinkedListNode head) {
            LinkedListNode currNode = head, prevNode = null;
            while (currNode != null) {
                LinkedListNode temp = currNode.next;
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = temp;
            }
            return prevNode;
        }

        public static void main(String[] args) {
            NodeFromEnd nodeFromEnd = new NodeFromEnd();
            LinkedListNode listHead = null;

            listHead = new LinkedListNode(7);
            listHead.append(14).append(21).append(28).append(35).append(42);
            System.out.print("List: ");
            listHead.display();

            LinkedListNode temp = nodeFromEnd.find(listHead, 3);
            System.out.println(String.format("3rd element from last: %d", temp.data));
            temp = nodeFromEnd.find2(listHead, 3);
            System.out.println(String.format("3rd element from last: %d", temp.data));
        }
    }

    /*Swap Nth Node with Head*/
    static class SwapNthNode {
        public LinkedListNode swap(LinkedListNode head, int N) {
            LinkedListNode prevNode = null, currNode = head, tmp = null;
            int cnt = 1;
            while (currNode != null && cnt < N) {
                prevNode = currNode;
                currNode = currNode.next;
                cnt++;
            }
            if (prevNode == null) {
                return prevNode;
            }
            currNode = head;
            tmp = prevNode.next;
            // remove tmp
            prevNode.next = prevNode.next.next;
            // replace head with tmp;
            tmp.next = currNode.next;
            // append head to prev node
            currNode.next = prevNode.next;
            prevNode.next = currNode;
            return tmp;
        }

        public static void main(String[] args) {
            SwapNthNode swapNthNode = new SwapNthNode();
            LinkedListNode listHead = new LinkedListNode(7);
            listHead.append(14).append(21).append(28).append(35).append(42);

            System.out.print("Original list: ");
            listHead.display();

            listHead = swapNthNode.swap(listHead, 4);
            System.out.print("Swapping 4th node with head: ");
            listHead.display();
        }
    }

    /*Merge Two Sorted Linked Lists*/
    static class SortedListMerge {
        public LinkedListNode merge(LinkedListNode head1, LinkedListNode head2) {
            LinkedListNode lstHead1 = head1, lstHead2 = head2, mergedHead = null, cureNode = null, nodeToAppend;
            while (lstHead1 != null && lstHead2 != null) {
                if (lstHead1.data <= lstHead2.data) {
                    nodeToAppend = lstHead1;
                    lstHead1 = lstHead1.next;
                } else {
                    nodeToAppend = lstHead2;
                    lstHead2 = lstHead2.next;
                }
                nodeToAppend.next = null;
                if (mergedHead == null) {
                    mergedHead = nodeToAppend;
                    cureNode = nodeToAppend;
                } else {
                    cureNode.next = nodeToAppend;
                    cureNode = nodeToAppend;
                }
            }
            if (lstHead1 == null && lstHead2 != null && cureNode != null) {
                cureNode.next = lstHead2;
            } else if (lstHead2 == null && lstHead1 != null && cureNode != null) {
                cureNode.next = lstHead1;
            }
            return mergedHead;
        }

        public static void main(String[] args) {
            SortedListMerge sortedListMerge = new SortedListMerge();
            LinkedListNode listHead1 = new LinkedListNode(4);
            listHead1.append(8).append(15).append(19).append(22);
            System.out.print("List 1: ");
            listHead1.display();

            LinkedListNode listHead2 = new LinkedListNode(7);
            listHead2.append(9).append(10).append(16);
            System.out.print("List 2: ");
            listHead2.display();

            System.out.print("Merged: ");
            LinkedListNode merge = sortedListMerge.merge(listHead1, listHead2);
            merge.display();
        }
    }

    /*Sort a Linked List Using Merge Sort*/
    static class LsMergeSort {
        public LinkedListNode merge(LinkedListNode lst1, LinkedListNode lst2) {
            LinkedListNode newHead, currNode;
            if (lst1 == null) {
                return lst2;
            }
            if (lst2 == null) {
                return lst1;
            }
            if (lst1.data < lst2.data) {
                newHead = lst1;
            } else {
                newHead = lst2;
            }
            currNode = newHead;
            while (lst1 != null && lst2 != null) {
                LinkedListNode tmp;
                if (lst1.data < lst2.data) {
                    tmp = lst1;
                    lst1 = lst1.next;
                } else {
                    tmp = lst2;
                    lst2 = lst2.next;
                }
                tmp.next = null;
                currNode.next = tmp;
                currNode = tmp;
            }

            if (lst1 != null) {
                currNode.next = lst1;
            } else {
                currNode.next = lst2;
            }
            return newHead;
        }

        public Pair<LinkedListNode, LinkedListNode> splitList(LinkedListNode head) {
            assert head != null;
            if (head.next == null) {
                return Pair.of(head, null);
            }
            LinkedListNode slowP = head, fastP = head.next;
            while (fastP != null) {
                fastP = fastP.next;
                if (fastP != null) {
                    fastP = fastP.next;
                    slowP = slowP.next;
                }
            }
            //terminate first
            Pair<LinkedListNode, LinkedListNode> pair = Pair.of(head, slowP.next);
            slowP.next = null;
            return pair;
        }

        public LinkedListNode mergeSort(LinkedListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            Pair<LinkedListNode, LinkedListNode> pair = splitList(head);
            LinkedListNode first = mergeSort(pair.getLeft());
            LinkedListNode second = mergeSort(pair.getRight());
            return merge(first, second);
        }

        public static void main(String[] args) {
            LsMergeSort lsMergeSort = new LsMergeSort();
            int[] v1 = {29, 23, 82, 11, 4, 3, 21};
            LinkedListNode listHead1 = new LinkedListNode(29);
            listHead1.append(23).append(82).append(11).append(4).append(3).append(21);

            System.out.print("Unsorted list: ");
            listHead1.display();

            listHead1 = lsMergeSort.mergeSort(listHead1);
            System.out.print("Sorted list: ");
            listHead1.display();
        }
    }

    /*Given a singly linked list, reverse the nodes at even indices (starting at 1).*/
    static class LsReverseEvenNodes {
        public LinkedListNode reverse(LinkedListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            LinkedListNode mainLsHead = head, evenLsHead = null, currNodeMainLs, currNodeEvenLs = null, temp;
            currNodeMainLs = mainLsHead;
            while (currNodeMainLs != null) {
                if (currNodeMainLs.next != null) {
                    // remove even node
                    temp = currNodeMainLs.next;
                    currNodeMainLs.next = temp.next;
                    temp.next = null;

                    if (evenLsHead == null) {
                        evenLsHead = temp;
                        currNodeEvenLs = evenLsHead;
                    } else {
                        currNodeEvenLs.next = temp;
                        currNodeEvenLs = currNodeEvenLs.next;
                    }
                }
                currNodeMainLs = currNodeMainLs.next;
            }
            mainLsHead.display();
            evenLsHead.display();
            // reverse even list
            LinkedListNode prevNode = null;
            currNodeEvenLs = evenLsHead;
            while (currNodeEvenLs != null) {
                temp = currNodeEvenLs.next;
                currNodeEvenLs.next = prevNode;
                prevNode = currNodeEvenLs;
                currNodeEvenLs = temp;
            }
            evenLsHead = prevNode;
            evenLsHead.display();
            // merge two lists
            currNodeEvenLs = evenLsHead;
            currNodeMainLs = mainLsHead;
            while (currNodeEvenLs != null && currNodeMainLs != null) {
                temp = currNodeEvenLs.next;

                currNodeEvenLs.next = currNodeMainLs.next;
                currNodeMainLs.next = currNodeEvenLs;

                currNodeEvenLs = temp;
                currNodeMainLs = currNodeMainLs.next.next;
            }
            mainLsHead.display();
            return mainLsHead;
        }

        public static void main(String[] args) {
            LsReverseEvenNodes lsReverseEvenNodes = new LsReverseEvenNodes();
            LinkedListNode listHead = new LinkedListNode(7);
            listHead.append(14).append(21).append(28).append(9);
            System.out.print("Original list: ");
            listHead.display();

            listHead = lsReverseEvenNodes.reverse(listHead);
            System.out.print("After Reverse: ");
            listHead.display();
        }
    }

    /*Rotate a Linked List*/
    static class LsRotate {
        public LinkedListNode rotate(LinkedListNode head, int N) {
            if (head == null) {
                return head;
            }

            LinkedListNode curNode1 = head, curNode2 = head;
            int len = 1, rotation = N;
            while (curNode2.next != null) {
                curNode2 = curNode2.next;
                len++;
            }
            rotation = rotation % len;
            if (rotation < 0) {
                rotation = rotation + len;
            }
            while (rotation > 0) {
                curNode1 = curNode1.next;
                rotation--;
            }
            curNode2.next = head;
            LinkedListNode newHead = curNode1.next;
            curNode1.next = null;
            return newHead;
        }

        public static void main(String[] args) {
            LsRotate lsRotate = new LsRotate();
            LinkedListNode listHead = new LinkedListNode(1);
            listHead.append(2).append(3).append(4).append(5);

            System.out.print("Original list: ");
            listHead.display();

            listHead = lsRotate.rotate(listHead, 2);
            System.out.print("List rotated by 2: ");
            listHead.display();
        }
    }

    /*Reverse Alternate K Nodes in a Singly Linked List*/
    static class LsReverseAlternateKNodes {
        public LinkedListNode reverse(LinkedListNode head) {
            LinkedListNode prevNode = null, currNode = head, temp = null;
            while (currNode != null) {
                temp = currNode.next;
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = temp;
            }
            return prevNode;
        }
        public Pair<Pair<LinkedListNode, LinkedListNode>, LinkedListNode> reverseAndSplit(LinkedListNode head, int K) {
            LinkedListNode currHead = head, currNode = head, prevNode=null, temp=null;
            int n = K;
            while(currNode != null && n > 0) {
                temp = currNode.next;
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = temp;
                n--;
            }
            // list 1 -> tail == currHead, head = preNode, list2  head = currNode
            Pair<LinkedListNode,LinkedListNode> revList = Pair.of(prevNode, currHead);
            return Pair.of(revList, currNode);
        }
        public LinkedListNode reverseNodes2(LinkedListNode head, int K) {
            if (head == null || head.next == null || K <= 1) {
                return head;
            }
            LinkedListNode reverseHead = null, prevTail = null, currentHead=head;
            Pair<Pair<LinkedListNode, LinkedListNode>, LinkedListNode> reverseAndSplit= reverseAndSplit(head, K);
            while(currentHead != null) {
                if(reverseHead == null) {
                    reverseHead = reverseAndSplit.getLeft().getLeft();
                }
                if(prevTail == null) {
                    prevTail = reverseAndSplit.getLeft().getRight();
                } else {
                    prevTail.next = reverseAndSplit.getLeft().getLeft();
                    prevTail = reverseAndSplit.getLeft().getRight();
                }
                currentHead = reverseAndSplit.getRight();
                reverseAndSplit= reverseAndSplit(currentHead, K);
            }
            return reverseHead;
        }


        public LinkedListNode reverseNodes(LinkedListNode head, int K) {
            if (head == null || head.next == null || K <= 1) {
                return head;
            }
            LinkedListNode currentNode = head;
            LinkedListNode reversedHead = null,prevTail = null;


            while (currentNode != null) {
                LinkedListNode currTail = currentNode, currhead = null;


                LinkedListNode  temp = null;
                int n = K;
                // reverse k elements
                while (currentNode != null && n > 0) {
                    temp = currentNode.next;
                    currentNode.next = currhead;
                    currhead = currentNode;
                    currentNode = temp;
                    n--;
                }
                if (reversedHead == null) {
                    reversedHead = currhead;
                }
                if(prevTail != null) {
                    prevTail.next = currhead;
                }
                prevTail = currTail;

            }
            return reversedHead;
        }

        public static void main(String[] args) {
            LsReverseAlternateKNodes lsReverseAlternateKNodes = new LsReverseAlternateKNodes();
            LinkedListNode listHead = new LinkedListNode(1);
            listHead.append(2).append(3).append(4).append(5).append(6).append(7);
            System.out.print("Original list: ");
            listHead.display();

            listHead = lsReverseAlternateKNodes.reverseNodes2(listHead, 4);
            System.out.print("List reversed by 4: ");
            listHead.display();
        }
    }
    /*Add Two Integers Represented by Linked Lists*/
    static class LsIntegerAddition {
        public LinkedListNode add(LinkedListNode lst1, LinkedListNode lst2) {
            LinkedListNode curNode1=lst1, curNode2=lst2, newHead=null, currNode=null;
            int carry =0;
            while(curNode1 != null || curNode2 != null) {
                int i1 = (curNode1!=null) ? curNode1.data : 0;
                int i2 = (curNode2!=null) ? curNode2.data : 0;

                int sum = i1 + i2 + carry;
                carry = 0;
                if(sum >= 10) {
                    carry = sum / 10;
                    sum = sum %10;
                }
                if(newHead == null) {
                    newHead = new LinkedListNode(sum);
                    currNode = newHead;
                } else {
                    currNode.append(sum);
                    currNode = currNode.next;
                }
                if(curNode1 != null) {
                    curNode1 = curNode1.next;
                }
                if(curNode2 != null) {
                    curNode2 = curNode2.next;
                }
            }
            if(carry != 0 && currNode != null) {
                currNode.append(carry);
            }
            return newHead;
        }

        public static void main(String[] args) {
            LsIntegerAddition lsIntegerAddition = new LsIntegerAddition();

            LinkedListNode first = new LinkedListNode(1); //1099
            first.append(0).append(9).append(9);
            LinkedListNode second = new LinkedListNode(7); // 732
            second.append(3).append(2);

            LinkedListNode result = lsIntegerAddition.add(first, second);

            System.out.printf("First integer: ");
            first.display();
            System.out.printf("Second integer: ");
            second.display();

            System.out.printf("Result: ");
           result.display();
        }
    }
    /*Copy Linked List with Arbitrary Pointer*/
    static class LsWithArbitaryPointer {
        public LinkedListNode deepCopy(LinkedListNode head) {
            if(head == null) {
                return head;
            }
            Map<LinkedListNode,LinkedListNode> nodeMap = new HashMap<>();
            LinkedListNode curNode = head, newHead=null, newCurrNode=null;
            while(curNode != null) {
                 LinkedListNode node = new LinkedListNode(curNode.data);
                 node.setArbPointer(curNode.getArbPointer());
                 nodeMap.put(curNode,node);
                 if(newCurrNode == null) {
                     newCurrNode = node;
                 } else {
                     newCurrNode.append(node);
                 }
                if(newHead == null) {
                    newHead = newCurrNode;
                }
                curNode = curNode.next;
            }
            curNode = newHead;
            while(curNode != null) {
                curNode.setArbPointer(nodeMap.get(curNode));
                curNode = curNode.next;
            }
            return newHead;
        }

        public static void main(String[] args) {

        }
    }

}
