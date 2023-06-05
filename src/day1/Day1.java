package day1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./src/day1/inputs/input.txt").normalize();
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

        String line;
        StringBuilder input = new StringBuilder();

        List<List<Integer>> calories = new ArrayList<>();
        List<Integer> totalCalories = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            input.append(line);
            input.append(System.lineSeparator());
        }

        String[] validatedInput = input.toString().split("\\s*\\n\\s*\\n\\s*");


        for (int i = 0; i < validatedInput.length; i++) {
            List<Integer> tempList = new ArrayList<>();
            List<String> currentListOfCalories = List.of(validatedInput[i].split("\n"));

            for (String item: currentListOfCalories) {
                tempList.add(Integer.parseInt(item.trim()));
            }
            calories.add(tempList);
        }

        for (int i = 0; i < calories.size(); i++) {
            Integer currentCaloriesSum = calories.get(i).stream().reduce(0, Integer::sum);
            totalCalories.add(currentCaloriesSum);
        }

        List<Integer> sortedCalories =  totalCalories.stream().sorted().toList();

        Integer maxCalories = sortedCalories.get(totalCalories.size() - 1);

        //        part one answer
        System.out.println(maxCalories);


        sortedCalories = sortedCalories.subList(sortedCalories.size() - 3, sortedCalories.size());
        Integer sumCalories = sortedCalories.stream().reduce(0, Integer::sum);

        //        part two answer
        System.out.println(sumCalories);

        reader.close();
    }
}
