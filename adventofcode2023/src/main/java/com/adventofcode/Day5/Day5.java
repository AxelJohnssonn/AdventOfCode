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
    public static ArrayList<Long>locations;

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
        //BRUTE FORCE WITH 10 THREADS HAHA
        //Result from threads:
        //2 491 050 754 too high!
        //1 169 277 931 too high!
        //2 355 386 760 too high!
        //702 443 113 too high!
        //KORREKT: 125 742 456

        //körning 2 16:32 <-startade
        
        String firstLine = input.get(0);
        
        for (String s : firstLine.split(":")[1].split(" ")) {
            if (!s.equals("")) {
                seeds.add(Long.parseLong(s.trim()));
            }
        }

        ArrayList<String> reversedLines = new ArrayList<>();

            for(int i = input.size()-1; i >= 0; i--){
                reversedLines.add(input.get(i));

            }

        ArrayList<Long> minList = new ArrayList<>();

        for(int i = 8; i < seeds.size(); i+=2) {
            minList.add(seeds.get(i));
        }
        System.out.println(minList);

        
        //Brute force deluxe haha.
        Thread[] threads = new Thread[minList.size()];
    
        locations = new ArrayList<>();
        for(int i = 0 ; i < minList.size(); i++) {
            Long min = minList.get(i);
            Long max = getRange(min, seeds);

            threads[i] = new Thread(() -> {
                findNbr(min, max, input);
            });
            System.out.println("Starting Thread: " + i + " Checking from: " + min + " To: " + max);
            

            threads[i].start();
        }
        System.out.println("Results from part 2 is not displayed correctly, all locations were printed out and manually checked, optimized algoritm on it's way!");
        return Long.toString(0);
        
    }

    public static Long getRange(Long min, ArrayList<Long> seeds) {
        Long max = (long) 0;
        int index = seeds.indexOf(min);

        max = min + seeds.get(index+1)-1;
        return max;
    }

    public long findNbr(Long min, Long max, ArrayList<String> input) {
        boolean converted = false;
        Long minlocation = Long.MAX_VALUE;
        for(Long i = min; i < max; i++) {
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

        System.out.println("For seed " + min +" to "+ max +" -> min location: "+  minlocation);
        return minlocation;
    }
}
