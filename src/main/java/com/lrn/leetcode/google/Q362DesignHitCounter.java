package com.lrn.leetcode.google;

public class Q362DesignHitCounter {
    /*
    * pd: Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
It is possible that several hits arrive roughly at the same time.
Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
    * assm: best time sol, max hit in a sec < int.max
    *  appr:
    * test cases:
    * */

    int[] hitcount = new int[300];
    int[] hitTimeStamp = new int[300];

    public void hit(int timestamp) {
        int idx = timestamp % 300;
        if (hitTimeStamp[idx] == timestamp) { // for this timestamp already have few hits
            hitcount[idx]++;
        } else { // new timestamp
            // update the timestamp at idx and reset the counter
            hitTimeStamp[idx] = timestamp;
            hitcount[idx] = 1;
        }
    }

    public int getHits(int timestamp) {
        // scan timestamp array for timestamp in last 5 mins.
        int count = 0;
        for (int idx = 0; idx < 300; idx++) {
            if (timestamp - hitTimeStamp[idx] < 300) { // if timestamp is in 5 mins
                count = count + hitcount[idx];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Q362DesignHitCounter counter = new Q362DesignHitCounter();
        // hit at timestamp 1.
        counter.hit(1);

// hit at timestamp 2.
        counter.hit(2);

// hit at timestamp 3.
        counter.hit(3);

// get hits at timestamp 4, should return 3.
        System.out.println(counter.getHits(4)==3 ? "pass" : "fail");

// hit at timestamp 300.
        counter.hit(300);

// get hits at timestamp 300, should return 4.
        System.out.println(counter.getHits(300)==4 ? "pass" : "fail");

// get hits at timestamp 301, should return 3.
        System.out.println(counter.getHits(301) == 3 ? "pass" : "fail");
    }


}
