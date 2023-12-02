package aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day1Part1 {
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
        char firstDigit = 'a';
        char lastDigit = 'a';
        char[] charArray = calibrationText.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (firstDigit == 'a' && Character.isDigit(charArray[i])) {
                firstDigit = charArray[i];
            }

            if (lastDigit == 'a' && Character.isDigit(charArray[charArray.length - 1 - i])) {
                lastDigit = charArray[charArray.length - 1 - i];
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
