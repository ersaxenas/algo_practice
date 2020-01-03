package com.lrn.educative.dsj.tr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class WorkTrUtil {


    static class TreeTraversal {
        /*Pre-Order Traversal - root -> left -> right*/
        public void preOrderTraversal(TNode node) {
            if (node == null) {
                return;
            }
            System.out.print(node.getData() + "-");
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }

        /*In-Order Traversal - left -> root -> right*/
        public void inOrderTraversal(TNode node) {
            if (node == null) {
                return;
            }
            inOrderTraversal(node.getLeft());
            System.out.print(node.getData() + "-");
            inOrderTraversal(node.getRight());
        }

        /*Post-Order Traversal - lef -> right -> root*/
        public void postOrderTraversal(TNode node) {
            if (node == null) {
                return;
            }
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node.getData() + "-");
        }

        // Function to print all nodes of a given level from left to right
        public boolean printLevel(TNode node, int level) {
            // basically pre-order traversal with little modification. Here based on the value of level we travel to the depth of the tree
            // for example for level  == 2 we will traverse tree one level down
            if (node == null) { // recursion exit condition
                return false; // reached at the leaf node so no further nodes are present.
            }

            if (level == 1) { // stop at the level and exit from here
                System.out.print(node.getData() + " - "); // we print when we hit level 1.
                return true; // return true since NODE is not null so there could be more nodes
            }

            // go left
            boolean moreLeftchildArePresent = printLevel(node.getLeft(), level - 1);
            // go right
            boolean moreRightChildArePresent = printLevel(node.getRight(), level - 1);
            return moreLeftchildArePresent || moreRightChildArePresent; // if there is more child present then return true
        }

        // Function to print level order traversal of given binary tree
        public void levelOrderTraversal(TNode root) {
            int level = 1;
            while (printLevel(root, level)) {
                level++;
            }
        }


        public static void main(String[] args) {
            TreeTraversal treeTraversal = new TreeTraversal();
            BST bst1 = new BST();
            bst1.add(6).add(4).add(2).add(5).add(9).add(8).add(12);
            treeTraversal.preOrderTraversal(bst1.getRoot());
            System.out.println();
            treeTraversal.inOrderTraversal(bst1.getRoot());
            System.out.println();
            treeTraversal.postOrderTraversal(bst1.getRoot());
            System.out.println();
            treeTraversal.levelOrderTraversal(bst1.getRoot());
        }
    }

    static class MinMaxOfBst {
        public Integer findMin(TNode node) {
            Integer result = null;
            if (node == null) {
                return result;
            }
            TNode currentNode = node;
            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
            result = currentNode.getData();
            return result;
        }

        public Integer findMax(TNode node) {
            Integer result = null;
            if (node == null) {
                return result;
            }
            TNode currentNode = node;
            while (currentNode.getRight() != null) {
                currentNode = currentNode.getRight();
            }
            result = currentNode.getData();
            return result;
        }
        public static void main(String[] args) {
            MinMaxOfBst minMaxOfBst = new MinMaxOfBst();
            BST bst = new BST();
            bst.add(6).add(4).add(9).add(5).add(2).add(8).add(12).add(10).add(14);
            System.out.println("Min: "+minMaxOfBst.findMin(bst.getRoot()));
            System.out.println("Max: "+minMaxOfBst.findMax(bst.getRoot()));
        }
    }

    /*Find Kth max in BST*/
    static class KMaxBst {
        int counter = 0;
        public Integer findKMax(TNode root, int K) {
            if(root == null) {
                return null;
            }
            counter = 0;
            final TNode kMaxR = findKMaxR(root, K);
            return (kMaxR) != null ? kMaxR.getData(): null;
        }

        public TNode findKMaxR(TNode node, int K) {
           if(node == null) return null;
           TNode currentNode = findKMaxR(node.getRight(), K);
           if(counter != K) {
               counter++;
               currentNode = node;
           }
           if(counter == K) {
              return currentNode;
           }
           return findKMaxR(currentNode.getLeft(), K);
        }

        public static void main(String[] args) {
            BST bst = new BST();
            bst.add(50).add(25).add(20).add(40).add(100).add(70).add(200).add(300).add(150).add(170).add(120);
            KMaxBst kMaxBst = new KMaxBst();
            System.out.println(kMaxBst.findKMax(bst.getRoot(), 1));
            System.out.println(kMaxBst.findKMax(bst.getRoot(), 2));
            System.out.println(kMaxBst.findKMax(bst.getRoot(), 3));
            System.out.println(kMaxBst.findKMax(bst.getRoot(), 4));
            System.out.println(kMaxBst.findKMax(bst.getRoot(), 5));
        }
    }
    /*Find the Ancestors of a Given Node in a Binary Tree*/
    static class AncestorsInBst {
        public List<TNode> findAncestors(TNode node, Integer val) {
            ArrayList<TNode> ancestors = new ArrayList<>();
            if(node == null) return ancestors;
            TNode currentNode = node;
            while(currentNode != null && !currentNode.getData().equals(val)) {
                ancestors.add(currentNode);
                if(val < currentNode.getData()) { // go left
                   currentNode = currentNode.getLeft();
                } else if(val > currentNode.getData()) { // go right
                  currentNode = currentNode.getRight();
                }
            }
            if(currentNode == null) {
                ancestors.clear();
            } else {
                ancestors.add(currentNode);
            }
            return ancestors;
        }
        public String getAncestors(TNode node, Integer val) {
            return findAncestors(node,val).stream().map(tnode -> String.valueOf(tnode.getData())).collect(Collectors.joining(","));
        }

        public static void main(String[] args) {
            BST bst = new BST();
            bst.add(50).add(25).add(20).add(40).add(100).add(70).add(200).add(300).add(150).add(170).add(120);
            AncestorsInBst ancestorsInBst = new AncestorsInBst();
            System.out.println(ancestorsInBst.getAncestors(bst.getRoot(), 120));
            System.out.println(ancestorsInBst.getAncestors(bst.getRoot(), 130));
        }
    }
    /*Find Ancestors of a Given Node in a Binary Tree*/
    static class TreeAncestors {
        public String findAncestors(TNode node, Integer target) {
            assert node != null && target != null;
            StringBuilder sbr = new StringBuilder();
            TNode currentNode = node;
            sbr.append(currentNode.getData());
            while(currentNode != null && !currentNode.getData().equals(target)) {
                sbr.append(" -> ");
                if(target < currentNode.getData()) { // go left
                    currentNode = currentNode.getLeft();
                } else { // go right
                    currentNode = currentNode.getRight();
                }
                sbr.append(currentNode.getData());
            }
            if(currentNode == null) {
                return null;
            }
            return sbr.toString();
        }
        public static void main(String[] args) {
            TreeAncestors treeAncestors = new TreeAncestors();
            BST tree = new BST();
		/* Construct a binary tree like this
				6
			   / \
			  4   9
			 / \  |  \
			2	5 8	  12
					  / \
					 10  14
		*/
            tree.add(6).add(4).add(9).add(2).add(5).add(8).add(8).add(12).add(10).add(14);
            int key = 10;
            System.out.print(treeAncestors.findAncestors(tree.getRoot(), key));

            System.out.println();
            key = 5;
            System.out.print(treeAncestors.findAncestors(tree.getRoot(), key));
        }
    }

    static class HeightOfBst {
        public Integer findHeightOfTree(TNode node) {
            if(node == null) return -1;
            return 1 + Math.max(findHeightOfTree(node.getLeft()), findHeightOfTree(node.getRight()));
        }
        public static void main(String[] args) {
            HeightOfBst heightOfBst = new HeightOfBst();
            BST bsT = new BST();
            bsT.add(6).add(4).add(9).add(5).add(2).add(8).add(12);
            System.out.println(heightOfBst.findHeightOfTree(bsT.getRoot()));
        }
    }
    /*Find Nodes at "k" Distance from the Root*/
    static class NodesAtKDistance {
        public List<Integer> findNodesAtLevel(TNode node, int K) {
             List<Integer> nodeList = new ArrayList<>();
             findNodesAtLevel(node,0, K, nodeList);
             return nodeList;
        }
        public void findNodesAtLevel(TNode node, int currentLevel, int K, List<Integer> list) {
            if(node == null) return;
            if(currentLevel == K) {
                list.add(node.getData());
                return;
            }
            findNodesAtLevel(node.getLeft(), currentLevel+1, K, list);
            findNodesAtLevel(node.getRight(), currentLevel +1, K, list);
        }
        public static void main(String[] args) {
            NodesAtKDistance nodesAtKDistance = new NodesAtKDistance();
            BST bst = new BST();
            bst.add(6).add(4).add(9).add(5).add(2).add(8).add(12);
            System.out.println(nodesAtKDistance.findNodesAtLevel(bst.getRoot(), 2));
        }

    }

}
