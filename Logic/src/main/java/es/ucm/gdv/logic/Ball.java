package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Ball {
    Ball(int y, Game game, GameManager gm){
        _img =  gm.getImage(GameManager.Images.BALLS);

        _y = y;

        _gm = gm;

        int rand = (int)(Math.random() * 3);
        if(rand>1)
            state = Color.BLACK;
        else
            state = Color.WHITE;
    }

    public boolean update(double elapsedTime){
        _y += _gm.getGameVelocity()*elapsedTime;
        if(_y >= finalY){
            return true; // devuelve true cuando llegue al final
        }
        return false;
    }

    public void render(Game game){
        // para cambiar entre player blanco y negro
        int clipY = 0;
        if(state == Color.BLACK)
            clipY = _img.getHeight()/2;

        int _x = game.getGraphics().getWidth()/2-((_img.getWidth()/10)/2);

        game.getGraphics().drawImage(_img,
                new Rect(_x,_y,0,0),
                new Rect(0,clipY,_img.getWidth()/10,_img.getHeight()/2), 255);
    }

    public void regenerate(){
        int rand = (int)(Math.random() * 2);
        if(rand>1)
            state = Color.BLACK;
        else
            state = Color.WHITE;
        _y = initialY;
    }
    public int getColor(){return state.ordinal();}

    private int _y;
    private final int initialY = -395*2 -200;
    private final int finalY = 395*2 -200;
    private Image _img;
    private Color state;
    private GameManager _gm;

    private enum Color{
        BLACK,
        WHITE
    }
}
