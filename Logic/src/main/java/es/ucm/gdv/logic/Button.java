package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Point;
import es.ucm.gdv.engine.Rect;

public class Button {
    Button(int x, int y, GameManager gm, GameManager.Buttons type){
        _x = x;
        _y = y;
        _type = type;
        _img = gm.getImage(GameManager.Images.BUTTONS);

        _width = _img.getWidth()/GameManager.Buttons.TOTAL_BUTTONS.ordinal();
        _height = _img.getHeight();
    }

    public void render(Game game){
        int cripX = _width * _type.ordinal();
        game.getGraphics().drawImage(_img,
                new Rect(_x,_y,_width,_height),
                new Rect(cripX,0,_width,_height), 255);
    }

    // check position for clicks
    public boolean inside(int x, int y){
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        if(x < _x || x > _x +_width )
            return false;
        if(y < _y || y > _y +_height)
            return  false;
        return true;
    }
    public GameManager.Buttons getType(){return _type;}
    private Image _img;
    private int _x, _y;
    private int _width, _height;
    private GameManager.Buttons _type;
}
