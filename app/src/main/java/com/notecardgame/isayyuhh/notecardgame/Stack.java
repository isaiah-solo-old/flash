package com.notecardgame.isayyuhh.notecardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Stack {

    /** Fields */
    private String name;
    private List<Notecard> stack;

    /** Constructor */
    public Stack (String name) {
        this.name = name;
    }

    /** Adds Notecard to the List */
    public void addNotecard (Notecard newNotecard) {
        stack.add(newNotecard);
    }
}
