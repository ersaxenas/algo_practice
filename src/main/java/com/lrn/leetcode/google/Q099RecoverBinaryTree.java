package com.lrn.leetcode.google;

public class Q099RecoverBinaryTree {
    /* 2022-01-14T16:04:12.878Z
    * pd: https://leetcode.com/problems/recover-binary-search-tree/
    * Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
    * assm: non null nodes, No. of nodes < 10000
    * appr: morris traversal.
    *
    * */

    /*
     * morris traversal: if don't want to use recursion and don't want to use stack then we can use morris traversal for inorder traversal.
     * Trick here is to temporarily connect a node to its predecessor.
     * how to find predecessor : just keep traversing right until there is no right child present.
     * */

    public void inorderMorrisTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode currNode = null;
        while (root != null) {
            if (root.left != null) { /*predecessor is always on left side in bst.*/
                currNode = root.left;
                while (currNode.right != null && currNode.right != root) { /*current node will be equal to root node when predecessor and node are connected, it means we have visited this branch already.*/
                    currNode = currNode.right; // go to right
                }
                if (currNode.right == null) { // node is not connected to its predecessor so we have NOT visited this node.
                    currNode.right = root; // now connect node with its predecessor
                    root = root.left; // now left node becomes root, so we will traverse left subtree.
                } else { /*node is already connected to its predecessor so we have visited this node*/
                    System.out.println(root.val);
                    currNode.right = null; // disconnect node with its predecessor
                    root = root.right;// traverse right sub tree.
                }
            } else {
                // since no predecessor then visit current node.
                System.out.println(root.val);
                root = root.right; // go to right child. If right child is null then while loop will break
            }
        }
    }

    /*
     * step 1: whenever visiting a node save ref of its previous node.
     * */

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode currNode, previousNode;
        while (root != null) {
            if (root.left != null) {
                currNode = root.left;
                while (currNode.right != null && currNode.right != root) {
                    currNode = currNode.right;
                }
                if (currNode.right == null) {
                    currNode.right = root;
                    root = root.left;
                } else {
                    /*visiting node*/
                    System.out.println(root.val);
                    previousNode = root;//step1 save previous node
                    currNode.right = null;
                    root = root.right; // moving to next node
                }
            } else {
                /*visiting node*/
                System.out.println(root.val);
                previousNode = root;//step1 save previous node
                root = root.right; // moving to next node
            }
        }
    }

    /*
     * Tree is a BST and in inorder traversal of BST a node is greater than its previous node.
     * Function: return previous.value >= current value
     * Ex: 1 2 3 4 5 6 7 8 9
     * When ever two values are swapped above function will not return true for those nodes.
     * When two node are swapped there are two cases
     * 1). nodes are adjacent
     * Ex: 1 2 3 5 4 6 7 8
     * In this case at node 4 Function will return false
     * 2). nodes are not adjacent
     * 1 2 7 4 5 6 3 8 9
     * In this case at node 7 and 3 Function will return false;
     *
     * Step 2: so save previous node and root.
     *
     * */

    public void recoverTree2(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode currNode, previousNode = null, node1 = null, node2 = null;
        while (root != null) {
            if (root.left != null) {
                currNode = root.left;
                while (currNode.right != null && currNode.right != root) {
                    currNode = currNode.right;
                }
                if (currNode.right == null) {
                    currNode.right = root;
                    root = root.left;
                } else {
                    /*visiting node*/
                    System.out.println(root.val);
                    if (previousNode != null && previousNode.val > root.val) {
                        if (node1 == null) {
                            node1 = previousNode;
                        }
                        node2 = root;
                    }
                    previousNode = root;
                    currNode.right = null;
                    root = root.right;
                }
            } else {
                /*visiting node*/
                System.out.println(root.val);
                if (previousNode != null && previousNode.val > root.val) {
                    if (node1 == null) {
                        node1 = previousNode;
                    }
                    node2 = root;
                }
                previousNode = root;
                root = root.right;
            }
        }

    }

    /*
     * Step3: swap value of two nodes.
     * */

    public void recoverTree3(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode currNode, previousNode = null, node1 = null, node2 = null;
        while (root != null) {
            if (root.left != null) {
                currNode = root.left;
                while (currNode.right != null && currNode.right != root) {
                    currNode = currNode.right;
                }
                if (currNode.right == null) {
                    currNode.right = root;
                    root = root.left;
                } else {
                    /*visiting node*/
                    System.out.println(root.val);
                    if (previousNode != null && previousNode.val > root.val) {
                        if(node1 == null) {
                            node1 = previousNode;
                        }
                        node2 = root;
                    }
                    previousNode = root;
                    currNode.right = null;
                    root = root.right;
                }
            } else {
                /*visiting node*/
                System.out.println(root.val);
                if (previousNode != null && previousNode.val > root.val) {
                    if(node1 == null) {
                        node1 = previousNode;
                    }
                    node2 = root;
                }
                previousNode = root;
                root = root.right;
            }
        }
        // swap values
        if(node1 != null) {
            int tmp = node1.val;
            node1.val = node2.val;
            node2.val = tmp;
        }

    }


    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n9 = new TreeNode(9);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        TreeNode n5 = new TreeNode(5);
        TreeNode n10 = new TreeNode(10);

        n20.left = n15;
        n20.right = n7;

        n9.left = n5;
        n9.right = n10;

        n3.right = n20;
        n3.left = n9;
        Q99RecoverBinaryTree sol = new Q99RecoverBinaryTree();
        sol.recoverTree3(n3);
    }

    public static void test2() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n1.left = n3;
        n3.right = n2;

        Q99RecoverBinaryTree sol = new Q99RecoverBinaryTree();
        sol.recoverTree3(n1);
    }


}
