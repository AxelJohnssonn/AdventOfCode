package com.adventofcode.Day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Aoc;

public class Day1 extends Aoc{
    protected Day1(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day1("src//main//java//com//adventofcode//Day1//input.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        int sum = 0;
        TreeMap<Integer, String> digitMap = new TreeMap<>();
        
        for (String line : input) {

            char[] charArray = line.toCharArray();
            String firstDigit = "";
            String lastDigit = "";
            for (int i = 0; i < charArray.length; i++) {
                if (Character.isDigit(charArray[i])) {
                    digitMap.put(i, Character.toString(charArray[i]));
                    if (firstDigit.equals("")) {
                        firstDigit = Character.toString(charArray[i]);
                        //System.out.print("First digit \"" + charArray[i] + "\" at index: " + i + "\n");
                    } else {
                        lastDigit = Character.toString(charArray[i]);
                        //System.out.print("last digit \"" + charArray[i] + "\" at index: " + i + "\n");
                    }
                }
            }

            if (!firstDigit.equals("")) {
                if (lastDigit.equals("")) {
                    sum = sum + Integer.parseInt(firstDigit + firstDigit);
                } else {
                    sum = sum + Integer.parseInt(firstDigit + lastDigit);
                }
            }
        }

        return Integer.toString(sum);
    }

    protected String part2(ArrayList<String> input) {
        Map<String, String> nbrs = new HashMap<>();
        nbrs.put("one", "1");
        nbrs.put("two", "2");
        nbrs.put("three", "3");
        nbrs.put("four", "4");
        nbrs.put("five", "5");
        nbrs.put("six", "6");
        nbrs.put("seven", "7");
        nbrs.put("eight", "8");
        nbrs.put("nine", "9");

        Map<String, String> reverseNbrs = new HashMap<>();

        reverseNbrs.put("eno", "1");
        reverseNbrs.put("owt", "2");
        reverseNbrs.put("eerht", "3");
        reverseNbrs.put("ruof", "4");
        reverseNbrs.put("evif", "5");
        reverseNbrs.put("xis", "6");
        reverseNbrs.put("neves", "7");
        reverseNbrs.put("thgie", "8");
        reverseNbrs.put("enin", "9");

        int lineCounter = 1;
        int sum = 0;

        TreeMap<Integer, String> wordMap = new TreeMap<>();
        TreeMap<Integer, String> digitMap = new TreeMap<>();

        for (String line : input) {
                //System.out.println("Line: " + lineCounter + " " + line);
                String regex = "(one|two|three|four|five|six|seven|eight|nine)";
                Pattern pattern = Pattern.compile(regex);
                Matcher Matcher = pattern.matcher(line);

                if (Matcher.find()) {
                   // System.out.print("First Word \"" + Matcher.group() + "\" at index: " + Matcher.start() + "\n");
                    wordMap.put(Matcher.start(), nbrs.get(Matcher.group()));

                }

                String regexReverse = "(enin|thgie|neves|xis|evif|ruof|eerht|owt|eno)";

                StringBuilder reversedString = new StringBuilder(line).reverse();
                // System.out.println(reversedString.toString());
                Pattern patternEnd = Pattern.compile(regexReverse);
                Matcher MatcherEnd = patternEnd.matcher(reversedString.toString());

                if (MatcherEnd.find()) {
                    String correctNbr = new StringBuilder(MatcherEnd.group()).reverse().toString();
                    //System.out.print("Last Word \"" + correctNbr + "\" at index: " + (line.length() - MatcherEnd.start()) + "\n");
                    wordMap.put(line.length() - MatcherEnd.start() - 1, nbrs.get(correctNbr));
                }

                String first = "";
                String last = "";

                char[] charArray = line.toCharArray();
                String firstDigit = "";
                for (int i = 0; i < charArray.length; i++) {
                    if (Character.isDigit(charArray[i])) {
                        digitMap.put(i, Character.toString(charArray[i]));
                        if (firstDigit.equals("")) {
                            firstDigit = Character.toString(charArray[i]);
                            //System.out.print("First digit \"" + charArray[i] + "\" at index: " + i + "\n");
                        } 
                    }
                }

                if (digitMap.isEmpty()) {
                    first = wordMap.get(wordMap.firstKey());
                    last = wordMap.get(wordMap.lastKey());
                } else if (wordMap.isEmpty()) {
                    first = digitMap.get(digitMap.firstKey());
                    last = digitMap.get(digitMap.lastKey());
                } else {

                    if (digitMap.firstKey() < wordMap.firstKey()) {
                        first = digitMap.get(digitMap.firstKey());

                    } else {
                        first = wordMap.get(wordMap.firstKey());

                    }

                    if (digitMap.lastKey() > wordMap.lastKey()) {

                        last = digitMap.get(digitMap.lastKey());
                    } else {
                        last = wordMap.get(wordMap.lastKey());

                    }
                }

                if (first == null && last != null) {
                    first = last;
                } else if (first != null && last == null) {
                    last = first;
                }

                lineCounter++;

                wordMap.clear();
                digitMap.clear();
                sum = sum + Integer.parseInt(first + last);
            }
            return Integer.toString(sum);
    }
}
