package es.ucm.gdv.enginePC;

import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

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
        _graphics.setColor(new Color(color));
        _graphics.fillRect(0, 0, getWidth(), getHeight());
    }
    // scr es la superficie en pantalla de lo que queremos dibujr
    // clip es el recorte de la imagen que queremos
    @Override
    public void drawImage(Image img, Rect scr, Rect clip, int alpha) {
        java.awt.Image tmp = ((PCImage)(img)).getImg();
        _graphics.drawImage(tmp,
                scr.getA().getX(), scr.getA().getY(), scr.getB().getX(), scr.getB().getY(),
                clip.getA().getX(), clip.getA().getY(), clip.getB().getX(), clip.getB().getY(),null);
    }
    @Override
    public int getWidth() {
        return _jFrame.getWidth();
    }

    @Override
    public int getHeight() {
        return _jFrame.getHeight();
    }

    public void setGraphics(){_graphics=_bufferStrategy.getDrawGraphics();}
    public void dispose(){
        _bufferStrategy.getDrawGraphics().dispose();
    }
    boolean frameReady()
    {
        return (!_bufferStrategy.contentsRestored() && !_bufferStrategy.contentsLost());
    }
    public void show(){_bufferStrategy.show();}

    private BufferStrategy _bufferStrategy;
    private Graphics _graphics;
    private JFrame _jFrame;
}
