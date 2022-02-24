package com.lrn.educative.gci.heap;

import com.lrn.educative.dsj.heap.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class WorkHeapUtils {
    static class MedianOfNumbers {
        private Heap maxHeap = new Heap(true, 50, false);
        private Heap minHeap = new Heap(true, 50, true);
        private int size = 0;

        /*
         *       max heap              -       min heap
         *  tail  e < e < e  head      -   head e < e < e tail
         * */
        public Double findMedian() {
            if (size == 0) {
                return null;
            }
            if (size % 2 == 0) {
                // even
                return (double) (maxHeap.top() + minHeap.top()) / 2;
            } else {
                //odd
                return (double) maxHeap.top();
            }
        }

        public MedianOfNumbers insert(Integer num) {
            if (maxHeap.isEmpty() || maxHeap.top() > num) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                //move element from maxHeap to minHeap
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            size++;
            return this;
        }

        public static void main(String[] args) {
            MedianOfNumbers medianOfNumbers = new MedianOfNumbers();
            medianOfNumbers.insert(3).insert(1);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
            medianOfNumbers.insert(5);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
            medianOfNumbers.insert(4);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
        }
    }

    static class MedianOfNumbers2 {
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        private int size = 0;

        /*
         *       max heap              -       min heap
         *  tail  e < e < e  head      -   head e < e < e tail
         * */
        public Double findMedian() {
            if (size == 0) {
                return null;
            }
            if (size % 2 == 0) {
                // even
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                //odd
                return (double) maxHeap.peek();
            }
        }

        public MedianOfNumbers2 insert(Integer num) {
            if (maxHeap.isEmpty() || maxHeap.peek() > num) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                //move element from maxHeap to minHeap
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            size++;
            return this;
        }

        public static void main(String[] args) {
            MedianOfNumbers2 medianOfNumbers = new MedianOfNumbers2();
            medianOfNumbers.insert(3).insert(1);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
            medianOfNumbers.insert(5);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
            medianOfNumbers.insert(4);
            System.out.println("The median is: " + medianOfNumbers.findMedian());
        }
    }

    /*Sliding Window Median*/
    static class SlidingWindowMedian {
        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public double[] findSlidingWindowMedian(int[] nums, int k) {
            assert nums != null && k > 0;
            List<Double> result = new ArrayList<>();
            for (int idx = 0; idx < nums.length; idx++) {
                final int elem = nums[idx];
                if (maxHeap.isEmpty() || maxHeap.peek() >= elem) {
                    maxHeap.offer(elem);
                } else {
                    minHeap.offer(elem);
                }
                rebalance();
                if (k == (maxHeap.size() + minHeap.size())) { // window established
                    if (maxHeap.size() == minHeap.size()) {
                        // even elements
                        result.add((double) (minHeap.peek() + maxHeap.peek()) / 2);
                    } else {
                        // odd elements
                        result.add((double) maxHeap.peek());
                    }
                    // remove first element from the window
                    int elementToBeRemoved = nums[idx + 1 - k];
                    if (maxHeap.peek() >= elementToBeRemoved) {
                        maxHeap.remove(elementToBeRemoved);
                    } else {
                        minHeap.remove(elementToBeRemoved);
                    }
                    rebalance();
                }
            }
            double[] arr = new double[result.size()];
            for (int idx = 0; idx < arr.length; idx++) {
                arr[idx] = result.get(idx);
            }
            return arr;
        }

        public void rebalance() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public static void main(String[] args) {
            SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
            double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[]{1, 2, -1, 3, 5}, 2);
            System.out.print("Sliding window medians are: ");
            for (double num : result)
                System.out.print(num + " ");
            System.out.println();

            slidingWindowMedian = new SlidingWindowMedian();
            result = slidingWindowMedian.findSlidingWindowMedian(new int[]{1, 2, -1, 3, 5}, 3);
            System.out.print("Sliding window medians are: ");
            for (double num : result)
                System.out.print(num + " ");
        }
    }

    /*Maximize Capital*/
    static class MaximizeCapital {
        class PrCapAndPro {
            int capital;
            int profits;

            public PrCapAndPro(int capital, int profits) {
                this.capital = capital;
                this.profits = profits;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PrCapAndPro that = (PrCapAndPro) o;
                return capital == that.capital &&
                        profits == that.profits;
            }

            @Override
            public int hashCode() {
                return Objects.hash(capital, profits);
            }

            @Override
            public String toString() {
                return "PrCapAndPro{" +
                        "capital=" + capital +
                        ", profits=" + profits +
                        '}';
            }
        }

        public int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
            PriorityQueue<PrCapAndPro> maxProfitHeap = new PriorityQueue<>(Comparator.comparing((PrCapAndPro pr) -> pr.profits).reversed());
            PriorityQueue<PrCapAndPro> minCapitalHeap = new PriorityQueue<>((PrCapAndPro a, PrCapAndPro b) -> Integer.compare(a.capital, b.capital));
            for (int idx = 0; idx < capital.length; idx++) {
                minCapitalHeap.offer(new PrCapAndPro(capital[idx], profits[idx]));
            }
            int currentCap = initialCapital;
            for (int prjCnt = 0; prjCnt < numberOfProjects; prjCnt++) {
                // for initial capital find project with max profit
                while (!minCapitalHeap.isEmpty() && minCapitalHeap.peek().capital <= currentCap) {
                    maxProfitHeap.offer(minCapitalHeap.poll());
                }
                // if we have any project in which we can invest
                if (!maxProfitHeap.isEmpty()) {
                    final PrCapAndPro prjSelected = maxProfitHeap.poll();
                    currentCap = currentCap + prjSelected.profits;
                    System.out.println(prjSelected + " - current running capital: " + currentCap);
                } else {
                    break;
                }
            }
            return currentCap;
        }
        public static void main(String[] args) {
            MaximizeCapital maximizeCapital = new MaximizeCapital();
            int result = maximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
            System.out.println("Maximum capital: " + result);
            result = maximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
            System.out.println("Maximum capital: " + result);
        }
    }
    /*Next Interval*/
    static class NextInterval {
        static class Interval {
            int start, end, index;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "Interval{" +
                        "start=" + start +
                        ", end=" + end +
                        ", index=" + index +
                        '}';
            }
        }
        public int[] findNextInterval(Interval[] intervals) {
            assert intervals != null && intervals.length > 2;
            int[] result = new int[intervals.length];
            PriorityQueue<Interval> maxStartHeap = new PriorityQueue<>(Comparator.comparingInt((Interval intv) -> intv.start).reversed());
            PriorityQueue<Interval> maxEndHeap = new PriorityQueue<>(Comparator.comparingInt((Interval intv) -> intv.end).reversed());
            for (int idx = 0; idx < intervals.length; idx++) {
                final Interval interval = intervals[idx];
                interval.index = idx;
                maxStartHeap.offer(interval);
                maxEndHeap.offer(interval);
            }
            while(!maxEndHeap.isEmpty()) {
                Interval currIntrv = maxEndHeap.poll();
                result[currIntrv.index] = -1; // set to default -1
                // now try to find its next interval where start >= currIntrv.end
                Interval nextInterval  = null;
                while(!maxStartHeap.isEmpty() && maxStartHeap.peek().start >= currIntrv.end) {
                    nextInterval = maxStartHeap.poll(); // run in loop because there can be more then 1 interval where start >= end and we want the closest one.
                }
                if(nextInterval != null) {
                    result[currIntrv.index] = nextInterval.index;
                    maxStartHeap.offer(nextInterval); // put it back.. it can be next interval of some other interval
                }
            }
            return result;
        }
        public static void main(String[] args) {
            NextInterval nextInterval = new NextInterval();
            Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
            int[] result = nextInterval.findNextInterval(intervals);
            System.out.print("Next interval indices are: ");
            for (int index : result)
                System.out.print(index + " ");
            System.out.println();

            intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
            result = nextInterval.findNextInterval(intervals);
            System.out.print("Next interval indices are: ");
            for (int index : result)
                System.out.print(index + " ");
        }
    }

}
