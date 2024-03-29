package com.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.Aoc;

public class Day2 extends Aoc {
    protected Day2(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day2("adventofcode/src/main/java/com/day2/data.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        HashMap<String, Integer> map = new HashMap<>();
        //A X rock
        //B Y paper
        //C Z Scissors

        map.put("A Y", 8); //win + 2
        map.put("A Z", 3); //loss + 3
        map.put("A X", 4); // draw + 1
        map.put("B X", 1); //loss + 1
        map.put("B Y", 5); //draw + 2
        map.put("B Z",9); //win + 3
        map.put("C X", 7); //win + 1
        map.put("C Z", 6); //draw + 3
        map.put("C Y", 2); //loss + 2

        int score = 0;
        for(String s : input) {
            if(map.containsKey(s)) {
                System.out.println("Adderar " + map.get(s) + " till " + score);
                score += map.get(s);
            }
        }
        return String.valueOf(score);
    }

    @Override
    protected String part2(ArrayList<String> input) {
       
        return String.valueOf(0);
    }

}