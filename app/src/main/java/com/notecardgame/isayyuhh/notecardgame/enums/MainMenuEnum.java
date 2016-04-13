package com.notecardgame.isayyuhh.notecardgame.enums;

import com.notecardgame.isayyuhh.notecardgame.fragment.list.MainMenuListFragment;
import com.notecardgame.isayyuhh.notecardgame.fragment.list.StackListFragment;

/**
 * Created by isayyuhh on 4/12/16.
 */
public enum MainMenuEnum {
    ALL("Your Notecard Stacks", StackListFragment.class),
    PRESENTATION("Presentation (Coming Soon)", MainMenuListFragment.class),
    QUIZ("Quiz! (Coming Soon)", MainMenuListFragment.class);

    private String string;
    private Class fragmentClass;

    MainMenuEnum(String string, Class fragmentClass) {
        this.string = string;
        this.fragmentClass = fragmentClass;
    }

    public String getString() {
        return this.string;
    }

    public Class getFragmentClass() {
        return this.fragmentClass;
    }
}
