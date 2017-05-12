package ercanduman.numberguessgameandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button checkButton, replayButton;
    private TextView remainTrailsTextView, resultTextView;
    private EditText userGuessEditText;

    private static int TRIALS = 10;
    private static int GUESSED_NUMBER;
    private int usersNumberGuess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
            }
        });*/


        initializeViews();
    }

    private void initializeViews() {
        //whenever app created, a new number will be picked randomly
        GUESSED_NUMBER = new Random().nextInt(100);

        //Buttons
        checkButton = (Button) findViewById(R.id.checkButton);
        checkButton.setOnClickListener(this);

        replayButton = (Button) findViewById(R.id.buttonReplay);
        replayButton.setOnClickListener(this);


        //TextViews
        resultTextView = (TextView) findViewById(R.id.tvResult);
        remainTrailsTextView = (TextView) findViewById(R.id.tvRemainTrials);


        //User input EditTexts
        userGuessEditText = (EditText) findViewById(R.id.userGuessText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkButton:
                checkNumberAndStartGame();
                break;
            case R.id.buttonReplay:
                startNewGame();
                break;
        }
    }

    private void checkNumberAndStartGame() {
        Toast.makeText(this, "Check Button clicked!", Toast.LENGTH_SHORT).show();
        String userCurrentInput = userGuessEditText.getText().toString().trim();

        if (userCurrentInput.length() == 0) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        usersNumberGuess = Integer.parseInt(userCurrentInput);

        if (usersNumberGuess > GUESSED_NUMBER) {
            resultTextView.setText("It is smaller than your guess");
            TRIALS--;
            remainTrailsTextView.setText("You have " + TRIALS + " trials left");
        } else if (usersNumberGuess < GUESSED_NUMBER) {
            resultTextView.setText("It is bigger than your guess");
            TRIALS--;
            remainTrailsTextView.setText("You have " + TRIALS + " trials left");
        } else {
            resultTextView.setText("You found it in " + (10 - TRIALS) + " trials!");
            TRIALS = 10;
            remainTrailsTextView.setText("Congratulations!");

        }

        if (TRIALS == 0) {
            resultTextView.setText("Trials are finished!");
            remainTrailsTextView.setText("GAME OVER!");
            TRIALS = 10;
        }
    }

    private void startNewGame() {
        Toast.makeText(this, "Replay Button clicked!", Toast.LENGTH_SHORT).show();
    }


    // HANDLE MENU ITEMS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
