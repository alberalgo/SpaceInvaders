package practicaps.space10;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


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

    //Clase con Hilo
    private naveB nv;
    private Enemigo enem;
    private DisparoEnemigo disEn1;
    private DisparoEnemigo disEn2;
    private DisparoEnemigo disEn3;
    private Disparo dis;

    //Musica
    public Musica m1;
    public Musica m2;
    private Button botonGameover;
    private TextView textoGameOver;
    private boolean isGameOver = false;
    private Intent goreinicio;

    //sonido
    public String silencio;

    //Dificultad
    long tiempo;
    final long tiempoAumento = 30000;    //tiempo en milisegundos tras el que aumenta la dificultad

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        layoutprincipal = (RelativeLayout) findViewById(R.id.activity_main);
        layoutprincipal.setBackgroundResource(R.drawable.deathstarwall);
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
        botonGameover = (Button) findViewById(R.id.buttonreinicio);
        textoGameOver = (TextView) findViewById(R.id.textogameover);
        textoGameOver.setVisibility(View.INVISIBLE);
        botonGameover.setVisibility(View.INVISIBLE);
        tiempo = System.currentTimeMillis();
        //musicafondo = new Musica(this);

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

        goreinicio = new Intent();

        //obtengo el silenciar sonido si o no
        String sound = getIntent().getStringExtra("silenciar_sonido");
        silencio = sound;

        if(silencio.equals("1")){
            //se silencia la app y no hay que inicar sonidos
        }else{
            m1= new Musica(this);
            m1.reproducir();
            m2=new Musica(this);
            m2.reproducir2();
        }


        //ejecuto el hilo
        nv = new naveB(navebonus, size.x);
        enem = new Enemigo(enemigo1, enemigo2, enemigo3, size.x);
        disEn1 = new DisparoEnemigo(enemigo3, dispEnemigo3, nave, size.y);
        disEn2 = new DisparoEnemigo(enemigo2, dispEnemigo2, nave, size.y);
        disEn3 = new DisparoEnemigo(enemigo1, dispEnemigo1, nave, size.y);
        dis = new Disparo(nave, disparo, this, size.y, silencio);
        nv.start();
        enem.start();
        disEn1.start();
        disEn2.start();
        disEn3.start();
        cs.start();
    }

    @Override
    public void onBackPressed() {
        if(!silencio.equals("1")){
            m1.parar();
            m2.parar();
        }
        //musicafondo.parar();
        nv.setFin(true);
        enem.setFin(true);
        disEn1.setFin(true);
        disEn2.setFin(true);
        disEn3.setFin(true);
        cs.setFin(true);
        goreinicio.setClass(MainActivity.this, new Menu().getClass());
        startActivity(goreinicio);
        finish();
    }

    public String getSilencio() {
        return silencio;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        float coorX = event.getX();

        switch (action) {

            case (MotionEvent.ACTION_DOWN):

                if (isGameOver) {
                    goreinicio.setClass(MainActivity.this, new Menu().getClass());
                    startActivity(goreinicio);
                    finish();
                }
                return true;

            case (MotionEvent.ACTION_POINTER_DOWN):
                try{
                    if((vidas>0)&&(!dis.isAlive())){
                        dis = new Disparo(nave, disparo, this, size.y, silencio);
                        dis.start();
                    }
                }
                catch(Exception e){}
                return true;

            case (MotionEvent.ACTION_MOVE):
                //Desplaza la nave siguiendo la pulsacion
                if ((nave.getX() - 75 <= coorX) && (nave.getX() + 75 >= coorX)) {
                    nave.setX(coorX);
                    return true;
                }
        }
        return false;
    }

    class Colisiones extends Thread {
        private boolean fin = false;


        public boolean isFin() {
            return fin;
        }

        public void setFin(boolean fin) {
            this.fin = fin;
        }

        @Override
        public void run() {
            while (!fin) {
                if (vidas == 0) {

                    isGameOver = true;
                    try {
                        layoutprincipal.setBackgroundResource(R.drawable.backgameover);
                        //musicafondo.parar();
                        if(!silencio.equals("1")){
                            m1.parar();
                            m2.parar();
                        }
                        disparo.setVisibility(View.INVISIBLE);
                        navebonus.setVisibility(View.INVISIBLE);
                        nave.setVisibility(View.INVISIBLE);
                        enemigo1.setVisibility(View.INVISIBLE);
                        enemigo2.setVisibility(View.INVISIBLE);
                        enemigo3.setVisibility(View.INVISIBLE);
                        ast1.setVisibility(View.INVISIBLE);
                        ast2.setVisibility(View.INVISIBLE);
                        dispEnemigo3.setVisibility(View.INVISIBLE);
                        dispEnemigo2.setVisibility(View.INVISIBLE);
                        dispEnemigo1.setVisibility(View.INVISIBLE);
                        //punt.setVisibility(View.INVISIBLE);


                        textoGameOver.setVisibility(View.VISIBLE);
                        //botonGameover.setVisibility(View.VISIBLE);
                        //textoGameOver.setText( "PUNTUACIÓN PLAYER_1 -->" + String.valueOf(puntuacion));
                        nv.setFin(true);
                        enem.setFin(true);
                        disEn1.setFin(true);
                        disEn2.setFin(true);
                        disEn3.setFin(true);
                        cs.setFin(true);


                    } catch (Exception e) {
                    }


                }
                if (chocar(navebonus, disparo)) {
                    navebonus.setX(navebonus.getX() - size.x * 2);
                    disparo.setY(-(size.y * 2));
                    try {
                        puntuacion += 5;
                        punt.setText(String.valueOf(puntuacion));
                    } catch (Exception e) {
                    }
                    Log.d("NAVE BONUS DESTRUIDA", "");
                    if(!silencio.equals("1")){
                        new Musica(context).reproducir3();
                    }
                }
                if (chocar(enemigo1, disparo)) {
                    enemigo1.setX(enemigo1.getX() - 2 * size.x);
                    disparo.setY(-(size.y * 2));
                    enemigosVivos -= 1;
                    try {
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    } catch (Exception e) {
                    }
                    Log.d("NAVE 1", "");
                    if(!silencio.equals("1")){
                        new Musica(context).reproducir3();
                    }

                }
                if (chocar(enemigo2, disparo)) {
                    enemigo2.setX(enemigo2.getX() - 2 * size.x);
                    disparo.setY(-(size.y * 2));
                    enemigosVivos -= 1;
                    try {
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    } catch (Exception e) {
                    }
                    Log.d("NAVE 2", "");
                    if(!silencio.equals("1")){
                        new Musica(context).reproducir3();
                    }
                }
                if (chocar(enemigo3, disparo)) {
                    enemigo3.setX(enemigo3.getX() - 2 * size.x);
                    disparo.setY(-(size.y * 2));
                    enemigosVivos -= 1;
                    try {
                        puntuacion += 1;
                        punt.setText(String.valueOf(puntuacion));
                    } catch (Exception e) {
                    }
                    if(!silencio.equals("1")){
                        new Musica(context).reproducir3();
                    }
                    Log.d("NAVE 3", "");
                }
                if (chocar(nave, dispEnemigo1)) {
                    quitarVida();
                    dispEnemigo1.setY(size.y * 2);
                }
                if (chocar(nave, dispEnemigo2)) {
                    quitarVida();
                    dispEnemigo2.setY(size.y * 2);
                }
                if (chocar(nave, dispEnemigo3)) {
                    quitarVida();
                    dispEnemigo3.setY(size.y * 2);
                }
                if ((chocar(ast1, dispEnemigo1)) || (chocar(ast2, dispEnemigo1))) {
                    dispEnemigo1.setY(size.y * 2);
                }
                if ((chocar(ast1, dispEnemigo2)) || (chocar(ast2, dispEnemigo2))) {
                    dispEnemigo2.setY(size.y * 2);
                }
                if ((chocar(ast1, dispEnemigo3)) || (chocar(ast2, dispEnemigo3))) {
                    dispEnemigo3.setY(size.y * 2);
                }

                if ((enemigo1.getY() >= ast1.getY()) && (ast1.getY() != 0)) {
                    quitarVida();
                    reestablecerNaves();
                }
                if (enemigosVivos == 0) {
                    reestablecerNaves();
                }
                if (chocar(ast1, disparo) || (chocar(ast2, disparo))) {
                    disparo.setX(size.x*2);
                    Log.d("ASTEROIDE ALCANZADO", "");
                }
                if(tiempo+tiempoAumento<System.currentTimeMillis()){
                    tiempo = System.currentTimeMillis();
                    aumentarDificultad();
                    Log.d("DIFICULTAD AUMENTADA", "");
                }
            }
        }

        public boolean chocar(ImageView obj, ImageView disp) {
            if ((obj.getX() - 40 <= disp.getX()) && (obj.getX() + 40 >= disp.getX()) && (obj.getY() - 40 <= disp.getY()) && (obj.getY() + 40 >= disp.getY()) && (disp.getX() != 0)) {
                return true;
            }
            return false;
        }

        public void reestablecerNaves() {
            enemigosVivos = 3;
            enemigo1.setX(150);
            enemigo2.setX(350);
            enemigo3.setX(550);
            enemigo1.setY(200);
            enemigo2.setY(200);
            enemigo3.setY(200);
        }

        public void quitarVida() {
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
            } catch (Exception e) {
            }
        }

        public void aumentarDificultad(){
            nv.aumentarVelocidad();
            enem.aumentarVelocidad();
            disEn1.aumentarVelocidad();
            disEn1.aumentarProbDisparo();
            disEn2.aumentarVelocidad();
            disEn2.aumentarProbDisparo();
            disEn3.aumentarVelocidad();
            disEn3.aumentarProbDisparo();
        }
    }
}