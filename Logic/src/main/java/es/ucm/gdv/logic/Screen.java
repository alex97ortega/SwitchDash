package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

// de momento esta clase sólo va a pintar los elementos que se pinten en todos los estados
public class Screen {
    public Screen(int screenWidth, int screenHeight)
    {
        _width = screenWidth;
        _height = screenHeight;
    }

    public void update(double elapsedTime){

        posYarrows += incrY*elapsedTime;

        if(posYarrows >= _height)
            posYarrows = 0;
    }
    public void render(Graphics graphics, GameManager gm, GameManager.BackgroundColor color ){
        // tenemos que actualizar el ancho y alto por si cambiamos el tamaño
        //  de la pantalla mientras se juega
        _width = graphics.getWidth();
        _height = graphics.getHeight();

        drawFondo(graphics, gm, color);
        drawArrows(graphics,gm);
    }

    private void drawFondo(Graphics graphics, GameManager gm, GameManager.BackgroundColor color){
        Image fondo = gm.getImage(GameManager.Images.BACKGROUND);
        int width = fondo.getWidth()/(GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        int height = fondo.getHeight();

        for (int i=0; i<_width;i+=width){ // ancho
            for (int j=0; j<_height;j+=height){ // alto
                graphics.drawImage
                        (fondo, new Rect(i,j,0,0),
                                new Rect(width*color.ordinal(),0,width,height),255);
            }
        }
    }
    private void drawArrows(Graphics graphics, GameManager gm){
        Image img = gm.getImage(GameManager.Images.ARROWS);
        int x = _width/2-(img.getWidth()/2);

        // dibujamos 2 imágenes que se irán recolocando arriba de la pantalla
        // al llegar alfinal de la misma

        graphics.drawImage(img,
                new Rect(x,posYarrows,0,0),
                new Rect(0,0,img.getWidth(),img.getHeight()),255);

        graphics.drawImage(img,
                new Rect(x,posYarrows -_height,0,0),
                new Rect(0,0,img.getWidth(),img.getHeight()),255);

    }
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }
    private int _width, _height;
    private int posYarrows = 0;
    private int incrY = 100;
}
