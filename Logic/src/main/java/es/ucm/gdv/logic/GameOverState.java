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

        // Game Over
        Image img = _gm.getImage(GameManager.Images.GAMEOVER);
        int x = (int)(( _screen.getWidth() / 2) - ((img.getWidth() * _graphics.getScale()) / 2));
        int y = (int)(_gm.gameOverPosY*_graphics.getScale());
        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                        new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);
        // score
        x = (int)(( _screen.getWidth() / 2) - ((100 * _graphics.getScale()) / 2));
        y = (int)((_gm.gameOverPosY+600)*_graphics.getScale());

        _screen.drawScore(x,y,score);

        x = (int)(( _screen.getWidth() / 2) - ((510 * _graphics.getScale()) / 2));
        y = (int)((_gm.gameOverPosY+800)*_graphics.getScale());
        _screen.drawText(x, y, "points");

        // Play again
        img = _gm.getImage(GameManager.Images.PLAYAGAIN);
        x = _gm.refScreenWidth/2-(img.getWidth()/2);
        y = _gm.playAgainPosY;
        _screen.drawAlphaImage(x,y,img);

        // buttons
        for (Button b:
                buttons) {
            b.render(_game, _gm, _screen);
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
            _game.pushGameState(new GamePlayState(_game,_gm));
    }

    private int score;
    private Button[] buttons;
    double initialTime;
}
