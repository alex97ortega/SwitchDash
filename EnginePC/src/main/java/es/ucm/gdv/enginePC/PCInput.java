package es.ucm.gdv.enginePC;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

// clase gestora del input para PC
public class PCInput implements es.ucm.gdv.engine.Input, MouseListener, KeyListener {

    // constructora
    PCInput(JFrame jFrame){
        events  = new ArrayList<>();
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
    }

    // obtener la lista de eventos
    @Override
    synchronized public List<TouchEvent> getTouchEvents() {
        List<TouchEvent> aux = (ArrayList)events.clone();

        if(!events.isEmpty())
            events.clear();

        return aux;
    }

    // llamadas del mouse, añade el evento que corresponda
    @Override
    synchronized public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    synchronized public void mousePressed(MouseEvent mouseEvent) {
        events.add(new TouchEvent(TouchEvent.Type.Pressed, mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    synchronized public void mouseReleased(MouseEvent mouseEvent) {
        events.add(new TouchEvent(TouchEvent.Type.Released, mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    synchronized  public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    synchronized public void mouseExited(MouseEvent mouseEvent) {

    }

    private ArrayList<TouchEvent> events;

    // llamadas para teclado, en este caso nos interesa que la tecla Espacio
    // devuelva el mismo evento que si se hubiera pulsado el ratón
    @Override
    synchronized public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
            events.add(new TouchEvent(TouchEvent.Type.Pressed, 0,0));
    }

    @Override
    synchronized public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
            events.add(new TouchEvent(TouchEvent.Type.Released, 0,0));
    }

    @Override
    synchronized public void keyTyped(KeyEvent keyEvent) {

    }
}
