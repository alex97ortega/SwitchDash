package es.ucm.gdv.engine;

public interface Graphics {
    Image newImage(String name);
    void clear(int color);
    void drawImage(Image img, Rect scr, Rect clip, float alpha);
    int getWidth();
    int getHeight();
}
