package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Q952LargestComponentSizebyCommonFactor {
    /*
    * pd: https://leetcode.com/problems/largest-component-size-by-common-factor/
    * approach: https://leetcode.com/problems/largest-component-size-by-common-factor/solution/
    *
    * */

    static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n+1];
            size = new int[n+1];
            for(int idx=0; idx <= n; idx++) {
                parent[idx] = idx; // each node is parent of its own
            }
            Arrays.fill(size, 1);
        }

        public int find(int a) {
            if(parent[a] != a) {
                parent[a] = find(parent[a]); // find parent of parent
            }
            return parent[a];
        }

        public boolean connect(int a, int b) {
            int parentOfA = find(a);
            int parentOfB = find(b);

            if(parentOfA == parentOfB) {
                return false; // already connected to same parent
            }

            if(size[parentOfA] > size[parentOfB]) {
                // swap parents of a and b so that parentOfA points to a group with less size
                // and b points to group with large size
                int tmp = parentOfA;
                parentOfA = parentOfB;
                parentOfB = tmp;
            }
            // merge two groups - smaller group is merging in to larger one - set parent of small group to larger group
            parent[parentOfA] = parentOfB; // now b (larger group) is parent of a ( small group)
            size[parentOfB] = size[parentOfB] + size[parentOfA];
            return true;
        }
        public boolean isConnected(int a , int b) {
            return find(a) == find(b);
        }
    }

    //Approach 2: Union-Find via Factors
    public int largestComponentSize1(int[] nums) {
        int maxVal = nums[0];
        for(int num: nums) {
            maxVal = Math.max(maxVal, num);
        }
        UnionFind uf = new UnionFind(maxVal);
        for(int num: nums) {
            for(int parent=2; parent < (int)(Math.sqrt(num))+1; parent++) {
                if(num % parent == 0) {
                    uf.connect(parent, num);
                    // if number is divisible by x than it will also be divisible by num/x
                    uf.connect(num/parent, num);
                }
            }
        }

        // now count the size of group and keep track of max size;
        int maxGroup = 0;
        HashMap<Integer, Integer> groupCnt = new HashMap<>();
        for(int num: nums) {
            int parent = uf.find(num);
            int count = groupCnt.getOrDefault(parent, 0);
            count++;
            groupCnt.put(parent, count);
            maxGroup = Math.max(maxGroup, count);
        }
        return maxGroup;
    }


    /*
     * Prime factors, prime decompose, sieve of Eratosthenes (let's call it sieve method for short), Sieve Method
     * */

    public List<Integer> primeFactors(int num) {
        List<Integer> result = new ArrayList<>();
        int primeFactor = 2; // start at 2 and then 3, 4, 5, 6, ... n
        /*
         * a no. can always be divided in to prime factors
         * Ex.
         * num = 6, prime factors = 2 x 3 ( one 2, one 3)
         * num = 12, prime factors = 2 x 2 x 3 ( two 2, one 3)
         * num = 27, prime factors = 3 x 3 x 3  ( three 3)
         * */
        while ( num >=  (primeFactor * primeFactor) ) {
            if(num % primeFactor == 0) { // divisible by prime
                result.add(primeFactor);
                num = num / primeFactor;
                continue; // once again try with same primeFactor
            }
            primeFactor++;// move to next prime factor
        }
        /* we are dividing num by prime factor num = num / prime factor.
         * Last no. will be prime factor itself so add the num to result
         * Ex: 13 at 4 while loop will break but 13 is divisible by 13
         *   : 6 - 2 x 3 at 3 while loop will terminate but 3 is part of prime factors,
         * */
        result.add(num);
        return result;
    }
   // Approach 2 : based on prime factors
    public int largestComponentSize2(int[] nums) {
        int maxVal = nums[0];
        for(int num: nums) {
            maxVal = Math.max(maxVal, num);
        }
        System.out.println("maxVal: " + maxVal);
        UnionFind uf = new UnionFind(maxVal);
        for(int num: nums) {
            List<Integer> primes = primeFactors(num);
            int smallestPrime = primes.get(0);
            uf.connect(smallestPrime, num); // connect no. with first prime
            // now connect primes since they belong to same no. so same group
            for(int idx=0; idx < primes.size(); idx++) {
                if(smallestPrime != primes.get(idx) && primes.get(idx) != primes.get(idx-1)) {
                    uf.connect(smallestPrime, primes.get(idx));
                }
            }
        }
        HashMap<Integer, Integer> groupCount = new HashMap<>();
        int maxGroup = 0;
        for(int num: nums) {
            int groupId = uf.find(num);
            groupCount.put(groupId, groupCount.getOrDefault(groupId, 0)+1);
            maxGroup = Math.max(maxGroup, groupCount.get(groupId));
        }
        return maxGroup;
    }

    public static void main(String[] args) {
       Q952LargestComponentSizebyCommonFactor sol = new Q952LargestComponentSizebyCommonFactor();
//        for(int num: Arrays.asList(4,6,15,35)) {
//            System.out.print(num + " -> ");
//            LsUtil.printList(sol.primeFactors(num));
//        }
        System.out.println(sol.largestComponentSize2(new int[]{20, 50, 9, 63}));

    }





}
