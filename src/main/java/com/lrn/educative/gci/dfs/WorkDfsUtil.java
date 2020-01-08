package com.lrn.educative.gci.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class WorkDfsUtil {
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

    /*Binary Tree Path Sum*/
    static class TreePathSum {
        public boolean hasPath(TreeNode node, int sum) {
            if (node == null) {
                return false;
            }
            if (sum == node.val && node.left == null && node.right == null) {
                return true;
            }
            return hasPath(node.left, (sum - node.val)) || hasPath(node.right, (sum - node.val));
        }

        public static void main(String[] args) {
            TreePathSum treePathSum = new TreePathSum();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(9);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            System.out.println("Tree has path: " + treePathSum.hasPath(root, 23));
            System.out.println("Tree has path: " + treePathSum.hasPath(root, 16));
        }
    }

    /*All Paths for a Sum*/
    static class AllPathForSum {
        public void findAllPaths(TreeNode node, int sum, List<List<Integer>> resultList, List<Integer> parentList) {
            if (node == null) {
                return;
            }
            if (node.val == sum && node.left == null && node.right == null) {
                ArrayList<Integer> finalList = new ArrayList<>(parentList);
                finalList.add(node.val);
                resultList.add(finalList);
                return;
            }
            ArrayList<Integer> pList = new ArrayList<>(parentList);
            pList.add(node.val);
            findAllPaths(node.left, sum - node.val, resultList, pList);
            findAllPaths(node.right, sum - node.val, resultList, pList);
        }

        private List<List<Integer>> findPaths(TreeNode root, int sum) {
            assert root != null;
            List<List<Integer>> resultList = new ArrayList<>();
            List<Integer> parentList = new ArrayList<>();
            findAllPaths(root, sum, resultList, parentList);
            return resultList;
        }

        public static void main(String[] args) {
            AllPathForSum allPathForSum = new AllPathForSum();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            int sum = 23;
            List<List<Integer>> result = allPathForSum.findPaths(root, sum);
            System.out.println("Tree paths with sum " + sum + ": " + result);
        }
    }

    /*Sum of Path Numbers*/
    static class SumOfPathNumbers {
        public void sumOfPath(TreeNode node, List<Integer> pathSumList, int pathSum) {
            if (node == null) {
                return;
            }
            int nPathSum = (pathSum * 10) + node.val;
            if (node.left == null && node.right == null) {
                pathSumList.add(nPathSum);
                return;
            }
            sumOfPath(node.left, pathSumList, nPathSum);
            sumOfPath(node.right, pathSumList, nPathSum);
        }

        public Integer sumOfPath2(TreeNode node, int pathSum) {
            if (node == null) {
                return 0;
            }
            int nPathSum = (pathSum * 10) + node.val;
            if (node.left == null && node.right == null) {
                return nPathSum;
            }
            return sumOfPath2(node.left, nPathSum) + sumOfPath2(node.right, nPathSum);
        }

        public Integer findSumOfPaths(TreeNode node) {
            List<Integer> pathSumList = new ArrayList<>();
            sumOfPath(node, pathSumList, 0);
            if (pathSumList.isEmpty()) {
                return 0;
            } else {
                return pathSumList.stream().reduce(0, (a, b) -> a + b);
            }
        }

        public Integer findSumOfPaths2(TreeNode node) {
            List<Integer> pathSumList = new ArrayList<>();
            sumOfPath(node, pathSumList, 0);
            if (pathSumList.isEmpty()) {
                return 0;
            } else {
                return pathSumList.stream().reduce(0, (a, b) -> a + b);
            }
        }

        public static void main(String[] args) {
            SumOfPathNumbers sumOfPathNumbers = new SumOfPathNumbers();
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(0);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(1);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(5);
            System.out.println("Total Sum of Path Numbers: " + sumOfPathNumbers.findSumOfPaths2(root));
        }
    }

    /*Path With Given Sequence*/
    static class PathWithGivenSequence {
        public boolean pathExists(TreeNode node, int[] pathSeq) {
            assert node != null;
            return checkPath(node, pathSeq, 0);
        }

        public boolean checkPath(TreeNode node, int[] pathSeq, int seqIndex) {
            if (node == null) return false;
            if (seqIndex >= pathSeq.length || node.val != pathSeq[seqIndex]) {
                return false;
            }
            if (node.left == null && node.right == null && seqIndex == pathSeq.length - 1) {
                return true;
            }
            return checkPath(node.left, pathSeq, seqIndex + 1) || checkPath(node.right, pathSeq, seqIndex + 1);
        }
        public static void main(String[] args) {
            PathWithGivenSequence pathWithGivenSequence = new PathWithGivenSequence();
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(0);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(1);
            root.right.left = new TreeNode(6);
            root.right.right = new TreeNode(5);

            System.out.println("Tree has path sequence: " + pathWithGivenSequence.pathExists(root, new int[] { 1, 0, 7 }));
            System.out.println("Tree has path sequence: " + pathWithGivenSequence.pathExists(root, new int[] { 1, 1, 6 }));
        }
    }
    /*Count Paths for a Sum - back tracking*/
    static class CountTreePathsWithSum{
        public Integer countAllPaths(TreeNode node, int sum) {
            return countPaths(node,sum,new ArrayList<>());
        }
        public Integer countPaths(TreeNode node, int sum, List<Integer> nodesInPathList) {
            if(node == null) return 0;
            int currentPathSum = 0, pathCount = 0;
            nodesInPathList.add(node.val);
            // calculate path sum - this is backtracking
            ListIterator<Integer> nodesInThePath = nodesInPathList.listIterator(nodesInPathList.size());//we want to iterate in reverse direction so iterator must start from the last index of the list
            while(nodesInThePath.hasPrevious()) {
                currentPathSum = currentPathSum + nodesInThePath.previous();
                if(currentPathSum == sum) {
                    pathCount++;
                }
            }

            pathCount = pathCount + countPaths(node.left, sum, nodesInPathList);
            pathCount = pathCount + countPaths(node.right, sum, nodesInPathList);

            nodesInPathList.remove(nodesInPathList.size() -1);
            return pathCount;
        }

        public static void main(String[] args) {
            CountTreePathsWithSum countTreePathsWithSum = new CountTreePathsWithSum();
            TreeNode root = new TreeNode(12);
            root.left = new TreeNode(7);
            root.right = new TreeNode(1);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(10);
            root.right.right = new TreeNode(5);
            System.out.println("Tree has path: " + countTreePathsWithSum.countAllPaths(root, 11));
        }
    }
   /*Tree Diameter*/
    static class TreeDiameter {
        Integer treeMaxDiaMeter = -1;
        public Integer findTreeHeight(TreeNode node) {
               if(node == null) return 0;
               int leftTreeHeight = findTreeHeight(node.left);
               int rightTreeHeight = findTreeHeight(node.right);
               int nodeHeight = Math.max(leftTreeHeight, rightTreeHeight) + 1;
               int nodeDiaMeter = leftTreeHeight + rightTreeHeight +1;
               treeMaxDiaMeter = Math.max(treeMaxDiaMeter, nodeDiaMeter);
               return nodeHeight;
        }

        public Integer getTreeDiameter(TreeNode node) {
            assert node!=null;
            findTreeHeight(node);
            return treeMaxDiaMeter;
        }

        public static void main(String[] args) {
            TreeDiameter treeDiameter = new TreeDiameter();
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(5);
            root.right.right = new TreeNode(6);
            System.out.println("Tree Diameter: " + treeDiameter.getTreeDiameter(root));
            root.left.left = null;
            root.right.left.left = new TreeNode(7);
            root.right.left.right = new TreeNode(8);
            root.right.right.left = new TreeNode(9);
            root.right.left.right.left = new TreeNode(10);
            root.right.right.left.left = new TreeNode(11);
            System.out.println("Tree Diameter: " + treeDiameter.getTreeDiameter(root));
        }
   }

   /*Path with Maximum Sum*/
    static class PathWithMaximumSum {
        Integer maxPathSum = Integer.MIN_VALUE;
        public Integer findPathWithMaxSum(TreeNode node) {
            maxPathSum = Integer.MIN_VALUE;
          finMaxPathSum(node);
          return maxPathSum;
        }

        public Integer finMaxPathSum(TreeNode node) {
            if(node == null) return 0;
            int leftMaxPath = Math.max(0,finMaxPathSum(node.left));
            int rightMaxPath = Math.max(0,finMaxPathSum(node.right));
            int maxPathSumForCurrentNode = leftMaxPath + rightMaxPath + node.val;
            maxPathSum = Math.max(maxPathSumForCurrentNode, maxPathSum);
            return Math.max(leftMaxPath, rightMaxPath) + node.val;
        }
        public static void main(String[] args) {
            PathWithMaximumSum pathWithMaximumSum = new PathWithMaximumSum();
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            System.out.println("Maximum Path Sum: " + pathWithMaximumSum.findPathWithMaxSum(root));

            root.left.left = new TreeNode(1);
            root.left.right = new TreeNode(3);
            root.right.left = new TreeNode(5);
            root.right.right = new TreeNode(6);
            root.right.left.left = new TreeNode(7);
            root.right.left.right = new TreeNode(8);
            root.right.right.left = new TreeNode(9);
            System.out.println("Maximum Path Sum: " + pathWithMaximumSum.findPathWithMaxSum(root));

            root = new TreeNode(-1);
            root.left = new TreeNode(-3);
            System.out.println("Maximum Path Sum: " + pathWithMaximumSum.findPathWithMaxSum(root));
        }
   }


}
