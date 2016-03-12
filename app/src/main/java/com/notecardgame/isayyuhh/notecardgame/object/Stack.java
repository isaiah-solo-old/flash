package com.notecardgame.isayyuhh.notecardgame.object;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Stack extends Paper {

    /**
     * Fields
     */
    private String name;
    private List<Notecard> notecards;

    /**
     * Constructor for stack with given stack name
     *
     * @param name Name of new stack
     */
    public Stack(String name) {
        this.name = name;
        this.notecards = new ArrayList<>();
    }

    /**
     * Gets the name of the stack
     *
     * @return Name of stack
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the size of the stack
     *
     * @return Amount of notecards in the stack
     */
    public int size() {
        return this.notecards.size();
    }

    /**
     * Adds a notecard to the stack
     *
     * @param notecard Notecard to add to stack
     */
    public void addNotecard(Notecard notecard) {
        this.notecards.add(0, notecard);
    }

    /**
     * Removes a notecard from the stack
     *
     * @param front Front of notecard to remove from stack
     */
    public void removeNotecard(String front) {
        for (Iterator<Notecard> iter = notecards.listIterator(); iter.hasNext(); ) {
            Notecard curr = iter.next();
            if (curr.getFront().compareTo(front) == 0) {
                iter.remove();
                break;
            }
        }
    }

    /**
     * Changes notecards' positions
     *
     * @param positionOne Position of first notecard
     * @param positionTwo Position of second notecard
     */
    public void swapNotecards(int positionOne, int positionTwo) {
        Collections.swap(this.notecards, positionOne, positionTwo);
    }

    /**
     * Find notecard in stack
     *
     * @param front Front of notecard to find in stack
     * @return
     */
    public Notecard find(String front) {
        Notecard foundNotecard = null;
        for (Notecard notecard : this.notecards) {
            if (notecard.getFront().compareTo(front) == 0) {
                foundNotecard = notecard;
                break;
            }
        }
        return foundNotecard;
    }

    /**
     * Gives notecard at specified position
     *
     * @param position Position in stack
     * @return
     */
    public Notecard at(int position) {
        return this.notecards.get(position);
    }

    /**
     * Gives json string of stack object
     *
     * @return json string
     */
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Get notecard position in stack
     *
     * @param front Front of notecard to find in stack
     * @return
     */
    public int getNotecardPosition(String front) {
        int position = -1;
        for (int i = 0; i < notecards.size(); i++) {
            if (notecards.get(i).getFront().compareTo(front) == 0) {
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * Gives a reference to the notecards
     *
     * @return An immutable reference to the notecards
     */
    public List<Notecard> getNotecards() {
        return Collections.unmodifiableList(this.notecards);
    }
}
