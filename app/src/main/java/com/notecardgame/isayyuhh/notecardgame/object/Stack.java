package com.notecardgame.isayyuhh.notecardgame.object;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Stack {

    /** Fields */
    private String name;
    private List<Notecard> notecards;

    /** Constructor */
    public Stack (String name) {
        this.name = name;
        notecards = new ArrayList<>();
    }

    /** Adds Notecard to the List */
    public void addNotecard (Notecard newNotecard) {
        this.notecards.add(newNotecard);
    }

    public void addNotecard (String json) {
        Gson gson = new Gson();
        Notecard notecard = gson.fromJson(json, Notecard.class);
        this.notecards.add(notecard);
    }

    public void removeNotecard (Notecard notecard) {
        notecards.remove(notecard);
    }

    /** Gets Stack name */
    public String getName() {
        return this.name;
    }

    public Notecard at (int position) {
        return notecards.get(position);
    }

    public int size () {
        return notecards.size();
    }

    public String getJson () {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
