package com.lrn.leetcode.weeklycomp;

import com.lrn.leetcode.google.LsUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WContest257 {
 /* https://leetcode.com/contest/weekly-contest-257/problems/count-special-quadruplets/
 * 1995. Count Special Quadruplets
 * */
 int cnt = 0;
    public int countQuadruplets(int[] nums) {
        for(int idx=nums.length-1; idx >= 3; idx--) {
            findnum(nums, idx-1, 1, nums[idx]);
        }
        return cnt;
    }

    public boolean findnum(int[] nums, int end, int n, int target) {
        if(target == 0 && n == 4) return true;
        if(end < 0 ) return false;
        for(int idx=end; idx >= 0; idx--) {
            if(findnum(nums, idx-1, n+1, target-nums[idx])) {
                cnt++;
            }
        }
        return false;
    }

    /* https://leetcode.com/contest/weekly-contest-257/problems/the-number-of-weak-characters-in-the-game/
    * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/1445242/Java-Solution-or-Use-only-Sorting-and-one-max-mark
    * 1996. The Number of Weak Characters in the Game
    * */

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        int maxdefence = 0, weakcount=0;

        for(int[] player : properties) {
            if(player[1] < maxdefence) {
                weakcount++;
            }
            maxdefence = Math.max(maxdefence, player[1]);
        }
        return weakcount;
    }

    /*1997. First Day Where You Have Been in All the Rooms
    * basic : prefix sum
    *
    * */

    public void prefixSum(int[] nums) {
        /* https://www.youtube.com/watch?v=AQBg24PiQGg
         idx   | 0 |  | 1 |  | 2 |  |  3 |  |  4 |  |  5 |  |  6 |
         nums  | 8 |  | 1 |  | 5 |  |  7 |  |  8 |  |  9 |  | 12 |
         psum  | 0 |  | 8 |  | 9 |  | 14 |  | 21 |  | 29 |  | 38 |  | 50 |
         now :
         1. get sum till index i -> psum[i+1]
         2. get sum from i to j (i inclusive) -> psum[j+1] - psum[i]
         2. get sum between i to j (i exclusive) -> psum[j+1] - psum[i+1]
         For ex:
         sum from index 2 to 6
         sum -> 5 + 7 + 8 + 9 + 12 = 41
         psum[6+1=7] - psum[2] = 50 - 9 = 41
         sum between index 2 to 6
         psum[6+1=7] - psum[2+1= 3] = 50 - 14 = 36
        * */
        int[] psum = new int[nums.length+1];
        psum[0] = 0;
        for(int idx=1; idx <= nums.length; idx++) {
            psum[idx] = psum[idx-1] + nums[idx-1];
        }
        LsUtil.printArray(nums);
        LsUtil.printArray(psum);
    }




    public int firstDayBeenInAllRoomsBruteForce(int[] nextVisit) {
        int N = nextVisit.length;
        Set<Integer> rooms = new HashSet<>();
        Map<Integer, Integer> visits = new HashMap<>();
        for(int idx=0; idx < nextVisit.length; idx++) {
            rooms.add(idx);
            visits.put(idx,0);
        }
        int day=0, nextroom=0;

        while(rooms.size() > 0) {
            System.out.println(nextroom);
            visits.put(nextroom, visits.get(nextroom)+1);
            rooms.remove(nextroom);
            if(rooms.size() == 0 ) break;
            if(visits.get(nextroom) % 2 == 0) { // even
                nextroom = (nextroom + 1) % N;
            } else { // odd
                nextroom = nextVisit[nextroom];
            }
            day++;
        }
        return day;
    }

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int N = nextVisit.length;
        int mod = (int)1e9 + 7;
        long dp[] = new long[N];
        dp[0] = 0;
        for(int idx=1; idx < N; idx++) {
            dp[idx] = ( 2 * dp[idx-1] // have to visit room idx -1 twice ( 1 attempt odd count, 2 attempt even count)
                        - dp[nextVisit[idx-1]] //if nextVisit[idx-1] = roomx, so when vising room idx-1 first time it will send us back to roomx
                                               // so from roomx we have to reach to room idx - 1 again but since we are starting at roomx we can subtract
                                               // step from idx 0 to roomx
                       + 2 // ( 1 day is needed to move from room idx-1 to idx and 1 day is needed from idx to roomx
                       + mod // overflow
                    ) %mod;
        }
        return (int) dp[N-1];
    }

    public static void main(String[] args) {
       WContest257 sol = new WContest257();
       //System.out.println(sol.countQuadruplets(new int[]{1, 1, 1, 3, 5}));
       sol.prefixSum(new int[] {8, 1, 5, 7, 8, 9, 12});
    }




}
