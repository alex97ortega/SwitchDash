package es.ucm.gdv.switchdashpc;

import javax.swing.JFrame;

import es.ucm.gdv.enginePC.PCGame;


public class SwitchDashPC extends JFrame{

    public void init(int windowWidth, int windowHeight){

        setSize(windowWidth,windowHeight);
        // Ojo esto es necesario para que se cierre la aplicacion una vez cerrada la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setVisible(true);
        game = new PCGame(this);

        game.run();
    }
    public static void main(String [] args)
    {
        SwitchDashPC ventana = new SwitchDashPC();

        ventana.init(400,400);
    }
    private PCGame game;
}
