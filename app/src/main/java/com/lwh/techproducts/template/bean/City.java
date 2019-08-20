package com.lwh.techproducts.template.bean;

import java.io.Serializable;

public class City implements Serializable {

    private String name;
    private int sceneResId;

    public City(String name, int sceneResId) {
        this.name = name;
        this.sceneResId = sceneResId;
    }

    public String getName() {
        return name;
    }

    public int getSceneResId() {
        return sceneResId;
    }
}
