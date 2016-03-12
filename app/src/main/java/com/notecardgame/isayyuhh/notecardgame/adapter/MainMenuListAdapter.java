package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Note;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class MainMenuListAdapter extends ListAdapter {

    /**
     * Adapter constructor
     *
     * @param context Activity context
     */
    public MainMenuListAdapter(Context context, ActivityCallback ac) {
        super(context, R.layout.list_item_stack, ac);
    }

    /**
     * Sets item view
     *
     * @param position    Reference to position
     * @param convertView View to inflate
     * @param parent      Reference to ViewGroup
     * @return View to inflate
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_main, null);
        }
        String string = ((Note) this.getItem(position)).value;

        TextView listItem = (TextView) convertView.findViewById(R.id.list_item_main);
        listItem.setText(string);
        return convertView;
    }
}
