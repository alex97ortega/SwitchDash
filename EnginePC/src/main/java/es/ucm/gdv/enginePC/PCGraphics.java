package es.ucm.gdv.enginePC;

import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

// clase encargada de la gestión gráfica en PC
public class PCGraphics implements es.ucm.gdv.engine.Graphics {

    // constructora, se le pasa una ventana

    public PCGraphics(JFrame jFrame){
        _jFrame = jFrame;
    }

    // Init: debemos devolver si se ha podido inicializar o no el graphics (buffer strategy)

    public boolean init(){

        _crop = new float[2];
        _crop[0] = 0.0f;
        _crop[1] = 0.0f;

        _scale = 1.0f;

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


    // proporcionando una ruta, devuelve la imagen correspondiente si existe
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

    // limpiar pantalla
    @Override
    public void clear(int color){
        _graphics2D.setColor(new Color(color));
        _graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }
    // bandas negras de relleno
    @Override
    public void clearCrop(int color, int x, int y, int w, int h) {
        try{
            Composite alphaComp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 1.0f);
            _graphics2D.setComposite(alphaComp);
        }
        catch(Exception e){
            System.err.println("Error en alpha");
        }
        _graphics2D.setColor( Color.BLACK);
        _graphics2D.fillRect(x, y, w, h);
    }

    // scr es la superficie en pantalla de lo que queremos dibujar
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

        _graphics2D.drawImage(tmp, scr.getX() + (int)_crop[0], scr.getY() + (int)_crop[1],
                scr.getX() + (int)_crop[0] + (int)((float)scr.getW() * _scale), scr.getY() + (int)_crop[1] + (int)((float)scr.getH() * _scale),
                clip.getA().getX(), clip.getA().getY(), clip.getB().getX(), clip.getB().getY(),null);
    }


    // gets del tamaño de la pantalla
    @Override
    public int getWidth() {
        return _jFrame.getWidth();
    }

    @Override
    public int getHeight() {
        return _jFrame.getHeight();
    }

    // get y set del scale
    @Override
    public float getScale() {  return _scale; }

    @Override
    public void setScale(float scale) { _scale = scale; }

    // get y set de las bandas
    @Override
    public float[] getCrop() {

        return _crop;
    }
    @Override
    public void setCrop(float[] crop) {

        _crop[0] = crop[0];
        _crop[1] = crop[1];

    }



    // llamadas hechas desde el run
    public void setGraphics(){_graphics2D=(Graphics2D)_bufferStrategy.getDrawGraphics();}

    public void dispose(){
        _bufferStrategy.getDrawGraphics().dispose();
    }

    boolean frameReady()
    {
        return (!_bufferStrategy.contentsRestored() && !_bufferStrategy.contentsLost());
    }

    public void show(){_bufferStrategy.show();}

    // variables privadas
    private BufferStrategy _bufferStrategy;
    private Graphics2D _graphics2D; // necesario para alpha
    private JFrame _jFrame;
    private float _scale;
    private float [] _crop;
}
