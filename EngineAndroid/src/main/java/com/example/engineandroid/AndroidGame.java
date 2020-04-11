package com.example.engineandroid;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.SurfaceView;

import java.util.LinkedList;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.SoundManager;

public class AndroidGame implements Game,Runnable {
    // constructora
    public AndroidGame(Activity activity, AssetManager assetManager){
        surfaceView = new SurfaceView(activity);
        _graphics = new AndroidGraphics(surfaceView, assetManager);
        _input = new AndroidInput(surfaceView);
        _soundManager = new AndroidSoundManager(activity);
        states = new LinkedList<GameState>();
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

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    //bucle de juego
    @Override
    public void run() {
        if (_renderThread != Thread.currentThread()) {
            throw new RuntimeException("run() should not be called directly");
        }

        while(_running && surfaceView.getWidth() == 0)
            // Espera activa. Sería más elegante al menos dormir un poco.
            ;

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle principal.
        while(_running) {

            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            getGameState().update(elapsedTime);

            // Informe de FPS
            /*if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;*/

            // Pintamos el frame
            while (!_graphics.validSurface())
                ;
            getGameState().render();
            _graphics.present();
                /*
                // Posibilidad: cedemos algo de tiempo. es una medida conflictiva...
                try {
                    Thread.sleep(1);
                }
                catch(Exception e) {}
    			*/

            // cambiamos el estado si hay que hacerlo
            changeGameState();

        } // while
    }

    // llamadas refereidas a la pila de estados
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

    // Pause y resume
    public void pause() {

        if (_running) {
            _running = false;
            while (true) {
                try {
                    _renderThread.join();
                    _renderThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            }
        }

    } // pause

    public void resume() {

        if (!_running) {
            _running = true;
            _renderThread = new Thread(this);
            _renderThread.start();
        }
    }

    // variables privadas
    private SurfaceView surfaceView;
    private Thread _renderThread;
    volatile boolean _running = false;

    private  AndroidGraphics _graphics;
    private  AndroidInput _input;
    private  AndroidSoundManager _soundManager;
    private  LinkedList<GameState> states;
}
