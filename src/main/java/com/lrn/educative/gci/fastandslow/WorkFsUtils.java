package com.lrn.educative.gci.fastandslow;

public class WorkFsUtils {
    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() { return "ListNode{value=" + value + '}';
        }
    }

    public static void printList(ListNode head) {
        System.out.println();
        ListNode currentNode = head;
        int cnt = 0;
        while (currentNode != null) {
            System.out.print(String.format("[%d] -> ", currentNode.value));
            currentNode = currentNode.next;
        }
        System.out.print("[null]\n");
    }

    /*Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.*/
    public static boolean findIfLinledListHasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if (fastPointer == slowPointer) {
                return true;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        System.out.println("LinkedList has cycle: " + findIfLinledListHasCycle(head));
//
//        head.next.next.next.next.next.next = head.next.next;
//        System.out.println("LinkedList has cycle: " + findIfLinledListHasCycle(head));
//
//        head.next.next.next.next.next.next = head.next.next.next;
//        System.out.println("LinkedList has cycle: " + findIfLinledListHasCycle(head));
//    }

    /*Given the head of a LinkedList with a cycle, find the length of the cycle.*/
    public static int findLengthOfCycleInLinkedList(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        int cycleLength = 0;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (fastPointer == slowPointer) {
                break;
            }
        }
        if (fastPointer != null && slowPointer != null) {
            /*now both slow pointer and fast pointer points to the same node in the cycle*/
            cycleLength++;
            slowPointer = slowPointer.next;
            while (fastPointer != slowPointer) {
                cycleLength++;
                slowPointer = slowPointer.next;
            }
        }
        return cycleLength;
    }

//    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//        head.next.next.next.next.next.next = head.next.next;
//        System.out.println("LinkedList cycle length: " + findLengthOfCycleInLinkedList(head));
//        head.next.next.next.next.next.next = head.next.next.next;
//        System.out.println("LinkedList cycle length: " + findLengthOfCycleInLinkedList(head));
//    }

    /*Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.*/
    public static ListNode findStartingNodeOfTheCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        ListNode startNode = head;
        int cycleLength = 0;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer == fastPointer) {
                break;
            }
        }
        if (fastPointer != null) {
            do {
                slowPointer = slowPointer.next;
                cycleLength++;
            } while (slowPointer != fastPointer);
            slowPointer = head;
            fastPointer = head;
            while (cycleLength > 0) {
                fastPointer = fastPointer.next;
                cycleLength--;
            }
            while (slowPointer != fastPointer) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }
        }
        return slowPointer;
    }

