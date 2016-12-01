package practicaps.space10;

import android.widget.ImageView;

/**
 * Created by Sergio on 12/11/2016.
 */

public class naveB extends Thread {
    private ImageView naveBon;
    private float inicialX;
    private boolean fin = false;
    private int velocidad;

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public naveB(ImageView nave, int sizeX) {
        naveBon = nave;
        inicialX = sizeX;
        naveBon.setX(naveBon.getX()+300);
        velocidad = 15;
    }

    @Override
    public void run() {
        while (!fin) {
            try{
                while(naveBon.getX()>-60) {
                    naveBon.setX(naveBon.getX() - 1);
                    Thread.sleep(velocidad);
                }
                naveBon.setX(inicialX);
                //30 SEGUNDOS ENTRE CADA NAVE BONUS DESDE QUE DESAPARECE
                Thread.sleep(30000);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    public void aumentarVelocidad(){
        if(velocidad==1){}
        else velocidad  -=1;
    }
}
