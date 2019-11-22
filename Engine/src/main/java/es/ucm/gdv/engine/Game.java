package es.ucm.gdv.engine;

public interface Game {
    Graphics getGraphics();
    Input getInput();
    SoundManager getSoundManager();

    void run();

    GameState getGameState();
    void changeGameState(GameState state);
    void pushGameState(GameState state);
    void popGameState();
}
