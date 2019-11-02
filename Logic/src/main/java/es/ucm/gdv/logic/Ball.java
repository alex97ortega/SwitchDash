package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Ball {
    Ball(int y, Game game, GameManager gm){
        _img =  gm.getImage(GameManager.Images.BALLS);

        _y = y;

        _gm = gm;

        double rand = (Math.random());
        if(rand>0.5)
            state = Player.Color.BLACK;
        else
            state = Player.Color.WHITE;
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
        if(state == Player.Color.BLACK)
            clipY = _img.getHeight()/2;

        int _x = game.getGraphics().getWidth()/2-((_img.getWidth()/10)/2);

        game.getGraphics().drawImage(_img,
                new Rect(_x,(int)_y,_img.getWidth()/10,_img.getHeight()/2),
                new Rect((_img.getWidth()/10)*7,clipY,_img.getWidth()/10,_img.getHeight()/2), 1.f);
    }

    public void regenerate(Player.Color lastColor){
        double rand = (Math.random());
        // 70% de posibilidad de que salga del mismo color que la bola anterior
        if(rand > 0.3)
            state = lastColor;
        else{
            if(lastColor == Player.Color.BLACK)
                state = Player.Color.WHITE;
            else
                state = Player.Color.BLACK;
        }
        _y = initialY;
    }
    public Player.Color getColor(){return state;}

    private float _y;
    private final int initialY = -395*2 -200;
    private final int finalY = 395*2 -200;
    private Image _img;
    private Player.Color state;
    private GameManager _gm;
}
