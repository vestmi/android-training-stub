package com.houston_inc.training;

import android.widget.Button;
import android.widget.TextView;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Robotium test for GameActivity
 *
 * This should be run as a regular jUnit test, NOT Android test.
 */
@RunWith(RobolectricTestRunner.class)
public class GameActivityTest {


    private GameActivity_ activity;

    private Button startButton;

    private TextView statusField;

    @Before
    public void setUp() throws Exception {
        activity = new GameActivity_();
        activity.onCreate(null);
        startButton = (Button) activity.findViewById(R.id.startButton);
        statusField = (TextView) activity.findViewById(R.id.status);
    }

    @Test
    public void shouldReturnCorrectName() throws Exception {
        String appName = activity.getResources().getString(R.string.app_name);
        assertThat(appName, equalTo("memorygame"));
    }

    @Test
    public void gameShouldStartWhenStartButtonClicked() throws InterruptedException {

        assertNull(activity.getGame());
        assertEquals("", statusField.getText());

        startButton.performClick();

        assertEquals("Game in progress...", statusField.getText());
        assertFalse(activity.getGame().isGameFinished());

    }

}
