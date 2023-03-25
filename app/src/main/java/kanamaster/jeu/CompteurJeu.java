package kanamaster.jeu;

import android.os.CountDownTimer;
import android.widget.TextView;

import animations.ClignotementAnimation;

public class CompteurJeu extends CountDownTimer {
    private final static int TEMPS_CHRONO = 60; // 60 secondes = 1 minute
    private final static int MILLISECONDE = 1000; // Le temps pour 1 seconde en milliseconde
    private final static int MINUTE = 60; // Le nombre de secondes en minutes
    private int compteur; // Le compteur pour le chronometre
    private TextView texteChronometre; // TextView qui sera modifiee pour afficher le chronometre a l'ecran
    private ClignotementAnimation clignotement; // Animation du clignotement
    private final static int TEMPS_CLIGNOTEMENT = 20; // Le temps auquel le chrono se met a clignoter

    public CompteurJeu(TextView textView, int couleurDeb, int couleurFin) {
        super(TEMPS_CHRONO * MILLISECONDE, MILLISECONDE);
        this.texteChronometre = textView;
        this.compteur = TEMPS_CHRONO;
        clignotement = new ClignotementAnimation(couleurDeb, couleurFin);
    }

    public CompteurJeu(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    /**
     * @brief A chaque tick (= seconde), met a jour le chronometre a l'ecran, fait clignoter le textView au bout
     * du temps de clignotement
     */
    @Override
    public void onTick(long l) {
        compteur--;
        int tmp = String.valueOf(compteur % MINUTE).length();
        texteChronometre.setText((compteur / MINUTE) + ":" + (tmp == 1 ? "0" : "") + (compteur % MINUTE));
        if(compteur % MINUTE == TEMPS_CLIGNOTEMENT && compteur / MINUTE == 0) // Se lance quand il reste le temps clignotement en secondes
            clignotement.lancer(texteChronometre);
    }

    @Override
    public void onFinish() {

    }
}
