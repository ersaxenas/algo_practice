package com.lrn.leetcode.google;

import java.util.ArrayDeque;

public class Q071SimplifyPath {
    /*2022-01-05T06:18:44.689Z
    https://leetcode.com/problems/simplify-path
    https://leetcode.com/problems/simplify-path/discuss/25686/Java-10-lines-solution-with-stack
    * Assm: best time sol, path contains only english letter, / and ., max path len 10000,
    * approach: stack
    * test cases:
    *
    *
    * */

    public String simplifyPath(String path) {
        if(path == null || path.isEmpty()) {
            return path;
        }
        path = path + "/"; // appending a slash to input
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        int prvCharIdx = 0;
        for(int idx=0; idx<path.length(); idx++) {
            if(path.charAt(idx) == '/') {
                String str = path.substring(prvCharIdx, idx);
                if(!str.isEmpty() && !str.equals("/") && !str.equals(".")) {
                    if(str.equals("..")) {
                        if(!arrayDeque.isEmpty()) arrayDeque.pop();
                    } else {
                      arrayDeque.push(str);
                    }
                }
                prvCharIdx = idx+1;
            }
        }
        String newPath = "";
        while(!arrayDeque.isEmpty()) {
            newPath = "/" + arrayDeque.pop() + newPath;
        }
        return newPath.isEmpty() ? "/" : newPath;
    }

    public static void main(String[] args) {
        Q071SimplifyPath sol = new Q071SimplifyPath();
        System.out.println(sol.simplifyPath("/a//b////c/d//././/.."));
        System.out.println(sol.simplifyPath("/home/"));
        System.out.println(sol.simplifyPath("/../"));
        System.out.println(sol.simplifyPath("/home//foo/"));
        System.out.println(sol.simplifyPath("/a/./b/../../c/"));
    }

}
