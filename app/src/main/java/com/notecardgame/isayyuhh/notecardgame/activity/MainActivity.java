package com.notecardgame.isayyuhh.notecardgame.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.gson.Gson;
import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_item.ItemFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_list.ListFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_list.MainMenuListFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;
import com.notecardgame.isayyuhh.notecardgame.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class MainActivity extends AppCompatActivity implements ActivityCallback {

    /**
     * Fields
     */
    private boolean init;
    private Toolbar mToolbar;
    private FragmentManager fm;
    private List<Stack> stacks;

    /**
     * On created activity
     * @param savedInstanceState Reference to the saved instance state
     */
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.initialize();
    }

    /**
     * Initializes variables
     */
    private void initialize() {
        this.init = true;

        this.updateStacks();

        this.mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.mToolbar.setTitleTextColor(this.getResources().getColor(R.color.colorToolBarText));
        this.setSupportActionBar(mToolbar);

        this.fm = getSupportFragmentManager();
        MainMenuListFragment fragment = new MainMenuListFragment();
        this.setListFragment(fragment);
    }

    /**
     * Updates internal storage file from reference to stacks
     */
    private void updateFile() {
        String filename = getResources().getString(R.string.filename_stacks);
        String newline = getResources().getString(R.string.literal_newline);
        try {
            FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE);
            for (Stack stack: this.stacks) {
                fos.write(stack.getJson().getBytes());
                fos.write(newline.getBytes());
            }
            fos.close();
        } catch (IOException ioe) {
            Log.e("FAIL", "File output failed");
            return;
        }
    }

    /**
     * Updates reference to stacks from internal storage file
     */
    private void updateStacks() {
        this.stacks = new ArrayList<>();
        String filename = getResources().getString(R.string.filename_stacks);
        Gson gson = new Gson();

        try {
            FileInputStream fis = this.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                Stack stack = gson.fromJson(line, Stack.class);

                this.stacks.add(stack);
            }
            br.close();
        } catch (IOException ioe) {
            Log.e("FAIL", "File input failed");
            return;
        }
    }

    /**
     * Sets new fragment
     * @param fragment Fragment to transition to
     */
    @Override
    public void setListFragment(ListFragment fragment) {
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        if (! this.init) ft.addToBackStack(null);
        else this.init = false;
        ft.commit();
    }

    @Override
    public void setItemFragment(ItemFragment fragment) {
        FragmentTransaction ft = this.fm.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        if (! this.init) ft.addToBackStack(null);
        else this.init = false;
        ft.commit();
    }

    /**
     * Sets new dialog fragment
     * @param fragment Dialog fragment to transition to
     */
    @Override
    public void setDialogFragment(AddDialogFragment fragment) {
        FragmentTransaction ft = this.fm.beginTransaction();
        Fragment prev = this.fm.findFragmentByTag(this.getStr(R.string.tag_dialog));
        if (prev != null) ft.remove(prev);
        ft.addToBackStack(null);
        fragment.show(ft, this.getStr(R.string.tag_dialog));
    }

    /**
     * Pop fragment
     */
    @Override
    public void popFragment() {
        this.fm.popBackStack();
    }

    /**
     * Sets toolbar title to desired string
     * @param title String to set title to
     */
    @Override
    public void setToolbarTitle(String title) {
        this.mToolbar.setTitle(title);
    }

    /**
     * Gets string from resources
     * @param id Resource id
     * @return String from resources
     */
    @Override
    public String getStr(int id) {
        return this.getResources().getString(id);
    }

    /**
     * Gets string array from resources
     * @param id Resource id
     * @return String array from resources
     */
    @Override
    public String[] getStrArr(int id) {
        return this.getResources().getStringArray(id);
    }

    /**
     * Gets color from resources
     * @param id Resource id
     * @return
     */
    @Override
    public int getCol(int id) {
        return this.getResources().getColor(id);
    }

    /**
     * Adds stack to internal storage file
     * @param stack Stack to add
     */
    @Override
    public void addStack(Stack stack) {
        this.stacks.add(0, stack);
        this.update();
    }

    /**
     * Deletes stack from internal storage file
     * @param name Name of stack to delete
     */
    @Override
    public void deleteStack(String name) {
        Stack stack = this.findStack(name);
        if (stack != null) this.stacks.remove(stack);

        this.update();
    }

    /**
     * Searches for stack in reference of stacks
     * @param name Name of stack to search for
     * @return Stack found
     */
    @Override
    public Stack findStack(String name) {
        Stack foundStack = null;
        for (Stack stack: this.stacks) {
            if (name.compareTo(stack.getName()) == 0) {
                foundStack = stack;
                break;
            }
        }
        return foundStack;
    }

    /**
     * Searches for stack at given position
     * @param position Position of stack in reference of stacks
     * @return Stack found
     */
    @Override
    public Stack stacksAt(int position) {
        return this.stacks.get(position);
    }

    /**
     * Gives a reference to the stacks
     * @return An immutable reference to the stacks
     */
    @Override
    public List<Stack> getStacks() {
        return Collections.unmodifiableList(this.stacks);
    }

    /**
     * Updates internal storage file and reference to stacks
     */
    @Override
    public void update() {
        this.updateFile();
        this.updateStacks();
    }

    /**
     * Adds notecard to given stack
     * @param notecard Notecard to add to stack
     * @param name Name of stack to add to
     */
    @Override
    public void addNotecardToStack(Notecard notecard, String name) {
        Stack stack = this.findStack(name);
        stack.addNotecard(notecard);

        this.update();
    }

    /**
     * Deletes notecard from given stack
     * @param notecard Notecard to delete from stack
     * @param name Name of stack to delete from
     */
    @Override
    public void removeNotecardFromStack(Notecard notecard, String name) {
        Stack stack = this.findStack(name);
        stack.removeNotecard(notecard.getFront());

        this.update();
    }

    /**
     * Find notecard given stack name and front of notecard
     * @param stackName Name of stack
     * @param notecardFront Front of notecard
     * @return
     */
    @Override
    public Notecard findNotecardInStack(String stackName, String notecardFront) {
        Notecard foundNotecard = null;
        for (Stack stack: this.stacks) {
            if (stackName.compareTo(stack.getName()) == 0) {
                foundNotecard = stack.find(notecardFront);
                break;
            }
        }
        return foundNotecard;
    }
}
