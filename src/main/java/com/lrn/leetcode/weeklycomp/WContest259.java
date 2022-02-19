package com.lrn.leetcode.weeklycomp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WContest259 {

    /*
    * https://leetcode.com/contest/weekly-contest-259/problems/final-value-of-variable-after-performing-operations/
    * */

    public int finalValueAfterOperations(String[] operations) {
        int x=0;
        for(String op: operations){
            if(op.charAt(1) == '+') {
                x++;
            } else {
                x--;
            }
        }
        return x;
    }

    /*
    * https://leetcode.com/contest/weekly-contest-259/problems/sum-of-beauty-in-the-array/
    * */
    public int sumOfBeauties(int[] nums) {
        int sum = 0, maxleft=nums[0], minright=nums[nums.length-1];
        int[] rightMin = new int[nums.length];
        rightMin[nums.length-1] = nums[nums.length-1];
        for(int idx = nums.length-2; idx >= 0; idx--) {
            rightMin[idx] = minright;
            minright = Math.min(minright, nums[idx]);
        }

        for(int idx = 1; idx <= nums.length-2; idx++) {
            if(maxleft < nums[idx] && nums[idx] < rightMin[idx]) {
                sum = sum + 2;
            } else if(nums[idx-1] < nums[idx] && nums[idx] < nums[idx+1]) {
                sum++;
            }
            maxleft = Math.max(maxleft, nums[idx]);
        }
        return sum;
    }

    /*
    * https://leetcode.com/contest/weekly-contest-259/problems/detect-squares/
    * https://leetcode.com/problems/detect-squares/discuss/1471958/C%2B%2BJavaPython-2-approaches-using-HashMap-with-Picture-Clean-and-Concise
    * */
    class DetectSquares {
        int[][] pointcnt = new int[1001][1001];
        List<int[]> points = new ArrayList<>();
        public DetectSquares() {
        }

        public void add(int[] point) {
         points.add(point);
         // update the count in array
         int x = point[0], y = point[1];
         pointcnt[x][y] += 1;
        }

        public int count(int[] point) {
         int x1 = point[0], y1 = point[1], squares = 0;
         /* if a point p3 is at diagonal position where it can be part of a square then abs(p1.x-p3.x) == abs(p1.y-p3.y) && abs(p1.x-p3.x) > 0
            first find a diagonal position point */
         for(int[] p3: points) {
             int x3 = p3[0], y3 = p3[1];
             if(Math.abs(x3-x1) > 0 && Math.abs(x3 - x1) == Math.abs(y3 - y1)) {
                 /* found a diagonal point which can form a square. Now find other two points
                 other two point will be p2(x1, y3) and p1(x3, y1) if we have any point on p1 and p2
                 than count in the pointcnt array will be non-zero.
                 */
                 squares = squares + ( pointcnt[x1][y3] * pointcnt[x3][y1] );
             }
         }
          return squares;
        }
    }





    public static void main(String[] args) {
        WContest259 sol = new WContest259();

    }


}
