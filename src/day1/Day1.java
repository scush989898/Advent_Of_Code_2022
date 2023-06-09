package day1;

import Utilities.FileReaderUtil.FileReaderUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./src/day1/inputs/input.txt").normalize();

        String content = FileReaderUtil.read(path);

        List<List<Integer>> calories = new ArrayList<>();
        List<Integer> totalCalories = new ArrayList<>();

        String[] validatedInput = content.split("\\s*\\n\\s*\\n\\s*");

        for (String s : validatedInput) {
            List<Integer> tempList = new ArrayList<>();
            List<String> currentListOfCalories = List.of(s.split("\n"));

            for (String item : currentListOfCalories) {
                tempList.add(Integer.parseInt(item.trim()));
            }
            calories.add(tempList);
        }

        for (List<Integer> calory : calories) {
            Integer currentCaloriesSum = calory.stream().reduce(0, Integer::sum);
            totalCalories.add(currentCaloriesSum);
        }

        List<Integer> sortedCalories = totalCalories.stream().sorted().toList();

        Integer maxCalories = sortedCalories.get(totalCalories.size() - 1);

        //        part one answer
//        69289
        System.out.println(maxCalories);


        sortedCalories = sortedCalories.subList(sortedCalories.size() - 3, sortedCalories.size());
        Integer sumCalories = sortedCalories.stream().reduce(0, Integer::sum);

        //        part two answer
//        205615
        System.out.println(sumCalories);

    }
}
