package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Sound;

public class GamePlayState extends BaseGameState {
    GamePlayState(Game game, GameManager gm){
        super(game,gm);
        player = new Player( _gm);

        balls = new Ball[_gm.numBalls]; // sólo va a haber 4 bolas pintándose como máximo

        for (int i = 0;i < balls.length; i++){
            balls[i] = new Ball(i,_gm);
        }
        int rdn = (int)(Math.random()*GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        _gm.setColor(GameManager.BackgroundColor.values()[rdn]);

        score = 0;

    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        // update de las bolas
        for(int i = 0; i<balls.length; i++){
            if(balls[i].update(elapsedTime)){ // si llega hasta el player vemos el color

                particleSystem = new ParticleSystem(balls[i].getColor(), _gm, _game, _screen);
                sonidoBola(balls[i].getColor());

                if(balls[i].getColor() != player.getColor()) // game over si el color es distinto
                    _game.pushGameState(new GameOverState(_game,_gm,score));
                else{
                    int previousBall = i-1;
                    if(i==0)
                        previousBall = balls.length-1;

                    balls[i].regenerate(balls[previousBall].getColor());
                    score++;
                    if(score % 10 == 0)
                        _gm.increaseGameVelocity();
                }
            }
        }
        if(particleSystem != null)
            particleSystem.update(elapsedTime);
    }
    private void sonidoBola(Player.Color color)
    {
        if(_gm.hasSound()){
            Sound  sound;
            if( color == Player.Color.BLACK)
                sound = _gm.getSound(GameManager.Sounds.SOUND_BLACK);
            else
                sound = _gm.getSound(GameManager.Sounds.SOUND_WHITE);

            sound.play();
        }
    }
    @Override
    public void render() {
        super.render();
        player.render(_game, _screen);

        for (Ball b: balls) {
            b.render(_game, _screen);
        }

        int x = (int)( _screen.getWidth() - (130  * _graphics.getScale()));
        int y = (int)(_gm.infoPosY*_graphics.getScale());
        _screen.drawScore(x,y,score);

        if(particleSystem != null)
            particleSystem.render(_game);
    }

    @Override
    public void onPress(int x, int y) {
        player.changeColor();
    }

    private Player player;
    private Ball[] balls;
    private ParticleSystem particleSystem;

    private int score;
}