//    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
//
//        head.next.next.next.next.next.next = head.next.next;
//        System.out.println("LinkedList cycle start: " + findStartingNodeOfTheCycle(head).value);
//
//        head.next.next.next.next.next.next = head.next.next.next;
//        System.out.println("LinkedList cycle start: " + findStartingNodeOfTheCycle(head).value);
//
//        head.next.next.next.next.next.next = head;
//        System.out.println("LinkedList cycle start: " + findStartingNodeOfTheCycle(head).value);
//    }

    /*Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.*/

    static class HappyNumber {

        public int squareSumOfTheNumber(int num) {
            if (num == 0) return 0;
            if (num == 1) return 1;
            int sum = 0, cNum = num;
            while (cNum > 0) {
                int digit = cNum % 10;
                sum = sum + (digit * digit);
                cNum = cNum / 10;
            }
            return sum;
        }

        public boolean isHappyNumber(int num) {
            if (num == 0) return false;
            if (num == 1) return true;
            int slow = num, fast = num;
            do {
                slow = squareSumOfTheNumber(slow);
                fast = squareSumOfTheNumber(squareSumOfTheNumber(fast));
            } while (slow != fast);
            return slow == 1;
        }

        public static void main(String[] args) {
            HappyNumber happyNumber = new HappyNumber();
            System.out.println(happyNumber.isHappyNumber(23));
            System.out.println(happyNumber.isHappyNumber(12));
        }

    }

    /*Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.*/
    static class MiddleOfTheLinkList {
        public int findTheMiddleOfTheLinkList(ListNode head) {
            if (head == null) {
                throw new IllegalArgumentException("LinkList head is null");
            }
            ListNode slowPointer = head;
            ListNode fastPointer = head;
            while (fastPointer != null && fastPointer.next != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }
            return slowPointer.value;
        }

        public static void main(String[] args) {
            MiddleOfTheLinkList middleOfTheLinkList = new MiddleOfTheLinkList();
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
            System.out.println("Middle Node: " + middleOfTheLinkList.findTheMiddleOfTheLinkList(head));

            head.next.next.next.next.next = new ListNode(6);
            System.out.println("Middle Node: " + middleOfTheLinkList.findTheMiddleOfTheLinkList(head));

            head.next.next.next.next.next.next = new ListNode(7);
            System.out.println("Middle Node: " + middleOfTheLinkList.findTheMiddleOfTheLinkList(head));
            ListNode nHead = new ListNode(7);
            nHead.next = new ListNode(14);
            nHead.next.next = new ListNode(10);
            nHead.next.next.next = new ListNode(21);
            System.out.println("Middle Node: " + middleOfTheLinkList.findTheMiddleOfTheLinkList(nHead));

        }
    }

    /*Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.

Your algorithm should use constant space and the input LinkedList should be in the original form once the algorithm is finished. The algorithm should have O(N)O(N) time complexity where ‘N’ is the number of nodes in the LinkedList.*/

    static class PalindromeLinkedList {

        public boolean checkIfLinkedListIsAPalindrome(ListNode head) {
            boolean isPalindrome = true;
            printList(head);
            /*find the middle of the linklist*/
            ListNode middleNode = findTheMiddleOfTheLinkList(head);
            ListNode secondHalfHead = reverseLinkedList(middleNode);
            printList(head);
            printList(secondHalfHead);
            ListNode copyOfsecondHalfHead = secondHalfHead;
            ListNode copyOfHead = head;
            while (copyOfHead != null && copyOfsecondHalfHead != null) {
                if (copyOfHead.value != copyOfsecondHalfHead.value) {
                    isPalindrome = false;
                    break;
                }
                copyOfHead = copyOfHead.next;
                copyOfsecondHalfHead = copyOfsecondHalfHead.next;
            }
            /*reverse the list again*/
            reverseLinkedList(secondHalfHead);
            return isPalindrome;
        }

        public ListNode findTheMiddleOfTheLinkList(ListNode head) {
            ListNode slowPointer = head;
            ListNode fastPointer = head;
            while (fastPointer != null && fastPointer.next != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }
            return slowPointer;
        }

        public ListNode reverseLinkedList(ListNode head) {
            ListNode previousNode = null;
            ListNode tmp, currentNode = head;
            while (currentNode != null) {
                tmp = currentNode.next;
                currentNode.next = previousNode;
                previousNode = currentNode;
                currentNode = tmp;
            }
            return previousNode;
        }

        public static void main(String[] args) {
            PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
            ListNode head = new ListNode(2);
            head.next = new ListNode(4);
            head.next.next = new ListNode(6);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(2);
            System.out.println("Is palindrome: " + palindromeLinkedList.checkIfLinkedListIsAPalindrome(head));

            head.next.next.next.next.next = new ListNode(2);
            System.out.println("Is palindrome: " + palindromeLinkedList.checkIfLinkedListIsAPalindrome(head));

            ListNode head2 = new ListNode(1);
            head2.next = new ListNode(0);
            head2.next.next = new ListNode(0);
            head2.next.next.next = new ListNode(1);
            System.out.println("Is palindrome: " + palindromeLinkedList.checkIfLinkedListIsAPalindrome(head2));
        }
    }

    /*Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes from the second half of the LinkedList are inserted alternately to the nodes from the first half in reverse order. So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.

Your algorithm should not use any extra space and the input LinkedList should be modified in-place.*/
    static class LinkedListMergerAlternately {
        public ListNode reverseLinkedList(ListNode head) {
            assert head != null;
            ListNode currentNode = head, prevNode = null, temp;
            while (currentNode != null) {
                temp = currentNode.next;
                currentNode.next = prevNode;
                prevNode = currentNode;
                currentNode = temp;
            }
            return prevNode;
        }

        public ListNode findMiddleNode(ListNode head) {
            assert head != null;
            ListNode slowPointer = head, fastPointer = head;
            while (fastPointer != null && fastPointer.next != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }
            return slowPointer;
        }

        public void insertAlternatively(ListNode head) {
            assert head != null;
            ListNode middleNode = findMiddleNode(head);
            ListNode secondHalfHead = reverseLinkedList(middleNode);
            ListNode firstHalfHead = head, temp1, temp2;
            while (secondHalfHead != null && firstHalfHead != null) {
                temp1 = firstHalfHead.next;
                temp2 = secondHalfHead.next;
                firstHalfHead.next = secondHalfHead;
                secondHalfHead.next = temp1;
                firstHalfHead = temp1;
                secondHalfHead = temp2;
            }
            if (firstHalfHead != null) {
                firstHalfHead.next = null;
            }
        }

        public static void main(String[] args) {
            LinkedListMergerAlternately linkedListMergerAlternately = new LinkedListMergerAlternately();
            ListNode head = new ListNode(2);
            head.next = new ListNode(4);
            head.next.next = new ListNode(6);
            head.next.next.next = new ListNode(8);
            head.next.next.next.next = new ListNode(10);
            head.next.next.next.next.next = new ListNode(12);
            linkedListMergerAlternately.insertAlternatively(head);
            printList(head);
        }
    }

