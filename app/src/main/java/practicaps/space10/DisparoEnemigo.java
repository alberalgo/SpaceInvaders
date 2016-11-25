package practicaps.space10;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Sergio on 20/11/2016.
 */

public class DisparoEnemigo implements Runnable {
    private ImageView enemigo;
    private ImageView disparo;
    private ImageView nave;
    private boolean disparando;
    private int sizeY;

    public DisparoEnemigo(ImageView enem, ImageView disp, ImageView n, int Y){
        enemigo = enem;
        disparo = disp;
        nave = n;
        disparando = false;
        sizeY = Y;
        disparo.setY(-sizeY);
        disparo.setVisibility(View.VISIBLE);
    }

    @Override
    public void run(){
        try{Thread.sleep(1000);}
        catch(Exception e){};
        while(true){
            try {
                disparar();
                moverDisparo();
                Thread.sleep(1);
            }
            catch(Exception e){}
        }
    }

    public void disparar() throws Exception{
        if((disparando==false)&&(enemigo.getX()-40 <= nave.getX())&&(enemigo.getX()+40 >= nave.getX())){
            int numeroAleatorio = (int) (Math.random()*300+1);
            if(numeroAleatorio == 10){
                disparando = true;
                disparo.setY(enemigo.getY());
                disparo.setX(enemigo.getX());
            }
        }
    }

    public void moverDisparo() throws Exception{
        if(disparando==true){
            disparo.setY(disparo.getY()+1);
            if(disparo.getY()>sizeY+40) disparando = false;
        }
    }
}
