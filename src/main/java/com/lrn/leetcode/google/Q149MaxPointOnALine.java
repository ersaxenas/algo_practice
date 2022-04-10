package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Q149MaxPointOnALine {
    /*2022-04-10T14:09:27.122Z
     * pd: Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     * assm: non null elem, elem in the array < 1000, best time sol.
     * appr:
     *  A line is determined by two factors,say y=ax+b
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course).
     *  Consider the gap between two points.
     *  We have (y2-y1)=a(x2-x1),a=(y2-y1)/(x2-x1) a is a rational, b is canceled since b is a constant
     *  If a third point (x3,y3) are on the same line. So we must have y3=ax3+b
     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
     *  Since a is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=a
     *  So we can use y0&x0 to track a line;
     *
     * */
    static class LPoint {
        public int X, Y;

        public LPoint(int x, int y) {
            this.X = x;
            this.Y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "X=" + X +
                    ", Y=" + Y +
                    '}';
        }
    }

    public int maxPoints(int[][] points) {
        int maxPoints = 0;
        if (points == null || points.length == 0) {
            return maxPoints;
        }
        if (points.length == 1) {
            return 1;
        }
        List<LPoint> lPoints = new ArrayList<>();
        for (int[] p : points) {
            LPoint lPoint = new LPoint(p[0], p[1]);
            lPoints.add(lPoint);
        }
        /*for each point try to build a line with other points*/
        for (int idx = 0; idx < points.length; idx++) {
            int duplicates=0,max=0;
            Map<String,Integer> pointMap = new HashMap<>();
            LPoint start = lPoints.get(idx);
            for (int idx2 = idx+1; idx2 < points.length; idx2++) {
                LPoint p2 = lPoints.get(idx2);
                int dx = p2.X - start.X;
                int dy = p2.Y - start.Y;
                /*check of duplicates*/
                if(dx==0 && dy ==0) {
                    duplicates++;
                    continue;
                }
                int gcd = gcd(dx,dy);
                int dfx = dx/gcd;
                int dfy = dy/gcd;
                String key = dfx + ":" + dfy;
                pointMap.put(key, pointMap.getOrDefault(key,0)+1);
                max = Math.max(max,pointMap.get(key));
            }
            maxPoints = Math.max(maxPoints, max+duplicates+1); // here +1 for the point at start
        }
        return maxPoints;

    }
    /*Euclid's algo*/
    public int gcd(int a, int b) {
        if(b==0) {
            return a;
        }
        return gcd(b,a%b);
    }

    public static void main(String[] args) {
        Q149MaxPointOnALine sol = new Q149MaxPointOnALine();
        System.out.println(sol.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {8, 8}, {6, 9}}));

    }

}
