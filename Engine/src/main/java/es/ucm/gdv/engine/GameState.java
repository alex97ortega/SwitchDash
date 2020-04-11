package es.ucm.gdv.engine;

// clase estado de juego
public interface GameState {
    void update(double elapsedTime);
    void render();
}
