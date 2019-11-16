package es.ucm.gdv.enginePC;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class PCInput extends Applet implements es.ucm.gdv.engine.Input, MouseListener, KeyListener {
    PCInput(JFrame jFrame){
        events  = new ArrayList<>();
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
    }
    @Override
    public List<TouchEvent> getTouchEvents() {
        return events;
    }
    @Override
    public void  deleteEvents(){
        events.clear();
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        events.add(new TouchEvent(TouchEvent.Type.Pressed, mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        events.add(new TouchEvent(TouchEvent.Type.Released, mouseEvent.getX(), mouseEvent.getY()));
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private ArrayList<TouchEvent> events;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
            events.add(new TouchEvent(TouchEvent.Type.Pressed, 0,0));
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE)
            events.add(new TouchEvent(TouchEvent.Type.Released, 0,0));
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
