package com.example.engineandroid;

import android.media.MediaPlayer;

import es.ucm.gdv.engine.Sound;

// clase para un sonido en android usando MediaPlayer
public class AndroidSound implements Sound {
    public AndroidSound(MediaPlayer sound){_sound=sound;}
    @Override
    public void play()
    {
        if(_sound!=null)
            _sound.start();
    }
    @Override
    public void stop()
    {
        if(_sound!=null)
            _sound.stop();
    }
    public MediaPlayer getSound(){return _sound;}
    private MediaPlayer _sound;
}
