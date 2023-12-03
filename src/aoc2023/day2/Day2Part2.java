package aoc2023.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Part2 {

    public static void main(String[] args) {
        String inputPath = "input/2023/day2/input.txt";

        System.out.println(getSumOfPowerOfAllGames(inputPath));
    }

    private static int getSumOfPowerOfAllGames(String inputPath) {
        int sumOfPowerOfGames = 0;
        List<String> inputData = new ArrayList<>();

        // Read input data
        try {
            inputData = Files.readAllLines(Path.of(inputPath));
        } catch (IOException e) {
            System.out.println("Exception while reading input file " + e.getLocalizedMessage());
        }

        for (String eachGame : inputData) {
            int id =Integer.parseInt(eachGame.split(":")[0].split(" ")[1]);

            String[] drawDatas = eachGame.split(":")[1].split("; ");
            List<String> colorDatas = new ArrayList<>();
            Arrays.stream(drawDatas).forEach(eachDrawData -> {
                String[] perDrayColorDatas = eachDrawData.split(",");
                colorDatas.addAll(List.of(perDrayColorDatas));
            });

            colorDatas.replaceAll(String::trim);
            int maxRedCount = 0;
            int maxGreenCount = 0;
            int maxBlueCount = 0;

            for (String eachColorData: colorDatas) {
                int count = Integer.parseInt(eachColorData.split(" ")[0]);
                String color = eachColorData.split(" ")[1].trim();

                switch (color) {
                    case "red":
                        maxRedCount = Math.max(maxRedCount, count);
                        break;
                    case "blue":
                        maxBlueCount = Math.max(maxBlueCount, count);
                        break;
                    case "green":
                        maxGreenCount = Math.max(maxGreenCount, count);
                        break;
                    default:
                        break;
                }
            }

            int powerOfGame = maxRedCount * maxGreenCount * maxBlueCount;
            sumOfPowerOfGames += powerOfGame;

            System.out.println("id: " + id + ", powerOfGame: " + powerOfGame);
        }

        return sumOfPowerOfGames;
    }
}
