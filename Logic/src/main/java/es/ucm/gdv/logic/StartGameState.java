package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class StartGameState extends BaseGameState {
    StartGameState(Game game, GameManager gm){
        super(game,gm);
        buttons = new Button[2];
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

        Image logo = _gm.getImage(GameManager.Images.LOGO);
        int x = _game.getGraphics().getWidth()/2-(logo.getWidth()/2);

        _game.getGraphics().drawImage(logo,
                new Rect(x,50,logo.getWidth(),logo.getHeight()),
                new Rect(0,0,logo.getWidth(),logo.getHeight()), 255);

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
                _game.changeGameState(new HelpGameState(_game,_gm));
                break;
            default:
                break;
        }
    }
    private Button[] buttons;
}
