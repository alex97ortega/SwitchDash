package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class StartGameState extends BaseGameState {
    StartGameState(Game game, GameManager gm){
        super(game,gm);
        buttons = new Button[2];
        if(_gm.hasSound())
            buttons[0] = new Button(Button.Position.LEFT,_gm,GameManager.Buttons.SOUND_ON);
        else
            buttons[0] = new Button(Button.Position.LEFT,_gm,GameManager.Buttons.SOUND_OFF);
        buttons[1] = new Button(Button.Position.RIGHT,_gm,GameManager.Buttons.HELP);
        _gm.setInitialVelocity();
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
    }
    @Override
    public void render() {
        super.render();

        // logo
        Image img = _gm.getImage(GameManager.Images.LOGO);
        int x = (int)(( _screen.getWidth() / 2) - ((img.getWidth() * _graphics.getScale()) / 2));
        int y = (int)(_gm.logoPosY*_graphics.getScale());
        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                        new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);

        // tap to play
        img = _gm.getImage(GameManager.Images.TAPTOPLAY);
        x = (int)(( _screen.getWidth() / 2) - ((img.getWidth() * _graphics.getScale()) / 2));
        y = (int)(_gm.tapToPlayPosY1*_graphics.getScale());

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
            if(b.inside(x,y, _game.getGraphics())){
                _gm.buttonClicked(b);
                return;
            }
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.pushGameState(new GamePlayState(_game,_gm));
    }
    private Button[] buttons;
}
