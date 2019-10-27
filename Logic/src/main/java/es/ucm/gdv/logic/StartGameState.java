package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Rect;

public class StartGameState implements es.ucm.gdv.engine.GameState {
    StartGameState(Game game, GameManager gm){
        _game = game;
        _gm = gm;
    }

    @Override
    public void update(double elapsedTime) {

    }
    @Override
    public void render() {
        _game.getGraphics().drawImage(_gm.getImages()[8], new Rect(0,100,0,0), new Rect(0,384/2,528,384/2));
    }

    private Game _game;
    private GameManager _gm;
}
