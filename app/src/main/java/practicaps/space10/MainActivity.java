package practicaps.space10;

import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    //declaro variables para los componentes

    private ImageView nave;
    private ImageView disparo;
    public float coordenadaX = 0;
    private TextView tou;
    //private float coordenadaDisparo = 1;
    public Point size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //relaciono la variable nave con el objeto nave por su id, y así con el resto
        nave = (ImageView) findViewById(R.id.nave_id);
        tou = (TextView) findViewById(R.id.touch);
        disparo = (ImageView) findViewById(R.id.disp);
        tou.setOnTouchListener(this);


        // Coge el objeto display para entrar a los datos de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        // Carga la resolución con un objeto Point
        size = new Point();
        display.getSize(size);
        //Ahora podemos usar size.x | size.y para obtener el ancho y alto de la pantalla

        Log.d("Posicion", String.valueOf(size.x));
        Log.d("Posicion", String.valueOf(size.y));

        //ejecuto el hilo
        new Thread(new Disparo(nave ,disparo)).start();

    }

    /*
    public void derecha(View view) {
        coordenadaX = nave.getX();
        Log.d("Posicion: ", String.valueOf(coordenadaX));
        if (coordenadaX < size.x) {
            nave.setX(nave.getX() + 100);
        }
    }

    public void izquierda(View view) {
        coordenadaX = nave.getX();
        Log.d("Posicion", String.valueOf(coordenadaX));
        if (coordenadaX > 0) {
            nave.setX(nave.getX() - 100);
        }
    }

    */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        float coorX = event.getX();
        int coorY = (int) event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):


                /* Comentado, codigo para mover la nave mediante pulsacion en pantalla
                disparo.setVisibility(View.VISIBLE);
                disparo.setX(coorX);
                coordenadaDisparo = 964;
                for(int i=0; i<100; i++){
                    coordenadaDisparo = coordenadaDisparo - 5;

                    disparo.setY(coordenadaDisparo);
                    //SystemClock.sleep(10);
                    Log.d("PosicionDisparoX: ", String.valueOf(coorX));
                    Log.d("PosicionDisparoY: ", String.valueOf(coordenadaDisparo));
                }

                if ( coorX > 300) {

                    nave.setX(nave.getX() + 100);
                    return true;
                }
                if ( coorX < 300) {

                    nave.setX(nave.getX() - 100);
                    return true;
                }
                */

            case (MotionEvent.ACTION_MOVE):
                //Desplaza la nave siguiendo la pulsacion

                Log.d("Posicion: ", String.valueOf(coorX));
                Log.d("Posicion: ", String.valueOf(coorY));
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
}