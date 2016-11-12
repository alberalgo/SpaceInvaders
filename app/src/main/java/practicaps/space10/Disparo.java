package practicaps.space10;

import android.content.Context;
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
    private float coordenadaDisparo;
    public Context cont;
    public Enemigo e;
    private int inicialY;



    public Disparo(ImageView nave, ImageView disparo, Context c, Enemigo enem, int sizeY) {
        this.nave = nave;
        this.disparo = disparo;
        this.cont=c;
        this.e=enem;
        inicialY = sizeY;
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
        new Musica(cont).reproducir();

        disparo.setX(nave.getX());
        disparo.setY(nave.getY());
        while(disparo.getY()>-70){
            try{
                disparo.setY(disparo.getY()-1);
                Thread.sleep(1);
            }
            catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}