package com.day1;

import java.util.ArrayList;
import java.util.TreeSet;

import com.Aoc;

public class Day1 extends Aoc {
    protected Day1(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day1("adventofcode/src/main/java/com/day1/data.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        int mostC = 0;
        int current = 0;
        for(String s : input) {
            if(s != null && s != "") {
                current = current + Integer.parseInt(s);
            }else if(mostC <= current){
                mostC = current;
                current = 0;
            }else {
                current = 0;
            }
        }

        return String.valueOf(mostC);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        TreeSet<Integer> set = new TreeSet<>();
        int sum = 0;
        int current = 0;
        for(String s : input) {
            if(s != null && s != "") {
                current += Integer.parseInt(s);
            }else {
                set.add(current);
                current = 0;
            }
        }

        for(int i = 0; i < 3; i++) {
            sum += set.pollLast();
        }
        return String.valueOf(sum);
    }

}