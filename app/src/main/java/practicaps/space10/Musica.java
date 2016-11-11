package practicaps.space10;

import android.net.Uri;
import android.util.Log;
import android.media.MediaPlayer;
import android.net.Uri;
import android.content.Context;

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

}
