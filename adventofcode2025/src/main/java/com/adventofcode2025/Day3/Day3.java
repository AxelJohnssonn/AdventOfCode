package com.adventofcode2025.Day3;

import java.util.ArrayList;
import java.util.Arrays;

import com.Aoc;

public class Day3 extends Aoc {

    protected Day3(String day, String inputFile) {
        super(day, inputFile);
    }

    public static void main(String[] args) {
        new Day3("3","input");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        ArrayList<Integer> largestBatteryList = new ArrayList<>();
        for(String batteries : input) {
            char[] sortedBatteries = batteries.toCharArray();
            Arrays.sort(sortedBatteries);
            String biggestBattery = String.valueOf(sortedBatteries[sortedBatteries.length-1]);
            char[] subCharArray = batteries.substring(batteries.indexOf(biggestBattery)+1, batteries.length()).toCharArray();
            Arrays.sort(subCharArray);
            String biggestAfterBiggest = "";
            if(subCharArray.length != 0) {

                biggestAfterBiggest = String.valueOf(subCharArray[subCharArray.length-1]);
            }else {
                biggestAfterBiggest = String.valueOf(sortedBatteries[sortedBatteries.length-2]);
                if(batteries.indexOf(biggestAfterBiggest)<batteries.indexOf(biggestBattery)) {
                    String temp = biggestBattery;
                    biggestBattery = biggestAfterBiggest;
                    biggestAfterBiggest = temp;
                }
            }
            largestBatteryList.add(Integer.parseInt(biggestBattery+biggestAfterBiggest));
        }
        return String.valueOf(largestBatteryList.stream().mapToInt(Integer::intValue).sum());
    }

    @Override
    protected String part2(ArrayList<String> input) {
        ArrayList<Long> largestBatteryList = new ArrayList<>();
        for (String batteries : input) {
            int toRemove = batteries.length() - 12;
            
            StringBuilder result = new StringBuilder();
            
            for (int i = 0; i < batteries.length(); i++) {
                char current = batteries.charAt(i);
                                
                while (result.length() > 0 && toRemove > 0 && result.charAt(result.length() - 1) < current) {
                    result.deleteCharAt(result.length() - 1);
                    toRemove--;
                }
                
                result.append(current);
            }
            
            while (result.length() > 12) {
                result.deleteCharAt(result.length() - 1);
            }
            
            largestBatteryList.add(Long.valueOf(result.toString()));
        }

        return String.valueOf(largestBatteryList.stream().mapToLong(Long::longValue).sum());
    }
}
