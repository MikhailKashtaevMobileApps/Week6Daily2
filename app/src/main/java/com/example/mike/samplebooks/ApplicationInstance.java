package com.example.mike.samplebooks;

import android.app.Application;

public class ApplicationInstance extends Application {
    private static ApplicationInstance instance;


    public ApplicationInstance() {
        instance = this;
    }

    public static ApplicationInstance getInstance() {
        return instance;
    }


    // Creating cache for db
    public Long lastUpdated = null;

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
