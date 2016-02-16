package com.notecardgame.isayyuhh.notecardgame.object;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Stack {

    /**
     * Fields
     */
    private String name;
    private List<Notecard> notecards;

    /**
     * Constructor for stack with given stack name
     * @param name Name of new stack
     */
    public Stack (String name) {
        this.name = name;
        this.notecards = new ArrayList<>();
    }

    /**
     * Gets the name of the stack
     * @return Name of stack
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a notecard to the stack
     * @param notecard Notecard to add to stack
     */
    public void addNotecard (Notecard notecard) {
        this.notecards.add(notecard);
    }

    /**
     * Removes a notecard from the stack
     * @param notecard Notecard to remove from stack
     */
    public void removeNotecard (Notecard notecard) {
        for (Iterator<Notecard> iter = notecards.listIterator(); iter.hasNext();) {
            Notecard curr = iter.next();
            if (curr.getFront().compareTo(notecard.getFront()) == 0) {
                iter.remove();
                break;
            }
        }
    }

    /**
     * Gives notecard at specified position
     * @param position Position in stack
     * @return
     */
    public Notecard at (int position) {
        return this.notecards.get(position);
    }

    /**
     * Gives json string of stack object
     * @return json string
     */
    public String getJson () {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Gives a reference to the notecards
     * @return An immutable reference to the notecards
     */
    public List<Notecard> getNotecards () {
        return Collections.unmodifiableList(this.notecards);
    }
}
