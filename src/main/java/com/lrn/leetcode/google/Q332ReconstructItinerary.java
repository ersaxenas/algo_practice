package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class Q332ReconstructItinerary {
    /*
    * pd: Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.
    * assm: total tickets < 1000, best time sol
    * appr:
    * test cases:
    * */

    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> result = new LinkedList<>();
        if (tickets.size() == 0) {
            return result;
        }
        Map<String, PriorityQueue<String>> ticketMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            PriorityQueue<String> dest = ticketMap.getOrDefault(ticket.get(0), new PriorityQueue<>());
            dest.add(ticket.get(1));
            ticketMap.put(ticket.get(0), dest);
        }
        prepItinerary("JFK", ticketMap, result);
        return result;
    }

    public void prepItinerary(String start, Map<String, PriorityQueue<String>> ticketMap, LinkedList<String> result) {
        PriorityQueue<String> destSet = ticketMap.get(start);
        while (destSet != null && !destSet.isEmpty()) {
            prepItinerary(destSet.poll(), ticketMap, result);
        }
        result.addFirst(start);
    }

    public static void main(String[] args) {
        Q332ReconstructItinerary sol = new Q332ReconstructItinerary();
        List<List<String>> itinerary = new ArrayList<>();
        itinerary.add(Arrays.asList("MUC", "LHR"));
        itinerary.add(Arrays.asList("JFK", "MUC"));
        itinerary.add(Arrays.asList("SFO", "SJC"));
        itinerary.add(Arrays.asList("LHR", "SFO"));
        LsUtil.printList(sol.findItinerary(itinerary));
        itinerary.clear();
        itinerary.add(Arrays.asList("JFK", "SFO"));
        itinerary.add(Arrays.asList("JFK", "ATL"));
        itinerary.add(Arrays.asList("SFO", "ATL"));
        itinerary.add(Arrays.asList("ATL", "JFK"));
        itinerary.add(Arrays.asList("ATL", "SFO"));
        LsUtil.printList(sol.findItinerary(itinerary));
        itinerary.clear();
        itinerary.add(Arrays.asList("JFK","KUL"));
        itinerary.add(Arrays.asList("JFK","NRT"));
        itinerary.add(Arrays.asList("NRT","JFK"));
        LsUtil.printList(sol.findItinerary(itinerary));
        itinerary.clear();
        itinerary.add(Arrays.asList("EZE","AXA"));
        itinerary.add(Arrays.asList("TIA","ANU"));
        itinerary.add(Arrays.asList("ANU","JFK"));
        itinerary.add(Arrays.asList("JFK","ANU"));
        itinerary.add(Arrays.asList("ANU","EZE"));
        itinerary.add(Arrays.asList("TIA","ANU"));
        itinerary.add(Arrays.asList("AXA","TIA"));
        itinerary.add(Arrays.asList("TIA","JFK"));
        itinerary.add(Arrays.asList("ANU","TIA"));
        itinerary.add(Arrays.asList("JFK","TIA"));
        LsUtil.printList(sol.findItinerary(itinerary));

    }

}
