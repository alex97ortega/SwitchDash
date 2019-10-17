package es.ucm.gdv.engine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bitmap img;
        try {
            //Drawable.createFromPath(name);

            InputStream inputStream = _assetManager.open("backgrounds.png");

            img = BitmapFactory.decodeStream(inputStream);
        }catch (Exception e)
        {
            System.out.print("Couldn't open "  + '\n');
        }
        //canvas.drawBitmap(bmp, 10, 10, null);
    }

    AssetManager _assetManager;
}
