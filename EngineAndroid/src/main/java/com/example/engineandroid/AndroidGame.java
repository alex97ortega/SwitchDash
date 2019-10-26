package com.example.engineandroid;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class AndroidGame implements es.ucm.gdv.engine.Game {
    public AndroidGame(){
        _graphics = new AndroidGraphics();
        _input = new AndroidInput();
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

    }

    @Override
    public void stop() {

    }

    private AndroidGraphics _graphics;
    private  AndroidInput _input;
}
