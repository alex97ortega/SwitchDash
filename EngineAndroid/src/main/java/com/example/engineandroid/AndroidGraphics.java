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

public class AndroidGraphics implements es.ucm.gdv.engine.Graphics {

    public AndroidGraphics(SurfaceView surfaceView, AssetManager assetManager){
        _assetManager = assetManager;
        _surfaceView = surfaceView;
        _canvas = _surfaceView.getHolder().lockCanvas();//lockHardwareCanvas()
        _paint = new Paint();
    }
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

    @Override
    public void clear(int color){
        _canvas = _surfaceView.getHolder().lockCanvas(); //lockHardwareCanvas()
        _canvas.drawColor(color); // ARGB
    }
    @Override
    public void drawImage(Image img, Rect scr, Rect clip, float alpha){
        // para el alpha
        // pequeño control para que no casque el setAlpha
        int finalAlpha;
        if (alpha > 1.f) finalAlpha = 255;
        else if (alpha < 0) finalAlpha = 0;
        else finalAlpha = (int)(alpha*255);
        _paint.setAlpha(finalAlpha);

        android.graphics.Rect _src =  new android.graphics.Rect(scr.getA().getX(), scr.getA().getY(),
                                                                scr.getB().getX(), scr.getB().getY());
        android.graphics.Rect _clip = new android.graphics.Rect(clip.getA().getX(), clip.getA().getY(),
                                                                clip.getB().getX(), clip.getB().getY());

        _canvas.drawBitmap(((AndroidImage)img).getImg(), _clip, _src, _paint);
    }

    @Override
    public int getWidth() {
        return _surfaceView.getWidth();
    }

    @Override
    public int getHeight() {
        return _surfaceView.getHeight();
    }

    public boolean validSurface()
    {
        return _surfaceView.getHolder().getSurface().isValid();
    }
    public void present()
    {
        _surfaceView.getHolder().unlockCanvasAndPost(_canvas);
    }

    private AssetManager _assetManager ;
    private SurfaceView _surfaceView;
    private Canvas _canvas;
    private Paint _paint;
}
