package com.lrn.leetcode.weeklycomp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WContest194 {
    /*
    * 1488. Avoid Flood in The City
    * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.

Given an integer array rains where:

rains[i] > 0 means there will be rains over the rains[i] lake.
rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
Return an array ans where:

ans.length == rains.length
ans[i] == -1 if rains[i] > 0.
ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.

Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.
    * */
    static class FloodAvoid {
//        public int[] avoidFlood2(int[] rains) {
//            // the previous appeared idx of rains[i]
//            Map<Integer, Integer> lakesfull = new HashMap<>(); // keep lakesfull and its index
//            TreeSet<Integer> zeros = new TreeSet<>();// keep the location of zeros ==
//            int[] res = new int[rains.length];
//            for (int i = 0; i < rains.length; i++) {
//                if (rains[i] == 0) {
//                    zeros.add(i);
//                    res[i] = 1;
//                } else {
//                    if (lakesfull.containsKey(rains[i])) {// lake is already full
//                        // find the location of zero that can be used to empty rains[i]
//                        Integer next = zeros.ceiling(lakesfull.get(rains[i])); // find if there is a day when this lake can be dried out.
//                        if (next == null) return new int[0]; // no way to dry this lake
//                        res[next] = rains[i]; // dry this lake on 'next' day
//                        zeros.remove(next); // remove this day from collection
//                    }
//                    res[i] = -1;
//                    lakesfull.put(rains[i], i);
//                }
//            }
//            return res;
//        }
        /*
         * Approach: we have two type of days 1. rainy day - on which lakes are filled 2. dry day on which a lake can be dried
         * keep track of lakes which are full and index of the rain day
         *   keep track of dry '0' days and its index
         *   When encounter a rainy day on which lake is already full just try to find dry '0' day which has index higher then rainy day,
         *   we use this day to empty lake if we dont find such a day then there is no way to avoid flood.
         * */

        public int[] avoidFlood(int[] rains) {
            int[] res = new int[rains.length];
            /*map stores full lake and its index in the rains array.*/
            HashMap<Integer, Integer> lakesFull = new HashMap<>();
            /*TreeSet hold index of dry days*/
            TreeSet<Integer> dryDayIndexSet = new TreeSet<>();
            for (int idx = 0; idx < rains.length; idx++) {
                if (rains[idx] != 0) { // rain day
                    if (lakesFull.containsKey(rains[idx])) { // lake is already full
                        // try to empty this lake. Find when last time it rained on this lake
                        Integer rainDayIndex = lakesFull.get(rains[idx]);
                        // do we have any dry day after this index
                        Integer nextDryDay = dryDayIndexSet.ceiling(rainDayIndex);
                        if (nextDryDay == null) {
                            // no such day found so flood cannot be avoided
                            return new int[]{};
                        }
                        res[nextDryDay] = rains[idx]; // dry this lake on nextDryDay
                        dryDayIndexSet.remove(nextDryDay); // remove dryday from set as we have used it to dry lake
                    }
                    // save index of rain day and its index
                    res[idx] = -1;
                    lakesFull.put(rains[idx], idx);
                } else {
                    // dry day
                    res[idx] = 1;
                    dryDayIndexSet.add(idx);
                }
            }
            return res;
        }

        public static void main(String[] args) {
            FloodAvoid floodAvoid = new FloodAvoid();
            printArray(floodAvoid.avoidFlood(new int[]{1, 2, 3, 4}));
            printArray(floodAvoid.avoidFlood(new int[]{1, 2, 0, 0, 2, 1}));
            printArray(floodAvoid.avoidFlood(new int[]{1, 2, 0, 1, 2}));
            printArray(floodAvoid.avoidFlood(new int[]{69, 0, 0, 0, 69}));
            printArray(floodAvoid.avoidFlood(new int[]{10, 20, 20}));
            printArray(floodAvoid.avoidFlood(new int[]{3, 5, 4, 0, 1, 0, 1, 5, 2, 8, 9}));
        }
    }

    public static void printArray(final int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println("");
    }
    public static void printArray(final String[] array) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(" | ");
            System.out.print(array[i] + " | ");
        }
        System.out.println("");
    }

    /*Given an array of strings names of size n. You will create n folders in your file system such that, at the ith minute, you will create a folder with the name names[i].
Since two files cannot have the same name, if you enter a folder name which is previously used, the system will have a suffix addition to its name in the form of (k), where, k is the smallest positive integer such that the obtained name remains unique.
Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.
Input: names = ["pes","fifa","gta","pes(2019)"]
Output: ["pes","fifa","gta","pes(2019)"]
Input: names = ["gta","gta(1)","gta","avalon"]
Output: ["gta","gta(1)","gta(2)","avalon"]
Input: names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
Output: ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
Input: names = ["wano","wano","wano","wano"]
Output: ["wano","wano(1)","wano(2)","wano(3)"]
Input: names = ["kaido","kaido(1)","kaido","kaido(1)"]
Output: ["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
 */
    static class GenFolderName {
        public String[] getFolderNames(String[] names) {
            String[] res = new String[names.length];
            HashMap<String, Integer> nameMap = new HashMap<>();
            for (int idx = 0; idx < res.length; idx++) {
                int val = 1;
                String nm = names[idx];
                if (nameMap.containsKey(nm)) {
                    val = nameMap.get(nm);
                    nm = String.format("%s(%d)", names[idx], val++);
                    while (nameMap.containsKey(nm)) {
                        nm = String.format("%s(%d)", names[idx], val++);
                    }
                    nameMap.put(names[idx], val);
                }
                res[idx] = nm;
                nameMap.put(nm, 1);
            }
            return res;
        }
        public static void main(String[] args) {
            GenFolderName genFolderName = new GenFolderName();
            printArray(genFolderName.getFolderNames(new String[]{"pes","fifa","gta","pes(2019)"}));
            printArray(genFolderName.getFolderNames(new String[]{"gta","gta(1)","gta","avalon"}));
            printArray(genFolderName.getFolderNames(new String[]{"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"}));
            printArray(genFolderName.getFolderNames(new String[]{"wano","wano","wano","wano"}));
            printArray(genFolderName.getFolderNames(new String[]{"kaido","kaido(1)","kaido(2)","kaido(1)(1)"}));
            printArray(genFolderName.getFolderNames(new String[]{"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"}));
        }
    }


}
