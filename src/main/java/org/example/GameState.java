package org.example;

enum GameStates {
    RUNNING,
    STARTED,
    PAUSED,
    GAME_OVER
}

public class GameState {

    GameStates currentGameState;
    public static GameState gameState = null;

    GameState() {
        currentGameState = GameStates.RUNNING;
    }

    public static GameState getInstance() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }
}