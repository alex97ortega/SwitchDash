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
        int rdn = (int)(Math.random()*GameManager.BackgroundColor.TOTAL_COLORS.ordinal()-1)+1;
        color = GameManager.BackgroundColor.values()[rdn];
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
        // update de las bolas
        for (Ball b: balls) {
            if(b.update(elapsedTime)){ // si llega hasta el player vemos el color
                if(b.getColor() != player.getColor()) // game over
                    _game.changeGameState(new StartGameState(_game,_gm));
                else
                    b.regenerate();
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
    }

    @Override
    public void onPress(int x, int y) {
        player.changeColor();
    }

    private Player player;
    private Ball[] balls;
    private GameManager.BackgroundColor color;
}
