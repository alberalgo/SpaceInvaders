package practicaps.space10;

import android.graphics.Point;
import android.media.Image;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.content.Context;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    //declaro variables para los componentes

    private ImageView nave;
    private ImageView enem;
    private ImageView disparo;
    private ImageView navebonus;
    private TextView tou;
    private int puntuacion;
    private Colisiones cs = new Colisiones();
    public Point size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        puntuacion = 0;
        nave = (ImageView) findViewById(R.id.nave_id);
        tou = (TextView) findViewById(R.id.touch);
        disparo = (ImageView) findViewById(R.id.disp);
        enem = (ImageView) findViewById(R.id.enemy);
        navebonus = (ImageView) findViewById(R.id.naveBonus);
        tou.setOnTouchListener(this);



        // Coge el objeto display para entrar a los datos de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        // Carga la resolución con un objeto Point
        size = new Point();
        display.getSize(size);
        //Ahora podemos usar size.x | size.y para obtener el ancho y alto de la pantalla

        Log.d("Ancho del disp", String.valueOf(size.x));
        Log.d("Alto del disp", String.valueOf(size.y));


        //coloco la nave enemiga en el centro del eje X
        //enem.setX(size.x/2);
        /*
        //coloco la nave enemiga en la parte superior del eje Y
        enem.setY(size.y/2);
        */

        new Musica(this).reproducir();
        new Musica(this).reproducir2();

        //ejecuto el hilo
        Enemigo e = new Enemigo(enem);
        new Thread(new naveB(navebonus,size.x)).start();
        new Thread(new Disparo(nave ,disparo, this, e, size.y)).start();
        cs.start();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        float coorX = event.getX();
        int coorY = (int) event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):


            case (MotionEvent.ACTION_MOVE):
                //Desplaza la nave siguiendo la pulsacion

                nave.setX(coorX);
                nave.setY(coorY - 100);
                if (coorY < 800) {
                    Log.d("Posicion: ", "Tocaste el limite");
                    nave.setY(700);
                }
                return true;
        }
        return false;
    }

    class Colisiones extends Thread{
        @Override
        public void run(){
            while(true){
                if(chocar(navebonus,disparo)){
                    navebonus.setX(navebonus.getX()-size.x*2);
                }
            }
        }

        public boolean chocar(ImageView obj, ImageView disp){
            if((obj.getX()-40<=disp.getX())&&(obj.getX()+40>=disp.getX())&&(obj.getY()-40<=disp.getY())&&(obj.getY()+40>=disp.getY())){
                return true;
            }
            return false;
        }
    }
}