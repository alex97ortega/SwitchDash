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

    public float getGameVelocity(){
        return gameVelocity / _game.getGraphics().getRelationY();
    }
    public void setInitialVelocity(){gameVelocity = initialVelocity;}
    public void increaseGameVelocity(){gameVelocity += increaseVelocity;}

    public void setColor(GameManager.BackgroundColor newColor){ color = newColor;}
    public GameManager.BackgroundColor getColor(){return color;}

    public int getBallSeparation(){
        return ballSeparation;
    }
    public float getRelationX(){
        return _game.getGraphics().getRelationX();
    }
    public float getRelationY(){
        return _game.getGraphics().getRelationY();
    }
    private Game _game;
    private Image[] images;
    private float gameVelocity;
    private GameManager.BackgroundColor color;

    // constantes del juego
    public final int playerPosY = 1200; // para 1080x1920
    public final int numBalls = 4;
    private final int ballSeparation = 395;
    private final float initialVelocity = 430;
    private final float increaseVelocity = 90;

    public final int infoPosY = 100;
    public final int gameOverPosY = 364;
    public final int howToPlayPosY = 290;
    public final int instructionsPosY = 768;
    public final int playAgainPosY = 1396;
    public final int logoPosY = 356;
    public final int tapToPlayPosY1 = 950;
    public final int tapToPlayPosY2 = 1464;

    public final int refScreenWidth = 1080;
    public final int refScreenHeight = 1920;

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
