package animations;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;

public class ClignotementAnimation {
    private final static int tempsClignotement = 1500; // Le temps de la duree de l'animation
    private int couleurDepart; // La couleur de la vue au debut
    private int couleurFin; // La couleur de la vue a la fin

    public ClignotementAnimation(int couleurDepart, int couleurFin) {
        this.couleurDepart = couleurDepart;
        this.couleurFin = couleurFin;
    }

    /**
     * @brief Lance l'animation sur la vue et fait clignoter avec les couleurs definies au constructeur
     * @param v : La vue qui sera animee
     */
    public void lancer(View v) {
        ObjectAnimator animator = ObjectAnimator.ofInt(v, "textColor", couleurDepart, couleurFin, couleurDepart);

        animator.setDuration(tempsClignotement);
        animator.setEvaluator(new ArgbEvaluator());

        // color will be show in reverse manner
        animator.setRepeatCount(Animation.REVERSE);

        // It will be repeated up to infinite time
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }
}
