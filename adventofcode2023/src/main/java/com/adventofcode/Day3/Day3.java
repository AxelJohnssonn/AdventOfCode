package com.adventofcode.Day3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.awt.Point;

public class Day3 {
    private static int[][] possibleDirections = new int[][] {
        { 0, 1 }, { 0, -1 }, { 1, 0 },
        { -1, 0 }, { 1, 1 }, { -1, -1 },
        { 1, -1 }, { -1, 1 } };

    public static void main(String[] args) {
        try {
            File file = new File("src//main//java//com//adventofcode//Day3//input");

            int rowLength = determSizeRows(file);
            int colLength = determSizeCols(file);

            char[][] charArray = new char[rowLength][colLength];

            Scanner scan = new Scanner(file);
            int row = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                char[] lineArray = line.toCharArray();

                for (int i = 0; i < charArray[row].length; i++) {
                    charArray[row][i] = lineArray[i];
                }
                row++;
            }
            part1(charArray);
            part2(charArray);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void part1(char[][] charArray) {
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
                        //System.out.println("temp: " + tmp);

                        nbr = nbr * 10 + tmp;
                        //System.out.println("nbr: " +nbr);

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
            System.out.println("Resilt part1: " + sum);
    }

    public static void part2(char[][] charArray) {
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
                        //System.out.println("temp: " + tmp);
                        nbr = nbr * 10 + tmp;
                        //System.out.println("nbr: " +nbr);

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

            System.out.println("Result part2: " + sum);
    }

    private static int findNbr(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }

        return -1;
    }

    public static int determSizeRows(File file) {
        try {
            return (int) Files.lines(file.toPath()).count();
        } catch (IOException e) {
            return 0;

        }
    }

    public static int determSizeCols(File file) {
        try {
            Scanner scan = new Scanner(file);
            String cols = scan.nextLine();
            return cols.length();
        } catch (IOException e) {
            return 0;

        }
    }
}
