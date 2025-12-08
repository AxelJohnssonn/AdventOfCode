package com.adventofcode2025.Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.Aoc;

public class Day7 extends Aoc {

    protected Day7(String day, String inputFile) {
        super(day, inputFile);
    }

    public static void main(String[] args) {
        new Day7("7","input");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        char[][] diagram = new char[input.size()][input.get(0).length()];

 
        for (int y = 0; y < diagram.length; y++) {
            for(int x = 0; x < diagram[y].length; x++) {
                diagram[y][x] = input.get(y).toCharArray()[x];
            }
        }
        int splitCounter = 0;
        for (int y = 0; y < diagram.length; y++) {
            for(int x = 0; x < diagram[y].length; x++) {
                if(diagram[y][x]=='S') {
                    diagram[y][x] = '|';
                }
                if(y > 0 && diagram[y][x] == '.' && diagram[y-1][x] == '|') {
                    diagram[y][x] = '|';
                }
                if(diagram[y][x] == '^' && diagram[y-1][x] == '|') {
                    diagram[y][x+1] = '|';
                    diagram[y][x-1] = '|';
                    splitCounter++;
        
                }

            }
        }
        return String.valueOf(splitCounter);
    }

   @Override
    protected String part2(ArrayList<String> input) {
        char[][] diagram = new char[input.size()][input.get(0). length()];

        for (int y = 0; y < diagram. length; y++) {
            for(int x = 0; x < diagram[y].length; x++) {
                diagram[y][x] = input.get(y).toCharArray()[x];
            }
        }
        
        int start = 0;
        for(int y = 0; y < diagram.length; y++) {
            for(int x = 0; x < diagram[y].length; x++) {
                if(diagram[y][x] == 'S') {
                    start = x;
                    break;
                }
            }
        }

        Map<String, Long> countMap = new HashMap<>();
        return String.valueOf(timelineCount(diagram, new Tuple(start, 0), countMap));
    }

    private long timelineCount(char[][] diagram, Tuple pos, Map<String, Long> countMap) {
        int x = pos.x;
        int y = pos. y;

        if (x < 0 || x >= diagram[0].length) {
            return 0;
        }

        if (y >= diagram.length) {
            return 1;
        }
        String key = x+"."+y;

        if(countMap.containsKey(key)) {
            return countMap.get(key);
        }
        
        long result = 0;
        if(diagram[y][x] == '^') {
            result = timelineCount(diagram, new Tuple(x-1, y+1), countMap) + timelineCount(diagram, new Tuple(x+1, y+1), countMap);
        } else {
            result = timelineCount(diagram, new Tuple(x, y+1), countMap);
        }

        countMap.put(key, result);
        return result;
    }
    
}

class Tuple{ 
    public final int x; 
    public final int y; 
    
    public Tuple(int x, int y) { 
        this.x = x; 
        this.y = y; 
    }
}