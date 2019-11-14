package com.example.engineandroid;

import android.app.Activity;
import android.content.res.AssetManager;
import android.view.SurfaceView;
//import android.media.MediaPlayer;

import java.util.Stack;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class AndroidGame extends SurfaceView implements Game,Runnable {
    public AndroidGame(Activity activity, AssetManager assetManager){
        super(activity);
        _graphics = new AndroidGraphics(this, assetManager);
        _input = new AndroidInput(this);
        states = new Stack<GameState>();
        //Sonido
        //mp3_player = MediaPlayer.create(this, res.raw. );
    }

    @Override
    public Graphics getGraphics() {
        return _graphics;
    }

    //SONIDO
    //public MediaPlayer mp3_player;

    @Override
    public Input getInput() {
        return _input;
    }
    @Override
    public void run() {
        if (_renderThread != Thread.currentThread()) {
            throw new RuntimeException("run() should not be called directly");
        }

        while(_running && getWidth() == 0)
            // Espera activa. Sería más elegante al menos dormir un poco.
            ;

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // SONIDO
        //mp3_player.start();

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
    public void resume() {

        if (!_running) {
            _running = true;
            _renderThread = new Thread(this);
            _renderThread.start();
        }
    }

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

    //nuevas
    private Thread _renderThread;
    volatile boolean _running = false;

    private AndroidGraphics _graphics;
    private  AndroidInput _input;
    private Stack<GameState> states;
}
