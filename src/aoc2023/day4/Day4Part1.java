package aoc2023.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day4Part1 {

    public static void main(String[] args) {
        String inputPath = "input/2023/day4/input.txt";
//        String inputPath = "input/2023/day4/testInput.txt";

        System.out.println("sumOfPartNumbers: " + getTotalPoints(inputPath));
    }

    private static int getTotalPoints(String inputPath) {
        List<String> inputs = new ArrayList<>();
        try {
            inputs = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Exception while reading input file " + e.getLocalizedMessage());
        }

        int totalPoints = 0;

        for (String inputLine : inputs) {

            String winningString = inputLine.split(":")[1].split("\\|")[0];
            String myNumbersString = inputLine.split(":")[1].split("\\|")[1];

            List<Integer> winningNumbers = Arrays.stream(winningString.trim().split(" ")).
                    map(String::trim).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            List<Integer> myNumbers = Arrays.stream(myNumbersString.trim().split(" ")).
                    map(String::trim).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();

            int numberOfMatches = 0;
            for (Integer winningNum : winningNumbers) {
                numberOfMatches += Collections.frequency(myNumbers, winningNum);
            }

            int pointsForEachCard =  numberOfMatches == 0 ? 0 : (int) Math.pow(2, numberOfMatches - 1);

//            System.out.println(inputLine.split(":")[0].trim() + " : " + pointsForEachCard);
            totalPoints += pointsForEachCard;
        }

        return totalPoints;
    }
}
