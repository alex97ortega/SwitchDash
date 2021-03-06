package es.ucm.gdv.engine;

// esta va a ser la clase que se cree desde el main y que
// tendrá los demás managers de juego y el run
public interface Game {
    Graphics getGraphics();
    Input getInput();
    SoundManager getSoundManager();

    GameState getGameState();
    void changeGameState();
    void pushGameState(GameState state);
    void popGameState();
    void ExitGame();
}
