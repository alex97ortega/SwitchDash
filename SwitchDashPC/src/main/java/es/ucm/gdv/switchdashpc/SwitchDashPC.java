package es.ucm.gdv.switchdashpc;

import javax.swing.JFrame;

import es.ucm.gdv.enginePC.PCGame;
import es.ucm.gdv.logic.GameManager;

public class SwitchDashPC {

    public SwitchDashPC(String title, int windowWidth, int windowHeight ){
        _jFrame = new JFrame(title);
        _jFrame.setSize(windowWidth,windowHeight);
        _jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean init(){
        pcGame = new PCGame(_jFrame);

        if (!pcGame.init()) return false;

        resourcesPaths = new String[numResources];

        resourcesPaths[0]  = "./Sprites/arrowsBackground.png";
        resourcesPaths[1]  = "./Sprites/backgrounds.png";
        resourcesPaths[2]  = "./Sprites/balls.png";
        resourcesPaths[3]  = "./Sprites/buttons.png";
        resourcesPaths[4]  = "./Sprites/gameOver.png";
        resourcesPaths[5]  = "./Sprites/howToPlay.png";
        resourcesPaths[6]  = "./Sprites/instructions.png";
        resourcesPaths[7]  = "./Sprites/playAgain.png";
        resourcesPaths[8]  = "./Sprites/players.png";
        resourcesPaths[9]  = "./Sprites/scoreFont.png";
        resourcesPaths[10] = "./Sprites/switchDashLogo.png";
        resourcesPaths[11] = "./Sprites/tapToPlay.png";
        resourcesPaths[12] = "./Sprites/white.png";

        return true;
    }

    public void start(){

        // inicializar lógica
        GameManager gm = new GameManager(pcGame);
        gm.init(resourcesPaths);

        pcGame.run();
    }

    public static void main(String [] args)
    {
        SwitchDashPC switchDashPC = new SwitchDashPC("SwitchDash", 1920, 1080);
        if(!switchDashPC.init())
            return;
        switchDashPC.start();
    }
    protected PCGame pcGame;
    protected JFrame _jFrame;

    private String[] resourcesPaths;
    private final int numResources = 13;
}
