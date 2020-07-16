package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Particle {
    Particle(Player.Color color, GameManager gm, float tama){
        _img =  gm.getImage(GameManager.Images.BALLS);

        _gm = gm;


        state = color;
        tam = tama;

        initialY = _gm.playerPosY - ((int)((_img.getHeight()/2)/tam))/2;

        _y = initialY;
        _x = _gm.refScreenWidth/2-((int)((_img.getWidth()/10)/tam))/2;
        alpha = 1.f;


        dirX = (float)((Math.random()))-0.5f;
        dirY = (float)((Math.random()))-0.5f;
    }

    public void update(double elapsedTime){
        _y += dirY*elapsedTime*_gm.getGameVelocity();
        _x += dirX*elapsedTime*_gm.getGameVelocity();
        alpha -= elapsedTime*_gm.getGameVelocity()/300;
    }

    public void render(Game game){
        // para cambiar entre player blanco y negro
        int clipY = 0;
        if(state == Player.Color.BLACK)
            clipY = _img.getHeight()/2;

        game.getGraphics().drawImage(_img,
                new Rect((int)_x,(int)_y,(int)((_img.getWidth()/10)/tam),(int)((_img.getHeight()/2)/tam)),
                        new Rect((_img.getWidth()/10)*7,clipY,_img.getWidth()/10,_img.getHeight()/2), alpha);
    }


    private float _y, _x;
    private float dirX, dirY ;
    private float alpha,tam;
    private int maxY, initialY;
    private Image _img;
    private Player.Color state;
    private GameManager _gm;
}
