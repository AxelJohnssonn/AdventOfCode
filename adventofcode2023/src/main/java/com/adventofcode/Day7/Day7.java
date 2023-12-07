package com.adventofcode.Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Arrays;
import com.Aoc;

import javafx.beans.binding.StringBinding;

public class Day7 extends Aoc {
    private TreeMap<String, Integer> values;

    protected Day7(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day7("src//main//java//com//adventofcode//Day7//example.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        values = new TreeMap<>();
        values.put("A", 14);
        values.put("K", 13);
        values.put("Q", 12);
        values.put("J", 11);
        values.put("T", 10);
        for (int i = 0; i < 10; i++) {
            values.put(Integer.toString(i), i);
        }

        System.out.println(values);

        HashMap<String, int[]> HandStrengthMap = new HashMap<>();

        HashMap<String, String> rankMap = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String hand = line.split(" ")[0];
            int[] v = getValue(hand);

            int bet = Integer.parseInt(line.split(" ")[1]);
            // System.out.println("Hand: " + hand + " Bet: "+ bet + " Type: " + getType(v));

            HandStrengthMap.put(hand, v);

            // char[] handArray= hand.toCharArray();
            // Arrays.sort(handArray);
            // hand = new String(handArray);

            // Place out on ranks:
            int rank = 0;
            rankMap.put(line, getType(getValue(hand)));
        }

            /* 
            if (getType(v).equals("5kind")) {
                if (rankMap.containsKey(6)) {
                    int k = 0;
                    while (getValue(rankMap.get(6).split(" ")[0])[k] == v[k]) {
                        //System.out.println("Lika: " + hand);
                        k++;
                    }
                }
                rankMap.put(6, line);
            } else if (getType(v).equals("4kind")) {
                if (rankMap.containsKey(5)) {
                    int k = 0;
                    while (getValue(rankMap.get(5).split(" ")[0])[k] == v[k]) {
                        // System.out.println("Lika: " + hand);
                        k++;
                    }
                }
                rankMap.put(5, line);
            } else if (getType(v).equals("full")) {
                if (rankMap.containsKey(4)) {
                    int k = 0;
                    while (getValue(rankMap.get(4).split(" ")[0])[k] == v[k]) {
                        k++;
                    }
                    if (k > 0) {

                        //System.out.println("Lika: " + hand + " & " + rankMap.get(4) + "Lika tills: " + k);
                    }
                }
                rankMap.put(4, line);
            } else if (getType(v).equals("3kind")) {
                if (rankMap.containsKey(3)) {
                    int k = 0;
                    while (getValue(rankMap.get(3).split(" ")[0])[k] == v[k]) {
                        k++;
                    }
                    if (k > 0) {

                        //System.out.println("Lika: " + hand + " & " + rankMap.get(3) + "Lika tills: " + k);
                    }
                }
                rankMap.put(3, line);
            } else if (getType(v).equals("2pair")) {
                if (rankMap.containsKey(2)) {
                    for (int k = 0; k < 5; k++) {
                        if (getValue(rankMap.get(2).split(" ")[0])[k] < v[k]) {
                            System.out.println("OPS" + getValue(rankMap.get(2).split(" ")[0])[k] + " " + v[k]);

                            int exist = getValue(rankMap.get(2).split(" ")[0])[k];
                            int checking = v[k];
                            System.out.println(exist + " " + checking);
                            if (exist < checking) {
                            rankMap.put(2, line);
                            System.out.println(rankMap.get(2) + " Ersätts med " + line);
                            }
                            break;
                        }
                    }

                    System.out.println(rankMap);
                    //System.out.println("Lika: " + hand + " & " + rankMap.get(2));

                    
                }else{
                    rankMap.put(2, line);
                   
                }
            } else if (getType(v).equals("pair")) {
                if (rankMap.containsKey(1)) {
                    int k = 0;
                    while (getValue(rankMap.get(1).split(" ")[0])[k] == v[k]) {
                        k++;
                    }
                    if (k > 0) {

                        //System.out.println("Lika: " + hand + " & " + rankMap.get(1) + "Lika tills: " + k);
                    }

                } else {
                    rankMap.put(1, line);
                }
            }

        }
        input.sort((s1, s2) -> s1.compareTo(s2));
        // System.out.println(HandStrengthMap);
        */
        return null;
    }

    public int[] getValue(String card) {
        int[] v = new int[5];
        char[] cArray = card.toCharArray();
        for (int k = 0; k < cArray.length; k++) {
            char c = cArray[k];
            v[k] = values.get(Character.toString(c));
            //System.out.println(v[k] + " " + card);
        }
        return v;
    }

    public String getType(int[] values) {
        String type = "";
        int likeCounter = 0;
        int prev = 999;
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            if (prev == 999) {
                prev = values[0];
            } else {
                if (prev == values[i]) {
                    likeCounter++;

                } else {
                    // likeCounter = 0;
                }
                prev = values[i];
            }
            // System.out.print(values[i]+ " ");
        }

        if (likeCounter == 1) {
            // System.out.println(" Prev Counter " + likeCounter + " type pair");
            return "6|pair";
        }

        if (likeCounter == 2) {
            if (values[0] == values[1]) {
                if (values[1] == values[2]) {
                    type = "4|3kind";
                    // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                    return "4|3kind";
                } else {
                    type = "5|2pair";
                    // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                    return "5|2pair";
                }
            } else if (values[1] == values[2]) {
                if (values[2] == values[3]) {
                    type = "4|3kind";
                    // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                    return "4|3kind";
                } else {
                    type = "5|2pair";
                    // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                    return "5|2pair";
                }
            } else if (values[2] == values[3]) {
                type = "4|3kind";
                // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                return "4|3kind";
            }
        }

        if (likeCounter == 3) {
            if (values[1] == values[2] && values[2] == values[3]) {
                type = "2|4kind";
                // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                return "2|4kind";
            } else {
                type = "3|full";
                // System.out.println(" Prev Counter " + likeCounter + " type " + type);
                return "3|full";
            }
        }

        if (likeCounter == 4) {
            type = "1|5kind";
            return "1|5kind";
        }

        // System.out.println(" Prev Counter " + likeCounter + " type " + "high");

        return "7|high";
    }

    @Override
    protected String part2(ArrayList<String> input) {
        return null;
    }

}
