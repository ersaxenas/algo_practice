package com.lrn.educative.gci.kln;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class WorkKlnUtils {
    /*Given an unsorted array of numbers, find the ‘K’ largest numbers in it.*/
    static class KLargestNumbers {
        public List<Integer> find(int[] arr, int K) {
            assert arr != null && arr.length > 1;
            List<Integer> largeNums = new ArrayList<>();
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int num : arr) {
                if (minHeap.size() < K) {
                    minHeap.offer(num);
                } else if (minHeap.peek() < num) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            largeNums.addAll(minHeap);
            return largeNums;
        }

        public static void main(String[] args) {
            KLargestNumbers kLargestNumbers = new KLargestNumbers();
            List<Integer> result = kLargestNumbers.find(new int[]{3, 1, 5, 12, 2, 11}, 3);
            System.out.println("Here are the top K numbers: " + result);

            result = kLargestNumbers.find(new int[]{5, 12, 11, -1, 12}, 3);
            System.out.println("Here are the top K numbers: " + result);
        }
    }

    /*Given an unsorted array of numbers, find Kth smallest number in it.*/
    static class KthSmallesNumber {
        public Integer find(int[] arr, int K) {
            assert arr != null && arr.length > 1;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            for (int num : arr) {
                if (maxHeap.size() < K) {
                    maxHeap.offer(num);
                } else if (maxHeap.peek() > num) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }
            return maxHeap.poll();
        }

        public static void main(String[] args) {
            KthSmallesNumber kthSmallesNumber = new KthSmallesNumber();
            int result = kthSmallesNumber.find(new int[]{1, 5, 12, 2, 11, 5}, 3);
            System.out.println("Kth smallest number is: " + result);

            // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
            result = kthSmallesNumber.find(new int[]{1, 5, 12, 2, 11, 5}, 4);
            System.out.println("Kth smallest number is: " + result);

            result = kthSmallesNumber.find(new int[]{5, 12, 11, -1, 12}, 3);
            System.out.println("Kth smallest number is: " + result);
        }
    }
    /*Given an array of points in the a 2D2D plane, find ‘K’ closest points to the origin.
     * Euclidean distance : p(x,y) :- square root of ((x*x)+(y*y))
     */

    static class KDistanceOfPointFromOrigin {
        class Point {
            int x;
            int y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int distFromOrigin() {
                // ignoring sqrt
                return (x * x) + (y * y);
            }
        }

        public List<Point> findPoints(Point[] points, int K) {
            assert points != null && points.length > 1;
            PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(Comparator.comparingInt((Point point) -> point.distFromOrigin()).reversed());
            for (Point point : points) {
                Integer dist = point.distFromOrigin();
                if (maxHeap.size() < K) {
                    maxHeap.offer(point);
                } else if (maxHeap.peek().distFromOrigin() > dist) {
                    maxHeap.poll();
                    maxHeap.offer(point);
                }
            }
            return new ArrayList<>(maxHeap);
        }

        public static void main(String[] args) {
            KDistanceOfPointFromOrigin kDistanceOfPointFromOrigin = new KDistanceOfPointFromOrigin();
            Point[] points = new Point[]{kDistanceOfPointFromOrigin.new Point(1, 3), kDistanceOfPointFromOrigin.new Point(3, 4), kDistanceOfPointFromOrigin.new Point(2, -1)};
            List<Point> result = kDistanceOfPointFromOrigin.findPoints(points, 2);
            System.out.print("Here are the k points closest the origin: ");
            for (Point p : result)
                System.out.print("[" + p.x + " , " + p.y + "] ");
        }
    }

    static class RopesConnector {
        public Integer connect(int[] lengthOfRopes) {
            assert lengthOfRopes != null && lengthOfRopes.length > 1;
            // assumptions : arrays will not have null values
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            Integer sumTotal = 0;
            for (int lengthOfRope : lengthOfRopes) {
                minHeap.add(lengthOfRope);
            }
            while (minHeap.size() > 1) {
                int sum = minHeap.poll() + minHeap.poll();
                minHeap.add(sum);
                sumTotal = sumTotal + sum;
            }
            return sumTotal;
        }

        public static void main(String[] args) {
            RopesConnector ropesConnector = new RopesConnector();
            int result = ropesConnector.connect(new int[]{1, 3, 11, 5});
            System.out.println("Minimum cost to connect ropes: " + result);
            result = ropesConnector.connect(new int[]{3, 4, 5, 6});
            System.out.println("Minimum cost to connect ropes: " + result);
            result = ropesConnector.connect(new int[]{1, 3, 11, 5, 2});
            System.out.println("Minimum cost to connect ropes: " + result);
        }
    }

    /*Given a string, sort it based on the decreasing frequency of its characters.*/
    static class CharFrequencySort {
        public String sort(String text) {
            Map<Character, StringBuilder> charFeqMap = new HashMap<>();
            for (Character ch : text.toCharArray()) {
                charFeqMap.put(ch, charFeqMap.getOrDefault(ch, new StringBuilder()).append(ch));
            }
            PriorityQueue<Map.Entry<Character, StringBuilder>> maxHeap = new PriorityQueue<>(Comparator.comparingInt((Map.Entry<Character, StringBuilder> entry) -> entry.getValue().length()).reversed());
            charFeqMap.entrySet().forEach((Map.Entry<Character, StringBuilder> entry) -> maxHeap.add(entry));
            StringBuilder result = new StringBuilder();
            while (!maxHeap.isEmpty()) {
                result.append(maxHeap.poll().getValue());
            }
            return result.toString();
        }

        public static void main(String[] args) {
            CharFrequencySort charFrequencySort = new CharFrequencySort();
            String result = charFrequencySort.sort("Programming");
            System.out.println("Here is the given string after sorting characters by frequency: " + result);

            result = charFrequencySort.sort("abcbab");
            System.out.println("Here is the given string after sorting characters by frequency: " + result);
        }
    }

    /*Kth Largest Number in a Stream*/
    static class KLargestNumInStream {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int k;

        public KLargestNumInStream(int[] arr, int k) {
            this.k = k;
            for (int num : arr) {
                add(num);
            }
        }

        public int add(int num) {
            // add the new number in the min heap
            minHeap.add(num);

            // if heap has more than 'k' numbers, remove one number
            if (minHeap.size() > this.k)
                minHeap.poll();

            // return the 'Kth largest number

            return minHeap.peek();
        }

        public static void main(String[] args) {
            int[] input = new int[]{3, 1, 5, 12, 2, 11};
            KLargestNumInStream kLargestNumInStream = new KLargestNumInStream(input, 4);
            System.out.println("4th largest number is: " + kLargestNumInStream.add(6));
            System.out.println("4th largest number is: " + kLargestNumInStream.add(13));
            System.out.println("4th largest number is: " + kLargestNumInStream.add(4));
        }
    }

    /*Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array. Return the numbers in the sorted order.*/
    static class KClosestNumber {
        class Entry {
            int key;
            int value;

            public Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public List<Integer> find1(int[] arr, int K, int X) {
            assert arr != null && arr.length > 1;
            List<Integer> result = new ArrayList<>();
            PriorityQueue<Entry> maxHeap = new PriorityQueue<>(Comparator.comparingInt((Entry entry) -> entry.key).reversed());
            for (int num : arr) {
                int key = X - num;
                if (key < 0) {
                    key = key * -1;
                }
                Entry entry = new Entry(key, num);
                if (maxHeap.size() < K) {
                    maxHeap.add(entry);
                } else if (key < maxHeap.peek().key) {
                    maxHeap.poll();
                    maxHeap.add(entry);
                }
            }
            maxHeap.forEach(entry -> result.add(entry.value));
            return result;
        }

        public List<Integer> find2(int[] arr, int K, int X) {
            List<Integer> result = new ArrayList<>();
            Integer elemIndex = binarySearch(arr, X);
            PriorityQueue<Entry> minHeap = new PriorityQueue<>(Comparator.comparingInt((Entry entry) -> entry.key));
            if (elemIndex != null) {
                int low = Math.max(0, elemIndex - K);
                int high = Math.min(arr.length - 1, elemIndex + K);
                for (int idx = low; idx <= high; idx++) {
                    minHeap.add(new Entry(Math.abs(arr[idx] - X), arr[idx]));
                }
                while (result.size() < K) {
                    result.add(minHeap.poll().value);
                }
                Collections.sort(result);
            }
            return result;
        }

        public Integer binarySearch(int[] arr, int elem) {
            int start = 0, end = arr.length - 1, mid = 0;
            while (start <= end) {
                mid = start + ((end - start) / 2);
                int midElem = arr[mid];
                if (elem < midElem) {
                    end = mid - 1;
                } else if (elem > midElem) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            /*if element not found*/
            if (start > 0) {
                return start - 1;
            }
            return start;
        }

        public static void main(String[] args) {
            KClosestNumber kClosestNumber = new KClosestNumber();
            List<Integer> result = kClosestNumber.find2(new int[]{5, 6, 7, 8, 9}, 3, 7);
            System.out.println("'K' closest numbers to 'X' are: " + result);

            result = kClosestNumber.find2(new int[]{2, 4, 5, 6, 9}, 3, 6);
            System.out.println("'K' closest numbers to 'X' are: " + result);

            result = kClosestNumber.find2(new int[]{2, 4, 5, 6, 9}, 3, 10);
            System.out.println("'K' closest numbers to 'X' are: " + result);
        }
    }

    /*Given an array of numbers and a number ‘K’, we need to remove ‘K’ numbers from the array such that we are left with maximum distinct numbers.*/
    static class MaximumKDistinctNum {
        public Integer findDistinct(int[] arr, int K) {
            int elemCanBeRemoved = K;
            Integer distinctCount = 0;
            if (arr.length < elemCanBeRemoved) {
                return distinctCount;
            }
            // prepare freq map
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : arr) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
            // min heap for element with freq > 1
            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt((Map.Entry<Integer, Integer> entry) -> entry.getValue()));
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                if (entry.getValue() > 1) {
                    minHeap.add(entry);
                } else {
                    distinctCount++;
                }
            }
            while (!minHeap.isEmpty() && elemCanBeRemoved > 0) {
                elemCanBeRemoved = elemCanBeRemoved - (minHeap.poll().getValue() - 1);
                if (elemCanBeRemoved > 0) {
                    distinctCount++;
                }
            }
            if (elemCanBeRemoved > 0) {
                distinctCount = distinctCount - elemCanBeRemoved;
            }
            return distinctCount;
        }

        public static void main(String[] args) {
            MaximumKDistinctNum maximumKDistinctNum = new MaximumKDistinctNum();
            int result = maximumKDistinctNum.findDistinct(new int[]{7, 3, 5, 8, 5, 3, 3}, 2);
            System.out.println("Maximum distinct numbers after removing K numbers: " + result);

            result = maximumKDistinctNum.findDistinct(new int[]{3, 5, 12, 11, 12}, 3);
            System.out.println("Maximum distinct numbers after removing K numbers: " + result);

            result = maximumKDistinctNum.findDistinct(new int[]{1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5}, 2);
            System.out.println("Maximum distinct numbers after removing K numbers: " + result);
        }
    }

    /*Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.*/
    static class SumElements {
        public Integer sum(int[] arr, int k1, int k2) {
            assert arr != null && arr.length >= k2;
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int num : arr) {
                minHeap.add(num);
            }
            int sum = 0;
            for (int idx = 0; idx < k2 - 1; idx++) {
                int elem = minHeap.poll();
                if (idx > k1 - 1) {
                    sum = sum + elem;
                }
            }
            return sum;
        }

        public static void main(String[] args) {
            SumElements sumElements = new SumElements();
            int result = sumElements.sum(new int[]{1, 3, 12, 5, 15, 11}, 3, 6); // 1,3,5,11,12,15
            System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);

            result = sumElements.sum(new int[]{3, 5, 8, 7}, 1, 4);
            System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
        }
    }

    /*Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.*/
    static class RearrangedString {
        public String rearrange(String text) {
            assert text != null && text.length() > 0;
            //freq of chars
            Map<Character, Integer> charFreqMap = new HashMap<>();
            for (Character ch : text.toCharArray()) {
                charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);
            }
            PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
            charFreqMap.entrySet().forEach(entry -> maxHeap.add(entry));
            StringBuilder sbr = new StringBuilder();
            Map.Entry<Character, Integer> previousEntry = null;
            while (!maxHeap.isEmpty()) {
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if (previousEntry != null && previousEntry.getValue() > 0) {
                    maxHeap.add(previousEntry);
                }
                sbr.append(currentEntry.getKey());
                currentEntry.setValue(currentEntry.getValue() - 1);
                previousEntry = currentEntry;
            }
            String result = sbr.toString();
            return (result.length() == text.length()) ? result : "";
        }

        public static void main(String[] args) {
            RearrangedString rearrangedString = new RearrangedString();
            System.out.println("Rearranged string: " + rearrangedString.rearrange("aappp"));
            System.out.println("Rearranged string: " + rearrangedString.rearrange("Programming"));
            System.out.println("Rearranged string: " + rearrangedString.rearrange("aapa"));
        }
    }

    /*Given a string and a number ‘K’, find if the string can be rearranged such that the same characters are at least ‘K’ distance apart from each other.*/
    static class RearrangeStringKDistanceApart {
        public String reorganizeString(String text, int K) {
            assert text != null && text.trim().length() > 2;
            // get freq map
            Map<Character, Integer> freqMap = new HashMap<>();
            for (Character ch : text.toCharArray()) {
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            }
            if (freqMap.size() < K) {
                return "";
            }
            Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
            PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
            maxHeap.addAll(freqMap.entrySet());
            StringBuilder sbr = new StringBuilder();
            while (!maxHeap.isEmpty()) {
                Map.Entry<Character, Integer> characterIntegerEntry = maxHeap.poll();
                sbr.append(characterIntegerEntry.getKey());
                characterIntegerEntry.setValue(characterIntegerEntry.getValue() - 1);
                queue.offer(characterIntegerEntry);
                if (queue.size() == K) {
                    Map.Entry<Character, Integer> entry = queue.poll();
                    if (entry.getValue() > 0) {
                        maxHeap.add(entry);
                    }
                }
            }
            String result = sbr.toString();
            return (result.length() == text.length()) ? result : "";
        }

        public static void main(String[] args) {
            RearrangeStringKDistanceApart rearrangeStringKDistanceApart = new RearrangeStringKDistanceApart();
            System.out.println("Reorganized string: " + rearrangeStringKDistanceApart.reorganizeString("mmpp", 2));
            System.out.println("Reorganized string: " + rearrangeStringKDistanceApart.reorganizeString("Programming", 3));
            System.out.println("Reorganized string: " + rearrangeStringKDistanceApart.reorganizeString("aab", 2));
            System.out.println("Reorganized string: " + rearrangeStringKDistanceApart.reorganizeString("aappa", 3));
        }
    }

    /*You are given a list of tasks that need to be run, in any order, on a server.
    Each task will take one CPU interval to execute but once a task has finished, it has a cooling period during which it can’t be run again.
    If the cooling period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals that the server needs to finish all tasks.
    If at any time the server can’t execute any task then it must stay idle.*/
    static class TaskScheduler {
        public int schedule(char[] tasks, int K) {
            int interval = 0;
            Map<Character, Integer> freqMap = new HashMap<>();
            for (char ch : tasks) {
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            }
            PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
            maxHeap.addAll(freqMap.entrySet());
            while (!maxHeap.isEmpty()) {
                List<Map.Entry<Character, Integer>> waitList = new LinkedList<>();
                // no. of iterations
                int n = K + 1;
                for (; n > 0 && !maxHeap.isEmpty(); n--) {
                    interval++;
                    Map.Entry<Character, Integer> job = maxHeap.poll();
                    System.out.println("Running task : " + job.getKey());
                    if (job.getValue() > 1) {
                        job.setValue(job.getValue() - 1);
                        waitList.add(job);
                    }
                }
                if (!waitList.isEmpty()) {
                    maxHeap.addAll(waitList);
                }
                if (n > 0 && !maxHeap.isEmpty()) {
                    interval = interval + n;
                    System.out.println(n + " empty cycle");
                }
            }
            return interval;
        }

        public static void main(String[] args) {
            TaskScheduler taskScheduler = new TaskScheduler();
            char[] tasks = new char[]{'a', 'a', 'a', 'b', 'c', 'c'};
            System.out.println("Minimum intervals needed to execute all tasks: " + taskScheduler.schedule(tasks, 2));

            tasks = new char[]{'a', 'b', 'a'};
            System.out.println("Minimum intervals needed to execute all tasks: " + taskScheduler.schedule(tasks, 3));
        }
    }

    /*Design a class that simulates a Stack data structure, implementing the following two operations:
push(int num): Pushes the number ‘num’ on the stack.
pop(): Returns the most frequent number in the stack. If there is a tie, return the number which was pushed later.*/
    static class StackSimulator {
        class Element {
            Integer num;
            Integer frequency;
            Integer sequence;

            public Element(Integer num, Integer frequency, Integer sequence) {
                this.num = num;
                this.frequency = frequency;
                this.sequence = sequence;
            }
        }
        Integer seqNo =0;

        Map<Integer, Integer> freqMap = new HashMap<>();
        PriorityQueue<Element> maxHeap = new PriorityQueue<>(
                (Element e1, Element e2) -> {
                    int result = e2.frequency.compareTo(e1.frequency);
                    if (result == 0) {
                        result = e2.sequence.compareTo(e1.sequence);
                    }
                    return result;
                }
        );

        public void push(Integer num) {
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
            maxHeap.add(new Element(num, freqMap.get(num), ++seqNo));
        }

        public Integer pop() {
            final Element elem = maxHeap.poll();
            if(elem.frequency > 1) {
                freqMap.put(elem.num, freqMap.get(elem.num) -1);
            } else {
                freqMap.remove(elem.num);
            }
            return elem.num;
        }

        public static void main(String[] args) {
            StackSimulator frequencyStack = new StackSimulator();
            frequencyStack.push(1);
            frequencyStack.push(2);
            frequencyStack.push(3);
            frequencyStack.push(2);
            frequencyStack.push(1);
            frequencyStack.push(2);
            frequencyStack.push(5);
            System.out.println(frequencyStack.pop());
            System.out.println(frequencyStack.pop());
            System.out.println(frequencyStack.pop());
        }
    }

}
