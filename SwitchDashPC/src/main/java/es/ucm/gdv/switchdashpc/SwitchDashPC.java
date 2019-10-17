package es.ucm.gdv.switchdashpc;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*; //Importa todo lo que hay dentro de este paquete

public class SwitchDashPC extends JFrame{ // quitar jframe
    public static void main(String [] args)
    {
        SwitchDashPC ventana = new SwitchDashPC();

        ventana.init();

        ventana.setVisible(true);
    }
    public void init(){
        setSize(400,400);
        // Ojo esto es necesario para que se cierre la aplicacion una vez cerrada la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BOTONES
       /* JButton boton;
        // Texto boton
        boton = new JButton("Pulsame");
          add(boton);
        boton.addActionListener(new MiActionListener());*/

    }
}
