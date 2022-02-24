package com.lrn.leetcode.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Q187RepeatedDNASequences {
    /*
    * pd: https://leetcode.com/problems/repeated-dna-sequences/
    * assm: 1 < str len < 1000, only contain a c g t, best time sol
    * appr:
    * test cases:
    *
    * */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if(s.isEmpty() || s.length() < 10) return result;
        HashMap<String, Integer> seqmap = new HashMap<>();
        for(int we=10; we <= s.length(); we++) {
            String sub = s.substring(we-10,we);
            int freq = seqmap.getOrDefault(sub,0);
            if(freq == 1) {
                result.add(sub);
            }
            seqmap.put(sub, freq+1);

        }
        return result;
    }

}
