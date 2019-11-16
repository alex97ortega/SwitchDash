package es.ucm.gdv.enginePC;

import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class PCGraphics implements es.ucm.gdv.engine.Graphics {

    public PCGraphics(JFrame jFrame){
        _jFrame = jFrame;
    }
    public boolean init(){
        // Intentamos crear el buffer strategy con 2 buffers.
        int tries = 100;
        while(tries-- >0)
        {
            try
            {
                _jFrame.createBufferStrategy(2);
                break;
            }
            catch (Exception e)
            {
                e.getCause();
                System.out.print("Couldn't built a buffer strategy");
                return false;
            }
        }
        _bufferStrategy = _jFrame.getBufferStrategy();
        setGraphics();

        return true;
    }
    @Override
    public Image newImage(String name) {
        PCImage img;
        try {
            img = new PCImage(javax.imageio.ImageIO.read(new java.io.File(name)));
        }
        catch (Exception e) {
            System.err.println(e);
            return null;
        }
        return img;
    }

    @Override
    public void clear(int color){
        _graphics2D.setColor(new Color(color));
        _graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }
    // scr es la superficie en pantalla de lo que queremos dibujr
    // clip es el recorte de la imagen que queremos
    @Override
    public void drawImage(Image img, Rect scr, Rect clip, float alpha) {
        java.awt.Image tmp = ((PCImage)(img)).getImg();

        // para el alpha
        // pequeño control para que no casque el setComposite
        if (alpha > 1.f) alpha = 1.f;
        else if (alpha < 0) alpha = 0;
        _graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    alpha));

        Rect scaledScr;
        if(scr.getX() == refScaleX/2 - (scr.getW()/2))
            scaledScr = scale(scr, true);
        else
            scaledScr = scale(scr, false);

        _graphics2D.drawImage(tmp,
                scaledScr.getA().getX(), scaledScr.getA().getY(), scaledScr.getB().getX(), scaledScr.getB().getY(),
                clip.getA().getX(), clip.getA().getY(), clip.getB().getX(), clip.getB().getY(),null);
    }

    private Rect scale( Rect oldScr, boolean centrado){

        int newX = (int)(oldScr.getA().getX()*getRelationX());
        int newY = (int)(oldScr.getA().getY()*getRelationY());
        int newWidth = (int)(oldScr.getW() * getRelationY());
        int newHeight = (int)(oldScr.getH()*getRelationY());

        if(centrado)
            newX = getWidth()/2 - newWidth/2;
        return new Rect(newX, newY, newWidth, newHeight);
    }
    @Override
    public int getWidth() {
        return _jFrame.getWidth();
    }

    @Override
    public int getHeight() {
        return _jFrame.getHeight();
    }

    @Override
    public float getRelationX(){
        return getWidth()/(float)refScaleX;
    }
    @Override
    public float getRelationY(){
        return getHeight()/(float)refScaleY;
    }

    public void setGraphics(){_graphics2D=(Graphics2D)_bufferStrategy.getDrawGraphics();}
    public void dispose(){
        _bufferStrategy.getDrawGraphics().dispose();
    }
    boolean frameReady()
    {
        return (!_bufferStrategy.contentsRestored() && !_bufferStrategy.contentsLost());
    }
    public void show(){_bufferStrategy.show();}

    private BufferStrategy _bufferStrategy;
    private Graphics2D _graphics2D; // para alpha
    private JFrame _jFrame;
}
