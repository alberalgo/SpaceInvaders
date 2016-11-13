package practicaps.space10;

import android.util.Log;
import android.widget.ImageView;

/**
 * Created by alberto on 24/10/16.
 */

public class Enemigo {
    private ImageView enem;
    private ImageView nave;
    private float coorX;
    private float coorY;
    private float coorYnave;

    public Enemigo(ImageView enem) {
        this.enem=enem;
        coorX=enem.getX();
        coorY=enem.getY();
    }

    public float getCoorX(){
        return coorX;
    }
}