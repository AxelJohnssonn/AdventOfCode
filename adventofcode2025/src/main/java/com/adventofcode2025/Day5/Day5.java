package com.adventofcode2025.Day5;
 
import java.util.ArrayList;
 
import com.Aoc;
 
public class Day5 extends Aoc {
 
    protected Day5(String day, String inputFile) {
        super(day, inputFile);
    }
 
    public static void main(String[] args) {
        new Day5("5","input");
    }
 
    @Override
    protected String part1(ArrayList<String> input) {
        ArrayList<Range> rangeList = new ArrayList<>();
        ArrayList<Long> ingredientIDList = new ArrayList<>();
        for(String s : input) {
            if(s.equals(""))
                continue;
            if(s.contains("-")) {
                rangeList.add(new Range(Long.valueOf(s.split("-")[0]), Long.valueOf(s.split("-")[1])));
            }else {
                ingredientIDList.add(Long.valueOf(s));
            }
        }
        int freshCounter = 0;
        for(long ingredientID : ingredientIDList) {
            for(Range range : rangeList) {
                if(ingredientID >= range.lowerLimit && ingredientID <= range.upperLimit) {
                    freshCounter++;
                    break;
                }
            }
        }
        return String.valueOf(freshCounter);
    }
 
    @Override
    protected String part2(ArrayList<String> input) {
         ArrayList<Range> rangeList = new ArrayList<>();
        for(String s : input) {
            if(s.contains("-")) {
                rangeList.add(new Range(Long.valueOf(s.split("-")[0]), Long.valueOf(s.split("-")[1])));
            }
        }
 
        ArrayList<Range> optimizedRangeList = new ArrayList<>();
        rangeList.sort((r1,r2) -> Long.compare(r1.lowerLimit, r2.lowerLimit));
        for(int i = 0; i < rangeList.size(); i++) {
            long start = rangeList.get(i).lowerLimit;
            long end = rangeList.get(i).upperLimit;

            if(!optimizedRangeList.isEmpty() && optimizedRangeList.get(optimizedRangeList.size()-1).upperLimit >= end) {
                continue;
            }

            for(int k = i+1; k < rangeList.size(); k++) {
                if(rangeList.get(k).lowerLimit <= end) {
                    end = Math.max(end, rangeList.get(k).upperLimit);
                }
            }
            optimizedRangeList.add(new Range(start,end));
        }
        long res = 0;
        for(Range r : optimizedRangeList) {
            res += (r.upperLimit - r.lowerLimit) +1;
        }
        return String.valueOf(res);
    }
 
}
 
class Range{
    public final long lowerLimit;
    public final long upperLimit;
   
    public Range(long lowerLimit, long upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
}