package com.notecardgame.isayyuhh.notecardgame.object;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Notecard {

    /**
     * Fields
     */
    private String front;
    private String back;

    /**
     * Constructor specifying front and back
     * @param front Text on front of notecard
     * @param back Text on back of notecard
     */
    public Notecard (String front, String back) {
        this.front = front;
        this.back = back;
    }

    /**
     * Gets the front of the notecard
     * @return Front of notecard
     */
    public String getFront () {
        return this.front;
    }

    /**
     * Gets the back of the notecard
     * @return Back of notecard
     */
    public String getBack () {
        return this.back;
    }
}
