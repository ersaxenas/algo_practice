package com.lrn.educative.crust;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree {
    public BinaryTreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(BinaryTreeNode head) {
        root = head;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BinaryTreeNode insert(int data) {
        if (root == null) {
            root = new BinaryTreeNode(data);
            return root;
        }
        return insertNodeRecursive(root, data);
    }

    public BinaryTreeNode insertNodeRecursive(BinaryTreeNode node, int data) {
        // base case
        if (node == null) {
            return new BinaryTreeNode(data);
        }
        // recursive
        if (data < node.getData()) {
            node.setLeft(insertNodeRecursive(node.getLeft(), data));
        } else {
            node.setRight(insertNodeRecursive(node.getRight(), data));
        }
        return node;
    }

    public BinaryTreeNode find(int data) {
        if(isEmpty()) {
            return null;
        }
        BinaryTreeNode node = root;
        while(node != null) {
            if(data < node.data) {
                node = node.left;
            } else if(data > node.data) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public boolean delete(int data) {
        if(isEmpty()) {
            return false;
        }
        // find node with data and its parent
        BinaryTreeNode node=root, parent=null;
        while(node != null && node.data != data) {
            parent = node;
             if(data < node.data) {
                 node = node.left;
             } else {
                 node = node.right;
             }
        }
        if(node == null) {
            return false; // node not found
        }

        boolean isLeftChildOfParent = node.data < parent.data;
        boolean isRootNode = node.data == root.data;

        if(node.left == null && node.right == null) { // no child
            if(isRootNode) { // if root node
                root = null;
                return true;
            }

            if(isLeftChildOfParent) { // leaf node
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if(node.left == null) { // only right child
            if(isRootNode) {
               root = node.right;
               return true;
            }

            if(isLeftChildOfParent) { //  node with right child
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }

        } else if(node.right == null) { // only left child
               if(isRootNode) {
                   root = node.left;
                   return true;
               }

               if(isLeftChildOfParent) {
                   parent.left = node.left;
               } else {
                   parent.left = node.right;
               }

        } else { // both children are present
               // find smallest node in the right subtree of node
               BinaryTreeNode smallestNode = findSmallest(node);
               delete(smallestNode.data);
               node.data = smallestNode.data;
        }
       return true;
    }


    public BinaryTreeNode findSmallest(BinaryTreeNode node) {
        BinaryTreeNode currNode = node;
        if(node == null) {
            return node;
        }
        while(currNode.left != null) {
            currNode = currNode.left;
        }
        return currNode;
    }

    public static BinaryTreeNode insert(BinaryTreeNode root, int d) {

        BinaryTreeNode pNew = new BinaryTreeNode(d);
        if (root == null) {
            return pNew;
        }

        BinaryTreeNode parent = null;
        BinaryTreeNode pTemp = root;
        while (pTemp != null) {
            parent = pTemp;
            if (d <= pTemp.data) {
                pTemp = pTemp.left;
            } else {
                pTemp = pTemp.right;
            }
        }

        if (d <= parent.data) {
            parent.left = pNew;
            pNew.parent = parent;
        } else {
            parent.right = pNew;
            pNew.parent = parent;
        }
        return root;
    }

    public static BinaryTreeNode insert_BT(BinaryTreeNode root, int d) {

        BinaryTreeNode pNew = new BinaryTreeNode(d);
        if (root == null) {
            return pNew;
        }

        BinaryTreeNode parent = null;
        BinaryTreeNode pTemp = root;
        while (pTemp != null) {
            parent = pTemp;
            if (d <= pTemp.data) {
                pTemp = pTemp.left;
            } else {
                pTemp = pTemp.right;
            }
        }

        Random generator = new Random();
        int dir = generator.nextInt(1000);

        if (dir%2 == 0) {
            parent.left = pNew;
            pNew.parent = parent;
        } else {
            parent.right = pNew;
            pNew.parent = parent;
        }
        return root;
    }

    public static BinaryTreeNode findInBst(BinaryTreeNode root, int d){
        if(root == null)
            return null;

        if(root.data == d) {
            return root;
        }
        else if(root.data > d){
            return findInBst(root.left,d);
        }
        else{
            return findInBst(root.right,d);
        }
    }


    public static void displayInorder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        displayInorder(node.left);
        System.out.print(node.data + ", ");
        displayInorder(node.right);
    }

    public static void getInorder(BinaryTreeNode node, List<Integer> output) {
        if (node == null) {
            return;
        }
        getInorder(node.left, output);
        output.add(node.data);
        getInorder(node.right, output);
    }

    public static void getPreorder(BinaryTreeNode node, List<Integer> output) {
        if (node == null) {
            return;
        }
        output.add(node.data);
        getPreorder(node.left, output);
        getPreorder(node.right, output);
    }

    public static BinaryTreeNode createBST(List<Integer> l) {
        BinaryTreeNode root = null;
        for (Integer x : l) {
            root = insert(root, x);
        }
        return root;
    }

    public static BinaryTreeNode createRandomBST(int count, int max_value) {
        BinaryTreeNode root = null;
        for (int i = 0; i < count; ++i) {
            Random generator = new Random();
            root = insert(root, generator.nextInt(max_value));
        }
        return root;
    }

    public static BinaryTreeNode createRandomBST(int count) {
        return createRandomBST(count, Integer.MAX_VALUE);
    }

    public static BinaryTreeNode createBinaryTree(int count) {
        BinaryTreeNode root = null;
        for (int i = 0; i < count; ++i) {
            Random generator = new Random();
            root = insert_BT(root, generator.nextInt(100));
        }
        return root;
    }

    private static void populateParentsRec(BinaryTreeNode root, BinaryTreeNode parent) {
        if (root == null) {
            return;
        }

        root.parent = parent;

        populateParentsRec(root.left, root);
        populateParentsRec(root.right, root);
    }

    public static void populate_parents(BinaryTreeNode root) {
        populateParentsRec(root, null);
    }

    public static void bstToArraylistRec(BinaryTreeNode root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        bstToArraylistRec(root.left, arr);
        arr.add(root.data);
        bstToArraylistRec(root.right, arr);
    }

    public static ArrayList<Integer> bst_to_arraylist(BinaryTreeNode root) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        bstToArraylistRec(root, arr);
        return arr;
    }

    public static void anotherDisplayTree(BinaryTreeNode root, int tabs) {
        if (root != null) {
            int i;

            if (root.left != null) {
                for(i = 0; i < tabs+4; ++i) {
                    System.out.print(" ");
                }
                System.out.print("L" + root.left.data + "\n");
            }

            if (root.right != null) {
                for(i = 0; i < tabs+4; ++i) {
                    System.out.print(" ");
                }
                System.out.print("R" + root.right.data + "\n");
            }

            anotherDisplayTree(root.left, tabs + 4);
            anotherDisplayTree(root.right, tabs + 4);
        }
    }

    public static void anotherDisplayTree(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.data + "\n");
            anotherDisplayTree(root, 0);
        }
    }

    public static void displayLevelOrder(BinaryTreeNode root) {
        if(root == null)
            return;

        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<BinaryTreeNode>();
        queue.addLast(root);

        while(!queue.isEmpty()){
            BinaryTreeNode temp = queue.removeFirst();
            System.out.print(temp.data + ",");

            if(temp.left != null) {
                queue.addLast(temp.left);
                //System.out.println(temp.left.data + "LEFT");
            }

            if(temp.right != null){
                queue.addLast(temp.right);
                //System.out.println(temp.right.data + "RIGHT");
            }
        }
        System.out.println();
    }

    public static List<Integer> getLevelOrder(BinaryTreeNode root) {

        List<Integer> output = new ArrayList<Integer>();

        if(root == null)
            return output;

        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<BinaryTreeNode>();
        queue.addLast(root);

        while(!queue.isEmpty()){
            BinaryTreeNode temp = queue.removeFirst();
            // System.out.print(temp.data + ", ");
            output.add(temp.data);

            if(temp.left != null) {
                queue.addLast(temp.left);
                //System.out.println(temp.left.data + "LEFT");
            }

            if(temp.right != null){
                queue.addLast(temp.right);
                //System.out.println(temp.right.data + "RIGHT");
            }
        }
        return output;
    }

    public static boolean isIdenticalTree(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        else if (root1 != null && root2 != null) {
            return ((root1.data == root2.data) &&
                    isIdenticalTree(root1.left, root2.left) &&
                    isIdenticalTree(root1.right, root2.right));
        }
        else {
            return false;
        }
    }


}
