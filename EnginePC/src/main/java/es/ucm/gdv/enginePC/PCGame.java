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

        _strategy = jFrame.getBufferStrategy();
        img = getGraphics().newImage("./Sprites/backgrounds.png");
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

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;
        // Bucle principal
        while(true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            // update();

            // Informe de FPS
            if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;
            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    try {
                        getGraphics().clear(0);
                        getGraphics().drawImage(img,100,100);
                    }
                    finally {
                        _graphics.dispose();
                    }
                } while(_strategy.contentsRestored());
                _strategy.show();
            } while(_strategy.contentsLost());
			/*
			// Posibilidad: cedemos algo de tiempo. es una medida conflictiva...
			try {
				Thread.sleep(1);
			}
			catch(Exception e) {}
			*/
        } // while
    }

    @Override
    public void stop() {

    }

    private PCGraphics _graphics;
    private PCInput _input;
    private java.awt.image.BufferStrategy _strategy;
    private JFrame _jFrame;
    Image img;
}
