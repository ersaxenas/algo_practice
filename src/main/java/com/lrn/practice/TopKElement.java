package com.lrn.practice;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKElement {

    public String reorganizeString(String str, int k) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char ch : str.toCharArray()) {
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }
        ArrayDeque<Map.Entry<Character, Integer>> queue = new ArrayDeque<>();
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            maxHeap.add(entry);
        }
        StringBuilder sbr = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            final Map.Entry<Character, Integer> entry = maxHeap.poll();
            sbr.append(entry.getKey());
            entry.setValue(entry.getValue() - 1);
            queue.add(entry);
            if (queue.size() >= k) {
                Map.Entry<Character, Integer> prvEntry = queue.poll();
                if (prvEntry.getValue() > 0) {
                    maxHeap.add(prvEntry);
                }
            }
        }
        System.out.println("sbr = " + sbr);
        return sbr.length() == str.length() ? sbr.toString() : "";
    }

    public static void main(String[] args) {
        TopKElement sol = new TopKElement();
        System.out.println(sol.reorganizeString("mmpp", 2));
        System.out.println(sol.reorganizeString("Programming", 3));
        System.out.println(sol.reorganizeString("aab", 2));
        System.out.println(sol.reorganizeString("aappa", 3));

    }

}
