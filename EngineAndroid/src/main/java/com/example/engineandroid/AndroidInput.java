package com.example.engineandroid;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

// clase gestora del input para Android
public class AndroidInput implements es.ucm.gdv.engine.Input, View.OnTouchListener {
    // constructora
    AndroidInput(SurfaceView surfaceView)
    {
        events = new ArrayList<>();
        surfaceView.setOnTouchListener(this);
    }

    // obtener la lista de eventos
    @Override
    synchronized public List<TouchEvent> getTouchEvents() {
        List<TouchEvent> aux = (ArrayList)events.clone();

        if(!events.isEmpty())
            events.clear();

        return aux;
    }
    private ArrayList<TouchEvent> events;

    // Añade el evento que corresponda a la lista
    // los tres eventos posibles son
    // ACTION_DOWN al pulsar
    // ACTION_MOVE al mover el dedo mientrasse pulsa
    // ACTION_UP al levantar el dedo de la pantalla
    @Override
    synchronized public boolean onTouch(View v, MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                events.add(new TouchEvent(TouchEvent.Type.Pressed, x, y));
                break;
            case MotionEvent.ACTION_MOVE:
                // nada de momento
                break;
            case MotionEvent.ACTION_UP:
                events.add(new TouchEvent(TouchEvent.Type.Released, x, y));
                break;
        }
        return true;
    }
}
