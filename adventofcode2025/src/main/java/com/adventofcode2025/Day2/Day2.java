package com.adventofcode2025.Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.Aoc;

public class Day2 extends Aoc {

    protected Day2(String day, String inputFile) {
        super(day, inputFile);
    }

    public static void main(String[] args) {
        new Day2("2","input");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        ArrayList<Tuple<Long, Long>> tupleList = new ArrayList<>();
        ArrayList<Long> invalidList = new ArrayList<>();
        for(String r : input){
            Arrays.stream(r.split(",")).filter(pair ->(pair.split("-")[0].length()%2 == 0 || pair.split("-")[1].length()%2 == 0))
                    .collect(Collectors.toList())
                    .forEach(pair -> tupleList.add(new Tuple(Long.valueOf(pair.split("-")[0]), Long.valueOf(pair.split("-")[1]))));
        }

        for(Tuple pair : tupleList)
        {
            for(long i =(long)pair.x; i <= (long) pair.y; i++){
                if(String.valueOf(i).length()%2 == 0) {
                    String leftHalf = String.valueOf(i).substring(0, String.valueOf(i).length()/2);
                    String RightHalf = String.valueOf(i).substring(String.valueOf(i).length()/2);
                    if(leftHalf.equals(RightHalf)){
                        invalidList.add(i);
                    }
                }

            }
        }
        
        return String.valueOf(invalidList.stream().mapToLong(Long::longValue).sum());
    }

    @Override
    protected String part2(ArrayList<String> input) {
       
        ArrayList<Tuple<Long, Long>> tupleList = new ArrayList<>();
        ArrayList<Long> invalidList = new ArrayList<>();
        for(String r : input){
            Arrays.stream(r.split(","))
                    .collect(Collectors.toList())
                    .forEach(pair -> tupleList.add(new Tuple(Long.valueOf(pair.split("-")[0]), Long.valueOf(pair.split("-")[1]))));
        }

        for(Tuple pair : tupleList)
        {
            for(long i =(long)pair.x; i <= (long) pair.y; i++){
                int length = String.valueOf(i).length();
                String s = String.valueOf(i);
                for(int patternLength = length / 2; patternLength >= 1; patternLength--) {
                    if(length % patternLength != 0) {
                        continue;
                    }
                    boolean repeting = true;
                    String pattern = s.substring(0, patternLength);
                    for(int k = patternLength; k < length; k+=patternLength) {
                        String segment = s.substring(k, k+patternLength);
                        if(!segment.equals(pattern)) {
                            repeting = false;
                            break;
                        }
                    }
                    if(repeting) {
                        invalidList.add(i);
                        break;
                    }
                }
                

            }
        }
        
        return String.valueOf(invalidList.stream().mapToLong(Long::longValue).sum());
    }
}

class Tuple<X, Y> { 
    public final X x; 
    public final Y y; 
    
    public Tuple(X x, Y y) { 
        this.x = x; 
        this.y = y; 
    } 
}
