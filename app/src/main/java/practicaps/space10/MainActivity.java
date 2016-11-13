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
    private ImageView disparo;
    private ImageView navebonus;
    private TextView tou;

    private Puntuacion punt;

    private Colisiones cs = new Colisiones();
    private int enemigosVivos;
    public Point size;
    private Musica musica;

    //Enemigos
    private ImageView enemigo1;
    private ImageView enemigo2;
    private ImageView enemigo3;

    //Asteroides
    private ImageView ast1;
    private ImageView ast2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        nave = (ImageView) findViewById(R.id.nave_id);
        tou = (TextView) findViewById(R.id.touch);
        disparo = (ImageView) findViewById(R.id.disp);

        navebonus = (ImageView) findViewById(R.id.naveBonus);

        tou.setOnTouchListener(this);
        enemigo1 = (ImageView) findViewById(R.id.ovni00);
        enemigo2 = (ImageView) findViewById(R.id.ovni01);
        enemigo3 = (ImageView) findViewById(R.id.ovni02);
        enemigosVivos = 3;
        ast1 = (ImageView) findViewById(R.id.asteroide1);
        ast2 = (ImageView) findViewById(R.id.asteroide2);
        musica = new Musica(this);



        // Coge el objeto display para entrar a los datos de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        // Carga la resolución con un objeto Point
        size = new Point();
        display.getSize(size);
        //Ahora podemos usar size.x | size.y para obtener el ancho y alto de la pantalla del dispositivo

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

        new Thread(new naveB(navebonus,size.x)).start();
        new Thread(new Disparo(nave ,disparo, this, size.y)).start();
        new Thread(new Enemigo(enemigo1,enemigo2,enemigo3, size.x)).start();
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
                    disparo.setY(-(size.y*2));
                    musica.reproducir3();
                }
                if(chocar(enemigo1,disparo)){
                    enemigo1.setX(enemigo1.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    musica.reproducir3();
                }
                if(chocar(enemigo2,disparo)){
                    enemigo2.setX(enemigo2.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    musica.reproducir3();
                }
                if(chocar(enemigo3,disparo)){
                    enemigo3.setX(enemigo3.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    musica.reproducir3();
                }
                if(enemigosVivos==0){
                    enemigosVivos = 3;
                    enemigo1.setX(150);
                    enemigo2.setX(350);
                    enemigo3.setX(550);
                    enemigo1.setY(200);
                    enemigo2.setY(200);
                    enemigo3.setY(200);
                }
                if(chocar(ast1,disparo)||(chocar(ast2,disparo))){
                    disparo.setY(-(size.y*2));
                }
            }
        }

        public boolean chocar(ImageView obj, ImageView disp){
            if((obj.getX()-40<=disp.getX())&&(obj.getX()+40>=disp.getX())&&(obj.getY()-40<=disp.getY())&&(obj.getY()+40>=disp.getY())&&(disp.getX()!=0)){
                return true;
            }
            return false;
        }
    }
}