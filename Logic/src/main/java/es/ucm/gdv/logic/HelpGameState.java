package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class HelpGameState extends BaseGameState  {
    HelpGameState(Game game, GameManager gm){
        super(game,gm);
        buttonCancel = new Button(-170,100,_gm,GameManager.Buttons.CANCEL);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);

    }
    @Override
    public void render() {
        super.render();

        // HOW TO PLAY
        Image img = _gm.getImage(GameManager.Images.HOWTOPLAY);
        int x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);

        _game.getGraphics().drawImage(img,
                new Rect(x,40,0,0),
                new Rect(0,0,img.getWidth(),img.getHeight()), 255);
        // INSTRUCTIONS
        Image img2 = _gm.getImage(GameManager.Images.INSTRUCTIONS);
        x = _game.getGraphics().getWidth()/2-(img2.getWidth()/2);

        _game.getGraphics().drawImage(img2,
                new Rect(x,img.getHeight()+40,0,0),
                new Rect(0,0,img2.getWidth(),img2.getHeight()), 255);

        buttonCancel.render(_game);
    }

    @Override
    public void onPress(int x, int y) {
        if(buttonCancel.inside(x,y)){
            _game.changeGameState(new StartGameState(_game,_gm));
            return;
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.changeGameState(new GamePlayState(_game,_gm));
    }

    private Button buttonCancel;
}
