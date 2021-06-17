package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.List;

public class Q93RestoreIPAddresses {

    /*
    * pd: https://leetcode.com/problems/restore-ip-addresses/
    * assm: best time sol
    * appr: https://leetcode.com/problems/restore-ip-addresses/discuss/30949/My-code-in-Java
    * test cases:
    *
    * */


    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        if(s == null || s.isEmpty()) {
            return result;
        }
        backtrack(result, s, 0, new ArrayList<>());
        return result;

    }

    public void backtrack(List<String> result, String str, int start, List<String> tmp) {
        if(tmp.size() == 4) {
            if(start >= str.length()) {
                result.add(String.join(".", tmp));
            }
        } else {
            for(int idx=1; idx<=3 && (start+idx) <= str.length(); idx++) {
                String num = str.substring(start, start+idx);
                if(isValid(num)) {
                    tmp.add(num);
                    backtrack(result, str, start+idx, tmp);
                    tmp.remove(tmp.size()-1);
                }
            }
        }
    }


    public boolean isValid(String s) {
        if(s == null || s.isEmpty() || s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Q93RestoreIPAddresses sol = new Q93RestoreIPAddresses();
        LsUtil.printList(sol.restoreIpAddresses("25525511135"));
    }


}
