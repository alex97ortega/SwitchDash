package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Player {
    Player(GameManager gm){
        _img =  gm.getImage(GameManager.Images.PLAYERS);
        state = Color.BLACK;

        _x = gm.refScreenWidth/2-(_img.getWidth()/2);
        _y = initialY = gm.playerPosY;
    }

    public void render(Game game, Screen screen){
        // para cambiar entre player blanco y negro
        int clipY = 0;
        if(state == Color.BLACK)
            clipY = _img.getHeight()/2;

        _x = (int)(( screen.getWidth() / 2) - ((_img.getWidth() * game.getGraphics().getScale()) / 2));
        _y = (int)(initialY*game.getGraphics().getScale());

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

    private Image _img;
    private Color state;
    private int _x,_y;
    private int initialY;

    public enum Color{
        BLACK,
        WHITE
    }
}
