package com.adventofcode.Day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.Aoc;

import java.awt.Point;

public class Day3 extends Aoc{

    protected Day3(String fileName) {
        super(fileName);
    }

    private static int[][] possibleDirections = new int[][] {
        { 0, 1 }, { 0, -1 }, { 1, 0 },
        { -1, 0 }, { 1, 1 }, { -1, -1 },
        { 1, -1 }, { -1, 1 } };

    public static void main(String[] args) {
        new Day3("src//main//java//com//adventofcode//Day3//input");
    }

    @Override
    protected String part1(final ArrayList<String> input) {
        char[][] charArray = new char[determSizeRows(input)][determSizeCols(input)];

            int row = 0;
            for(String s : input) {
                String line = s;
                char[] lineArray = line.toCharArray();

                for (int i = 0; i < charArray[row].length; i++) {
                    charArray[row][i] = lineArray[i];
                }
                row++;
            }
        int sum = 0;
        for (int i = 0; i < charArray.length; i++) {
                int nbr = 0;
                boolean isnbr = false;
                for (int j = 0; j < charArray[i].length; j++) {
                    int tmp = findNbr(charArray[i][j]);
                    if (tmp == -1) {
                        if (isnbr) {
                            sum += nbr;
                            isnbr = false;
                        }
                        nbr = 0;
                    } else {
                        nbr = nbr * 10 + tmp;
                        for (int[] direction : possibleDirections) {

                            if (i + direction[0] >= 0 && i + direction[0] < charArray.length && j + direction[1] >= 0
                                    && j + direction[1] < charArray[i + direction[0]].length) {
                                if (charArray[i + direction[0]][j + direction[1]] != '.'
                                        && findNbr(charArray[i + direction[0]][j + direction[1]]) == -1)
                                    isnbr = true;
                            }
                        }
                    }
                }
                if (isnbr) {
                    sum = sum + nbr;
                }
            }
            return Integer.toString(sum);
    }

    @Override
    protected String part2(final ArrayList<String> input) {
        char[][] charArray = new char[determSizeRows(input)][determSizeCols(input)];

            int row = 0;
            for(String s : input) {
                String line = s;
                char[] lineArray = line.toCharArray();

                for (int i = 0; i < charArray[row].length; i++) {
                    charArray[row][i] = lineArray[i];
                }
                row++;
            }
        int sum = 0;
        Map<Point, Integer> map = new HashMap<>();
        Map<Point, Integer> counts = new HashMap<>();

        for (int i = 0; i < charArray.length; i++) {
                Point gearCoord = new Point(-1, -1);
                boolean isGear = false;
                int nbr = 0;
                for (int j = 0; j < charArray[i].length; j++) {
                    int tmp = findNbr(charArray[i][j]);
                    if (tmp == -1) {
                        if (isGear) {
                            map.put(gearCoord, map.getOrDefault(gearCoord, 1) * nbr);
                            counts.put(gearCoord, counts.getOrDefault(gearCoord, 0) + 1);
                            gearCoord = new Point(-1, -1);
                            isGear = false;
                        }
                        nbr = 0;
                    } else {
                        nbr = nbr * 10 + tmp;
                        for (int[] directions : possibleDirections) {

                            if (i + directions[0] >= 0 && i + directions[0] < charArray.length && j + directions[1] >= 0
                                    && j + directions[1] < charArray[i + directions[0]].length) {
                                if (!isGear && charArray[i + directions[0]][j + directions[1]] == '*') {
                                    gearCoord = new Point(i + directions[0], j + directions[1]);
                                    isGear = true;
                                }
                            }
                        }
                    }
                }

                if (isGear) {
                    map.put(gearCoord, map.getOrDefault(gearCoord, 1) * nbr);
                    counts.put(gearCoord, counts.getOrDefault(gearCoord, 0) + 1);
                }
            }
            for (Point p : map.keySet()) {
                sum = sum + (counts.get(p) == 2 ? map.get(p) : 0);
            }
            return Integer.toString(sum);
    }

    private static int findNbr(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }

        return -1;
    }

    public static int determSizeRows(ArrayList<String> input) {
        try {
            return input.size();
        } catch (Exception e) {
            return 0;

        }
    }

    public static int determSizeCols(ArrayList<String> input) {
        try {
            
            return input.get(0).length();
        } catch (Exception e) {
            return 0;

        }
    }
}
