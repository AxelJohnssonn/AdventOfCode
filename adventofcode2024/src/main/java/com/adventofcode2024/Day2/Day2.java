package com.adventofcode2024.Day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.Aoc;
import com.adventofcode2024.Day1.Day1;

public class Day2 extends Aoc {
    protected Day2(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day2("adventofcode2024/src/main/java/com/adventofcode2024/Day2/input.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        int safeSum = 0;

        for (String row : input) {
            List<Integer> report = Arrays.stream(row.split(" ")).mapToInt(c -> Integer.parseInt(c)).boxed()
                    .collect(Collectors.toList());
            if (isSafe(report)) {
                safeSum++;
            }
        }
        return String.valueOf(safeSum);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        int safeSum = 0;

        for (String row : input) {
            List<Integer> report = Arrays.stream(row.split(" ")).mapToInt(c -> Integer.parseInt(c)).boxed()
                    .collect(Collectors.toList());
            if (isSafe(report)) {
                safeSum++;
                continue;
            } else {
                for (int i = 0; i < report.size(); i++) {
                    int removed = report.remove(i);
                    if (isSafe(report)) {
                        safeSum++;
                        break;
                    }
                    report.add(i, removed);
                }
            }
        }
        return String.valueOf(safeSum);
    }

    private boolean isSafe(List<Integer> report) {
        int diff = 0;
        int prev = report.get(0);
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 1; i < report.size(); i++) {
            int nbr = report.get(i);
            diff = Math.abs(prev - nbr);
            if (diff > 3 || diff < 1) {
                return false;
            }
            if ((nbr - prev) > 0) {
                decreasing = false;
            } else if ((nbr - prev) < 0) {
                increasing = false;
            }
            prev = nbr;
        }
        return increasing || decreasing;
    }
}
