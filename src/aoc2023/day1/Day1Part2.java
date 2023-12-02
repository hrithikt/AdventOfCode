package aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day1Part2 {
    public static void main(String[] args) throws Exception {
        String inputPath = "input/2023/day1/input.txt";

        System.out.println(getSumOfCalibrationValues(inputPath));
    }

    private static Long getSumOfCalibrationValues(String inputPath) {
        List<String> calibrationTexts = new ArrayList<>();
        try {
            calibrationTexts = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Exception while reading input file " + e.getLocalizedMessage());
        }

        long sumOfCalibrationValues = 0L; // Initialize output value

        for (String calibrationText : calibrationTexts) {
//            System.out.println(calibrationText);
            // Extract each calibration value
            int calibrationValue = extractCalibrationValueFromText(calibrationText);
            // Add calibration value to the sum
            sumOfCalibrationValues += calibrationValue;
        }
        return sumOfCalibrationValues;
    }

    private static int extractCalibrationValueFromText(String calibrationText) {
        Map<String, Character> digitWordMap = Map.of(
                "zero", '0',
                "one", '1',
                "two", '2',
                "three", '3',
                "four", '4',
                "five", '5',
                "six", '6',
                "seven", '7',
                "eight", '8',
                "nine", '9'
        );

        char firstDigit = 'a';
        char lastDigit = 'a';


        char[] charArray = calibrationText.toCharArray();
        int textLength = charArray.length;
//        System.out.println("textLength: " + textLength);
        for (int i = 0; i < textLength; i++) {
            if (firstDigit == 'a') {
                if (Character.isDigit(charArray[i])) {
                    firstDigit = charArray[i];
                } else {
                    for (Map.Entry<String, Character> entry : digitWordMap.entrySet()) {
//                        System.out.println("i: " + i + ", Key: " + entry.getKey());
                        if (
                                (i <= textLength - entry.getKey().length()) &&
                                        (entry.getKey().equals(calibrationText.substring(i, i + entry.getKey().length())))
                        ) {
                            firstDigit = entry.getValue();
                        }
                    }
                }
            }

            if (lastDigit == 'a') {
                if (Character.isDigit(charArray[textLength - 1 - i])) {
                    lastDigit = charArray[textLength - 1 - i];
                } else {
                    for (Map.Entry<String, Character> entry : digitWordMap.entrySet()) {
//                        System.out.println("i: " + i + ", Key: " + entry.getKey());
                        if (
                                (i <= textLength - entry.getKey().length()) &&
                                        (entry.getKey().equals(calibrationText.substring(textLength - i - entry.getKey().length() , textLength - i)))
                        ) {
                            lastDigit = entry.getValue();
                        }
                    }
                }
            }

            if (firstDigit != 'a' && lastDigit != 'a') {
                break;
            }
        }

        String resultString = String.valueOf(firstDigit) + lastDigit;
        try {
            return Integer.parseInt(resultString);
        } catch (NumberFormatException exp) {
            System.out.println("Invalid calibrationText: " + calibrationText);
            return 0;
        }
    }
}
