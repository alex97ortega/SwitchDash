package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class GameOverState extends BaseGameState{
    GameOverState(Game game, GameManager gm, GameManager.BackgroundColor c, int sc){
        super(game,gm);
        buttons = new Button[2];
        color=c;
        score = sc;
        buttons[0] = new Button(30,100,_gm,GameManager.Buttons.SOUND_ON);
        buttons[1] = new Button(game.getGraphics().getWidth()-170,100,_gm,GameManager.Buttons.HELP);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
    }
    @Override
    public void render() {
        super.render();
        screen.render(color);

        // Game Over
        Image img = _gm.getImage(GameManager.Images.GAMEOVER);
        int x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);
        int y = 250;
        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);
        // score
        img = _gm.getImage(GameManager.Images.SCOREFONT);
        x = _game.getGraphics().getWidth()/2-(img.getWidth()/15/2);
        y = _game.getGraphics().getHeight()/2;

        screen.drawNumber(x,y,score);
        screen.drawText(x-180, y+130, "points");

        // Play again
        /*img = _gm.getImage(GameManager.Images.TAPTOPLAY);
        x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);
        y = _game.getGraphics().getHeight()/2-(img.getHeight()/2);
        screen.drawTapToPlay(x,y);*/

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
            if(b.inside(x,y)){
                buttonClicked(b.getType());
                return;
            }
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.changeGameState(new GamePlayState(_game,_gm));
    }

    private void buttonClicked(GameManager.Buttons type){
        switch (type){
            case SOUND_ON:
                break;
            case HELP:
                _game.changeGameState(new HelpGameState(_game,_gm,color));
                break;
            default:
                break;
        }
    }
    private int score;
    private Button[] buttons;
    private GameManager.BackgroundColor color;
}