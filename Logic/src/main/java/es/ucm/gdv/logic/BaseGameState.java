package es.ucm.gdv.logic;

import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Input;

// comportamientos que sean comunes a todos los estados de juego
public abstract  class BaseGameState implements es.ucm.gdv.engine.GameState {

    BaseGameState(Game game, GameManager gm){
        _game = game;
        _gm = gm;
        _graphics = _game.getGraphics();
        _screen = new Screen(_game.getGraphics().getWidth(), _game.getGraphics().getHeight(), _game.getGraphics(), _gm);
        _screen.doFlashEffect();
        resize(_graphics.getWidth(), _graphics.getHeight());

        _lastWidth = 0;
        _lastHeight = 0;
    }
    @Override
    public void update(double elapsedTime) {

        // ver si se ha redimensionado la pantalla
        if(_lastWidth != _graphics.getWidth() || _lastHeight != _graphics.getHeight()) {

            resize(_graphics.getWidth(), _graphics.getHeight());

            _lastWidth = _graphics.getWidth();
            _lastHeight = _graphics.getHeight();

            _screen.updateArrowsInitialPos();
        }
        // input
        List<Input.TouchEvent> aux = _game.getInput().getTouchEvents();

        if(!aux.isEmpty()){
            for (Input.TouchEvent t: aux ) {
                // input onPress, presionar el ratón o el Escape o hacer un touch
                if(t.type == Input.TouchEvent.Type.Pressed){
                    onPress((int)(t.x- _graphics.getCrop()[0]), (int)(t.y- _graphics.getCrop()[1]));
                }
                // presionado boton ESC, salimos del juego (util para pantalla completa)
                else if (t.type == Input.TouchEvent.Type.Exit)
                {
                    _game.ExitGame();
                }
            }
        }
        _screen.update(elapsedTime);
    }

    @Override
    public void render() {

        _screen.render(_gm.getColor());

        _graphics.clearCrop(0, 0,  0, _graphics.getWidth(), (int)_graphics.getCrop()[1]);
        _graphics.clearCrop(0, 0,  (int)((float) _screen.getHeight() + _graphics.getCrop()[1]), _graphics.getWidth(),
                _graphics.getHeight() - (int)((float) _screen.getHeight() + _graphics.getCrop()[1]));
    }
    @Override
    public void resize(int width, int height) {

        float aspectRatio = (float)width/(float)height;

        float scale;

        float [] crop = new float[2];

        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop[0] = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop[1] = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH * scale;
        float h = (float)VIRTUAL_HEIGHT * scale;

        //Screen.getScreen(_graphics).setWidth((int)w);
        //Screen.getScreen(_graphics).setHeight((int)h);

        _screen.setWidth((int)w);
        _screen.setHeight((int)h);

        _graphics.setCrop(crop);
        _graphics.setScale(scale);
    }

    public abstract void onPress(int x, int y);


    protected Game _game;
    protected Graphics _graphics;
    protected GameManager _gm;
    protected Screen _screen;

    private int _lastWidth;
    private int _lastHeight;

    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 1920;
    private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
}
