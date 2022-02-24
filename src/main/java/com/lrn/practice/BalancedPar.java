package com.lrn.practice;

import com.lrn.leetcode.google.LsUtil;

import java.util.ArrayList;
import java.util.List;

public class BalancedPar {
    /*generate parentheses for in n*/
    public List<String> generateParentheses(int N) {
       List<String> result = new ArrayList<>();
       backtrack(N*2, 0,0,new char[N*2], 0, result);
       return result;
    }

    public void backtrack(int N, int o, int c, char[] arr, int idx, List<String> result) {
        // basecase
        if(idx == arr.length) {
            result.add(new String(arr));
        } else {
            if(o < N/2) {
                arr[idx] = '(';
                 backtrack(N,o+1,c,arr,idx+1,result);
            }
            if(idx != 0 && c < o) {
                arr[idx] = ')';
                backtrack(N, o, c+1, arr,idx+1, result);
            }
        }
    }

    public static void main(String[] args) {
        BalancedPar sol = new BalancedPar();
        LsUtil.printList(sol.generateParentheses(3));
    }
}
