package com.notecardgame.isayyuhh.notecardgame.activity;

import com.notecardgame.isayyuhh.notecardgame.fragment_dialog.AddDialogFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_item.ItemFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment_list.ListFragment;
import com.notecardgame.isayyuhh.notecardgame.object.Notecard;
import com.notecardgame.isayyuhh.notecardgame.object.Stack;

import java.util.List;

/**
 * Created by isayyuhh on 2/2/16.
 */
public interface ActivityCallback {

    /**
     * Sets new fragment
     *
     * @param fragment Fragment to transition to
     */
    void setListFragment(ListFragment fragment);

    /**
     * Sets new fragment
     *
     * @param fragment Fragment to transition to
     */
    void setItemFragment(ItemFragment fragment);

    /**
     * Sets new dialog fragment
     *
     * @param fragment Dialog fragment to transition to
     */
    void setDialogFragment(AddDialogFragment fragment);

    /**
     * Pop fragment
     */
    void popFragment();

    /**
     * Sets toolbar title to desired string
     *
     * @param title String to set title to
     */
    void setToolbarTitle(String title);

    /**
     * Updates action menu
     */
    void updateMenu();

    /**
     * Gets string from resources
     *
     * @param id Resource id
     * @return String from resources
     */
    String getStr(int id);

    /**
     * Gets string array from resources
     *
     * @param id Resource id
     * @return String array from resources
     */
    String[] getStrArr(int id);

    /**
     * Gets color from resources
     *
     * @param id Resource id
     * @return
     */
    int getCol(int id);

    /**
     * Adds stack to internal storage file
     *
     * @param stack Stack to add
     */
    void addStack(Stack stack);

    /**
     * Deletes stack from internal storage file
     *
     * @param name Name of stack to delete
     */
    void deleteStack(String name);

    /**
     * Swaps position of stacks
     *
     * @param positionOne Position of first stack
     * @param positionTwo Position of second stack
     */
    void swapStacks(int positionOne, int positionTwo);

    /**
     * Searches for stack in reference of stacks
     *
     * @param name Name of stack to search for
     * @return Stack found
     */
    Stack findStack(String name);

    /**
     * Searches for stack at given position
     *
     * @param position Position of stack in reference of stacks
     * @return Stack found
     */
    Stack stacksAt(int position);

    /**
     * Gives a reference to the stacks
     *
     * @return An immutable reference to the stacks
     */
    List<Stack> getStacks();

    /**
     * Updates internal storage file and reference to stacks
     */
    void update();

    /**
     * Adds notecard to given stack
     *
     * @param notecard Notecard to add to stack
     * @param name     Name of stack to add to
     */
    void addNotecardToStack(Notecard notecard, String name, int position);

    /**
     * Deletes notecard from given stack
     *
     * @param notecard Notecard to delete from stack
     * @param name     Name of stack to delete from
     */
    void removeNotecardFromStack(Notecard notecard, String name);

    /**
     * Find notecard given stack name and front of notecard
     *
     * @param stackName     Name of stack
     * @param notecardFront Front of notecard
     * @return
     */
    Notecard findNotecardInStack(String stackName, String notecardFront);
}

