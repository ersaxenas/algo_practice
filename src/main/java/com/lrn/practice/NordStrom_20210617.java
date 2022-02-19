package com.lrn.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NordStrom_20210617 {

    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */


    public static List<String> findFriends(Map<String, List<String>> friends, String friendName) {
        Map<String, Set<String>> friendsMap = new HashMap<>();
        for (String name : friends.keySet()) {
            friendsMap.put(name, friendsMap.getOrDefault(name, new HashSet<>()));
            for (String friend : friends.get(name)) {
                friendsMap.put(friend, friendsMap.getOrDefault(friend, new HashSet<>()));
                friendsMap.get(name).add(friend);
                friendsMap.get(friend).add(name);
            }
        }
        List<String> result = new ArrayList<>(friendsMap.get(friendName));
        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> friends = new HashMap<String, List<String>>();
        friends.put("Jim", List.of("Estella", "Mahendran", "Han"));
        friends.put("Zihao", List.of("Natalie", "Han", "Mahendran"));
        friends.put("Prasad", List.of("Vikas", "Mahendran", "Jim"));
        friends.put("Seth", List.of("Han", "Natalie", "Estella", "Vikas", "Jim", "Mahendran", "Zihao"));
        Collection<String> friendship = findFriends(friends, "Jim");
        for (String friendName : friendship) {
            System.out.println(friendName);
        }
    }


}
