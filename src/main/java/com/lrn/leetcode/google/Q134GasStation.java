package com.lrn.leetcode.google;

import java.util.PriorityQueue;

public class Q134GasStation {
    /*
    * pd: There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
Note:
If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
* Input:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
    * assm: non elem in arrays, array length < 100, best time sol
    * appr:
    * 1. if sum of gas is more than sum of cost, then there must be a solution. And the question guaranteed that the solution is unique(The first one I found is the right one).
    * 2. The tank should never be negative, so restart whenever there is a negative number.
    * 3. at any node of gas - cost is < 0 then dont start from that node.
    *
    * */

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int totalGas=0, totalCost=0, tank=0, start=0;
        for(int idx=0; idx<gas.length; idx++) {
            totalGas = totalGas + gas[idx];
            totalCost = totalCost + cost[idx];
            tank = tank + gas[idx] - cost[idx];
            if(tank < 0) {
                start = idx+1;
                tank =0;
            }
        }
        if(totalGas < totalCost) {
            return -1;
        } else {
            return start;
        }
    }



    class GStation {
        public int id;
        public int gas;
        public int nextNodeCost;
        GStation nextStation;

        public GStation(int id, int gas, int nextNodeCost) {
            this.id = id;
            this.gas = gas;
            this.nextNodeCost = nextNodeCost;
        }
        public GStation setNextStation(GStation nextStation) {
            this.nextStation = nextStation;
            return nextStation;
        }
        public int gasToCostDiff() {return gas - nextNodeCost;}
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || gas.length < 1 || gas.length != cost.length) {
            return -1;
        }
        PriorityQueue<GStation> minHeap = new PriorityQueue<>((s1,s2) -> Integer.compare(s2.gasToCostDiff(), s1.gasToCostDiff()));
        GStation prv = null, start = null;
        for(int idx=0; idx<gas.length; idx++) {
           GStation station = new GStation(idx, gas[idx], cost[idx]);
           if(prv!=null) {
               prv.nextStation = station;
           } else {
               start = station;
           }
           prv = station;
           minHeap.add(station);
        }
        prv.nextStation = start;

        while(!minHeap.isEmpty()) {
            start = minHeap.poll();
            if(start.gasToCostDiff() >=0) {
                GStation st = start; // start from this node.
                int gasst=st.gas;
                do{
                    // go to next node
                    gasst = gasst - st.nextNodeCost;
                    st = st.nextStation;
                    if(gasst <0) {
                        break;
                    }
                    gasst = gasst+st.gas;
                } while(st.id != start.id);
                if(st.id == start.id && gasst >=0) {
                    return start.id;
                }
            }
        }
       return -1;
    }

    public static void main(String[] args) {
        Q134GasStation sol = new Q134GasStation();

        System.out.println(sol.canCompleteCircuit2(new int[]{4,5,3,1,4}, new int[]{5,4,3,4,2}));
        System.out.println(sol.canCompleteCircuit2(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(sol.canCompleteCircuit2(new int[]{2,3,4}, new int[]{3,4,3}));
        System.out.println(sol.canCompleteCircuit2(new int[]{3,1,1}, new int[]{1,2,2}));

    }

}
