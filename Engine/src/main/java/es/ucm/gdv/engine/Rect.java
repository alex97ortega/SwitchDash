package es.ucm.gdv.engine;

// clase auxiliar para representar un rectángulo en pantalla
public class Rect {
    private Point a;
    private Point b;
    private int w;
    private int h;

    // constructora a partir de un x,y (esquina superior izquierda)
    // y un tamaño
    public Rect(int x, int y, int w, int h)
    {
        this.a = new Point(x, y);
        this.b = new Point(x +w, y + h);
        this.w = w;
        this.h = h;
    }
    // constructora a partir de un punto (esquina superior izquierda)
    // y un tamaño
    public Rect(Point p, int w, int h) {
        this.a = p;
        this.b = new Point(p.getX() + w, p.getY() + h);
        this.w = w;
        this.h = h;
    }
    // constructora a partir de dos puntos que formen la diagonal del Rectángulo
    public Rect(Point a, Point b)
    {
        this.a = a;
        this.b = b;
        this.w = Math.abs(a.getX() - b.getX());
        this.h = Math.abs(a.getY() - b.getY());
    }

    // gets y sets
    public int getX() { return a.getX();}
    public int getY() {return a.getY();}
    public Point getA()
    {
        return a;
    }

    public Point getB()
    {
        return b;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
