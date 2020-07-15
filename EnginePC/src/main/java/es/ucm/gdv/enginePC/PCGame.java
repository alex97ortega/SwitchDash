package es.ucm.gdv.enginePC;

import java.util.LinkedList;

import javax.swing.JFrame;

import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.SoundManager;

public class PCGame implements es.ucm.gdv.engine.Game{

    // Constructora. Hacemos new de todos los managers
    public PCGame(JFrame jFrame){
        _graphics = new PCGraphics(jFrame);
        _input = new PCInput(jFrame);
        _soundManager = new PCSoundManager();
        jFrame.setIgnoreRepaint(true);
        jFrame.setVisible(true);
    }
    // Devuelve si se ha podido inicializar el graphics, por si acaso.
    public boolean init(){
        states = new LinkedList<GameState>();
        return _graphics.init();
    }

    // gets de los managers principales del juego
    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    @Override
    public Input getInput() {
        return _input;
    }

    @Override
    public SoundManager getSoundManager(){return _soundManager;}

    // bucle de juego

    public void run() {

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle principal
        while(true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
                // Informe de FPS
                /*if (currentTime - informePrevio > 1000000000l) {
                    long fps = frames * 1000000000l / (currentTime - informePrevio);
                    System.out.println("" + fps + " fps");
                    frames = 0;
                    informePrevio = currentTime;
                }
                ++frames;*/

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

                // cambiamos el estado si hay que hacerlo
                changeGameState();

        } // while
    }

    // llamadas de la pila de estados
    @Override
    public GameState getGameState() {
        if(states.isEmpty())
            return null;
        return states.peek();
    }


    @Override
    public void pushGameState(GameState state) {
        states.add(state);
    }
    @Override
    public void popGameState() {
        if (!states.isEmpty())
            states.poll();
    }
    // cambia el estado solo si hay uno encolado detrás del actual
    // lo llamamos al final, cuando se hayan hecho todos los update y render
    @Override
    public void changeGameState() {
        if (states.size() > 1){
            popGameState();
        }
    }
    // variables privadas
    private PCGraphics _graphics;
    private PCInput _input;
    private PCSoundManager _soundManager;
    private LinkedList<GameState> states;
}

