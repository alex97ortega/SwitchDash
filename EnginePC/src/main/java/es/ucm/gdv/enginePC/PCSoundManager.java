package es.ucm.gdv.enginePC;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import es.ucm.gdv.engine.Sound;
import es.ucm.gdv.engine.SoundManager;

public class PCSoundManager implements SoundManager {
    @Override
    public Sound loadSound(String name) {
        PCSound sonido;
        try {
            sonido = new PCSound(AudioSystem.getClip());
            sonido.getSound().open(AudioSystem.getAudioInputStream(new File(name)));
        }
        catch (Exception e) {
            return null;
        }
        return sonido;
    }

    @Override
    public void freeSound(Sound sonido) {
        try {
            Clip tmp = ((PCSound)(sonido)).getSound();
            tmp.close();
        }
        catch (Exception e) {
            return;
        }
    }

    @Override
    public void playSound(Sound sonido) {
        try {
            Clip tmp = ((PCSound)(sonido)).getSound();
            tmp.start();
        }
        catch (Exception e) {
            System.out.println("Ningun sonido que reproducir");
            return;
        }
    }

    @Override
    public void stopSound(Sound sonido) {
        try {
            Clip tmp = ((PCSound)(sonido)).getSound();
            tmp.stop();
        }
        catch (Exception e) {
            return;
        }
    }
}

