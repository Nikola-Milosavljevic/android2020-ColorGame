package com.example.colorgame2020;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RanklistAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<PlayerScore> playerScores;

    public RanklistAdapter(Context context, ArrayList<PlayerScore> playerScores) {
        this.context = context;
        this.playerScores = playerScores;
    }

    @Override
    public int getCount() {
        return playerScores.size();
    }

    @Override
    public Object getItem(int position) {
        return playerScores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater
                    = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ranklist_item, null, false);
        }

        TextView username_view = (TextView) convertView.findViewById(R.id.text_view_ranklist_item_username);
        TextView difficulty_view = (TextView) convertView.findViewById(R.id.text_view_ranklist_item_difficulty);
        TextView score_view = (TextView) convertView.findViewById(R.id.text_view_ranklist_item_score);

        username_view.setText(playerScores.get(position).getUsername());
        username_view.setTextColor(Color.BLUE);
        difficulty_view.setText(String.valueOf(playerScores.get(position).getDifficulty()));
        score_view.setText(String.valueOf(playerScores.get(position).getScore()));

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.GRAY);
        } else {
            convertView.setBackgroundColor(Color.MAGENTA);
        }

        return convertView;
    }
}
