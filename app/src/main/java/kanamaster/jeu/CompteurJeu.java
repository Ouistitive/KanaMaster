package kanamaster.jeu;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CompteurJeu extends CountDownTimer {
    private final static int TEMPS_CHRONO = 180; // 180 secondes = 3 minutes
    private final static int MILLISECONDE = 1000; // Le temps pour 1 seconde en milliseconde
    private final static int MINUTE = 60; // Le nombre de secondes en minutes
    private int compteur; // Le compteur pour le chronometre
    private TextView texteChronometre; // TextView qui sera modifiee pour afficher le chronometre a l'ecran

    public CompteurJeu(TextView textView) {
        super(TEMPS_CHRONO * MILLISECONDE, MILLISECONDE);
        texteChronometre = textView;
        compteur = TEMPS_CHRONO;
    }

    public CompteurJeu(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * @brief A chaque tick (= seconde), met a jour le chronometre a l'ecran
     */
    @Override
    public void onTick(long l) {
        compteur--;
        int tmp = String.valueOf(compteur % MINUTE).length();
        texteChronometre.setText((compteur / MINUTE) + ":" + (tmp == 1 ? "0" : "") + (compteur % MINUTE));
    }

    @Override
    public void onFinish() {

    }
}
