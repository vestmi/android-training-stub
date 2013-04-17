package com.houston_inc.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.googlecode.androidannotations.annotations.*;

import java.util.Locale;

@OptionsMenu(R.menu.main_menu)
@EActivity(R.layout.main)
public class GameActivity extends Activity implements TextToSpeech.OnInitListener {

    protected MemoryGame game;

    @ViewById(R.id.integerField)
    TextView integerField;

    @ViewById(R.id.status)
    TextView status;

    @ViewById(R.id.startButton)
    Button startButton;

    private TextToSpeech tts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts = new TextToSpeech(this, this);
    }

    @Click(R.id.startButton)
    void onStartButtonClick() {
        startButton.setEnabled(false);
        initGame();
        runGame();
    }

    private void initGame() {
        game = new MemoryGame(3, 2);
        status.setText("Game in progress...");
    }

    @Background
    void runGame() {

        if (game.isGameFinished()) {
            setGameFinished();
            return;
        }

        char[] chars = game.getSequenceToBeShownToUser();

        try {

            for (char c : chars) {
                updateCharToView(c);
                speakText(String.valueOf(c));
                Thread.sleep(1000);
                updateCharToView(' ');
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        requestUserAnswer();

    }

    @UiThread
    void setGameFinished() {
        updateCharToView(' ');
        startButton.setEnabled(true);
        status.setText("Finished with score: " + game.getScore());

        Log.i("GameActivity", "Game ended");

        startHighScoreActivity();

    }

    private void startHighScoreActivity() {

        Intent intent = new Intent(getBaseContext(), HighscoreActivity_.class);
        if (game!=null) {
            intent.putExtra("user_score", game.getScore());
        }
        startActivity(intent);

    }

    @UiThread
    void requestUserAnswer() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Memory game");
        alert.setMessage("Enter numbers shown");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                game.advance(value);
                runGame();
            }
        });
        alert.show();
    }

    @UiThread
    void updateCharToView(char c) {
        integerField.setText(String.valueOf(c));
    }

    private void speakText(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                Log.e("TTS", "succesfully initialized");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    @OptionsItem(R.id.menu_game)
    void onGameMenuSelected() {
        // Not implemented
    }

    @OptionsItem(R.id.menu_highscore)
    void onHighscoreMenuSelected() {
        startHighScoreActivity();
    }

    public MemoryGame getGame() {
        return game;
    }

}

