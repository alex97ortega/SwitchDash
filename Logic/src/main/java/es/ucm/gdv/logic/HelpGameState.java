package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class HelpGameState extends BaseGameState  {
    HelpGameState(Game game, GameManager gm){
        super(game,gm);
        buttonCancel = new Button(Button.Position.RIGHT,_gm,GameManager.Buttons.CANCEL);
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
        int x = (int)(( _screen.getWidth() / 2) - ((img.getWidth() * _graphics.getScale()) / 2));
        int y = (int)(_gm.howToPlayPosY*_graphics.getScale());

        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                        new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);
        // INSTRUCTIONS
        Image img2 = _gm.getImage(GameManager.Images.INSTRUCTIONS);
        x = (int)(( _screen.getWidth() / 2) - ((img2.getWidth() * _graphics.getScale()) / 2));
        y = (int)(_gm.instructionsPosY*_graphics.getScale());

        _game.getGraphics().drawImage(img2,
                new Rect(x,y,img2.getWidth(),img2.getHeight()),
                        new Rect(0,0,img2.getWidth(),img2.getHeight()), 1.f);

        // TapToPlay
        img = _gm.getImage(GameManager.Images.TAPTOPLAY);
        x = (int)(( _screen.getWidth() / 2) - ((img2.getWidth() * _graphics.getScale()) / 2));
        y = (int)(_gm.tapToPlayPosY2*_graphics.getScale());
        _screen.drawAlphaImage(x,y,img);

        buttonCancel.render(_game, _gm, _screen);
    }

    @Override
    public void onPress(int x, int y) {
        if(buttonCancel.inside(x,y,_game.getGraphics())){
            _game.pushGameState(new StartGameState(_game,_gm));
            return;
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.pushGameState(new GamePlayState(_game,_gm));
    }

    private Button buttonCancel;
}
