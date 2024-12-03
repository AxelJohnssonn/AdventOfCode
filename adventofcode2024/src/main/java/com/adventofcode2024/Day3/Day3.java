package com.adventofcode2024.Day3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Aoc;
import com.adventofcode2024.Day2.Day2;

public class Day3 extends Aoc {
    protected Day3(String day) {
        super(day);
    }

    public static void main(String[] args) {
        new Day3("3");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        StringBuilder sb = new StringBuilder();
        input.forEach(s -> sb.append(s));
        String pattern = "mul\\(\\d+,\\d+\\)";
        Pattern cPattern = Pattern.compile(pattern);
        Matcher matcher = cPattern.matcher(sb.toString());

        int sum = 0;
        while (matcher.find()) {
            String match = matcher.group();
            int first = Integer.parseInt(match.substring(4, match.length() - 1).split(",")[0]);
            int second = Integer.parseInt(match.substring(4, match.length() - 1).split(",")[1]);
            sum += first * second;
        }
        return String.valueOf(sum);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        StringBuilder sb = new StringBuilder();
        input.forEach(s -> sb.append(s));
        String pattern = "mul\\(\\d+,\\d+\\)|do\\(\\)|don\\'t\\(\\)";
        Pattern cPattern = Pattern.compile(pattern);
        Matcher matcher = cPattern.matcher(sb.toString());

        int sum = 0;
        Boolean calculate = true;
        while (matcher.find()) {
            String match = matcher.group();
            if (match.equals("do()")) {
                calculate = true;
            } else if (match.equals("don't()")) {
                calculate = false;
            }

            if (calculate && match.matches("mul\\(\\d+,\\d+\\)")) {
                int first = Integer.parseInt(match.substring(4, match.length() - 1).split(",")[0]);
                int second = Integer.parseInt(match.substring(4, match.length() - 1).split(",")[1]);
                sum += first * second;
            }
        }
        return String.valueOf(sum);
    }

}