/*We are given an array containing positive and negative numbers. Suppose the array contains a number ‘M’ at a particular index. Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative move backwards ‘M’ indices. You should assume that the array is circular which means two things:

If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.
If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.
Write a method to determine if the array has a cycle. The cycle should have more than one element and should follow one direction which means the cycle should not contain both forward and backward movements.*/

    static class ArrayCycleWithPositiveAndNagativeNumbers {

        public boolean findCycleInArray(int[] arr) {
            assert arr != null;
            boolean isForwardDirection;
            int slow, fast;
            for (int index = 0; index < arr.length; index++) {
                isForwardDirection = arr[index] >= 0;
                slow = index;
                fast = index;
                do {
                    slow = findNextIndext(arr, isForwardDirection, slow);
                    fast = findNextIndext(arr, isForwardDirection, fast);
                    if (fast != -1) {
                        fast = findNextIndext(arr, isForwardDirection, fast);
                    }
                } while (fast != -1 && slow != -1 && slow != fast);
                if (slow != -1 && slow == fast) { /*slow and fast met so there is a loop.*/
                    return true;
                }
            }
            return false;
        }


        public int findNextIndext(int[] arr, boolean isForwardDirection, int indexOfCurrentElement) {
            boolean currentDirection = arr[indexOfCurrentElement] >= 0;
            if (isForwardDirection != currentDirection) {
                /*Direction change check: here original direction (isForwardDirection) is changing. So exit - cycle should follow only one direction.*/
                return -1;
            }
            int nextIndex = (indexOfCurrentElement + arr[indexOfCurrentElement]) % arr.length;
            if (nextIndex < 0) {
                nextIndex = nextIndex + arr.length;
            }
            /*Check for one element - ex. if arr.length == 5 and you move index by 5 then you will end at same index*/
            if (nextIndex == indexOfCurrentElement) {
                return -1;
            }
            return nextIndex;
        }

        public static void main(String[] args) {
            ArrayCycleWithPositiveAndNagativeNumbers arrayCycleWithPositiveAndNagativeNumbers = new ArrayCycleWithPositiveAndNagativeNumbers();
            System.out.println(arrayCycleWithPositiveAndNagativeNumbers.findCycleInArray(new int[]{1, 2, -1, 2, 2}));
            System.out.println(arrayCycleWithPositiveAndNagativeNumbers.findCycleInArray(new int[]{2, 2, -1, 2}));
            System.out.println(arrayCycleWithPositiveAndNagativeNumbers.findCycleInArray(new int[]{2, 1, -1, -2}));
        }


    }


}
