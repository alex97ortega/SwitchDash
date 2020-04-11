package com.example.engineandroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import es.ucm.gdv.engine.Sound;
import es.ucm.gdv.engine.SoundManager;

// clase gestora del sonido en Android
public class AndroidSoundManager implements SoundManager {
    //constructora
    public AndroidSoundManager(Context context){_context = context;}

    // cargar un sonido
    @Override
    public Sound loadSound(String name) {
        AndroidSound sonido;
        // así deberia ser la forma correcta, pero sigue sin funcionarnos
        Uri myUri = Uri.parse(name);
        sonido = new AndroidSound(MediaPlayer.create(_context, myUri));
        return sonido;
    }

    // liberar un sonido
    @Override
    public void freeSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        if(tmp == null){
            System.out.println("No hay sonido que liberar");
            return;
        }
        tmp.release();
    }

    // reproducir un sonido
    @Override
    public void playSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        if(tmp == null){
            System.out.println("No hay sonido que reproducir");
            return;
        }
        tmp.start();
    }

    // parar un sonido
    @Override
    public void stopSound(Sound sonido) {
        MediaPlayer tmp = ((AndroidSound)(sonido)).getSound();
        if(tmp == null){
            System.out.println("No hay sonido que parar");
            return;
        }
        tmp.stop();
    }
    private Context _context;
}
