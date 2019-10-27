package es.ucm.gdv.switchdashpc;

import javax.swing.JFrame;

import es.ucm.gdv.enginePC.PCGame;

public class SwitchDashPC {

    public SwitchDashPC(String title, int windowWidth, int windowHeight ){
        _jFrame = new JFrame(title);
        _jFrame.setSize(windowWidth,windowHeight);
        _jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean init(){
        pcGame = new PCGame(_jFrame);

        return pcGame.init();
    }

    public void run(){
        pcGame.run();
    }

    public static void main(String [] args)
    {
        SwitchDashPC switchDashPC = new SwitchDashPC("SwitchDashPC", 1920, 1080);
        if(!switchDashPC.init())
            return;
        switchDashPC.run();
    }

    protected PCGame pcGame;
    protected JFrame _jFrame;
}
