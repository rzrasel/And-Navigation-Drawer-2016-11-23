package com.sm.navigationdrawerone;

/**
 * Created by Rz Rasel on 2016-11-23.
 */

public class ModelDrawerData {
    public int icon;
    public String title;

    // Constructor.
    public ModelDrawerData(int argIcon, String argTitle) {

        this.icon = argIcon;
        this.title = argTitle;
    }

    public String getTitle() {
        return title;
    }
}