package practicaps.space10;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Marco on 21/10/2016.
 */

public class Disparo extends Thread {

    private ImageView nave;
    private ImageView disparo;
    private float coordenadaDisparo;
    public Context cont;
    private int inicialY;
    public String sil;

    public Disparo(ImageView nave, ImageView disparo, Context c, int sizeY, String sil) {
        this.nave = nave;
        this.disparo = disparo;
        this.cont=c;
        this.sil=sil;
        inicialY = sizeY;
        disparo.setY(-inicialY);
        disparo.setVisibility(View.VISIBLE);
    }

    @Override
    public void run() {
        try{
            Disparo(this.nave, this.disparo);
        }
        catch(Exception e){}
    }

    public void Disparo (ImageView nave, ImageView disparo) throws Exception{
        //new Musica(cont).reproducir();
        if(!sil.equals("1")){
            new Musica(cont).reproducir();
        }

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