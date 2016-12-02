package practicaps.space10;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by alberto on 23/10/16.
 */

public class Musica {
    MediaPlayer m;
    Context context;


    public Musica(Context context){

        this.context=context;
    }



    public void reproducir() {
        m = MediaPlayer.create(context, R.raw.disparo2);
        m.setLooping(false);
        //m.setVolume(100,100);
        m.start();
    }

    public void reproducir2() {
        m = MediaPlayer.create(context, R.raw.space);
        m.setLooping(true);
        //m.setVolume(100,100);
        m.start();
    }

    public void reproducir3(){
        m = MediaPlayer.create(context, R.raw.impacto);
        m.setLooping(false);
        //m.setVolume(100,100);
        m.start();
    }

    public void parar(){
        m.stop();
    }

}
