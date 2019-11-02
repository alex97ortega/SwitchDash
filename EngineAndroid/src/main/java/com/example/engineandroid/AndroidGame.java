package com.example.engineandroid;

import java.util.Stack;

import es.ucm.gdv.engine.GameState;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

public class AndroidGame implements es.ucm.gdv.engine.Game {
    public AndroidGame(){
        _graphics = new AndroidGraphics();
        _input = new AndroidInput();
        states = new Stack<GameState>();
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

    private AndroidGraphics _graphics;
    private  AndroidInput _input;
    private Stack<GameState> states;
}
