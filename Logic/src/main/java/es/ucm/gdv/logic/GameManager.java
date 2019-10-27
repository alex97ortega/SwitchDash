package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;

public class GameManager {
    public GameManager(Game game)
    {
        _game = game;
    }
    public void init(String[] imagePaths)
    {
        initSprites(imagePaths);
        _game.pushGameState(new StartGameState(_game, this));
    }
    private void initSprites(String[] imagePaths)
    {
        images = new Image[imagePaths.length];
        for (int i = 0; i < imagePaths.length;i++) {
            images[i] = _game.getGraphics().newImage(imagePaths[i]);
        }
    }
    public Image[] getImages(){return images;} // esto habrá que cambiarlo

    private Game _game;
    private Image[] images;
}
