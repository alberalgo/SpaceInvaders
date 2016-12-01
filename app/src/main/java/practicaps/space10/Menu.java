package practicaps.space10;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.VideoView;
import android.net.Uri;


public class Menu extends AppCompatActivity {

    public Button boton_empezar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        //declaraciones para salto a otra pantalla
        boton_empezar = (Button) findViewById(R.id.bot_emp);


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



        Uri path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gif2);

        videoView.setVideoURI(path);

        videoView.start();



        boton_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent boton_empezar = new Intent(Menu.this, MainActivity.class);
                startActivity(boton_empezar);
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

