package animations;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.view.View;

public class TremblementAnimation extends CountDownTimer {
    private final static long MS_AVANT_FIN = 1000; // Le temps de l'animation
    private final static long MS_INTERVALLE = 50; // L'intervalle entre chaque animation
    private final static int TEMPS_DEPLACEMENT = 40; // Le temps de glissement dans l'animation
    private final static float DISTANCE_DEPLACEMENT = 10f; // La distance de glissement de l'objet anime
    private View objetTremble; // L'objet qui sera anime
    private boolean deplacementVersGauche; // Si le deplacement est vers la gauche²

    public TremblementAnimation(View v) {
        this(MS_AVANT_FIN, MS_INTERVALLE);
        objetTremble = v;
        deplacementVersGauche = true;
    }

    private TremblementAnimation(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * @brief A chaque intervalle, fait deplacer l'objet vers la gauche
     * puis vers la droite
     */
    @Override
    public void onTick(long l) {
        animationTremblement(deplacementVersGauche);
        deplacementVersGauche = !deplacementVersGauche;
    }

    @Override
    public void onFinish() {

    }

    /**
     * @brief Deplace l'objet anime vers la gauche ou vers la droite d'une certaine distance et temps
     * @brief versGauche : l'objet va se deplacer vers la gauche
     */
    private void animationTremblement(boolean versGauche) {
        ObjectAnimator deplacementDroite = ObjectAnimator.ofFloat(objetTremble, "translationX", versGauche ? DISTANCE_DEPLACEMENT : -DISTANCE_DEPLACEMENT);
        deplacementDroite.setDuration(TEMPS_DEPLACEMENT);
        deplacementDroite.start();
    }
}
