package com.lrn.epi;

public class primitivetype {
    static class ReverseDigit {
        public int reverse(int num) {
            if(num == 0) {
                return 0;
            }
            int x = num, result =0;
            while(x != 0) {
                int dig = x % 10;
                result = result * 10 + dig;
                x = x /10;
            }
            return result;
        }

        public static void main(String[] args) {
            ReverseDigit reverseDigit = new ReverseDigit();
            System.out.println(String.format("Rev %d - %d", 1234, reverseDigit.reverse(1234)));
        }
    }

    static class PalindromicNumber {
        public boolean isPalindromicNum(int num) {
           if(num == 0) {
               return true;
           }
           int numLen = (int) Math.floor(Math.log10(num)) + 1;
           int msdMask = (int) Math.pow(10, numLen-1);
           for(int idx=0; idx<(numLen/2); idx++) {
               //    first dig      last dig
               if( (num % 10) != (num / msdMask) ) {
                   return false;
               }
               num = (num % msdMask); // remove last dig
               num = num / 10; // remove first dig
               msdMask = msdMask / 100; // since we removed to 2 digs
           }
           return true;
        }

        public static void main(String[] args) {
            PalindromicNumber palindromicNumber = new PalindromicNumber();
            System.out.println(palindromicNumber.isPalindromicNum(1221));
        }
    }
}
