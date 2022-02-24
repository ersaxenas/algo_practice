package com.lrn.leetcode.google;

public class Q393UTF8Validation {
    /*
    * pd: A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

* ( observations)
* For First byte N 1 bits
* 1. firt n bit tell how many bytes whole utf has including 1st one. So look at 0xxxxxxx is valid but
* 10xxxxxx is not valid because single byte utf will start with 0 ( it represents utf directly)
* Ex: 10xxxxxx 10xxxxxx is invalid because in total it is two bytes
*     10xxxxxx is invalid because it is a single digit utf and must start with 0
* So over call an utf seq's first byte will start with 0xxx... or 110xx... it will never start with 10xxxx
* 2. First byte will never have more than 4 ones because upf8 is 4 bytes long
* For rest of the bytes : just check if it starts with 10
* finally array size must be == Firt byte N 1 bits
Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.
    * assm: arr len < 1000, best time sol
    * appr:
    *
    * */

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }

        int idx = 0;
        int noOf1 = 0;
        while (idx < data.length) {
            String brep = getBString(data[idx]);
            if(noOf1 != 0) {
                if (!checkNextNBytesFor10(data, idx)) {
                    return false;
                }
                noOf1--;
            } else {
                for (int i = 0; i < brep.length(); i++) {
                    if (brep.charAt(i) == '1') {
                        noOf1++;
                    } else {
                        break;
                    }
                }
                if (noOf1 == 1 || noOf1 > 4) {
                    return false;
                } else if(noOf1 > 1) {
                    noOf1--;
                }
            }
          idx++;
        }
        return noOf1 == 0;
    }

    public boolean checkNextNBytesFor10(int[] nums, int idx) {
        String brep = getBString(nums[idx]);
        if (!brep.startsWith("10")) {
            return false;
        }
        return true;
    }

    public String getBString(int num) {
        String brep = Integer.toBinaryString(num);
        if (brep.length() > 8) { // only need 8 least significant bits
            brep = brep.substring(brep.length() - 8);
        } else if (brep.length() < 8) { // append 0 to make 8 bit representation
            for (int idx = 0; idx < (8 - brep.length()); idx++) {
                brep = "0" + brep;
            }
        }
        return brep;
    }

    public static void main(String[] args) {
        Q393UTF8Validation sol = new Q393UTF8Validation();
        System.out.println(sol.validUtf8(new int[]{197, 130, 1}));
        System.out.println(sol.validUtf8(new int[]{235, 140, 4}));
    }


}
