package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Q146LRUCache {
    /*
    * pd: Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
The cache is initialized with a positive capacity.
Follow up:
Could you do both operations in O(1) time complexity?
Example:
* LRUCache cache = new LRUCache( 2  capacity  );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
    * assm: reject null inserts, cache size 10000, key int and value int ,best time sol.
    * appr: Store entries in the hashmap
    *       keep keys in a linked list as well when ever an entry is accessed move to the tail of the list
    *       when cache is full then take entries from the linked list head and remove from list and map both
    *       on cache hit return value and on cache miss return -1
    * Test cases:
    *
    * */

    static class LRUCache {

        private HashMap<Integer, Integer> cache;
        private LinkedList<Integer> queue;
        private int size;

        public LRUCache(int capacity) {
            this.size = capacity;
            queue = new LinkedList<>();
            cache = new HashMap<>(size);
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                moveToLast(key);
                return;
            }
            if (isFull()) {
                evict();
            }
            cache.put(key, value);
            queue.add(key);
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                moveToLast(key);
                return cache.get(key);
            }
            return -1;
        }

        private void moveToLast(int key) {
            queue.removeIf(val -> val == key);
            queue.addLast(key);
        }

        private boolean isFull() {
            return queue.size() == size;
        }

        private void evict() {
            if (!queue.isEmpty()) {
                System.out.println("evict: "+ queue.peek());
                cache.remove(queue.poll());
            }
        }

    }

    static class DNode {
        int key, val;
        DNode pre, next;
        public DNode(int key, int val) {
         this.key = key;
         this.val = val;
        }
    }

    static class LRUCache2 {

        DNode head, tail;
        HashMap<Integer, DNode> cache;
        int size;
        public LRUCache2(int capacity) {
            this.size = capacity;
            this.cache = new HashMap<>(capacity);
            head = new DNode(-1,-1);
            tail = new DNode(-1,-1);
            head.next = tail;
            tail.pre = head;
        }
        private void addToHead(DNode node) {
            node.pre = head;
            node.next = head.next;

            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(DNode node) {
            DNode pre = node.pre;
            DNode next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        private DNode removeFromTail() {
            DNode node = tail.pre;
            removeNode(tail.pre);
            return node;
        }

        public int get(int key) {
              DNode node = cache.get(key);
              if(node == null) return -1;
              removeNode(node);
              addToHead(node);
              return node.val;
        }

        public void put(int key, int value) {
              DNode node = cache.get(key);
              if(node == null) {
                  node = new DNode(key, value);
                  cache.put(key, node);
                  addToHead(node);
                  if(cache.size() > size) evict();
                  return;
              }
              node.val = value;
              removeNode(node);
              addToHead(node);
        }

        public void evict() {
            DNode node = removeFromTail();
            cache.remove(node.key);
        }

    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(2);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // returns 1
//        cache.put(3, 3);    // evicts key 2
//        System.out.println(cache.get(2));
//        cache.put(4, 4);    // evicts key 1
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

//        cache.put(2, 1);
//        cache.put(1, 1);
//        System.out.println(cache.get(1));
//        cache.put(3, 3);
//        System.out.println(cache.get(2));
//        cache.put(4, 1); // evict 1
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(2));//        cache.put(2, 1);

        System.out.println(cache.get(2));
        cache.put(2, 1);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        System.out.println(cache.get(2));
        cache.put(4, 1); // evict 1
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
    }

}



