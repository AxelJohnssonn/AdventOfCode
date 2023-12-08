package com.adventofcode.Day7;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;
import com.Aoc;

public class Day7 extends Aoc {
   // private TreeMap<String, Integer> values;

    protected Day7(String fileName) {
        super(fileName);
    }

    public static void main(String[] args) {
        new Day7("src//main//java//com//adventofcode//Day7//input.txt");
    }

    @Override
    protected String part1(ArrayList<String> input) {
        
        TreeMap<String, Integer> values = new TreeMap<>();
        values.put("A", 14);
        values.put("K", 13);
        values.put("Q", 12);
        values.put("J", 11);
        values.put("T", 10);
        for (int i = 0; i < 10; i++) {
            values.put(Integer.toString(i), i);
        }

        ArrayList<String> fiveKind = new ArrayList<>();
        ArrayList<String> fourKind = new ArrayList<>();
        ArrayList<String> full = new ArrayList<>();
        ArrayList<String> threeKind = new ArrayList<>();
        ArrayList<String> twoPair = new ArrayList<>();
        ArrayList<String> pair = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String hand = line.split(" ")[0];
            int[] v = getValue(hand,values);

            if (getType(v,false).equals("1|5kind")) {
                fiveKind.add(line);
            } else if (getType(v,false).equals("2|4kind")) {
                fourKind.add(line);
            } else if (getType(v,false).equals("3|full")) {
                full.add(line);
            } else if (getType(v,false).equals("4|3kind")) {
                threeKind.add(line);
            } else if (getType(v,false).equals("5|2pair")) {
                twoPair.add(line);
            } else if (getType(v,false).equals("6|pair")) {
                pair.add(line);
            } else {
                high.add(line);
            }
        }

        fiveKind = sort(fiveKind,values);
        fourKind = sort(fourKind,values);
        full = sort(full,values);
        threeKind = sort(threeKind,values);
        twoPair = sort(twoPair,values);
        pair = sort(pair,values);
        high = sort(high,values);

        ArrayList<String> all = new ArrayList<>();
        high.forEach(s -> all.add(s));
        pair.forEach(s -> all.add(s));
        twoPair.forEach(s -> all.add(s));
        threeKind.forEach(s -> all.add(s));
        full.forEach(s -> all.add(s));
        fourKind.forEach(s -> all.add(s));
        fiveKind.forEach(s -> all.add(s));

