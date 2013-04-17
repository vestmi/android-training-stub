package com.houston_inc.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

import java.util.Locale;

@EActivity(R.layout.highscore)
public class HighscoreActivity extends Activity {

    @ViewById(R.id.highScore)
    TextView highScore;

    @ViewById(R.id.userScore)
    TextView userScore;

    @Pref
    Preferences_ preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        populateScores();
    }

    private void populateScores() {

        String highestScore = String.valueOf(preferences.highScore().get());

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            int value = extras.getInt("user_score");
            userScore.setText(""+value);

            Log.i("HighscoreActivity", "High scrore"+Integer.valueOf(highestScore));
            Log.i("HighscoreActivity", "User score"+value);

            if (value>Integer.valueOf(highestScore)) {
                Log.i("HighscoreActivity", "NEW HIGHSCORE: "+value);
                preferences.highScore().put(value);
            }

        }

        highScore.setText(highestScore);

    }

}

