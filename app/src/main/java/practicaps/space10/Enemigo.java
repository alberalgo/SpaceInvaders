package practicaps.space10;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Sergio on 13/11/2016.
 */

public class Enemigo implements Runnable{
    int direccion; //1 izquierda, 0 derecha
    float anchoPantalla;
    private ImageView enemigo1;
    private ImageView enemigo2;
    private ImageView enemigo3;

    public Enemigo(ImageView en1, ImageView en2, ImageView en3, int ancho){
        direccion = 1;
        enemigo1 = en1;
        enemigo2 = en2;
        enemigo3 = en3;
        anchoPantalla = ancho-120;
    }

    @Override
    public void run() {
        while (true) {
            try{
                if((enemigo1.getX()==25)||(enemigo2.getX()==25)||(enemigo3.getX()==25)){
                    direccion = 0;
                    enemigo1.setY(enemigo1.getY()+20);
                    enemigo2.setY(enemigo2.getY()+20);
                    enemigo3.setY(enemigo3.getY()+20);
                }
                if((enemigo3.getX()>=anchoPantalla)||(enemigo2.getX()>=anchoPantalla)||(enemigo1.getX()>=anchoPantalla)){
                    direccion = 1;
                    enemigo1.setY(enemigo1.getY()+20);
                    enemigo2.setY(enemigo2.getY()+20);
                    enemigo3.setY(enemigo3.getY()+20);
                }
                if(direccion == 1) {
                    enemigo1.setX(enemigo1.getX() - 1);
                    enemigo2.setX(enemigo2.getX() - 1);
                    enemigo3.setX(enemigo3.getX() - 1);
                }
                if(direccion == 0) {
                    enemigo1.setX(enemigo1.getX() + 1);
                    enemigo2.setX(enemigo2.getX() + 1);
                    enemigo3.setX(enemigo3.getX() + 1);
                }
                Thread.sleep(4);
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }
}
