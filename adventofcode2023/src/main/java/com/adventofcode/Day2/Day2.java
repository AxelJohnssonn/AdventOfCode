package com.adventofcode.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

    private int gameID = 1;
    public static void main(String[] args) {
        int green = 0;
        int red = 0;
        int blue = 0;
        int sum = 0;

        int gameID = 1;
        int currentID = 0;
        boolean checked = false;
        boolean ok = true;

        try {
            File file = new File("AdventOfCode2023//adventofcode2023//src//main//java//com//adventofcode//Day2//data.txt");
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] editedData = data.split(":");
                data = editedData[1];
                //System.out.println("Mitt id: " + gameID + " " + editedData[0]);
                int maxRed = 0;
                int maxBlue = 0;
                int maxGreen = 0;
                String[] cubesData = data.split(",|;");
                currentID = gameID;
                for(String s : cubesData) {
                    char[] dataArray = s.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (char c : dataArray) {
                        if (Character.isDigit(c)) {
                            sb.append(c);
                            System.out.println(sb.toString());
                        }

                        if (Character.toString(c).equals("r")&& !checked) {
                            checked = true;
                            if(Integer.parseInt(sb.toString()) > maxRed) {
                                maxRed = Integer.parseInt(sb.toString());
                                break;
                            }
                        }

                        if (Character.toString(c).equals("g")&& !checked) {
                            checked = true;
                            if(Integer.parseInt(sb.toString()) > maxGreen) {
                                maxGreen = Integer.parseInt(sb.toString());
                                break;
                            }
                        }

                        if (Character.toString(c).equals("b")&& !checked) {
                            checked = true;
                            if(Integer.parseInt(sb.toString()) > maxBlue) {
                                maxBlue = Integer.parseInt(sb.toString());
                                break;
                            }
                        }
                    }
                    checked = false;
                
                }
                sum = sum + maxRed*maxGreen*maxBlue;

                System.out.println("game" + gameID + " sum " + maxRed*maxGreen*maxBlue + " r" + maxRed+ " g" + maxGreen+ " b" + maxBlue);
                gameID++;
                //PART 1
                /* 
                for (String s : cubesData) {
                    System.out.println("game: " + gameID + " | Data" + s);
                    char[] dataArray = s.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    for (char c : dataArray) {
                        if (Character.isDigit(c)) {
                            sb.append(c);
                            //System.out.println(sb.toString());
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

                        if (Character.toString(c).equals("b")&& !checked) {
                            checked = true;
                            blue = Integer.parseInt(sb.toString());
                            if (blue > 14) {
                                currentID = 0;
                                ok = false;
                                break;
                            }
                        }

                        if (Character.toString(c).equals("g")&& !checked) {
                            checked = true;
                            green = Integer.parseInt(sb.toString());
                            if (green > 15) {
                                currentID = 0;
                                ok = false;
                                break;
                            }
                        }
                    }
                    checked = false;
                }
                
                if(ok) {
                    sum = sum + currentID;
                }
                */
                //System.out.println(cubesData);
                System.out.println("Game" + gameID + " Nuvarande sum: " + sum + " Blev godkänd? " + ok + currentID +"\n");
                gameID++;
                ok = true;
                
            }
            
            System.out.println(sum);
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
