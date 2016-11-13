package practicaps.space10;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Sergio on 12/11/2016.
 */

public class naveB implements Runnable {
    private ImageView naveBon;
    private float inicialX;

    public naveB(ImageView nave, int sizeX) {
        naveBon = nave;
        inicialX = sizeX;
        naveBon.setX(naveBon.getX()+300);
    }

    @Override
    public void run() {
        while (true) {
            try{
                while(naveBon.getX()>-60) {
                    naveBon.setX(naveBon.getX() - 1);
                    Thread.sleep(15);
                }
                naveBon.setX(inicialX);
                //30 SEGUNDOS ENTRE CADA NAVE BONUS DESDE QUE DESAPARECE
                Thread.sleep(30000);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }
}
