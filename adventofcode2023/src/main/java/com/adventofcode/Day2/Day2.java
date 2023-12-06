package com.adventofcode.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.Aoc;

public class Day2 extends Aoc {

    protected Day2(String fileName) {
        super(fileName);
    }

    private static int sum1 = 0;
    private static int sum2 = 0;

    public static void main(String[] args) {
        new Day2("src//main//java//com//adventofcode//Day2//data.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        int gameID = 1;

        for (String data : input) {

            int green = 0;
            int red = 0;
            int blue = 0;

            int currentID = 0;
            boolean checked = false;
            boolean ok = true;

            String[] editedData = data.split(":");
            data = editedData[1];
            // System.out.println("Mitt id: " + gameID + " " + editedData[0]);
            String[] cubesData = data.split(",|;");
            currentID = gameID;

            for (String s : cubesData) {
                System.out.println("game: " + gameID + " | Data" + s);
                char[] dataArray = s.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : dataArray) {

                    if (Character.isDigit(c)) {
                        sb.append(c);
                    }

                    if (Character.toString(c).equals("r") && !checked) {
                        checked = true;
                        red = Integer.parseInt(sb.toString());
                        if (red > 12) {
                            currentID = 0;
                            ok = false;
                            break;
                        }
                    }

                    if (Character.toString(c).equals("b") && !checked) {
                        checked = true;
                        blue = Integer.parseInt(sb.toString());
                        if (blue > 14) {
                            currentID = 0;
                            ok = false;
                            break;
                        }
                    }

                    if (Character.toString(c).equals("g") && !checked) {
                        checked = true;
                        green = Integer.parseInt(sb.toString());
                        if (green > 13) {
                            currentID = 0;
                            ok = false;
                            break;
                        }
                    }
                }
                checked = false;
            }

            if (ok) {
                sum1 = sum1 + currentID;
            }
            gameID++;
            ok = true;
        }
        return Integer.toString(sum1);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        
        for (String data : input) {
            boolean checked = false;
            String[] editedData = data.split(":");
            data = editedData[1];
            int maxRed = 0;
            int maxBlue = 0;
            int maxGreen = 0;
            String[] cubesData = data.split(",|;");
            for (String s : cubesData) {
                char[] dataArray = s.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : dataArray) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                        // System.out.println(sb.toString());
                    }

                    if (Character.toString(c).equals("r") && !checked) {
                        checked = true;
                        if (Integer.parseInt(sb.toString()) > maxRed) {
                            maxRed = Integer.parseInt(sb.toString());
                            break;
                        }
                    }

                    if (Character.toString(c).equals("g") && !checked) {
                        checked = true;
                        if (Integer.parseInt(sb.toString()) > maxGreen) {
                            maxGreen = Integer.parseInt(sb.toString());
                            break;
                        }
                    }

                    if (Character.toString(c).equals("b") && !checked) {
                        checked = true;
                        if (Integer.parseInt(sb.toString()) > maxBlue) {
                            maxBlue = Integer.parseInt(sb.toString());
                            break;
                        }
                    }
                }
                checked = false;

            }
            sum2 = sum2 + maxRed * maxGreen * maxBlue;
        }
        return Integer.toString(sum2);
    }
}
