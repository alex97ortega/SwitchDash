package es.ucm.gdv.engine;

// interfaz de la clase encargada de la gestión de audio
public interface SoundManager {
    Sound loadSound(String name);
    void freeSound(Sound sound);
}
