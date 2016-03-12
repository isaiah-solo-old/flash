package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.object.Paper;

import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public abstract class ListAdapter extends ArrayAdapter<Paper> {

    /**
     * Fields
     */
    protected ActivityCallback ac;

    /**
     * Adapter constructor
     *
     * @param context Activity context
     */
    public ListAdapter(Context context, int res, ActivityCallback ac) {
        super(context, res);
        this.ac = ac;
    }

    /**
     * Sets data to adapter
     *
     * @param list List to add to adapter
     */
    public void setData(List<Paper> list) {
        this.clear();
        for (Paper paper : list) {
            this.add(paper);
        }
        this.notifyDataSetChanged();
    }
}
