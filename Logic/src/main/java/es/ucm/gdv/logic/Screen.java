package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

// de momento esta clase sólo va a pintar los elementos que se pinten en todos los estados
public class Screen {
    public Screen(int screenWidth, int screenHeight, Graphics graphics, GameManager gm)
    {
        _width = screenWidth;
        _height = screenHeight;
        this.gm=gm;
        this.graphics=graphics;

        arrow = gm.getImage(GameManager.Images.ARROWS);
    }

    public void update(double elapsedTime){

        posYarrows += (gm.getGameVelocity()-46)*elapsedTime;

        if(posYarrows >= arrow.getHeight())
            posYarrows = 0;
    }
    public void render(GameManager.BackgroundColor color ){
        // tenemos que actualizar el ancho y alto por si cambiamos el tamaño
        //  de la pantalla mientras se juega

        _width = graphics.getWidth();
        _height = graphics.getHeight();


        drawFondo(color);
        //drawFondoGamePlay(color);
        drawArrows();
    }

    private void drawFondo(GameManager.BackgroundColor color){
        switch (color){
            case GREEN:
                graphics.clear( 0x41a85f);
                break;
            case GREENISH_BLUE:
                graphics.clear( 0x00a885);
                break;
            case CYAN:
                graphics.clear( 0x3d8eb9);
                break;
            case LIGHT_BLUE:
                graphics.clear( 0x2969b0);
                break;
            case PURPLE:
                graphics.clear( 0x553982);
                break;
            case DARK_BLUE:
                graphics.clear( 0x28324e);
                break;
            case ORANGE:
                graphics.clear( 0xf37934);
                break;
            case RED:
                graphics.clear( 0xd14b41);
                break;
            case BEIGE:
                graphics.clear( 0x75706b);
                break;
            default:
                break;
        }
    }
    // pintar el trozo de fondo que corresponde al pasillo donde se pintan las flechas.
    // Aunque no tiene mucho sentido, porque es exactamente del mismo color que el drawFondo()
    // y baja bastante el rendimiento. Pero ahí lo dejo, por siaca.
    private void drawFondoGamePlay(GameManager.BackgroundColor color){
        Image fondo = gm.getImage(GameManager.Images.BACKGROUND);
        int width = fondo.getWidth()/(GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        int height = fondo.getHeight();

        int comienzoX = _width/2-(arrow.getWidth()/2);
        int finalX = comienzoX + arrow.getWidth() - width;

        for (int i=comienzoX; i<finalX;i+=width){ // ancho
            for (int j=0; j<_height;j+=height){ // alto
                graphics.drawImage
                        (fondo, new Rect(i,j,0,0),
                                new Rect(width*color.ordinal(),0,width,height),255);
            }
        }
    }
    private void drawArrows(){
        int x = _width/2-(arrow.getWidth()/2);

        // dibujamos 2 imágenes que se irán recolocando arriba de la pantalla
        // al llegar alfinal de la misma

        // la imagen es demasiado grande, si la pinto con su tamaño en lugar de
        // con tamaño height, baja el rendimiento
        graphics.drawImage(arrow,
                new Rect(x,posYarrows,arrow.getWidth(),arrow.getHeight()),
                new Rect(0,0,arrow.getWidth(),arrow.getHeight()),255);

        graphics.drawImage(arrow,
                new Rect(x,posYarrows -_height,arrow.getWidth(),arrow.getHeight()),
                new Rect(0,0,arrow.getWidth(),arrow.getHeight()),255);

    }
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }
    private int _width, _height;
    private int posYarrows = 0;
    private Image arrow;

    private GameManager gm;
    private Graphics graphics;
}
