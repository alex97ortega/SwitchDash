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
        color = BackgroundColor.GREEN;
        _game.pushGameState(new StartGameState(_game, this));
    }

    private void initSprites(String[] imagePaths)
    {
        images = new Image[imagePaths.length];
        for (int i = 0; i < imagePaths.length;i++) {
            images[i] = _game.getGraphics().newImage(imagePaths[i]);
        }
    }
    public void buttonClicked(Button b){
        switch (b.getType()){
            case SOUND_ON:
                b.ChangeType(GameManager.Buttons.SOUND_OFF);
                break;
            case SOUND_OFF:
                b.ChangeType(GameManager.Buttons.SOUND_ON);
                break;
            case HELP:
                _game.changeGameState(new HelpGameState(_game,this));
                break;
            default:
                break;
        }
    }
    public Image getImage(Images img){return images[img.ordinal()];}

    public float getGameVelocity(){return gameVelocity;}
    public void setInitialVelocity(){gameVelocity = initialVelocity;}
    public void increaseGameVelocity(){gameVelocity += increaseVelocity;}
    public GameManager.BackgroundColor getColor(){return color;}
    public void setColor(GameManager.BackgroundColor newColor){ color = newColor;}

    private Game _game;
    private Image[] images;
    private float gameVelocity;
    private final float initialVelocity = 300;
    private final float increaseVelocity = 90;
    private GameManager.BackgroundColor color;

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
    public enum Buttons{
        HELP,
        CANCEL,
        SOUND_ON,
        SOUND_OFF,
        HOME,
        STAR,
        DOLLAR,
        SETTINGS,
        EXIT,
        SHOP,
        TOTAL_BUTTONS
    }
}
