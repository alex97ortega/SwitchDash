package es.ucm.gdv.enginePC;

import javax.sound.sampled.Clip;

import es.ucm.gdv.engine.Sound;

public class PCSound implements Sound {
    public PCSound(Clip clip){_clip = clip;}

    public  Clip getSound(){return _clip;}
    private Clip _clip;
}
