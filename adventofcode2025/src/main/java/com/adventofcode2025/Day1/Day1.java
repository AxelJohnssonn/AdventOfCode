package com.adventofcode2025.Day1;
import java.util.ArrayList;

import com.Aoc;



public class Day1 extends Aoc {
    
    protected Day1(String day, String inputFile) {
        super(day, inputFile);
    }

    public static void main(String[] args) {
        new Day1("1","input");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        int password = 0;
        int currentposition = 50;
        
        for(String row : input)
            {
                int step = 0;
                if(row.startsWith("R")) {
                    step = Integer.parseInt(row.split("R")[1]);
                    currentposition = (currentposition + step) % 100;
                } else if(row.startsWith("L")){
                    step = Integer.parseInt(row.split("L")[1]);
                    currentposition = (100+currentposition-step) % 100;
                }
                if(currentposition == 0)
                    password++;
            }
      
        return String.valueOf(password);
    }

    

    @Override
    protected String part2(ArrayList<String> input) {
        int password = 0;
        int currentposition = 50;
        boolean right = false;
        for(String row : input) {
            int step = 0;
            if(row.startsWith("R")) {
                step = Integer.parseInt(row.split("R")[1]);
                right = true;
            } else if(row.startsWith("L")){
                step = Integer.parseInt(row.split("L")[1]);
                right = false;
            }
            for(int i = 0; i < step; i++) {
                if(right) {
                    currentposition = (currentposition + 1) % 100;
                } else {
                    currentposition = (currentposition - 1 + 100) % 100;
                }
                if(currentposition == 0) {
                    password++;
                }
            }
        }
      
        return String.valueOf(password);
    }
}
