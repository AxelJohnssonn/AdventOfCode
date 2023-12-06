package com.adventofcode.Day6;

import java.util.ArrayList;
import com.Aoc;

public class Day6 extends Aoc {

    protected Day6(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day6("src//main//java//com//adventofcode//Day6//input.txt");
        
    }

    @Override
    protected String part1(final ArrayList<String> in) {

        ArrayList<Integer> timeList = new ArrayList<>();
        ArrayList<Integer> distanceList = new ArrayList<>();

        for (String s : in.get(0).split(": ")[1].trim().split(" ")) {
            if (!s.isEmpty()) {
                timeList.add(Integer.parseInt(s));
            }
        }

        for (String s : in.get(1).split(": ")[1].trim().split(" ")) {
            if (!s.isEmpty()) {
                distanceList.add(Integer.parseInt(s));
            }
        }

        int winCounter = 0;
        int res = 1;
        for (int k = 0; k < timeList.size(); k++) {
            for (int i = 0; i < timeList.get(k); i++) {
                int time = timeList.get(k) - i;
                int currentTravel = 0;
                int speed = i;
                currentTravel = time * speed;
                if (currentTravel > distanceList.get(k)) {
                    winCounter++;
                }
            }
            res = res * winCounter;
            winCounter = 0;
        }

        return Integer.toString(res);
    }

    @Override
    protected String part2(final ArrayList<String> in) {
        String totTimeString = "";
        String totDisctanceString = "";

        for (String s : in.get(0).split(": ")[1].trim().split(" ")) {
            if (!s.isEmpty()) {
                totTimeString = totTimeString + s;
            }
        }

        for (String s : in.get(1).split(": ")[1].trim().split(" ")) {
            if (!s.isEmpty()) {
                totDisctanceString = totDisctanceString + s;
            }
        }

        Long totTime = Long.parseLong(totTimeString);
        Long totDisctance = Long.parseLong(totDisctanceString);

        int winCounter = 0;
        for (int i = 0; i < totTime; i++) {
            Long time = totTime - i;
            Long currentTravel = (long) 0;
            Long speed = (long) i;
            currentTravel = time * speed;
            if (currentTravel > totDisctance) {
                winCounter++;
            }

        }
        return Integer.toString(winCounter);
    }
}
