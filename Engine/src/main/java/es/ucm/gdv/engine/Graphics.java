package es.ucm.gdv.engine;

// interfaz de clase encargada de la gestión gráfica
public interface Graphics {
    Image newImage(String name);
    void clear(int color);
    void drawImage(Image img, Rect scr, Rect clip, float alpha);
    int getWidth();
    int getHeight();
    float getRelationX();
    float getRelationY();

    int refScaleX = 1080;
    int refScaleY = 1920;
}
