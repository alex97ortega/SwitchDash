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
    public Image getImage(Images img){return images[img.ordinal()];}
    public int getColor(BackgroundColor color){return color.ordinal();}

    private Game _game;
    private Image[] images;


    public enum Images{
        ARROWS,
        BACKGROUND,
        BALLS,
        BUTTONS,
        GAMEOVER,
        HOWTOPLAY,
        INSTRUCTIONS,
        PLAYAGAIN,
        PLAYERS,
        SCOREFONT,
        LOGO,
        TAPTOPLAY,
        WHITE,
        TOTAL_IMAGES
    }
    public enum BackgroundColor{
        GREEN,
        GREENISH_BLUE,
        CYAN,
        LIGHT_BLUE,
        PURPLE,
        DARK_BLUE,
        ORANGE,
        RED,
        BEIGE,
        TOTAL_COLORS
    }
}
