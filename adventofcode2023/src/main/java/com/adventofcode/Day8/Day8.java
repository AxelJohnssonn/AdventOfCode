package com.adventofcode.Day8;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.Aoc;

public class Day8 extends Aoc {
    protected Day8(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day8("src//main//java//com//adventofcode//Day8//input.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {

        TreeMap<String, String> map = new TreeMap<>();

        String directions = input.get(0);
        char[] dArray = directions.toCharArray();

        for (int i = 2; i < input.size(); i++) {
            String key = input.get(i).split("=")[0];
            String value = input.get(i).split("=")[1].replaceAll("[^a-zA-Z0-9 ]", "");
            map.put(key.trim(), value);
        }
        String current = "AAA";
        Boolean found = false;
        int steps = 0;
        while (!found) {

            for (int i = 0; i < dArray.length; i++) {

                if (current.equals("ZZZ")) {
                    found = true;
                    break;
                }

                if (dArray[i] == 'L') {
                    current = map.get(current).split(" ")[1];
                } else if (dArray[i] == 'R') {
                    current = map.get(current).split(" ")[2];
                }
                steps++;
            }
        }

        return Integer.toString(steps);

    }

    @Override
    protected String part2(ArrayList<String> input) {
        TreeMap<String, String> map = new TreeMap<>();

        String directions = input.get(0);
        char[] dArray = directions.toCharArray();

        for (int i = 2; i < input.size(); i++) {
            String key = input.get(i).split("=")[0];
            String value = input.get(i).split("=")[1].replaceAll("[^a-zA-Z0-9 ]", "");
            map.put(key.trim(), value);
        }

        ArrayList<String> endsWithA = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().endsWith("A")) {
                endsWithA.add(entry.getKey());
                // System.out.println(entry.getKey());
            }
        }

        Boolean found = false;
        String[] a = endsWithA.toArray(new String[endsWithA.size()]);

        ArrayList<Integer> res = new ArrayList<>();
        int steps = 0;
        String current[] = a;
        for (int k = 0; k < current.length; k++) {
            steps = 0;
            found = false;
            // System.out.println("Checking " + current[]);
            while (!found) {

                for (int i = 0; i < dArray.length; i++) {

                    if (current[k].endsWith("Z")) {
                        found = true;
                        break;
                    }

                    if (dArray[i] == 'L') {
                        current[k] = map.get(current[k]).split(" ")[1];
                    } else if (dArray[i] == 'R') {
                        current[k] = map.get(current[k]).split(" ")[2];
                    }
                    steps++;
                }
            }
            res.add(steps);
        }

        System.out.println("Answer for each: " + res);
        //System.out.println(lcm(res));

        return Long.toString(lcm(res));
    }

    public Long lcm(ArrayList<Integer> res) {
        Integer[] resArray = res.toArray(new Integer[res.size()]);

        long lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean div = false;

            for (int i = 0; i < resArray.length; i++) {
                if (resArray[i] == 1) {
                    counter++;
                }
                if (resArray[i] % divisor == 0) {
                    div = true;
                    resArray[i] = resArray[i] / divisor;
                }
            }
            if (div) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            } else {
                divisor++;
            }
            if (counter == resArray.length) {
                return lcm_of_array_elements;
            }
        }
    }
}
