package practicaps.space10;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marco on 13/11/2016.
 */

public class Puntuacion {

    private static final int PUNTOS_MATAR_ENEMIGO = 1;
    private static final int PUNTOS_NAVE_BONUS = 5;
    private int puntuacionJugador;


    public Puntuacion() {
        this.puntuacionJugador = 0;

    }

    public int getPuntuacionJugador() {
        return puntuacionJugador;
    }

    public void setPuntuacionJugador(int puntuacionJugador) {
        this.puntuacionJugador = puntuacionJugador;
    }

    public void enemigoMuerto() {
        int puntosActualizados = this.getPuntuacionJugador() + PUNTOS_MATAR_ENEMIGO;
        this.setPuntuacionJugador(puntosActualizados);
    }

    public void naveBonusObtenida() {
        int puntosActualizados = this.getPuntuacionJugador() + PUNTOS_NAVE_BONUS;
        this.setPuntuacionJugador(puntosActualizados);
    }

}
