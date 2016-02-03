package com.notecardgame.isayyuhh.notecardgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class StackMenuAdapter extends ArrayAdapter<Stack> {
    public StackMenuAdapter(Context context) {
        super(context, R.layout.item_stack);
    }

    public void setData(List<Stack> stacks) {
        clear();
        /*
        for(Event e : stacks) {
            add(e);
        }
        */
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_stack, null);
        }
        return convertView;
    }
}
