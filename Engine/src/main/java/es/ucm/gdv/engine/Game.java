package es.ucm.gdv.engine;

public interface Game {
    Graphics getGraphics();
    Input getInput();

    void run();
    void stop();
}
