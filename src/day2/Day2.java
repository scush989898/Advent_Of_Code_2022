package day2;

import Utilities.FileReaderUtil.FileReaderUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Day2 {

    static HashMap<String, Integer> moveValues = new HashMap<>();
    static HashMap<String, String> decryptedMoves = new HashMap<>();
    static String winningCondition = "BACB";
    static String losingCondition = "BCAB";
    static int drawValue = 3;
    static int winValue = 6;
    static int loseValue = 0;

    public static void main(String[] args) throws IOException {
        moveValues.put("A", 1); //rock
        moveValues.put("B", 2); //paper
        moveValues.put("C", 3); // scissors
        decryptedMoves.put("Y", "B"); //paper
        decryptedMoves.put("Z", "C"); //scissors
        decryptedMoves.put("X", "A"); //rock
        decryptedMoves.put("A", "A"); //rock
        decryptedMoves.put("B", "B"); //paper
        decryptedMoves.put("C", "C"); // scissors

        Path path = Paths.get("./src/day2/inputs/input.txt").normalize();
        String content = FileReaderUtil.read(path);
        List<List<String>> moves = new ArrayList<>();

        List<String> contentSplited = List.of(content.split("\n"));
        for (String line : contentSplited) {
            List<String> currentMove = List.of(line.replace(" ", "").split(""));
            moves.add(currentMove);
        }

        //        first part answer
        //        13446
        int score = calculateScore(moves);
        System.out.println(score);


        //        second part answer
        //        13509
        moves = updateMoves(moves);
        score = calculateScore(moves);
        System.out.println(score);

    }

    public static int calculateScore(List<List<String>> moves) {

        String firstPlayerMove = "";
        String secondPlayerMove = "";
        int secondPlayerScore = 0;

        for (List<String> move : moves) {

            firstPlayerMove = move.get(0);
            secondPlayerMove = move.get(1);

            secondPlayerScore += getCurrentRoundScore(firstPlayerMove, secondPlayerMove);
            secondPlayerScore += getCurrentMoveValue(secondPlayerMove);

        }

        return secondPlayerScore;
    }

    public static int getCurrentRoundScore(String moveA, String moveB) {

        String decryptedMoveA = decryptedMoves.get(moveA);
        String decryptedMoveB = decryptedMoves.get(moveB);

        if (decryptedMoveA.equals(decryptedMoveB)) {
            return drawValue;
        }

        String move = decryptedMoveB + decryptedMoveA;
        if (winningCondition.contains(move)) {
            return winValue;
        }

        return loseValue;
    }

    public static int getCurrentMoveValue(String move) {

        String decrypted = decryptedMoves.get(move);
        return moveValues.get(decrypted);
    }

    public static List<List<String>> updateMoves(List<List<String>> moves) {

        char updatedMove = 0;

        List<List<String>> updatedMoves = new ArrayList<>();

        for (List<String> move : moves) {
            List<String> updatedMoveList = new ArrayList<>(move);

            String firstPlayerMove = move.get(0);
            String secondPlayerMove = move.get(1);

            int newMoveIndex = 0;

            if (secondPlayerMove.equals("Y")) {
                updatedMove = firstPlayerMove.charAt(0);
            } else if (secondPlayerMove.equals("Z")) {
                newMoveIndex = winningCondition.lastIndexOf(firstPlayerMove);
                updatedMove = winningCondition.charAt(newMoveIndex - 1);
            } else {
                newMoveIndex = losingCondition.lastIndexOf(firstPlayerMove);
                updatedMove = losingCondition.charAt(newMoveIndex - 1);
            }

            updatedMoveList.set(1, Character.toString(updatedMove));
            updatedMoves.add(updatedMoveList);
        }

        return updatedMoves;
    }


}
