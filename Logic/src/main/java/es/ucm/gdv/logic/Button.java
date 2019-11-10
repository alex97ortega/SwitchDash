package es.ucm.gdv.logic;


import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Button {
    Button(Position pos, GameManager gm, GameManager.Buttons type){
        _type = type;
        _img = gm.getImage(GameManager.Images.BUTTONS);

        _width = _img.getWidth()/GameManager.Buttons.TOTAL_BUTTONS.ordinal();
        _height = _img.getHeight();
        position = pos;

        if(position == Position.LEFT)
            _x =30;
        else
            _x = gm.refScreenWidth-170;
        _y = gm.infoPosY;
    }

    public void render(Game game){

        int cripX = _width * _type.ordinal();
        game.getGraphics().drawImage(_img,
                new Rect(_x,_y,_width,_height),
                new Rect(cripX,0,_width,_height), 1.f);
    }

    // check position for clicks
    // necesito saber el tamaño de la pantalla para saber
    // el X  y el Y real, este X es para resolución estándar 1080x1920

    public boolean inside(int x, int y, Graphics graphics){

        float relationX = graphics.getWidth()/(float)graphics.refScaleX;
        float relationY = graphics.getHeight()/(float)graphics.refScaleY;

        int realX = (int)(_x *relationX);
        int realY = (int)(_y *relationY);

        int realW = (int)(_width *relationX);
        int realH = (int)(_height *relationY);

        if(x < realX || x > realX +realW )
            return false;
        if(y < realY || y > realY +realH)
            return  false;
        return true;
    }
    public GameManager.Buttons getType(){return _type;}
    public void ChangeType(GameManager.Buttons type){_type = type;}

    private Image _img;
    private int _x, _y;
    private int _width, _height;
    private GameManager.Buttons _type;
    private Position position;

    public enum Position{
        LEFT, RIGHT
    }
}
