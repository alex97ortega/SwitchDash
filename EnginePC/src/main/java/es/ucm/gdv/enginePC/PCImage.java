package es.ucm.gdv.enginePC;

import java.awt.image.BufferedImage;

public class PCImage implements es.ucm.gdv.engine.Image {

    public PCImage(BufferedImage image)
    {
        _image = image;
    }

    @Override
    public int getHeight()
    {
        return _image.getHeight(null);
    }

    @Override
    public int getWidth()
    {
        return _image.getWidth(null);
    }

    public BufferedImage  getImg(){return _image;}

    private BufferedImage _image;
}
