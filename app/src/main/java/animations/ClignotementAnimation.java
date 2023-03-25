package animations;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;

public class ClignotementAnimation {
    private final static int tempsClignotement = 1500;
    private int couleurDepart;
    private int couleurFin;

    public ClignotementAnimation(int couleurDepart, int couleurFin) {
        this.couleurDepart = couleurDepart;
        this.couleurFin = couleurFin;
    }

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
