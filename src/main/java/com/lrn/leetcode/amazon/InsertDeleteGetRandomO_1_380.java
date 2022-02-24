package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandomO_1_380 {

	static class RandomizedSet {

		Map<Integer, Integer> keyLocation;
		List<Integer> list;
		Random random = new Random();

		/** Initialize your data structure here. */
		public RandomizedSet() {
			keyLocation = new HashMap<>();
			list = new ArrayList<>();
		}

		/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
		public boolean insert(final int val) {
			if(keyLocation.containsKey(val)) {
				return false;
			}
			list.add(val);
			keyLocation.put(val, list.size()-1);
			return true;
		}

		/** Removes a value from the set. Returns true if the set contained the specified element. */
		public boolean remove(final int val) {
			if(keyLocation.containsKey(val)) {
				int loc = keyLocation.get(val);
				if(loc != (list.size()-1)) {
					int valLast = list.get(list.size()-1);
					list.set(loc, valLast);
					keyLocation.put(valLast, loc);
				}
				keyLocation.remove(val);
				list.remove(list.size()-1);
				return true;
			}
			return false;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			int randomInt = random.ints(0, list.size()).findFirst().getAsInt();
			return list.get(randomInt);
		}
	}

	public static void main(final String[] args) {
		// Init an empty set.
		RandomizedSet randomSet = new RandomizedSet();

		// Inserts 1 to the set. Returns true as 1 was inserted successfully.
		System.out.println(randomSet.insert(1));

		// Returns false as 2 does not exist in the set.
		System.out.println(randomSet.remove(2));

		// Inserts 2 to the set, returns true. Set now contains [1,2].
		System.out.println(randomSet.insert(2));

		// getRandom should return either 1 or 2 randomly.
		System.out.println(randomSet.getRandom());

		// Removes 1 from the set, returns true. Set now contains [2].
		System.out.println(randomSet.remove(1));

		// 2 was already in the set, so return false.
		System.out.println(randomSet.insert(2));

		// Since 2 is the only number in the set, getRandom always return 2.
		System.out.println(randomSet.getRandom());
	}

}
