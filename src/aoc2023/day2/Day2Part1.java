package aoc2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Part1 {

    private static final int maxRed = 12;
    private static final int maxGreen = 13;
    private static final int maxBlue = 14;

    public static void main(String[] args) {
        String inputPath = "input/2023/day2/input.txt";

        System.out.println(getSumOfPossibleGameIds(inputPath));
    }

    private static int getSumOfPossibleGameIds(String inputPath) {
        int sumOfPossibleGameIds = 0;
        List<String> inputData = new ArrayList<>();

        // Read input data
        try {
            inputData = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Exception while reading input file " + e.getLocalizedMessage());
        }

        for (String eachGame : inputData) {
            boolean isValidGame = true;
            int id =Integer.parseInt(eachGame.split(":")[0].split(" ")[1]);

            String[] drawDatas = eachGame.split(":")[1].split("; ");
            List<String> colorDatas = new ArrayList<>();
            Arrays.stream(drawDatas).forEach(eachDrawData -> {
                String[] perDrayColorDatas = eachDrawData.split(",");
                colorDatas.addAll(List.of(perDrayColorDatas));
            });

            colorDatas.replaceAll(String::trim);
            for (String eachColorData: colorDatas) {
                int count = Integer.parseInt(eachColorData.split(" ")[0]);
                String color = eachColorData.split(" ")[1].trim();

                switch (color) {
                    case "red":
                        isValidGame = count <= maxRed;
                        break;
                    case "blue":
                        isValidGame = count <= maxBlue;
                        break;
                    case "green":
                        isValidGame = count <= maxGreen;
                        break;
                    default:
                        break;
                }

                if (!isValidGame) {
                    break;
                }
            }


            // if Valid Game add the id to the result
            sumOfPossibleGameIds = isValidGame ? sumOfPossibleGameIds + id : sumOfPossibleGameIds;

            System.out.println("id: " + id + ", isValid: " + isValidGame);
        }

        return sumOfPossibleGameIds;
    }
}
