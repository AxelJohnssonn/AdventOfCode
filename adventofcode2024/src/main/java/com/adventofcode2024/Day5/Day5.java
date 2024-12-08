package com.adventofcode2024.Day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.Aoc;
import com.adventofcode2024.Day4.Day4;

public class Day5 extends Aoc {

    private List<List<Integer>> invalidList;

    protected Day5(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day5("5");
    }

    @Override
    protected String part1(ArrayList<String> input) {

        Map<Integer, List<Integer>> rules = new HashMap<>();
        invalidList = new ArrayList<>();
        for (String s : input.subList(0, input.indexOf(""))) {
            int key = Integer.parseInt(s.split("\\|")[0]);
            int value = Integer.parseInt(s.split("\\|")[1]);
            rules.compute(key, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(value);
                return v;
            });
        }
        int sum = 0;
        for (String s : input.subList(input.indexOf("") + 1, input.size())) {
            boolean valid = true;
            List<Integer> printedPages = new ArrayList<>();
            for (String page : Arrays.stream(s.split(",")).toList()) {

                List<Integer> rule = rules.get(Integer.parseInt(page));
                if (rule != null && printedPages.stream().anyMatch(rule::contains)) {
                    valid = false;
                }

                printedPages.add(Integer.parseInt(page));

            }

            if (valid) {
                sum += Integer.parseInt(
                        Arrays.stream(s.split(",")).toList().get(Arrays.stream(s.split(",")).toList().size() / 2));
            } else {
                invalidList.add(
                        Arrays.stream(s.split(",")).mapToInt(p -> Integer.parseInt(p)).boxed()
                                .collect(Collectors.toList()));
            }
        }

        return String.valueOf(sum);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        Map<Integer, List<Integer>> rules = new HashMap<>();

        for (String s : input.subList(0, input.indexOf(""))) {
            int key = Integer.parseInt(s.split("\\|")[0]);
            int value = Integer.parseInt(s.split("\\|")[1]);
            rules.compute(key, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(value);
                return v;
            });
        }

        for (List<Integer> invalid : invalidList) {
            for (int i = invalid.size() - 1; i >= 0; i--) {
                int page = invalid.get(i);

                List<Integer> rule = rules.get(page);
                if (rule != null) {
                    for (int target : rule) {
                        int targetIndex = invalid.indexOf(target);
                        if (targetIndex != -1 && targetIndex < i) {
                            int tmp = invalid.get(targetIndex);
                            invalid.set(targetIndex, page);
                            invalid.set(i, tmp);
                            i++;
                            break;
                        }
                    }
                }
            }
        }
        return String.valueOf(invalidList.stream().mapToInt(invalid -> invalid.get((invalid.size() - 1) / 2)).sum());

    }
}
