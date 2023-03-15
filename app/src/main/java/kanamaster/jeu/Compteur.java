package kanamaster.jeu;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.kanamaster.JeuActivity;

public class Compteur extends CountDownTimer {
    private final static int TEMPS_CHRONO = 10; // 180 secondes = 3 minutes
    private final static int MILLISECONDE = 1000;
    private final static int MINUTE = 60;
    private int compteur; // Le compteur pour le chronometre
    private TextView texteChronometre;

    public Compteur(TextView textView) {
        super(TEMPS_CHRONO * MILLISECONDE, MILLISECONDE);
        texteChronometre = textView;
        compteur = TEMPS_CHRONO;
    }

    public Compteur(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

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
