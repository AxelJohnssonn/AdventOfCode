package adventofcode2023.src.main.java.com.adventofcode.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File file = new File(
                "C://Users//axeljoh//Documents//AdventOfCode2023//AdventOfCode2023//adventofcode2023//src//main//java//com//adventofcode//Day3//example");
        int rowLength = determSizeRows(file);
        int colLength = determSizeCols(file);

        String[][] map = new String[rowLength][colLength];

        Scanner scan;
        try {
            scan = new Scanner(file);
            int row = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                char[] lineArray = line.toCharArray();

                for (int i = 0; i < map[row].length; i++) {
                    map[row][i] = Character.toString(lineArray[i]);
                }
                row++;
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        
        boolean ok = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[i].length; k++) {
                char c = map[i][k].toCharArray()[0];
                if(Character.isDigit(c)) {
                    sb.append(c);
                    if(checkAdjecent(map)) {
                        ok = true;
                    }
                }else {
                    if(ok){
                        System.out.println(sb.toString());
                        sb.delete(0, sb.length());
                    }
                    //sb = new StringBuilder();
                }
                //System.out.println(c);
            }
            ok = false;
        }

    }

    public static boolean checkAdjecent(String[][] map) {
        return true;
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
