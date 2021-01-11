package com.lrn.leetcode.weeklycomp;

import java.util.Arrays;
import java.util.Stack;

public class Context196 {
    /*1503. Last Moment Before All Ants Fall Out of a Plank
     * https://leetcode.com/contest/weekly-contest-196/problems/last-moment-before-all-ants-fall-out-of-a-plank/
     * If two ants bump into each other and change directions, it's the same as if these ants continue as nothing happens.
     * So, we can think about that plank as a two-way street. So, find the maximum units that any ant needs to travel.
     * */
    static class AntPlank {
        public int getLastMoment(int n, int[] left, int[] right) {
            int maxLeft = 0, minRight = Integer.MAX_VALUE;
            for (int elem : left) {
                maxLeft = Math.max(maxLeft, elem);
            }
            for (int elem : right) {
                minRight = Math.min(minRight, elem);
            }
            return Math.max(maxLeft, n - minRight);
        }
    }

    /*
     *
     * */

    static class SubmatricesCount {
        public int numSubmat(int[][] mat) {
            int M = mat.length, N = mat[0].length;

            int res = 0;
            for (int up = 0; up < M; ++up) {
                int[] h = new int[N];
                Arrays.fill(h, 1);
                for (int down = up; down < M; ++down) {
                    for (int k = 0; k < N; ++k) {
                        h[k] &= mat[down][k];
                    }
                    res += countOneRow(h);
                }
            }

            return res;
        }

        private int countOneRow(int[] A) {

            int res = 0, length = 0;
            for (int i = 0; i < A.length; ++i) {
                length = (A[i] == 0 ? 0 : length + 1);
                res += length;
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] arr = new int[3][3];
            arr[0] = new int[]{1, 0, 1};
            arr[1] = new int[]{1, 1, 0};
            arr[2] = new int[]{1, 1, 0};
            SubmatricesCount submatricesCount = new SubmatricesCount();
            System.out.println(submatricesCount.numSubmat(arr));
        }
    }

    static class SubmatricesCount2 {
        public int numSubmat(int[][] mat) {

            int M = mat.length, N = mat[0].length;

            int res = 0;

            int[] h = new int[N];
            for (int i = 0; i < M; ++i) {
                for (int j = 0; j < N; ++j) {
                    h[j] = (mat[i][j] == 0 ? 0 : h[j] + 1);
                }
                res += helper(h);
            }

            return res;
        }

        private int helper(int[] A) {

            int[] sum = new int[A.length];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < A.length; ++i) {

                while (!stack.isEmpty() && A[stack.peek()] >= A[i]) {
                    stack.pop();
                }

                if (!stack.isEmpty()) {
                    int preIndex = stack.peek();
                    sum[i] = sum[preIndex];
                    sum[i] += A[i] * (i - preIndex);
                } else {
                    sum[i] = A[i] * (i + 1);
                }

                stack.push(i);
            }

            int res = 0;
            for (int s : sum) res += s;

            return res;
        }

        public static void main(String[] args) {
            int[][] arr = new int[3][3];
            arr[0] = new int[]{1, 0, 1};
            arr[1] = new int[]{1, 1, 0};
            arr[2] = new int[]{1, 1, 0};
            SubmatricesCount2 submatricesCount = new SubmatricesCount2();
            System.out.println(submatricesCount.numSubmat(arr));
        }
    }


}
