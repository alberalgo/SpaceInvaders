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
    private Colisiones cs = new Colisiones();
    private int enemigosVivos;
    public Point size;
    public TextView punt;
    private int puntuacion;
    private Context context;
    private RelativeLayout layoutprincipal;

    //Enemigos
    private ImageView enemigo1;
    private ImageView enemigo2;
    private ImageView enemigo3;

    //Asteroides
    private ImageView ast1;
    private ImageView ast2;

    //Disparo enemigo
    private ImageView dispEnemigo3;
    private ImageView dispEnemigo2;
    private ImageView dispEnemigo1;

    //vidas
    private ImageView vida1;
    private ImageView vida2;
    private ImageView vida3;
    private int vidas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        layoutprincipal = (RelativeLayout) findViewById(R.id.activity_main);
        context = this;
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
        punt = (TextView) findViewById(R.id.puntuacion);
        punt.setText("0");
        puntuacion = 0;
        dispEnemigo3 = (ImageView) findViewById(R.id.disparoEnemigo3);
        dispEnemigo2 = (ImageView) findViewById(R.id.disparoEnemigo2);
        dispEnemigo1 = (ImageView) findViewById(R.id.disparoEnemigo1);
        vida1 = (ImageView) findViewById(R.id.vida1);
        vida2 = (ImageView) findViewById(R.id.vida2);
        vida3 = (ImageView) findViewById(R.id.vida3);
        vidas = 3;






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
        naveB nv = new naveB(navebonus,size.x);
        Disparo dis = new Disparo(nave ,disparo, this, size.y);
        Enemigo enem = new Enemigo(enemigo1,enemigo2,enemigo3, size.x);
        new Thread(nv).start();
        new Thread(dis).start();
        new Thread(enem).start();
        new Thread(new DisparoEnemigo(enemigo3, dispEnemigo3, nave, size.y)).start();
        new Thread(new DisparoEnemigo(enemigo2, dispEnemigo2, nave, size.y)).start();
        new Thread(new DisparoEnemigo(enemigo1, dispEnemigo1, nave, size.y)).start();
        cs.start();
    }

    @Override
    public void onBackPressed() {
        try{System.exit(1);}
        catch(Exception e){}

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        float coorX = event.getX();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):


            case (MotionEvent.ACTION_MOVE):
                //Desplaza la nave siguiendo la pulsacion
                if((nave.getX()-75<=coorX)&&(nave.getX()+75>=coorX)) {
                    nave.setX(coorX);
                    return true;
                }
        }
        return false;
    }

    class Colisiones extends Thread{
        @Override
        public void run(){
            while(true){
                if(vidas==0){
                    //PERDER
                }
                if(chocar(navebonus,disparo)){
                    navebonus.setX(navebonus.getX()-size.x*2);
                    disparo.setY(-(size.y*2));
                    try{
                        puntuacion += 5;
                        punt.setText(String.valueOf(puntuacion));
                    }
                    catch(Exception e){}
                    Log.d("NAVE BONUS DESTRUIDA", "");
                    new Musica(context).reproducir3();
                }
                if(chocar(enemigo1,disparo)){
                    enemigo1.setX(enemigo1.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    try{
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    }
                    catch(Exception e){}
                    Log.d("NAVE 1", "");
                    new Musica(context).reproducir3();

                }
                if(chocar(enemigo2,disparo)){
                    enemigo2.setX(enemigo2.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    try{
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    }
                    catch(Exception e){}
                    Log.d("NAVE 2", "");
                    new Musica(context).reproducir3();
                }
                if(chocar(enemigo3,disparo)){
                    enemigo3.setX(enemigo3.getX()-2*size.x);
                    disparo.setY(-(size.y*2));
                    enemigosVivos -= 1;
                    try{
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    }
                    catch(Exception e){}
                    new Musica(context).reproducir3();
                    Log.d("NAVE 3", "");
                }
                if(chocar(nave, dispEnemigo1)){
                    quitarVida();
                    dispEnemigo1.setY(size.y*2);
                }
                if(chocar(nave, dispEnemigo2)){
                    quitarVida();
                    dispEnemigo2.setY(size.y*2);
                }
                if(chocar(nave, dispEnemigo3)){
                    quitarVida();
                    dispEnemigo3.setY(size.y*2);
                }
                if((chocar(ast1, dispEnemigo1))||(chocar(ast2,dispEnemigo1))){
                    dispEnemigo1.setY(size.y*2);
                }
                if((chocar(ast1, dispEnemigo2))||(chocar(ast2,dispEnemigo2))){
                    dispEnemigo2.setY(size.y*2);
                }
                if((chocar(ast1, dispEnemigo3))||(chocar(ast2,dispEnemigo3))){
                    dispEnemigo3.setY(size.y*2);
                }

                if((enemigo1.getY()>=ast1.getY())&&(ast1.getY()!=0)){
                    quitarVida();
                    reestablecerNaves();
                }
                if(enemigosVivos==0){
                    reestablecerNaves();
                }
                if(chocar(ast1,disparo)||(chocar(ast2,disparo))){
                    disparo.setY(-(size.y*2));
                    Log.d("ASTEROIDE ALCANZADO", "");
                }
            }
        }

        public boolean chocar(ImageView obj, ImageView disp){
            if((obj.getX()-40<=disp.getX())&&(obj.getX()+40>=disp.getX())&&(obj.getY()-40<=disp.getY())&&(obj.getY()+40>=disp.getY())&&(disp.getX()!=0)){
                return true;
            }
            return false;
        }

        public void reestablecerNaves(){
            enemigosVivos = 3;
            enemigo1.setX(150);
            enemigo2.setX(350);
            enemigo3.setX(550);
            enemigo1.setY(200);
            enemigo2.setY(200);
            enemigo3.setY(200);
        }

        public void quitarVida(){
            vidas -= 1;
            try {
                if (vida3.getVisibility() == View.VISIBLE) {
                    vida3.setVisibility(View.INVISIBLE);
                    return;
                }
                if (vida2.getVisibility() == View.VISIBLE) {
                    vida2.setVisibility(View.INVISIBLE);
                    return;
                }
                if (vida1.getVisibility() == View.VISIBLE) {
                    vida1.setVisibility(View.INVISIBLE);
                    return;
                }
            }
            catch(Exception e){}
        }
    }
}