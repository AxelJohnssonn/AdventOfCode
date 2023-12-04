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

public class Day4 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        TreeMap<Integer, Integer> cardDeck = new TreeMap<>();
        ArrayList<String> haveList = new ArrayList<>();
        ArrayList<String> winningList = new ArrayList<>();
        int sum = 0;
        
        File file = new File("C://Users//axeljoh//Documents//AdventOfCode2023//AdventOfCode2023//adventofcode2023//src//main//java//com//adventofcode//Day4//input");
        
        Scanner scan;
        try {
            scan = new Scanner(file);
            //File file = new FileReader(null)
            int nbrOfCards = 0;

            while(scan.hasNextLine()) {
                scan.nextLine();
                nbrOfCards++;
            }

            System.out.println("Nbr of cards " + nbrOfCards);
            for(int i = 1; i <= nbrOfCards; i++) {
                cardDeck.put(i,1);
            }

            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String in = scan.nextLine();

                String regexCard = "(?<= ).*(?=\\:)";

                Pattern patternCard = Pattern.compile(regexCard);
                Matcher matcherCard = patternCard.matcher(in);

                matcherCard.find();
                int card = Integer.parseInt(matcherCard.group().trim());

                

                // System.out.println(card);
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
                // double i = 0;
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

                
                //cardDeck.put(1, 1);

                int copies = 0;

                if(cardDeck.containsKey(card)) {
                    copies = cardDeck.get(card);
                }

                for(int k = 0; k < copies; k++) {

                    for (int i = card+1; i <= card + copyIndex; i++) {
                        //System.out.println("Copies" + i);
                        //System.out.println("Card: " + card + " Copies: " + cardDeck.get(card));

                        Integer count = cardDeck.get(i);
                        if (count == null) {
                            cardDeck.put(i, 1);
                        } else {
                            cardDeck.put(i, count + 1);
                        }
                    }
                }

                
                
                //System.out.println("Copies " + copyIndex + " card: " + card);
                sum = sum + currentSum;

                haveList.clear();
                winningList.clear();
            }
            long finish = System.currentTimeMillis();

            System.out.println("Result part1: " + sum);
            int result = 0;

            for(Map.Entry<Integer,Integer> entry: cardDeck.entrySet()) {
                //System.out.println(entry.getValue());
                result = result + entry.getValue();
            }
            long timeElapsed = finish - start;

            System.out.println("Result part2: " + result);
            System.out.println("Time to complete: " + timeElapsed + " ms");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
