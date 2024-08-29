package org.example;

enum GameStates {
    OPENED,
    RUNNING,
    PAUSED,
    GAME_OVER
}

public class GameState {

    GameStates currentGameState;
    public static GameState gameState = null;

    GameState() {
        currentGameState = GameStates.OPENED;
    }

    public static GameState getInstance() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }
}