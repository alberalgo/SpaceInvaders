package practicaps.space10;

import android.widget.ImageView;

/**
 * Created by danic on 03/12/2016.
 */

public class Nave2 implements Runnable {

    private ImageView nave2;
    private float inicialX;

    public Nave2 (ImageView nave, int sizeX) {
        nave2 = nave;
        inicialX = sizeX;
        nave2.setX(nave.getX()+100);
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (nave2.getX() > -60) {
                    nave2.setX(nave2.getX() - 1);
                    Thread.sleep(5);
                }
                nave2.setX(inicialX);
                //5 SEGUNDOS ENTRE CADA NAVE  DESDE QUE DESAPARECE
                Thread.sleep(5);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }
}
