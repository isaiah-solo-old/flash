package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class MainMenuListAdapter extends ArrayAdapter<String> {

    /**
     * Adapter constructor
     * @param context Activity context
     */
    public MainMenuListAdapter(Context context) {
        super(context, R.layout.list_item_stack);
    }

    /**
     * Sets data to adapter
     * @param array Array to add to adapter
     */
    public void setData(String[] array) {
        this.clear();
        for(String string: array) {
            this.add(string);
        }
        this.notifyDataSetChanged();
    }

    /**
     * Sets item view
     * @param position Reference to position
     * @param convertView View to inflate
     * @param parent Reference to ViewGroup
     * @return View to inflate
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_main, null);
        }
        String string = this.getItem(position);

        TextView listItem = (TextView) convertView.findViewById(R.id.list_item_main);
        listItem.setText(string);
        return convertView;
    }
}
