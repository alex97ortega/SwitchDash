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
        return true;
    }

    public void start(){

        // inicializar lógica
        GameManager gm = new GameManager(pcGame);
        gm.init();

        pcGame.run();
    }

    public static void main(String [] args)
    {
        SwitchDashPC switchDashPC = new SwitchDashPC("SwitchDash", 1080/2, 1920/2);
        if(!switchDashPC.init())
            return;
        switchDashPC.start();
    }
    protected PCGame pcGame;
    protected JFrame _jFrame;
}
