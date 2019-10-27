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
        _graphics = _bufferStrategy.getDrawGraphics();

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
    @Override
    public void drawImage(Image img, Rect scr, Rect clip) {
        BufferedImage tmp = ((PCImage)(img)).getImg();

        if (tmp != null) {
            tmp = tmp.getSubimage(clip.getX(), clip.getY(), clip.getW(), clip.getH());
            _graphics.drawImage(tmp, scr.getX(), scr.getY(), null);
        }
    }
    @Override
    public int getWidth() {
        return _jFrame.getWidth();
    }

    @Override
    public int getHeight() {
        return _jFrame.getHeight();
    }

    public BufferStrategy getBuffer(){return _bufferStrategy;}

    private BufferStrategy _bufferStrategy;
    private Graphics _graphics;
    private JFrame _jFrame;


}
