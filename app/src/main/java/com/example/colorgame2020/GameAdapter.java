package com.example.colorgame2020;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

public class GameAdapter extends BaseAdapter {

    public ArrayList<ColorElement> elements;

    public GameAdapter(ArrayList<ColorElement> elements) {
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater
                    = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.colorlist_item, parent, false);
        }

        ColorElement element = elements.get(position);
        TextView textView = convertView.findViewById(R.id.list_element_value);
        textView.setText(String.valueOf(element.getValue()));

        Context context = parent.getContext();

        LinearLayout linearLayout = convertView.findViewById(R.id.colorlist_layout);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                        context.getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
                        context.getResources().getDisplayMetrics())
        );

        linearLayout.removeAllViewsInLayout();
        int viewNumber = element.getColorsSize();

        TextView[] views = new TextView[viewNumber];
        for (int i = 0; i < viewNumber; i++) {
            views[i] = new TextView(context);
            views[i].setLayoutParams(lp);
            views[i].setBackgroundColor(element.getColor(i));
            linearLayout.addView(views[i]);

            Space space = new Space(context);
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    5, context.getResources().getDisplayMetrics());
            space.setLayoutParams(new LinearLayout.LayoutParams(padding, padding));
            linearLayout.addView(space);
        }
        convertView.setBackgroundColor(element.getCurrColor());

        return convertView;
    }
}