        int sum = 0;
        for (int i = 0; i < all.size(); i++) {
            sum = sum + (Integer.parseInt(all.get(i).split(" ")[1]) * (i + 1));
        }
        return Integer.toString(sum);
        
    }
    
    public int[] getValue(String card, TreeMap<String, Integer> values) {
        int[] v = new int[5];
        char[] cArray = card.toCharArray();
        for (int k = 0; k < cArray.length; k++) {
            char c = cArray[k];
            v[k] = values.get(Character.toString(c));
        }
        return v;
    }
    

    public ArrayList<String> sort(ArrayList<String> list, TreeMap<String, Integer> values) {

        String[] listArray = list.toArray(new String[list.size()]);
        
        for (int i = 0; i < listArray.length; i++) {
            for (int x = 0; x < listArray.length - 1; x++) {
                if (getValue(listArray[x].split(" ")[0],values)[0] > getValue(listArray[x + 1].split(" ")[0],values)[0]) {
                    String temp = listArray[x];
                    listArray[x] = listArray[x + 1];
                    listArray[x + 1] = temp;
                } else if (getValue(listArray[x].split(" ")[0],values)[0] == getValue(listArray[x + 1].split(" ")[0],values)[0]) {
                    int[] first = getValue(listArray[x].split(" ")[0],values);
                    int[] last = getValue(listArray[x + 1].split(" ")[0],values);
                    for (int k = 1; k < 5; k++) {
                        if (first[k] > last[k]) {
                            String temp = listArray[x];
                            listArray[x] = listArray[x + 1];
                            listArray[x + 1] = temp;
                            break;
                        } else if (first[k] < last[k]) {
                            break;
                        }
                    }
                }
            }
        }

        ArrayList<String> out = new ArrayList<>();
        for (String s : listArray) {
            out.add(s);
        }
        return out;
    }

    public String getType(int[] values, boolean part2) {
        String type = "";
        int likeCounter = 0;
        int prev = 999;
        int jCounter = 0;
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            if(values[i] == 1 && part2) {
                jCounter++;
                continue;
            }else {
                if (prev == 999) {
                    prev = values[0];
                } else {
                    if (prev == values[i]) {
                        likeCounter++;
                    }
                    prev = values[i];
                }
            }

        }

        //System.out.println(jCounter);
        if (likeCounter == 1) {
            type = "6|pair";
        }

        if (likeCounter == 2) {
            if (values[0] == values[1]) {
                if (values[1] == values[2]) {
                    type = "4|3kind";
                } else {
                    type = "5|2pair";
                }
            } else if (values[1] == values[2]) {
                if (values[2] == values[3]) {
                    type = "4|3kind";
                } else {
                    type = "5|2pair";
                }
            } else if (values[2] == values[3]) {
                type = "4|3kind";
            }
        }

        if (likeCounter == 3) {
            if (values[1] == values[2] && values[2] == values[3]) {
                type = "2|4kind";
            } else {
                type = "3|full";
            }
        }

        if (likeCounter == 4) {
            type = "1|5kind";
        }
        if(likeCounter == 0) {
            type = "7|high";
        }


        if(part2) {
            //System.out.println("HEJHEJ " + jCounter + " " + type);
            if (type.equals("7|high")) {
                if (jCounter == 1) {
                    type = "6|pair";
                } else if (jCounter == 2) {
                    type = "4|3kind";
                } else if (jCounter == 3) {
                    type = "2|4kind";
                } else if (jCounter == 4) {
                    type = "1|5kind";
                } else if (jCounter == 5) {
                    type = "1|5kind";
                }
            } else if (type.equals("6|pair")) {
                if (jCounter == 1) {
                    type = "4|3kind";
                } else if (jCounter == 2) {
                    type = "2|4kind";
                } else if (jCounter == 3) {
                    type = "1|5kind";
                }
            } else if (type.equals("5|2pair")) {
                if (jCounter == 1) {
                    type = "3|full";
                }
            } else if (type.equals("4|3kind")) {
                if (jCounter == 1) {
                    type = "2|4kind";
                } else if (jCounter == 2) {
                    type = "1|5kind";
                }
            } else if (type.equals("2|4kind")) {
                if (jCounter == 1) {
                    type = "1|5kind";
                }
            }
        }
        //System.out.print(jCounter);
        return type;
    }


    @Override
    protected String part2(ArrayList<String> input) {
        TreeMap<String, Integer> values = new TreeMap<>();
        values.put("A", 14);
        values.put("K", 13);
        values.put("Q", 12);
        values.put("J", 1);
        values.put("T", 10);
        for (int i = 0; i < 10; i++) {
            values.put(Integer.toString(i), i);
        }

        ArrayList<String> fiveKind = new ArrayList<>();
        ArrayList<String> fourKind = new ArrayList<>();
        ArrayList<String> full = new ArrayList<>();
        ArrayList<String> threeKind = new ArrayList<>();
        ArrayList<String> twoPair = new ArrayList<>();
        ArrayList<String> pair = new ArrayList<>();
        ArrayList<String> high = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            String hand = line.split(" ")[0];
            int[] v = getValue(hand,values);

            if (getType(v,true).equals("1|5kind")) {
                fiveKind.add(line);
                //if(line.contains("J")) System.out.println(line + " =  5kind");
            } else if (getType(v,true).equals("2|4kind")) {
                fourKind.add(line);
               // if(line.contains("J"))System.out.println(line + " =  4kind");
            } else if (getType(v,true).equals("3|full")) {
                full.add(line);
               // if(line.contains("J"))System.out.println(line + " =  full");
            } else if (getType(v,true).equals("4|3kind")) {
                threeKind.add(line);
                //if(line.contains("J"))System.out.println(line + " =  3kind");
            } else if (getType(v,true).equals("5|2pair")) {
                twoPair.add(line);
                //if(line.contains("J"))System.out.println(line + " =  2pair");
            } else if (getType(v,true).equals("6|pair")) {
                pair.add(line);
               // if(line.contains("J"))System.out.println(line + " =  pair");
            } else {
                high.add(line);
              //  if(line.contains("J"))System.out.println(line + " =  high");
            }
        }

        fiveKind = sort(fiveKind,values);
        fourKind = sort(fourKind,values);
        full = sort(full,values);
        threeKind = sort(threeKind,values);
        twoPair = sort(twoPair,values);
        pair = sort(pair,values);
        high = sort(high,values);

        ArrayList<String> all = new ArrayList<>();
        high.forEach(s -> all.add(s));
        pair.forEach(s -> all.add(s));
        twoPair.forEach(s -> all.add(s));
        threeKind.forEach(s -> all.add(s));
        full.forEach(s -> all.add(s));
        fourKind.forEach(s -> all.add(s));
        fiveKind.forEach(s -> all.add(s));

        int sum = 0;
        for (int i = 0; i < all.size(); i++) {
            sum = sum + (Integer.parseInt(all.get(i).split(" ")[1]) * (i + 1));
        }
        return Integer.toString(sum);
    }


}
