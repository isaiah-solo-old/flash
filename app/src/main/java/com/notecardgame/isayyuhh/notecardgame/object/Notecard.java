package com.notecardgame.isayyuhh.notecardgame.object;

/**
 * Created by isayyuhh on 2/3/16.
 */
public class Notecard extends Paper {

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

    /**
     * Sets front of notecard
     * @param front New front string
     */
    public void setFront (String front) {
        this.front = front;
    }

    /**
     * Sets back of notecard
     * @param back New back string
     */
    public void setBack (String back) {
        this.back = back;
    }
}
