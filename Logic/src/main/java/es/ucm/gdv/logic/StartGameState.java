package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
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
        _game.getGraphics().clear(255);
        _gm.getScreen().render(_game.getGraphics(),_gm);

        Image img = _gm.getImage(GameManager.Images.PLAYERS);
        int x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);
        int y = 600;
        _game.getGraphics().drawImage(img,
                new Rect(x,y,0,0),
                new Rect(0,img.getHeight()/2,img.getWidth(),img.getHeight()/2));
    }


    private Game _game;
    private GameManager _gm;
}
