package com.notecardgame.isayyuhh.notecardgame.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.notecardgame.isayyuhh.notecardgame.adapter.StackListAdapter;
import com.notecardgame.isayyuhh.notecardgame.fragment.MainMenuListFragment;
import com.notecardgame.isayyuhh.notecardgame.listener.ItemClickListener;
import com.notecardgame.isayyuhh.notecardgame.listener.MultiChoiceListener;
import com.notecardgame.isayyuhh.notecardgame.logic.StackListLogic;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;
import com.notecardgame.isayyuhh.notecardgame.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback {

    /**
     * Fields
     */
    private FragmentManager fm;
    private Toolbar mToolbar;
    private List<Stack> stacks;

    /**
     * OnCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Sets View
        setContentView(R.layout.activity_main);
        initialize();
    }

    /**
     * Initializes app
     */
    private void initialize() {
        // Initializes Stacks
        this.updateStacks();

        // Sets Toolbar and title
        this.mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.mToolbar.setTitleTextColor(this.getResources().getColor(R.color.colorWhite));

        // Sets FragmentManager
        this.fm = getSupportFragmentManager();
        MainMenuListFragment newFragment = new MainMenuListFragment();

        // Begins initial FragmentTransaction
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.listFragment, newFragment);
        ft.commit();
    }

    private void updateFile () {
        String filename = getResources().getString(R.string.stack_file_name);
        String newline = getResources().getString(R.string.new_line);
        try {
            FileOutputStream fos;
            fos = this.openFileOutput(filename, Context.MODE_PRIVATE);
            for (Stack stack: this.stacks) {
                fos.write(stack.getStackName().getBytes());
                fos.write(newline.getBytes());
            }
            fos.close();
        } catch (IOException ioe) {
            Log.e("FAIL", "File output failed");
            return;
        }
    }

    private void updateStacks () {
        this.stacks = new ArrayList<>();
        String filename = getResources().getString(R.string.stack_file_name);

        try {
            FileInputStream fis = this.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                this.stacks.add(new Stack(line));
            }
            br.close();
        } catch (IOException ioe) {
            Log.e("FAIL", "File input failed");
            return;
        }
    }

    @Override
    public String getStr(int id) {
        return this.getResources().getString(id);
    }

    @Override
    public String[] getStrArr(int id) {
        return this.getResources().getStringArray(id);
    }

    /**
     * Sets up new Fragment
     */
    @Override
    public void setFragment(Fragment newFragment) {
        // Starts FragmentTransaction
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.listFragment, newFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Sets up new DialogFragment
     */
    @Override
    public void setDialogFragment(DialogFragment newFragment) {
        FragmentTransaction ft = this.fm.beginTransaction();
        Fragment prev = this.fm.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        newFragment.show(ft, "dialog");
    }

    /**
     * Changes current Toolbar title
     */
    @Override
    public void setToolbarTitle(String title) {
        this.mToolbar.setTitle(title);
    }

    @Override
    public Stack stacksAt(int position) {
        return this.stacks.get(position);
    }

    /**
     * Updates the current Stacks in the Internal Storage
     */
    @Override
    public void refreshStacksList(View view) {
        this.updateStacks();

        ListView listView = (ListView) view.findViewById(R.id.menu_list);
        StackListAdapter adp = new StackListAdapter(this, this);
        listView.setAdapter(adp);
        adp.setData(stacks);
        listView.setOnItemClickListener(new ItemClickListener(this, new StackListLogic(this)));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new MultiChoiceListener(listView, adp));
        listView.setItemsCanFocus(false);
    }

    /**
     * Adds a Stack to the Internal Storage
     */
    @Override
    public void addStack(String text) {
        Stack newStack = new Stack(text);
        this.stacks.add(newStack);

        updateFile();
    }

    @Override
    public void deleteStack(String name) {
        for (Stack stack: this.stacks) {
            if (stack.getStackName().equals(name)) {
                this.stacks.remove(stack);
                updateFile();
                return;
            }
        }
        Log.e("FAIL", "Item does not exist in List");
    }
}
