package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static TreeNode constructTree(Integer[] array) {
        if(array == null || array.length ==0 ) {
            return null;
        }
        List<Integer> lst = new ArrayList<>();
        for(Integer elem : array) {
            lst.add(elem);
        }
        return constructTree(lst);
    }

    public static TreeNode constructTree(List<Integer> lst) {
        //base case
        if(lst.isEmpty() ) {
            return null;
        }
        if(lst.get(0) == null) {
            lst.remove(0);
            return null;
        }
        TreeNode node = new TreeNode(lst.remove(0));
        node.left = constructTree(lst);
        node.right = constructTree(lst);
        return node;
    }


}
