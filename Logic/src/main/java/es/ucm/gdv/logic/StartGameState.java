package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.Sound;

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
        screen.render(_gm.getColor());

        // logo
        Image img = _gm.getImage(GameManager.Images.LOGO);
        int x =  _gm.refScreenWidth/2-(img.getWidth()/2);
        int y = _gm.logoPosY;
        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);

        // tap to play
        img = _gm.getImage(GameManager.Images.TAPTOPLAY);
        x =  _gm.refScreenWidth/2-(img.getWidth()/2);
        y = _gm.tapToPlayPosY1;

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
            if(b.inside(x,y, _game.getGraphics())){
                _gm.buttonClicked(b);
                return;
            }
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.changeGameState(new GamePlayState(_game,_gm));
    }
    private Button[] buttons;
}
