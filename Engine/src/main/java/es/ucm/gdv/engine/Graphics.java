package es.ucm.gdv.engine;

public interface Graphics {
    Image newImage(String name);
    void clear(int color);
    // puede que se necesiten más parámetros para reescalado, alpha...
    void drawImage(Image img, int x, int y);
    int getWidth();
    int getHeight();
}
