package es.ucm.gdv.engine;

// interfaz de clase encargada de la gestión gráfica
public interface Graphics {
    Image newImage(String name);
    void clear(int color);
    void clearCrop(int color, int x, int y, int w, int h);
    void drawImage(Image img, Rect scr, Rect clip, float alpha);

    int getWidth();
    int getHeight();
    void setScale(float scale);

    float getScale();

    void setCrop(float [] crop);

    float[] getCrop();
}
