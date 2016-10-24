package practicaps.space10;

import android.util.Log;
import android.widget.ImageView;

/**
 * Created by alberto on 24/10/16.
 */

public class Enemigo {
    public ImageView enem;
    public ImageView nave;
    public float coorX;
    public float coorY;
    public float coorYnave;

    public Enemigo(ImageView enem) {
        this.enem=enem;
        coorX=enem.getX();
        coorY=enem.getY();
    }

    public float getCoorX(){
        return coorX;
    }
}