package es.ucm.gdv.enginePC;

import java.awt.Image;

public class PCImage implements es.ucm.gdv.engine.Image {

    public PCImage(Image image)
    {
        _image = image;
    }

    @Override
    public int getHeight()
    {
        return _image.getWidth(null);
    }

    @Override
    public int getWidth()
    {
        return _image.getHeight(null);
    }
    public Image getImg(){return _image;}
    // Image heredando de awt, no de Engine
    private  Image _image;
}
