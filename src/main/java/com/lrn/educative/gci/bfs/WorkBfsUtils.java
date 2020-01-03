package com.lrn.educative.gci.bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WorkBfsUtils {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        public TreeNode(int x) {
            val = x;
        }

        public void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }

    /*level order traversal tree*/
    static class LevelOrderTraversal {
        public List<List<Integer>> traverse(TreeNode node) {
            assert node != null;
            List<List<Integer>> levelList = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                ArrayList<Integer> nodeList = new ArrayList<>();
                for (int idx = 0; idx < queueSize; idx++) {
                    TreeNode currentNode = queue.poll();
                    nodeList.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                levelList.add(nodeList);
            }
            return levelList;
        }

        public static void main(String[] args) {
            LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            List<List<Integer>> result = levelOrderTraversal.traverse(root);
            System.out.println("Level order traversal: " + result);
        }
    }

    /*Reverse Level Order Traversal*/
    static class ReverseLevelOrderTraversal {
        public List<List<Integer>> traverse(TreeNode node) {
            assert node != null;
            ArrayList<List<Integer>> levelList = new ArrayList();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                ArrayList<Integer> list = new ArrayList<>();
                for (int idx = 0; idx < levelSize; idx++) {
                    final TreeNode currentNode = queue.poll();
                    list.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                levelList.add(0, list);
            }
            return levelList;
        }

        public static void main(String[] args) {
            ReverseLevelOrderTraversal reverseLevelOrderTraversal = new ReverseLevelOrderTraversal();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            List<List<Integer>> result = reverseLevelOrderTraversal.traverse(root);
            System.out.println("Reverse level order traversal: " + result);
        }
    }

    /*Zigzag Traversal*/
    static class ZigZagTraversal {
        public List<List<Integer>> traverse(TreeNode node) {
            assert node != null;
            ArrayList<List<Integer>> levelList = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            boolean leftToRight = true;
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                ArrayList<Integer> nodeList = new ArrayList<>();
                for (int idx = 0; idx < levelSize; idx++) {
                    final TreeNode treeNode = queue.poll();
                    if (leftToRight) {
                        nodeList.add(treeNode.val);
                    } else {
                        nodeList.add(0, treeNode.val);
                    }
                    if (treeNode.left != null) {
                        queue.offer(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.offer(treeNode.right);
                    }
                }
                levelList.add(nodeList);
                leftToRight = !leftToRight;
            }
            return levelList;
        }

        public static void main(String[] args) {
            ZigZagTraversal zigZagTraversal = new ZigZagTraversal();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            root.right.left.left = new TreeNode(20);
            root.right.left.right = new TreeNode(17);
            List<List<Integer>> result = zigZagTraversal.traverse(root);
            System.out.println("Zigzag traversal: " + result);
        }
    }

    /*Level Averages in a Binary Tree*/
    static class LevelAverages {
        public List<Double> getAverages(TreeNode node) {
            assert node != null;
            List<Double> levelAverageList = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                int sum = 0;
                for (int idx = 0; idx < levelSize; idx++) {
                    TreeNode currentNode = queue.poll();
                    sum = sum + currentNode.val;
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                levelAverageList.add((double) sum / levelSize);
            }
            return levelAverageList;
        }

        public static void main(String[] args) {
            LevelAverages levelAverages = new LevelAverages();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.left.right = new TreeNode(2);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            List<Double> result = levelAverages.getAverages(root);
            System.out.print("Level averages are: " + result);
        }
    }

    /*Maximum value at each level*/
    static class MaxValueAtLevel {
        public List<Integer> getMaxAtEachLevel(TreeNode node) {
            assert node != null;
            ArrayList<Integer> maxValList = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                int levelMax = Integer.MIN_VALUE;
                for (int idx = 0; idx < levelSize; idx++) {
                    TreeNode currentNode = queue.poll();
                    levelMax = Math.max(levelMax, currentNode.val);
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                maxValList.add(levelMax);
            }
            return maxValList;
        }

        public static void main(String[] args) {
            MaxValueAtLevel maxValueAtLevel = new MaxValueAtLevel();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.left.right = new TreeNode(2);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            List<Integer> result = maxValueAtLevel.getMaxAtEachLevel(root);
            System.out.print("Level averages are: " + result);
        }
    }
    /*Minimum Depth of a Binary Tree*/
    static class MinimumDepthOfBt {
        public Integer findMinimumDepth(TreeNode node) {
            assert node != null;
            Integer minimumDepth = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            while(!queue.isEmpty()) {
                int levelSize = queue.size();
                for(int idx=0; idx<levelSize; idx++) {
                    TreeNode currentNode = queue.poll();
                    if(currentNode.left == null && currentNode.right == null) {
                        return minimumDepth;
                    }
                    if(currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if(currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                minimumDepth++;
            }
            return minimumDepth;
        }
        public static void main(String[] args) {
            MinimumDepthOfBt minimumDepthOfBt = new MinimumDepthOfBt();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            System.out.println("Tree Minimum Depth: " + minimumDepthOfBt.findMinimumDepth(root));
            root.left.left = new TreeNode(9);
            root.right.left.left = new TreeNode(11);
            System.out.println("Tree Minimum Depth: " + minimumDepthOfBt.findMinimumDepth(root));
        }
    }
    /*Level Order Successor */
    static class LevelOrderSuccessor {
        public TreeNode findSuccessor(TreeNode node, int key) {
            assert node != null;
            TreeNode successor = null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(node);
            boolean nodeFound = false;
            while(!queue.isEmpty()) {
                TreeNode currentNode = queue.poll();
                if(nodeFound) {
                    successor = currentNode;
                    break;
                }
                if(key == currentNode.val) {
                    nodeFound = true;
                }
                if(currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            return successor;
        }
        public static void main(String[] args) {
            LevelOrderSuccessor levelOrderSuccessor = new LevelOrderSuccessor();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            TreeNode result = levelOrderSuccessor.findSuccessor(root, 12);
            if (result != null)
                System.out.println(result.val + " ");
            result = levelOrderSuccessor.findSuccessor(root, 9);
            if (result != null)
                System.out.println(result.val + " ");
        }
    }
    /*Connect Level Order Siblings*/
    static class SiblingLink {

        public void connectSibling(TreeNode node) {
            assert node!=null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            while(!queue.isEmpty()) {
                int levelSize = queue.size();
                TreeNode preNode = null, currentNode = null;
                for (int idx = 0; idx < levelSize; idx++) {
                    currentNode = queue.poll();
                    if(preNode != null) {
                        preNode.next = currentNode;
                    }
                    if(currentNode.left!=null) {
                        queue.add(currentNode.left);
                    }
                    if(currentNode.right!=null) {
                        queue.add(currentNode.right);
                    }
                    preNode = currentNode;
                    currentNode = null;
                }
                preNode.next = currentNode;
            }
        }
        public static void main(String[] args) {
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            new SiblingLink().connectSibling(root);
            System.out.println("Level order traversal using 'next' pointer: ");
            root.printLevelOrder();
        }
    }
    /*Connect All Level Order Siblings*/
    static class SiblingConnector {
        public void connect(TreeNode node) {
            assert node != null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            TreeNode prevNode = null;
            while(!queue.isEmpty()) {
                TreeNode currentNode = queue.poll();
                if(currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
                if(prevNode != null) {
                    prevNode.next = currentNode;
                }
                prevNode = currentNode;
            }
        }
        public static void main(String[] args) {
            SiblingConnector siblingConnector = new SiblingConnector();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            siblingConnector.connect(root);
            // level order traversal using 'next' pointer
            TreeNode current = root;
            System.out.println("Traversal using 'next' pointer: ");
            while (current != null) {
                System.out.print(current.val + " ");
                current = current.next;
            }
        }
    }
    /*Right View of a Binary Tree*/
    static class RightViewOfBst {
        public List<TreeNode> getRightView(TreeNode node) {
            assert node != null;
            ArrayList<TreeNode> resultNodeList = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            while(!queue.isEmpty()) {
                TreeNode currentNode = null;
                int queueSize = queue.size();
                for(int idx=0; idx<queueSize; idx++) {
                    currentNode = queue.poll();
                    if(currentNode.left!=null) {
                        queue.offer(currentNode.left);
                    }
                    if(currentNode.right!=null) {
                        queue.offer(currentNode.right);
                    }
                }
                resultNodeList.add(currentNode);
            }
            return resultNodeList;
        }
        public List<TreeNode> getLeftView(TreeNode node) {
            assert node!=null;
            ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            while(!queue.isEmpty()) {
                int levelSize = queue.size();
                for(int idx=0; idx<levelSize; idx++) {
                    TreeNode currentNode = queue.poll();
                    if(idx==0) {
                        nodeList.add(currentNode);
                    }
                    if(currentNode.left!=null) {
                        queue.offer(currentNode.left);
                    }
                    if(currentNode.right!=null) {
                        queue.offer(currentNode.right);
                    }
                }
            }
            return nodeList;
        }
        public static void main(String[] args) {
            RightViewOfBst rightViewOfBst = new RightViewOfBst();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            root.left.left.left = new TreeNode(3);
            List<TreeNode> result = rightViewOfBst.getRightView(root);
            for (TreeNode node : result) {
                System.out.print(node.val + " ");
            }
            System.out.println();
             result = rightViewOfBst.getLeftView(root);
            for (TreeNode node : result) {
                System.out.print(node.val + " ");
            }
        }
    }
    /*Tree Boundary*/
    static class TreeBoundary {
        public List<TreeNode> getBoundary(TreeNode node) {
            assert node!= null;
            ArrayList<TreeNode> nodeList = new ArrayList<>();
            ArrayList<TreeNode> leftView = new ArrayList<>();
            ArrayList<TreeNode> rightView = new ArrayList<>();
            ArrayList<TreeNode> leafNodes = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            while(!queue.isEmpty()){
                int sizeOfLevel = queue.size();
                TreeNode currentNode = null;
                for (int idx = 0; idx < sizeOfLevel; idx++) {
                    currentNode = queue.poll();
                    if(currentNode.left!= null) {
                        queue.offer(currentNode.left);
                    }
                    if(currentNode.right!=null) {
                        queue.offer(currentNode.right);
                    }
                    if(idx==0) {
                        leftView.add(currentNode);
                        continue;
                    }
                    if(currentNode!=null && currentNode.left==null && currentNode.right == null && sizeOfLevel != idx+1) {
                     leafNodes.add(0,currentNode);
                     continue;
                    }
                }
                rightView.add(currentNode);
            }
            nodeList.addAll(leftView);
            nodeList.addAll(leafNodes);
            nodeList.addAll(rightView);
            return nodeList;
        }
        public static void main(String[] args) {
            TreeBoundary treeBoundary = new TreeBoundary();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(4);
            root.left.left.left = new TreeNode(9);
            root.left.right = new TreeNode(3);
            root.left.right.left = new TreeNode(15);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            root.right.right.left = new TreeNode(6);
            List<TreeNode> result = treeBoundary.getBoundary(root);
            for (TreeNode node : result) {
                System.out.print(node.val + " ");
            }
        }
    }

}
