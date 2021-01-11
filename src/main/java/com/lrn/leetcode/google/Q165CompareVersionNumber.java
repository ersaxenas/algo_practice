package com.lrn.leetcode.google;

public class Q165CompareVersionNumber {
    /*
    * Given two version numbers, version1 and version2, compare them.
Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
    * assm: no white space, version no. length < 50, individual version < int max
    * appr:
    *
    * test cases:
    * Input: version1 = "1.01", version2 = "1.001" Output: 0
    * Input: version1 = "1.0", version2 = "1.0.0" Output: 0
    * Input: version1 = "0.1", version2 = "1.1" Output: -1
    * Input: version1 = "1.0.1", version2 = "1" Output: 1
    * Input: version1 = "7.5.2.4", version2 = "7.5.3" Output: -1
    * */

    public int compareVersionIter(String version1, String version2) {
        final String[] v1 = version1.split("\\.");
        final String[] v2 = version2.split("\\.");
        int maxLen = Math.max(v1.length,v2.length);
        for(int idx=0; idx<maxLen; idx++) {
            int vn1 = (idx<v1.length) ? Integer.parseInt(v1[idx]) : 0;
            int vn2 = (idx<v2.length) ? Integer.parseInt(v2[idx]) : 0;
            int cmp = Integer.compare(vn1, vn2);
            if(cmp != 0) {
                return cmp;
            }
        }
        return 0;
    }


    public int compareVersionRec(String version1, String version2) {
        if(version1 != null && version1.equals(version2)) {
            return 0;
        }
        final int dotidxv1 = version1.indexOf(".");
        final int dotidxv2 = version2.indexOf(".");
        int ver1 = 0;
        if(!version1.isEmpty()) {
            ver1 = Integer.parseInt((dotidxv1 != -1) ? version1.substring(0, dotidxv1) : version1);
        }
        int ver2 = 0;
        if(!version2.isEmpty()) {
          ver2 = Integer.parseInt((dotidxv2 != -1) ? version2.substring(0, dotidxv2) : version2);
        }
        if(ver1 < ver2) {
            return -1;
        } else if(ver1 > ver2) {
            return 1;
        }
        String v1 = (dotidxv1 != -1) ? version1.substring(dotidxv1 +1) : "";
        String v2 = (dotidxv2 != -1) ? version2.substring(dotidxv2 +1) : "";
        return compareVersionRec(v1,v2);
    }

    public static void main(String[] args) {
        Q165CompareVersionNumber sol = new Q165CompareVersionNumber();
        System.out.println((0 == sol.compareVersionRec("1.01", "1.001")) ? "passed" : "failed");
        System.out.println((0 == sol.compareVersionRec("1.00", "1.0.0")) ? "passed" : "failed");
        System.out.println((-1 == sol.compareVersionRec("0.1", "1.1")) ? "passed" : "failed");
        System.out.println((-1 == sol.compareVersionRec("7.5.2.4", "7.5.3")) ? "passed" : "failed");

        System.out.println((0 == sol.compareVersionIter("1.01", "1.001")) ? "passed" : "failed");
        System.out.println((0 == sol.compareVersionIter("1.00", "1.0.0")) ? "passed" : "failed");
        System.out.println((-1 == sol.compareVersionIter("0.1", "1.1")) ? "passed" : "failed");
        System.out.println((-1 == sol.compareVersionIter("7.5.2.4", "7.5.3")) ? "passed" : "failed");
    }

}
