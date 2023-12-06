package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Aoc {
    	protected Aoc(String fileName) {
        System.out.println("Starting...");
        final ArrayList<String> input = getList(fileName);

		Instant start = Instant.now();
		final String output1 = part1(input);
		Instant end = Instant.now();
        System.out.println("---------------------------------------------------------");
		System.out.println("Result part 1: " + output1);
		System.out.println("Took: " + Duration.between(start, end).toMillis() + " ms.");
		System.out.println("---------------------------------------------------------\n");
		start = Instant.now();
		final String output2 = part2(input);
		end = Instant.now();
        System.out.println("---------------------------------------------------------");
		System.out.println("Result part 2: " + output2);
		System.out.println("Took: " + Duration.between(start, end).toMillis() + " ms.");
        System.out.println("---------------------------------------------------------\n");
        
        System.out.println("Finished.");
	}
    
    protected abstract String part1(ArrayList<String> input);

    protected abstract String part2(ArrayList<String> input);

    private ArrayList<String> getList(final String fileName) {
        ArrayList<String> list = new ArrayList<>();
        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));
            while(scan.hasNextLine()) {
                list.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

        return list;
    }
}
