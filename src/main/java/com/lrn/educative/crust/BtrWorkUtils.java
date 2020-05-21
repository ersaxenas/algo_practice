package com.lrn.educative.crust;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BtrWorkUtils {
    /*Check if Two Binary Trees are Identical*/
    static class IdenticalTree {
        public boolean isIdentical(BinaryTreeNode root1, BinaryTreeNode root2) {
            if (root1 == null && root2 == null) {
                return true;
            } else if (root1 != null && root2 != null) {
                return (root1.data == root2.data) && isIdentical(root1.left, root2.left) && isIdentical(root2.right, root2.right);
            } else {
                return false;
            }
        }

        public static void main(String[] args) {
            IdenticalTree identicalTree = new IdenticalTree();
            List<Integer> input1 = new ArrayList<Integer>();
            input1.add(100);
            input1.add(50);
            input1.add(200);
            input1.add(25);
            input1.add(125);
            input1.add(250);
            BinaryTreeNode root1 = BinaryTree.createBST(input1);

            List<Integer> input2 = new ArrayList<Integer>();
            input2.add(1);
            input2.add(2);
            input2.add(10);
            input2.add(50);
            input2.add(180);
            input2.add(199);
            BinaryTreeNode root2 = BinaryTree.createBST(input2);

            BinaryTree.displayLevelOrder(root1);

            BinaryTree.displayLevelOrder(root2);

            if (identicalTree.isIdentical(root1, root2)) {
                System.out.println("The trees are identical");
            } else {
                System.out.println("The trees are not identical");
            }
        }
    }

    /*Write an In-Order Iterator for a Binary Tree*/
    static class InOrderIterator {
        Stack<BinaryTreeNode> stack = new Stack<>();

        public Iterator<BinaryTreeNode> getInOrderIterator(BinaryTreeNode root) {
            populateStack(root);
            Iterator<BinaryTreeNode> inOderIterator = new Iterator<BinaryTreeNode>() {
                @Override
                public boolean hasNext() {
                    return !stack.isEmpty();
                }

                @Override
                public BinaryTreeNode next() {
                    if (stack.isEmpty()) {
                        return null;
                    }
                    BinaryTreeNode node = stack.pop();
                    populateStack(node.right);
                    return node;
                }
            };
            return inOderIterator;
        }

        public void populateStack(BinaryTreeNode node) {
            if (node == null) {
                return;
            }
            stack.push(node);
            populateStack(node.left);
        }

        public String inorderUsingIterator(BinaryTreeNode root) {
            Iterator<BinaryTreeNode> iter = getInOrderIterator(root);
            String result = "";
            while (iter.hasNext()) {
                result += iter.next().data + " ";
            }
            return result;
        }

        public static void main(String[] args) {
            List<Integer> input = new ArrayList<>();
            input.add(100);
            input.add(50);
            input.add(200);
            input.add(25);
            input.add(75);
            input.add(125);
            input.add(300);
            input.add(12);
            input.add(35);
            input.add(60);
            BinaryTreeNode root = BinaryTree.createBST(input);
            InOrderIterator inOrderIterator = new InOrderIterator();
            System.out.print("Inorder Iterator = ");
            System.out.println(inOrderIterator.inorderUsingIterator(root));
        }
    }

    /*Iterative In-Order Traversal of Binary Tree*/
    /*
     * Find the value dd in BST.
     * If dd has a right child then the left most child in right child’s subtree will be the in-order successor of dd. This would also be the child with the minimum value in that subtree.
     * If dd has no right child then:
     * in-order successor is NULL if dd is right most node in the BST i.e. last node in the in-order traversal.
     * in-order successor is the node with minimum value higher than dd in the parent chain of d.
     * */
    static class InOrderTraversalIterative {
        public String inOrderTraversal(BinaryTreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sbr = new StringBuilder();
            /*left -> root -> right*/
            Stack<BinaryTreeNode> stack = new Stack<>();
            BinaryTreeNode currNode = root;
            while (!stack.isEmpty() || currNode != null) {
                if (currNode != null) {
                    stack.push(currNode);
                    currNode = currNode.left;
                } else {
                    currNode = stack.pop();
                    System.out.println(currNode.data);
                    sbr.append(currNode.data).append(" ");
                    currNode = currNode.right;
                }
            }
            sbr.setLength(sbr.length() - 1);
            return sbr.toString();
        }

        public String inOrderTraversal2(BinaryTreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sbr = new StringBuilder();
            /*left -> root -> right*/
            Stack<BinaryTreeNode> stack = new Stack<>();
            BinaryTreeNode currNode = root;
            while (!stack.isEmpty() || currNode != null) {
                if (currNode == null) {
                    currNode = stack.pop();
                    System.out.println(currNode.data);
                    sbr.append(currNode.data).append(" ");
                    currNode = currNode.right;
                } else {
                    stack.push(currNode);
                    currNode = currNode.left;
                }
            }
            sbr.setLength(sbr.length() - 1);
            return sbr.toString();
        }

        public static void main(String[] args) {
            InOrderTraversalIterative inOrderTraversalIterative = new InOrderTraversalIterative();
            List<Integer> input = new ArrayList<>();
            input.add(100);
            input.add(50);
            input.add(200);
            input.add(25);
            input.add(75);
            input.add(125);
            input.add(350);
            BinaryTreeNode root = BinaryTree.createBST(input);
            System.out.print("Inorder Iterative Traversal= ");
            inOrderTraversalIterative.inOrderTraversal(root);
            System.out.println();
        }
    }

    /*In-order Successor of Binary Search Tree*/
    static class InOrderSuccessor {
        public BinaryTreeNode inOrderSuccessor(BinaryTreeNode root, int nodeValue) {
            if (root == null) {
                return null;
            }
            BinaryTreeNode currNode = root, successor = null;
            while (currNode != null) {
                if (nodeValue < currNode.data) { // got left
                    successor = currNode;
                    currNode = currNode.left;
                } else if (nodeValue > currNode.data) { // go right
                    currNode = currNode.right;
                } else { // node found
                    if (currNode.right != null) {
                        return findMin(currNode.right);
                    }
                    return successor;
                }
            }
            return null;
        }

        public BinaryTreeNode findMin(BinaryTreeNode node) {
            BinaryTreeNode currNode = node;
            while (currNode != null && currNode.left != null) {
                currNode = currNode.left;
            }
            return currNode;
        }

        public static void main(String[] args) {
            InOrderSuccessor inOrderSuccessor = new InOrderSuccessor();
            ArrayList<Integer> origData = new ArrayList<>();
            origData.add(100);
            origData.add(50);
            origData.add(200);
            origData.add(25);
            origData.add(75);
            origData.add(125);
            origData.add(350);
            BinaryTreeNode root = BinaryTree.createBST(origData);

            ArrayList<Integer> allData = BinaryTree.bst_to_arraylist(root);

            for (Integer d : origData) {
                BinaryTreeNode successor = inOrderSuccessor.inOrderSuccessor(root, d);
                int i = allData.indexOf(d);
                Integer expectedVal = null;
                if (i < allData.size() - 1) {
                    expectedVal = allData.get(i + 1);
                }

                if (successor != null) {
                    if (expectedVal.intValue() != successor.data) {
                        System.out.println("*******" + d + " ==== " + expectedVal + ", " + successor.data + "*****");
                        //System.out.println(expectedVal.intValue() == successor.data);
                    }
                } else {
                    //System.out.println(successor == null);
                }
                if (successor != null) {
                    System.out.print("(" + d + ", " + successor.data + ") ");
                } else {
                    System.out.print("(" + d + ", null) ");
                }
            }
        }
    }

    /*Level Order Traversal of Binary Tree*/
    static class LevelOrderTraversalBt {
        /*bfs*/
        public String levelOrderTraversal1(BinaryTreeNode root) {
            if (root == null) {
                return null;
            }
            StringBuilder sbr = new StringBuilder();
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int currQueueSize = queue.size();
                for (int idx = 0; idx < currQueueSize; idx++) {
                    BinaryTreeNode node = queue.poll();
                    sbr.append(node.data).append(" ");
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return sbr.toString();
        }

        public static void main(String[] args) {
            LevelOrderTraversalBt levelOrderTraversalBt = new LevelOrderTraversalBt();
            List<Integer> input = new ArrayList<Integer>();
            input.add(100);
            input.add(50);
            input.add(200);
            input.add(25);
            input.add(75);
            input.add(350);
            BinaryTreeNode root = BinaryTree.createBST(input);
            System.out.println("Level Order Traversal: " + levelOrderTraversalBt.levelOrderTraversal1(root));
        }
    }

    /*Is a Binary Search Tree Valid?*/
    static class ValidBTCheck {
        public boolean isValidBSTRecursive(BinaryTreeNode node, int min, int max) {
            if (node == null) {
                return true;
            }
            if (node.data < min && node.data > max) { // out of bounds
                return false;
            }
            return isValidBSTRecursive(node.left, min, node.data) && isValidBSTRecursive(node.right, node.data, max);
        }

        /*when cannot define min and max bounds: inorder traversal of BST is sorted : so check if current node is greater than previous*/
        BinaryTreeNode prevNode = null;

        public boolean isValidBST2Recursive(BinaryTreeNode node) {
            // base case
            if (node == null) {
                return true;
            }

            // recursive case
            if (!isValidBST2Recursive(node.left)) {
                return false;
            }
            // check
            if (prevNode != null && prevNode.data > node.data) {
                return false;
            }
            prevNode = node;

            if (!isValidBST2Recursive(node.right)) {
                return false;
            }
            return true;
        }

        /*when cannot define min and max bounds: inorder traversal of BST is sorted : brute force*/
        public boolean isValidBSTBruteForce(BinaryTreeNode node) {
            if (node == null) {
                return false;
            }
            List<Integer> lst = new ArrayList<>();
            BinaryTreeNode currNode = node, prevNode = null;
            Stack<BinaryTreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || currNode != null) {
                if (currNode == null) {
                    currNode = stack.pop();
                    if (prevNode != null && prevNode.data > currNode.data) {
                        return false;
                    }
                    prevNode = currNode;
                    currNode = currNode.right;
                } else {
                    stack.push(currNode);
                    currNode = currNode.left;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            ValidBTCheck validBTCheck = new ValidBTCheck();
            BinaryTreeNode root = new BinaryTreeNode(100);
            BinaryTree.insert(root, 50);
            BinaryTree.insert(root, 200);
            BinaryTree.insert(root, 25);
            // Add a node at an incorrect position
            BinaryTree.insert(root, 125);
            BinaryTree.insert(root, 150);
            BinaryTree.insert(root, 300);

            BinaryTree.displayInorder(root);
            System.out.println();
            System.out.println(validBTCheck.isValidBSTRecursive(root, Integer.MAX_VALUE, Integer.MAX_VALUE));
            System.out.println(validBTCheck.isValidBSTBruteForce(root));
            System.out.println(validBTCheck.isValidBST2Recursive(root));
        }
    }

    /*Convert Binary Tree to Doubly Linked List*/
    static class BTtoDoublyLinkedList {
        public BinaryTreeNode convertToDoubleLinkedList(BinaryTreeNode root) {
            if (root == null) {
                return null;
            }
            inOrderTraversalRecursive(root);
            return head;
        }

        BinaryTreeNode prevNode = null, head = null;

        public void inOrderTraversalRecursive(BinaryTreeNode node) {
            //base
            if (node == null) {
                return;
            }

            //recursive
            inOrderTraversalRecursive(node.left);
            System.out.println(node.data);
            if (prevNode != null) {
                prevNode.right = node;
                node.left = prevNode;
            } else {
                head = node;
            }
            prevNode = node;
            inOrderTraversalRecursive(node.right);
        }

        public static void main(String[] args) {
            BTtoDoublyLinkedList bTtoDoublyLinkedList = new BTtoDoublyLinkedList();
            List<Integer> input = new ArrayList<Integer>();
            input.add(100);
            input.add(50);
            input.add(200);
            input.add(25);
            input.add(75);
            input.add(125);
            input.add(350);
            input.add(30);
            input.add(60);
            BinaryTreeNode root = BinaryTree.createBST(input);

            BinaryTreeNode head = bTtoDoublyLinkedList.convertToDoubleLinkedList(root);
            System.out.println();
            while (head != null) {
                System.out.println(((head.left != null) ? "[" + head.left.data + "] <- [" : "[null] <- [") + head.data + "] -> " + ((head.right != null) ? "[" + head.right.data + "]" : "[null]"));
                head = head.right;
            }
        }
    }

    /*Print Tree Perimeter*/
    static class TreePerimeter {
        public String perimeterBruteForce(BinaryTreeNode root) {
            if (root == null) {
                return "";
            }
            List<String> nodeList = new ArrayList<>();
            nodeList.add(String.valueOf(root.data));
            getLeftSideView(root.left, nodeList);
            getLeafNodes(root.left, nodeList);
            getLeafNodes(root.right, nodeList);
            getRightSideView(root.right, nodeList);
            return String.join(",", nodeList);
        }

        public void getLeftSideView(BinaryTreeNode node, List<String> lst) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) { // ignore leaf nodes
                return;
            }
            lst.add(String.valueOf(node.data));
            if (node.left == null) { // if no left child then try right child
                getLeftSideView(node.right, lst);
            } else {
                getLeftSideView(node.left, lst);
            }
        }

        public void getRightSideView(BinaryTreeNode node, List<String> lst) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) { // ignore leaf nodes
                return;
            }
            lst.add(String.valueOf(node.data));
            if (node.right == null) { // if no right child then try right left
                getRightSideView(node.left, lst);
            } else {
                getLeftSideView(node.right, lst);
            }
        }

        public void getLeafNodes(BinaryTreeNode node, List<String> lst) {
            if (node == null) {
                return;
            }
            if (node.left == null && node.right == null) { // ignore leaf nodes
                lst.add(String.valueOf(node.data));
            }
            getLeafNodes(node.left, lst);
            getLeafNodes(node.right, lst);
        }


        public static void main(String[] args) {
            TreePerimeter treePerimeter = new TreePerimeter();
            List<Integer> arr = new ArrayList<Integer>();
            arr.add(100);
            arr.add(50);
            arr.add(200);
            arr.add(25);
            arr.add(60);
            arr.add(350);
            arr.add(10);
            arr.add(70);
            arr.add(400);
            BinaryTreeNode root = BinaryTree.createBST(arr);
            BinaryTree.displayLevelOrder(root);
            BinaryTree.anotherDisplayTree(root);
            System.out.print("Perimeter:\n");
            System.out.println(treePerimeter.perimeterBruteForce(root));
        }
    }

    /*Connect Same Level Siblings of a Binary Tree*/
    static class LevelSiblingConnector {
        public void connect1(BinaryTreeNode root) {
            if (root == null) {
                return;
            }
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode prevNode = null;
                int currentQueueSize = queue.size();
                for (int idx = 0; idx < currentQueueSize; idx++) {
                    BinaryTreeNode node = queue.poll();
                    if (prevNode != null) {
                        prevNode.next = node;
                    }
                    prevNode = node;
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }

        public void connectNextLevel(BinaryTreeNode head) {
            BinaryTreeNode currentLevelHead = head;
            if (currentLevelHead == null) {
                return;
            }
            BinaryTreeNode preNode = null;
            while (currentLevelHead != null) {
                if (currentLevelHead.left != null && currentLevelHead.right != null) { // has both children
                    // connect siblings of currentLevelHead
                    currentLevelHead.left.next = currentLevelHead.right;
                    //connect prev node
                    if (preNode != null) {
                        preNode.next = currentLevelHead.left;
                    }
                    preNode = currentLevelHead.right;
                } else if (currentLevelHead.left != null) { // has only left child
                    //connect prev node
                    if (preNode != null) {
                        preNode.next = currentLevelHead.left;
                    }
                    preNode = currentLevelHead.left;
                } else if (currentLevelHead.right != null) { // has only right child
                    //connect prev node
                    if (preNode != null) {
                        preNode.next = currentLevelHead.right;
                    }
                    preNode = currentLevelHead.right;
                }
                currentLevelHead = currentLevelHead.next;
            }
        }

        public void connect2(BinaryTreeNode node) {
            if (node == null) {
                return;
            }
            connectNextLevel(node);
            if (node.left != null) {
                connect2(node.left);
            } else {
                connect2(node.right);
            }
        }

        List<Integer> getLevelOrderUsingNext(BinaryTreeNode root) {
            List<Integer> output = new ArrayList<Integer>();
            while (root != null) {
                BinaryTreeNode head = root;
                BinaryTreeNode next_head = null;
                while (head != null) {
                    output.add(head.data);

                    if (next_head == null) {
                        next_head = head.left != null ? head.left : head.right;
                    }
                    head = head.next;
                }
                root = next_head;
            }
            return output;
        }

        public static void main(String[] args) {
            LevelSiblingConnector levelSiblingConnector = new LevelSiblingConnector();
            List<Integer> v = new ArrayList<>();
            v.add(100);
            v.add(50);
            v.add(200);
            v.add(25);
            v.add(75);
            v.add(300);
            v.add(10);
            v.add(350);
            v.add(15);

            BinaryTreeNode head = BinaryTree.createBST(v);

            List<Integer> v1 = BinaryTree.getLevelOrder(head);

            //levelSiblingConnector.connect1(head);
            levelSiblingConnector.connect2(head);

            List<Integer> result = levelSiblingConnector.getLevelOrderUsingNext(head);

            System.out.println(result);
            System.out.println(head.next); //null
            System.out.println(head.left.next.data); //200
            System.out.println(head.left.right.next.data); //300
            System.out.println(head.right.right.next); //null
        }
    }

    /*Connect All Siblings of a Binary Tree*/
    static class AllSiblingConnector {
        public void connect1(BinaryTreeNode root) {
            if (root == null) {
                return;
            }
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.add(root);
            BinaryTreeNode preNode = null;
            while (!queue.isEmpty()) {
                int currentQueueSize = queue.size();
                for (int idx = 0; idx < currentQueueSize; idx++) {
                    BinaryTreeNode node = queue.poll();
                    //connect
                    if (preNode != null) {
                        preNode.next = node;
                    }
                    preNode = node;
                    // add child nodes to queue
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }
        public void connectNextLevel(BinaryTreeNode head) {
            if(head == null) {
                return;
            }
            BinaryTreeNode currentLevelHead = head, nextLevelHead=null, prevNode=null;
            while(currentLevelHead != null) {
                if(currentLevelHead.left != null && currentLevelHead.right != null) { //both child are present
                    // connect left to right
                    currentLevelHead.left.next = currentLevelHead.right;
                    // connect previous node
                    if(prevNode != null) {
                        prevNode.next = currentLevelHead.left;
                    }
                    prevNode = currentLevelHead.right;
                    //save nextLevel head
                    if(nextLevelHead == null) {
                        nextLevelHead = currentLevelHead.left;
                    }
                } else if(currentLevelHead.left != null) { // only left child is presnt
                    // connect previous node
                    if(prevNode != null) {
                        prevNode.next = currentLevelHead.left;
                    }
                    prevNode = currentLevelHead.left;
                    if(nextLevelHead == null) {
                        nextLevelHead = currentLevelHead.left;
                    }
                } else if(currentLevelHead.right != null) { // only right child is present
                    // connect previous node
                    if(prevNode != null) {
                        prevNode.next = currentLevelHead.right;
                    }
                    prevNode = currentLevelHead.right;
                    if(nextLevelHead == null) {
                        nextLevelHead = currentLevelHead.right;
                    }
                }
                currentLevelHead = currentLevelHead.next;
            }
            head.next = nextLevelHead;
        }

        public void connect2(BinaryTreeNode node) {
            if(node == null) {
                return;
            }
            if(node.left != null) {
                connect2(node.left);
            } else {
                connect2(node.right);
            }
        }


        public  List<Integer> getLevelOrderTraversalWithSibling(BinaryTreeNode root)
        {
            List<Integer> l = new ArrayList<Integer>();
            while(root != null) {
                l.add(root.data);
                root = root.next;
            }
            return l;
        }

        public static void main(String[] args) {
            AllSiblingConnector allSiblingConnector = new AllSiblingConnector();
            List<Integer> input = new ArrayList<Integer>();
            input.add(100);input.add(50);input.add(200);input.add(25);input.add(75);input.add(125);input.add(350);
            BinaryTreeNode root = BinaryTree.createBST(input);

            allSiblingConnector.connect1(root);
            List<Integer> l2 = allSiblingConnector.getLevelOrderTraversalWithSibling(root);
            System.out.println(l2);

            allSiblingConnector.connect2(root);
            l2 = allSiblingConnector.getLevelOrderTraversalWithSibling(root);
            System.out.println(l2);
        }
    }
    /*Serialize/Deserialize Binary Tree*/
    static class SerializeAndDeserializeBTree {
        int MARKER = Integer.MIN_VALUE;
        /*preorder traversal*/
        public void serialize(BinaryTreeNode node, ObjectOutputStream stream) throws IOException {
            if(node == null) {
                stream.writeInt(MARKER);
                return;
            }
            stream.writeInt(node.data);
            serialize(node.left, stream);
            serialize(node.right,stream);
        }

        public BinaryTreeNode deserialize(ObjectInputStream stream) throws IOException {
            int data = stream.readInt();
            if(data == MARKER) {
                return null;
            }
            BinaryTreeNode node = new BinaryTreeNode(data);
            node.left = deserialize(stream);
            node.right = deserialize(stream);
            return  node;
        }

        public static void main(String[] args) {
            SerializeAndDeserializeBTree serializeAndDeserializeBTree = new SerializeAndDeserializeBTree();
            try{
                List<Integer> input = new ArrayList<Integer>();
                input.add(100);input.add(50);input.add(200);input.add(25);input.add(75);input.add(125);input.add(350);
                BinaryTreeNode root = BinaryTree.createBST(input);

                BinaryTree.displayLevelOrder(root);

                ByteArrayOutputStream baostream = new ByteArrayOutputStream();
                ObjectOutputStream stream = new ObjectOutputStream(baostream);
                serializeAndDeserializeBTree.serialize(root, stream);
                stream.close();

                ByteArrayInputStream baistream = new ByteArrayInputStream(baostream.toByteArray());
                ObjectInputStream  istream = new ObjectInputStream(baistream);
                BinaryTreeNode rootDeserialized = serializeAndDeserializeBTree.deserialize(istream);

                System.out.println("\nResult:");
                BinaryTree.displayLevelOrder(rootDeserialized);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    /*Nth Highest Number in Binary Search Tree*/
    static class NthHighestNumberInBST {
        int counter = 0;
        public BinaryTreeNode nHighestNode(BinaryTreeNode node, int K) {
           return nHighestNodeRecursive(node,K);
        }

        public BinaryTreeNode nHighestNodeRecursive(BinaryTreeNode node, int K) {
            if(node == null) {
                return null;
            }
            BinaryTreeNode currNode = nHighestNode(node.right, K);
            if(currNode != null) {
                return currNode;
            }
            counter++;
            if(counter == K) {
                return node;
            }
            return nHighestNode(node.left, K);
        }
    }

    public BinaryTreeNode nHighestNode2Rec(BinaryTreeNode node, int N) {
        if(node == null) {
            return null;
        }
        int leftCnt = 0;
        if(node.left != null) {
            leftCnt = node.left.count;
        }
        int K = node.count - leftCnt;
        if(K == N) {
            return node;
        } else if(K > N) {
            return nHighestNode2Rec(node.right, N);
        } else {
            return nHighestNode2Rec(node.left, N);
        }
    }

    public static void main(String[] args) {
        NthHighestNumberInBST nthHighestNumberInBST = new NthHighestNumberInBST();
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.count = 7;
        BinaryTree.insert(root, 12);
        BinaryTreeNode node12 = BinaryTree.findInBst(root, 12);
        node12.count = 3;
        BinaryTree.insert(root, 3);
        BinaryTreeNode node3 = BinaryTree.findInBst(root, 3);
        node3.count = 3;
        BinaryTree.insert(root, 4);
        BinaryTreeNode node4 = BinaryTree.findInBst(root, 4);
        node4.count = 2;
        BinaryTree.insert(root, 8);
        BinaryTreeNode node8 = BinaryTree.findInBst(root, 8);
        node8.count = 1;
        BinaryTree.insert(root, 18);
        BinaryTreeNode node18 = BinaryTree.findInBst(root, 18);
        node18.count = 2;
        BinaryTree.insert(root, 16);
        BinaryTreeNode node16 = BinaryTree.findInBst(root, 16);
        node16.count = 1;
        BinaryTree.displayInorder(root);
        System.out.println();
        int n = 2;
        nthHighestNumberInBST.counter = 0;
        BinaryTreeNode nth_highest_node = nthHighestNumberInBST.nHighestNode(root, n);
        System.out.println(nth_highest_node.data);
        n = 1;
        nthHighestNumberInBST.counter = 0;
        nth_highest_node = nthHighestNumberInBST.nHighestNode(root, n);
        System.out.println(nth_highest_node.data);
        n = 5;
        nthHighestNumberInBST.counter = 0;
        nth_highest_node = nthHighestNumberInBST.nHighestNode(root, n);
        System.out.println(nth_highest_node.data);
        n = 30;
        nthHighestNumberInBST.counter = 0;
        nth_highest_node = nthHighestNumberInBST.nHighestNode(root, n);
        String val = nth_highest_node == null ? "null" : String.valueOf(nth_highest_node.data);
        System.out.println(val);
    }

    /*Mirror Binary Tree Nodes*/
    static class MirrorBT {
        public void mirrorTreeDFS(BinaryTreeNode node) {
            if(node == null) {
                return;
            }
            BinaryTreeNode tmp = node.right;
            node.right = node.left;
            node.left = tmp;
            mirrorTreeDFS(node.left);
            mirrorTreeDFS(node.right);
        }

        public static void main(String[] args) {
            MirrorBT mirrorBT = new MirrorBT();
            List<Integer> input1 = new ArrayList<>();
            input1.add(20);input1.add(50);input1.add(200);input1.add(75);input1.add(25);input1.add(300);
            BinaryTreeNode root  = BinaryTree.createBST(input1);
            BinaryTree.displayLevelOrder(root);
            System.out.println();
            mirrorBT.mirrorTreeDFS(root);
            System.out.println("Mirrored Tree:");
            BinaryTree.displayLevelOrder(root);
        }
    }
   /*Delete Zero Sum Sub-Trees*/
    static class DeleteZoreSumSubTree {
        public Integer sumSubTree(BinaryTreeNode node) {
            // base case
            if(node == null) {
                return 0;
            }
            int sumLeft = sumSubTree(node.left);
            if(sumLeft == 0) {
                node.left = null;
            }
            int sumRight = sumSubTree(node.right);
            if(sumRight == 0) {
                node.right = null;
            }
            return node.data + sumLeft + sumRight;
        }
        public BinaryTreeNode DeleteZeroSumSubTree(BinaryTreeNode node) {
            if(node == null) {
                return null;
            }
            Integer treeSum = sumSubTree(node);
            return (treeSum == 0) ? null : node;
        }

       public static void main(String[] args) {
           BinaryTreeNode head = new BinaryTreeNode(7);
           BinaryTreeNode currentHead = head;

           BinaryTreeNode left = new BinaryTreeNode(5);
           BinaryTreeNode right = new BinaryTreeNode(6);//
           currentHead.left = left;
           currentHead.right = right;

           currentHead = head.left;
           left = new BinaryTreeNode(-3);
           right = new BinaryTreeNode(-2);
           currentHead.left = left;
           currentHead.right = right;

           DeleteZoreSumSubTree deleteZoreSumSubTree = new DeleteZoreSumSubTree();

           System.out.println("Level Order Traversal");
           BinaryTree.displayLevelOrder(head);
           System.out.println("Level Order Traversal");
           deleteZoreSumSubTree.DeleteZeroSumSubTree(head);
           BinaryTree.displayLevelOrder(head);
       }
   }
   /*Convert N-ary Tree to Binary Tree*/
    static class NarrTreeToBtree {
       public BinaryTreeNode createBTreeNode(NTreeNode node, boolean isLeft) {
           if(node == null) {
               return null;
           }
           BinaryTreeNode bNode = new BinaryTreeNode(node.data);
           if(!node.children.isEmpty()) {
               BinaryTreeNode currNode = bNode;
               for(NTreeNode child: node.children) {
                   if(child != null) {
                       BinaryTreeNode bChild = createBTreeNode(child, !isLeft);
                       if(isLeft) {
                           currNode.left = bChild;
                       } else {
                           currNode.right = bChild;
                       }
                       currNode = bChild;
                   }
               }
           }
           return bNode;
       }

       public BinaryTreeNode convertToBTree(NTreeNode ntreeNode) {
         return createBTreeNode(ntreeNode, true);
       }

       public NTreeNode createNtreeNode(BinaryTreeNode bTreeNode, boolean isLeft) {
           if(bTreeNode == null) {
               return null;
           }
           NTreeNode ntreeNode = new NTreeNode(bTreeNode.data);

           BinaryTreeNode currNode = bTreeNode;
           if(isLeft) {
               while(currNode != null) {
                    NTreeNode newNtreeNode = createNtreeNode(currNode.left, !isLeft);
                    if(newNtreeNode != null) {
                        ntreeNode.addChild(newNtreeNode);
                    }
                    currNode = currNode.left;
               }
           } else {
               while(currNode != null) {
                   NTreeNode newNtreeNode = createNtreeNode(currNode.right, !isLeft);
                   if(newNtreeNode != null) {
                       ntreeNode.addChild(newNtreeNode);
                   }
                   currNode = currNode.right;
               }
           }
          return ntreeNode;
       }

       public NTreeNode createNTree(BinaryTreeNode binaryTreeNode) {
           return createNtreeNode(binaryTreeNode, true);
       }

       public static void main(String[] args) {
           NTreeNode node1 = new NTreeNode(1);
           NTreeNode node2 = new NTreeNode(2);
           NTreeNode node3 = new NTreeNode(3);
           NTreeNode node4 = new NTreeNode(4);
           node1.children.add(node2);
           node1.children.add(node3);
           node1.children.add(node4);
           NTreeNode node5 = new NTreeNode(5);
           NTreeNode node6 = new NTreeNode(6);
           node3.children.add(node5);
           node3.children.add(node6);

           System.out.println("Original n-ary tree");
           System.out.println(node1.getStringRepresentation(node1));
           NarrTreeToBtree narrTreeToBtree = new NarrTreeToBtree();
           BinaryTreeNode bnode1 = narrTreeToBtree.convertToBTree(node1);
           System.out.println("\nConverted binary tree");
           BinaryTree.displayLevelOrder(bnode1);
           // If the tree is converted into BT then the following statement must return "5"
           // System.out.println("\nRoot.Left.Left.Right = " + bnode1.left.left.right.data);

           // System.out.println("Converting BT to N_ary Tree\n");
           NTreeNode tnode1 = narrTreeToBtree.createNTree(bnode1);
           System.out.println("Converted n-ary tree");
           System.out.println(tnode1.getStringRepresentation(tnode1));
       }
   }

}
