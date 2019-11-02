package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Player {
    Player(Game game, GameManager gm){
        _img =  gm.getImage(GameManager.Images.PLAYERS);
        state = Color.BLACK;
    }

    public void render(Game game){
        // para cambiar entre player blanco y negro
        int clipY = 0;
        if(state == Color.BLACK)
            clipY = _img.getHeight()/2;

        int _x = game.getGraphics().getWidth()/2-(_img.getWidth()/2);

        game.getGraphics().drawImage(_img,
                new Rect(_x,_y,_img.getWidth(),_img.getHeight()/2),
                new Rect(0,clipY,_img.getWidth(),_img.getHeight()/2), 1.f);
    }

    public void changeColor(){
        if(state == Color.BLACK)
            state = Color.WHITE;
        else state = Color.BLACK;
    }
    public Color getColor(){return state;}

    int _y = 700;
    private Image _img;
    private Color state;

    public enum Color{
        BLACK,
        WHITE
    }
}
