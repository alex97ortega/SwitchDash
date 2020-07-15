package es.ucm.gdv.enginePC;

import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

import java.awt.AlphaComposite;
import java.awt.Color;
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

    // establecemos la resolución que nos interese en el juego para el canvas lógico
    @Override
    public void setResolutionRef(int refX, int refY)
    {
        refScaleX = refX;
        refScaleY = refY;
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

        Rect scaledScr = scale(scr);

        _graphics2D.drawImage(tmp,
                scaledScr.getA().getX(), scaledScr.getA().getY(), scaledScr.getB().getX(), scaledScr.getB().getY(),
                clip.getA().getX(), clip.getA().getY(), clip.getB().getX(), clip.getB().getY(),null);
    }

    // devuelve una posicion y tamaño nuevos para el reescalado que haya
    private Rect scale( Rect oldScr){

        int newX = (int)(oldScr.getA().getX()*getRelationX());
        int newY = (int)(oldScr.getA().getY()*getRelationY());
        int newWidth = (int)(oldScr.getW() * getRelationX());
        int newHeight = (int)(oldScr.getH()*getRelationY());

        return new Rect(newX, newY, newWidth, newHeight);
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

    // gets de la resolución o relación de aspecto
    @Override
    public float getRelationX(){
        return getWidth()/(float)refScaleX;
    }
    @Override
    public float getRelationY(){
        return getHeight()/(float)refScaleY;
    }
    @Override
    public int getResolutionRefX(){ return refScaleX; }
    @Override
    public int getResolutionRefY(){ return refScaleY; }

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
    // canvas lógico
    private int refScaleX;
    private int refScaleY;
}
