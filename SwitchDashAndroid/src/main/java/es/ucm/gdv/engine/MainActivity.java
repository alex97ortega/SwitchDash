package es.ucm.gdv.engine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.engineandroid.AndroidGame;

import es.ucm.gdv.logic.GameManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = new AndroidGame(this, getAssets());
        gm = new GameManager(game);
        String[] resourcesPaths = new String[numResources];

        resourcesPaths[0]  = "arrowsBackground.png";
        resourcesPaths[1]  = "backgrounds.png";
        resourcesPaths[2]  = "balls.png";
        resourcesPaths[3]  = "buttons.png";
        resourcesPaths[4]  = "gameOver.png";
        resourcesPaths[5]  = "howToPlay.png";
        resourcesPaths[6]  = "instructions.png";
        resourcesPaths[7]  = "playAgain.png";
        resourcesPaths[8]  = "players.png";
        resourcesPaths[9]  = "scoreFont.png";
        resourcesPaths[10] = "switchDashLogo.png";
        resourcesPaths[11] = "tapToPlay.png";
        resourcesPaths[12] = "white.png";

        // Preparamos el contenido de la actividad.
        setContentView(game);
        gm.init(resourcesPaths);
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.resume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();

    }
    protected AndroidGame game;
    protected GameManager gm;

    final int numResources = 13;
}
