package com.adventofcode.Day9;

import java.util.ArrayList;

import com.Aoc;

public class Day9 extends Aoc{
    private static ArrayList<Integer> lastDigit;

    public Day9(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        lastDigit = new ArrayList<>();
        new Day9("src//main//java//com//adventofcode//Day9//input.txt");
    }
    
    @Override
    protected String part1(ArrayList<String> input) {

        for(String s : input) {
            String[] line = s.split(" ");
            ArrayList<Integer> diffList =  new ArrayList<>();

            for(String o : line) {
                diffList.add(Integer.parseInt(o));
            }

            while(breakDown(diffList) != null) {
                
            }

            //breakDown(diffList);
            //System.out.println(breakDown(diffList));
            System.out.println(lastDigit);
        }

        return null;
    }

    public ArrayList<Integer> breakDown(ArrayList<Integer> list) {
        lastDigit.add(list.get(list.size()-1));

        if(list.get(list.size()-1) == list.get(0) ) {
            return list;
        }
        
        ArrayList<Integer> out = new ArrayList<>();
        
        for(int i = 0; i < list.size()-1; i++) {
            int o1 = list.get(i);
            int o2 = list.get(i+1);
            out.add(o2-o1);
        }

        return out;
        
        
    }

    @Override
    protected String part2(ArrayList<String> input) {
        return null;
    }
    
}