package aoc2023.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3Part1 {

    public static void main(String[] args) {
        String inputPath = "input/2023/day3/input.txt";

        System.out.println("sumOfPartNumbers: " + getSumOfPartNumbers(inputPath));
    }

    private static int getSumOfPartNumbers(String inputPath) {
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

        int sumOfPartNumbers = 0;

        int i = 0;
        while (i < length) {
            int j = 0;
            while (j < length) {
                if (Character.isDigit(input2DArray[i][j])) {
                    StringBuilder partNumber = new StringBuilder();
                    partNumber.append(input2DArray[i][j]);
                    boolean isValid = checkValidityOfCell(input2DArray, i , j, length);
                    j++;
                    while (j < length && Character.isDigit(input2DArray[i][j])) {
                        partNumber.append(input2DArray[i][j]);
                        if (!isValid) {
                            isValid = checkValidityOfCell(input2DArray, i, j, length);
                        }
                        j++;
                    }
                    System.out.println("partNumber: " + partNumber + ", isValid: " + isValid);
                    sumOfPartNumbers = isValid ? sumOfPartNumbers + Integer.parseInt(partNumber.toString()) : sumOfPartNumbers;
                }
                j++;
            }
            i++;
        }

        return sumOfPartNumbers;
    }

    private static boolean checkValidityOfCell(char[][] input2DArray, int i, int j, int length) {
        return (
                    (i > 0 && j > 0 && isSymbol(input2DArray[i-1][j-1])) ||
                    (i > 0 && isSymbol(input2DArray[i-1][j])) ||
                    (i > 0 && j != length - 1 && isSymbol(input2DArray[i-1][j+1])) ||
                    (j > 0 && isSymbol(input2DArray[i][j-1])) ||
                    (j != length - 1 && isSymbol(input2DArray[i][j+1])) ||
                    (i != length - 1 && j > 0 && isSymbol(input2DArray[i + 1][j-1])) ||
                    (i != length - 1 && isSymbol(input2DArray[i + 1][j])) ||
                    (i != length - 1 && j != length - 1 && isSymbol(input2DArray[i + 1][j+1]))
                );
    }

    private static boolean isSymbol(char ch) {
        return (!Character.isDigit(ch) && ch != '.');
    }
}
