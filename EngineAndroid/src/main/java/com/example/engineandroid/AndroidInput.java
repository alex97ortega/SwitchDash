package com.example.engineandroid;

import java.util.ArrayList;
import java.util.List;

public class AndroidInput implements es.ucm.gdv.engine.Input{
    AndroidInput()
    {
        events = new ArrayList<>();
    }
    synchronized public void addTouchEvent(TouchEvent event)
    {
        events.add(event);
    }
    @Override
    public List<TouchEvent> getTouchEvents() {
        return (ArrayList<TouchEvent>)events.clone();
    }
    @Override
    public void deleteEvents() {
        events.clear();
    }
    private ArrayList<TouchEvent> events;
}
