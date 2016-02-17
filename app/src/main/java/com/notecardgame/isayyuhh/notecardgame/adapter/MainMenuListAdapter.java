package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.logic.ListLogic;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class MainMenuListAdapter extends ArrayAdapter<String> {
    private ActivityCallback mCallback;
    private ListLogic listLogic;

    public MainMenuListAdapter(Context context, ActivityCallback mCallback) {
        super(context, R.layout.item_stack);
        this.mCallback = mCallback;
    }

    public void setData(String[] array) {
        this.clear();
        for(String string: array) {
            this.add(string);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_main, null);
        }
        String string = this.getItem(position);

        TextView listItem = (TextView) convertView.findViewById(R.id.list_item_main);
        listItem.setText(string);
        return convertView;
    }
}
