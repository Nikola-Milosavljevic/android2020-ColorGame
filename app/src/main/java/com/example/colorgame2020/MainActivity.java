package com.example.colorgame2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "username";
    public static final String DIFFICULTY = "difficulty";
    public static final String SCORE = "score";
    public static final int REQUEST_GAME = 1;
    public RanklistAdapter ranklistAdapter;
    public ArrayList<PlayerScore> playerScores = new ArrayList<PlayerScore>();
    public Spinner spinner;
    public EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner_connect_num);
        ArrayAdapter<CharSequence> spinnerAdapter
                = ArrayAdapter.createFromResource(this, R.array.difficulties,
                android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ListView rankListView = findViewById(R.id.list_view_ranklist);
        ranklistAdapter = new RanklistAdapter(this, playerScores);
        rankListView.setAdapter(ranklistAdapter);
        rankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerScore playerScore = (PlayerScore) parent.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), playerScore.getUsername() + " " +
                        playerScore.getDifficulty() + " " + playerScore.getScore(), Toast.LENGTH_SHORT).show();
            }
        });

        editText = (EditText) findViewById(R.id.edit_text_username);

        Button startGameButton = (Button) findViewById(R.id.button_start_game);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);

        String username = editText.getText().toString();
        Integer difficulty = Integer.valueOf(spinner.getSelectedItem().toString());
        intent.putExtra(USERNAME, username);
        intent.putExtra(DIFFICULTY, difficulty);

        startActivityForResult(intent, REQUEST_GAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.REQUEST_GAME) {
            if (data != null) {
                String username = data.getStringExtra(USERNAME);
                int difficulty = data.getIntExtra(DIFFICULTY, 0);
                int score = data.getIntExtra(SCORE, 0);

                PlayerScore playerScore = new PlayerScore(username, difficulty, score);
                playerScores.add(playerScore);
                ranklistAdapter.notifyDataSetChanged();
                if (resultCode != Activity.RESULT_OK) {
                    Toast.makeText(this, username + " " + getString(R.string.failed), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
