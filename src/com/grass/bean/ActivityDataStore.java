package com.grass.bean;

import java.util.ArrayList;

/**
 * Created by grass on 15/12/20.
 */
public class ActivityDataStore {
    private ArrayList<ActivityInfo> mList = new ArrayList<>();
    private static ActivityDataStore ourInstance = new ActivityDataStore();

    public static ActivityDataStore getInstance() {
        return ourInstance;
    }

    private ActivityDataStore() {
    }

    public void add(ActivityInfo info) {
        if (info == null) {
            return;
        }
        if (!mList.contains(info)) {
            mList.add(info);
        }
    }

    public void add(String name, String des, String fullClassName) {
        ActivityInfo info = new ActivityInfo(name, des, fullClassName);
        if (info == null) {
            return;
        }
        if (!mList.contains(info)) {
            mList.add(info);
        }
    }
}
