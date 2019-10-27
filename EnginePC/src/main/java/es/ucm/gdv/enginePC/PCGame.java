package es.ucm.gdv.enginePC;

import javax.swing.JFrame;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;

public class PCGame implements es.ucm.gdv.engine.Game{

    public PCGame(JFrame jFrame){
        _jFrame = jFrame;
        _graphics = new PCGraphics(_jFrame);
        _input = new PCInput();

    }
    public boolean init(){
        _logo = _graphics.newImage("./Sprites/players.png");
        if(_logo == null)    return false;

        _jFrame.setIgnoreRepaint(true);
        _jFrame.setVisible(true);

        return _graphics.init();
    }
    public void render(){
        _graphics.clear(255);

        if (_logo != null) {
            _graphics.drawImage(_logo, 0, 100);
        }
    }
    @Override
    public Graphics getGraphics() { return _graphics; }

    @Override
    public Input getInput() {
        return _input;
    }

    @Override
    public void run() {

        long lastFrameTime = System.nanoTime();

        // Bucle principal
        while(true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            //switchDashPC.update(elapsedTime);

            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    java.awt.Graphics graphics = _graphics.getBuffer().getDrawGraphics();
                    try {
                        render();
                    }
                    finally {
                        graphics.dispose();
                    }
                } while(_graphics.getBuffer().contentsRestored());
                _graphics.getBuffer().show();
            } while(_graphics.getBuffer().contentsLost());
        } // while
    }

    @Override
    public void stop() {

    }

    private PCGraphics _graphics;
    private PCInput _input;
    private JFrame _jFrame;
    protected Image _logo;

}
