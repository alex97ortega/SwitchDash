package es.ucm.gdv.switchdashpc;

import java.awt.Frame;

import javax.swing.JFrame;

import es.ucm.gdv.enginePC.PCGame;
import es.ucm.gdv.logic.GameManager;

public class SwitchDashPC {

    // constructora para pantalla con alto y ancho indicados
    public SwitchDashPC(String title, int windowWidth, int windowHeight ){
        _jFrame = new JFrame(title);

        _jFrame.setSize(windowWidth,windowHeight);
        _jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // constructora para pantalla completa
    public SwitchDashPC(){
        _jFrame = new JFrame();

        // para pantalla completa
        _jFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        // esta instrucción quita los bordes de la ventana
        _jFrame.setUndecorated(true);

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
        // juego con ventana definida (mitad de alto y ancho de la referencia 1080x1920)
        SwitchDashPC switchDashPC = new SwitchDashPC("SwitchDash", 1080/2, 1920/2);
        // juego a pantalla completa
        //SwitchDashPC switchDashPC = new SwitchDashPC();
        if(!switchDashPC.init())
            return;
        switchDashPC.start();
    }
    protected PCGame pcGame;
    protected JFrame _jFrame;
}
