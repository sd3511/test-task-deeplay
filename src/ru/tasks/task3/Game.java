package ru.tasks.task3;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final int numberOfThrows;
    private final int numberOfGames;
    private double countAllWinsFirstPlayer;
    private double countAllWinsSecondPlayer;
    private double countDraw;

    public Game(Player firstPlayer, Player secondPlayer, int numberOfThrows, int numberOfGames) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.numberOfThrows = numberOfThrows;
        this.numberOfGames = numberOfGames;
        countAllWinsFirstPlayer = 0d;
        countAllWinsSecondPlayer = 0d;
    }

    public String start() {
        List<Integer> winSequenceFirstPlayer = new ArrayList<>();
        List<Integer> winSequenceSecondPlayer = new ArrayList<>();

        for (int i = 0; i < numberOfGames; i++) {
            for (int j = 0; j < numberOfThrows; j++) {
                int resOfPlayCraps = playCraps();
                checkResult(resOfPlayCraps, firstPlayer, winSequenceFirstPlayer);
                checkResult(resOfPlayCraps, secondPlayer, winSequenceSecondPlayer);
            }
            countAllWinsOrDraws();
        }


        String result = "Вероятность победы игрока " + firstPlayer.getName() +
                " при " + numberOfThrows + " броске(ах) с последовательностью " +
                firstPlayer.getSelectedSequence() + " равна: " + countAllWinsFirstPlayer / numberOfGames +
                "\n" +
                "Вероятность победы игрока " + secondPlayer.getName() +
                " при " + numberOfThrows + " броске(ах) с последовательностью " +
                secondPlayer.getSelectedSequence() + " равна: " + countAllWinsSecondPlayer / numberOfGames +
                "\n" +
                "Вероятность ничьи при данных условиях : " + countDraw / numberOfGames;
        return result;
    }

    private void countAllWinsOrDraws() {
        if (firstPlayer.getNumberOfPoints() > secondPlayer.getNumberOfPoints()) {
            countAllWinsFirstPlayer++;
        } else if (firstPlayer.getNumberOfPoints() < secondPlayer.getNumberOfPoints()) {
            countAllWinsSecondPlayer++;
        } else if (firstPlayer.getNumberOfPoints() == secondPlayer.getNumberOfPoints()) {
            countDraw++;
        }
        firstPlayer.setNumberOfPoints(0);
        secondPlayer.setNumberOfPoints(0);
    }

    private int playCraps() {
        return (int) ((Math.random() * 6) + 1);
    }

    private void checkResult(int resOfPlayCraps, Player player, List<Integer> winSequence) {
        if (winSequence.size() == 2) {
            if (player.getSelectedSequence().get(2) == resOfPlayCraps) {
                winSequence.add(resOfPlayCraps);
                player.setNumberOfPoints(player.getNumberOfPoints() + 1);
                winSequence.clear();
                return;
            } else if (player.getSelectedSequence().get(1) == resOfPlayCraps) {
                winSequence.clear();
                winSequence.add(resOfPlayCraps);
            } else {
                winSequence.clear();
                return;
            }
        }
        if (winSequence.size() == 1) {
            if (player.getSelectedSequence().get(1) == resOfPlayCraps) {
                winSequence.add(resOfPlayCraps);
            } else if (player.getSelectedSequence().get(0) == resOfPlayCraps) {
                winSequence.clear();
                winSequence.add(resOfPlayCraps);
            } else {
                winSequence.clear();
                return;
            }
        }
        if (winSequence.size() == 0) {
            if (player.getSelectedSequence().get(0) == resOfPlayCraps) {
                winSequence.add(resOfPlayCraps);
            }
        }

    }
}
