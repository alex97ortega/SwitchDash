package com.example.engineandroid;

import android.graphics.Bitmap;

// clase Imagen para Android, será un Bitmap
// sólo hace falta constructora y los gets de momento
public class AndroidImage implements es.ucm.gdv.engine.Image {
    public AndroidImage(Bitmap image)
    {
        _image = image;
    }

    @Override
    public int getHeight()
    {
        return _image.getHeight();
    }

    @Override
    public int getWidth()
    {
        return _image.getWidth();
    }

    public Bitmap  getImg(){return _image;}
    private Bitmap _image;
}
