package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;

public class GamePlayState extends BaseGameState {
    GamePlayState(Game game, GameManager gm){
        super(game,gm);
        player = new Player(_game, _gm);

        balls = new Ball[4]; // sólo va a haber 4 bolas pintándose como máximo

        for (int i = 0;i < balls.length; i++){
            balls[i] = new Ball(-395*i,_game,_gm );
        }
        int rdn = (int)(Math.random()*GameManager.BackgroundColor.TOTAL_COLORS.ordinal());
        color = GameManager.BackgroundColor.values()[rdn];

        score = 0;
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        // update de las bolas
        for (Ball b: balls) {
            if(b.update(elapsedTime)){ // si llega hasta el player vemos el color
                if(b.getColor() != player.getColor()) // game over si el color es distinto
                    _game.changeGameState(new GameOverState(_game,_gm,color,score));
                else{
                    b.regenerate();
                    score++;
                }
            }
        }
        // muestro por pantalla el tiempo
    }
    @Override
    public void render() {
        screen.render(color);
        player.render(_game);
        for (Ball b: balls) {
            b.render(_game);
        }
        showScore();
    }

    @Override
    public void onPress(int x, int y) {
        player.changeColor();
    }

    private void showScore(){
        int x = _game.getGraphics().getWidth()-160;
        int y = 100;
        // por ahora suponemos que el score nunca va a pasar de 99
        if(score > 99)
            return;
        if(score > 9){
            screen.drawNumber(x-40,y, score/10);
            screen.drawNumber(x+30,y, score%10);
        }
        else
            screen.drawNumber(x,y, score%10);
    }
    private Player player;
    private Ball[] balls;

    private int score;
    private GameManager.BackgroundColor color;
}
