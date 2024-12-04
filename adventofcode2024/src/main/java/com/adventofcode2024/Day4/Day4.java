package com.adventofcode2024.Day4;

import java.util.ArrayList;

import com.Aoc;

public class Day4 extends Aoc {
    protected Day4(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day4("4");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        char[][] matrix = new char[input.get(0).length()][input.size()];
        int rowIndex = 0;
        int counter = 0;
        for (String s : input) {
            char[] rowArray = s.toCharArray();
            for (int i = 0; i < rowArray.length; i++) {
                matrix[rowIndex][i] = rowArray[i];

            }
            rowIndex++;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (matrix[i][k] == 'X') {
                    // left
                    if (k >= 3 && matrix[i][k - 1] == 'M' && matrix[i][k - 2] == 'A'
                            && matrix[i][k - 3] == 'S') {
                        counter++;
                    }
                    // right
                    if (k < matrix[0].length - 3 && matrix[i][k + 1] == 'M' && matrix[i][k + 2] == 'A'
                            && matrix[i][k + 3] == 'S') {
                        counter++;
                    }
                    // up
                    if (i >= 3 && matrix[i - 1][k] == 'M' && matrix[i - 2][k] == 'A'
                            && matrix[i - 3][k] == 'S') {
                        counter++;
                    }
                    // down
                    if (i < matrix.length - 3 && matrix[i + 1][k] == 'M' && matrix[i + 2][k] == 'A'
                            && matrix[i + 3][k] == 'S') {
                        counter++;
                    }
                    // upleft
                    if (i >= 3 && k >= 3 && matrix[i - 1][k - 1] == 'M' && matrix[i - 2][k - 2] == 'A'
                            && matrix[i - 3][k - 3] == 'S') {
                        counter++;
                    }
                    // upright
                    if (i >= 3 && k < matrix[0].length - 3 && matrix[i - 1][k + 1] == 'M' && matrix[i - 2][k + 2] == 'A'
                            && matrix[i - 3][k + 3] == 'S') {
                        counter++;
                    }
                    // downright
                    if (i < matrix.length - 3 && k < matrix[0].length - 3 && matrix[i + 1][k + 1] == 'M'
                            && matrix[i + 2][k + 2] == 'A'
                            && matrix[i + 3][k + 3] == 'S') {
                        counter++;
                    }
                    // downleft
                    if (i < matrix.length - 3 && k >= 3 && matrix[i + 1][k - 1] == 'M' && matrix[i + 2][k - 2] == 'A'
                            && matrix[i + 3][k - 3] == 'S') {
                        counter++;
                    }
                }

            }
        }
        return String.valueOf(counter);
    }

    @Override
    protected String part2(ArrayList<String> input) {
        char[][] matrix = new char[input.get(0).length()][input.size()];
        int rowIndex = 0;
        int counter = 0;
        for (String s : input) {
            char[] rowArray = s.toCharArray();
            for (int i = 0; i < rowArray.length; i++) {
                matrix[rowIndex][i] = rowArray[i];

            }
            rowIndex++;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (matrix[i][k] == 'A' && i >= 1 && i < matrix.length - 1 && k >= 1 && k < matrix[0].length - 1) {
                    String leftToRight = Character.toString(matrix[i - 1][k - 1]) + Character.toString('A')
                            + Character.toString(matrix[i + 1][k + 1]);
                    String rightToLeft = Character.toString(matrix[i - 1][k + 1]) + Character.toString('A')
                            + Character.toString(matrix[i + 1][k - 1]);
                    if ((leftToRight.equals("MAS") || leftToRight.equals("SAM"))
                            && (rightToLeft.equals("MAS") || rightToLeft.equals("SAM"))) {
                        counter++;
                    }
                }

            }
        }
        return String.valueOf(counter);
    }
}
