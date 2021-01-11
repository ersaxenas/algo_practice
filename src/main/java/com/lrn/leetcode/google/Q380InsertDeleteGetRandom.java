package com.lrn.leetcode.google;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Q380InsertDeleteGetRandom {
    /*
    * pd: Implement the RandomizedSet class:
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
Follow up: Could you implement the functions of the class with each function works in average O(1) time?
    * assm: best time sol, no. of elem max < int.max
    * appr:
    * test cases:
    *
    * */

    HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
    LinkedList<Integer> list = new LinkedList<>();
    Random random = new Random();
    public boolean insert(int val) {
        if(valueIndexMap.containsKey(val)) return false;
        list.add(val);
        valueIndexMap.put(val, list.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if(!valueIndexMap.containsKey(val)) return false;
        final Integer index = valueIndexMap.get(val);
        if(index != list.size()-1) {
            final Integer last = list.getLast();
            list.set(index, last);
            valueIndexMap.put(last, index);
        }
        list.removeLast();
        valueIndexMap.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        Q380InsertDeleteGetRandom sol = new Q380InsertDeleteGetRandom();
        System.out.println(sol.remove(0));
        System.out.println(sol.remove(0));
        System.out.println(sol.insert(0));
        System.out.println(sol.getRandom());
        System.out.println(sol.remove(0));
        System.out.println(sol.insert(0));

    }



}
