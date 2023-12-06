package com.adventofcode.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.Aoc;

public class Day5 extends Aoc{

    protected Day5(String fileName) {
        super(fileName);
    }

    private static ArrayList<Long> seeds;
    private static ArrayList<String> lines;

    public static void main(String[] args) {
        
        new Day5("src//main//java//com//adventofcode//Day5//input.txt");
    }

    protected String part1(final ArrayList<String> input) {
        seeds = new ArrayList<>();

        String firstLine = input.get(0);

        for (String s : firstLine.split(":")[1].split(" ")) {
            if (!s.equals("")) {
                seeds.add(Long.parseLong(s.trim()));
            }
        }

        Long[] data = new Long[seeds.size()];
        System.out.println(seeds);

        for (int i = 0; i < seeds.size(); i++) {
            data[i] = seeds.get(i);
            //System.out.println(data[i]);
        }

        boolean converted = false;

        for (int i = 0; i < data.length; i++) {

            for (String line : input) {

                if (line.equals("")) {
                    converted = false;
                }

                if (!line.equals("") && Character.isDigit(line.charAt(0)) && !converted) {

                    if (data[i] <= Long.parseLong(line.split(" ")[1]) + Long.parseLong(line.split(" ")[2]) - 1
                            && data[i] >= Long.parseLong(line.split(" ")[1])) {
                        data[i] = Long.parseLong(line.split(" ")[0]) - Long.parseLong(line.split(" ")[1]) + data[i];
                        converted = true;
                    }

                }
            }
        }

        ArrayList<Long> locations = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i++) {
            System.out.println("Seed " + seeds.get(i) + " -> " + " Location " + data[i]);
            locations.add(data[i]);
        }

        locations.sort((o1, o2) -> o1.compareTo(o2));

        System.out.println("Result part1: " + locations.get(0));
        return locations.get(0).toString();
    }

    protected String part2(final ArrayList<String> input) {

        seeds = new ArrayList<>();

        
        //Long min = Collections.min(seeds);
        
        
        String firstLine = input.get(0);
        
        for (String s : firstLine.split(":")[1].split(" ")) {
            if (!s.equals("")) {
                seeds.add(Long.parseLong(s.trim()));
            }
        }

        ArrayList<Long> minList = new ArrayList<>();

        for(int i = 0; i < seeds.size(); i+=2) {
            minList.add(seeds.get(i));
        }
        System.out.println(minList);

        //minList.sort((l1,l2) -> l1.compareTo(l2));
        Long min = minList.get(0);

        Long minlocation = Long.MAX_VALUE;

        boolean converted = false;
        for(Long i = min; i < getRange(min, seeds); i++) {
            Long seed = i;
            //System.out.println(seed);
            for (String line : input) {

                if (line.equals("")) {
                    converted = false;
                }

                if (!line.equals("") && Character.isDigit(line.charAt(0)) && !converted) {

                    if (seed <= Long.parseLong(line.split(" ")[1]) + Long.parseLong(line.split(" ")[2]) - 1
                            && seed >= Long.parseLong(line.split(" ")[1])) {
                        seed = Long.parseLong(line.split(" ")[0]) - Long.parseLong(line.split(" ")[1]) + seed;
                        converted = true;
                    }

                }
            }
            if(seed < minlocation) {
                minlocation = seed;
            }
        }

        return Long.toString(minlocation);
    }

    public static Long getRange(Long min, ArrayList<Long> seeds) {
        Long max = (long) 0;
        int index = seeds.indexOf(min);

        max = min + seeds.get(index+1);
        return max;
    }
}
