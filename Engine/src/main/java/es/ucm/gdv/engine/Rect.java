package es.ucm.gdv.engine;

public class Rect {
    private Point a;
    private Point b;
    private int w;
    private int h;

    public Rect(int x, int y, int w, int h)
    {
        this.a = new Point(x, y);
        this.b = new Point(x +w, y + h);
        this.w = w;
        this.h = h;
    }
    public Rect(Point p, int w, int h) {
        this.a = p;
        this.b = new Point(p.getX() + w, p.getY() + h);
        this.w = w;
        this.h = h;
    }
    public Rect(Point a, Point b)
    {
        this.a = a;
        this.b = b;
        this.w = Math.abs(a.getX() - b.getX());
        this.h = Math.abs(a.getY() - b.getY());
    }
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
