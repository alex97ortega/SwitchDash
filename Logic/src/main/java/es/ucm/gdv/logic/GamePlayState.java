package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;

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
                if(balls[i].getColor() != player.getColor()) // game over si el color es distinto
                    _game.changeGameState(new GameOverState(_game,_gm,score));
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

    }
    @Override
    public void render() {
        screen.render(_gm.getColor());
        player.render(_game);
        for (Ball b: balls) {
            b.render(_game);
        }
        int x = _game.getGraphics().getWidth()-160;
        int y = _gm.infoPosY;
        screen.drawScore(x,y,score);
    }

    @Override
    public void onPress(int x, int y) {
        player.changeColor();
    }

    private Player player;
    private Ball[] balls;

    private int score;
}
