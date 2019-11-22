package es.ucm.gdv.engine;

public interface SoundManager {
    Sound loadSound(String name);
    void freeSound(Sound sound);

    void playSound(Sound sound);
    void stopSound(Sound sound);
}
