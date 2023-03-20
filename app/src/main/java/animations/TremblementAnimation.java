package animations;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.view.View;

public class TremblementAnimation extends CountDownTimer {
    private final static long MS_AVANT_FIN = 1000;
    private final static long MS_INTERVALLE = 50;
    private final static int TEMPS_DEPLACEMENT = 40;
    private final static float DISTANCE_DEPLACEMENT = 10f;
    private View objetTremble;
    private boolean deplacementVersGauche;

    public TremblementAnimation(View v) {
        this(MS_AVANT_FIN, MS_INTERVALLE);
        objetTremble = v;
        deplacementVersGauche = true;
    }

    public TremblementAnimation(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {
        animationTremblement(deplacementVersGauche);
        deplacementVersGauche = !deplacementVersGauche;
    }

    @Override
    public void onFinish() {

    }

    private void animationTremblement(boolean versGauche) {
        ObjectAnimator deplacementDroite = ObjectAnimator.ofFloat(objetTremble, "translationX", versGauche ? DISTANCE_DEPLACEMENT : -DISTANCE_DEPLACEMENT);
        deplacementDroite.setDuration(TEMPS_DEPLACEMENT);
        deplacementDroite.start();
    }
}
