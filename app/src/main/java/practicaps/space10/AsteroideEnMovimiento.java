package practicaps.space10;

import android.widget.ImageView;

/**
 * Created by danic on 03/12/2016.
 */

public class AsteroideEnMovimiento implements Runnable {
    int direccion;
    float pantalla;
    private ImageView asteroide;
    public AsteroideEnMovimiento(ImageView asteroide, float ancho ){
        this.direccion=1;
        this.asteroide= asteroide;
        this.pantalla=ancho -300;


    }


    @Override
    public void run() {
        while (true) {
            try{
                if(asteroide.getX()==0){
                    direccion = 0;
                    asteroide.setY(asteroide.getY()+0);

                }
                if(asteroide.getX()>=pantalla){
                    direccion = 1;
                    asteroide.setY(asteroide.getY()+0);

                }
                if(direccion == 1) {
                    asteroide.setX(asteroide.getX() - 1);

                }
                if(direccion == 0) {
                    asteroide.setX(asteroide.getX() + 1);

                }
                Thread.sleep(10);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }
}
