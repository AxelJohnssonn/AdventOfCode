package com.adventofcode2024.Day1;

import java.util.ArrayList;
import java.util.HashMap;

import com.Aoc;

public class Day1 extends Aoc {

    protected Day1(String day) {
        super(day);
    }

    public static void main(String[] args) {
        new Day1("1");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        for (String row : input) {
            firstList.add(Integer.parseInt(row.split("   ")[0].trim()));
            secondList.add(Integer.parseInt(row.split("   ")[1].trim()));
        }
        firstList.sort((n1, n2) -> n1.compareTo(n2));
        secondList.sort((n1, n2) -> n1.compareTo(n2));
        int sum = 0;
        for (int i = 0; i < firstList.size(); i++) {
            sum += Math.abs(firstList.get(i) - secondList.get(i));
        }
        return String.valueOf(sum);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        ArrayList<Integer> firstList = new ArrayList<>();
        ArrayList<Integer> secondList = new ArrayList<>();
        HashMap<Integer, Integer> freqList = new HashMap<>();
        for (String row : input) {
            firstList.add(Integer.parseInt(row.split("   ")[0].trim()));
            secondList.add(Integer.parseInt(row.split("   ")[1].trim()));
        }
        secondList.forEach(nbr -> {
            freqList.compute(nbr, (k, v) -> (v == null) ? 1 : v + 1);
        });
        int sum = 0;
        for (int nbr : firstList) {
            if (freqList.containsKey(nbr)) {
                sum += (nbr * freqList.get(nbr));
            }
        }
        return String.valueOf(sum);
    }
}
