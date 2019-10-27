package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Player {
    Player(Game game, GameManager gm){
        _game = game;
        _img =  gm.getImage(GameManager.Images.PLAYERS);

        _x = _game.getGraphics().getWidth()/2-(_img.getWidth()/2);
        _y = 600;
        state = State.BLACK;
    }

    public void render(){
        // para cambiar entre player blanco y negro
        int clipY = 0;
        if(state == State.BLACK)
            clipY = _img.getHeight()/2;

        _game.getGraphics().drawImage(_img,
                new Rect(_x,_y,0,0),
                new Rect(0,clipY,_img.getWidth(),_img.getHeight()/2), 255);
    }

    public void changeColor(){
        if(state == State.BLACK)
            state = State.WHITE;
        else state = State.BLACK;
    }
    private int _x,_y;
    private Image _img;
    private Game _game;
    private State state;

    private enum State{
        BLACK,
        WHITE
    }
}
