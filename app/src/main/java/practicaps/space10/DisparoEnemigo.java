package practicaps.space10;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Sergio on 20/11/2016.
 */

public class DisparoEnemigo extends Thread {
    private ImageView enemigo;
    private ImageView disparo;
    private ImageView nave;
    private boolean disparando;
    private int sizeY;
    private boolean fin = false;
    private int velocidad;
    private int probabilidadDisparo;

    public DisparoEnemigo(ImageView enem, ImageView disp, ImageView n, int Y){
        enemigo = enem;
        disparo = disp;
        nave = n;
        disparando = false;
        sizeY = Y;
        disparo.setY(-sizeY);
        disparo.setVisibility(View.VISIBLE);
        velocidad = 2;
        probabilidadDisparo = 300;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    @Override
    public void run(){
        try{Thread.sleep(1000);}
        catch(Exception e){}
        while(!fin){

            try {
                disparar();
                moverDisparo();
                Thread.sleep(velocidad);
            }
            catch(Exception e){}
        }
    }

    public void disparar() throws Exception{
        if((disparando==false)&&(enemigo.getX()-40 <= nave.getX())&&(enemigo.getX()+40 >= nave.getX())){
            int numeroAleatorio = (int) (Math.random()*probabilidadDisparo+1);
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

    public void aumentarVelocidad(){
        if(velocidad==1){}
        else velocidad-=1;
    }

    public void aumentarProbDisparo(){
        if(velocidad<=10){}
        else velocidad-=10;
    }
}
