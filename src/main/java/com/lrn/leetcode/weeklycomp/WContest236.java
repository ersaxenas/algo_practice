package com.lrn.leetcode.weeklycomp;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class WContest236 {

    /*  https://leetcode.com/contest/weekly-contest-236/problems/sign-of-the-product-of-an-array/
       1822. Sign of the Product of an Array
       assm: arrlen < 1000, best time sol
       appr: count -ves if even then ans 1 or -1, if encounter 0 then ans 0
       test cases:
     */
    public int arraySign(int[] nums) {
        int neg = 0;
        for (int num : nums) {
            if (num < 0) {
                neg++;
            } else if (num == 0) {
                return 0;
            }
        }
        return (neg % 2 == 0) ? 1 : -1;
    }


    public int findTheWinner(int n, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int idx = 1; idx <= n; idx++) {
            queue.offer(idx);
        }
        while (queue.size() > 1) {
            for (int idx = 1; idx < k && queue.size() > 1; idx++) {
                queue.offer(queue.poll());
            }
            System.out.println(queue.poll());
        }
        return queue.poll();
    }

    class SS {
        public int jumps, lane = 2, index = 0;

        public SS() {
        }

        ;

        public SS(int lane, int index, int jumps) {
            this.jumps = jumps;
            this.lane = lane;
            this.index = index;
        }

        @Override
        public String toString() {
            return "{ [" + jumps + "],<" + lane + ", " + index + "> }";
        }
    }

    public int minSideJumps(int[] obstacles) {
        ArrayDeque<SS> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][obstacles.length];
        deque.add(new SS());
        int minJumps = Integer.MAX_VALUE;
        while (!deque.isEmpty()) {
            SS st = deque.poll();
            visited[st.lane][st.index] = true;
            if (st.index == obstacles.length - 1) {
                minJumps = Math.min(minJumps, st.jumps);
                continue;
            }
            // move next index
            if (obstacles[st.index + 1] != st.lane) {
                deque.offer(new SS(st.lane, st.index + 1, st.jumps));
            }
            if (st.lane == 1) {
                if (!visited[2][st.index] && obstacles[st.index] != 2) {
                    deque.offer(new SS(2, st.index, st.jumps + 1));
                }
                if (!visited[3][st.index] && obstacles[st.index] != 3) {
                    deque.offer(new SS(3, st.index, st.jumps + 1));
                }
            } else if (st.lane == 2) {
                if (!visited[1][st.index] && obstacles[st.index] != 1) {
                    deque.offer(new SS(1, st.index, st.jumps + 1));
                }
                if (!visited[3][st.index] && obstacles[st.index] != 3) {
                    deque.offer(new SS(3, st.index, st.jumps + 1));
                }
            } else if (st.lane == 3) {
                if (!visited[2][st.index] && obstacles[st.index] != 2) {
                    deque.offer(new SS(2, st.index, st.jumps + 1));
                }
                if (!visited[1][st.index] && obstacles[st.index] != 1) {
                    deque.offer(new SS(1, st.index, st.jumps + 1));
                }
            }

        }
        return minJumps;
    }

    public int minSideJumps2(int[] obstacles) {
        int[] jumps = new int[]{1, 0, 1};
        for (int obstacle : obstacles) { /* obstacle array also represents length of lanes */
            if (obstacle > 0) { /* if obstacle found */
                jumps[obstacle - 1] = 1000000; /* Set jump to very large number. */
            }
            for (int currentLane = 0; currentLane < 3; ++currentLane) { /* for each lane calculate jumps */
                if (obstacle != currentLane + 1) { /* if current lane doesn't have obstacle */
                    /* From a lane frog can jump to two lanes
                     * +1 for next lane and +2 for next to next lane
                     * If currentLane +1 or +2 > 3 then module by 3 will give correct index of lane.
                     * We have 3 lanes : lane1 (index 0 of jumps array), lane2 (index 1 of jumps array), lane2 (index 2 of jumps array)
                     * So if frog is at lane 1 (index = 0 ) then next two lanes are : (0+1) % 3 = 1, (0+2) % 3 = 2
                     *    if frog is at lane 2 (index = 1 ) then next two lanes are : (1+1) % 3 = 2, (1+2) % 3 = 0
                     *    if frog is at lane 3 (index = 2 ) then next two lanes are : (2+1) % 3 = 0, (2+2) % 3 = 1
                     * It is like a circular list, move ahead by +1 and +2 index.
                     * */
                    final int lane1 = (currentLane + 1) % 3; /* first lane where frog can jump*/
                    final int lane2 = (currentLane + 2) % 3; /* second lange where frog can jump*/
                    final int lane1Jump = jumps[lane1]; /* current count of jumps taken to reach first lane*/
                    final int lane2Jump = jumps[lane2]; /* current count of jumps taken to reach second lane*/
                    /*
                     * Here calculating minimum jumps required to reach current lane from other two lanes.
                     * Since Frog starts at middle lane Lane2 so jumps array has been initialized with { 1 (lane 1), 0 (lane 2), 1 (lane 3)}
                     * Why: Frog starts at lane 2 so how many jumps it needs to reach
                     * a). lane 1 : 1 jump
                     * b). lane 2 : 0 jump
                     * a). lane 3 : 1 jump
                     * Frog moves froward and when it encounters an obstacle it jumps.
                     * That is why when an obstacle is encountered we are setting value in jump array to large value (1000000).
                     * */
                    jumps[currentLane] = Math.min(jumps[currentLane], Math.min(lane1Jump, lane2Jump) + 1);
                }
            }
        }
        return Math.min(jumps[0], Math.min(jumps[1], jumps[2]));
    }

    public static void main(String[] args) {
        WContest236 sol = new WContest236();
//        System.out.println("ans: " + sol.findTheWinner(5, 2));
        System.out.println(sol.minSideJumps2(new int[]{0, 1, 2, 3, 0}));
    }

}
