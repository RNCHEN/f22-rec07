package game;

import java.util.Arrays;

public class GameState {

    private final Cell[] cells;
    private static Player winner;
    private static Player currentPlayer;
    private GameState(Cell[] cells) {
        this.cells = cells;
    }

    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        winner = game.getWinner();  // 获取 winner
        currentPlayer = game.getPlayer();
        return new GameState(cells);
    }

    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * toString() of GameState will return the string representing
     * the GameState in JSON format.
     */
    @Override
    public String toString() {
        return """
                { "cells": %s,
                  "winner": "%s",
                  "currentPlayer": "%s"
                }
                """.formatted(Arrays.toString(this.cells),winner,currentPlayer);
    }

    private static Cell[] getCells(Game game) {
        Cell cells[] = new Cell[9];
        Board board = game.getBoard();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                String text = "";
                boolean playable = false;
                Player player = board.getCell(x, y);
                if (player == Player.PLAYER0)
                    text = "X";
                else if (player == Player.PLAYER1)
                    text = "O";
                else if (player == null) {
                    playable = true;
                }
                cells[3 * y + x] = new Cell(x, y, text, playable, game.getWinner());
            }
        }
        return cells;
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean playable;
    private Player player;

    Cell(int x, int y, String text, boolean playable, Player player) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public String toString() {
        return """
                {
                    "text": "%s",
                    "playable": %b,
                    "x": %d,
                    "y": %d 
                }
                """.formatted(this.text, this.playable, this.x, this.y);
    }
}