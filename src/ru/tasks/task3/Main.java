package ru.tasks.task3;

public class Main {

    public static void main(String[] args) {

        Player player1 = new Player("Игрок 1", 1,2,3);
        Player player2 = new Player("Игрок 2", 4,5,5);

        Game game = new Game(player1, player2, 100, 100000);
        System.out.println(game.start());

    }
}
