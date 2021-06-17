package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q95UniqueBinaryTrees2 {
   /*
   * pd: https://leetcode.com/problems/unique-binary-search-trees-ii
   * assm: nodes < 1000, best time sol
   * appr: https://leetcode.com/problems/unique-binary-search-trees-ii/discuss/31494/A-simple-recursive-solution
   *  test cases:
   * */

    public List<TreeNode> generateTrees(int n) {
        return genTreeRec(1,n);
    }

    public List<TreeNode> genTreeRec(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if(start > end) {
            list.add(null);
        }
        if(start == end) {
            list.add(new TreeNode(start));
            return list;
        }
        for(int root=start; root<=end; root++) { // each node will become root
            List<TreeNode> left = genTreeRec(start, root-1); // left side nodes ( less then root node)
            List<TreeNode> right = genTreeRec(root+1, end); // right side nodes ( greater then root node)
            for(TreeNode l: left) { // left * right
                for(TreeNode r: right) {
                    TreeNode nR = new TreeNode(root);
                    nR.left = l;
                    nR.right = r;
                    list.add(nR);
                }
            }
        }
        return list;
    }


}
