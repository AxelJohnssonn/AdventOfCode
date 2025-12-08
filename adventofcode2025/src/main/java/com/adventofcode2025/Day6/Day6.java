package com.adventofcode2025.Day6;

import java.util.ArrayList;
import java.util.List;
import com.Aoc;

public class Day6 extends Aoc {

    protected Day6(String day, String inputFile) {
        super(day, inputFile);
    }

    public static void main(String[] args) {
        new Day6("6", "input");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        long result = 0;
        for(int i = 0; i < input.get(input.size() - 1).trim().split("\\s+").length; i++) {
            long res = 0;
            String prefix = input.get(input.size() - 1).trim().split("\\s+")[i];
            for(int k = 0; k < input.size() - 1; k++) {
                long number = 0;
                number = Long.parseLong(input.get(k).trim().split("\\s+")[i].trim());
                if (res == 0) {
                    res = number;
                    continue;
                }
                if(prefix.equals("*")) {
                    res = res * number;
                } else if (prefix.equals("+")) {
                    res += number;
                }
            }
            result += res;
        }

        return String.valueOf(result);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        long result = 0;
        String operatorLine = input.get(input.size() - 1);
        List<int[]> positions = new ArrayList<>();

        int index = 0;
        while (index < operatorLine.length()) {
            while (index < operatorLine.length() && operatorLine.charAt(index) == ' ') {
                index++;
            }
            if (index >= operatorLine.length())
                break;

            int start = index;
            while (index < operatorLine.length() && operatorLine.charAt(index) != ' ') {
                index++;
            }
            int end = index;
            while (end < operatorLine.length() && operatorLine.charAt(end) == ' ') {
                end++;
            }
            positions.add(new int[] { start, end });
            index = end;
        }

        int maxLineLen = input.stream().mapToInt(String::length).max().orElse(0);
        positions.get(positions.size() - 1)[1] = maxLineLen;

        for (int i = positions.size() - 1; i >= 0; i--) {
            int start = positions.get(i)[0];
            int stop = positions.get(i)[1];

            String prefix = operatorLine.substring(start, Math.min(stop, operatorLine.length())).trim();
            List<String> numberList = new ArrayList<>();
            for (int k = 0; k < input.size() - 1; k++) {
                String line = input.get(k);
                String number = "";
                if (start < line.length()) {
                    number = line.substring(start, Math.min(stop, line.length()));
                }
                numberList.add(number);
            }
            int maxLen = numberList.stream().mapToInt(String::length).max().orElse(0);

            ArrayList<String> parsedList = new ArrayList<>();

            for (int col = maxLen - 1; col >= 0; col--) {
                StringBuilder sb = new StringBuilder();
                for (String s : numberList) {
                    if (col < s.length() && s.charAt(col) != ' ') {
                        sb.append(s.charAt(col));
                    }
                }
                if (sb.length() > 0) {
                    parsedList.add(sb.toString());
                }
            }
            long res = 0;
            for (String s : parsedList) {
                if (prefix.equals("*")) {
                    if (res == 0) {
                        res = Long.parseLong(s);
                        continue;
                    }
                    res = res * Long.parseLong(s);
                } else if (prefix.equals("+")) {
                    res += Long.parseLong(s);
                }

            }
            result += res;
        }
        return String.valueOf(result);
    }
}