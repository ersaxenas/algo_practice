package com.lrn.educative.dsj.ll;

public class WorkLsUtils {

    static class SinglyLinkedList<T> {
        public class Node {
            public T data;
            public Node nextNode;

            public Node(T data, Node nextNode) {
                this.data = data;
                this.nextNode = nextNode;
            }

            @Override
            public String toString() {
                return "Node{data=" + data + '}';
            }
        }

        public Node head = null, tail = null;
        int size;

        public SinglyLinkedList() {
            size = 0;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public SinglyLinkedList insertAtHead(T data) {
            Node newNode = new Node(data, null);
            newNode.nextNode = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
            return this;
        }

        public SinglyLinkedList insertAtTheEnd(T data) {
            Node newNode = new Node(data, null);
            if (tail != null) {
                tail.nextNode = newNode;
            }
            tail = newNode;
            if (head == null) head = newNode;
            size++;
            return this;
        }

        public boolean insertAfter(T data, T previous) {
            if(isEmpty()) {
                return false;
            }
            Node newNode = new Node(data, null);
            Node currentNode = head;
            while(currentNode != null && currentNode.data.equals(previous)) {
                currentNode = currentNode.nextNode;
            }
            if(currentNode!=null) {
              newNode.nextNode = currentNode.nextNode;
              currentNode.nextNode=newNode;
              size++;
              return true;
            } else {
                return false;
            }
        }

        public boolean searchNode(T data) {
            if(isEmpty()) {
                return false;
            }
            Node currentNode = head;
            while(currentNode!=null) {
                if(currentNode.data.equals(data)) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            }
            return false;
        }

        public boolean deleteAtHead() {
            if(isEmpty()) {
                return false;
            }
            Node node = head;
            head = head.nextNode;
            node.nextNode=null;
            if(head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        public boolean deleteByValue(T data) {
           if(isEmpty()) { return false;}
           if(head.data == data) { return deleteAtHead();}
           Node node = head, previous=null;
           while(node!=null && node.data.equals(data)) {
               previous = node;
               node = node.nextNode;
           }
           if(node!=null) {
               previous.nextNode = node.nextNode;
               if(node == tail) { tail = previous;}
               node.nextNode = null;
               size--;
               return true;
           } else {
               return false;
           }
        }

        public void printList() {
            if (isEmpty()) System.out.println("List is empty.");
            Node tmp = head;
            System.out.print("\nList : ");
            while (tmp != null) {
                System.out.print(tmp.data + " -> ");
                tmp = tmp.nextNode;
            }
            System.out.print("null \n");
        }

        public static void main(String[] args) {
            SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
            ll.insertAtTheEnd(1).insertAtHead(2).insertAtHead(3).insertAtHead(4).insertAtHead(5).insertAtTheEnd(10);
            ll.insertAfter(30 , 3);
            System.out.println(ll.searchNode(30));
            System.out.println(ll.searchNode(22));
            ll.printList();
        }
    }

    static class MiddleNodeOfLL {
        public SinglyLinkedList.Node find(SinglyLinkedList lst) {
            if(lst == null || lst.isEmpty()) {return null;}
            SinglyLinkedList.Node slowPointer = lst.head;
            SinglyLinkedList.Node fastPointer = lst.head;
            while(fastPointer!=null && fastPointer.nextNode!=null) {
                fastPointer = fastPointer.nextNode.nextNode;
                if(fastPointer != null) {
                    slowPointer = slowPointer.nextNode;
                }
            }
            return slowPointer;
        }
        public static void main(String[] args) {
              MiddleNodeOfLL middleNodeOfLL = new MiddleNodeOfLL();
              SinglyLinkedList lst = new SinglyLinkedList();
              lst.insertAtTheEnd(7).insertAtTheEnd(14).insertAtTheEnd(10).insertAtTheEnd(21);
            System.out.println(middleNodeOfLL.find(lst));
        }
    }

    static class NthNodeFromTheEnd {
        public SinglyLinkedList.Node find(SinglyLinkedList<Integer> lst, int n) {
            if(lst == null || lst.isEmpty()) {
                return null;
            }
            SinglyLinkedList.Node pointer1 = lst.head, pointer2 = lst.head;
            for (int idx = 0; idx < n && pointer1 != null; idx++) {
                pointer1 = pointer1.nextNode;
            }
            if(pointer1 == null) {
                return null;
            }
            while(pointer1 != null) {
                pointer1 = pointer1.nextNode;
                pointer2 = pointer2.nextNode;
            }
         return pointer2;
        }
        public static void main(String[] args) {
            NthNodeFromTheEnd nthNodeFromTheEnd = new NthNodeFromTheEnd();
            SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
            sll.printList(); //list is empty
            System.out.println("3rd element from end : " + nthNodeFromTheEnd.find(sll, 3)); //will return null
            for(int i=0; i<15; i+=2){
                sll.insertAtHead(i);
            }
            sll.printList(); // List is 14 -> 12 -> 10 -> 8 -> 6 -> 4 -> 2 -> 0 -> null
            System.out.println("3rd element from end : " + nthNodeFromTheEnd.find(sll, 3)); //will return 4
            System.out.println("10th element from end : " + nthNodeFromTheEnd.find(sll, 10));//will return null
        }
    }

}
