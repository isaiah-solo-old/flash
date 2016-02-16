package com.notecardgame.isayyuhh.notecardgame.object;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Notecard {
    /** Fields */
    private String front;
    private String back;

    /** Constructors */
    public Notecard (String front, String back) {
        this.front = front;
        this.back = back;
    }

    /** Functions */
    public String getFront () {
        return this.front;
    }

    public String getBack () {
        return this.back;
    }
}
