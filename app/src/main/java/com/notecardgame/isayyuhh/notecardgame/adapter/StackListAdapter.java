package com.notecardgame.isayyuhh.notecardgame.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.notecardgame.isayyuhh.notecardgame.activity.ActivityCallback;
import com.notecardgame.isayyuhh.notecardgame.R;
import com.notecardgame.isayyuhh.notecardgame.object.Paper;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class StackListAdapter extends ListAdapter {

    /**
     * Fields
     */
    private ActivityCallback ac;

    /**
     * Adapter constructor
     * @param context Activity context
     * @param ac Reference to Activity
     */
    public StackListAdapter(Context context, ActivityCallback ac) {
        super(context, R.layout.list_item_stack);
        this.ac = ac;
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
            convertView = inflater.inflate(R.layout.list_item_stack, null);
        }
        Stack stack = (Stack) this.getItem(position);
        TextView stackName = (TextView) convertView.findViewById(R.id.stack_name);
        stackName.setText(stack.getName());
        return convertView;
    }

    /**
     * Removes stack from stacks and listview
     * @param stack Stack to remove from stacks and listview
     */
    @Override
    public void remove(Paper stack) {
        super.remove(stack);
        this.ac.deleteStack(((Stack) stack).getName());
        this.notifyDataSetChanged();
    }
}
