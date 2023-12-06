package com.adventofcode.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Aoc;

public class Day4 extends Aoc {

    private static TreeMap<Integer, Integer> cardDeck;
    private static ArrayList<String> haveList;
    private static ArrayList<String> winningList;

    protected Day4(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day4("src//main//java//com//adventofcode//Day4//input");
    }

    @Override
    protected String part1(ArrayList<String> input) {

        cardDeck = new TreeMap<>();
        haveList = new ArrayList<>();
        winningList = new ArrayList<>();
        int sum = 0;
        for (int i = 1; i <= input.size(); i++) {
            cardDeck.put(i, 1);
        }
        for (String in : input) {
            String regexCard = "(?<= ).*(?=\\:)";
            Pattern patternCard = Pattern.compile(regexCard);
            Matcher matcherCard = patternCard.matcher(in);
            matcherCard.find();
            int card = Integer.parseInt(matcherCard.group().trim());
            String regex = "(?<=\\|).*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(in);
            matcher.find();
            String having = matcher.group();
            String regexWinning = "(?<=:)-*.*(?=\\|)";
            Pattern patternWinning = Pattern.compile(regexWinning);
            Matcher matcherWinning = patternWinning.matcher(in);
            matcherWinning.find();
            String winning = matcherWinning.group();
            for (String s : having.split(" ")) {
                haveList.add(s);
            }
            for (String s : winning.split(" ")) {
                winningList.add(s);
            }
            int currentSum = 0;
            int copyIndex = 0;
            for (String s : haveList) {
                if (winningList.contains(s) && !s.equals("")) {

                    if (currentSum == 0) {
                        currentSum = 1;
                    } else {
                        currentSum = currentSum * 2;
                    }
                    copyIndex++;

                } else {

                }
            }
            int copies = 0;
            if (cardDeck.containsKey(card)) {
                copies = cardDeck.get(card);
            }
            for (int k = 0; k < copies; k++) {

                for (int i = card + 1; i <= card + copyIndex; i++) {
                    Integer count = cardDeck.get(i);
                    if (count == null) {
                        cardDeck.put(i, 1);
                    } else {
                        cardDeck.put(i, count + 1);
                    }
                }
            }

            sum = sum + currentSum;

            haveList.clear();
            winningList.clear();
        }

        return Integer.toString(sum);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : cardDeck.entrySet()) {
            result = result + entry.getValue();
        }

        return Integer.toString(result);
    }
}
