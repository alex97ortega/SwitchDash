package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Rect;

public class ParticleSystem {
    ParticleSystem(Player.Color color, GameManager _gm, Game game, Screen screen)
    {
        particles = new Particle[10];

        // 3 tamaños diferentes
        for (int i = 0;i < particles.length; i++){
            if(i>7)
                particles[i] = new Particle(color,_gm,2.5f, game, screen);
            else if (i>4)
                particles[i] = new Particle(color,_gm,2.f, game, screen);
            else
                particles[i] = new Particle(color,_gm,1.5f, game, screen);

        }
    }
    public void update(double elapsedTime){
        for (Particle p: particles) {
            p.update(elapsedTime);
        }
    }

    public void render(Game game){
        for (Particle p: particles) {
            p.render(game);
        }
    }
    Particle[] particles;
}
