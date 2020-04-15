package com.example.colorgame2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String username;
    private int difficulty;
    private GameAdapter gameAdapter;
    private int curr_score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra(MainActivity.USERNAME);
            difficulty = intent.getIntExtra(MainActivity.DIFFICULTY, 3);
        }

        gameAdapter = new GameAdapter(getStartList());
        ListView listView = findViewById(R.id.list_view_gamelist);
        listView.setAdapter(gameAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Click((GameAdapter) parent.getAdapter(), position);
            }
        });

        Button addButton = findViewById(R.id.button_add_row);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameAdapter.elements.add(getRandomColorElement());
                gameAdapter.notifyDataSetChanged();
                updateScore(10);
            }
        });

        Button abortButton = findViewById(R.id.button_abort_game);
        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abortGame();
            }
        });

        updateScore(0);

    }

    private void abortGame() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.USERNAME, username);
        intent.putExtra(MainActivity.DIFFICULTY, difficulty);
        intent.putExtra(MainActivity.SCORE, 1000000);
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }

    private void endGame() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.USERNAME, username);
        intent.putExtra(MainActivity.DIFFICULTY, difficulty);
        intent.putExtra(MainActivity.SCORE, curr_score);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void updateScore(int value) {
        TextView textView = findViewById(R.id.text_view_score);
        curr_score = curr_score + value;
        textView.setText(String.valueOf(curr_score));
    }

    private void Click(GameAdapter adapter, int position) {
        ColorElement element = (ColorElement) adapter.getItem(position);
        element.changeColor();
        updateScore(element.getValue());

        ArrayList<ColorElement> list = adapter.elements;

        while (true) {
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                int j = i;
                while (j < list.size() && list.get(i).getCurrColor() == list.get(j).getCurrColor()) {
                    j++;
                }
                j--;
                if (j - i + 1 >= difficulty) {
                    found = true;
                    list.subList(i, j + 1).clear();
                    break;
                }
            }
            if (!found || list.isEmpty()) {
                break;
            }
        }

        adapter.notifyDataSetChanged();

        if (list.isEmpty()) {
            endGame();
        }
    }

    private ColorElement getRandomColorElement() {
        Random random = new Random();
        int value = 1 + random.nextInt(10);
        int colorsSize = 3 + random.nextInt(3);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < colorsSize; i++) {
            colors.add(ColorElement.COLORS[random.nextInt(5)]);
        }
        return new ColorElement(value, colors);
    }

    private ArrayList<ColorElement> getStartList() {
        ArrayList<ColorElement> elements = new ArrayList<ColorElement>();
        for (int i = 0; i < 5; i++) {
            elements.add(getRandomColorElement());
        }
        return elements;
    }
}
