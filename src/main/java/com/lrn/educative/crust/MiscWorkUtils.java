package com.lrn.educative.crust;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class MiscWorkUtils {
    static class SumOfThreeValues {
        public String findValuesWithSum1(Integer[] arr, int KSum) {
            StringBuilder buffer = new StringBuilder();
            if (arr == null || arr.length == 0) {
                return buffer.toString();
            }
            Arrays.sort(arr);
            for (int idx = 0; idx < arr.length - 2; idx++) {
                int targetSum = KSum - arr[idx];
                int pfwd = idx + 1, pbwd = arr.length - 1;
                while (pfwd < pbwd) {
                    int sum = arr[pfwd] + arr[pbwd];
                    if (targetSum < sum) {
                        pfwd++;
                    } else if (targetSum > sum) {
                        pbwd--;
                    } else {
                        buffer.append(arr[idx]).append(",");
                        buffer.append(arr[pfwd]).append(",");
                        buffer.append(arr[pbwd]);
                        break;
                    }
                }
            }
            return buffer.toString();
        }

        public String findValuesWithSum2(Integer[] arr, int KSum) {
            StringBuilder buffer = new StringBuilder();
            if (arr == null || arr.length == 0) {
                return buffer.toString();
            }
            Arrays.sort(arr);
            System.out.println("arr = " + Arrays.asList(arr));
            for (int idx1 = 0; idx1 < arr.length - 2; idx1++) {
                for (int idx2 = idx1 + 1; idx2 < arr.length - 1; idx2++) {
                    int target = KSum - (arr[idx1] + arr[idx2]);
                    //System.out.println("target = " + target);
                    int tIndex = binarySearchArr(arr, 0, arr.length - 1, target);
                    if (tIndex != -1 && target != idx1 && tIndex != idx2) {
                        buffer.append(arr[idx1]).append(",");
                        buffer.append(arr[idx2]).append(",");
                        buffer.append(arr[tIndex]);
                    }
                }
            }
            return buffer.toString();
        }

        public Integer binarySearchArr(Integer[] arr, int start, int end, int elemToFind) {
            if (arr == null || arr.length == 0) {
                return -1;
            }
            if (start == end) {
                return elemToFind == arr[start] ? start : -1;
            }
            while (start < end) {
                int mid = start + ((end - start) / 2);
                int elemMid = arr[mid];
                if (elemToFind < elemMid) {
                    end = mid - 1;
                } else if (elemToFind > elemMid) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            SumOfThreeValues sumOfThreeValues = new SumOfThreeValues();
            Integer[] arr = {3, 7, 1, 2, 8, 4, 5};
            System.out.println("Original Array: " + Arrays.toString(arr));
            System.out.println("Sum 20 exists " + sumOfThreeValues.findValuesWithSum1(arr, 20));
            System.out.println("Sum 10 exists " + sumOfThreeValues.findValuesWithSum1(arr, 10));
            System.out.println("Sum 21 exists " + sumOfThreeValues.findValuesWithSum1(arr, 21));
            System.out.println("Sum 20 exists " + sumOfThreeValues.findValuesWithSum2(arr, 20));
            System.out.println("Sum 10 exists " + sumOfThreeValues.findValuesWithSum2(arr, 10));
            System.out.println("Sum 21 exists " + sumOfThreeValues.findValuesWithSum2(arr, 21));
        }
    }

    /*Make Columns and Rows Zeros*/
    static class MatrixRowsColZero {
        public void makeRowAncColdZero(int[][] matrix) {
            if (matrix == null && matrix.length == 0) {
                return;
            }
            int N = matrix[0].length;
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            for (int row = 0; row < N; row++) {
                if (rowSet.contains(row)) {
                    continue;
                }
                for (int col = 0; col < N; col++) {
                    if (colSet.contains(col)) {
                        continue;
                    }
                    if (matrix[row][col] == 0) {
                        rowSet.add(row);
                        for (int idx = 0; idx < N; idx++) {
                            matrix[row][idx] = 0;
                        }

                        colSet.add(col);
                        for (int idx = 0; idx < N; idx++) {
                            matrix[idx][col] = 0;
                        }
                        break; // remember
                    }
                }
            }
        }

        public void printMatrix(int[][] m) {
            System.out.println();
            for (int i = 0; i < m.length; ++i) {
                for (int j = 0; j < m[i].length; ++j) {
                    System.out.print(m[i][j]);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            MatrixRowsColZero matrixRowsColZero = new MatrixRowsColZero();
            int[][] matrix = {
                    {5, 4, 3, 9},
                    {2, 0, 7, 6},
                    {1, 3, 4, 0},
                    {9, 8, 3, 4}
            };
            System.out.print("Original Matrix");
            matrixRowsColZero.printMatrix(matrix);
            matrixRowsColZero.makeRowAncColdZero(matrix);
            System.out.print("\nMatrix with Zeros");
            matrixRowsColZero.printMatrix(matrix);
        }
    }

    /*Search in a Matrix*/
    /*
     * rows are sorted and col are sorted
     * */
    static class MatrixSearch {

        static class Pair<F, S> {
            F first;
            S second;

            public Pair(F f, S s) {
                this.first = f;
                this.second = s;
            }
        }

        static class IntPair extends Pair<Integer, Integer> {
            public IntPair(Integer first, Integer second) {
                super(first, second);
            }

            public static IntPair of(Integer first, Integer second) {
                return new IntPair(first, second);
            }
        }

        public IntPair findKey(int[][] matrix, int key) {
            if (matrix == null) {
                return IntPair.of(-1, -1);
            }
            int rowMax = matrix.length;
            int colMax = matrix[0].length;
            int row = 0, col = colMax - 1;

            while (row < rowMax && col >= 0) {
                int currElem = matrix[row][col];
                if (currElem == key) {
                    return IntPair.of(row, col);
                } else if (key < currElem) {
                    col--; // go left towards smaller keys
                } else {
                    row++; // got down towards larger keys
                }
            }
            return IntPair.of(-1, -1);
        }

        public static void main(String[] args) {
            MatrixSearch matrixSearch = new MatrixSearch();
            int[][] matrix = new int[][]{
                    {2, 4, 9, 13, 15},
                    {3, 5, 11, 18, 22},
                    {6, 8, 16, 21, 28},
                    {9, 11, 20, 25, 31},
            };
            IntPair val_loc = matrixSearch.findKey(matrix, 8);
            System.out.println("Verifying at " + val_loc.first + "," + val_loc.second + ": " + matrix[val_loc.first][val_loc.second]);
        }
    }

    /*Implement LRU Cache*/
    static class LRUCache {
        static class Pair {
            int first;
            int second;

            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }

            public static Pair of(int first, int second) {
                return new Pair(first, second);
            }

            @Override
            public String toString() {
                return "Pair{" +
                        "first=" + first +
                        ", second=" + second +
                        '}';
            }
        }

        int capacity;
        HashSet<Integer> dataSet;
        LinkedList<Pair> list;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.dataSet = new HashSet<>(capacity);
            this.list = new LinkedList<>();
        }

        public Integer get(int key) {
            Pair next;
            if (dataSet.contains(key)) {
                ListIterator<Pair> pairListIterator = list.listIterator();
                while (pairListIterator.hasNext()) {
                    next = pairListIterator.next();
                    if (next.first == key) {
                        pairListIterator.remove();
                        list.addLast(next);
                        return next.second;
                    }
                }
            }
            return null;
        }

        public void set(int key, int value) {
            if (dataSet.contains(key)) {
                list.forEach(elem -> {
                    if (elem.first == key) {
                        elem.second = value;
                    }
                });
            } else {
                if (isFull()) {
                    evictData();
                }
                Pair pair = Pair.of(key, value);
                list.addLast(pair);
                dataSet.add(key);
            }
        }

        public boolean isFull() {
            return dataSet.size() == capacity;
        }

        public void evictData() {
            if (!list.isEmpty()) {
                Pair pair = list.removeFirst();
                dataSet.remove(pair.first);
            }
        }

        void print() {
            ListIterator<Pair> iterator = list.listIterator(0);
            while (iterator.hasNext()) {
                Pair node = iterator.next();
                System.out.print("(" + node.first + "," + node.second + ")");
            }
            System.out.println("");
        }

        public static void main(String[] args) {
            LRUCache cache = new LRUCache(2);

            cache.set(10, 20);
            cache.print();

            cache.set(15, 25);
            cache.print();

            cache.set(20, 30);
            cache.print();

            cache.set(25, 35);
            cache.print();

            cache.set(5, 25);
            cache.print();
        }
    }

    /*Closest Meeting Point*/
    static class MeetingPointInGrid {
        /*Euclidean distance (considers diagonal move as well) between two points = sqrt( (x2 - x1) pow 2 + ( y2 - y1 ) pow 2 )
        /*Manhattan distance (NOT considers diagonal move) between two points = abs( x2 - x1 ) + abs ( y2 - y1 )
        * */
        static class Point {
            int X;
            int Y;

            public static Point of(int x, int y) {
                Point point = new Point();
                point.X = x;
                point.Y = y;
                return point;
            }

            @Override
            public String toString() {
                return "Point{X=" + X + ", Y=" + Y + '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return new EqualsBuilder().append(X, point.X).append(Y, point.Y).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(X).append(Y).toHashCode();
            }

            public double calculateEuclideanDistance(Point p) {
                if (p == null || this.equals(p)) {
                    return 0;
                }
                return Math.sqrt(Math.pow((p.X - this.X), 2) + Math.pow((p.Y - this.Y), 2));
            }

            public double calculateManhattanDistance(Point p) {
                if (p == null || this.equals(p)) {
                    return -1;
                }
                return Math.abs(p.X - this.X) + Math.abs(p.Y - this.Y);
            }
        }

        public Point findMeetingPoint1(int M, List<Point> points) {
            Point minPoint=Point.of(-1,-1);
            if (points == null || points.isEmpty()) {
                return minPoint;
            }
            double minDistance = Double.MAX_VALUE;
            for (int row = 1; row <=M; row++) {
                for (int col = 1; col <= M; col++) {
                    double distance = 0;
                    Point currPoint = Point.of(row,col);
                    for (Point point : points) {
                        distance = distance + currPoint.calculateEuclideanDistance(point);
                    }
                    if(distance < minDistance) {
                        minDistance = distance;
                        minPoint = currPoint;
                    }
                }
            }
            System.out.println(String.format("Minimum distance point X:%d, Y:%d, distance: %f", minPoint.X, minPoint.Y, minDistance));
            return minPoint;
        }

        public Point findMeetingPoint2(int M, List<Point> points) {
            Point minPoint=Point.of(-1,-1);
            if (points == null || points.isEmpty()) {
                return minPoint;
            }
            // find centroid
            int cX = 0, cY = 0, cnt=0;
            for (Point point : points) {
                cX = cX + point.X;
                cY = cY + point.Y;
                cnt++;
            }
            Point centriod = Point.of(-1,-1);
            centriod.X = Math.round(cX/cnt);
            centriod.Y = Math.round(cY/cnt);
            System.out.println("Centriod found at: " + centriod);

            double minDistance = Double.MAX_VALUE;
            for (int row = centriod.X -1; row <=centriod.X+1; row++) {
                for (int col = centriod.Y -1; col <= centriod.Y+1; col++) {
                    double distance = 0;
                    Point currPoint = Point.of(row,col);
                    for (Point point : points) {
                        distance = distance + currPoint.calculateEuclideanDistance(point);
                    }
                    if(distance < minDistance) {
                        minDistance = distance;
                        minPoint = currPoint;
                    }
                }
            }
            System.out.println(String.format("Minimum distance point X:%d, Y:%d, distance: %f", minPoint.X, minPoint.Y, minDistance));
            return minPoint;
        }

        public static void main(String[] args) {
            MeetingPointInGrid meetingPointInGrid = new MeetingPointInGrid();
            int [] arr = {5, 10, 8};
            ArrayList<Point> points = new ArrayList<Point>();
            points.add(Point.of(1, 2));
            points.add(Point.of(3, 3));
            points.add(Point.of(4, 2));

            System.out.println("Grid 5x5 and values ((1, 2), (3, 3), (4, 2))");

            Point pt = meetingPointInGrid.findMeetingPoint2(arr[0], points);
            System.out.println("Shortest Distance Point = p(" + pt.X + ", " + pt.Y + ")");
            ArrayList<Point> points2 = new ArrayList<Point>();
            points2.add(Point.of(1, 1));
            points2.add(Point.of(3, 5));
            points2.add(Point.of(6, 2));
            points2.add(Point.of(7, 7));
            points2.add(Point.of(8, 4));

            System.out.println("\nGrid 10x10 and values ((1, 1), (3, 5), 24, 2), (7, 7), (8,4))");
            pt = meetingPointInGrid.findMeetingPoint2(arr[1], points2);
            System.out.println("Shortest Distance Point = p(" + pt.X + ", " + pt.Y + ")");

            ArrayList<Point> points3 = new ArrayList<>();
            points3.add(Point.of(4, 3));
            points3.add(Point.of(5, 5));
            points3.add(Point.of(2, 7));

            System.out.println("\nGrid 8x8 and values ((4, 3), (5, 5), (2, 7))");
            pt = meetingPointInGrid.findMeetingPoint2(arr[2], points3);
            System.out.println("Shortest Distance Point = p(" + pt.X + ", " + pt.Y + ")");
        }
    }
}
