package es.ucm.gdv.enginePC;

import es.ucm.gdv.engine.Image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class PCGraphics implements es.ucm.gdv.engine.Graphics {

    public PCGraphics(JFrame jFrame){
        _jFrame = jFrame;

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
            }
        }
        _bufferStrategy = _jFrame.getBufferStrategy();
        setGraphics(_bufferStrategy.getDrawGraphics());
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
    public void drawImage(Image img, int x, int y){
        if (img != null) {
            _graphics.drawImage(((PCImage)(img)).getImg(), (int)x, y, null);
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

    public void setGraphics(Graphics graphics){_graphics=graphics;}
    public void dispose(){_graphics.dispose();}

    private BufferStrategy _bufferStrategy;
    private Graphics _graphics;
    private JFrame _jFrame;
}
