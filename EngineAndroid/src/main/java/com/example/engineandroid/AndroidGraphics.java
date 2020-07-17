package com.example.engineandroid;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

// Manager encargado de la gestión gráfica
public class AndroidGraphics implements es.ucm.gdv.engine.Graphics {

    // constructora
    public AndroidGraphics(SurfaceView surfaceView, AssetManager assetManager){
        _assetManager = assetManager;
        _surfaceView = surfaceView;
        _canvas = _surfaceView.getHolder().lockCanvas();//lockHardwareCanvas()
        _paint = new Paint();

        _crop = new float[2];
        _crop[0] = 0.0f;
        _crop[1] = 0.0f;

        _scale = 0.5f;
    }

    // proporcionando una ruta, devuelve la imagen correspondiente si existe
    @Override
    public Image newImage(String name) {

        InputStream inputStream = null;
        AndroidImage image;
        try {
            inputStream = _assetManager.open(name);
            image = new AndroidImage(BitmapFactory.decodeStream(inputStream));
        }
        catch (IOException e) {
            android.util.Log.e("MainActivity", "Error leyendo el sprite");
            return null;
        }
        finally {
            // Haya pasado lo que haya pasado, cerramos el stream.
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch(Exception e) {
                }
            }
        }
        return image;
    }

    // clear (limpiar pantalla)
    @Override
    public void clear(int color){
        _canvas = _surfaceView.getHolder().lockCanvas(); //lockHardwareCanvas()
        _canvas.drawColor(color); // ARGB
    }
    @Override
    public void clearCrop(int color, int x, int y, int w, int h) {

    }
    // dibuja una imagen (en este caso un bitmap)
    // proporcionando la posicion y el tamaño en el Rect scr
    // y el trozo de imagen que queremos de la imagen completa en el Rect clip
    @Override
    public void drawImage(Image img, Rect scr, Rect clip, float alpha){
        // para el alpha
        // pequeño control para que no casque el setAlpha
        int finalAlpha;
        if (alpha > 1.f) finalAlpha = 255;
        else if (alpha < 0) finalAlpha = 0;
        // se le pasa de 0 a 1 y se convierte de 0 a 255 por comodidad
        else finalAlpha = (int)(alpha*255);
        _paint.setAlpha(finalAlpha);


        android.graphics.Rect _clip = new android.graphics.Rect(clip.getA().getX(), clip.getA().getY(),
                                                                clip.getB().getX(), clip.getB().getY());

        android.graphics.Rect _src =  new android.graphics.Rect(scr.getX()+ (int)_crop[0],scr.getY()+ (int)_crop[1],
                scr.getX()+  (int)_crop[0] + (int)((float)scr.getW() * _scale), scr.getY()+(int)_crop[1] + (int)((float)scr.getH() * _scale));

        _canvas.drawBitmap(((AndroidImage)img).getImg(), _clip, _src, _paint);
    }

    // gets del tamaño de la pantalla
    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }


    @Override
    public float getScale() {  return _scale;  }

    @Override
    public void setScale(float scale) {

        _scale = scale;
    }
    @Override
    public float[] getCrop() {
        return _crop;
    }

    @Override
    public void setCrop(float[] crop) {

        _crop[0] = crop[0];
        _crop[1] = crop[1];
    }


    // llamadas hechas desde el run
    public boolean validSurface()
    {
        return _surfaceView.getHolder().getSurface().isValid();
    }
    public void present()
    {
        _surfaceView.getHolder().unlockCanvasAndPost(_canvas);
    }


    // variables privadas
    private AssetManager _assetManager ;
    private SurfaceView _surfaceView;
    private Canvas _canvas;
    private Paint _paint;
    private float _scale;
    private float [] _crop;
}
