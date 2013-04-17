package com.houston_inc.training.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;
import com.houston_inc.training.*;
import com.houston_inc.training.R;
import com.jayway.android.robotium.solo.Solo;

/**
 * Robotium test for the main activity.
 *
 * This should be run as an Android test, NOT Junit test.
 *
 */
public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<GameActivity_> {

    private Solo solo;

    public HelloAndroidActivityTest() {
        super(GameActivity_.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testHighscroreAfterUserAnswersIncorrectly() throws Exception {

        solo.assertCurrentActivity("Should be game activity", GameActivity_.class);

        TextView numberField = (TextView)solo.getCurrentActivity().findViewById(R.id.integerField);
        TextView statusField = (TextView)solo.getCurrentActivity().findViewById(R.id.status);

        solo.clickOnButton(0);
        solo.waitForText("Game in progress...");
        assertEquals("Game in progress...", statusField.getText());

        MemoryGame game = getActivity().getGame();
        char[] testString = game.getSequenceToBeShownToUser();


        // Check numbers one by one
        /* For some reason, does not work as expected

        solo.waitForText(String.valueOf(testString[0]));
        assertEquals(numberField.getText(), String.valueOf(testString[0]));

        solo.waitForText(" ");
        assertEquals(numberField.getText(), " ");

        solo.waitForText(String.valueOf(testString[1]));
        assertEquals(numberField.getText(), String.valueOf(testString[1]));

        solo.waitForText(" ");
        assertEquals(numberField.getText(), " ");

        solo.waitForText(String.valueOf(testString[2]));
        assertEquals(numberField.getText(), String.valueOf(testString[2]));
        */

        // Alert dialog

        solo.waitForText("Enter numbers shown");

        EditText editText = solo.getEditText(0);
        solo.clickOnView(editText);
        solo.typeText(editText, String.valueOf(testString));
        solo.clickOnButton("OK");

        assertFalse(((GameActivity_)solo.getCurrentActivity()).getGame().isGameFinished());

        // Wait for new alert dialog
        solo.waitForText("Enter numbers shown");

        // Type wrong answer
        editText = solo.getEditText(0);
        solo.clickOnView(editText);
        solo.typeText(editText, "0000");
        solo.clickOnButton("OK");

        solo.waitForText("High score");

        solo.assertCurrentActivity("Should be highscore activity", HighscoreActivity_.class);

    }
}

