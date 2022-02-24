package com.lrn.leetcode.google;

public class Q421MaximumXORofTwoNumbersinanArray {
    /*
    * pd: Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.
Follow up: Could you do this in O(n) runtime?
    * assm: best time sol, arr len < 10000, 0 < elem <10000,
    * appr: tries
    * test cases:
    * Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
    * Input: nums = [0]
Output: 0
    * */

    static class TrieNode {
        TrieNode _0;
        TrieNode _1;

        public TrieNode() {
        }
        public void insert(String binString) {
            if(binString.isEmpty()) {
                return;
            }
            char ch = binString.charAt(0);
            if(ch == '0') {
                if(_0 == null) {
                    _0 = new TrieNode();
                }
                _0.insert(binString.substring(1));
            } else {
                if(_1 == null) {
                    _1 = new TrieNode();
                }
                _1.insert(binString.substring(1));
            }
        }
    }

    public int findMaximumXOR(int[] nums) {
        String[] binaryRep = new String[nums.length];
        int numslen = nums.length;
        /*find max binary len of num*/
        int maxNum =0;
        for(int idx=0; idx<numslen; idx++) {
            maxNum = Math.max(nums[idx],maxNum);
        }
        int bitMask = 1 << Integer.toBinaryString(maxNum).length();
        TrieNode root = new TrieNode();
        for(int idx=0; idx<numslen; idx++) {
            // build trie
            String binRep = Integer.toBinaryString(nums[idx] | bitMask).substring(1); // cut binary string by max bits and remove first 1 as it comes from bitmask
            root.insert(binRep);
            binaryRep[idx] = binRep;
        }
        int maxXor =0;
        for(String num: binaryRep) {
            TrieNode node = root;
            int currXor = 0;
            for(char ch: num.toCharArray()) {
                char chToggle = (ch == '0') ? '1' : '0';
                if((chToggle == '0' && node._0 != null) || (chToggle == '1' && node._1 != null)) {
                    // opposite bit is present so take 1 -- we are doing xor
                    currXor = (currXor << 1) | 1; // shift by one and add 1 at the end so or with 1
                    node = (chToggle == '0') ? node._0 : node._1;
                } else {
                  currXor = currXor << 1; // just shift by 1 so it will add 0 to the end
                  node = (ch == '0') ? node._0 : node._1;
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }
        return maxXor;
    }

    public static void main(String[] args) {
        Q421MaximumXORofTwoNumbersinanArray sol = new Q421MaximumXORofTwoNumbersinanArray();
        System.out.println(sol.findMaximumXOR(new int[]{3,10,5,25,2,8}));
        System.out.println(sol.findMaximumXOR(new int[]{0}));
        System.out.println(sol.findMaximumXOR(new int[]{2,4}));
        System.out.println(sol.findMaximumXOR(new int[]{8, 10, 2}));
        System.out.println(sol.findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
    }


}
