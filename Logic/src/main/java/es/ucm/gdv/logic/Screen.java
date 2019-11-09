package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Point;
import es.ucm.gdv.engine.Rect;

// de momento esta clase sólo va a pintar los elementos que se pinten en todos los estados
public class Screen {
    public Screen(int screenWidth, int screenHeight, Graphics graphics, GameManager gm)
    {
        _width = screenWidth;
        _height = screenHeight;
        _gm=gm;
        _graphics=graphics;

        arrow = gm.getImage(GameManager.Images.ARROWS);

        alpha = 0;
        alphaFlash = 0;
        alphaSum=true;
    }

    public void update(double elapsedTime){
        posYarrows += (_gm.getGameVelocity()-46)*elapsedTime;
        if(posYarrows >= arrow.getHeight())
            posYarrows = 0;

        // alpha para el tapToPlay
        if(alpha <= 0){
            alpha = 0;
            alphaSum = true;
        }
        else if (alpha >= 1.f){
            alpha = 0.9f;
            alphaSum = false;
        }
        if(alphaSum)
            alpha += 2*elapsedTime;
        else
            alpha -= 2*elapsedTime;

        if(alphaFlash > 0)
            alphaFlash-= 3*elapsedTime;
    }
    public void render(GameManager.BackgroundColor color ){
        // tenemos que actualizar el ancho y alto por si cambiamos el tamaño
        //  de la pantalla mientras se juega

        _width = _graphics.getWidth();
        _height = _graphics.getHeight();

        drawFondo(color);
        drawFondoGamePlay(color);
        drawArrows();
        if(alphaFlash>0)
            drawFlashEffect(alphaFlash);
    }

    private void drawFondo(GameManager.BackgroundColor color){
        switch (color){
            case GREEN:
                _graphics.clear( 0xFF41a85f);
                break;
            case GREENISH_BLUE:
                _graphics.clear( 0xFF00a885);
                break;
            case CYAN:
                _graphics.clear( 0xFF3d8eb9);
                break;
            case LIGHT_BLUE:
                _graphics.clear( 0xFF2969b0);
                break;
            case PURPLE:
                _graphics.clear( 0xFF553982);
                break;
            case DARK_BLUE:
                _graphics.clear( 0xFF28324e);
                break;
            case ORANGE:
                _graphics.clear( 0xFFf37934);
                break;
            case RED:
                _graphics.clear( 0xFFd14b41);
                break;
            case BEIGE:
                _graphics.clear( 0xFF75706b);
                break;
            default:
                break;
        }
    }
    // pintar el trozo de fondo que corresponde al pasillo donde se pintan las flechas.
    private void drawFondoGamePlay(GameManager.BackgroundColor color){
        Image fondo = _gm.getImage(GameManager.Images.BACKGROUND);
        int width = fondo.getWidth()/(GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        int height = fondo.getHeight();

        int comienzoX = _width/2-(arrow.getWidth()/2);
        int finalX = comienzoX + arrow.getWidth() - width;

        for (int i=comienzoX; i<finalX;i+=width){ // ancho
            for (int j=0; j<_height;j+=height){ // alto
                _graphics.drawImage
                        (fondo, new Rect(i,j,width,height),
                                new Rect(width*color.ordinal(),0,width,height),1.f);
            }
        }
    }
    private void drawArrows(){
        int x = _width/2-(arrow.getWidth()/2);

        // dibujamos 2 imágenes que se irán recolocando arriba de la pantalla
        // al llegar alfinal de la misma

        _graphics.drawImage(arrow,
                new Rect(x,(int)posYarrows,arrow.getWidth(),arrow.getHeight()),
                new Rect(0,0,arrow.getWidth(),arrow.getHeight()),0.8f);

        _graphics.drawImage(arrow,
                new Rect(x,(int)posYarrows -arrow.getHeight(),arrow.getWidth(),arrow.getHeight()),
                new Rect(0,0,arrow.getWidth(),arrow.getHeight()),0.8f);

    }
    public void drawAlphaImage(int x, int y, Image img){
        _graphics.drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                new Rect(0,0,img.getWidth(),img.getHeight()), alpha);
    }
    public void drawScore(int x, int y, int num){
        //suponemos que el score nunca va a pasar de 999
        if(num > 99){
            drawNumber(x-90,y, num/100);
            drawNumber(x-20,y, num/10);
            drawNumber(x+50,y, num%10);
        }
        else if(num > 9){
            drawNumber(x-40,y, num/10);
            drawNumber(x+30,y, num%10);
        }
        else
            drawNumber(x,y, num%10);
    }
    private  void drawNumber(int x, int y, int num){
        Image imgScore = _gm.getImage(GameManager.Images.SCOREFONT);
        int clipx = (imgScore.getWidth()/15)*(7 + num);
        int clipy = (imgScore.getHeight()/7)*3;

        if(num > 7){
            clipx = (imgScore.getWidth()/15)*(num -8);
            clipy = (imgScore.getHeight()/7) *4;

        }
        _graphics.drawImage(imgScore,
                new Rect(x,y,imgScore.getWidth()/15,imgScore.getHeight()/7),
                new Rect(clipx,clipy,imgScore.getWidth()/15,imgScore.getHeight()/7), 1.f);
    }
    public void drawText(int x, int y, String text){
        Image imgScore = _gm.getImage(GameManager.Images.SCOREFONT);
        int clipx = (imgScore.getWidth()/15);
        int clipy = (imgScore.getHeight()/7);

        int charA = 97; // el char A corresponde con el int 97

        Point[] positions = new Point[text.length()];

        for (int i =0; i< positions.length; i++){
            int letra = (int)text.charAt(i) - charA;
            positions[i] = new Point(letra%15, letra /15);
            _graphics.drawImage(imgScore,
                    new Rect(x + (i *70),y,imgScore.getWidth()/15,imgScore.getHeight()/7),
                    new Rect(clipx * positions[i].getX(),clipy* positions[i].getY(),imgScore.getWidth()/15,imgScore.getHeight()/7), 1.f);
        }
    }
    private void drawFlashEffect(float alpha){
        Image fondo = _gm.getImage(GameManager.Images.WHITE);

        for (int i=0; i<_width;i+=fondo.getWidth()){ // ancho
            for (int j=0; j<_height;j+=fondo.getHeight()){ // alto
                _graphics.drawImage
                        (fondo, new Rect(i,j,fondo.getWidth(),fondo.getHeight()),
                                new Rect(0,0,fondo.getWidth(),fondo.getHeight()),alpha);
            }
        }
    }
    public void doFlashEffect(){ alphaFlash = 1.f;}
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    private int _width, _height;
    private float posYarrows = 0;
    private Image arrow;

    float alpha;
    float alphaFlash;
    boolean alphaSum;

    private GameManager _gm;
    private Graphics _graphics;
}
