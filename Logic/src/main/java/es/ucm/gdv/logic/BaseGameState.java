package es.ucm.gdv.logic;

import java.util.List;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Input;

// comportamientos que sean comunes a todos los estados de juego
public abstract  class BaseGameState implements es.ucm.gdv.engine.GameState {

    BaseGameState(Game game, GameManager gm){
        _game = game;
        _gm = gm;
        screen = new Screen(_game.getGraphics().getWidth(), _game.getGraphics().getHeight(), _game.getGraphics(), _gm);
        screen.doFlashEffect();
    }
    @Override
    public void update(double elapsedTime) {

        List<Input.TouchEvent> aux = _game.getInput().getTouchEvents();

        if(!aux.isEmpty()){
            for (Input.TouchEvent t: aux ) {
                if(t.type == Input.TouchEvent.Type.Pressed){
                    onPress((int)t.x, (int)t.y);
                }
            }
        }
        screen.update(elapsedTime);
    }

    @Override
    public void render() {

    }

    public abstract void onPress(int x, int y);


    protected Game _game;
    protected GameManager _gm;
    protected Screen screen;
}
