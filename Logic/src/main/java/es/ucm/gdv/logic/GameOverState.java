package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class GameOverState extends BaseGameState{
    GameOverState(Game game, GameManager gm,  int sc){
        super(game,gm);
        buttons = new Button[2];
        score = sc;
        if(_gm.hasSound())
            buttons[0] = new Button(Button.Position.LEFT,_gm,GameManager.Buttons.SOUND_ON);
        else
            buttons[0] = new Button(Button.Position.LEFT,_gm,GameManager.Buttons.SOUND_OFF);
        buttons[1] = new Button(Button.Position.RIGHT,_gm,GameManager.Buttons.HELP);

        _gm.setInitialVelocity();
        initialTime = System.nanoTime()/ 1.0E9;
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
    }
    @Override
    public void render() {
        super.render();
        screen.render(_gm.getColor());

        // Game Over
        Image img = _gm.getImage(GameManager.Images.GAMEOVER);
        int x = _gm.refScreenWidth/2-(img.getWidth()/2);
        int y = _gm.gameOverPosY;
        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);
        // score
        img = _gm.getImage(GameManager.Images.SCOREFONT);
        x = _gm.refScreenWidth/2-(img.getWidth()/15/2);
        y = _gm.refScreenHeight/2;

        screen.drawScore(x,y-50,score);
        screen.drawText(x-180, y+130, "points");

        // Play again
        img = _gm.getImage(GameManager.Images.PLAYAGAIN);
        x = _gm.refScreenWidth/2-(img.getWidth()/2);
        y = _gm.playAgainPosY;
        screen.drawAlphaImage(x,y,img);

        // buttons
        for (Button b:
                buttons) {
            b.render(_game);
        }
    }

    @Override
    public void onPress(int x, int y) {
        for (Button b:
                buttons) {
            if(b.inside(x,y,_game.getGraphics())){
                _gm.buttonClicked(b);
                return;
            }
        }
        // dejamos un segundo sin poder reiniciar partida
        // para que el jugador pueda ver el resultado, ya que es probable
        // que si estaba jugando el juego con mucha velocidad haga click sin querer
        // justo al haber cambiado el estado, y no da tiempo a ver el record.
        double currentTime = System.nanoTime()/ 1.0E9;
        if(currentTime - initialTime > 1)
            _game.changeGameState(new GamePlayState(_game,_gm));
    }

    private int score;
    private Button[] buttons;
    double initialTime;
}
