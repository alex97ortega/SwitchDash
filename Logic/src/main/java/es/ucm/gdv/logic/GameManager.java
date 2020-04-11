package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Sound;

public class GameManager {
    public GameManager(Game game)
    {
        _game = game;
    }
    public void init()
    {
        _game.getGraphics().setResolutionRef(1080,1920);
        initResources();
        color = BackgroundColor.GREEN;
        _game.pushGameState(new StartGameState(_game, this));

        musica = getSound(GameManager.Sounds.MUSIC);
        musica.play();
    }

    private void initResources()
    {
        resourcesPaths = new String[numImages];
        resourcesPaths[0]  = "Sprites/arrowsBackground.png";
        resourcesPaths[1]  = "Sprites/backgrounds.png";
        resourcesPaths[2]  = "Sprites/balls.png";
        resourcesPaths[3]  = "Sprites/buttons.png";
        resourcesPaths[4]  = "Sprites/gameOver.png";
        resourcesPaths[5]  = "Sprites/howToPlay.png";
        resourcesPaths[6]  = "Sprites/instructions.png";
        resourcesPaths[7]  = "Sprites/playAgain.png";
        resourcesPaths[8]  = "Sprites/players.png";
        resourcesPaths[9]  = "Sprites/scoreFont.png";
        resourcesPaths[10] = "Sprites/switchDashLogo.png";
        resourcesPaths[11] = "Sprites/tapToPlay.png";
        resourcesPaths[12] = "Sprites/white.png";

        images = new Image[numImages];
        for (int i = 0; i < numImages;i++) {
            images[i] = _game.getGraphics().newImage(resourcesPaths[i]);
        }
        soundsPaths = new String[numSounds];
        sounds = new Sound[numSounds];
        soundsPaths[0]  = "Sound/music.wav";
        soundsPaths[1]  = "Sound/soundB.wav";
        soundsPaths[2]  = "Sound/soundW.wav";
    }

    public void buttonClicked(Button b){
        switch (b.getType()){
            case SOUND_ON:
                b.ChangeType(GameManager.Buttons.SOUND_OFF);
                withSound = false;
                musica.stop();
                break;
            case SOUND_OFF:
                b.ChangeType(GameManager.Buttons.SOUND_ON);
                withSound = true;
                musica.play();
                break;
            case HELP:
                _game.pushGameState(new HelpGameState(_game,this));
                break;
            default:
                break;
        }
    }
    public Image getImage(Images img){return images[img.ordinal()];}
    public Sound getSound(Sounds sound){
        Sound newSound = _game.getSoundManager().loadSound(soundsPaths[sound.ordinal()]);
        return newSound;
    }
    public boolean hasSound(){return withSound;}

    public float getGameVelocity(){
        return gameVelocity ;
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

    // recursos
    String[] resourcesPaths ;
    String[] soundsPaths;
    private final int numImages = 13;
    private final int numSounds = 3;
    private Image[] images;
    private Sound[] sounds;
    private boolean withSound = true;
    private Sound musica;

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
    public enum Sounds{
        MUSIC,
        SOUND_BLACK,
        SOUND_WHITE,
        TOTAL_SOUNDS
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
