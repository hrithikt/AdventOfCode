package aoc2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day3Part2 {

    public static void main(String[] args) {
        String inputPath = "input/2023/day3/input.txt";

        System.out.println("sumOfGearRatios: " + getSumOfGearRatios(inputPath));
    }

    private static long getSumOfGearRatios(String inputPath) {
        List<String> inputLines = new ArrayList<>();
        try {
            inputLines = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Exception while reading input file " + e.getLocalizedMessage());
        }

        int length = inputLines.size();
        char[][] input2DArray = new char[length][length];

        for (int i = 0; i < inputLines.size(); i++) {
            input2DArray[i] = inputLines.get(i).toCharArray();
        }

//        System.out.println(Arrays.deepToString(input2DArray));

        long sumOfGearRatios = 0;

        int i = 0;
        while (i < length) {
            int j = 0;
            while (j < length) {
                if (input2DArray[i][j] == '*') {
                    System.out.println("found * - i: " + i + " , j: " + j);
                    Set<Integer> adjacentParts = getAdjacentPartsSet(input2DArray, i, j);
//                    System.out.println("adjacentParts.size: " + adjacentParts.size());
                    if (adjacentParts.size() == 2) {
                        long gearRatio = 1;
                        for (int eachValue : adjacentParts) {
                            gearRatio *= eachValue;
                        }
                        sumOfGearRatios += gearRatio;
                    }
                }

                j++;
            }
            i++;
        }

        return sumOfGearRatios;
    }

    private static Set<Integer> getAdjacentPartsSet(char[][] input2DArray, int i, int j) {
        Set<Integer> adjacentParts = new HashSet<>();
        int length = input2DArray.length;

        if (i > 0 && j > 0 && Character.isDigit(input2DArray[i - 1][j - 1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i - 1, j - 1);
        }
        if (i > 0 && Character.isDigit(input2DArray[i - 1][j])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i - 1, j);
        }
        if (i > 0 && j != length - 1 && Character.isDigit(input2DArray[i-1][j+1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i - 1, j + 1);
        }
        if (j > 0 && Character.isDigit(input2DArray[i][j-1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i, j - 1);
        }
        if (j != length - 1 && Character.isDigit(input2DArray[i][j+1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i, j + 1);
        }
        if (i != length - 1 && j > 0 && Character.isDigit(input2DArray[i + 1][j-1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i + 1, j - 1);
        }
        if (i != length - 1 && Character.isDigit(input2DArray[i + 1][j])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i + 1, j);
        }
        if (i != length - 1 && j != length - 1 && Character.isDigit(input2DArray[i + 1][j+1])) {
            getAdjacentPartEntry(adjacentParts, input2DArray, i + 1, j + 1);
        }

        return adjacentParts;
    }

    private static void getAdjacentPartEntry(Set<Integer> adjacentParts, char[][] inputArray, int i , int j) {
        int startIndex = j;
        while (startIndex > -1 && Character.isDigit(inputArray[i][startIndex])) {
            startIndex--;
        }
        int endIndex = ++startIndex;

        StringBuilder partNumber = new StringBuilder();
        while (endIndex < inputArray.length && Character.isDigit(inputArray[i][endIndex])) {
            partNumber.append(inputArray[i][endIndex]);
            endIndex++;
        }
        System.out.println("partNumber: " + partNumber.toString());
        if (!partNumber.isEmpty()) {
            adjacentParts.add(Integer.valueOf(partNumber.toString()));
        }
    }
}
