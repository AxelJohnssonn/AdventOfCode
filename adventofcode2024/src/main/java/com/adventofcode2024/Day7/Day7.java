package com.adventofcode2024.Day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Aoc;

public class Day7 extends Aoc {
    protected Day7(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day7("7");
    }

    @Override
    protected String part1(ArrayList<String> input) {

        List<List<Long>> rows = input.stream()
                .map(line -> Arrays.stream(line.split(":? ")).map(Long::parseLong).toList()).toList();

        Long sum = (long) 0;
        for (List<Long> row : rows) {
            if (isValid(row, row.get(0), row.get(1), 2)) {
                sum += row.get(0);
            }

        }

        return String.valueOf(sum);
    }

    public boolean isValid(List<Long> row, Long result, Long current, int index) {
        if (index == row.size()) {
            return current.longValue() == result.longValue();
        }
        return isValid(row, result, current + row.get(index), index + 1)
                || isValid(row, result, current * row.get(index), index + 1);

    }

    public boolean isValidPart2(List<Long> row, Long result, Long current, int index) {
        if (index == row.size()) {
            return current.longValue() == result.longValue();
        }
        return isValidPart2(row, result, current + row.get(index), index + 1)
                || isValidPart2(row, result, current * row.get(index), index + 1)
                || isValidPart2(row, result, Long.parseLong(String.format("%d%d", current, row.get(index))), index + 1);

    }

    @Override
    protected String part2(ArrayList<String> input) {
        List<List<Long>> rows = input.stream()
                .map(line -> Arrays.stream(line.split(":? ")).map(Long::parseLong).toList()).toList();

        Long sum = (long) 0;
        for (List<Long> row : rows) {
            if (isValidPart2(row, row.get(0), row.get(1), 2)) {
                sum += row.get(0);
            }
        }
        return String.valueOf(sum);
    }
}
