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

        // Preparamos el contenido de la actividad.
        setContentView(game.getSurfaceView());
        gm.init();
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

}
