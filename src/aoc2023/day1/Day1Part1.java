package aoc2023.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day1 {
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

    // Part - 1
    /*
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
    */

    // Part - 2
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
//        firstDigitIndex = Math.min(firstDigitIndex, searchDigitWords(calibrationText, 0, firstDigitIndex - 1, false));
        char firstDigit = searchDigitWords(calibrationText.substring(0, firstDigitIndex), false);
        firstDigit = firstDigit == 'a' ? charArray[firstDigitIndex] : firstDigit;

        // then check are there any number-words between index lastDigitIndex + 1 and charArray.length - 1, if exist it will be lastDigitIndex
//        lastDigitIndex = Math.max(lastDigitIndex, searchDigitWords(calibrationText, lastDigitIndex + 1, charArray.length - 1, true));
        char lastDigit = searchDigitWords(calibrationText.substring(lastDigitIndex + 1, charArray.length), true);
        lastDigit = lastDigit == 'a' ? charArray[lastDigitIndex] : lastDigit;

//        char firstDigit = charArray[firstDigitIndex];
//        char lastDigit = charArray[lastDigitIndex];

        String resultString = String.valueOf(firstDigit) + lastDigit;
        try {
            return Integer.parseInt(resultString);
        } catch (NumberFormatException exp) {
            System.out.println("Invalid calibrationText: " + calibrationText);
            return 0;
        }
    }

    private static char searchDigitWords(String calibrationText, boolean reverse) {
        // eight23wothree
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
            if (index != -1 && index < firstIndex) {
                firstIndex = index;
                result = entry.getValue();
            }
        }



        return result;
    }

//    private static int searchDigitWords(String calibrationText, int start, int end, boolean reverse) {
//        // eightwothree
//        Map<String, Character> digitWordMap = Map.of(
//                "zero", '0',
//                "one", '1',
//                "two", '2',
//                "three", '3',
//                "four", '4',
//                "five", '5',
//                "six", '6',
//                "seven", '7',
//                "eight", '8',
//                "nine", '9'
//        );
//
//        Map<String, Character> digitWordReverseMap = Map.of(
//                "orez", '0',
//                "eno", '1',
//                "owt", '2',
//                "eerht", '3',
//                "ruof", '4',
//                "evif", '5',
//                "xis", '6',
//                "neves", '7',
//                "thgie", '8',
//                "enin", '9'
//        );
//
//        Map<String, Character> wordMap = reverse ? digitWordReverseMap : digitWordMap;
//        int resultIndex = reverse ? start : end;
//        calibrationText = reverse ? reverseText(calibrationText) : calibrationText;
//
//        for (Map.Entry<String, Character> entry: wordMap.entrySet()) {
//            int index = calibrationText.substring(start, end).indexOf(entry.getKey());
//            if (!reverse && index < resultIndex) {
//                resultIndex = index;
//            } else if (reverse && index > resultIndex) {
//                resultIndex = index;
//            }
//        }
//
//
//        return 0;
//    }

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
