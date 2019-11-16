package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Input;

// comportamientos que sean comunes a todos los estados de juego
public class BaseGameState implements es.ucm.gdv.engine.GameState {

    BaseGameState(Game game, GameManager gm){
        _game = game;
        _gm = gm;
        screen = new Screen(_game.getGraphics().getWidth(), _game.getGraphics().getHeight(), _game.getGraphics(), _gm);
        screen.doFlashEffect();
    }
    @Override
    public void update(double elapsedTime) {

        screen.update(elapsedTime);
        if(!_game.getInput().getTouchEvents().isEmpty()){
            // Esta basura está petando a veces y no le veo el sentido
            // No me da ninguna pista clara y además parece que es sólo
            // en el ordenador de mi casa, en los labs no pasa
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

    }
    @Override
    public void onPress(int x, int y){

    }

    protected Game _game;
    protected GameManager _gm;
    protected Screen screen;
}
