package com.adventofcode.Day7;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;
import com.Aoc;

public class Day7 extends Aoc {
    private TreeMap<String, Integer> values;

    protected Day7(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day7("src//main//java//com//adventofcode//Day7//input.txt");
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

        ArrayList<String> fiveKind = new ArrayList<>();
        ArrayList<String> fourKind = new ArrayList<>();
        ArrayList<String> full = new ArrayList<>();
        ArrayList<String> threeKind = new ArrayList<>();
        ArrayList<String> twoPair = new ArrayList<>();
        ArrayList<String> pair = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String hand = line.split(" ")[0];
            int[] v = getValue(hand);

            if (getType(v).equals("1|5kind")) {
                fiveKind.add(line);
            } else if (getType(v).equals("2|4kind")) {
                fourKind.add(line);
            } else if (getType(v).equals("3|full")) {
                full.add(line);
            } else if (getType(v).equals("4|3kind")) {
                threeKind.add(line);
            } else if (getType(v).equals("5|2pair")) {
                twoPair.add(line);
            } else if (getType(v).equals("6|pair")) {
                pair.add(line);
            } else {
                high.add(line);
            }
        }

        fiveKind = sort(fiveKind);
        fourKind = sort(fourKind);
        full = sort(full);
        threeKind = sort(threeKind);
        twoPair = sort(twoPair);
        pair = sort(pair);
        high = sort(high);

        ArrayList<String> all = new ArrayList<>();
        high.forEach(s -> all.add(s));
        pair.forEach(s -> all.add(s));
        twoPair.forEach(s -> all.add(s));
        threeKind.forEach(s -> all.add(s));
        full.forEach(s -> all.add(s));
        fourKind.forEach(s -> all.add(s));
        fiveKind.forEach(s -> all.add(s));

        int sum = 0;
        for (int i = 0; i < all.size(); i++) {
            sum = sum + (Integer.parseInt(all.get(i).split(" ")[1]) * (i + 1));
        }
        return Integer.toString(sum);
    }

    public int[] getValue(String card) {
        int[] v = new int[5];
        char[] cArray = card.toCharArray();
        for (int k = 0; k < cArray.length; k++) {
            char c = cArray[k];
            v[k] = values.get(Character.toString(c));
        }
        return v;
    }

    public ArrayList<String> sort(ArrayList<String> list) {

        String[] listArray = list.toArray(new String[list.size()]);

        for (int i = 0; i < listArray.length; i++) {
            for (int x = 0; x < listArray.length - 1; x++) {
                if (getValue(listArray[x].split(" ")[0])[0] > getValue(listArray[x + 1].split(" ")[0])[0]) {
                    String temp = listArray[x];
                    listArray[x] = listArray[x + 1];
                    listArray[x + 1] = temp;
                } else if (getValue(listArray[x].split(" ")[0])[0] == getValue(listArray[x + 1].split(" ")[0])[0]) {
                    int[] first = getValue(listArray[x].split(" ")[0]);
                    int[] last = getValue(listArray[x + 1].split(" ")[0]);
                    for (int k = 1; k < 5; k++) {
                        if (first[k] > last[k]) {
                            String temp = listArray[x];
                            listArray[x] = listArray[x + 1];
                            listArray[x + 1] = temp;
                            break;
                        } else if (first[k] < last[k]) {
                            break;
                        }
                    }
                }
            }
        }

        ArrayList<String> out = new ArrayList<>();
        for (String s : listArray) {
            out.add(s);
        }
        return out;
    }

    public String getType(int[] values) {
        int likeCounter = 0;
        int prev = 999;
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            if (prev == 999) {
                prev = values[0];
            } else {
                if (prev == values[i]) {
                    likeCounter++;

                }
                prev = values[i];
            }
        }

        if (likeCounter == 1) {
            return "6|pair";
        }

        if (likeCounter == 2) {
            if (values[0] == values[1]) {
                if (values[1] == values[2]) {
                    return "4|3kind";
                } else {
                    return "5|2pair";
                }
            } else if (values[1] == values[2]) {
                if (values[2] == values[3]) {
                    return "4|3kind";
                } else {
                    return "5|2pair";
                }
            } else if (values[2] == values[3]) {
                return "4|3kind";
            }
        }

        if (likeCounter == 3) {
            if (values[1] == values[2] && values[2] == values[3]) {
                return "2|4kind";
            } else {
                return "3|full";
            }
        }

        if (likeCounter == 4) {
            return "1|5kind";
        }
        return "7|high";
    }

    @Override
    protected String part2(ArrayList<String> input) {
        return null;
    }

}
