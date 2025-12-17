package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    Game game = new Game();
    Player p1 = new Player(1, "Oleg", 100);
    Player p2 = new Player(2, "Ivan", 150);
    Player p3 = new Player(3, "Anna", 100);

    @Test
    public void shouldWinFirstPlayer() {
        game.register(p2); // сила 150
        game.register(p1); // сила 100

        int expected = 1;
        int actual = game.round("Ivan", "Oleg");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWinSecondPlayer() {
        game.register(p1);
        game.register(p2);

        int expected = 2;
        int actual = game.round("Oleg", "Ivan");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeDraw() {
        game.register(p1);
        game.register(p3); // силы равны (100)

        int expected = 0;
        int actual = game.round("Oleg", "Anna");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfFirstNotRegistered() {
        game.register(p2);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Unknown", "Ivan");
        });
    }

    @Test
    public void shouldThrowExceptionIfSecondNotRegistered() {
        game.register(p1);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Oleg", "Unknown");
        });
    }

    @Test
    public void shouldThrowExceptionIfBothNotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Unknown1", "Unknown2");
        });
    }
}