package es.ucm.gdv.engine;

public interface GameState {
    void update(double elapsedTime);
    void render();
    void onPress(int x, int y);
}
