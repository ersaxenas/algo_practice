package com.lrn.educative.dsj.tr;

public class BST {
    private TNode root;

    public TNode getRoot() {
        return root;
    }

    public BST add(Integer data) {
        if (data == null) return this;
        root = insertData(root, data);
        return this;
    }

    public TNode insertData(TNode node, Integer data) {
        if (node == null) {
            return new TNode(data);
        }
        if (data < node.getData()) {
            node.setLeft(insertData(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(insertData(node.getRight(), data));
        }
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public TNode search(Integer data) {
        if (data == null) return null;
        TNode currentNode = root;
        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                return currentNode;
            } else if (data < currentNode.getData()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public boolean delete(Integer data) {

        if (data == null) {
            return false;
        }
        /*find node and if node found then store parent*/
        TNode currentNode = root;
        TNode parentNode = null;
        while (currentNode != null && !currentNode.getData().equals(data)) {
            parentNode = currentNode;
            if (data < currentNode.getData()) { // go left
                currentNode = currentNode.getLeft();
            } else if (data > currentNode.getData()) { // go right
                currentNode = currentNode.getRight();
            }
        }
        if(currentNode == null) {
            return false; // node not present in the tree.
        }
        boolean isCurrentNodeLeftChildOfParent = currentNode.getData() < parentNode.getData();
        // three delete scenario
        //1. both left and right child is null
        if(currentNode.getLeft() == null && currentNode.getRight() == null) {
            // so node to be deleted is leaf node - no child
            // if node is root node
            if(root.getData().equals(currentNode.getData())) {
                root = null;
                return true;
            }
            if(isCurrentNodeLeftChildOfParent) {
                // current node is left child
                parentNode.setLeft(null);
            } else {
                // current node is right child
                parentNode.setRight(null);
            }

        } // 2. only left child is null
        else if( currentNode.getLeft() == null) {
            // root node with right child only
            if(root.getData().equals(currentNode.getData())) {
                root = currentNode.getRight();
                currentNode.setRight(null);
                return true;
            }
            if(isCurrentNodeLeftChildOfParent) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }


        }// 3. only right child is null
        else if(currentNode.getRight() == null) {
            // root node with left child only
            if(root.getData().equals(currentNode.getData())) {
                root = currentNode.getLeft();
                currentNode.setLeft(null);
                return true;
            }
            if(isCurrentNodeLeftChildOfParent) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }

        } // both child are present
        else {
            // find the smallest element in the right subtree
            TNode smallestNodeInTheRightSubTree = findSmallestInTree(currentNode.getRight());
            // now delete smallest node from tree
            Integer sData = smallestNodeInTheRightSubTree.getData();
            delete(sData);
            //set smallest node data to current node== replace current node with smallestNodeInTheRightSubTree
            currentNode.setData(sData);
        }
     return true;
    }

    public TNode findSmallestInTree(TNode currentNode) {
        TNode node = currentNode;
        while(node != null && node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }


}
