package practicaps.space10;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.AudioAttributes;
import android.provider.MediaStore;
import android.media.SoundPool;
import android.media.AudioManager;

/**
 * Created by Marco on 21/10/2016.
 */

public class Disparo implements Runnable {

    private ImageView nave;
    private ImageView disparo;
    public float coordenadaX = 10;
    private TextView tou;
    private float coordenadaDisparo;




    public Disparo(ImageView nave, ImageView disparo) {
        this.nave = nave;
        this.disparo = disparo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Disparo(this.nave, this.disparo);
        }
    }

    public void Disparo(ImageView nave, ImageView disparo) {
        Log.d("Disparo: ", "Se ha producido un disparo");


        coordenadaDisparo = nave.getY();

        disparo.setX(nave.getX());
        Log.d("Coordenada disparo: ", String.valueOf(coordenadaDisparo));

        for (int i = 0; i < 50; i++) {
            int val = i;
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coordenadaDisparo = coordenadaDisparo - val;
            Log.d("PUM: ", String.valueOf(val));
            disparo.setY(coordenadaDisparo - val * 20);
        }
    }
}