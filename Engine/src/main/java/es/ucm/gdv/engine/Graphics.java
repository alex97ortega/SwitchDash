package es.ucm.gdv.engine;

// interfaz de clase encargada de la gestión gráfica
public interface Graphics {
    void setResolutionRef(int refX, int refY);
    Image newImage(String name);
    void clear(int color);
    void drawImage(Image img, Rect scr, Rect clip, float alpha);

    int getWidth();
    int getHeight();
    float getRelationX();
    float getRelationY();
    int getResolutionRefX();
    int getResolutionRefY();
}
