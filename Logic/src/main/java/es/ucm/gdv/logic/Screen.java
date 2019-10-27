package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class Screen {
    public Screen(int screenWidth, int screenHeight)
    {
        _width = screenWidth;
        _height = screenHeight;
    }
    public void render(Graphics graphics, GameManager gm){
        drawFondo(graphics, gm, GameManager.BackgroundColor.GREEN);
    }
    private void drawFondo(Graphics graphics, GameManager gm, GameManager.BackgroundColor color){
        Image fondo = gm.getImage(GameManager.Images.BACKGROUND);
        int width = fondo.getWidth()/(GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        int height = fondo.getHeight();

        for (int i=0; i<_width;i+=width){ // ancho
            for (int j=0; j<_height;j+=height){ // alto
                graphics.drawImage
                        (fondo, new Rect(i,j,0,0),
                                new Rect(width*(color.ordinal()),0,width,height));
            }
        }
    }
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }
    private int _width, _height;
}
