package day3;

import Utilities.FileReaderUtil.FileReaderUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./src/day3/inputs/input.txt").normalize();
        String rucksacks = FileReaderUtil.read(path);

        List<String> rearrangementItems = rucksacksCompartmentItemsToBeReorganized(rucksacks);
//        first part answer
//        8153
        int sum = calculateItemsSum(rearrangementItems);
        System.out.println(sum);


//        second part answer
//        2342
        List<String> elvesBadges = getElvesBadges(rucksacks);
        sum = calculateItemsSum(elvesBadges);
        System.out.println(sum);

    }

    public static List<String> rucksacksCompartmentItemsToBeReorganized(String rucksacks) {
        List<String> rearrangementItems = new ArrayList<>();

        for (String rucksack : rucksacks.split("\n")) {

            String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
            String secondCompartment = rucksack.substring(rucksack.length() / 2);
            if (firstCompartment.equals(secondCompartment)) {
                continue;
            }

            List<String> firstCompartmentItems = List.of(firstCompartment.split(""));
            List<String> secondCompartmentItems = List.of(secondCompartment.split(""));

            Set<String> firstCompartmentSet = new HashSet<>(firstCompartmentItems);
            Set<String> secondCompartmentSet = new HashSet<>(secondCompartmentItems);


            for (String firstCompartmentChar : firstCompartmentSet) {

                for (String secondCompartmentChar : secondCompartmentSet) {

                    if (firstCompartmentChar.equals(secondCompartmentChar)) {
                        rearrangementItems.add(firstCompartmentChar);
                    }
                }
            }

        }
        return rearrangementItems;
    }

    public static List<String> getElvesBadges(String rucksacks) {
        List<String> elvesBadges = new ArrayList<>();

        List<String> rucksacksList = List.of(rucksacks.split("\n"));

        List<List<String>> rucksacksGroups = new ArrayList<>();

        List<String> rucksacksListWithoutDuplicates = new ArrayList<>();

        for (String item : rucksacksList) {

            Set<String> LineSet = new HashSet<>(List.of(item.split("")));
            String Line = String.join("", LineSet);
            rucksacksListWithoutDuplicates.add(Line.replaceAll("\\s+", ""));

        }

        for (int i = 0; i < rucksacksListWithoutDuplicates.size(); i += 3) {

            ArrayList<String> group = new ArrayList<>();

            group.add(rucksacksListWithoutDuplicates.get(i));

            if (i + 1 < rucksacksListWithoutDuplicates.size()) {
                group.add(rucksacksListWithoutDuplicates.get(i + 1));
            }

            if (i + 2 < rucksacksListWithoutDuplicates.size()) {
                group.add(rucksacksListWithoutDuplicates.get(i + 2));
            }

            rucksacksGroups.add(group);
        }


        for (List<String> group : rucksacksGroups) {
            String firstLine = group.get(0);
            List<String> remainingLines = group.subList(1, group.size());

            for (String letter : firstLine.split("")) {
                boolean letterPresentInAllLines = true;

                for (String line : remainingLines) {
                    if (!line.contains(letter)) {
                        letterPresentInAllLines = false;
                        break;
                    }
                }
                if (letterPresentInAllLines) {
                    elvesBadges.add(letter);
                }
            }
        }

        return elvesBadges;
    }

    public static int calculateItemsSum(List<String> rearrangementItems) {
        int sum = 0;
        for (String item : rearrangementItems) {
            sum += getItemValue(item.charAt(0));
        }
        return sum;
    }

    public static int getItemValue(int asciiValue) {

//        Lowercase item types a through z have priorities 1 through 26.
//        Uppercase item types A through Z have priorities 27 through 52.

        return asciiValue >= 97 ? asciiValue - 96 : asciiValue - 38;
    }

}
