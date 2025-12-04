package com.adventofcode2025.Day4;
import java.util.ArrayList;
import com.Aoc;
 
public class Day4 extends Aoc {
 
    protected Day4(String day, String inputFile) {
        super(day, inputFile);
    }
 
    public static void main(String[] args) {
        new Day4("4","input");
    }
 
    @Override
    protected String part1(ArrayList<String> input) {
        char[][] grid = new char[input.get(0).length()][input.size()];
        int row = 0;
        for(String s : input) {
            char[] lineArray = s.toCharArray();
            System.arraycopy(lineArray, 0, grid[row], 0, grid[row].length);
            row++;
        }
        int result = 0;
 
        for(int y = 0; y < grid.length; y++) {
            for(int x = 0; x < grid[y].length; x++) {
                if(grid[y][x] == '@' && getAdjacentRolls(x, y, grid) < 4) {
                        result++;
                }
            }
            
        }
               
        return String.valueOf(result);
    }
 
    @Override
    protected String part2(ArrayList<String> input) {
        boolean moreToRemove = true;
        int removed = 0;
        char[][] grid = new char[input.get(0).length()][input.size()];
        int row = 0;
        for(String s : input) {
            char[] lineArray = s.toCharArray();
            System.arraycopy(lineArray, 0, grid[row], 0, grid[row].length);
            row++;
        }

        while(moreToRemove) {
            char[][] removeGrid = new char[input.get(0).length()][input.size()];
            int removedPerRound = 0;
            for(int y = 0; y < grid.length; y++) {
                for(int x = 0; x < grid[y].length; x++) { 
                    if(grid[y][x] == '@' && getAdjacentRolls(x, y, grid) < 4) {
                        removed++;
                        removedPerRound++;
                        removeGrid[y][x] = 'X';
                    }
                }
            }

            for(int y = 0; y < grid.length; y++) {
                for(int x = 0; x < grid[y].length; x++) {
                    if(removeGrid[y][x] == 'X') {
                        grid[y][x] = removeGrid[y][x];
                    }
                }
                
            }
            
            if(removedPerRound == 0) {
                moreToRemove = false;
            }
        }
        return String.valueOf(removed);
    }

    public int getAdjacentRolls(int x, int y, char[][] grid) {
        int rollCounter = 0;
        int[][] possibleDirections = new int[][] {
        { 0, 1 }, { 0, -1 }, { 1, 0 },
        { -1, 0 }, { 1, 1 }, { -1, -1 },
        { 1, -1 }, { -1, 1 } };

        for (int[] direction : possibleDirections) {
            if (y + direction[0] >= 0 && y + direction[0] < grid.length && x + direction[1] >= 0
                && x + direction[1] < grid[y + direction[0]].length) {
                if (grid[y + direction[0]][x + direction[1]] == '@') {
                    rollCounter++;
                }

            }
        }
        
        return rollCounter;
    }
}
 
