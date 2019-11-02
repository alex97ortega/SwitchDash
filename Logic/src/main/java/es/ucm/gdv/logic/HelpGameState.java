package es.ucm.gdv.logic;

import es.ucm.gdv.engine.Game;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;

public class HelpGameState extends BaseGameState  {
    HelpGameState(Game game, GameManager gm, GameManager.BackgroundColor c){
        super(game,gm);
        color = c;
        buttonCancel = new Button(game.getGraphics().getWidth()-170,100,_gm,GameManager.Buttons.CANCEL);
    }

    @Override
    public void update(double elapsedTime) {
        super.update(elapsedTime);
    }
    @Override
    public void render() {
        super.render();
        screen.render(color);

        // HOW TO PLAY
        Image img = _gm.getImage(GameManager.Images.HOWTOPLAY);
        int x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);
        int y = 40;

        _game.getGraphics().drawImage(img,
                new Rect(x,y,img.getWidth(),img.getHeight()),
                new Rect(0,0,img.getWidth(),img.getHeight()), 1.f);
        // INSTRUCTIONS
        Image img2 = _gm.getImage(GameManager.Images.INSTRUCTIONS);
        x = _game.getGraphics().getWidth()/2-((int)(img2.getWidth()/1.4f)/2);
        y = img.getHeight()+50;

        _game.getGraphics().drawImage(img2,
                new Rect(x,y,(int)(img2.getWidth()/1.4f),(int)(img2.getHeight()/1.4f)),
                new Rect(0,0,img2.getWidth(),img2.getHeight()), 1.f);

        // TapToPlay
        x = _game.getGraphics().getWidth()/2-(img.getWidth()/2);
        y += (int)(img2.getHeight()/1.4f)+10;
        screen.drawTapToPlay(x,y);

        buttonCancel.render(_game);
    }

    @Override
    public void onPress(int x, int y) {
        if(buttonCancel.inside(x,y)){
            _game.changeGameState(new StartGameState(_game,_gm, color));
            return;
        }
        // si hemos hecho click en cualqueir otro lado, comenzamos a jugar
        _game.changeGameState(new GamePlayState(_game,_gm));
    }

    private Button buttonCancel;
    private GameManager.BackgroundColor color;
}
