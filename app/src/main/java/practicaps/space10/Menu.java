package practicaps.space10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;


public class Menu extends AppCompatActivity {

    public Button boton_empezar;
    public ImageButton boton_silencio;
    public int pulsado=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        //declaraciones para salto a otra pantalla
        boton_empezar = (Button) findViewById(R.id.bot_emp);
        boton_silencio = (ImageButton) findViewById(R.id.boton_sonido);
        final VideoView videoView = (VideoView) findViewById(R.id.videoGif);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gif2);

        videoView.setVideoURI(path);


        videoView.start();

        boton_silencio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //activo el silencio e imagen del boton en base a una pulsacion par o impar
                pulsado = pulsado+1;
                if ((pulsado % 2) != 0) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mute);
                    boton_silencio.setImageBitmap(bmp);
                }
                else{
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sonidoon);
                    boton_silencio.setImageBitmap(bmp);
                }
            }
        });

        boton_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, new MainActivity().getClass());

                //calculo si la pulsacion es par o impar para cambiar entre activado o desactivado el sonido

                if ((pulsado % 2) != 0) {
                    intent.putExtra("silenciar_sonido", "1");
                }else{
                    intent.putExtra("silenciar_sonido", "0");
                }
                startActivity(intent);
                finish();
            }

        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }
}

