package es.ucm.gdv.engine;

public interface Graphics {
    Image newImage(String name);
    void clear(int color);
    void drawImage(Image img); // puede que se necesiten más parámetros para reescalado, alpha...
    int getWidth();
    int getHeight();
}
