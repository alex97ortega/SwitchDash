package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class StartGameState extends BaseGameState {
    StartGameState(Game game, GameManager gm){
        super(game,gm);
        screen = new Screen(_game.getGraphics().getWidth(), _game.getGraphics().getHeight());
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

    }
    @Override
    public void render() {
        super.render();

        Image logo = _gm.getImage(GameManager.Images.LOGO);
        int x = _game.getGraphics().getWidth()/2-(logo.getWidth()/2);

        _game.getGraphics().drawImage(logo,
                new Rect(x,50,0,0),
                new Rect(0,0,logo.getWidth(),logo.getHeight()), 255);
    }

    @Override
    public void onPress(int x, int y) {
        System.out.print(_game.getInput().getTouchEvents());
    }
}
