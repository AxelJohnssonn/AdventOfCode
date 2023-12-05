package com.adventofcode.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File("src//main//java//com//adventofcode//Day5//input.txt"));
            ArrayList<Long> seeds = new ArrayList<>();
            ArrayList<String> lines = new ArrayList<>();

            String firstLine = scan.nextLine();
            
            for (String s : firstLine.split(":")[1].split(" ")) {
                if (!s.equals("")) {
                    seeds.add(Long.parseLong(s.trim()));
                }
            }
            
            Long[] data = new Long[seeds.size()];
            System.out.println(seeds);

            for (int i = 0; i < seeds.size(); i++) {
                data[i] = seeds.get(i);
                System.out.println(data[i]);
            }
            
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }

            boolean converted = false;

            for (int i = 0; i < data.length; i++) {

                for (String line : lines) {

                    if (line.equals("")) {
                        converted = false;
                    }

                    if (!line.equals("") && Character.isDigit(line.charAt(0)) && !converted) {

                        if (data[i] <= Long.parseLong(line.split(" ")[1]) + Long.parseLong(line.split(" ")[2]) - 1
                                && data[i] >= Long.parseLong(line.split(" ")[1])) {
                            data[i] = Long.parseLong(line.split(" ")[0]) - Long.parseLong(line.split(" ")[1]) + data[i];
                            converted = true;
                        }

                    }
                }
            }

            ArrayList<Long> locations = new ArrayList<>();
            for (int i = 0; i < seeds.size(); i++) {
                // data[i] = seeds.get(i);
                System.out.println("Seed " + seeds.get(i) + " -> " + " Location " + data[i]);
                locations.add(data[i]);
                // System.out.println(data[i]);
            }

            locations.sort((o1, o2) -> o1.compareTo(o2));

            System.out.println("Result part1: " + locations.get(0));
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
