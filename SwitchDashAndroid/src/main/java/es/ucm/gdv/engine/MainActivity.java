package es.ucm.gdv.engine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


import android.content.Context;
import android.view.SurfaceHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream inputStream = null;
        try {
            AssetManager assetManager = this.getAssets();
            inputStream = assetManager.open("backgrounds.png");
            _sprite = BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException e) {
            android.util.Log.e("MainActivity", "Error leyendo el sprite");
        }
        finally {
            // Haya pasado lo que haya pasado, cerramos el stream.
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch(Exception e) {
                    // Esto no debería ocurrir nunca... y si ocurre, el
                    // usuario tampoco tiene mucho de qué preocuparse,
                    // ¿para qué molestarle?
                }
            } // if (inputStream != null)
        } // try-catch-finally

        // Preparamos el contenido de la actividad.
        _renderView = new MySurfaceView(this);
        setContentView(_renderView);
    }
    @Override
    protected void onResume() {

        // Avisamos a la vista (que es la encargada del active render)
        // de lo que está pasando.
        super.onResume();
        _renderView.resume();

    } // onResume

    @Override
    protected void onPause() {

        // Avisamos a la vista (que es la encargada del active render)
        // de lo que está pasando.
        super.onPause();
        _renderView.pause();

    } // onPause

    protected MySurfaceView _renderView;
    protected Bitmap _sprite;

    class MySurfaceView extends SurfaceView implements Runnable {

        /**
         * Constructor.
         *
         * @param context Contexto en el que se integrará la vista
         *                (normalmente una actividad).
         */
        public MySurfaceView(Context context) {

            super(context);
            _holder = getHolder();

        } // MySurfaceView

        public void resume() {

            if (!_running) {
                // Solo hacemos algo si no nos estábamos ejecutando ya
                // (programación defensiva, nunca se sabe quién va a
                // usarnos...)
                _running = true;
                // Lanzamos la ejecución de nuestro método run()
                // en una hebra nueva.
                _renderThread = new Thread(this);
                _renderThread.start();
            } // if (!_running)

        } // resume

        public void pause() {

            if (_running) {
                _running = false;
                while (true) {
                    try {
                        _renderThread.join();
                        _renderThread = null;
                        break;
                    } catch (InterruptedException ie) {
                        // Esto no debería ocurrir nunca...
                    }
                } // while(true)
            } // if (_running)

        } // pause

        @Override
        public void run() {

            if (_renderThread != Thread.currentThread()) {
                // ¿¿Quién es el tuercebotas que está llamando al
                // run() directamente?? Programación defensiva
                // otra vez, con excepción, por merluzo.
                throw new RuntimeException("run() should not be called directly");
            }

            if (_sprite != null)
                _imageWidth = _sprite.getWidth();

            // Antes de saltar a la simulación, confirmamos que tenemos
            // un tamaño mayor que 0. Si la hebra se pone en marcha
            // muy rápido, la vista podría todavía no estar inicializada.
            while(_running && getWidth() == 0)
                // Espera activa. Sería más elegante al menos dormir un poco.
                ;

            long lastFrameTime = System.nanoTime();

            long informePrevio = lastFrameTime; // Informes de FPS
            int frames = 0;

            // Bucle principal.
            while(_running) {

                long currentTime = System.nanoTime();
                long nanoElapsedTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                double elapsedTime = (double) nanoElapsedTime / 1.0E9;
                update(elapsedTime);
                // Informe de FPS
                if (currentTime - informePrevio > 1000000000l) {
                    long fps = frames * 1000000000l / (currentTime - informePrevio);
                    System.out.println("" + fps + " fps");
                    frames = 0;
                    informePrevio = currentTime;
                }
                ++frames;

                // Pintamos el frame
                while (!_holder.getSurface().isValid())
                    ;
                Canvas canvas = _holder.lockCanvas();
                render(canvas);
                _holder.unlockCanvasAndPost(canvas);
                /*
                // Posibilidad: cedemos algo de tiempo. es una medida conflictiva...
                try {
                    Thread.sleep(1);
                }
                catch(Exception e) {}
    			*/

            } // while

        } // run

        protected void update(double deltaTime) {
            int maxX = getWidth() - _imageWidth;

            _x += _incX * deltaTime;
            while(_x < 0 || _x > maxX) {
                // Vamos a pintar fuera de la pantalla. Rectificamos.
                if (_x < 0) {
                    // Nos salimos por la izquierda. Rebotamos.
                    _x = -_x;
                    _incX *= -1;
                }
                else if (_x > maxX) {
                    // Nos salimos por la derecha. Rebotamos
                    _x = 2*maxX - _x;
                    _incX *= -1;
                }
            } // while

        } // update

        protected void render(Canvas c) {

            c.drawColor(0xFF0000FF); // ARGB
            if (_sprite != null) {
                c.drawBitmap(_sprite, (int) _x, 100, null);
            } // if (_sprite != null)

        } // render

        protected double _x = 0;
        int _incX = 50;
        int _imageWidth;
        Thread _renderThread;
        SurfaceHolder _holder;
        volatile boolean _running = false;

    } // class MySurfaceView

}
