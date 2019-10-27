package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class GamePlayState extends BaseGameState {
    GamePlayState(Game game, GameManager gm){
        super(game,gm);
        screen = new Screen(_game.getGraphics().getWidth(), _game.getGraphics().getHeight());
        player = new Player(_game, _gm);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

    }
    @Override
    public void render() {
        _game.getGraphics().clear(0);
        screen.render(_game.getGraphics(),_gm, GameManager.BackgroundColor.GREENISH_BLUE);
        player.render(_game);
    }

    @Override
    public void onPress(int x, int y) {
        player.changeColor();
    }
    private Player player;
}
