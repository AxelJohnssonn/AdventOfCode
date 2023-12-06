package com.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) {
        try {
            File file = new File("src//main//java//com//Day6//input.txt");
            Scanner scan;
            scan = new Scanner(file);
            ArrayList<Integer> timeList = new ArrayList<>();
            ArrayList<Integer> distanceList = new ArrayList<>();
            String totTime = "";
            String totDisctance = "";

            for (String s : scan.nextLine().split(": ")[1].trim().split(" ")) {
                if (!s.isEmpty()) {
                    timeList.add(Integer.parseInt(s));
                    totTime = totTime + s;
                }
            }

            for (String s : scan.nextLine().split(": ")[1].trim().split(" ")) {
                if (!s.isEmpty()) {
                    distanceList.add(Integer.parseInt(s));
                    totDisctance = totDisctance + s;
                }
            }
            
            part1(timeList, distanceList);
            part2(Long.parseLong(totTime), Long.parseLong(totDisctance));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void part1(ArrayList<Integer> timeList, ArrayList<Integer> distanceList) {
         int winCounter = 0;
            int res = 1;
            for (int k = 0; k < timeList.size(); k++) {
                for (int i = 0; i < timeList.get(k); i++) {
                    int time = timeList.get(k) - i;
                    int currentTravel = 0;
                    int speed = i;
                    currentTravel = time * speed;
                    if (currentTravel > distanceList.get(k)) {
                        winCounter++;
                    }
                }
                res = res * winCounter;
                winCounter = 0;
            }

            System.out.println("Result part1: " + res);
    }

    private static void part2(long totTime, long totDisctance) {
        int winCounter = 0;
        for (int i = 0; i < totTime; i++) {
            Long time =  totTime - i;
            Long currentTravel = (long) 0;
            Long speed = (long) i;
            currentTravel = time * speed;
            if (currentTravel > totDisctance) {
                winCounter++;
            }

        }
        System.out.println("Result part2: " + winCounter);
    }
}
