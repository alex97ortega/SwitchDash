package com.example.engineandroid;

import android.media.MediaPlayer;

import es.ucm.gdv.engine.Sound;

public class AndroidSound implements Sound {
    public AndroidSound(MediaPlayer sound){_sound=sound;}
    public MediaPlayer getSound(){return _sound;}
    private MediaPlayer _sound;
}