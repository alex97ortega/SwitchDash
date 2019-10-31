package es.ucm.gdv.enginePC;

import java.util.Stack;

import javax.swing.JFrame;

import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class PCGame implements es.ucm.gdv.engine.Game{

    public PCGame(JFrame jFrame){
        _graphics = new PCGraphics(jFrame);
        _input = new PCInput(jFrame);

        jFrame.setIgnoreRepaint(true);
        jFrame.setVisible(true);
    }
    public boolean init(){
        states = new Stack<GameState>();
        return _graphics.init();
    }
    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    @Override
    public void run() {

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;
        double elapsedTime = 0.0d;

        // Bucle principal
        while(true) {

            float barrera = 1/60.0f;

            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            elapsedTime += (double) nanoElapsedTime / 1.0E9;


            if (elapsedTime > barrera) {
                // Informe de FPS
                if (currentTime - informePrevio > 1000000000l) {
                    long fps = frames * 1000000000l / (currentTime - informePrevio);
                    //System.out.println("" + fps + " fps");
                    frames = 0;
                    informePrevio = currentTime;
                }
                ++frames;

                getGameState().update(elapsedTime);
                // Pintamos el frame con el BufferStrategy

                do {
                    _graphics.setGraphics();
                    try {
                        getGameState().render();
                    } finally {
                        _graphics.dispose();
                    }
                } while (!_graphics.frameReady());
                _graphics.show();
            }
        } // while
    }
    @Override
    public GameState getGameState() {
        if(states.empty())
            return null;
        return states.peek();
    }

    @Override
    public void changeGameState(GameState state) {
        if (!states.empty()){
            states.pop();
            states.push(state);
        }
    }

    @Override
    public void pushGameState(GameState state) {
        states.push(state);
    }
    @Override
    public void popGameState() {
        if (!states.empty())
            states.pop();
    }

    private PCGraphics _graphics;
    private PCInput _input;
    private Stack<GameState> states;
}

