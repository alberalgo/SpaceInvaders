package practicaps.space10;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.VideoView;
import android.net.Uri;


public class Menu extends AppCompatActivity {

    public Button boton_empezar;
    public ImageButton boton_silencio;
    public MainActivity juego;
    public int pulsado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        //declaraciones para salto a otra pantalla
        boton_empezar = (Button) findViewById(R.id.bot_emp);
        boton_silencio = (ImageButton) findViewById(R.id.sound);
        VideoView videoView = (VideoView)findViewById(R.id.videoGif);


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
              pulsado = 1;
            }

        });

        boton_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent empezar = new Intent(Menu.this, MainActivity.class);
                if(pulsado==1){
                    empezar.putExtra("silenciar_sonido", "1");
                }else{
                    empezar.putExtra("silenciar_sonido", "0");
                }
                startActivity(empezar);
            }

        });


    }
}

