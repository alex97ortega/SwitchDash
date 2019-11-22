package com.example.engineandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import es.ucm.gdv.engine.Sound;
import es.ucm.gdv.engine.SoundManager;

public class AndroidSoundManager implements SoundManager {
    public AndroidSoundManager(Context context){_context = context;}
    @Override
    public Sound loadSound(String name) {
        AndroidSound sonido;
        try {
            Uri myUri = Uri.parse(name);
            sonido = new AndroidSound(MediaPlayer.create(_context,  myUri));
        }
        catch (Exception e) {
            System.out.println("Falla mi uri");
            return null;
        }
        return sonido;
    }

    @Override
    public void freeSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        tmp.release();
    }

    @Override
    public void playSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        if(tmp == null){
            System.out.println("No hay sonido que reproducir");
            return;
        }
        tmp.start();
    }

    @Override
    public void stopSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        tmp.stop();
    }
    private Context _context;
}
