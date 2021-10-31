package com.lrn.leetcode.google;

public class Q482LicenseKeyFormat {
    /*
    * pd: https://leetcode.com/problems/license-key-formatting
    * assm: 1 < str len < 10000, 1 < k < 1000, best time sol
    * appr:
    * test cases:
    * */

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder buff = new StringBuilder(s.replaceAll("-",""));
        for(int idx = buff.length()-k; idx > 0; idx = idx - k) {
            buff.insert(idx,"-");
        }
        return buff.toString().toUpperCase();
    }

}
