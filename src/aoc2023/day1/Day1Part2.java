package aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day1Part2 {
    public static void main(String[] args) throws Exception {
//        String inputPath = "input/2023/day1/testInput.txt";
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
        char[] charArray = calibrationText.toCharArray();
        int firstDigitIndex = charArray.length;
        int lastDigitIndex = -1;
        for (int i = 0; i < charArray.length; i++) {
            if (firstDigitIndex == charArray.length && Character.isDigit(charArray[i])) {
                firstDigitIndex = i;
            }

            if (lastDigitIndex == -1 && Character.isDigit(charArray[charArray.length - 1 - i])) {
                lastDigitIndex = charArray.length - 1 - i;
            }

            if (firstDigitIndex != charArray.length && lastDigitIndex != -1) {
                break;
            }
        }
//        System.out.println("firstDigitIndex: " + firstDigitIndex + ", lastDigitIndex: " + lastDigitIndex);
        // now we need to check are there any number-words between index 0 and firstDigitIndex - 1, if exist it will be firstDigitIndex
        char firstDigit = searchDigitWords(calibrationText.substring(0, firstDigitIndex), false);
        firstDigit = firstDigit == 'a' ? charArray[firstDigitIndex] : firstDigit;

        // then check are there any number-words between index lastDigitIndex + 1 and charArray.length - 1, if exist it will be lastDigitIndex
        char lastDigit = searchDigitWords(calibrationText.substring(lastDigitIndex + 1, charArray.length), true);
        lastDigit = lastDigit == 'a' ? charArray[lastDigitIndex] : lastDigit;

        String resultString = String.valueOf(firstDigit) + lastDigit;
        try {
            return Integer.parseInt(resultString);
        } catch (NumberFormatException exp) {
            System.out.println("Invalid calibrationText: " + calibrationText);
            return 0;
        }
    }

    private static char searchDigitWords(String calibrationText, boolean reverse) {
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

        Map<String, Character> digitWordReverseMap = Map.of(
                "orez", '0',
                "eno", '1',
                "owt", '2',
                "eerht", '3',
                "ruof", '4',
                "evif", '5',
                "xis", '6',
                "neves", '7',
                "thgie", '8',
                "enin", '9'
        );

        char result = 'a';

        calibrationText = reverse ? reverseText(calibrationText) : calibrationText;
        Map<String, Character> wordMap = reverse ? digitWordReverseMap : digitWordMap;

        int firstIndex = calibrationText.length();
        for (Map.Entry<String, Character> entry: wordMap.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", index: " + calibrationText.indexOf(entry.getKey()));
            int index = calibrationText.indexOf(entry.getKey());
            if (index == 0) {
                return entry.getValue();
            } else if (index > 0 && index < firstIndex) {
                firstIndex = index;
                result = entry.getValue();
            }
        }

        return result;
    }

    private static String reverseText(String text) {
        char[] charArray = text.toCharArray();

        for (int i = 0, j = charArray.length - 1; i < j; i++, j--) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
        }

        return new String(charArray);
    }
}
