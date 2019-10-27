package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Input;
import es.ucm.gdv.engine.Rect;

// comportamientos que sean comunes a todos los estados de juego
public class BaseGameState implements es.ucm.gdv.engine.GameState {

    BaseGameState(Game game, GameManager gm){
        _game = game;
        _gm = gm;
    }
    @Override
    public void update(double elapsedTime) {

        screen.update(elapsedTime);
        if(!_game.getInput().getTouchEvents().isEmpty()){
            for (Input.TouchEvent t:_game.getInput().getTouchEvents() ) {
                if(t.type == Input.TouchEvent.Type.Pressed){
                    onPress((int)t.x, (int)t.y);
                }
            }
        }
        _game.getInput().deleteEvents();// VACIAR EVENTOS
    }

    @Override
    public void render() {
        _game.getGraphics().clear(0);
        screen.render(_game.getGraphics(),_gm, GameManager.BackgroundColor.GREEN);
    }
    @Override
    public void onPress(int x, int y){

    }

    protected Game _game;
    protected GameManager _gm;
    protected Screen screen;
}
