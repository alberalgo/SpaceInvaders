package practicaps.space10;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.AudioAttributes;
import android.provider.MediaStore;
import android.media.SoundPool;
import android.media.AudioManager;

/**
 * Created by Marco on 21/10/2016.
 */

public class Disparo extends Thread {

    private ImageView nave;
    private ImageView disparo;
    private float coordenadaDisparo;
    public Context cont;
    private int inicialY;
    private boolean fin = false;


    public Disparo(ImageView nave, ImageView disparo, Context c, int sizeY) {
        this.nave = nave;
        this.disparo = disparo;
        this.cont=c;
        inicialY = sizeY;
        disparo.setY(-inicialY);
        disparo.setVisibility(View.VISIBLE);
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    @Override
    public void run() {

        try{Thread.sleep(1000);}
        catch(Exception e){}
        while (!fin) {
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
            catch (Exception e) {e.printStackTrace();}
        }
    }
}