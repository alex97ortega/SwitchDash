package es.ucm.gdv.enginePC;

import java.util.Stack;

import javax.swing.JFrame;

import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class PCGame implements es.ucm.gdv.engine.Game{

    public PCGame(JFrame jFrame){
        _graphics = new PCGraphics(jFrame);
        _input = new PCInput();

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

        // Bucle principal
        while(true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            //switchDashPC.update(elapsedTime);
            getGameState().update(elapsedTime);
            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    try {
                        getGameState().render();
                    }
                    finally {
                        _graphics.dispose();
                    }
                } while(_graphics.getBuffer().contentsRestored());
                _graphics.getBuffer().show();
            } while(_graphics.getBuffer().contentsLost());
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

